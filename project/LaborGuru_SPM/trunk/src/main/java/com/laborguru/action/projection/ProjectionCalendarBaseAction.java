/*
 * File name: ProjectionCalendarBaseAction.java
 * Creation date: Aug 11, 2008 3:19:42 PM
 * Copyright Mindpool
 */
package com.laborguru.action.projection;

import com.laborguru.action.SpmAction;
import com.laborguru.action.SpmActionResult;
import com.laborguru.frontend.model.WeekDaySelector;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public abstract class ProjectionCalendarBaseAction extends SpmAction {

	private WeekDaySelector weekDaySelector;
	
	private String selectedDate;
	
	private String selectedWeekDay;
	
	/**
	 * 
	 */
	public ProjectionCalendarBaseAction() {
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
	 * 
	 * @return
	 */
	public String changeWeek() {
		getWeekDaySelector().setStringStartingWeekDay(getSelectedDate());
		getWeekDaySelector().setStringSelectedDay(getSelectedDate());
		
		return SpmActionResult.INPUT.getResult();
	}
	
	/**
	 * 
	 * @return
	 */
	public String changeDay() {
		getWeekDaySelector().setStringStartingWeekDay(getSelectedDate());
		getWeekDaySelector().setStringSelectedDay(getSelectedWeekDay());
		
		return SpmActionResult.INPUT.getResult();
	}

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
}
