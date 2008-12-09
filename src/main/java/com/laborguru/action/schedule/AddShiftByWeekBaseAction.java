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

import com.laborguru.frontend.model.WeeklyScheduleRow;
import com.laborguru.model.StoreSchedule;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public abstract class AddShiftByWeekBaseAction extends AddShiftBaseAction {
	private static final Logger log = Logger.getLogger(AddShiftByWeekBaseAction.class);
	
	private List<WeeklyScheduleRow> scheduleData = null;
	private List<StoreSchedule> storeSchedules = null;
	List<Date> weekDays = null;
	
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
	 * @return the scheduleData
	 */
	public List<WeeklyScheduleRow> getScheduleData() {
		if(scheduleData == null) {
			setScheduleData(new ArrayList<WeeklyScheduleRow>());
		}
		return scheduleData;
	}

	/**
	 * @param scheduleData the scheduleData to set
	 */
	public void setScheduleData(List<WeeklyScheduleRow> scheduleData) {
		this.scheduleData = scheduleData;
	}
	
	/**
	 * @return the storeSchedule
	 */
	public List<StoreSchedule> getStoreSchedules() {
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
	 * @return the weekDays
	 */
	public List<Date> getWeekDays() {
		if(weekDays == null) {
			this.weekDays = getWeekDaySelector().getWeekDays();
		}
		return weekDays;
	}
	
	
}
