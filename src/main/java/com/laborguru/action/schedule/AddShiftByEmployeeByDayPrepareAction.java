/*
 * File name: AddShiftByEmployeeByDayPrepareAction.java
 * Creation date: Sep 20, 2008 1:26:42 PM
 * Copyright Mindpool
 */
package com.laborguru.action.schedule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.laborguru.action.SpmActionResult;
import com.laborguru.frontend.model.ScheduleRow;
import com.laborguru.model.Position;
import com.laborguru.service.position.PositionService;
import com.laborguru.util.CalendarUtils;
import com.opensymphony.xwork2.Preparable;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class AddShiftByEmployeeByDayPrepareAction extends AddShiftBaseAction  implements Preparable {
	private static final Logger log = Logger.getLogger(AddShiftByEmployeeByDayPrepareAction.class);
	
	private List<ScheduleRow> scheduleData;
	private List<Integer> minimumStaffing;
	
	private Position position;
	
	private List<Position> positions;
	private PositionService positionService;
	
	private Integer newEmployeeId;
	private String newEmployeeName;
	private Integer newEmployeePositionId;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8960503469589990853L;

	/**
	 * 
	 */
	public AddShiftByEmployeeByDayPrepareAction() {
	}

	/**
	 * 
	 * @return
	 */
	public Integer getScheduleRows() {
		return new Integer(getScheduleData().size());
	}
	
	/**
	 * Loads position and status list
	 */
	private void loadPageData() {
		this.setPositions(getPositionService().getPositionsByStore(getEmployeeStore()));
		loadCalendarData();
	}
	
	/**
	 * 
	 */
	public Integer getTotalMinimutStaffingMinutes() {
		int total = 0;
		
		for(Integer i : getMinimumStaffing()) {
			total += i != null ? i.intValue() : 0;
		}
		
		return new Integer(MINUTES_INTERVAL * total);
	}
	
	/**
	 * 
	 */
	public Date getTotalMinimutStaffingTime() {
		Integer min = getTotalMinimutStaffingMinutes();
		if(min != null && min.intValue() > 0) {
			return new Date(60000L * min.intValue());
		} else {
			return CalendarUtils.removeTimeFromDate(new Date());
		}
	}
	
	/**
	 * Prepare data to be used in the actions methods defined for this action
	 * 
	 * @throws Exception
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	public void prepare() throws Exception {
		// It's needed by the Preparable interface, don't comment out or removed
	}
	
	/**
	 * 
	 * @see com.laborguru.action.schedule.AddShiftBaseAction#prepareChangeDay()
	 */
	@Override
	public void prepareChangeDay() {
		loadPageData();
	}

	/**
	 * 
	 */
	public void prepareSelectPosition() {
		loadPageData();
	}
	
	/**
	 * 
	 * @see com.laborguru.action.schedule.AddShiftBaseAction#prepareChangeWeek()
	 */
	@Override
	public void prepareChangeWeek() {
		loadPageData();
	}

	/**
	 * 
	 * @see com.laborguru.action.schedule.AddShiftBaseAction#processChangeDay()
	 */
	@Override
	protected void processChangeDay() {
		setStoreSchedule(null);
		resetScheduleData();
		setScheduleData();
	}

	/**
	 * 
	 * @see com.laborguru.action.schedule.AddShiftBaseAction#processChangeWeek()
	 */
	@Override
	protected void processChangeWeek() {
		setStoreSchedule(null);
		resetScheduleData();
		setScheduleData();
	}

	/**
	 * Prepare the data to be used on the edit page
	 */
	public void prepareEdit() {
		loadPageData();
	}
	
	/**
	 * Prepare the data to be used on the edit page
	 */
	public void prepareSave() {
		loadPageData();
	}
	
	/**
	 * Prepare the data to be used on the edit page
	 */
	public void prepareCancel() {
		loadPageData();
	}
	
	/**
	 * 
	 */
	private void setScheduleData() {
		if(scheduleData == null || scheduleData.isEmpty()) {
			setScheduleData(buildScheduleFor(getPosition()));
		}
		
		if(minimumStaffing == null || minimumStaffing.isEmpty()) {
			setMinimumStaffing(buildMinimumStaffingFor(getPosition()));
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public String edit() {
		initializeDayWeekSelector(getSelectedDate(), getSelectedWeekDay());

		setScheduleData();
		
		return SpmActionResult.EDIT.getResult();
	}

	/**
	 * 
	 * @return
	 */
	public String selectPosition() {
		initializeDayWeekSelector(getSelectedDate(), getSelectedWeekDay());
		
		resetScheduleData();
		setScheduleData();
		
		return SpmActionResult.EDIT.getResult();
	}
	
	/**
	 * 
	 */
	public void prepareAddEmployee() {
		loadPageData();
	}
	
	/**
	 * 
	 * @return
	 */
	private String getPositionName(Integer positionId) {
		if(positionId != null) {
			Position position = new Position();
			position.setId(positionId);
			position = getPositionService().getPositionById(position);
			return position != null ? position.getName() : null;
		} else {
			return null;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public String addEmployee() {
		initializeDayWeekSelector(getSelectedDate(), getSelectedWeekDay());
		
		ScheduleRow newRow = new ScheduleRow();
		newRow.setEmployeeId(getNewEmployeeId());
		newRow.setOriginalEmployeeId(getNewEmployeeId());
		newRow.setPositionId(getNewEmployeePositionId());
		newRow.setPositionName(getPositionName(getNewEmployeePositionId()));
		newRow.setEmployeeName(getNewEmployeeName());
		newRow.setSchedule(initializeScheduleRow());
		newRow.setHours(initializeScheduleHoursRow());
		
		getScheduleData().add(newRow);
		
		setNewEmployeeId(null);
		setNewEmployeeName(null);
		setNewEmployeePositionId(null);
		
		return SpmActionResult.EDIT.getResult();
	}
	
	/**
	 * 
	 * @return
	 */
	public String save() {
		initializeDayWeekSelector(getSelectedDate(), getSelectedWeekDay());
		
		if(log.isDebugEnabled()) {
			log.debug("About to save schedule data" + getScheduleData());
		}
		
		setSchedule(getScheduleData(), getPosition());

		if(log.isDebugEnabled()) {
			log.debug("About to save schedule " + getStoreSchedule());
		}
	
		getScheduleService().save(getStoreSchedule());

		resetScheduleData();
		setScheduleData();
		
		return SpmActionResult.EDIT.getResult();
	}
	
	/**
	 * 
	 */
	private void resetScheduleData() {
		setScheduleData(null);
	}
	
	/**
	 * 
	 * @return
	 */
	public String cancel() {
		initializeDayWeekSelector(getSelectedDate(), getSelectedWeekDay());
		resetScheduleData();
		setScheduleData();
		
		return SpmActionResult.EDIT.getResult();
	}
	
	/**
	 * @return the scheduleData
	 */
	public List<ScheduleRow> getScheduleData() {
		if(scheduleData == null) {
			setScheduleData(new ArrayList<ScheduleRow>());
		}
		return scheduleData;
	}

	/**
	 * @param scheduleData the scheduleData to set
	 */
	public void setScheduleData(List<ScheduleRow> scheduleData) {
		this.scheduleData = scheduleData;
	}

	/**
	 * @return the position
	 */
	public Position getPosition() {
		if(position != null && position.getId() == null) {
			return null;
		} else if(position != null && position.getId() != null && position.getName() == null) {
			position.setName(getPositionName(position.getId()));
		}
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * @return the positions
	 */
	public List<Position> getPositions() {
		return positions;
	}

	/**
	 * @param positions the positions to set
	 */
	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	/**
	 * @return the positionService
	 */
	public PositionService getPositionService() {
		return positionService;
	}

	/**
	 * @param positionService the positionService to set
	 */
	public void setPositionService(PositionService positionService) {
		this.positionService = positionService;
	}

	/**
	 * @return the newEmployeeId
	 */
	public Integer getNewEmployeeId() {
		return newEmployeeId;
	}

	/**
	 * @param newEmployeeId the newEmployeeId to set
	 */
	public void setNewEmployeeId(Integer newEmployeeId) {
		this.newEmployeeId = newEmployeeId;
	}

	/**
	 * @return the newEmployeePositionId
	 */
	public Integer getNewEmployeePositionId() {
		return newEmployeePositionId;
	}

	/**
	 * @param newEmployeePositionId the newEmployeePositionId to set
	 */
	public void setNewEmployeePositionId(Integer newEmployeePositionId) {
		this.newEmployeePositionId = newEmployeePositionId;
	}

	/**
	 * @return the newEmployeeName
	 */
	public String getNewEmployeeName() {
		return newEmployeeName;
	}

	/**
	 * @param newEmployeeName the newEmployeeName to set
	 */
	public void setNewEmployeeName(String newEmployeeName) {
		this.newEmployeeName = newEmployeeName;
	}

	/**
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	@Override
	public void validate() {
		validateSchedule(getScheduleData());
	}

	/**
	 * @return the minimumStaffing
	 */
	public List<Integer> getMinimumStaffing() {
		if(minimumStaffing == null) {
			setMinimumStaffing(new ArrayList<Integer>());
		}		
		return minimumStaffing;
	}

	/**
	 * @param minimumStaffing the minimumStaffing to set
	 */
	public void setMinimumStaffing(List<Integer> minimumStaffing) {
		this.minimumStaffing = minimumStaffing;
	}	
	
	
}
