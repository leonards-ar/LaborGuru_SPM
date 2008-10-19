/*
 * File name: DailyCalculatedPositionStaff.java
 * Creation date: 19/10/2008 15:46:47
 * Copyright Mindpool
 */
package com.laborguru.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class DailyCalculatedPositionStaff extends SpmObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7207256794677396300L;
	
	private Long id;
	private Date date;
	private Date startingTime;
	
	private Position position;
	private List<HalfHourCalculatedPositionStaff> halfHourCalculatedPositionStaffing;
	
	
	/**
	 * 
	 */
	public DailyCalculatedPositionStaff() {
	}

	/**
	 * @param obj
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

		DailyCalculatedPositionStaff other = (DailyCalculatedPositionStaff) obj;
		
		return new EqualsBuilder()
		.append(getDate(), other.getDate())
		.append(getPosition(), other.getPosition())
		.isEquals();	
	}

	/**
	 * @return
	 * @see com.laborguru.model.SpmObject#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
		.append(getDate())
		.append(getPosition())
		.toHashCode();
	}

	/**
	 * @return
	 * @see com.laborguru.model.SpmObject#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, DEFAULT_TO_STRING_STYLE)
	   	.append("id" , getId())
	   	.append("date",getDate())
	   	.append("startingTime",getStartingTime())
	   	.toString();
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the projectionDate
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param projectionDate the projectionDate to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the startingTime
	 */
	public Date getStartingTime() {
		return startingTime;
	}

	/**
	 * @param startingTime the startingTime to set
	 */
	public void setStartingTime(Date startingTime) {
		this.startingTime = startingTime;
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
	 * @return the halfHourCalculatedPositionStaffing
	 */
	public List<HalfHourCalculatedPositionStaff> getHalfHourCalculatedPositionStaffing() {
		if(halfHourCalculatedPositionStaffing == null) {
			halfHourCalculatedPositionStaffing = new ArrayList<HalfHourCalculatedPositionStaff>();
		}
		return halfHourCalculatedPositionStaffing;
	}

	/**
	 * @param halfHourCalculatedPositionStaffing the halfHourCalculatedPositionStaffing to set
	 */
	public void setHalfHourCalculatedPositionStaffing(List<HalfHourCalculatedPositionStaff> halfHourCalculatedPositionStaffing) {
		this.halfHourCalculatedPositionStaffing = halfHourCalculatedPositionStaffing;
	}

	/**
	 * 
	 * @return
	 */
	public Store getStore() {
		return getPosition() != null ? getPosition().getStore() : null;
	}
	
	/**
	 * Adds a HalfHourCalculatedPositionStaff. Handles the bi-directional
	 * relation.
	 * @param halfHourCalculatedPositionStaff The HalfHourCalculatedPositionStaff to add
	 */
	public void addHalfHourProjection(HalfHourCalculatedPositionStaff halfHourCalculatedPositionStaff){
		
		if (halfHourCalculatedPositionStaff == null){
			throw new IllegalArgumentException("Null halfHourCalculatedPositionStaff passed in as parameter");
		}
		
		if (halfHourCalculatedPositionStaff.getDailyCalculatedPositionStaff() != null){
			halfHourCalculatedPositionStaff.getDailyCalculatedPositionStaff().getHalfHourCalculatedPositionStaffing().remove(halfHourCalculatedPositionStaff);
		}
		
		halfHourCalculatedPositionStaff.setDailyCalculatedPositionStaff(this);
		getHalfHourCalculatedPositionStaffing().add(halfHourCalculatedPositionStaff);
	}
	
	/**
	 * Removes halfHourCalculatedPositionStaff from the HalfHourCalculatedPositionStaffing list. Handles the bi-directional
	 * relation.
	 * @param halfHourCalculatedPositionStaff The halfHourCalculatedPositionStaff to remove
	 */
	public void removeHalfHourProjection(HalfHourCalculatedPositionStaff halfHourCalculatedPositionStaff){
		
		if (halfHourCalculatedPositionStaff == null){
			throw new IllegalArgumentException("Null halfHourCalculatedPositionStaff passed in as parameter");
		}
				
		getHalfHourCalculatedPositionStaffing().remove(halfHourCalculatedPositionStaff);
		
		if (halfHourCalculatedPositionStaff.getDailyCalculatedPositionStaff() != null){
			halfHourCalculatedPositionStaff.setDailyCalculatedPositionStaff(null);
		}
	}	
}
