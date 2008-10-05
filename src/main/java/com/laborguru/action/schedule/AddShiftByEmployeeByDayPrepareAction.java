/*
 * File name: AddShiftByEmployeeByDayPrepareAction.java
 * Creation date: Sep 20, 2008 1:26:42 PM
 * Copyright Mindpool
 */
package com.laborguru.action.schedule;

import java.util.List;

import org.apache.log4j.Logger;

import com.laborguru.action.SpmActionResult;
import com.laborguru.frontend.model.ScheduleRow;
import com.laborguru.model.Position;
import com.laborguru.service.position.PositionService;
import com.opensymphony.xwork2.Preparable;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class AddShiftByEmployeeByDayPrepareAction extends AddShiftBaseAction  implements Preparable {
	private static final Logger log = Logger.getLogger(AddShiftByEmployeeByDayPrepareAction.class);
	
	private List<ScheduleRow> scheduleData;
	private Position position;
	
	private List<Position> positions;
	private PositionService positionService;
	
	private Integer newEmployeeId;
	private String newEmployeeName;
	private Integer newEmployeePositionId;
	
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
	 * Loads position and status list
	 */
	private void loadPageData() {
		log.debug("");
		this.setPositions(getPositionService().getPositionsByStore(getEmployeeStore()));
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
		loadPageData();
	}

	/**
	 * 
	 * @see com.laborguru.action.schedule.AddShiftBaseAction#prepareChangeWeek()
	 */
	@Override
	public void prepareChangeWeek() {
		loadPageData();
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
	 * Prepare the data to be used on the edit page
	 */
	public void prepareEdit() {
		loadPageData();
	}
	
	/**
	 * 
	 * @return
	 */
	public String edit() {
		
		return SpmActionResult.EDIT.getResult();
	}

	/**
	 * 
	 */
	public void prepareAddEmployee() {
		loadPageData();
	}
	
	/**
	 * 
	 * @return
	 */
	public String addEmployee() {
		ScheduleRow newRow = new ScheduleRow();
		newRow.setEmployeeId(getNewEmployeeId());
		newRow.setInHour("0");
		newRow.setOutHour("0");
		newRow.setTotalHours("0");
		newRow.setPositionId(getNewEmployeePositionId());
		newRow.setEmployeeName(getNewEmployeeName());
		
		getScheduleData().add(newRow);
		
		setNewEmployeeId(null);
		setNewEmployeeName(null);
		setNewEmployeePositionId(null);
		
		return SpmActionResult.EDIT.getResult();
	}
	
	/**
	 * @return the scheduleData
	 */
	public List<ScheduleRow> getScheduleData() {
		if(scheduleData == null) {
			setScheduleData(buildScheduleFor(getPosition()));
		}
		return scheduleData;
	}

	/**
	 * @param scheduleData the scheduleData to set
	 */
	public void setScheduleData(List<ScheduleRow> scheduleData) {
		this.scheduleData = scheduleData;
	}

	/**
	 * @return the position
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * @return the positions
	 */
	public List<Position> getPositions() {
		return positions;
	}

	/**
	 * @param positions the positions to set
	 */
	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	/**
	 * @return the positionService
	 */
	public PositionService getPositionService() {
		return positionService;
	}

	/**
	 * @param positionService the positionService to set
	 */
	public void setPositionService(PositionService positionService) {
		this.positionService = positionService;
	}

	/**
	 * @return the newEmployeeId
	 */
	public Integer getNewEmployeeId() {
		return newEmployeeId;
	}

	/**
	 * @param newEmployeeId the newEmployeeId to set
	 */
	public void setNewEmployeeId(Integer newEmployeeId) {
		this.newEmployeeId = newEmployeeId;
	}

	/**
	 * @return the newEmployeePositionId
	 */
	public Integer getNewEmployeePositionId() {
		return newEmployeePositionId;
	}

	/**
	 * @param newEmployeePositionId the newEmployeePositionId to set
	 */
	public void setNewEmployeePositionId(Integer newEmployeePositionId) {
		this.newEmployeePositionId = newEmployeePositionId;
	}

	/**
	 * @return the newEmployeeName
	 */
	public String getNewEmployeeName() {
		return newEmployeeName;
	}

	/**
	 * @param newEmployeeName the newEmployeeName to set
	 */
	public void setNewEmployeeName(String newEmployeeName) {
		this.newEmployeeName = newEmployeeName;
	}	
}
