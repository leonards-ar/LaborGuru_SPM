package com.laborguru.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.laborguru.model.comparator.ComparableObject;

/**
 * Area business object
 * @author <a href="fbarreraoro@gmail.com">Federico Barrera Oro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */

public class PositionGroup extends SpmObject implements ComparableObject {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private Store store;
	private Set<Position> positions;
	
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the positions
	 */
	public Set<Position> getPositions() {
		if(positions == null) {
			positions = new HashSet<Position>();
		}
		return positions;
	}

	/**
	 * @param positions the positions to set
	 */
	private void setPositions(Set<Position> positions) {
		this.positions = positions;
	}
	
	public void addPosition(Position position){
		
		if (position == null){
			throw new IllegalArgumentException("Null position passed in as parameter");
		}

		if(position.getPositionGroup() != null) {
			position.getPositionGroup().getPositions().remove(position);
		}
		
		position.setPositionGroup(this);
		getPositions().add(position);
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) 
			return false;
		
		if(!(obj instanceof PositionGroup)) 
			return false;
		
		if(this == obj)
			return true;
		
		PositionGroup other = (PositionGroup)obj;
		
		return new EqualsBuilder().append(this.name, other.name).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(this.name).toHashCode();
	}

	/**
	 * PositionGroup toString
	 * @return string version of the object 
	 * @see com.laborguru.model.SpmObject#toString()
	 */
	@Override
	public String toString()
	{
		return new ToStringBuilder(this, DEFAULT_TO_STRING_STYLE)
	   	.append("id" , id)
	   	.append("name",name)
	   	.toString();		
	}

}
