package com.laborguru.model;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class TotalHour extends SpmObject{
	
	private static final long serialVersionUID = -7940750254658565313L;
	
	private String columnName;
	private BigDecimal schedule;
	private BigDecimal target;
	private BigDecimal difference;
	private BigDecimal percentaje;
	
	
	/**
	 * @return the column
	 */
	public String getColumnName() {
		return columnName;
	}
	/**
	 * @param column the column to set
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
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
	/**
	 * @return the difference
	 */
	public BigDecimal getDifference() {
		return difference;
	}
	/**
	 * @param difference the difference to set
	 */
	public void setDifference(BigDecimal difference) {
		this.difference = difference;
	}
	/**
	 * @return the percentaje
	 */
	public BigDecimal getPercentaje() {
		return percentaje;
	}
	/**
	 * @param percentaje the percentaje to set
	 */
	public void setPercentaje(BigDecimal percentaje) {
		this.percentaje = percentaje;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		final TotalHour other = (TotalHour) obj;
		
		return new EqualsBuilder().append(this.schedule, other.schedule)
		.append(this.target, other.target)
		.append(this.columnName, other.columnName)
		.isEquals();		
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
		.append(this.schedule)
		.append(this.target)
		.append(this.columnName)
		.toHashCode();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, DEFAULT_TO_STRING_STYLE)
		.append(this.columnName)
		.append(this.schedule)
		.append(this.target)
		.append(this.difference)
		.append(this.percentaje)
		.toString();
	}
	
	
}
