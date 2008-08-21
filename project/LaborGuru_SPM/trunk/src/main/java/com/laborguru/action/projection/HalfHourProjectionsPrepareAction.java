package com.laborguru.action.projection;

import com.laborguru.action.SpmActionResult;
import com.opensymphony.xwork2.Preparable;

/**
 * This action deals with Half Hours Projections.
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
@SuppressWarnings("serial")
public class HalfHourProjectionsPrepareAction extends ProjectionCalendarBaseAction implements Preparable {

	/**
	 * Prepare the data to be used on the edit page
	 * @throws Exception
	 */
	public void prepareEdit(){

	}
	
	/**
	 * Prepare the data to be used on the add page
	 * @throws Exception
	 */
	public void prepareSave(){
	}

	/**
	 * Prepare data to be used in the actions methods defined for this action
	 * @throws Exception
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	public void prepare() throws Exception {
		//It's needed by the Preparable interface, don't comment out or removed
	}

	
	/**
	 * Prepares the edit page
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		// Force object initialization
		setSelectedDate(getWeekDaySelector().getStringStartingWeekDay());
		
		return SpmActionResult.EDIT.getResult();
	}


	
	/**
	 * Stores an employee on the DB
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {		
		/*
		try {


		} catch (SpmCheckedException e) {
			addActionError(e.getErrorMessage());
		}
		*/
		return SpmActionResult.INPUT.getResult();
	}

	/**
	 * 
	 * 
	 * @see com.laborguru.action.projection.ProjectionCalendarBaseAction#prepareChangeDay()
	 */
	@Override
	public void prepareChangeDay() {
	}

	/**
	 * 
	 * @see com.laborguru.action.projection.ProjectionCalendarBaseAction#prepareChangeWeek()
	 */
	@Override
	public void prepareChangeWeek() {
	}

	/**
	 * 
	 * 
	 * @see com.laborguru.action.projection.ProjectionCalendarBaseAction#processChangeDay()
	 */
	@Override
	protected void processChangeDay() {
	}

	/**
	 * 
	 * 
	 * @see com.laborguru.action.projection.ProjectionCalendarBaseAction#processChangeWeek()
	 */
	@Override
	protected void processChangeWeek() {
	}
	
}
