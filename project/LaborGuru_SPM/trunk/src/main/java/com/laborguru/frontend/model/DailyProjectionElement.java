package com.laborguru.frontend.model;

import java.math.BigDecimal;
import java.util.Date;

import com.laborguru.util.CalendarUtils;

public class DailyProjectionElement {
	
	private BigDecimal calculatedProjection;
	private BigDecimal adjustedProjection;
	private Date projectionDate;
	
	/**
	 * @return the calculatedProjection
	 */
	public BigDecimal getCalculatedProjection() {
		return calculatedProjection;
	}
	/**
	 * @param calculatedProjection the calculatedProjection to set
	 */
	public void setCalculatedProjection(BigDecimal calculatedProjection) {
		this.calculatedProjection = calculatedProjection;
	}
	/**
	 * @return the adjustedProjection
	 */
	public BigDecimal getAdjustedProjection() {
		return adjustedProjection;
	}
	/**
	 * @param adjustedProjection the adjustedProjection to set
	 */
	public void setAdjustedProjection(BigDecimal adjustedProjection) {
		this.adjustedProjection = adjustedProjection;
	}
	/**
	 * @return the projectionDate
	 */
	public Date getProjectionDate() {
		return projectionDate;
	}
	/**
	 * @param projectionDate the projectionDate to set
	 */
	public void setProjectionDate(Date projectionDate) {
		this.projectionDate = projectionDate;
	}
	
	public Boolean getEditable(){
		return (CalendarUtils.todayWithoutTime().compareTo(getProjectionDate()) <= 0);
	}

}
