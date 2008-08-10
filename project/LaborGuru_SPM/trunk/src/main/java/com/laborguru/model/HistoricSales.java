package com.laborguru.model;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Deals with sales data collected from the client.
 * 
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class HistoricSales extends SpmObject {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Date dateTime;
	private Integer dayOfWeek;
	private BigDecimal salesValue;
	
	private Integer checksNumber;
	private Integer guestsNumber;
	private String product;
	
	private Store store;
	
	
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

		final HistoricSales other = (HistoricSales) obj;
		
		return new EqualsBuilder()
		.append(getDateTime(), other.getDateTime())
		.append(getDayOfWeek(), other.getDayOfWeek())
		.isEquals();	
	}

	/**
	 * @return
	 * @see com.laborguru.model.SpmObject#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
		.append(getDateTime())
		.append(getDayOfWeek())
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
	   	.append("dateTime",getDateTime())
	   	.append("dow",getDayOfWeek())	   	
	   	.append("salesValue",getSalesValue())
	   	.toString();	
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
	 * @return the date
	 */
	public Date getDateTime() {
		return dateTime;
	}

	/**
	 * @param date the date to set
	 */
	public void setDateTime(Date date) {
		this.dateTime = date;
	}

	/**
	 * @return the dayOfWeek
	 */
	public Integer getDayOfWeek() {
		return dayOfWeek;
	}

	/**
	 * @param dayOfWeek the dayOfWeek to set
	 */
	public void setDayOfWeek(Integer dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	/**
	 * @return the salesValue
	 */
	public BigDecimal getSalesValue() {
		return salesValue;
	}

	/**
	 * @param salesValue the salesValue to set
	 */
	public void setSalesValue(BigDecimal salesValue) {
		this.salesValue = salesValue;
	}

	/**
	 * @return the checksNumber
	 */
	public Integer getChecksNumber() {
		return checksNumber;
	}

	/**
	 * @param checksNumber the checksNumber to set
	 */
	public void setChecksNumber(Integer checksNumber) {
		this.checksNumber = checksNumber;
	}

	/**
	 * @return the guestsNumber
	 */
	public Integer getGuestsNumber() {
		return guestsNumber;
	}

	/**
	 * @param guestsNumber the guestsNumber to set
	 */
	public void setGuestsNumber(Integer guestsNumber) {
		this.guestsNumber = guestsNumber;
	}

	/**
	 * @return the product
	 */
	public String getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(String product) {
		this.product = product;
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

}
