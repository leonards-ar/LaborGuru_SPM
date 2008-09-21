/*
 * File name: AddShiftBaseAction.java
 * Creation date: Sep 20, 2008 1:17:19 PM
 * Copyright Mindpool
 */
package com.laborguru.action.schedule;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.laborguru.action.SpmAction;
import com.laborguru.action.SpmActionResult;
import com.laborguru.frontend.model.ScheduleHourLabelElement;
import com.laborguru.frontend.model.WeekDaySelector;
import com.laborguru.model.OperationTime;
import com.laborguru.model.StoreSchedule;
import com.laborguru.util.CalendarUtils;

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
		if(getStoreSchedule() != null && getStoreSchedule().getStore() != null) {
			OperationTime operationTime = getStoreSchedule().getStore().getOperationTime(getWeekDaySelector().getSelectedDayOfWeek());
			Date closeHour = getScheduleCloseHour(operationTime.getCloseHour());
			Date d = operationTime != null ? getScheduleOpenHour(operationTime.getOpenHour()) : null;
			
			List<Date> hours = new ArrayList<Date>();
			while(d != null && d.getTime() <= closeHour.getTime()) {
				hours.add(d);
				d = new Date(d.getTime() + MINUTES_INTERVAL * 60000L);
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
	public List<ScheduleHourLabelElement> getScheduleLabelHours() {
		if(getStoreSchedule() != null && getStoreSchedule().getStore() != null) {
			OperationTime operationTime = getStoreSchedule().getStore().getOperationTime(getWeekDaySelector().getSelectedDayOfWeek());
			
			Date d = operationTime != null ? getScheduleOpenHour(operationTime.getOpenHour()) : null;
			Date baseCloseHour = getScheduleBaseHour(operationTime.getCloseHour());
			
			List<ScheduleHourLabelElement> hours = new ArrayList<ScheduleHourLabelElement>();
			
			// Open hour is a special case
			hours.add(new ScheduleHourLabelElement(d, getOpenHourColspan(d)));
			
			d = getScheduleBaseHour(CalendarUtils.addOrSubstractHours(d, 1));

			while(d != null && d.getTime() < baseCloseHour.getTime()) {
				hours.add(new ScheduleHourLabelElement(d, getHourColspan(d)));
				d = getScheduleBaseHour(CalendarUtils.addOrSubstractHours(d, 1));
			}
			
			if(operationTime.getCloseHour().getTime() > baseCloseHour.getTime()) {
				hours.add(new ScheduleHourLabelElement(baseCloseHour, getCloseHourColspan(d)));
			}
			
			return hours;
			
		} else {
			return new ArrayList<ScheduleHourLabelElement>();
		}
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
	private Integer getCloseHourColspan(Date hour) {
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
	private Integer getOpenHourColspan(Date hour) {
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
}
