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
		return CalendarUtils.addOrSubstractHours(operationTime.getOpenHour(), (-1) * getStoreScheduleExtraHours());
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
		return CalendarUtils.addOrSubstractHours(operationTime.getCloseHour(), getStoreScheduleExtraHours());
	}
}
