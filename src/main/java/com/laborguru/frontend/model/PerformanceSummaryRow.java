/*
 * File name: PerformanceSummaryRow.java
 * Creation date: 21/02/2009 16:09:43
 * Copyright Mindpool
 */
package com.laborguru.frontend.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.laborguru.util.CalendarUtils;
import com.laborguru.util.NumberUtils;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class PerformanceSummaryRow implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6812126214877741390L;
	
	private Date date;
	private BigDecimal projectedVolume;
	private Double projectedTarget;
	private Double projectedScheduled;
	
	private BigDecimal actualVolume;
	private Double actualTarget;
	private Double actualScheduled;	
	
	/**
	 * 
	 */
	public PerformanceSummaryRow() {
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return the projectedVolume
	 */
	public BigDecimal getProjectedVolume() {
		return projectedVolume;
	}
	/**
	 * @param projectedVolume the projectedVolume to set
	 */
	public void setProjectedVolume(BigDecimal projectedVolume) {
		this.projectedVolume = projectedVolume;
	}
	/**
	 * @return the projectedTarget
	 */
	public Double getProjectedTarget() {
		return projectedTarget;
	}
	/**
	 * @param projectedTarget the projectedTarget to set
	 */
	public void setProjectedTarget(Double projectedTarget) {
		this.projectedTarget = projectedTarget;
	}
	/**
	 * @return the projectedScheduled
	 */
	public Double getProjectedScheduled() {
		return projectedScheduled;
	}
	/**
	 * @param projectedScheduled the projectedScheduled to set
	 */
	public void setProjectedScheduled(Double projectedScheduled) {
		this.projectedScheduled = projectedScheduled;
	}
	/**
	 * @return the actualVolume
	 */
	public BigDecimal getActualVolume() {
		return actualVolume;
	}
	/**
	 * @param actualVolume the actualVolume to set
	 */
	public void setActualVolume(BigDecimal actualVolume) {
		this.actualVolume = actualVolume;
	}
	/**
	 * @return the actualTarget
	 */
	public Double getActualTarget() {
		return actualTarget;
	}
	/**
	 * @param actualTarget the actualTarget to set
	 */
	public void setActualTarget(Double actualTarget) {
		this.actualTarget = actualTarget;
	}
	/**
	 * @return the actualScheduled
	 */
	public Double getActualScheduled() {
		return actualScheduled;
	}
	/**
	 * @param actualScheduled the actualScheduled to set
	 */
	public void setActualScheduled(Double actualScheduled) {
		this.actualScheduled = actualScheduled;
	}

	/**
	 * 
	 * @return
	 */
	public Double getProjectedDifference() {
		return new Double(NumberUtils.getDoubleValue(getProjectedScheduled()) - NumberUtils.getDoubleValue(getProjectedTarget()));
	}
	
	/**
	 * 
	 * @return
	 */
	public Double getProjectedDifferencePercentage() {
		double target = NumberUtils.getDoubleValue(getProjectedTarget());
		if(target != 0.0) {
			double diff = getProjectedDifference().doubleValue();
			return new Double(diff / target * 100);
		} else {
			return new Double(0.0);
		}
	}	

	/**
	 * 
	 * @return
	 */
	public Double getActualDifference() {
		return new Double(NumberUtils.getDoubleValue(getActualScheduled()) - NumberUtils.getDoubleValue(getActualTarget()));
	}
	
	/**
	 * 
	 * @return
	 */
	public Double getActualDifferencePercentage() {
		double target = NumberUtils.getDoubleValue(getActualTarget());
		if(target != 0.0) {
			double diff = getActualDifference().doubleValue();
			return new Double(diff / target * 100);
		} else {
			return new Double(0.0);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public String getProjectedTargetAsString() {
		return CalendarUtils.hoursToTime(getProjectedTarget());
	}
	
	/**
	 * 
	 * @return
	 */
	public String getActualTargetAsString() {
		return CalendarUtils.hoursToTime(getActualTarget());
	}
	
	/**
	 * 
	 * @return
	 */
	public String getProjectedScheduledAsString() {
		return CalendarUtils.hoursToTime(getProjectedScheduled());
	}
	
	/**
	 * 
	 * @return
	 */
	public String getActualScheduledAsString() {
		return CalendarUtils.hoursToTime(getActualScheduled());
	}

	/**
	 * 
	 * @return
	 */
	public String getProjectedDifferenceAsString() {
		return CalendarUtils.hoursToTime(getProjectedDifference());
	}
	
	/**
	 * 
	 * @return
	 */
	public String getActualDifferenceAsString() {
		return CalendarUtils.hoursToTime(getActualDifference());
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isProjectedVolumeSet() {
		return NumberUtils.getDoubleValue(getProjectedVolume()) > 0.0;
	}
	
}
