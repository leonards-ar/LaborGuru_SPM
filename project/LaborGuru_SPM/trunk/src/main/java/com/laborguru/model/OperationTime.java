/*
 * File name: DayOperationHours.java
 * Creation date: Jul 6, 2008 12:55:16 AM
 * Copyright Mindpool
 */
package com.laborguru.model;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * This class holds the start and close hours on a daily basis for Stores.
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class OperationTime extends SpmObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5285245853035872927L;
	private Integer id;
	private Date openHour;
	private Date closeHour;
	private Store store;
	
	/**
	 * Integer holding the same value for
	 * day constants defined in the java.util.Calendar class.
	 */	
	private DayOfWeek dayOfWeek;
	
	/**
	 * 
	 */
	public OperationTime() {
	}

	/**
	 * @return the openHour
	 */
	public Date getOpenHour() {
		return openHour;
	}

	/**
	 * @param openHour the openHour to set
	 */
	public void setOpenHour(Date openHour) {
		this.openHour = openHour;
	}

	/**
	 * @return the closeHour
	 */
	public Date getCloseHour() {
		return closeHour;
	}

	/**
	 * @param closeHour the closeHour to set
	 */
	public void setCloseHour(Date closeHour) {
		this.closeHour = closeHour;
	}

	/**
	 * @return the store
	 */
	public Store getStore() {
		return store;
	}

	/**
	 * @param store the store to set
	 */
	public void setStore(Store store) {
		this.store = store;
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
	 * @return
	 * @see com.laborguru.model.SpmObject#hashCode()
	 */
	@Override
	public int hashCode() {
		
		return new HashCodeBuilder(17, 37)
		.append(getDayOfWeek())
		.append(getStore() != null? getStore().getId() : null)
		.toHashCode();
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
		
		final OperationTime other = (OperationTime) obj;

		return new EqualsBuilder()
		.append(getDayOfWeek(), other.getDayOfWeek())
		.append(getStore() != null ? getStore().getId() : null, other.getStore() != null ? other.getStore().getId() : null)
		.isEquals();
	}
	
	/**
	 * @return the dayOfWeek
	 */
	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getDayOfWeekIndex() {
		return getDayOfWeek() != null ? new Integer(getDayOfWeek().ordinal()) : null;
	}
	
	/**
	 * 
	 * @param dayofWeek
	 */
	public void setDayOfWeekIndex(Integer dayOfWeek) {
		if(dayOfWeek != null) {
			setDayOfWeek(DayOfWeek.values()[dayOfWeek.intValue()]);
		} else {
			setDayOfWeek(null);
		}
	}
	
	/**
	 * @param dayOfWeek the dayOfWeek to set
	 */
	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	/**
	 * 
	 * @param list
	 * @param dayOfWeek
	 * @return
	 */
	public static OperationTime getOperationTimeByDayOfWeek(List<OperationTime> list, DayOfWeek dayOfWeek) {
		for(OperationTime aOperationTime: list) {
			if(aOperationTime.getDayOfWeek().equals(dayOfWeek)) {
				return aOperationTime;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @return
	 * @see com.laborguru.model.SpmObject#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, DEFAULT_TO_STRING_STYLE)
	   	.append("id" , id)
	   	.append("store" , store)
	   	.append("day of week", dayOfWeek)
	   	.append("open hour", openHour)
	   	.append("close hour", closeHour)
	   	.toString();
	}
	
	

}
