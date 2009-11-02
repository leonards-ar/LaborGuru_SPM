package com.laborguru.model.report;

import java.math.BigDecimal;

import com.laborguru.model.Location;
import com.laborguru.model.Region;
import com.laborguru.model.SpmObject;

public class TotalManagerHour extends SpmObject {

	private static final long serialVersionUID = -4263736677595272709L;
	
	Location location;
	BigDecimal sales;
	BigDecimal schedule;
	BigDecimal target;
	
	
	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * @return the sales
	 */
	public BigDecimal getSales() {
		return sales;
	}

	/**
	 * @param sales the sales to set
	 */
	public void setSales(BigDecimal sales) {
		this.sales = sales;
	}

	/**
	 * @return the schedule
	 */
	public BigDecimal getSchedule() {
		return schedule;
	}

	/**
	 * @param schedule the schedule to set
	 */
	public void setSchedule(BigDecimal schedule) {
		this.schedule = schedule;
	}

	/**
	 * @return the target
	 */
	public BigDecimal getTarget() {
		return target;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(BigDecimal target) {
		this.target = target;
	}

	@Override
	public boolean equals(Object other) {
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public String toString() {
		return null;
	}
	
	
	
	

}
