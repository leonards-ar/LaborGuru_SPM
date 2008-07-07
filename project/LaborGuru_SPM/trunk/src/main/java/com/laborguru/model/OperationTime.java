/*
 * File name: DayOperationHours.java
 * Creation date: Jul 6, 2008 12:55:16 AM
 * Copyright Mindpool
 */
package com.laborguru.model;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

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
		Object storeId = getStore() != null ? getStore().getId() : null;
		
		return new HashCodeBuilder(17, 37)
		.append(getDayOfWeek())
		.append(storeId)
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

		if(getStore() == null || other.getStore() == null)
			return false;
		
		return new EqualsBuilder()
		.append(getDayOfWeek(), other.getDayOfWeek())
		.append(getStore().getId(), other.getStore().getId())
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
	public Integer getDayOfWeekAsInteger() {
		return getDayOfWeek().getDayOfWeek();
	}
	
	/**
	 * 
	 * @param dayofWeek
	 */
	public void setDayOfWeekAsInteger(Integer dayOfWeek) {
		setDayOfWeek(DayOfWeek.valueOf(String.valueOf(dayOfWeek)));
	}
	
	/**
	 * @param dayOfWeek the dayOfWeek to set
	 */
	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

}
