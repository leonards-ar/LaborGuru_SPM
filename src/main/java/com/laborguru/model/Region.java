package com.laborguru.model;

import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Region business object
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class Region extends SpmObject {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private Customer customer;
	private Set<Area> areas;
	
	/**
	 * Region toString
	 * @return string version of the object 
	 * @see com.laborguru.model.SpmObject#toString()
	 */
	public String toString()
	{
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
	   	.append("id" , id)
	   	.append("name",name)
	   	.toString();		
	}	
				
	/**
	 * @return
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(this.name)
		.append(this.customer)
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

		final Region other = (Region) obj;
		
		return new EqualsBuilder().append(this.name, other.name)
		.append(this.customer, other.customer)
		.isEquals();		
	}
	
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
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}
	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the areas
	 */
	public Set<Area> getAreas() {
		return areas;
	}

	/**
	 * @param areas the areas to set
	 */
	private void setAreas(Set<Area> areas) {
		this.areas = areas;
	}	
	
	/**
	 * Adds an area to this region. Handles the bi-directional
	 * relation.
	 * @param area The area to add
	 */
	public void addArea(Area area){
		
		if (area == null){
			throw new IllegalArgumentException("Null area passed in as parameter");
		}
		
		if (area.getRegion() != null){
			area.getRegion().getAreas().remove(area);
		}
		
		area.setRegion(this);
		this.areas.add(area);
	}		
}
