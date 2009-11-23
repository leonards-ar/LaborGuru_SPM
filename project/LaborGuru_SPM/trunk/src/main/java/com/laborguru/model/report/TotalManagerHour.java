package com.laborguru.model.report;

import java.math.BigDecimal;

import com.laborguru.model.SpmObject;
import com.laborguru.util.SpmConstants;

/**
 *
 * @author <a href="fbarreraoro@gmail.com">Federico Barrera Oro</a>
 * @version 1.1
 * @since SPM 1.1
 *
 */
public abstract class TotalManagerHour extends SpmObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 723035706927140054L;
	BigDecimal sales;
	BigDecimal schedule;
	BigDecimal target;

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

	public BigDecimal getDifference() {
		return schedule.subtract(target);
	}
	
	/**
	 * @return the percentage
	 */
	public BigDecimal getPercentage() {
		if(target.compareTo(SpmConstants.BD_ZERO_VALUE) == 0) {
			return SpmConstants.BD_ZERO_VALUE;
		}
		return getDifference().divide(target, 2, SpmConstants.ROUNDING_MODE).multiply(new BigDecimal(100));
	}

	public BigDecimal getScheduleMPH(){
		 if(schedule.compareTo(SpmConstants.BD_ZERO_VALUE) == 0) {
			 return SpmConstants.BD_ZERO_VALUE;
		 }
		 return sales.divide(schedule, 2, SpmConstants.ROUNDING_MODE);
	}
	
	public BigDecimal getTargerMPH(){
		 if(target.compareTo(SpmConstants.BD_ZERO_VALUE) == 0) {
			 return SpmConstants.BD_ZERO_VALUE;
		 }
		 return sales.divide(target, 2, SpmConstants.ROUNDING_MODE);
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
