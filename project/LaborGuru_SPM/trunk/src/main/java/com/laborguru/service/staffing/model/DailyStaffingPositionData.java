/*
 * File name: DailyStaffingPositionData.java
 * Creation date: 12/11/2008 23:18:31
 * Copyright Mindpool
 */
package com.laborguru.service.staffing.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.laborguru.model.DayPartData;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class DailyStaffingPositionData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2629969275009440055L;
	private BigDecimal dayPartTotalProjection = new BigDecimal(0.0);
	private DayPartData dayPartData = null;
	
	/**
	 * 
	 */
	public DailyStaffingPositionData() {
	}

	/**
	 * @return the dayPartTotalProjection
	 */
	public BigDecimal getDayPartTotalProjection() {
		return dayPartTotalProjection;
	}

	/**
	 * @param dayPartTotalProjection the dayPartTotalProjection to set
	 */
	public void setDayPartTotalProjection(BigDecimal dayPartTotalProjection) {
		this.dayPartTotalProjection = dayPartTotalProjection;
	}
	
	/**
	 * 
	 * @param halfHourProjection
	 */
	public void addHalfHourProjection(BigDecimal halfHourProjection) {
		setDayPartTotalProjection(getDayPartTotalProjection().add(halfHourProjection));
	}

	/**
	 * @return the dayPartData
	 */
	public DayPartData getDayPartData() {
		return dayPartData;
	}

	/**
	 * @param dayPartData the dayPartData to set
	 */
	public void setDayPartData(DayPartData dayPartData) {
		this.dayPartData = dayPartData;
	}

}
