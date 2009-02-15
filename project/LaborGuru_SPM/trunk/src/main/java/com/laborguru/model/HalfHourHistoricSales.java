package com.laborguru.model;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.laborguru.util.CalendarUtils;


/**
 * Deals with Half an hour historic sales behaviour.
 * 
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class HalfHourHistoricSales extends SpmObject implements Comparable<HalfHourHistoricSales> {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private BigDecimal value;
	private Integer index;
	private DailyHistoricSales dailyHistoricSales;
	private Date time;
	
	
	public HalfHourHistoricSales(){
		
	}
		
	/**
	 * @param obj
	 * @return
	 * @see com.laborguru.model.SpmObject#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;		

		final HalfHourHistoricSales other = (HalfHourHistoricSales) obj;
		
		return new EqualsBuilder()
		.append(getValue(), other.getValue())
		.append(getIndex(), other.getIndex())
		.isEquals();	
	}

	/**
	 * @return
	 * @see com.laborguru.model.SpmObject#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
		.append(getValue())
		.append(getIndex())
		.toHashCode();
	}

	/**
	 * @return
	 * @see com.laborguru.model.SpmObject#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, DEFAULT_TO_STRING_STYLE)
	   	.append("id" , getId())
	   	.append("value",getValue())
	   	.append("index",getIndex())
	   	.toString();	
	}

	/**
	 * @param dailyHistoricSales
	 */
	public void setDailyHistoricSales(DailyHistoricSales dailyHistoricSales) {
		this.dailyHistoricSales = dailyHistoricSales;
	}

	/**
	 * @return
	 */
	public DailyHistoricSales getDailyHistoricSales() {
		return dailyHistoricSales;
	}	
	
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
	 * @return the index
	 */
	public Integer getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}

	/**
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	
	/**
	 * Sets time 
	 * @param time the time to set
	 */
	public void setTime(String time) {
		setTime(CalendarUtils.displayTimeToDate(time));
	}	
	
	/**
	 * Returns time as string if not null.
	 * @return the time as string
	 */
	public String getTimeAsString(){
		
		if (time != null)
			return CalendarUtils.dateToDisplayTime(this.getTime());
		
		return null;
	}

	/**
	 * Compares to HalfHourHistoricSales by time
	 * @param object
	 * @return
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(HalfHourHistoricSales object) {

		if (object == null){
			return 1;
		}
		
		Date anotherDate = object.getTime();
		
		if (getTime() != null){
			return (anotherDate != null)? getTime().compareTo(anotherDate): 1;
		}
		
		return (anotherDate != null)?-1:0;
	}
}
