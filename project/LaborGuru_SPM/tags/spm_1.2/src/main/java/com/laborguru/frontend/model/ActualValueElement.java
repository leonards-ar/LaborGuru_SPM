package com.laborguru.frontend.model;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.laborguru.util.CalendarUtils;
import com.laborguru.util.NumberUtils;
import com.laborguru.util.SpmConstants;

public class ActualValueElement {

	private BigDecimal mainValue;
	private Double hours;
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
	
	public Boolean getEditable() {
		if(System.getProperty(SpmConstants.DEBUG) != null) {
			return Boolean.TRUE;
		} else {			
			return (CalendarUtils.todayWithoutTime().compareTo(getDate()) >= 0);
		}
	}	

	public int getMainValueToDisplay(){
		return NumberUtils.bigDecimalToInt(mainValue);
	}
	
	public String toString(){
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
	   	.append("mainValue" , getMainValue())
	   	.append("hours" , getHours())
	   	.append("date",getDate())
	   	.toString();		
		
	}
	/**
	 * @return the actualHours
	 */
	public Double getHours() {
		if (this.hours == null)
		{
			setHours(0.0);
		}
		
		return hours;
	}
	/**
	 * @param actualHours the actualHours to set
	 */
	public void setHours(Double actualHours) {
		this.hours = actualHours;
	}
}
