/*
 * File name: AddShiftBaseAction.java
 * Creation date: Sep 20, 2008 1:17:19 PM
 * Copyright Mindpool
 */
package com.laborguru.action.schedule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.laborguru.action.SpmAction;
import com.laborguru.action.SpmActionResult;
import com.laborguru.frontend.model.WeekDaySelector;
import com.laborguru.model.OperationTime;
import com.laborguru.model.StoreSchedule;

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
		if(getStoreSchedule() != null && getStoreSchedule().getStore() != null) {
			OperationTime operationTime = getStoreSchedule().getStore().getOperationTime(getWeekDaySelector().getSelectedDayOfWeek());
			Date d = operationTime != null ? operationTime.getOpenHour() : null;
			List hours = new ArrayList<Date>();
			while(d != null && d.before(operationTime.getCloseHour())) {
				
			}
			return hours;
		} else {
			return new ArrayList<Date>();
		}
	}
	
	/**
	 * This method returns a list with the labels of the
	 * schedule hours
	 * @return
	 */
	public List<Date> getScheduleLabelHours() {
		return null;
	}
}
