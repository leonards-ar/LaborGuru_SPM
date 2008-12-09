/*
 * File name: WeeklyScheduleDailyRow.java
 * Creation date: 08/12/2008 11:19:59
 * Copyright Mindpool
 */
package com.laborguru.frontend.model;

import java.io.Serializable;
import java.util.Date;

import com.laborguru.util.CalendarUtils;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class WeeklyScheduleDailyRow implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5232573319988760565L;
	private Date day;
	private Date inHour;
	private Date outHour;
	private Double totalHours;
	
	/**
	 * 
	 */
	public WeeklyScheduleDailyRow() {
	}

	/**
	 * @return the day
	 */
	public Date getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(Date day) {
		this.day = day;
	}

	/**
	 * @return the inHour
	 */
	public Date getInHour() {
		return inHour;
	}

	/**
	 * @param inHour the inHour to set
	 */
	public void setInHour(Date inHour) {
		this.inHour = inHour;
	}

	/**
	 * @return the outHour
	 */
	public Date getOutHour() {
		return outHour;
	}

	/**
	 * @param outHour the outHour to set
	 */
	public void setOutHour(Date outHour) {
		this.outHour = outHour;
	}

	/**
	 * @return the totalHours
	 */
	public Double getTotalHours() {
		if(totalHours == null) {
			setTotalHours(CalendarUtils.differenceInHours(getOutHour(), getInHour()));
		}
		return totalHours;
	}

	/**
	 * @param totalHours the totalHours to set
	 */
	public void setTotalHours(Double totalHours) {
		this.totalHours = totalHours;
	}

}
