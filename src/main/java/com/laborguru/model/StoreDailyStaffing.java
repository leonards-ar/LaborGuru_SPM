/*
 * File name: StoreDailyStaffing.java
 * Creation date: Oct 21, 2008 2:13:30 PM
 * Copyright Mindpool
 */
package com.laborguru.model;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class StoreDailyStaffing extends SpmObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5438231250357406297L;
	
	private Map<Position, DailyStaffing> storeDailyStaffing = new HashMap<Position, DailyStaffing>();
	private Store store;
	private Date date;
	
	/**
	 * 
	 */
	public StoreDailyStaffing(Store store) {
		super();
		setStore(store);
	}

	/**
	 * 
	 * @param dailyStaffing
	 */
	public void addDailyStaffing(DailyStaffing dailyStaffing) {
		if(dailyStaffing != null && dailyStaffing.getPosition() != null) {
			this.storeDailyStaffing.put(dailyStaffing.getPosition(), dailyStaffing);
		}
	}
	
	/**
	 * 
	 * @param position
	 * @return
	 */
	public DailyStaffing getDailyStaffing(Position position) {
		return storeDailyStaffing.get(position);
	}
	
	/**
	 * 
	 * @return
	 */
	public Collection<DailyStaffing> getStoreDailyStaffing() {
		return this.storeDailyStaffing.values();
	}
	
	/**
	 * 
	 * @param time
	 * @return
	 */
	public int getHalfHourStaffing(Date time) {
		int total = 0;
		int halfHourIndex = -1;
		for(DailyStaffing dailyStaffing : getStoreDailyStaffing()) {
			if(halfHourIndex < 0) {
				halfHourIndex = dailyStaffing.getHalfHourIndex(time);
			}
			total += dailyStaffing.getHalfHourStaffing().get(halfHourIndex).getCalculatedStaff().intValue();
		}
		return total;
	}
	
	/**
	 * 
	 * @param position
	 * @param time
	 * @return
	 */
	public int getHalfHourStaffing(Position position, Date time) {
		DailyStaffing dailyStaffing = this.storeDailyStaffing.get(position);
		if(dailyStaffing != null) {
			int halfHourIndex = dailyStaffing.getHalfHourIndex(time);
			return dailyStaffing.getHalfHourStaffing().get(halfHourIndex).getCalculatedStaff().intValue();
		} else {
			return 0;
		}
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
	 * 
	 * @return
	 */
	public Double getTotalDailyStaffing() {
		double total = 0.0;
		Double positionTotal;
		for(DailyStaffing dailyStaffing : getStoreDailyStaffing()) {
			positionTotal = dailyStaffing.getCalculatedDailyHours();
			total += positionTotal != null ? positionTotal.doubleValue() : 0.0;
		}
		return new Double(total);
	}
}
