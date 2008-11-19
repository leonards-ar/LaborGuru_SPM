/*
 * File name: ScheduleByPositionEntry.java
 * Creation date: Nov 19, 2008 10:26:24 AM
 * Copyright Mindpool
 */
package com.laborguru.frontend.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.laborguru.model.Position;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class ScheduleByPositionEntry implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6787538652374987850L;
	
	private List<ScheduleRow> scheduleData;
	private List<Integer> minimumStaffing;

	private Integer newEmployeeId;
	private String newEmployeeName;
	private Integer newEmployeePositionId;
	
	private Position position;
	
	/**
	 * 
	 */
	public ScheduleByPositionEntry() {
	}

	/**
	 * @return the scheduleData
	 */
	public List<ScheduleRow> getScheduleData() {
		if(scheduleData == null) {
			setScheduleData(new ArrayList<ScheduleRow>());
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
	 * @return the minimumStaffing
	 */
	public List<Integer> getMinimumStaffing() {
		if(minimumStaffing == null) {
			setMinimumStaffing(new ArrayList<Integer>());
		}
		return minimumStaffing;
	}

	/**
	 * @param minimumStaffing the minimumStaffing to set
	 */
	public void setMinimumStaffing(List<Integer> minimumStaffing) {
		this.minimumStaffing = minimumStaffing;
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

}
