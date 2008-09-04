package com.laborguru.model;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.ToStringBuilder;

public class HalfHourCalculated extends SpmObject {

	private static final long serialVersionUID = 1L;
	private String time;
	private BigDecimal value;
	
	public HalfHourCalculated(String time, BigDecimal value){
		this.time = time;
		this.value = value;
	}
	
	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}
	/**
	 * @return the value
	 */
	public BigDecimal getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	/**
	 * @return
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 0;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}
	/**
	 * @param obj
	 * @return
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		HalfHourCalculated other = (HalfHourCalculated) obj;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	public String toString(){
		return new ToStringBuilder(this, DEFAULT_TO_STRING_STYLE)
	   	.append("time" , getTime())
	   	.append("value",getValue())
	   	.toString();	
	}

}
