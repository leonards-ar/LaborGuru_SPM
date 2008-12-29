/*
 * File name: AddShiftByDayBaseAction.java
 * Creation date: 08/12/2008 18:45:39
 * Copyright Mindpool
 */
package com.laborguru.action.schedule;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.laborguru.frontend.model.ScheduleHourLabelElement;
import com.laborguru.frontend.model.ScheduleRow;
import com.laborguru.model.DailyProjection;
import com.laborguru.model.DailyStaffing;
import com.laborguru.model.Employee;
import com.laborguru.model.EmployeeSchedule;
import com.laborguru.model.OperationTime;
import com.laborguru.model.Position;
import com.laborguru.model.Shift;
import com.laborguru.model.StoreDailyStaffing;
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
public abstract class AddShiftByDayBaseAction extends AddShiftBaseAction {
	private static final Logger log = Logger.getLogger(AddShiftByDayBaseAction.class);
	
	private List<ScheduleHourLabelElement> scheduleLabelHours;
	private List<Date> scheduleIndividualHours;
	private StoreSchedule storeSchedule;
	private StoreDailyStaffing dailyStaffing = null;
	private BigDecimal dailyVolume;
	
	/**
	 * 
	 */
	public AddShiftByDayBaseAction() {
	}

	/**
	 * @return the storeSchedule
	 */
	public StoreSchedule getStoreSchedule() {
		if(storeSchedule == null) {
			try {
				storeSchedule = getScheduleService().getStoreScheduleByDate(getEmployeeStore(), getWeekDaySelector().getSelectedDay());
			} catch(RuntimeException ex) {
				log.error("Could not retrieve schedule for " + getWeekDaySelector().getSelectedDay() + " for store " + getEmployeeStore(), ex);
			}
			if(storeSchedule == null) {
				storeSchedule = new StoreSchedule();
				storeSchedule.setStore(getEmployeeStore());
			}
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
					d = new Date(d.getTime() + SpmConstants.MINUTES_INTERVAL * 60000L);
				}
				this.scheduleIndividualHours = hours;
			} else {
				this.scheduleIndividualHours = new ArrayList<Date>();
			}
		}
		return this.scheduleIndividualHours;
	}
	
	/**
	 * 
	 * @return
	 */
	public Date getLastScheduleIndividualHour() {
		Date d = getScheduleIndividualHours().get(getScheduleIndividualHours().size() - 1);
		return new Date(d.getTime() + SpmConstants.MINUTES_INTERVAL * 60000L);
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
		Integer total = new Integer(60 / SpmConstants.MINUTES_INTERVAL);
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
		Integer total = new Integer(60 / SpmConstants.MINUTES_INTERVAL);
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
			for(int i = 0; SpmConstants.MINUTES_INTERVAL * i <= 60 ;i++ ) {
				if(minutes <= SpmConstants.MINUTES_INTERVAL * i) {
					cal.set(Calendar.MINUTE, SpmConstants.MINUTES_INTERVAL * i);
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

			for(int i = 0; minutes > 0 && SpmConstants.MINUTES_INTERVAL * i <= 60 ;i++ ) {
				if(minutes <= SpmConstants.MINUTES_INTERVAL * i) {
					cal.set(Calendar.MINUTE, SpmConstants.MINUTES_INTERVAL * i);
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
	 * @param hour Hour that must be normalized according to SpmConstants.MINUTES_INTERVAL
	 * @return
	 */
	private Integer getCloseHourSelectable(Date hour) {
		Calendar cal = CalendarUtils.getCalendar(hour);
		int minutes = cal.get(Calendar.MINUTE);
		for(int i = 0; SpmConstants.MINUTES_INTERVAL * i <= 60 ;i++ ) {
			if(minutes <= SpmConstants.MINUTES_INTERVAL * i) {
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
		return new Integer(60 / SpmConstants.MINUTES_INTERVAL);
	}
	
	/**
	 * 
	 * @param hour Hour that must be normalized according to SpmConstants.MINUTES_INTERVAL
	 * @return
	 */
	private Integer getOpenHourSelectable(Date hour) {
		Calendar cal = CalendarUtils.getCalendar(hour);
		int minutes = cal.get(Calendar.MINUTE);
		for(int i = 0; SpmConstants.MINUTES_INTERVAL * i <= 60 ;i++ ) {
			if(minutes <= SpmConstants.MINUTES_INTERVAL * i) {
				return new Integer((60/SpmConstants.MINUTES_INTERVAL) - i);
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
				boolean isFirst = true;
				for(Shift shift : employeeSchedule.getShifts()) {
					if(!shift.isBreak() && (position == null || (position != null && isEqualPosition(position, shift.getPosition())))) {
						aRow = buildScheduleRow(employeeSchedule, shift, schedule, isFirst);
						isFirst = false;
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
	 * @param position
	 * @return
	 */
	protected List<Integer> buildMinimumStaffingFor(Position position) {
		int size = getScheduleIndividualHours().size();
		List<Integer> minimumStaffing = new ArrayList<Integer>(size);

		// :TODO: Improve performance. This will call getHalfHourStaffing every 15 minutes (2 times)
		// This should be calculated only once
		StoreDailyStaffing dailyStaffing = getDailyStaffing();
		if(position == null) {
			for(Date time : getScheduleIndividualHours()) {
				minimumStaffing.add(new Integer(dailyStaffing.getHalfHourStaffing(time)));
			}			
		} else {
			for(Date time : getScheduleIndividualHours()) {
				minimumStaffing.add(new Integer(dailyStaffing.getHalfHourStaffing(position, time)));
			}			
		}
		
		return minimumStaffing;
	}

	/**
	 * 
	 * @param schedule
	 * @param position
	 * @param day
	 */
	protected void setSchedule(List<ScheduleRow> schedule, Position position) {
		getStoreSchedule().setDay(getWeekDaySelector().getSelectedDay());
		getStoreSchedule().setStore(getEmployeeStore());
		
		Set<Integer> employeeIds = getDifferentEmployeeIds(schedule);
		Employee employee;
		for(Integer employeeId : employeeIds) {
			employee = getEmployeeService().getEmployeeById(new Employee(employeeId));
			EmployeeSchedule employeeSchedule = getStoreSchedule().getEmployeeSchedule(employee);
			if(employeeSchedule == null) {
				employeeSchedule = new EmployeeSchedule();
				employeeSchedule.setEmployee(employee);
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
		if(getStoreSchedule() != null && employeeIds != null) {
			Set<EmployeeSchedule> employeeSchedulesToRemove = new HashSet<EmployeeSchedule>();
			Set<Shift> shiftsToRemove = new HashSet<Shift>();
			
			for(EmployeeSchedule employeeSchedule : getStoreSchedule().getEmployeeSchedules()) {
				if(employeeSchedule.getEmployee() != null && !employeeIds.contains(employeeSchedule.getEmployee().getId())) {
					if(position == null) {
						// Applies for all positions
						employeeSchedulesToRemove.add(employeeSchedule);
					} else {
						for(Shift shift : employeeSchedule.getShifts()) {
							if(isEqualPosition(shift.getPosition(), position)) {
								shiftsToRemove.add(shift);
							}
						}
						employeeSchedule.getShifts().removeAll(shiftsToRemove);
						// There are no more shifts, then remove the employee schedule
						//:TODO: Should remove also all employee schedules with just break shifts???
						if(employeeSchedule.getShifts().isEmpty()) {
							employeeSchedulesToRemove.add(employeeSchedule);
						}
					}
				}
			}
			getStoreSchedule().getEmployeeSchedules().removeAll(employeeSchedulesToRemove);
		}
	}

	/**
	 * 
	 * @param source
	 * @param employeeSchedule
	 * @param position
	 */
	private void setShifts(List<ScheduleRow> source, EmployeeSchedule employeeSchedule, Position position) {
		if(position == null) {
			setShiftsAllPositions(source, employeeSchedule);
		} else {
			setShiftsForPosition(source, employeeSchedule, position);
		}
	}
	
	/**
	 * 
	 * @param source
	 * @param employeeSchedule
	 * @param position
	 */
	private void setShiftsForPosition(List<ScheduleRow> source, EmployeeSchedule employeeSchedule, Position position) {
		int shiftPosition = 0;
		List<Shift> rowShifts;
		List<Shift> currentShifts = employeeSchedule.getShifts();
		int currentShiftsSize = currentShifts.size();
		
		for(ScheduleRow row : source) {
			rowShifts = retrieveShifts(row);
			for(Shift aShift : rowShifts) {
				if(isEqualPosition(position, aShift.getPosition())) {
					while(shiftPosition < currentShifts.size() && !isEqualPosition(position, currentShifts.get(shiftPosition).getPosition())) {
						shiftPosition++;
					}
					
					if(shiftPosition < currentShiftsSize) {
						updateShift(aShift, currentShifts.get(shiftPosition));
						shiftPosition++;
					} else {
						aShift.setEmployeeSchedule(employeeSchedule);
						employeeSchedule.addShift(aShift);
					}
				}
			}
		}
		
		// Remove non-existing shifts
		if(shiftPosition < currentShiftsSize) {
			for(int i = currentShiftsSize - 1; i >= shiftPosition; i--) {
				if(isEqualPosition(position, currentShifts.get(i).getPosition())) {
					currentShifts.remove(i);
				}
			}
		}		
	}
	
	
	
	/**
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	protected void validateSchedule(List<ScheduleRow> schedule) {
		validateScheduleRows(schedule);
		Set<Integer> employeeIds = getDifferentEmployeeIds(schedule);

		for(Integer employeeId : employeeIds) {
			validateEmployeeSchedule(employeeId, schedule);
		}
	}

	/**
	 * 
	 * @param schedule
	 */
	private void validateScheduleRows(List<ScheduleRow> schedule) {
		int rowCount = 1;
		
		for(ScheduleRow aRow : schedule) {
			if(aRow.getEmployeeId() == null || aRow.getEmployeeId().intValue() <= 0) {
				addActionError(getText("error.schedule.addshift.employee_missing", new String[]{String.valueOf(rowCount)}));
			}
			rowCount++;
		}
	}
	
	/**
	 * 
	 * @param employeeId
	 * @param schedule
	 */
	private void validateEmployeeSchedule(Integer employeeId, List<ScheduleRow> schedule) {
		List<ScheduleRow> employeeSchedules = getEmployeeSchedule(employeeId, schedule);
		int buckets = getScheduleIndividualHours().size();
		boolean collision = false;
		int quantity;
		
		for(int i = 0; i < buckets && !collision; i++) {
			quantity = 0;
			for(int j = 0; j < employeeSchedules.size() && !collision; j++) {
				if(!SpmConstants.SCHEDULE_FREE.equals(employeeSchedules.get(j).getSchedule().get(i))) {
					quantity++;
				}
				collision = quantity > 1;
			}
		}
		
		if(collision) {
			String employeeName = employeeSchedules.get(0).getEmployeeName();
			addActionError(getText("error.schedule.addshift.employee_shift_collision", new String[]{employeeName}));
		}
	}
	
	/**
	 * 
	 * @param source
	 * @param employeeSchedule
	 */
	private void setShiftsAllPositions(List<ScheduleRow> source, EmployeeSchedule employeeSchedule) {
		int shiftPosition = 0;
		List<Shift> rowShifts;
		int currentShiftsSize = employeeSchedule.getShifts().size();
		
		for(ScheduleRow row : source) {
			rowShifts = retrieveShifts(row);
			for(Shift aShift : rowShifts) {
				if(shiftPosition < currentShiftsSize) {
					updateShift(aShift, employeeSchedule.getShifts().get(shiftPosition));
					shiftPosition++;
				} else {
					aShift.setEmployeeSchedule(employeeSchedule);
					employeeSchedule.addShift(aShift);
				}
			}
		}
		
		// Remove non-existing shifts
		if(shiftPosition < currentShiftsSize) {
			for(int i = currentShiftsSize - 1; i >= shiftPosition; i--)
				employeeSchedule.getShifts().remove(i);
		}
	}
	
	/**
	 * 
	 * @param row
	 * @return
	 */
	private List<Shift> retrieveShifts(ScheduleRow row) {
		List<Shift> rowShifts = new ArrayList<Shift>();
		
		Shift aShift = null;
		String currentShift = null;
		String element;
		for(int i = 0; i < row.getSchedule().size(); i++) {
			element = row.getSchedule().get(i);
			
			if(currentShift == null && !SpmConstants.SCHEDULE_FREE.equals(element)) {
				currentShift = element;
				aShift = new Shift();
				aShift.setFromHour(getScheduleIndividualHours().get(i));
			} else if(currentShift != null && !currentShift.equals(element)) {
				aShift.setToHour(getScheduleIndividualHours().get(i));
				
				if(SpmConstants.SCHEDULE_BREAK.equals(currentShift)) {
					aShift.setPosition(null);
				} else {
					Position pos = new Position();
					//:TODO: Validation
					pos.setId(new Integer(currentShift));
					aShift.setPosition(pos);
				}
				rowShifts.add(aShift);
				aShift = null;
				
				if(SpmConstants.SCHEDULE_FREE.equals(element)) {
					currentShift = null;
				} else {
					currentShift = element;
					aShift = new Shift();
					aShift.setFromHour(getScheduleIndividualHours().get(i));
				}
			}
		}
		
		if(currentShift != null) {
			aShift.setToHour(getLastScheduleIndividualHour());
			
			if(SpmConstants.SCHEDULE_BREAK.equals(currentShift)) {
				aShift.setPosition(null);
			} else {
				Position pos = new Position();
				//:TODO: Validation
				pos.setId(new Integer(currentShift));
				aShift.setPosition(pos);
			}
			rowShifts.add(aShift);			
		}
		return rowShifts;
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
	 * 
	 * @param schedule
	 * @param employee
	 * @param shift
	 * @return
	 */
	private boolean alreadyInSchedule(List<ScheduleRow> schedule, Employee employee, Shift shift) {
		for(ScheduleRow row : schedule) {
			if(isEqualId(row.getEmployeeId(), employee != null ? employee.getId() : null)) {
				if(shift.isBreak()/* && row.getPositionId() == null*/) {
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
	private ScheduleRow buildScheduleRow(EmployeeSchedule employeeSchedule, Shift shift, List<ScheduleRow> schedule, boolean isFirst) {
		if(!alreadyInSchedule(schedule, employeeSchedule.getEmployee(), shift) && employeeSchedule.getEmployee() != null) {
			ScheduleRow aRow = new ScheduleRow();
			aRow.setEmployeeId(employeeSchedule.getEmployee().getId());
			aRow.setOriginalEmployeeId(employeeSchedule.getEmployee().getId());
			aRow.setEmployeeName(employeeSchedule.getEmployee().getFullName());
			aRow.setPositionId(shift.getPosition() != null ? shift.getPosition().getId() : null);
			aRow.setPositionName(shift.getPosition() != null ? shift.getPosition().getName() : null);
			aRow.setEmployeeMaxDaysWeek(employeeSchedule.getEmployee().getMaxDaysWeek());
			aRow.setEmployeeMaxHoursDay(employeeSchedule.getEmployee().getMaxHoursDay());
			aRow.setEmployeeMaxHoursWeek(employeeSchedule.getEmployee().getMaxHoursWeek());
			
			
			List<Date> scheduleBuckets = getScheduleIndividualHours();
			List<String> occupation = initializeScheduleRow();
			List<String> hours = initializeScheduleHoursRow();
			boolean foundFirst = false;
			
			for(Shift aShift : employeeSchedule.getShifts()) {
				if((aShift.isBreak() && isFirst) || (aShift.isBreak() && foundFirst) || isEqualPosition(shift.getPosition(), aShift.getPosition())) {
					setScheduleOccupation(occupation, scheduleBuckets, aShift);
					if(!aShift.isBreak()) {
						foundFirst = true;
					}
				} else {
					// Skip until first
					if(foundFirst && !aShift.isBreak()) {
						// Position changed!
						break;
					}
				}
			}
			
			aRow.setSchedule(occupation);
			aRow.setHours(hours);
			
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
		int to = getIndexOfBucket(shift.getToHour(), scheduleBuckets) - 1;
		
		String value;
		for(int i = from; i <= to; i++) {
			value = occupation.get(i);
			//Never override a shift with a break
			if(!shift.isBreak() || (shift.isBreak() && SpmConstants.SCHEDULE_FREE.equals(value))) {
				occupation.set(i, shift.isBreak() ? SpmConstants.SCHEDULE_BREAK : String.valueOf(shift.getPosition().getId()));
			}
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
			if(hour.getTime() <= anHour.getTime()) {
				return i;
			}
		}
		return scheduleBuckets.size();
	}
	
	/**
	 * 
	 * @param scheduleBuckets
	 * @return
	 */
	protected List<String> initializeScheduleRow() {
		int size = getScheduleIndividualHours().size();
		List<String> scheduleRow = new ArrayList<String>(size);
		for(int i = 0; i < size; i++) {
			scheduleRow.add(SpmConstants.SCHEDULE_FREE);
		}
		return scheduleRow;
	}

	/**
	 * 
	 * @param scheduleBuckets
	 * @return
	 */
	protected List<String> initializeScheduleHoursRow() {
		List<Date> scheduleBuckets = getScheduleIndividualHours();
		List<String> hoursRow = new ArrayList<String>(scheduleBuckets.size());
		final SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		for(int i = 0; i < scheduleBuckets.size(); i++) {
			hoursRow.add(df.format(scheduleBuckets.get(i)));
		}
		return hoursRow;
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

	/**
	 * 
	 * @return
	 */
	public Integer getTotalIndividualHours() {
		return new Integer(getScheduleIndividualHours().size());
	}
	
	/**
	 * @return the dailyStaffing
	 */
	public StoreDailyStaffing getDailyStaffing() {
		if(dailyStaffing == null) {
			setDailyStaffing(getStaffingService().getDailyStaffingByDate(getEmployeeStore(), getWeekDaySelector().getSelectedDay()));
		}
		return dailyStaffing;
	}

	/**
	 * @param dailyStaffing the dailyStaffing to set
	 */
	public void setDailyStaffing(StoreDailyStaffing dailyStaffing) {
		this.dailyStaffing = dailyStaffing;
	}
	
	/**
	 * 
	 * @param position
	 * @return
	 */
	private int getTotalPositionTargetInMinutes(Position position) {
		StoreDailyStaffing storeDailyStaffing = getDailyStaffing();
		if(storeDailyStaffing != null) {
			DailyStaffing dailyStaffing = storeDailyStaffing.getDailyStaffing(position);
			return getTotalDailyStaffingInMinutes(dailyStaffing);
		} else {
			return 0;
		}		
	}
	
	/**
	 * @return the dailyVolume
	 */
	public BigDecimal getDailyVolume() {
		if(dailyVolume == null) {
			DailyProjection dailyProjection = getProjectionService().getDailyProjection(getEmployeeStore(), getWeekDaySelector().getSelectedDay());
			if(dailyProjection != null) {
				setDailyVolume(dailyProjection.getDailyProjectionValue());
			} else {
				setDailyVolume(new BigDecimal("0"));
			}
		}
		return dailyVolume;
	}



	/**
	 * @param dailyVolume the dailyVolume to set
	 */
	public void setDailyVolume(BigDecimal dailyVolume) {
		this.dailyVolume = dailyVolume;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getTotalTarget() {
		StoreDailyStaffing storeDailyStaffing = getDailyStaffing();
		if(storeDailyStaffing != null) {
			return CalendarUtils.minutesToTime(new Integer(getTotalTargetInMinutes(storeDailyStaffing.getStoreDailyStaffing())));
		} else {
			return CalendarUtils.minutesToTime(new Integer(0));
		}
	}
	
	/**
	 * 
	 * @param position
	 * @return
	 */
	public String getTotalPositionTarget(Position position) {
		return CalendarUtils.minutesToTime(new Integer(getTotalPositionTargetInMinutes(position)));
	}

	/**
	 * 
	 * @see com.laborguru.action.schedule.AddShiftBaseAction#onWeekdaySelectorChange()
	 */
	@Override
	protected void onWeekdaySelectorChange() {
		setDailyVolume(null);
	}
}
