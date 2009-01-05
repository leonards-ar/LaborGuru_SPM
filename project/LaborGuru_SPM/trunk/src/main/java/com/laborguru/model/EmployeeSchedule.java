/*
 * File name: EmployeeSchedule.java
 * Creation date: Sep 20, 2008 6:13:10 PM
 * Copyright Mindpool
 */
package com.laborguru.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.laborguru.util.CalendarUtils;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class EmployeeSchedule extends SpmObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5102605001510861305L;
	
	private Integer id;
	private Employee employee;
	private List<Shift> shifts;
	private StoreSchedule storeSchedule;

	/**
	 * 
	 */
	public EmployeeSchedule() {
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
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 * @return the shifts
	 */
	public List<Shift> getShifts() {
		if(shifts == null) {
			shifts = new ArrayList<Shift>();
		}
		return shifts;
	}

	/**
	 * 
	 * @param position
	 * @return
	 */
	public boolean hasMultipleShifts(Position position) {
		int count = 0;
		if(position != null && position.getId() != null) {
			for(Shift shift : getShifts()) {
				if(shift.getPosition() != null && position.getId().equals(shift.getPosition().getId())) {
					count++;
					if(count > 1) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param position
	 * @return
	 */
	public Date getFromHour(Position position) {
		Date inTime = null;
		if(position != null && position.getId() != null) {
			for(Shift shift : getShifts()) {
				if(shift.getPosition() != null && position.getId().equals(shift.getPosition().getId())) {
					if(inTime == null || CalendarUtils.greaterTime(inTime, shift.getFromHour())) {
						inTime = shift.getFromHour();
					}					
				}
			}
		}
		return inTime;		
	}
	
	/**
	 * 
	 * @param position
	 * @return
	 */
	public Date getToHour(Position position) {
		Date outTime = null;
		if(position != null && position.getId() != null) {
			for(Shift shift : getShifts()) {
				if(shift.getPosition() != null && position.getId().equals(shift.getPosition().getId())) {
					if(outTime == null || CalendarUtils.smallerTime(outTime, shift.getToHour())) {
						outTime = shift.getToHour();
					}					
				}
			}
		}
		return outTime;				
	}
	
	/**
	 * 
	 * @param shift
	 */
	public void addShift(Shift shift) {
		if(shift != null) {
			shift.setEmployeeSchedule(this);
			shift.setShiftIndex(new Integer(getShifts().size()));
			getShifts().add(shift);
		}
	}
	
	/**
	 * @param shifts the shifts to set
	 */
	public void setShifts(List<Shift> shifts) {
		this.shifts = shifts;
	}

	/**
	 * @return the storeSchedule
	 */
	public StoreSchedule getStoreSchedule() {
		return storeSchedule;
	}

	/**
	 * @param storeSchedule the storeSchedule to set
	 */
	public void setStoreSchedule(StoreSchedule storeSchedule) {
		this.storeSchedule = storeSchedule;
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
		
		final EmployeeSchedule other = (EmployeeSchedule) obj;
		
		return new EqualsBuilder()
		.append(this.employee != null? this.employee.getId():null, other.employee != null? other.employee.getId():null)
		.append(this.storeSchedule != null? this.storeSchedule.getId():null, other.storeSchedule != null? other.storeSchedule.getId():null)
		.isEquals();
	}

	/**
	 * @return
	 * @see com.laborguru.model.SpmObject#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
		.append(this.employee != null? this.employee.getId():null)
		.append(this.storeSchedule != null? this.storeSchedule.getId():null)
		.toHashCode();
	}

	/**
	 * @return
	 * @see com.laborguru.model.SpmObject#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, DEFAULT_TO_STRING_STYLE)
	   	.append("employee" , employee)
	   	.append("storeSchedule", storeSchedule)
	   	.toString();
	}

	/**
	 * 
	 * @return
	 */
	public Set<Position> getSchedulePositions() {
		Set<Position> positions = new HashSet<Position>();
		
		for(Shift shift : getShifts()) {
			if(shift.getPosition() != null) {
				positions.add(shift.getPosition());
			}
		}
		
		return positions;		
	}
	
	/**
	 * 
	 * @param position
	 * @return
	 */
	public List<Shift> getShiftsFor(Position position) {
		List<Shift> shifts = new ArrayList<Shift>();
		if(position != null && position.getId() != null) {
			for(Shift shift : getShifts()) {
				if(shift.getPosition() != null && position.getId().equals(shift.getPosition().getId())) {
					shifts.add(shift);
				}
			}
		}
		
		return shifts;
	}
}
