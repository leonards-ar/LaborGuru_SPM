/*
 * File name: AddShiftBaseAction.java
 * Creation date: Sep 20, 2008 1:17:19 PM
 * Copyright Mindpool
 */
package com.laborguru.action.schedule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.laborguru.model.DailyStaffing;
import com.laborguru.model.Position;
import com.laborguru.model.Shift;
import com.laborguru.service.data.ReferenceDataService;
import com.laborguru.service.employee.EmployeeService;
import com.laborguru.service.position.PositionService;
import com.laborguru.service.projection.ProjectionService;
import com.laborguru.service.schedule.ScheduleService;
import com.laborguru.service.staffing.StaffingService;
import com.laborguru.util.CalendarUtils;
import com.laborguru.util.SpmConstants;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public abstract class AddShiftBaseAction extends ScheduleBaseAction {
	private static final Logger log = Logger.getLogger(AddShiftBaseAction.class);
	
	private Map<String, String> scheduleViewsMap = null;

	private String selectView;
	
	private ScheduleService scheduleService;
	private EmployeeService employeeService;
	private StaffingService staffingService;
	private ProjectionService projectionService;
	private ReferenceDataService referenceDataService;
	private PositionService positionService;
	
	private String saveSchedule;

	private Date copyTargetDay;
	
	private List<Position> positions;
	private Position position;
	
	/**
	 * 
	 */
	public AddShiftBaseAction() {
	}

	/**
	 * 
	 * @return
	 */
	public String getBreakId() {
		return SpmConstants.SCHEDULE_BREAK;
	}
	
	/**
	 * 
	 */
	protected void loadCopyTargetDay() {
		setCopyTargetDay(CalendarUtils.addOrSubstractDays(getWeekDaySelector().getSelectedDay(), 1));
	}
	
	/**
	 * 
	 * @return
	 */
	protected String getPositionName(Integer positionId) {
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
	 * @return the scheduleService
	 */
	public ScheduleService getScheduleService() {
		return scheduleService;
	}

	/**
	 * @param scheduleService the scheduleService to set
	 */
	public void setScheduleService(ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}

	/**
	 * @return the employeeService
	 */
	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	/**
	 * @param employeeService the employeeService to set
	 */
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	/**
	 * @return the staffingService
	 */
	public StaffingService getStaffingService() {
		return staffingService;
	}

	/**
	 * @param staffingService the staffingService to set
	 */
	public void setStaffingService(StaffingService staffingService) {
		this.staffingService = staffingService;
	}

	/**
	 * @return the projectionService
	 */
	public ProjectionService getProjectionService() {
		return projectionService;
	}

	/**
	 * @param projectionService the projectionService to set
	 */
	public void setProjectionService(ProjectionService projectionService) {
		this.projectionService = projectionService;
	}

	/**
	 * 
	 */
	protected abstract void loadScheduleViewsMap();

	/**
	 * @return the scheduleViewsMap
	 */
	public Map<String, String> getScheduleViewsMap() {
		if(scheduleViewsMap == null) {
			loadScheduleViewsMap();
		}
		return scheduleViewsMap;
	}

	/**
	 * @param scheduleViewsMap the scheduleViewsMap to set
	 */
	public void setScheduleViewsMap(Map<String, String> scheduleViewsMap) {
		this.scheduleViewsMap = scheduleViewsMap;
	}

	/**
	 * @return the referenceDataService
	 */
	public ReferenceDataService getReferenceDataService() {
		return referenceDataService;
	}

	/**
	 * @param referenceDataService the referenceDataService to set
	 */
	public void setReferenceDataService(ReferenceDataService referenceDataService) {
		this.referenceDataService = referenceDataService;
	}

	/**
	 * @return the selectView
	 */
	public String getSelectView() {
		return selectView;
	}

	/**
	 * @param selectView the selectView to set
	 */
	public void setSelectView(String selectView) {
		this.selectView = selectView;
	}
	
	/**
	 * 
	 */
	protected abstract void initializeSelectView();
	
	/**
	 * @return the saveSchedule
	 */
	public String getSaveSchedule() {
		return saveSchedule;
	}

	/**
	 * @param saveSchedule the saveSchedule to set
	 */
	public void setSaveSchedule(String saveSchedule) {
		this.saveSchedule = saveSchedule;
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
	 * @return the copyTargetDay
	 */
	public Date getCopyTargetDay() {
		return copyTargetDay;
	}

	/**
	 * @param copyTargetDay the copyTargetDay to set
	 */
	public void setCopyTargetDay(Date copyTargetDay) {
		this.copyTargetDay = copyTargetDay;
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
	 * @param positions the positions to set
	 */
	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}
	
	/**
	 * 
	 */
	protected void loadPositions() {
		this.setPositions(getPositionService().getPositionsByStore(getEmployeeStore()));
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
	 * Compares two positions by their ID
	 * @param pos1
	 * @param pos2
	 */
	protected boolean isEqualPosition(Position pos1, Position pos2) {
		return pos1 != null && pos2 != null && isEqualId(pos1.getId(), pos2.getId());
	}

	/**
	 * 
	 * @param id1
	 * @param id2
	 * @return
	 */
	protected boolean isEqualId(Integer id1, Integer id2) {
		return id1 != null && id1.equals(id2);
	}
	

	
	/**
	 * 
	 * @param source
	 * @param destination
	 */
	protected void updateShift(Shift source, Shift destination) {
		destination.setFromHour(source.getFromHour());
		destination.setToHour(source.getToHour());
		destination.setPosition(source.getPosition());
	}	

	/**
	 * 
	 * @param dailyStaffing
	 * @return
	 */
	protected int getTotalDailyStaffingInMinutes(DailyStaffing dailyStaffing) {
		Integer mins = CalendarUtils.hoursToMinutes(dailyStaffing != null ? dailyStaffing.getTotalDailyTarget() : new Double(0.0));
		return mins != null ? mins.intValue() : 0;
	}
	
	/**
	 * 
	 * @return
	 */
	protected int getTotalTargetInMinutes(Collection<DailyStaffing> storeDailyStaffing) {
		int total = 0;
		
		for(DailyStaffing dailyStaffing : storeDailyStaffing) {
			total += getTotalDailyStaffingInMinutes(dailyStaffing);
		}
		
		return total;
	}

}
