package com.laborguru.frontend.model;

import java.math.BigDecimal;
import java.util.Date;

import com.laborguru.util.CalendarUtils;
import com.laborguru.util.SpmConstants;

public class ActualValueElement {

	private BigDecimal mainValue;	
	private Date date;
	
	/**
	 * @return the mainValue
	 */
	public BigDecimal getMainValue() {
		if (this.mainValue == null){
			setMainValue(new BigDecimal(SpmConstants.INIT_VALUE_ZERO));
		}
		return this.mainValue;
	}
	/**
	 * @param mainValue the mainValue to set
	 */
	public void setMainValue(BigDecimal mainValue) {
		this.mainValue = mainValue;
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
	
	public Boolean getEditable(){
		return (CalendarUtils.todayWithoutTime().compareTo(getDate()) >= 0);
	}	

}
