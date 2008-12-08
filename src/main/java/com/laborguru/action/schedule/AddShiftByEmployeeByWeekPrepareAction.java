/*
 * File name: AddShiftByEmployeeByWeekPrepareAction.java
 * Creation date: 07/12/2008 20:26:38
 * Copyright Mindpool
 */
package com.laborguru.action.schedule;

import com.laborguru.action.SpmActionResult;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class AddShiftByEmployeeByWeekPrepareAction extends AddShiftBaseAction {

	/**
	 * 
	 */
	public AddShiftByEmployeeByWeekPrepareAction() {
	}

	/**
	 * 
	 * @see com.laborguru.action.schedule.AddShiftBaseAction#prepareChangeDay()
	 */
	@Override
	public void prepareChangeDay() {
	}

	/**
	 * 
	 * @see com.laborguru.action.schedule.AddShiftBaseAction#prepareChangeWeek()
	 */
	@Override
	public void prepareChangeWeek() {
	}

	/**
	 * 
	 * @see com.laborguru.action.schedule.AddShiftBaseAction#processChangeDay()
	 */
	@Override
	protected void processChangeDay() {
	}

	/**
	 * 
	 * @see com.laborguru.action.schedule.AddShiftBaseAction#processChangeWeek()
	 */
	@Override
	protected void processChangeWeek() {
	}

	/**
	 * 
	 * @return
	 */
	public String edit() {
		return SpmActionResult.EDIT.getResult();
	}
}
