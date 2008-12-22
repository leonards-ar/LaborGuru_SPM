/*
 * File name: WeeklyScheduleRow.java
 * Creation date: 08/12/2008 11:15:09
 * Copyright Mindpool
 */
package com.laborguru.frontend.model;

import java.io.Serializable;
import java.util.ArrayList;
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
	private Integer originalEmployeeId;
	private String employeeName;
	private List<WeeklyScheduleDailyEntry> weeklySchedule;
	
	private Integer employeeMaxHoursWeek;
	private Integer employeeMaxDaysWeek;
	private Integer employeeMaxHoursDay;
	
	private Object groupById;
	
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
		/*
		 * Because of the missing attribute keyValue in the autocompleter
		 * component, then a shadow employeeId must be kept.
		 */
		if(employeeId == null) {
			return getOriginalEmployeeId();
		} else {
			return employeeId;
		}
	}

	/**
	 * 
	 * @return
	 */
	public boolean isSelectedEmployeeChange() {
		return employeeId != null && !employeeId.equals(getOriginalEmployeeId());
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
	public List<WeeklyScheduleDailyEntry> getWeeklySchedule() {
		if(weeklySchedule == null) {
			setWeeklySchedule(new ArrayList<WeeklyScheduleDailyEntry>());
		}
		return weeklySchedule;
	}

	/**
	 * @param weeklySchedule the weeklySchedule to set
	 */
	public void setWeeklySchedule(List<WeeklyScheduleDailyEntry> weeklySchedule) {
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

	/**
	 * @return the originalEmployeeId
	 */
	public Integer getOriginalEmployeeId() {
		return originalEmployeeId;
	}

	/**
	 * @param originalEmployeeId the originalEmployeeId to set
	 */
	public void setOriginalEmployeeId(Integer originalEmployeeId) {
		this.originalEmployeeId = originalEmployeeId;
	}

	/**
	 * @return the groupById
	 */
	public Object getGroupById() {
		return groupById;
	}

	/**
	 * @param groupById the groupById to set
	 */
	public void setGroupById(Object groupById) {
		this.groupById = groupById;
	}

}
