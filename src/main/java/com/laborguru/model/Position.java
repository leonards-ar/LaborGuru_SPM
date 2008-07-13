package com.laborguru.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.laborguru.model.comparator.ComparableObject;

public class Position extends SpmObject implements ComparableObject{

	/**
	 * Default serial version ID
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Store store;
	private String name;
	
	private List<DayOfWeekData> dayOfWeekData;
	private Set<DayPartData> dayPartData;
	
	/**
	 * Position toString
	 * @return string version of the object 
	 * @see com.laborguru.model.SpmObject#toString()
	 */
	public String toString()
	{
		return new ToStringBuilder(this, DEFAULT_TO_STRING_STYLE)
	   	.append("id" , id)
	   	.append("name",name)
	   	.toString();		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
		.append(this.name)
		.append(this.store != null? this.store.getId():null)
		.toHashCode();
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
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		final Position other = (Position) obj;
		
		return new EqualsBuilder().append(this.name, other.name)
		.append(this.store != null? this.store.getId():null, other.store != null? other.store.getId():null)
		.isEquals();
	}

	/**
	 * @return the dayOfWeekData
	 */
	public List<DayOfWeekData> getDayOfWeekData() {
		if(dayOfWeekData == null) {
			dayOfWeekData = new ArrayList<DayOfWeekData>();
		}
		return dayOfWeekData;
	}

	/**
	 * @param dayOfWeekData the dayOfWeekData to set
	 */
	public void setDayOfWeekData(List<DayOfWeekData> dayOfWeekData) {
		this.dayOfWeekData = dayOfWeekData;
	}

	/**
	 * @return the dayPartData
	 */
	public Set<DayPartData> getDayPartData() {
		if(dayPartData == null) {
			dayPartData = new HashSet<DayPartData>();
		}
		return dayPartData;
	}

	/**
	 * @param dayPartData the dayPartData to set
	 */
	public void setDayPartData(Set<DayPartData> dayPartData) {
		this.dayPartData = dayPartData;
	}
}
