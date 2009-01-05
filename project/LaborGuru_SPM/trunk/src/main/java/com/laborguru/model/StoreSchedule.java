/*
 * File name: StoreSchedule.java
 * Creation date: Sep 20, 2008 5:09:08 PM
 * Copyright Mindpool
 */
package com.laborguru.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class StoreSchedule extends SpmObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4145515420736901455L;
	
	private Integer id;
	private Store store;
	private Date day;
	private Set<EmployeeSchedule> employeeSchedules;

	/**
	 * 
	 */
	public StoreSchedule() {
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
	 * @return the day
	 */
	public Date getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(Date day) {
		this.day = day;
	}

	/**
	 * @return the employeeSchedules
	 */
	public Set<EmployeeSchedule> getEmployeeSchedules() {
		if(employeeSchedules == null) {
			setEmployeeSchedules(new HashSet<EmployeeSchedule>());
		}		
		return employeeSchedules;
	}

	/**
	 * @param employeeSchedules the employeeSchedules to set
	 */
	public void setEmployeeSchedules(Set<EmployeeSchedule> employeeSchedules) {
		this.employeeSchedules = employeeSchedules;
	}

	/**
	 * @param other
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
		
		final StoreSchedule other = (StoreSchedule) obj;
		
		return new EqualsBuilder().append(this.day, other.day)
		.append(this.store != null? this.store.getId():null, other.store != null? other.store.getId():null)
		.isEquals();
	}

	/**
	 * @return
	 * @see com.laborguru.model.SpmObject#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
		.append(this.day)
		.append(this.store != null? this.store.getId():null)
		.toHashCode();
	}

	/**
	 * @return
	 * @see com.laborguru.model.SpmObject#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, DEFAULT_TO_STRING_STYLE)
	   	.append("store" , store)
	   	.append("day", day)
	   	.toString();
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
	 * 
	 * @param employee
	 * @return
	 */
	public EmployeeSchedule getEmployeeSchedule(Employee employee) {
		for(EmployeeSchedule anEmployeeSchedule : getEmployeeSchedules()) {
			if(anEmployeeSchedule.getEmployee() != null && anEmployeeSchedule.getEmployee().getId() != null && employee != null && anEmployeeSchedule.getEmployee().getId().equals(employee.getId())) {
				return anEmployeeSchedule;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public Set<Position> getSchedulePositions() {
		Set<Position> positions = new HashSet<Position>();
		
		for(EmployeeSchedule employeeSchedule : getEmployeeSchedules()) {
			positions.addAll(employeeSchedule.getSchedulePositions());
		}
		
		return positions;
	}
}
