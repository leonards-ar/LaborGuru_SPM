/*
 * File name: AddShiftByEmployeeByWeekPrepareAction.java
 * Creation date: 07/12/2008 20:26:38
 * Copyright Mindpool
 */
package com.laborguru.action.schedule;

import com.laborguru.action.SpmActionResult;
import com.laborguru.model.Employee;
import com.laborguru.model.Shift;
import com.opensymphony.xwork2.Preparable;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class AddShiftByEmployeeByWeekPrepareAction extends AddShiftByWeekBaseAction implements Preparable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1728336763892104682L;

	/**
	 * 
	 */
	public AddShiftByEmployeeByWeekPrepareAction() {
	}

	/**
	 * Prepare the data to be used on the edit page
	 */
	public void prepareEdit() {
		loadPageData();
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
		setScheduleData();
		
		return SpmActionResult.EDIT.getResult();
	}
	
	/**
	 * 
	 */
	private void loadPageData() {
		loadPositions();
	}

	/**
	 * 
	 * @throws Exception
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	public void prepare() throws Exception {
	}

	/**
	 * 
	 * @param employee
	 * @param shift
	 * @return
	 * @see com.laborguru.action.schedule.AddShiftByWeekBaseAction#getGroupById(com.laborguru.model.Employee, com.laborguru.model.Shift)
	 */
	@Override
	protected Object getGroupById(Employee employee, Shift shift) {
		return employee != null ? employee.getId() : null;
	}
}
