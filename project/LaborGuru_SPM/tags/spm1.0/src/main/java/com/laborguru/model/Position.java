package com.laborguru.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.laborguru.model.comparator.ComparableObject;

/**
 *
 * @author <a href="fbarrera@gmail.com">Federico Barrera Oro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
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
	private PositionGroup positionGroup;
	private Double utilizationBottom;
	private Double utilizationTop;
	private Integer utilizationMinimum;
	private Integer utilizationMaximum;
	private boolean manager;
	private boolean guestService;
	
	private Integer positionIndex;
	
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
	
	/**
	 * 
	 * @return
	 * @see com.laborguru.model.comparator.ComparableObject#getId()
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return
	 */
	public Store getStore() {
		return store;
	}
	
	/**
	 * 
	 * @param store
	 */
	public void setStore(Store store) {
		this.store = store;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @param name
	 */
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
	 * Returns the day part data for the given day part.
	 * @param dayPart the day part
	 * @return The day part data or null
	 */
	public DayPartData getDayPartDataFor(DayPart dayPart) {
		if(dayPart == null) {
			return null;
		}
		for(DayPartData dayPartData : getDayPartData()) {
			if(dayPart.equals(dayPartData.getDayPart())) {
				return dayPartData;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param dayOfWeek
	 * @return
	 */
	public DayOfWeekData getDayOfWeekDataFor(DayOfWeek dayOfWeek) {
		if(dayOfWeek != null && dayOfWeek.ordinal() >= 0 && dayOfWeek.ordinal() < getDayOfWeekData().size()) {
			return getDayOfWeekData().get(dayOfWeek.ordinal());
		} else {
			return null;
		}
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
	
	/**
	 * Returns the list of day of week related values, starting
	 * in the first day of the week configured for the containing
	 * store.
	 * @return the list of day week data sorted by the first day of the week
	 */
	public List<DayOfWeekData> getDayOfWeekDataStartingInFirstDayOfWeek() {
		if(getStore() != null && getStore().getFirstDayOfWeek() != null) {
			List<DayOfWeekData> l = getDayOfWeekData();
			List<DayOfWeekData> sortedDayOfWeekData = new ArrayList<DayOfWeekData>(l.size());
			int firstDayOfWeek = getStore().getFirstDayOfWeek().ordinal();
			
			for(int i = firstDayOfWeek; i < l.size(); i++) {
				sortedDayOfWeekData.add(l.get(i));
			}
			
			for(int i = 0; i < firstDayOfWeek; i++) {
				sortedDayOfWeekData.add(l.get(i));
			}
			
			return sortedDayOfWeekData;
		} else {
			// Missing store or first day of week
			return getDayOfWeekData();
		}
	}

	/**
	 * @return the utilizationBottom
	 */
	public Double getUtilizationBottom() {
		return utilizationBottom;
	}

	/**
	 * @param utilizationBottom the utilizationBottom to set
	 */
	public void setUtilizationBottom(Double utilizationBottom) {
		this.utilizationBottom = utilizationBottom;
	}

	/**
	 * @return the utilizationTop
	 */
	public Double getUtilizationTop() {
		return utilizationTop;
	}

	/**
	 * @param utilizationTop the utilizationTop to set
	 */
	public void setUtilizationTop(Double utilizationTop) {
		this.utilizationTop = utilizationTop;
	}

	/**
	 * @return the utilizationMinimum
	 */
	public Integer getUtilizationMinimum() {
		return utilizationMinimum;
	}

	/**
	 * @param utilizationMinimum the utilizationMinimum to set
	 */
	public void setUtilizationMinimum(Integer utilizationMinimum) {
		this.utilizationMinimum = utilizationMinimum;
	}

	/**
	 * @return the utilizationMaximum
	 */
	public Integer getUtilizationMaximum() {
		return utilizationMaximum;
	}

	/**
	 * @param utilizationMaximum the utilizationMaximum to set
	 */
	public void setUtilizationMaximum(Integer utilizationMaximum) {
		this.utilizationMaximum = utilizationMaximum;
	}

	/**
	 * @return the positionIndex
	 */
	public Integer getPositionIndex() {
		return positionIndex;
	}

	/**
	 * @param positionIndex the positionIndex to set
	 */
	public void setPositionIndex(Integer positionIndex) {
		this.positionIndex = positionIndex;
	}

	/**
	 * @return the positionGroup
	 */
	public PositionGroup getPositionGroup() {
		return positionGroup;
	}

	/**
	 * @param positionGroup the positionGroup to set
	 */
	public void setPositionGroup(PositionGroup positionGroup) {
		this.positionGroup = positionGroup;
	}

	/**
	 * @return the manager
	 */
	public boolean isManager() {
		return manager;
	}

	/**
	 * @param manager the manager to set
	 */
	public void setManager(boolean manager) {
		this.manager = manager;
	}

	/**
	 * @return the guestService
	 */
	public boolean isGuestService() {
		return guestService;
	}

	/**
	 * @param guestService the guestService to set
	 */
	public void setGuestService(boolean guestService) {
		this.guestService = guestService;
	}
}