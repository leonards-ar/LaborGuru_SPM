package com.laborguru.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.laborguru.model.comparator.SpmComparator;
import com.laborguru.util.CalendarUtils;

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
	private Integer dailyProjectionsWeeksDefault;
	private Integer halfHourProjectionsWeeksDefault;

	private Date creationDate;
	private Date lastUpdateDate;
	
	private List<Position> positions;
	private List<OperationTime> operationTimes;
	private List<DayPart> dayParts;
	private Double allPositionsUtilization;
	private Set<PositionGroup> positionGroups;
	
	private Double scheduleInefficiency = null;
	private Double fillInefficiency = null;
	private Double trainingFactor = null;
	private Double earnedBreakFactor = null;
	private Double floorManagementFactor = null;
	private Integer minimumFloorManagementHours = null;
	private Integer extraScheduleHours = null;
	
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
	   	.append("code",code)
	   	.toString();		
	}
	
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public DayPart getDayPartByName(String name) {
		if(name == null) {
			throw new IllegalArgumentException("name cannot be null");
		}
		for(DayPart dp : getDayParts()) {
			if(name.equals(dp.getName())) {
				return dp;
			}
		}
		return null;
	}

	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public Position getPositionByName(String name) {
		if(name == null) {
			throw new IllegalArgumentException("name cannot be null");
		}
		for(Position position : getPositions()) {
			if(name.equals(position.getName())) {
				return position;
			}
		}
		return null;
	}
	
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public PositionGroup getPositionGroupByName(String name) {
		if(name == null) {
			throw new IllegalArgumentException("name cannot be null");
		}
		for(PositionGroup pg : getPositionGroups()) {
			if(name.equals(pg.getName())) {
				return pg;
			}
		}
		return null;
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
	public List<Position> getPositions() {
		if(positions == null) {
			setPositions(new ArrayList<Position>());
		}
		return positions;
	}


	/**
	 * it is private to enforce the cardinality with the addPositions.
	 * DO NOT MAKE IT PUBLIC
	 * @param childMenuItems
	 */
	private void setPositions(List<Position> positions) {
		this.positions = positions;
	}
	

	/**
	 * Adds a position to the store. Handles the bi-directional
	 * relation.
	 * @param position The position to add
	 */
	public void addPosition(Position position){
		
		if (position == null){
			throw new IllegalArgumentException("Null position passed in as parameter");
		}
		
		if (position.getStore() != null){
			position.getStore().getPositions().remove(position);
		}
		
		position.setStore(this);
		getPositions().add(position);
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
	 * Returns the operation time by date
	 * @return the operationTime
	 */
	public OperationTime getStoreOperationTimeByDate(Date date) {
	
		Calendar dateCalendar = Calendar.getInstance();
		dateCalendar.setTime(date);
		
		for(OperationTime aOperationTime: getOperationTimes()) {
			if(dateCalendar.get(Calendar.DAY_OF_WEEK) == aOperationTime.getDayOfWeek().getDayOfWeek()) {
				return aOperationTime;
			}
		}
		
		return null;		
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
	 * 
	 * @param dayOfWeek
	 * @return
	 */
	public OperationTime getOperationTime(DayOfWeek dayOfWeek) {
		if(dayOfWeek == null) {
			throw new IllegalArgumentException("DayOfWeek cannot be null");
		}
		for(OperationTime ot : getOperationTimes()) {
			if(dayOfWeek.equals(ot.getDayOfWeek())) {
				return ot;
			}
		}
		return null;
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
	public void addOperationTime(OperationTime operationTime){
		
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
		if(firstDayOfWeek == null) {
			firstDayOfWeek = DayOfWeek.SUNDAY;
		}
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
	public void addDayPart(DayPart dayPart){
		
		if (dayPart == null){
			throw new IllegalArgumentException("Null day part passed in as parameter");
		}
		
		if (dayPart.getStore() != null){
			dayPart.getStore().getDayParts().remove(dayPart);
		}
		
		dayPart.setStore(this);
		getDayParts().add(dayPart);
	}
	
	/**
	 * Removes a day part from the store. Handles the bi-directional
	 * relation.
	 * @param dayPart The day part to add
	 */
	public void removeDayPart(DayPart dayPart){
		
		if (dayPart == null){
			throw new IllegalArgumentException("Null day part passed in as parameter");
		}
				
		getDayParts().remove(dayPart);
		
		if (dayPart.getStore() != null){
			dayPart.setStore(null);
		}
	}

	/**
	 * @return the allPositionsUtilization
	 */
	public Double getAllPositionsUtilization() {
		return allPositionsUtilization;
	}

	/**
	 * @param allPositionsUtilization the allPositionsUtilization to set
	 */
	public void setAllPositionsUtilization(Double allPositionsUtilization) {
		this.allPositionsUtilization = allPositionsUtilization;
	}

	/**
	 * @return the postionGroups
	 */
	public Set<PositionGroup> getPositionGroups() {
		if(positionGroups == null) {
			setPositionGroups(new HashSet<PositionGroup>());
		}
		return positionGroups;
	}

	/** 
	 * Get Ordered PositionGroups
	 * @return
	 */
	public List<PositionGroup> getOrderedPositionGroups() {
		List<PositionGroup> orderedPositionGroups = new ArrayList<PositionGroup>(getPositionGroups());
		Collections.sort(orderedPositionGroups, new SpmComparator());
		return orderedPositionGroups;
	}
	
	/**
	 * @param postionGroups the postionGroups to set
	 */
	private void setPositionGroups(Set<PositionGroup> positionGroups) {
		this.positionGroups = positionGroups;
	}
	
	/**
	 * Add a positionGroup
	 * @param positionGroup
	 */
	public void addPositionGroup(PositionGroup positionGroup) {
		if (positionGroup == null){
			throw new IllegalArgumentException("Null position group passed in as parameter");
		}
		
		if (positionGroup.getStore() != null){
			positionGroup.getStore().getPositionGroups().remove(positionGroup);
		}
		
		positionGroup.setStore(this);
		getPositionGroups().add(positionGroup);
	}

	/**
	 * @return the dailyProjectionsWeeksDefault
	 */
	public Integer getDailyProjectionsWeeksDefault() {
		return dailyProjectionsWeeksDefault;
	}

	/**
	 * @param dailyProjectionsWeeksDefault the dailyProjectionsWeeksDefault to set
	 */
	public void setDailyProjectionsWeeksDefault(Integer dailyProjectionsWeeksDefault) {
		this.dailyProjectionsWeeksDefault = dailyProjectionsWeeksDefault;
	}

	/**
	 * @return the halfHourProjectionsWeeksDefault
	 */
	public Integer getHalfHourProjectionsWeeksDefault() {
		return this.halfHourProjectionsWeeksDefault;
	}

	/**
	 * @param halfHourProjectionsWeeksDefault the halfHourProjectionsWeeksDefault to set
	 */
	public void setHalfHourProjectionsWeeksDefault(
			Integer halfHourProjectionsWeeksDefault) {
		this.halfHourProjectionsWeeksDefault = halfHourProjectionsWeeksDefault;
	}

	/**
	 * @return the scheduleInefficiency
	 */
	public Double getScheduleInefficiency() {
		return scheduleInefficiency;
	}

	/**
	 * @param scheduleInefficiency the scheduleInefficiency to set
	 */
	public void setScheduleInefficiency(Double scheduleInefficiency) {
		this.scheduleInefficiency = scheduleInefficiency;
	}

	/**
	 * @return the fillInefficiency
	 */
	public Double getFillInefficiency() {
		return fillInefficiency;
	}

	/**
	 * @param fillInefficiency the fillInefficiency to set
	 */
	public void setFillInefficiency(Double fillInefficiency) {
		this.fillInefficiency = fillInefficiency;
	}

	/**
	 * @return the trainingFactor
	 */
	public Double getTrainingFactor() {
		return trainingFactor;
	}

	/**
	 * @param trainingFactor the trainingFactor to set
	 */
	public void setTrainingFactor(Double trainingFactor) {
		this.trainingFactor = trainingFactor;
	}

	/**
	 * @return the earnedBreakFactor
	 */
	public Double getEarnedBreakFactor() {
		return earnedBreakFactor;
	}

	/**
	 * @param earnedBreakFactor the earnedBreakFactor to set
	 */
	public void setEarnedBreakFactor(Double earnedBreakFactor) {
		this.earnedBreakFactor = earnedBreakFactor;
	}

	/**
	 * @return the floorManagementFactor
	 */
	public Double getFloorManagementFactor() {
		return floorManagementFactor;
	}

	/**
	 * @param floorManagementFactor the floorManagementFactor to set
	 */
	public void setFloorManagementFactor(Double floorManagementFactor) {
		this.floorManagementFactor = floorManagementFactor;
	}

	/**
	 * @return the minimumFloorManagementHours
	 */
	public Integer getMinimumFloorManagementHours() {
		return minimumFloorManagementHours;
	}

	/**
	 * @param minimumFloorManagementHours the minimumFloorManagementHours to set
	 */
	public void setMinimumFloorManagementHours(Integer minimumFloorManagementHours) {
		this.minimumFloorManagementHours = minimumFloorManagementHours;
	}
	
	/**
	 * 
	 * @param time
	 * @return
	 */
	public DayPart getDayPartFor(Date time) {
		if(time != null) {
			DayPart dayPart, nextDayPart;
			if(getDayParts().size() > 1) {
				for(int i = 0; i < getDayParts().size(); i++) {
					dayPart = getDayParts().get(i);
					nextDayPart = i + 1 < getDayParts().size() ? getDayParts().get(i + 1) : getDayParts().get(0);
					if(dayPart != null && nextDayPart != null) {
						if(CalendarUtils.inRangeNotIncludingEndTime(time, dayPart.getStartHour(), nextDayPart.getStartHour())) {
							return dayPart;
						}
					}
				}
			} else {
				// For sure time is before the first day part
				dayPart = getDayParts().size() > 0 ? getDayParts().get(0) : null;
				return dayPart != null && dayPart.getStartHour() != null ? dayPart : null;
			}
		}
		return null;
	}


	/**
	 * @return the extraScheduleHours
	 */
	public Integer getExtraScheduleHours() {
		return extraScheduleHours;
	}


	/**
	 * @param extraScheduleHours the extraScheduleHours to set
	 */
	public void setExtraScheduleHours(Integer extraScheduleHours) {
		this.extraScheduleHours = extraScheduleHours;
	}
	
	/**
	 * 
	 * @param dayOfWeek
	 * @return
	 */
	public Date getStoreScheduleStartHour(DayOfWeek dayOfWeek) {
		OperationTime operationTime = getOperationTime(dayOfWeek);
		Date startTime = CalendarUtils.addOrSubstractHours(operationTime.getOpenHour(), (-1) * getCalculatedExtraHours(operationTime.getOpenHour(), operationTime.getCloseHour(), false));
		Date previuosCloseTime = getStoreScheduleEndHour(dayOfWeek.getPreviousDayOfWeek());
		OperationTime previousOperationTime = getOperationTime(dayOfWeek.getPreviousDayOfWeek());
		
		if(CalendarUtils.greaterTime(startTime, operationTime.getOpenHour())) {
			// Case 1: Real start time is yesterday.
			if(CalendarUtils.smallerTime(previuosCloseTime, previousOperationTime.getCloseHour())) {
				// Case 1.1: Yesterday's real close hour is today
				return CalendarUtils.greaterTime(startTime, previuosCloseTime) ? previuosCloseTime : startTime;
			} else {
				// Case 1.2: Real previous close hour and today's open hour are yesterday
				return CalendarUtils.smallerTime(startTime, previuosCloseTime) ? previuosCloseTime : startTime;
			}
		} else {
			// Case 2: Real start time is still today. 
			if(CalendarUtils.greaterTime(previuosCloseTime, previousOperationTime.getCloseHour())) {
				// Case 2.1: Real previous close hour is yerterday but today's open hour is today
				return startTime;
			} else {
				// Case 2.2: Yesterday's real close hour is today and real open hour is today
				return CalendarUtils.smallerTime(startTime, previuosCloseTime) ? previuosCloseTime : startTime;
			}
		}
	}

	/**
	 * 
	 * @param open
	 * @param close
	 * @return
	 */
	private int getCalculatedExtraHours(Date open, Date close, boolean isCeil) {
		if(CalendarUtils.equalsTime(open, close)) {
			// 24hs store
			return 0;
		} else {
			double diff = CalendarUtils.differenceInHours(close, open).doubleValue();
			int extraHours = getExtraHoursAsInt();
			if(diff + extraHours >= 24) {
				double hs = (diff + extraHours - 24) / 2;
				return (int) (isCeil ? Math.ceil(hs) : Math.floor(hs));
			} else {
				// Normal case
				return extraHours;
			}
		}
	}
	
	/**
	 * 
	 * @return
	 */
	private int getExtraHoursAsInt() {
		return getExtraScheduleHours() != null ? getExtraScheduleHours().intValue() : 0;
	}
	
	/**
	 * 
	 * @param dayOfWeek
	 * @return
	 */
	public Date getStoreScheduleEndHour(DayOfWeek dayOfWeek) {
		OperationTime operationTime = getOperationTime(dayOfWeek);
		return CalendarUtils.addOrSubstractHours(operationTime.getCloseHour(), getCalculatedExtraHours(operationTime.getOpenHour(), operationTime.getCloseHour(), false));
	}
	
	/**
	 * 
	 * @param dayOfWeek
	 * @return
	 */
	public boolean isMultiDaySchedule(DayOfWeek dayOfWeek) {
		OperationTime operationTime = getOperationTime(dayOfWeek);
		return CalendarUtils.equalsOrGreaterTime(operationTime.getOpenHour(), operationTime.getCloseHour());
	}	

	/**
	 * 
	 * @param dayOfWeek
	 * @return
	 */
	public boolean isMultiDayScheduleWithExtraHours(DayOfWeek dayOfWeek) {
		return CalendarUtils.equalsOrGreaterTime(getStoreScheduleStartHour(dayOfWeek), getStoreScheduleEndHour(dayOfWeek));
	}


	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}


	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}


	/**
	 * @return the lastUpdateDate
	 */
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}


	/**
	 * @param lastUpdateDate the lastUpdateDate to set
	 */
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}	
}
