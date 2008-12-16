package com.laborguru.model.report;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.laborguru.model.SpmObject;
import com.laborguru.util.SpmConstants;

/**
 *
 * @author <a href="fbarreraoro@gmail.com">Federico Barrera Oro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class TotalHour extends SpmObject{
	
	private static final long serialVersionUID = -7940750254658565313L;
	
	private Date day;
	private BigDecimal schedule;
	private BigDecimal target;
	
	
	/**
	 * @return the day
	 */
	public Date getDay() {
		return day;
	}
	/**
	 * @param column the day to set
	 */
	public void setDay(Date day) {
		this.day = day;
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
		return schedule.subtract(target);
	}
	
	/**
	 * @return the percentaje
	 */
	public BigDecimal getPercentaje() {
		if(target.compareTo(SpmConstants.BD_ZERO_VALUE) == 0) {
			return SpmConstants.BD_ZERO_VALUE;
		}
		return getDifference().divide(target, 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).abs();
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
		.append(this.day, other.day)
		.isEquals();		
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
		.append(this.schedule)
		.append(this.target)
		.append(this.day)
		.toHashCode();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, DEFAULT_TO_STRING_STYLE)
		.append(new SimpleDateFormat("E MM/dd").format(this.day))
		.append(this.schedule)
		.append(this.target)
		.append(getDifference())
		.append(getPercentaje())
		.toString();
	}
	
	
}
