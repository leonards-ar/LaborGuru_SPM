package com.laborguru.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.laborguru.model.comparator.SpmComparator;

public class Store extends SpmObject {

	/**
	 * Default serial version ID
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;	
	private String name;
	private String code;
	private DayOfWeek firstDayOfWeek;
	private Area area;
	private List<Position> positions;
	private List<OperationTime> operationTimes;
	private List<DayPart> dayParts;
	
	/**
	 * Store toString
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
	 */
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Returns the day this store uses as the first day of
	 * the week. The possible values are:
	 * <ul>
	 * 	<li>0: Sunday</li>
	 * 	<li>1: Monday</li>
	 * 	<li>2: Tuesday</li>
	 * 	<li>3: Wednesday</li>
	 * 	<li>4: Thursday</li>
	 * 	<li>5: Friday</li>
	 * 	<li>6: Saturday</li>
	 * </ul>
	 * @return the firstDayOfWeek
	 */
	public Integer getFirstDayOfWeekAsInteger() {
		return getFirstDayOfWeek() != null ? new Integer(getFirstDayOfWeek().ordinal()) : null;
	}

	/**
	 * @param firstDayOfWeek the firstDayOfWeek to set
	 */
	public void setFirstDayOfWeekAsInteger(Integer firstDayOfWeek) {
		if(firstDayOfWeek != null) {
			setFirstDayOfWeek(DayOfWeek.values()[firstDayOfWeek.intValue()]);
		} else {
			setFirstDayOfWeek(null);
		}
	}

	/**
	 * @return the area
	 */
	public Area getArea() {
		return area;
	}

	/**
	 * @param area the area to set
	 */
	public void setArea(Area area) {
		this.area = area;
	}

	/**
	 * @return the positions
	 */
	public Set<Position> getPositions() {
		return new HashSet<Position>(positions);
	}

	/**
	 * 
	 * @return
	 */
	public List<Position> getOrderedPositions() {
		if(positions == null) {
			setPositions(new HashSet<Position>());
		}
		return positions;
	}
	/**
	 * it is private to enforce the cardinality with the addPositions.
	 * DO NOT MAKE IT PUBLIC
	 * @param childMenuItems
	 */
	private void setPositions(Set<Position> positions) {
		this.positions = new ArrayList<Position>(positions);
		Collections.sort(this.positions, new SpmComparator()); 
	}
	

	/**
	 * Adds a position to the store. Handles the bi-directional
	 * relation.
	 * @param position The position to add
	 */
	public void addPositions(Position position){
		
		if (position == null){
			throw new IllegalArgumentException("Null position passed in as parameter");
		}
		
		if (position.getStore() != null){
			position.getStore().getPositions().remove(position);
		}
		
		position.setStore(this);
		this.positions.add(position);
	}	
	
	
	/**
	 * @return
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
		.append(this.name)
		.append(this.area != null? this.area.getId():null)
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
		
		final Store other = (Store) obj;
		
		return new EqualsBuilder()
		.append(this.area != null? this.area.getId():null, other.area != null? other.area.getId():null)
		.append(this.name, other.name)
		.isEquals();
	}

	/**
	 * @return the operationTime
	 */
	public List<OperationTime> getOperationTimes() {
		if(operationTimes == null) {
			setOperationTimes(new ArrayList<OperationTime>());
		}		
		return operationTimes;
	}

	/**
	 * @param operationTime the operationTime to set
	 */
	private void setOperationTimes(List<OperationTime> operationTimes) {
		this.operationTimes = operationTimes;
	}

	/**
	 * Adds an operation time (open and close hours) to the store.
	 * Handles the bi-directional relation.
	 * @param operationTime The operationTime to add
	 */
	public void addOperationTimes(OperationTime operationTime){
		
		if (operationTime == null){
			throw new IllegalArgumentException("Null operationTime passed in as parameter");
		}
		
		if (operationTime.getStore() != null){
			operationTime.getStore().getOperationTimes().remove(operationTime);
		}
		
		operationTime.setStore(this);

		getOperationTimes().add(operationTime);
	}

	/**
	 * @return the firstDayOfWeek
	 */
	public DayOfWeek getFirstDayOfWeek() {
		return firstDayOfWeek;
	}

	/**
	 * @param firstDayOfWeek the firstDayOfWeek to set
	 */
	public void setFirstDayOfWeek(DayOfWeek firstDayOfWeek) {
		this.firstDayOfWeek = firstDayOfWeek;
	}

	/**
	 * @return the dayParts
	 */
	public List<DayPart> getDayParts() {
		if(dayParts == null) {
			setDayParts(new ArrayList<DayPart>());
		}
		return dayParts;
	}

	/**
	 * @param dayParts the dayParts to set
	 */
	private void setDayParts(List<DayPart> dayParts) {
		this.dayParts = dayParts;
	}
	
	/**
	 * Adds a day part to the store. Handles the bi-directional
	 * relation.
	 * @param dayPart The day part to add
	 */
	public void addDayParts(DayPart dayPart){
		
		if (dayPart == null){
			throw new IllegalArgumentException("Null day part passed in as parameter");
		}
		
		if (dayPart.getStore() != null){
			dayPart.getStore().getDayParts().remove(dayPart);
		}
		
		dayPart.setStore(this);
		getDayParts().add(dayPart);
	}	
}
