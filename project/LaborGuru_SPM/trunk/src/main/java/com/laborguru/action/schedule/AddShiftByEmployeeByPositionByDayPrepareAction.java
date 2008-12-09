/*
 * File name: AddShiftByEmployeeByPositionByDayPrepareAction.java
 * Creation date: 18/11/2008 21:01:22
 * Copyright Mindpool
 */
package com.laborguru.action.schedule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.laborguru.action.SpmActionResult;
import com.laborguru.frontend.model.ScheduleByPositionEntry;
import com.laborguru.frontend.model.ScheduleRow;
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
public class AddShiftByEmployeeByPositionByDayPrepareAction extends AddShiftByDayBaseAction implements Preparable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3865026783579164330L;

	private static final Logger log = Logger.getLogger(AddShiftByEmployeeByPositionByDayPrepareAction.class);
	
	private Position position;
	private Integer scheduleDataIndex;
	
	private List<Position> positions;
	private PositionService positionService;
	
	private List<ScheduleByPositionEntry> positionScheduleData;

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
		loadCopyTargetDay();
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
		loadCopyTargetDay();
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
		loadCopyTargetDay();
	}
	
	/**
	 * 
	 */
	private void resetScheduleData() {
		setPositionScheduleData(null);
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
	 * 
	 */
	public void prepareCopySchedule() {
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
		
		resetScheduleData();
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
		
		if(getScheduleDataIndex() != null && getScheduleDataIndex().intValue() >= 0 && getScheduleDataIndex().intValue() < getPositionScheduleData().size()) {
			ScheduleByPositionEntry entry = getPositionScheduleData().get(getScheduleDataIndex().intValue());
			
			if(entry.getNewEmployeeId() != null) {
				newEmployee = getEmployeeService().getEmployeeById(new Employee(entry.getNewEmployeeId()));
	
			}
			
			ScheduleRow newRow = new ScheduleRow();
			newRow.setEmployeeId(entry.getNewEmployeeId());
			newRow.setOriginalEmployeeId(entry.getNewEmployeeId());
			newRow.setPositionId(entry.getNewEmployeePositionId());
			newRow.setPositionName(getPositionName(entry.getNewEmployeePositionId()));
			newRow.setEmployeeName(entry.getNewEmployeeName());
			newRow.setSchedule(initializeScheduleRow());
			newRow.setHours(initializeScheduleHoursRow());
	
			if(newEmployee != null) {
				newRow.setEmployeeMaxDaysWeek(newEmployee.getMaxDaysWeek());
				newRow.setEmployeeMaxHoursDay(newEmployee.getMaxHoursDay());
				newRow.setEmployeeMaxHoursWeek(newEmployee.getMaxHoursWeek());		
			}
			
			entry.getScheduleData().add(newRow);
			
			entry.setNewEmployeeId(null);
			entry.setNewEmployeeName(null);
			entry.setNewEmployeePositionId(null);
		}

		return SpmActionResult.EDIT.getResult();
	}
	
	/**
	 * 
	 * @return
	 */
	public String copySchedule() {
		initializeDayWeekSelector(getSelectedDate(), getSelectedWeekDay());

		if(log.isDebugEnabled()) {
			log.debug("About to copy schedule data " + getPositionScheduleData() + " to date " + getCopyTargetDay());
		}

		Date currentDay = getWeekDaySelector().getSelectedDay();
		List<Object> params = new ArrayList<Object>();
		params.add(getCopyTargetDay());
		params.add(currentDay);
		params.add(new Date());

		if(getCopyTargetDay() != null && getCopyTargetDay().after(new Date())) {
			/*
			 * Force selected date to the target date.
			 */
			setStoreSchedule(null);
			getWeekDaySelector().setSelectedDay(getCopyTargetDay());
			
			setSchedule();

			if(log.isDebugEnabled()) {
				log.debug("About to copy schedule " + getStoreSchedule());
			}
		
			getScheduleService().save(getStoreSchedule());

			/*
			 * Set selected day back again
			 */
			getWeekDaySelector().setSelectedDay(currentDay);
			setStoreSchedule(null);
			
			addActionMessage(getText("schedule.addshift.copy_success", params));
		} else {
			addActionError(getText("error.schedule.addshift.invalid_target_day", params));
		}
		
		return SpmActionResult.EDIT.getResult();
	}
	
	/**
	 * 
	 * @return
	 */
	public String save() {
		initializeDayWeekSelector(getSelectedDate(), getSelectedWeekDay());
		
		if(log.isDebugEnabled()) {
			log.debug("About to save schedule data" + getPositionScheduleData());
		}
		
		setSchedule();

		if(log.isDebugEnabled()) {
			log.debug("About to save schedule " + getStoreSchedule());
		}
	
		getScheduleService().save(getStoreSchedule());

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
	 * @return the positionScheduleData
	 */
	public List<ScheduleByPositionEntry> getPositionScheduleData() {
		if(positionScheduleData == null) {
			setPositionScheduleData(new ArrayList<ScheduleByPositionEntry>());
		}
		return positionScheduleData;
	}

	/**
	 * @param positionScheduleData the scheduleData to set
	 */
	public void setPositionScheduleData(List<ScheduleByPositionEntry> positionScheduleData) {
		this.positionScheduleData = positionScheduleData;
	}

	/**
	 * 
	 */
	private void setScheduleData() {
		if(positionScheduleData == null || positionScheduleData.isEmpty()) {
			ScheduleByPositionEntry scheduleEntry;
			for(Position aPosition : getPositions()) {
				scheduleEntry = new ScheduleByPositionEntry();
				scheduleEntry.setScheduleData(buildScheduleFor(aPosition));
				scheduleEntry.setMinimumStaffing(buildMinimumStaffingFor(aPosition));
				scheduleEntry.setPosition(aPosition);
				
				getPositionScheduleData().add(scheduleEntry);
			}
		}
	}	
	
	/**
	 * 
	 */
	private void setSchedule() {
		for(ScheduleByPositionEntry anEntry : getPositionScheduleData()) {
			setSchedule(anEntry.getScheduleData(), anEntry.getPosition());	
		}
	}

	/**
	 * @return the scheduleDataIndex
	 */
	public Integer getScheduleDataIndex() {
		return scheduleDataIndex;
	}

	/**
	 * @param scheduleDataIndex the scheduleDataIndex to set
	 */
	public void setScheduleDataIndex(Integer scheduleDataIndex) {
		this.scheduleDataIndex = scheduleDataIndex;
	}
}
