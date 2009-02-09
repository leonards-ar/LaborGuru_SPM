/*
 * File name: ScheduleBaseAction.java
 * Creation date: 11/01/2009 09:06:37
 * Copyright Mindpool
 */
package com.laborguru.action.schedule;

import java.util.Date;

import com.laborguru.action.SpmAction;
import com.laborguru.model.OperationTime;
import com.laborguru.model.Store;
import com.laborguru.util.CalendarUtils;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public abstract class ScheduleBaseAction extends SpmAction {

	/**
	 * 
	 */
	public ScheduleBaseAction() {
	}

	/**
	 * 
	 * @param operationTime
	 * @return
	 */
	protected Date getStoreScheduleStartHour(OperationTime operationTime) {
		return CalendarUtils.addOrSubstractHours(operationTime.getOpenHour(), (-1) * getCalculatedExtraHours(operationTime.getOpenHour(), operationTime.getCloseHour(), true));
	}

	/**
	 * 
	 * @param open
	 * @param close
	 * @return
	 */
	private int getCalculatedExtraHours(Date open, Date close, boolean isCeil) {
		if(CalendarUtils.equalsTime(open, close)) {
			// 24hs store
			return 0;
		} else {
			double diff = CalendarUtils.differenceInHours(close, open).doubleValue();
			int extraHours = getStoreScheduleExtraHours();
			if(diff + extraHours > 24) {
				double hs = (diff + extraHours - 24) / 2;
				return (int) (isCeil ? Math.ceil(hs) : Math.floor(hs));
			} else {
				// Normal case
				return extraHours;
			}
		}
	}
	
	/**
	 * 
	 * @return
	 */
	private int getStoreScheduleExtraHours() {
		Store store = getEmployeeStore();
		return store != null && store.getExtraScheduleHours() != null ? store.getExtraScheduleHours().intValue() : 0;
	}
	
	/**
	 * 
	 * @param day
	 * @return
	 */
	protected OperationTime getOperationTime(Date day) {
		Store store = getEmployeeStore();
		return store != null ? store.getOperationTime(CalendarUtils.getDayOfWeek(day)) : null;
	}
	
	/**
	 * 
	 * @param operationTime
	 * @return
	 */
	protected Date getStoreScheduleEndHour(OperationTime operationTime) {
		return CalendarUtils.addOrSubstractHours(operationTime.getCloseHour(), getCalculatedExtraHours(operationTime.getOpenHour(), operationTime.getCloseHour(), false));
	}
	
	/**
	 * 
	 * @param day
	 * @return
	 */
	protected boolean isMultiDaySchedule(Date day) {
		OperationTime operationTime = getOperationTime(day);
		return CalendarUtils.equalsOrGreaterTime(operationTime.getOpenHour(), operationTime.getCloseHour());
	}	

	/**
	 * 
	 * @param day
	 * @return
	 */
	protected boolean isMultiDayScheduleWithExtraHours(Date day) {
		OperationTime operationTime = getOperationTime(day);
		return CalendarUtils.equalsOrGreaterTime(getStoreScheduleStartHour(operationTime), getStoreScheduleEndHour(operationTime));
	}	
}
