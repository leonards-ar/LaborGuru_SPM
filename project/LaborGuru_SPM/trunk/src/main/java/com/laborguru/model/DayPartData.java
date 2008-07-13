/*
 * File name: DayPartData.java
 * Creation date: 13/07/2008 09:41:42
 * Copyright Mindpool
 */
package com.laborguru.model;

/**
 * This object stores data related to positions and day parts.<br/>
 * Among the values stored in this object you can find
 * <ul>
 * <li>Labor Allowances – Weekday Guest Services</li>
 * <li>Labor Allowances – Weekend Guest Services</li>
 * <li>Labor Allowances – Variable Flexible</li>
 * <li>Labor Allowances – Variable Opening</li>
 * <li>Labor Allowances – Fixed Guest Service</li>
 * <li>Labor Assumptions – Minimum Staffing</li>
 * </ul>
 * 
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class DayPartData extends SpmObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6322463798852263411L;

	private Double weekdayGuestService;
	private Double weekendGuestService;
	private Double variableFlexible;
	private Double variableOpening;
	private Double fixedGuestService;
	private Integer minimunStaffing;
	private Position position;

	/**
	 * 
	 */
	public DayPartData() {
	}

	/**
	 * @param other
	 * @return
	 * @see com.laborguru.model.SpmObject#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {
		return false;
	}

	/**
	 * @return
	 * @see com.laborguru.model.SpmObject#hashCode()
	 */
	@Override
	public int hashCode() {
		return 0;
	}

	/**
	 * @return
	 * @see com.laborguru.model.SpmObject#toString()
	 */
	@Override
	public String toString() {
		return null;
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
	 * @return the weekdayGuestService
	 */
	public Double getWeekdayGuestService() {
		return weekdayGuestService;
	}

	/**
	 * @param weekdayGuestService the weekdayGuestService to set
	 */
	public void setWeekdayGuestService(Double weekdayGuestService) {
		this.weekdayGuestService = weekdayGuestService;
	}

	/**
	 * @return the weekendGuestService
	 */
	public Double getWeekendGuestService() {
		return weekendGuestService;
	}

	/**
	 * @param weekendGuestService the weekendGuestService to set
	 */
	public void setWeekendGuestService(Double weekendGuestService) {
		this.weekendGuestService = weekendGuestService;
	}

	/**
	 * @return the variableFlexible
	 */
	public Double getVariableFlexible() {
		return variableFlexible;
	}

	/**
	 * @param variableFlexible the variableFlexible to set
	 */
	public void setVariableFlexible(Double variableFlexible) {
		this.variableFlexible = variableFlexible;
	}

	/**
	 * @return the variableOpening
	 */
	public Double getVariableOpening() {
		return variableOpening;
	}

	/**
	 * @param variableOpening the variableOpening to set
	 */
	public void setVariableOpening(Double variableOpening) {
		this.variableOpening = variableOpening;
	}

	/**
	 * @return the fixedGuestService
	 */
	public Double getFixedGuestService() {
		return fixedGuestService;
	}

	/**
	 * @param fixedGuestService the fixedGuestService to set
	 */
	public void setFixedGuestService(Double fixedGuestService) {
		this.fixedGuestService = fixedGuestService;
	}

	/**
	 * @return the minimunStaffing
	 */
	public Integer getMinimunStaffing() {
		return minimunStaffing;
	}

	/**
	 * @param minimunStaffing the minimunStaffing to set
	 */
	public void setMinimunStaffing(Integer minimunStaffing) {
		this.minimunStaffing = minimunStaffing;
	}

}
