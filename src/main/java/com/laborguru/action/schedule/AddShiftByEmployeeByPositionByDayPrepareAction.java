/*
 * File name: AddShiftByEmployeeByPositionByDayPrepareAction.java
 * Creation date: 18/11/2008 21:01:22
 * Copyright Mindpool
 */
package com.laborguru.action.schedule;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.laborguru.action.SpmActionResult;
import com.laborguru.frontend.model.ScheduleByPositionEntry;
import com.laborguru.model.Employee;
import com.laborguru.model.Position;
import com.laborguru.service.position.PositionService;
import com.opensymphony.xwork2.Preparable;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class AddShiftByEmployeeByPositionByDayPrepareAction extends AddShiftBaseAction implements Preparable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3865026783579164330L;

	private static final Logger log = Logger.getLogger(AddShiftByEmployeeByPositionByDayPrepareAction.class);
	
	private Position position;
	
	private List<Position> positions;
	private PositionService positionService;
	
	private List<ScheduleByPositionEntry> scheduleData;
	
	/**
	 * 
	 */
	public AddShiftByEmployeeByPositionByDayPrepareAction() {
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
	 * Loads position and status list
	 */
	private void loadPageData() {
		this.setPositions(getPositionService().getPositionsByStore(getEmployeeStore()));
		loadCalendarData();
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
	 * 
	 */
	private void resetScheduleData() {
		setScheduleData(null);
	}	

	/**
	 * 
	 */
	public void prepareSelectPosition() {
		loadPageData();
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
	public void prepareSelectView() {
		loadPageData();
	}

	/**
	 * 
	 * @return
	 */
	public String selectView() {
		initializeDayWeekSelector(getSelectedDate(), getSelectedWeekDay());

		setScheduleData();
		
		return SpmActionResult.EDIT.getResult();
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
	public String addEmployee() {
		initializeDayWeekSelector(getSelectedDate(), getSelectedWeekDay());
		
		Employee newEmployee = null;
		/*
		if(getNewEmployeeId() != null) {
			newEmployee = getEmployeeService().getEmployeeById(new Employee(getNewEmployeeId()));

		}
		
		ScheduleRow newRow = new ScheduleRow();
		newRow.setEmployeeId(getNewEmployeeId());
		newRow.setOriginalEmployeeId(getNewEmployeeId());
		newRow.setPositionId(getNewEmployeePositionId());
		newRow.setPositionName(getPositionName(getNewEmployeePositionId()));
		newRow.setEmployeeName(getNewEmployeeName());
		newRow.setSchedule(initializeScheduleRow());
		newRow.setHours(initializeScheduleHoursRow());

		if(newEmployee != null) {
			newRow.setEmployeeMaxDaysWeek(newEmployee.getMaxDaysWeek());
			newRow.setEmployeeMaxHoursDay(newEmployee.getMaxHoursDay());
			newRow.setEmployeeMaxHoursWeek(newEmployee.getMaxHoursWeek());		
		}
		
		getScheduleData().add(newRow);
		
		setNewEmployeeId(null);
		setNewEmployeeName(null);
		setNewEmployeePositionId(null);
		*/
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
		
		setSchedule();

		if(log.isDebugEnabled()) {
			log.debug("About to save schedule " + getStoreSchedule());
		}
	
		getScheduleService().save(getStoreSchedule());
		getStaffingService().save(getDailyStaffing());

		resetScheduleData();
		setScheduleData();
		
		addActionMessage(getText("schedule.addshift.save_success"));
		
		return SpmActionResult.EDIT.getResult();
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
	 * @return the scheduleData
	 */
	public List<ScheduleByPositionEntry> getScheduleData() {
		return scheduleData;
	}

	/**
	 * @param scheduleData the scheduleData to set
	 */
	public void setScheduleData(List<ScheduleByPositionEntry> scheduleData) {
		this.scheduleData = scheduleData;
	}

	/**
	 * 
	 */
	private void setScheduleData() {
		if(scheduleData == null || scheduleData.isEmpty()) {
			ScheduleByPositionEntry scheduleEntry;
			for(Position aPosition : getPositions()) {
				scheduleEntry = new ScheduleByPositionEntry();
				scheduleEntry.setScheduleData(buildScheduleFor(aPosition));
				scheduleEntry.setMinimumStaffing(buildMinimumStaffingFor(aPosition));
				scheduleEntry.setPosition(aPosition);
			}
		}
	}	
	
	/**
	 * 
	 */
	private void setSchedule() {
		for(ScheduleByPositionEntry anEntry : getScheduleData()) {
			setSchedule(anEntry.getScheduleData(), anEntry.getPosition());	
		}
	}
}
