/*
 * File name: ScheduleRow.java
 * Creation date: Sep 21, 2008 8:04:44 AM
 * Copyright Mindpool
 */
package com.laborguru.frontend.model;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class ScheduleRow implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -139301522656297022L;
	
	private Integer positionId;
	private Integer employeeId;
	private String employeeName;
	private String inHour;
	private String outHour;
	private String totalHours;
	private List<String> schedule;
	
	/**
	 * 
	 */
	public ScheduleRow() {
	}

	/**
	 * @return the positionId
	 */
	public Integer getPositionId() {
		return positionId;
	}

	/**
	 * @param positionId the positionId to set
	 */
	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	/**
	 * @return the employeeId
	 */
	public Integer getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}

	/**
	 * @param employeeName the employeeName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	/**
	 * @return the inHour
	 */
	public String getInHour() {
		return inHour;
	}

	/**
	 * @param inHour the inHour to set
	 */
	public void setInHour(String inHour) {
		this.inHour = inHour;
	}

	/**
	 * @return the outHour
	 */
	public String getOutHour() {
		return outHour;
	}

	/**
	 * @param outHour the outHour to set
	 */
	public void setOutHour(String outHour) {
		this.outHour = outHour;
	}

	/**
	 * @return the totalHours
	 */
	public String getTotalHours() {
		return totalHours;
	}

	/**
	 * @param totalHours the totalHours to set
	 */
	public void setTotalHours(String totalHours) {
		this.totalHours = totalHours;
	}

	/**
	 * @return the schedule
	 */
	public List<String> getSchedule() {
		return schedule;
	}

	/**
	 * @param schedule the schedule to set
	 */
	public void setSchedule(List<String> schedule) {
		this.schedule = schedule;
	}
}
