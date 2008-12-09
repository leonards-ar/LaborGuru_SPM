/*
 * File name: WeeklyScheduleRow.java
 * Creation date: 08/12/2008 11:15:09
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
public class WeeklyScheduleRow implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8384388191864678705L;
	private Integer positionId;
	private String positionName;
	private Integer employeeId;
	private String employeeName;
	private List<WeeklyScheduleDailyRow> weeklySchedule;
	
	private Integer employeeMaxHoursWeek;
	private Integer employeeMaxDaysWeek;
	private Integer employeeMaxHoursDay;
	
	/**
	 * 
	 */
	public WeeklyScheduleRow() {
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
	 * @return the positionName
	 */
	public String getPositionName() {
		return positionName;
	}

	/**
	 * @param positionName the positionName to set
	 */
	public void setPositionName(String positionName) {
		this.positionName = positionName;
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
	 * @return the weeklySchedule
	 */
	public List<WeeklyScheduleDailyRow> getWeeklySchedule() {
		return weeklySchedule;
	}

	/**
	 * @param weeklySchedule the weeklySchedule to set
	 */
	public void setWeeklySchedule(List<WeeklyScheduleDailyRow> weeklySchedule) {
		this.weeklySchedule = weeklySchedule;
	}

	/**
	 * @return the employeeMaxHoursWeek
	 */
	public Integer getEmployeeMaxHoursWeek() {
		return employeeMaxHoursWeek;
	}

	/**
	 * @param employeeMaxHoursWeek the employeeMaxHoursWeek to set
	 */
	public void setEmployeeMaxHoursWeek(Integer employeeMaxHoursWeek) {
		this.employeeMaxHoursWeek = employeeMaxHoursWeek;
	}

	/**
	 * @return the employeeMaxDaysWeek
	 */
	public Integer getEmployeeMaxDaysWeek() {
		return employeeMaxDaysWeek;
	}

	/**
	 * @param employeeMaxDaysWeek the employeeMaxDaysWeek to set
	 */
	public void setEmployeeMaxDaysWeek(Integer employeeMaxDaysWeek) {
		this.employeeMaxDaysWeek = employeeMaxDaysWeek;
	}

	/**
	 * @return the employeeMaxHoursDay
	 */
	public Integer getEmployeeMaxHoursDay() {
		return employeeMaxHoursDay;
	}

	/**
	 * @param employeeMaxHoursDay the employeeMaxHoursDay to set
	 */
	public void setEmployeeMaxHoursDay(Integer employeeMaxHoursDay) {
		this.employeeMaxHoursDay = employeeMaxHoursDay;
	}

}
