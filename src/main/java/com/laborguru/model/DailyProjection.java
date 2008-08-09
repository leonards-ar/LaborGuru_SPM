package com.laborguru.model;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

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
	
	private List<HalfHourProjection> halfHourProjections;
	
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
	public void removeDayPart(HalfHourProjection halfHourProjection){
		
		if (halfHourProjection == null){
			throw new IllegalArgumentException("Null halfHourProjection passed in as parameter");
		}
				
		getHalfHourProjections().remove(halfHourProjection);
		
		if (halfHourProjection.getProjection() != null){
			halfHourProjection.setProjection(null);
		}
	}	
}
