package com.laborguru.model.report;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.laborguru.model.SpmObject;
import com.laborguru.util.NumberUtils;
import com.laborguru.util.SpmConstants;

/**
 *
 * @author <a href="fbarreraoro@gmail.com">Federico Barrera Oro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class TotalHour extends SpmObject {
	
	private static final long serialVersionUID = -7940750254658565313L;
	
	private Date day;
	private BigDecimal sales = SpmConstants.BD_ZERO_VALUE;
	private BigDecimal schedule = SpmConstants.BD_ZERO_VALUE;
	private BigDecimal target = SpmConstants.BD_ZERO_VALUE;
	private Double storeAverageVariable = SpmConstants.DOUBLE_ZERO_VALUE;
	private Double storeAverageWage = SpmConstants.DOUBLE_ZERO_VALUE;
	private Double storeTotalWage = SpmConstants.DOUBLE_ZERO_VALUE;
	
	/**
	 * 
	 */
	public TotalHour() {
		super();
	}
	
	/**
	 * 
	 * @param storeAverageVariable
	 * @param storeAverageWage
	 */
	public TotalHour(Double storeAverageVariable, Double storeAverageWage, Double storeTotalWage) {
		super();
		setStoreAverageVariable(storeAverageVariable);
		setStoreAverageWage(storeAverageWage);
		setStoreTotalWage(storeTotalWage);
	}
	
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
	 * @return the difference
	 */
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
	
	/**
	 * 
	 * @return
	 */
	public BigDecimal getVplhSchedule() {
		if(schedule == null || schedule.compareTo(SpmConstants.BD_ZERO_VALUE) == 0) {
			return SpmConstants.BD_ZERO_VALUE;
		}
		return getSales().divide(schedule, 2, SpmConstants.ROUNDING_MODE);		
	}

	/**
	 * 
	 * @return
	 */
	public BigDecimal getProjectedSales() {
		return new BigDecimal(NumberUtils.getDoubleValue(getStoreAverageVariable())).multiply(getSales());
	}
	
	/**
	 * 
	 * @return
	 */
	public BigDecimal getScheduleLaborPercentage() {
		BigDecimal ps = getProjectedSales();
		if(ps != null && ps.compareTo(SpmConstants.BD_ZERO_VALUE) != 0) {
			return getSchedule().multiply(getStoreAverageWageAsBigDecimal()).divide(ps, 2, SpmConstants.ROUNDING_MODE);
		} else {
			return SpmConstants.BD_ZERO_VALUE;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public BigDecimal getTargetLaborPercentage() {
		BigDecimal ps = getProjectedSales();
		if(ps != null && ps.compareTo(SpmConstants.BD_ZERO_VALUE) != 0) {
			return getTarget().multiply(getStoreAverageWageAsBigDecimal()).divide(ps, 2, SpmConstants.ROUNDING_MODE);
		} else {
			return SpmConstants.BD_ZERO_VALUE;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public BigDecimal getVplhTarget() {
		if(target == null || target.compareTo(SpmConstants.BD_ZERO_VALUE) == 0) {
			return SpmConstants.BD_ZERO_VALUE;
		}
		return getSales().divide(target, 2, SpmConstants.ROUNDING_MODE);		
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
		.append(this.sales, other.sales)
		.append(this.day, other.day)
		.isEquals();		
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
		.append(this.sales)
		.append(this.schedule)
		.append(this.target)
		.append(this.day)
		.toHashCode();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, DEFAULT_TO_STRING_STYLE)
		.append(new SimpleDateFormat("E MM/dd").format(this.day))
		.append(this.sales)
		.append(this.schedule)
		.append(this.target)
		.append(getDifference())
		.append(getPercentage())
		.toString();
	}
	
	/**
	 * 
	 * @return
	 */
	public Double getStoreAverageVariable() {
		return storeAverageVariable;
	}
	
	/**
	 * 
	 * @param storeAverageVariable
	 */
	public void setStoreAverageVariable(Double storeAverageVariable) {
		this.storeAverageVariable = storeAverageVariable;
	}

	/**
	 * 
	 * @return
	 */
	public Double getStoreAverageWage() {
		return storeAverageWage;
	}

	/**
	 * 
	 * @return
	 */
	private BigDecimal getStoreAverageWageAsBigDecimal() {
		return new BigDecimal(NumberUtils.getDoubleValue(getStoreAverageWage()));
	}
	
	/**
	 * 
	 * @param storeAverageWage
	 */
	public void setStoreAverageWage(Double storeAverageWage) {
		this.storeAverageWage = storeAverageWage;
	}

	/**
	 * 
	 * @return
	 */
	public Double getStoreTotalWage() {
		return storeTotalWage;
	}

	/**
	 * 
	 * @param storeTotalWage
	 */
	public void setStoreTotalWage(Double storeTotalWage) {
		this.storeTotalWage = storeTotalWage;
	}
}
