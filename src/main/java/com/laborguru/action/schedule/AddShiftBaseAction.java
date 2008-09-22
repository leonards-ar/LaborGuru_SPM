/*
 * File name: AddShiftBaseAction.java
 * Creation date: Sep 20, 2008 1:17:19 PM
 * Copyright Mindpool
 */
package com.laborguru.action.schedule;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.laborguru.action.SpmAction;
import com.laborguru.action.SpmActionResult;
import com.laborguru.frontend.model.ScheduleHourLabelElement;
import com.laborguru.frontend.model.ScheduleRow;
import com.laborguru.frontend.model.WeekDaySelector;
import com.laborguru.model.Employee;
import com.laborguru.model.EmployeeSchedule;
import com.laborguru.model.OperationTime;
import com.laborguru.model.Position;
import com.laborguru.model.Shift;
import com.laborguru.model.StoreSchedule;
import com.laborguru.util.CalendarUtils;
import com.laborguru.util.SpmConstants;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public abstract class AddShiftBaseAction extends SpmAction {
	private WeekDaySelector weekDaySelector;
	private String selectedDate;
	private String selectedWeekDay;
	private List<ScheduleHourLabelElement> scheduleLabelHours;
	private List<Date> scheduleIndividualHours;
	
	private StoreSchedule storeSchedule;
	
	public static final int MINUTES_INTERVAL = 15;
	
	/**
	 * 
	 */
	public AddShiftBaseAction() {
	}

	/**
	 * @return the weekDaySelector
	 */
	public WeekDaySelector getWeekDaySelector() {
		if(weekDaySelector == null) {
			weekDaySelector = new WeekDaySelector(getEmployeeStore().getFirstDayOfWeek());
		}
		return weekDaySelector;
	}
	
	/**
	 * @param weekDaySelector the weekDaySelector to set
	 */
	public void setWeekDaySelector(WeekDaySelector weekDaySelector) {
		this.weekDaySelector = weekDaySelector;
	}
	
	/**
	 * Actions that must be performed before
	 * this action is executed
	 */
	public abstract void prepareChangeWeek();
	
	/**
	 * Actions that must be performed before
	 * this action is executed
	 */
	public abstract void prepareChangeDay();
	
	/**
	 * Performs any needed calculations after
	 * a change week action is issued.
	 */
	protected abstract void processChangeWeek();

	/**
	 * Performs any needed calculations after
	 * a change day action is issued.
	 */
	protected abstract void processChangeDay();
	
	/**
	 * @return the selectedDate
	 */
	public String getSelectedDate() {
		return selectedDate;
	}

	/**
	 * @param selectedDate the selectedDate to set
	 */
	public void setSelectedDate(String selectedDate) {
		this.selectedDate = selectedDate;
	}

	/**
	 * @return the selectedWeekDay
	 */
	public String getSelectedWeekDay() {
		return selectedWeekDay;
	}

	/**
	 * @param selectedWeekDay the selectedWeekDay to set
	 */
	public void setSelectedWeekDay(String selectedWeekDay) {
		this.selectedWeekDay = selectedWeekDay;
	}
	
	/**
	 * 
	 * @return
	 */
	public String changeWeek() {
		getWeekDaySelector().setStringStartingWeekDay(getSelectedDate());
		getWeekDaySelector().setStringSelectedDay(getSelectedDate());
		
		processChangeWeek();

		return SpmActionResult.INPUT.getResult();
	}
	
	/**
	 * 
	 * @return
	 */
	public String changeDay() {
		getWeekDaySelector().setStringStartingWeekDay(getSelectedDate());
		getWeekDaySelector().setStringSelectedDay(getSelectedWeekDay());
		
		processChangeDay();
		
		return SpmActionResult.INPUT.getResult();
	}
	
	/**
	 * @return the storeSchedule
	 */
	public StoreSchedule getStoreSchedule() {
		if(storeSchedule == null) {
			storeSchedule = new StoreSchedule();
			storeSchedule.setStore(getEmployeeStore());
		}
		return storeSchedule;
	}

	/**
	 * @param storeSchedule the storeSchedule to set
	 */
	public void setStoreSchedule(StoreSchedule storeSchedule) {
		this.storeSchedule = storeSchedule;
	}
	
	/**
	 * This method returns a list with all the individaul 
	 * selectable times (For example all the 15 minutes
	 * periods from the store open hour to the store close
	 * hour)
	 * @return
	 */
	public List<Date> getScheduleIndividualHours() {
		if(this.scheduleIndividualHours == null) {
			if(getStoreSchedule() != null && getStoreSchedule().getStore() != null) {
				OperationTime operationTime = getStoreSchedule().getStore().getOperationTime(getWeekDaySelector().getSelectedDayOfWeek());
				Date closeHour = getScheduleCloseHour(operationTime.getCloseHour());
				Date d = operationTime != null ? getScheduleOpenHour(operationTime.getOpenHour()) : null;
				
				List<Date> hours = new ArrayList<Date>();
				while(d != null && d.getTime() < closeHour.getTime()) {
					hours.add(d);
					d = new Date(d.getTime() + MINUTES_INTERVAL * 60000L);
				}
				this.scheduleIndividualHours = hours;
			} else {
				this.scheduleIndividualHours = new ArrayList<Date>();
			}
		}
		return this.scheduleIndividualHours;
	}
	
	/**
	 * This method returns a list with the labels of the
	 * schedule hours
	 * @return
	 */
	public List<ScheduleHourLabelElement> getScheduleLabelHours() {
		if(this.scheduleLabelHours == null) {
			if(getStoreSchedule() != null && getStoreSchedule().getStore() != null) {
				OperationTime operationTime = getStoreSchedule().getStore().getOperationTime(getWeekDaySelector().getSelectedDayOfWeek());
				
				Date d = operationTime != null ? getScheduleOpenHour(operationTime.getOpenHour()) : null;
				Date baseCloseHour = getScheduleBaseHour(operationTime.getCloseHour());
				
				List<ScheduleHourLabelElement> hours = new ArrayList<ScheduleHourLabelElement>();
				
				// Open hour is a special case
				hours.add(new ScheduleHourLabelElement(getScheduleBaseHour(d), getHourColspan(d), getOpenHourSelectable(d)));
				
				d = getScheduleBaseHour(CalendarUtils.addOrSubstractHours(d, 1));

				while(d != null && d.getTime() < baseCloseHour.getTime()) {
					hours.add(new ScheduleHourLabelElement(d, getHourColspan(d), getHourColspan(d)));
					d = getScheduleBaseHour(CalendarUtils.addOrSubstractHours(d, 1));
				}
				
				if(operationTime.getCloseHour().getTime() > baseCloseHour.getTime()) {
					hours.add(new ScheduleHourLabelElement(baseCloseHour, getHourColspan(d), getCloseHourSelectable(operationTime.getCloseHour())));
				}
				
				this.scheduleLabelHours = hours;
				
			} else {
				this.scheduleLabelHours = new ArrayList<ScheduleHourLabelElement>();
			}
			
		}
		return this.scheduleLabelHours;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Integer> getScheduleIndividualStartHoursToIgnore() {
		Integer total = new Integer(60 / MINUTES_INTERVAL);
		List<Integer> toIgnore = new ArrayList<Integer>();
		Integer start = getScheduleLabelHours() != null && getScheduleLabelHours().size() > 0 ?  getScheduleLabelHours().get(0).getSelectableCount() : null;

		if(start != null && start > 0) {
			for(Integer i = start; i < total; i++) {
				toIgnore.add(i);
			}
		}
		
		return toIgnore;
	}

	/**
	 * 
	 * @return
	 */
	public List<Integer> getScheduleIndividualEndHoursToIgnore() {
		Integer total = new Integer(60 / MINUTES_INTERVAL);
		List<Integer> toIgnore = new ArrayList<Integer>();
		Integer start = getScheduleLabelHours() != null && getScheduleLabelHours().size() > 1 ?  getScheduleLabelHours().get(getScheduleLabelHours().size() - 1).getSelectableCount() : null;

		if(start != null && start > 0) {
			for(Integer i = start; i < total; i++) {
				toIgnore.add(i);
			}
		}
		
		return toIgnore;
	}
	
	/**
	 * 
	 * @param storeOpenHour
	 * @return
	 */
	private Date getScheduleOpenHour(Date storeOpenHour) {
		if(storeOpenHour != null) {
			Calendar cal = CalendarUtils.getCalendar(storeOpenHour);
			int minutes = cal.get(Calendar.MINUTE);
			for(int i = 0; MINUTES_INTERVAL * i <= 60 ;i++ ) {
				if(minutes <= MINUTES_INTERVAL * i) {
					cal.set(Calendar.MINUTE, MINUTES_INTERVAL * i);
					return cal.getTime();
				}
			}
			// This should never happend as minutes should always be less than 60
			return storeOpenHour;
		} else {
			return null;
		}
	}
	
	/**
	 * 
	 * @param storeOpenHour
	 * @return
	 */
	private Date getScheduleCloseHour(Date storeCloseHour) {
		if(storeCloseHour != null) {
			Calendar cal = CalendarUtils.getCalendar(storeCloseHour);
			int minutes = cal.get(Calendar.MINUTE);

			for(int i = 0; minutes > 0 && MINUTES_INTERVAL * i <= 60 ;i++ ) {
				if(minutes <= MINUTES_INTERVAL * i) {
					cal.set(Calendar.MINUTE, MINUTES_INTERVAL * i);
					return cal.getTime();
				}
			}
			// Null means that close hour is at the minute 0
			return storeCloseHour;
		} else {
			return null;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	private Date getScheduleBaseHour(Date storeCloseHour) {
		if(storeCloseHour != null) {
			Calendar cal = CalendarUtils.getCalendar(storeCloseHour);
			cal.set(Calendar.MINUTE, 0);
			return cal.getTime();
		} else {
			return null;
		}
		
	}
	
	/**
	 * 
	 * @param hour Hour that must be normalized according to MINUTES_INTERVAL
	 * @return
	 */
	private Integer getCloseHourSelectable(Date hour) {
		Calendar cal = CalendarUtils.getCalendar(hour);
		int minutes = cal.get(Calendar.MINUTE);
		for(int i = 0; MINUTES_INTERVAL * i <= 60 ;i++ ) {
			if(minutes <= MINUTES_INTERVAL * i) {
				return new Integer(i);
			}
		}
		// This should never happend as minutes should always be less than 60
		return null;
	}
	
	/**
	 * 
	 * @param hour
	 * @return
	 */
	private Integer getHourColspan(Date hour) {
		return new Integer(60 / MINUTES_INTERVAL);
	}
	
	/**
	 * 
	 * @param hour Hour that must be normalized according to MINUTES_INTERVAL
	 * @return
	 */
	private Integer getOpenHourSelectable(Date hour) {
		Calendar cal = CalendarUtils.getCalendar(hour);
		int minutes = cal.get(Calendar.MINUTE);
		for(int i = 0; MINUTES_INTERVAL * i <= 60 ;i++ ) {
			if(minutes <= MINUTES_INTERVAL * i) {
				return new Integer((60/MINUTES_INTERVAL) - i);
			}
		}
		// This should never happend as minutes should always be less than 60
		return null;
	}
	
	/**
	 * Builds the frontend object that will hold the information to be displayed
	 * in the GUI. If position is null, then all positions will be taken
	 * into account, if not the schedule will be filtered by position.
	 * The StoreSchedule object is used as the source of data.
	 * @param position
	 * @return
	 */
	protected List<ScheduleRow> buildScheduleFor(Position position) {
		if(getStoreSchedule() != null) {
			List<ScheduleRow> schedule = new ArrayList<ScheduleRow>();
			ScheduleRow aRow = null;
			
			for(EmployeeSchedule employeeSchedule : getStoreSchedule().getEmployeeSchedules()) {
				for(Shift shift : employeeSchedule.getShifts()) {
					if((position == null || shift.isBreak() || (position != null && isEqualPosition(position, shift.getPosition())))) {
						aRow = buildScheduleRow(employeeSchedule, shift, schedule); 
						if(aRow != null) {
							schedule.add(aRow);
						}
					}
				}
			}
			return schedule;
		} else {
			return new ArrayList<ScheduleRow>();
		}
	}

	/**
	 * 
	 * @param schedule
	 * @param position
	 */
	protected void setSchedule(List<ScheduleRow> schedule, Position position) {
		getStoreSchedule().setDay(getWeekDaySelector().getSelectedDay());
		getStoreSchedule().setStore(getEmployeeStore());
		
		Set<Integer> employeeIds = getDifferentEmployeeIds(schedule);
		
		for(Integer employeeId : employeeIds) {
			EmployeeSchedule employeeSchedule = getStoreSchedule().getEmployeeSchedule(new Employee(employeeId));
			if(employeeSchedule == null) {
				employeeSchedule = new EmployeeSchedule();
				employeeSchedule.setEmployee(new Employee(employeeId));
				employeeSchedule.setStoreSchedule(getStoreSchedule());
				getStoreSchedule().getEmployeeSchedules().add(employeeSchedule);
			}
			setShifts(getEmployeeSchedule(employeeId, schedule), employeeSchedule, position);
		}
		
		// Remove non existing shifts for the employee and for a position
		cleanStoreSchedule(employeeIds, position);
	}

	/**
	 * 
	 * @param employeeIds
	 * @param position
	 */
	private void cleanStoreSchedule(Set<Integer> employeeIds, Position position) {
		
	}

	/**
	 * 
	 * @param source
	 * @param employeeSchedule
	 * @param position
	 */
	private void setShifts(List<ScheduleRow> source, EmployeeSchedule employeeSchedule, Position position) {
		
	}
	
	/**
	 * 
	 * @param employeeId
	 * @param schedule
	 * @return
	 */
	private List<ScheduleRow> getEmployeeSchedule(Integer employeeId, List<ScheduleRow> schedule) {
		List<ScheduleRow> employeeSchedule = new ArrayList<ScheduleRow>();
		
		if(schedule != null) {
			for(ScheduleRow aRow : schedule) {
				if(employeeId != null && employeeId.equals(aRow.getEmployeeId())) {
					employeeSchedule.add(aRow);
				}
			}
		}
		return employeeSchedule;
	}
	
	/**
	 * 
	 * @param schedule
	 * @return
	 */
	private Set<Integer> getDifferentEmployeeIds(List<ScheduleRow> schedule) {
		Set<Integer> ids = new HashSet<Integer>();
		
		if(schedule != null) {
			for(ScheduleRow aRow : schedule) {
				if(!ids.contains(aRow.getEmployeeId())) {
					ids.add(aRow.getEmployeeId());
				}
			}
		}
		
		return ids;
	}
	
	/**
	 * Compares two positions by their ID
	 * @param pos1
	 * @param pos2
	 */
	private boolean isEqualPosition(Position pos1, Position pos2) {
		return pos1 != null && pos2 != null && isEqualId(pos1.getId(), pos2.getId());
	}

	/**
	 * 
	 * @param id1
	 * @param id2
	 * @return
	 */
	private boolean isEqualId(Integer id1, Integer id2) {
		return id1 != null && id1.equals(id2);
	}
	
	/**
	 * 
	 * @param schedule
	 * @param employee
	 * @param shift
	 * @return
	 */
	private boolean alreadyInSchedule(List<ScheduleRow> schedule, Employee employee, Shift shift) {
		for(ScheduleRow row : schedule) {
			if(isEqualId(row.getEmployeeId(), employee != null ? employee.getId() : null)) {
				if(shift.isBreak() && row.getPositionId() == null) {
					// Employee has only breaks
					return true;
				} else if(isEqualId(row.getPositionId(), shift.getPosition() != null ? shift.getPosition().getId() : null)) {
					// Position already taken into account
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * @param employeeSchedule
	 * @param shift
	 * @param schedule
	 * @return
	 */
	private ScheduleRow buildScheduleRow(EmployeeSchedule employeeSchedule, Shift shift, List<ScheduleRow> schedule) {
		if(!alreadyInSchedule(schedule, employeeSchedule.getEmployee(), shift) && employeeSchedule.getEmployee() != null) {
			ScheduleRow aRow = new ScheduleRow();
			aRow.setEmployeeId(employeeSchedule.getEmployee().getId());
			aRow.setEmployeeName(employeeSchedule.getEmployee().getFullName());
			aRow.setPositionId(shift.getPosition() != null ? shift.getPosition().getId() : null);
			
			
			List<Date> scheduleBuckets = getScheduleIndividualHours();
			List<String> occupation = initializeScheduleRow(scheduleBuckets);
			
			for(Shift aShift : employeeSchedule.getShifts()) {
				if(aShift.isBreak() || isEqualPosition(shift.getPosition(), aShift.getPosition())) {
					setScheduleOccupation(occupation, scheduleBuckets, aShift);
				} else {
					// Position changed!
					break;
				}
			}
			
			aRow.setSchedule(occupation);
			
			return aRow;
		} else {
			return null;
		}
	}
	
	/**
	 * 
	 * @param occupation
	 * @param scheduleBuckets
	 * @param shift
	 */
	private void setScheduleOccupation(List<String> occupation, List<Date> scheduleBuckets, Shift shift) {
		int from = getIndexOfBucket(shift.getFromHour(), scheduleBuckets);
		int to = getIndexOfBucket(shift.getToHour(), scheduleBuckets);
		for(int i = from; i <= to; i++) {
			occupation.set(i, shift.isBreak() ? SpmConstants.SCHEDULE_BREAK : String.valueOf(shift.getPosition().getId()));
		}
	}
	
	/**
	 * 
	 * @param hour
	 * @param scheduleBuckets
	 * @return
	 */
	private int getIndexOfBucket(Date hour, List<Date> scheduleBuckets) {
		Date anHour;
		for(int i = 0; i < scheduleBuckets.size(); i++) {
			anHour = scheduleBuckets.get(i);
			if(hour.getTime() >= anHour.getTime()) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * 
	 * @param scheduleBuckets
	 * @return
	 */
	private List<String> initializeScheduleRow(List<Date> scheduleBuckets) {
		List<String> scheduleRow = new ArrayList<String>(scheduleBuckets.size());
		for(int i = 0; i < scheduleBuckets.size(); i++) {
			scheduleRow.add(SpmConstants.SCHEDULE_FREE);
		}
		return scheduleRow;
	}

	/**
	 * @param scheduleLabelHours the scheduleLabelHours to set
	 */
	public void setScheduleLabelHours(
			List<ScheduleHourLabelElement> scheduleLabelHours) {
		this.scheduleLabelHours = scheduleLabelHours;
	}

	/**
	 * @param scheduleIndividualHours the scheduleIndividualHours to set
	 */
	public void setScheduleIndividualHours(List<Date> scheduleIndividualHours) {
		this.scheduleIndividualHours = scheduleIndividualHours;
	}
}
