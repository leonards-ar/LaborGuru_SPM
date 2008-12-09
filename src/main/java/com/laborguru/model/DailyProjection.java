package com.laborguru.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.joda.time.DateTime;

import com.laborguru.util.SpmConstants;

/**
 * Deals with the Daily projection behaviour.
 * Holds a list of half an hour projections.
 * 
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class DailyProjection extends SpmObject {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Date projectionDate;
	private Date startingTime;
	
	private Store store;
	
	private BigDecimal dailyProjectionVariable2;
	private BigDecimal dailyProjectionVariable3;
	
	
	private List<HalfHourProjection> halfHourProjections = new ArrayList<HalfHourProjection>();
		
	
	/**
	 * Returns the sum of all the halfhours defined for the projection.
	 * If there is no halfhours returns NULL
	 * @return the projection value or null
	 */
	public BigDecimal getDailyProjectionValue(){
		BigDecimal retValue = new BigDecimal("0.00");
		
		for (HalfHourProjection aHalfHourProjection: getHalfHourProjections()){
			retValue = retValue.add(aHalfHourProjection.getAdjustedValue());
		}
		
		return getHalfHourProjections().isEmpty()? null:retValue;
	}
	
	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;		

		final DailyProjection other = (DailyProjection) obj;
		
		return new EqualsBuilder()
		.append(getProjectionDate(), other.getProjectionDate())
		.append(getStartingTime(), other.getStartingTime())
		.isEquals();		
	}

	/**
	 * @return
	 * @see com.laborguru.model.SpmObject#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
		.append(getProjectionDate())
		.append(getStartingTime())
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
	   	.append("date",getProjectionDate())
	   	.append("startingTime",getStartingTime())
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
	 * @return the projectionDate
	 */
	public Date getProjectionDate() {
		return projectionDate;
	}

	/**
	 * @param projectionDate the projectionDate to set
	 */
	public void setProjectionDate(Date projectionDate) {
		this.projectionDate = projectionDate;
	}

	/**
	 * @return the startingTime
	 */
	public Date getStartingTime() {
		return startingTime;
	}

	/**
	 * @param startingTime the startingTime to set
	 */
	public void setStartingTime(Date startingTime) {
		this.startingTime = startingTime;
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

	/**
	 * @return the halfHourProjections
	 */
	public List<HalfHourProjection> getHalfHourProjections() {
		return halfHourProjections;
	}

	/**
	 * @param halfHourProjections the halfHourProjections to set
	 */
	@SuppressWarnings("unused")
	private void setHalfHourProjections(List<HalfHourProjection> halfHourProjections) {
		this.halfHourProjections = halfHourProjections;
	}
	
	
	/**
	 * Adds a HalfHourProjection. Handles the bi-directional
	 * relation.
	 * @param halfHourProjection The HalfHourProjection to add
	 */
	public void addHalfHourProjection(HalfHourProjection halfHourProjection){
		
		if (halfHourProjection == null){
			throw new IllegalArgumentException("Null halfHourProjection passed in as parameter");
		}
		
		if (halfHourProjection.getProjection() != null){
			halfHourProjection.getProjection().getHalfHourProjections().remove(halfHourProjection);
		}
		
		halfHourProjection.setProjection(this);
		getHalfHourProjections().add(halfHourProjection);
	}
	
	/**
	 * Removes HalfHourProjection from the projection list. Handles the bi-directional
	 * relation.
	 * @param halfHourProjection The halfHourProjection to remove
	 */
	public void removeHalfHourProjection(HalfHourProjection halfHourProjection){
		
		if (halfHourProjection == null){
			throw new IllegalArgumentException("Null halfHourProjection passed in as parameter");
		}
				
		getHalfHourProjections().remove(halfHourProjection);
		
		if (halfHourProjection.getProjection() != null){
			halfHourProjection.setProjection(null);
		}
	}

	/**
	 * Returns an empty instance of this class.
	 * :TODO: Move to another class and/or package? Too many logic for a model class??
	 * @param store
	 * @param date
	 * @return
	 */
	public static DailyProjection getEmptyDailyProjectionInstance(Store store, Date date) {
		DailyProjection dailyProjection = new DailyProjection();
		dailyProjection.setProjectionDate(date);
		dailyProjection.setStore(store);
		dailyProjection.setStartingTime(store.getStoreOperationTimeByDate(date).getOpenHour());
		
		HalfHourProjection aHalfHourProjection;
		DateTime nextTime = new DateTime().withDate(1970, 1, 1).withTime(0,30,0,0);
		
		for(int i = 0; i < SpmConstants.HALF_HOURS_IN_A_DAY; i++) {
			aHalfHourProjection = new HalfHourProjection();
			aHalfHourProjection.setTime(nextTime.toDate());
			aHalfHourProjection.setAdjustedValue(new BigDecimal(SpmConstants.INIT_VALUE_ZERO));
			aHalfHourProjection.setIndex(i);
			dailyProjection.addHalfHourProjection(aHalfHourProjection);
			
			nextTime = nextTime.plusMinutes(SpmConstants.HALF_HOUR);
		}
		
		return dailyProjection;
	}

	/**
	 * @return the dailyProjectionVariable3
	 */
	public BigDecimal getDailyProjectionVariable3() {
		if(dailyProjectionVariable3 == null) {
			setDailyProjectionVariable3(SpmConstants.BD_ZERO_VALUE);
		}
		return dailyProjectionVariable3;
	}

	/**
	 * @param dailyProjectionVariable3 the dailyProjectionVariable3 to set
	 */
	public void setDailyProjectionVariable3(BigDecimal dailyProjectionVariable3) {
		this.dailyProjectionVariable3 = dailyProjectionVariable3;
	}

	/**
	 * @return the dailyProjectionVariable2
	 */
	public BigDecimal getDailyProjectionVariable2() {
		if(dailyProjectionVariable2 == null) {
			setDailyProjectionVariable2(SpmConstants.BD_ZERO_VALUE);
		}
		return dailyProjectionVariable2;
	}

	/**
	 * @param dailyProjectionVariable2 the dailyProjectionVariable2 to set
	 */
	public void setDailyProjectionVariable2(BigDecimal dailyProjectionVariable2) {
		this.dailyProjectionVariable2 = dailyProjectionVariable2;
	}	
}
