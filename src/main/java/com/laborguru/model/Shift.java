/*
 * File name: Shift.java
 * Creation date: Sep 20, 2008 5:10:17 PM
 * Copyright Mindpool
 */
package com.laborguru.model;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.laborguru.util.CalendarUtils;

/**
 * This class represents a shift which is a period of time in which an
 * employee occupies a position.
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class Shift extends SpmObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8048083516798654020L;
	
	private Integer id;
	private EmployeeSchedule employeeSchedule;
	private Date fromHour;
	private Date toHour;
	private Position position;
	private Integer shiftIndex;
	private Shift contiguousShift;
	private Shift startingShift;
	
	/**
	 * 
	 */
	public Shift() {
	}

	/**
	 * @param other
	 * @return
	 * @see com.laborguru.model.SpmObject#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		final Shift other = (Shift) obj;
		
		return new EqualsBuilder().append(this.fromHour, other.fromHour)
		.append(this.toHour, other.toHour)
		.append(this.employeeSchedule != null? this.employeeSchedule.getId():null, other.employeeSchedule != null? other.employeeSchedule.getId():null)
		.isEquals();
	}

	/**
	 * @return
	 * @see com.laborguru.model.SpmObject#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
		.append(this.fromHour)
		.append(this.toHour)
		.append(this.employeeSchedule != null? this.employeeSchedule.getId():null)
		.toHashCode();
	}

	/**
	 * @return
	 * @see com.laborguru.model.SpmObject#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, DEFAULT_TO_STRING_STYLE)
	   	.append("employeeSchedule" , employeeSchedule)
	   	.append("from", fromHour)
	   	.append("to", toHour)
	   	.append("position", position)
	   	.toString();	
	}

	/**
	 * 
	 * @return
	 */
	public boolean isBreak() {
		return this.position == null;
	}

	/**
	 * @return the fromHour
	 */
	public Date getFromHour() {
		return fromHour;
	}

	/**
	 * @param fromHour the fromHour to set
	 */
	public void setFromHour(Date fromHour) {
		this.fromHour = fromHour;
	}

	/**
	 * @return the toHour
	 */
	public Date getToHour() {
		return toHour;
	}

	/**
	 * @param toHour the toHour to set
	 */
	public void setToHour(Date toHour) {
		this.toHour = toHour;
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
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the employeeSchedule
	 */
	public EmployeeSchedule getEmployeeSchedule() {
		return employeeSchedule;
	}

	/**
	 * @param employeeSchedule the employeeSchedule to set
	 */
	public void setEmployeeSchedule(EmployeeSchedule employeeSchedule) {
		this.employeeSchedule = employeeSchedule;
	}

	/**
	 * @return the shiftIndex
	 */
	public Integer getShiftIndex() {
		return shiftIndex;
	}

	/**
	 * @param shiftIndex the shiftIndex to set
	 */
	public void setShiftIndex(Integer shiftIndex) {
		this.shiftIndex = shiftIndex;
	}	
	
	/**
	 * 
	 * @return
	 */
	public Double getTotalShiftHours() {
		return CalendarUtils.differenceInHours(getToHour(), getFromHour());
	}
	
	/**
	 * 
	 * @return
	 */
	public Double getTotalShiftHoursWithContiguous() {
		return CalendarUtils.differenceInHours(getToHourWithContiguousShift(), getFromHour());
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean hasContiguousShift() {
		return getContiguousShift() != null;
	}
	
	/**
	 * @return the contiguousShift
	 */
	public Shift getContiguousShift() {
		return contiguousShift;
	}

	/**
	 * @param contiguousShift the contiguousShift to set
	 */
	public void setContiguousShift(Shift contiguousShift) {
		if(contiguousShift != null) {
			contiguousShift.startingShift = this;
		}
		this.contiguousShift = contiguousShift;
	}	
	
	/**
	 * @return the toHour
	 */
	public Date getToHourWithContiguousShift() {
		return hasContiguousShift() ? getContiguousShift().getToHour() : getToHour();
	}

	/**
	 * @return the referencedShift
	 */
	public boolean isReferencedShift() {
		return getStartingShift() != null;
	}

	/**
	 * @return the startingShift
	 */
	public Shift getStartingShift() {
		return startingShift;
	}

	/**
	 * @param startingShift the startingShift to set
	 */
	public void setStartingShift(Shift startingShift) {
		if(startingShift != null) {
			startingShift.contiguousShift = this;
		}
		this.startingShift = startingShift;
	}
}
