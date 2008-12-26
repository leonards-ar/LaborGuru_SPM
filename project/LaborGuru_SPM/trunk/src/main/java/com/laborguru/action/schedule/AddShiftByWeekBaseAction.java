/*
 * File name: AddShiftByWeekBaseAction.java
 * Creation date: 08/12/2008 19:02:03
 * Copyright Mindpool
 */
package com.laborguru.action.schedule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.laborguru.frontend.model.WeeklyScheduleDailyEntry;
import com.laborguru.frontend.model.WeeklyScheduleData;
import com.laborguru.frontend.model.WeeklyScheduleRow;
import com.laborguru.model.Employee;
import com.laborguru.model.EmployeeSchedule;
import com.laborguru.model.Position;
import com.laborguru.model.Shift;
import com.laborguru.model.StoreSchedule;
import com.laborguru.util.CalendarUtils;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public abstract class AddShiftByWeekBaseAction extends AddShiftBaseAction {
	private static final Logger log = Logger.getLogger(AddShiftByWeekBaseAction.class);
	
	private WeeklyScheduleData weeklyScheduleData = null;
	
	private List<StoreSchedule> storeSchedules = null;
	private List<Date> weekDays = null;
	
	private Integer newEmployeeId;
	private String newEmployeeName;
	private Integer newEmployeePositionId;
	
	/**
	 * 
	 */
	public AddShiftByWeekBaseAction() {
	}

	/**
	 * 
	 * @see com.laborguru.action.schedule.AddShiftBaseAction#onWeekdaySelectorChange()
	 */
	@Override
	protected void onWeekdaySelectorChange() {
	}

	/**
	 * @return the storeSchedule
	 */
	protected List<StoreSchedule> getStoreSchedules() {
		if(storeSchedules == null) {
			storeSchedules = new ArrayList<StoreSchedule>();
			try {
				StoreSchedule aSchedule;
				//:TODO: Probably should retrieve the whole week from the database
				for(Date aDate : getWeekDays()) {
					aSchedule = getScheduleService().getStoreScheduleByDate(getEmployeeStore(), aDate);
					if(aSchedule == null) {
						aSchedule = new StoreSchedule();
						aSchedule.setStore(getEmployeeStore());
					}					
					storeSchedules.add(aSchedule);
				}
			} catch(RuntimeException ex) {
				log.error("Could not retrieve schedule for week days " + getWeekDays() + " for store " + getEmployeeStore(), ex);
			}

		}
		return storeSchedules;
	}

	/**
	 * 
	 * @param position
	 */
	protected void buildScheduleDataFor(Position position) {
		int size = getStoreSchedules().size();
		StoreSchedule aSchedule;
		WeeklyScheduleRow aRow;
		
		for(int i = 0; i < size; i++) {
			aSchedule = getStoreSchedules().get(i);
			for(EmployeeSchedule employeeSchedule : aSchedule.getEmployeeSchedules()) {
				for(Shift shift : employeeSchedule.getShifts()) {
					if(!shift.isBreak() && (position == null || (position != null && isEqualPosition(position, shift.getPosition())))) {
						aRow = getRowFor(employeeSchedule, shift);
						if(aRow == null) {
							aRow = buildRowFor(employeeSchedule, shift);
							getWeeklyScheduleData().addScheduleRow(getGroupById(employeeSchedule.getEmployee(), shift), aRow);
						}
						buildScheduleDataFor(aRow, employeeSchedule, shift, i);
					}
				}
			}			
		}
	}
	
	/**
	 * 
	 * @param row
	 * @param employeeSchedule
	 * @param shift
	 * @param dayIndex
	 */
	private void buildScheduleDataFor(WeeklyScheduleRow row, EmployeeSchedule employeeSchedule, Shift shift, int dayIndex) {
		WeeklyScheduleDailyEntry entry = getDailyEntry(row.getWeeklySchedule(), dayIndex);
		if(!entry.isMultipleShifts()) {
			entry.setMultipleShifts(employeeSchedule.hasMultipleShifts(shift.getPosition()));
		}
		
		if(entry.getInHour() == null || CalendarUtils.greaterTime(entry.getInHour(), shift.getFromHour())) {
			entry.setInHour(shift.getFromHour());
			entry.resetTotalHours();
		}
		if(entry.getOutHour() == null || CalendarUtils.smallerTime(entry.getOutHour(), shift.getToHour())) {
			entry.setOutHour(shift.getToHour());
			entry.resetTotalHours();
		}
	}
	
	/**
	 * 
	 * @param entries
	 * @param dayIndex
	 * @return
	 */
	private WeeklyScheduleDailyEntry getDailyEntry(List<WeeklyScheduleDailyEntry> entries, int dayIndex) {
		if(entries.size() > dayIndex && dayIndex >= 0) {
			return entries.get(dayIndex);
		} else {
			return null;
		}
	}
	
	/**
	 * 
	 * @param employeeSchedule
	 * @param shift
	 * @return
	 */
	private WeeklyScheduleRow buildRowFor(EmployeeSchedule employeeSchedule, Shift shift) {
		WeeklyScheduleRow row = new WeeklyScheduleRow();
		Employee employee = employeeSchedule.getEmployee();
		Position position = shift.getPosition();
		if(employee != null && position != null) {
			row.setEmployeeId(employee.getId());
			row.setEmployeeMaxDaysWeek(employee.getMaxDaysWeek());
			row.setEmployeeMaxHoursDay(employee.getMaxHoursDay());
			row.setEmployeeMaxHoursWeek(employee.getMaxHoursWeek());
			row.setEmployeeName(employee.getFullName());
			row.setPositionId(position.getId());
			row.setPositionName(position.getName());
			row.setOriginalEmployeeId(employee.getId());
			row.setGroupById(getGroupById(employeeSchedule.getEmployee(), shift));
			
			WeeklyScheduleDailyEntry entry;
			for(Date d : getWeekDays()) {
				entry = new WeeklyScheduleDailyEntry();
				entry.setDay(d);
				row.getWeeklySchedule().add(entry);
			}
		}
		return row;
	}
	
	/**
	 * 
	 * @param employeeSchedule
	 * @param shift
	 * @return
	 */
	private WeeklyScheduleRow getRowFor(EmployeeSchedule employeeSchedule, Shift shift) {
		List<WeeklyScheduleRow> dataRows = getWeeklyScheduleData().getScheduleDataFor(getGroupById(employeeSchedule.getEmployee(), shift));
		if(dataRows != null) {
			for(WeeklyScheduleRow aRow : dataRows) {
				if(isEqualId(aRow.getEmployeeId(), employeeSchedule.getEmployee().getId()) && isEqualId(aRow.getPositionId(), shift.getPosition().getId())) {
					return aRow;
				}
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param employee
	 * @param shift
	 * @return
	 */
	protected abstract Object getGroupById(Employee employee, Shift shift);

	/**
	 * @return the weekDays
	 */
	public List<Date> getWeekDays() {
		if(weekDays == null) {
			this.weekDays = getWeekDaySelector().getWeekDays();
		}
		return weekDays;
	}

	/**
	 * @return the scheduleData
	 */
	public WeeklyScheduleData getWeeklyScheduleData() {
		if(weeklyScheduleData == null) {
			setWeeklyScheduleData(new WeeklyScheduleData());
		}
		return weeklyScheduleData;
	}

	/**
	 * @param weeklyScheduleData the scheduleData to set
	 */
	public void setWeeklyScheduleData(WeeklyScheduleData weeklyScheduleData) {
		this.weeklyScheduleData = weeklyScheduleData;
	}
	
	/**
	 * 
	 */
	protected void setScheduleData() {
		if(getWeeklyScheduleData().isEmpty()) {
			buildScheduleDataFor(getPosition());
		}
	}

	/**
	 * @return the newEmployeeId
	 */
	public Integer getNewEmployeeId() {
		return newEmployeeId;
	}

	/**
	 * @param newEmployeeId the newEmployeeId to set
	 */
	public void setNewEmployeeId(Integer newEmployeeId) {
		this.newEmployeeId = newEmployeeId;
	}

	/**
	 * @return the newEmployeeName
	 */
	public String getNewEmployeeName() {
		return newEmployeeName;
	}

	/**
	 * @param newEmployeeName the newEmployeeName to set
	 */
	public void setNewEmployeeName(String newEmployeeName) {
		this.newEmployeeName = newEmployeeName;
	}

	/**
	 * @return the newEmployeePositionId
	 */
	public Integer getNewEmployeePositionId() {
		return newEmployeePositionId;
	}

	/**
	 * @param newEmployeePositionId the newEmployeePositionId to set
	 */
	public void setNewEmployeePositionId(Integer newEmployeePositionId) {
		this.newEmployeePositionId = newEmployeePositionId;
	}
}
