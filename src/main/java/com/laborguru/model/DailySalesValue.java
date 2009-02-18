/*
 * File name: DailySalesValue.java
 * Creation date: Feb 17, 2009 5:24:30 PM
 * Copyright Mindpool
 */
package com.laborguru.model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public abstract class DailySalesValue extends SpmObject {
	private Long id;
	private Date salesDate;
	private Date startingTime;
	
	private Store store;

	/**
	 * 
	 */
	public DailySalesValue() {
	}

	/**
	 * 
	 * @return
	 */
	public abstract List<? extends HalfHourSalesValue> getHalfHourSalesValues();

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
	 * @return the salesDate
	 */
	public Date getSalesDate() {
		return salesDate;
	}

	/**
	 * @param salesDate the salesDate to set
	 */
	public void setSalesDate(Date salesDate) {
		this.salesDate = salesDate;
	}

}
