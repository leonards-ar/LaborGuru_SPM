/*
 * File name: AddShiftByEmployeeByDayPrepareAction.java
 * Creation date: Sep 20, 2008 1:26:42 PM
 * Copyright Mindpool
 */
package com.laborguru.action.schedule;

import org.apache.log4j.Logger;

import com.laborguru.action.SpmActionResult;
import com.opensymphony.xwork2.Preparable;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class AddShiftByEmployeeByDayPrepareAction extends AddShiftBaseAction  implements Preparable {
	private static final Logger LOGGER = Logger.getLogger(AddShiftByEmployeeByDayPrepareAction.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8960503469589990853L;

	/**
	 * 
	 */
	public AddShiftByEmployeeByDayPrepareAction() {
	}

	/**
	 * Prepare data to be used in the actions methods defined for this action
	 * 
	 * @throws Exception
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	public void prepare() throws Exception {
		// It's needed by the Preparable interface, don't comment out or removed
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
