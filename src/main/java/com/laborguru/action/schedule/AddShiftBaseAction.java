/*
 * File name: AddShiftBaseAction.java
 * Creation date: Sep 20, 2008 1:17:19 PM
 * Copyright Mindpool
 */
package com.laborguru.action.schedule;

import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;

import com.laborguru.action.SpmAction;
import com.laborguru.action.SpmActionResult;
import com.laborguru.frontend.model.WeekDaySelector;
import com.laborguru.model.Position;
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
public abstract class AddShiftBaseAction extends SpmAction {
	private static final Logger log = Logger.getLogger(AddShiftBaseAction.class);
	
	private Map<String, String> scheduleViewsMap = null;

	private WeekDaySelector weekDaySelector;
	private String selectedDate;
	private String selectedWeekDay;

	private String selectView;
	
	private ScheduleService scheduleService;
	private EmployeeService employeeService;
	private StaffingService staffingService;
	private ProjectionService projectionService;
	private ReferenceDataService referenceDataService;
	private PositionService positionService;
	
	private String saveSchedule;

	private Date copyTargetDay;
	
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
	 * @return the weekDaySelector
	 */
	public WeekDaySelector getWeekDaySelector() {
		if(weekDaySelector == null) {
			weekDaySelector = new WeekDaySelector(getEmployeeStore().getFirstDayOfWeek());
		}
		return weekDaySelector;
	}
	
	/**
	 * @param weekDaySelector the weekDaySelector to set
	 */
	public void setWeekDaySelector(WeekDaySelector weekDaySelector) {
		this.weekDaySelector = weekDaySelector;
	}
	
	/**
	 * Actions that must be performed before
	 * this action is executed
	 */
	public abstract void prepareChangeWeek();
	
	/**
	 * Actions that must be performed before
	 * this action is executed
	 */
	public abstract void prepareChangeDay();
	
	/**
	 * Performs any needed calculations after
	 * a change week action is issued.
	 */
	protected abstract void processChangeWeek();

	/**
	 * Performs any needed calculations after
	 * a change day action is issued.
	 */
	protected abstract void processChangeDay();
	
	/**
	 * @return the selectedDate
	 */
	public String getSelectedDate() {
		return selectedDate;
	}

	/**
	 * @param selectedDate the selectedDate to set
	 */
	public void setSelectedDate(String selectedDate) {
		this.selectedDate = selectedDate;
	}

	/**
	 * @return the selectedWeekDay
	 */
	public String getSelectedWeekDay() {
		return selectedWeekDay;
	}

	/**
	 * @param selectedWeekDay the selectedWeekDay to set
	 */
	public void setSelectedWeekDay(String selectedWeekDay) {
		this.selectedWeekDay = selectedWeekDay;
	}

	/**
	 * 
	 * @param weekSelectedDay
	 * @param selectedDay
	 */
	protected void initializeDayWeekSelector(String weekSelectedDay, String selectedDay) {
		if(log.isDebugEnabled()) {
			log.debug("Initializing day week selector with weekSelectedDay: " + weekSelectedDay + " and selectedDay: " + selectedDay);
		}
		getWeekDaySelector().initializeChangeDay(weekSelectedDay, selectedDay);
	}

	/**
	 * 
	 */
	protected void loadCalendarData() {
		setSelectedDate(getWeekDaySelector().getStringStartingWeekDay());
		setSelectedWeekDay(getWeekDaySelector().getStringSelectedDay());
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
	 * 
	 * @return
	 */
	public String changeWeek() {
		getWeekDaySelector().initializeChangeWeek(getSelectedDate(), getSelectedWeekDay());
		
		processChangeWeek();
		
		loadCalendarData();
		
		onWeekdaySelectorChange();
		
		return SpmActionResult.INPUT.getResult();
	}
	
	/**
	 * 
	 * @return
	 */
	public String changeDay() {
		initializeDayWeekSelector(getSelectedDate(), getSelectedWeekDay());
		
		processChangeDay();
		
		loadCalendarData();

		onWeekdaySelectorChange();
		
		return SpmActionResult.INPUT.getResult();
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
	 * @return the scheduleViewsMap
	 */
	public Map<String, String> getScheduleViewsMap() {
		if(scheduleViewsMap == null) {
			setScheduleViewsMap(getReferenceDataService().getScheduleViews());
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
	 * 
	 */
	protected abstract void onWeekdaySelectorChange();
}
