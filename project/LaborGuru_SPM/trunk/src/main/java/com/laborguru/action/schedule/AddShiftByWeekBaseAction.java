/*
 * File name: AddShiftByWeekBaseAction.java
 * Creation date: 08/12/2008 19:02:03
 * Copyright Mindpool
 */
package com.laborguru.action.schedule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.laborguru.action.SpmActionResult;
import com.laborguru.frontend.model.WeeklyScheduleDailyEntry;
import com.laborguru.frontend.model.WeeklyScheduleData;
import com.laborguru.frontend.model.WeeklyScheduleRow;
import com.laborguru.model.DailyProjection;
import com.laborguru.model.DailyStaffing;
import com.laborguru.model.Employee;
import com.laborguru.model.EmployeeSchedule;
import com.laborguru.model.Position;
import com.laborguru.model.Shift;
import com.laborguru.model.StoreDailyStaffing;
import com.laborguru.model.StoreSchedule;
import com.laborguru.util.CalendarUtils;
import com.laborguru.util.SpmConstants;
import com.opensymphony.xwork2.Preparable;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public abstract class AddShiftByWeekBaseAction extends AddShiftBaseAction implements Preparable {
	private static final Logger log = Logger.getLogger(AddShiftByWeekBaseAction.class);
	
	private WeeklyScheduleData weeklyScheduleData = null;
	
	private List<StoreSchedule> storeSchedules = null;
	private List<Date> weekDays = null;
	private List<StoreDailyStaffing> dailyStaffings = null;
	
	private Integer newEmployeeId;
	private String newEmployeeName;
	private Integer newEmployeePositionId;
	
	private BigDecimal weeklyVolume;
	
	/**
	 * 
	 */
	public AddShiftByWeekBaseAction() {
	}

	/**
	 * @return the storeSchedule
	 */
	protected List<StoreSchedule> getStoreSchedules() {
		if(storeSchedules == null) {
			storeSchedules = new ArrayList<StoreSchedule>();
			try {
				StoreSchedule aSchedule;
				//:TODO: Probably should retrieve the whole week from the database
				for(Date aDate : getWeekDays()) {
					aSchedule = getScheduleService().getStoreScheduleByDate(getEmployeeStore(), aDate);
					if(aSchedule == null) {
						aSchedule = new StoreSchedule();
						aSchedule.setStore(getEmployeeStore());
						aSchedule.setDay(aDate);
					}					
					storeSchedules.add(aSchedule);
				}
			} catch(RuntimeException ex) {
				log.error("Could not retrieve schedule for week days " + getWeekDays() + " for store " + getEmployeeStore(), ex);
			}

		}
		return storeSchedules;
	}

	/**
	 * 
	 * @see com.laborguru.action.schedule.AddShiftBaseAction#onWeekdaySelectorChange()
	 */
	@Override
	protected void onWeekdaySelectorChange() {
	}
	
	/**
	 * 
	 * @param position
	 */
	protected void buildScheduleDataFor(Position position) {
		int size = getStoreSchedules().size();
		StoreSchedule aSchedule;
		WeeklyScheduleRow aRow;
		
		for(int i = 0; i < size; i++) {
			aSchedule = getStoreSchedules().get(i);
			for(EmployeeSchedule employeeSchedule : aSchedule.getEmployeeSchedules()) {
				for(Shift shift : employeeSchedule.getShifts()) {
					if(!shift.isBreak() && (position == null || (position != null && isEqualPosition(position, shift.getPosition())))) {
						aRow = getRowFor(employeeSchedule, shift);
						if(aRow == null) {
							aRow = buildRowFor(employeeSchedule, shift);
							getWeeklyScheduleData().addScheduleRow(getGroupById(employeeSchedule.getEmployee(), shift), aRow);
						}
						buildScheduleDataFor(aRow, employeeSchedule, shift, i);
					}
				}
			}			
		}
	}
	
	/**
	 * 
	 * @param row
	 * @param employeeSchedule
	 * @param shift
	 * @param dayIndex
	 */
	private void buildScheduleDataFor(WeeklyScheduleRow row, EmployeeSchedule employeeSchedule, Shift shift, int dayIndex) {
		WeeklyScheduleDailyEntry entry = getDailyEntry(row.getWeeklySchedule(), dayIndex);
		if(!entry.isMultipleShifts()) {
			entry.setMultipleShifts(employeeSchedule.hasMultipleShifts(shift.getPosition()));
		}
		
		if(entry.getInHour() == null || CalendarUtils.greaterTime(entry.getInHour(), shift.getFromHour())) {
			entry.setInHour(shift.getFromHour());
			entry.resetTotalHours();
		}
		if(entry.getOutHour() == null || CalendarUtils.smallerTime(entry.getOutHour(), shift.getToHour())) {
			entry.setOutHour(shift.getToHour());
			entry.resetTotalHours();
		}
	}
	
	/**
	 * 
	 * @param entries
	 * @param dayIndex
	 * @return
	 */
	private WeeklyScheduleDailyEntry getDailyEntry(List<WeeklyScheduleDailyEntry> entries, int dayIndex) {
		if(entries.size() > dayIndex && dayIndex >= 0) {
			return entries.get(dayIndex);
		} else {
			return null;
		}
	}
	
	/**
	 * 
	 * @param employeeSchedule
	 * @param shift
	 * @return
	 */
	private WeeklyScheduleRow buildRowFor(EmployeeSchedule employeeSchedule, Shift shift) {
		WeeklyScheduleRow row = new WeeklyScheduleRow();
		Employee employee = employeeSchedule.getEmployee();
		Position position = shift.getPosition();
		if(employee != null && position != null) {
			row.setEmployeeId(employee.getId());
			row.setEmployeeMaxDaysWeek(employee.getMaxDaysWeek());
			row.setEmployeeMaxHoursDay(employee.getMaxHoursDay());
			row.setEmployeeMaxHoursWeek(employee.getMaxHoursWeek());
			row.setEmployeeName(employee.getFullName());
			row.setPositionId(position.getId());
			row.setPositionName(position.getName());
			row.setOriginalEmployeeId(employee.getId());
			row.setGroupById(getGroupById(employeeSchedule.getEmployee(), shift));
			
			row.setWeeklySchedule(initializeWeeklySchedule());
		}
		return row;
	}
	
	/**
	 * 
	 * @return
	 */
	protected List<WeeklyScheduleDailyEntry> initializeWeeklySchedule() {
		List<WeeklyScheduleDailyEntry> weekData = new ArrayList<WeeklyScheduleDailyEntry>();
		WeeklyScheduleDailyEntry entry;
		for(Date d : getWeekDays()) {
			entry = new WeeklyScheduleDailyEntry();
			entry.setDay(d);
			weekData.add(entry);
		}
		return weekData;
	}	
	
	/**
	 * 
	 * @param employeeSchedule
	 * @param shift
	 * @return
	 */
	private WeeklyScheduleRow getRowFor(EmployeeSchedule employeeSchedule, Shift shift) {
		List<WeeklyScheduleRow> dataRows = getWeeklyScheduleData().getScheduleDataFor(getGroupById(employeeSchedule.getEmployee(), shift));
		if(dataRows != null) {
			for(WeeklyScheduleRow aRow : dataRows) {
				if(isEqualId(aRow.getEmployeeId(), employeeSchedule.getEmployee().getId()) && isEqualId(aRow.getPositionId(), shift.getPosition().getId())) {
					return aRow;
				}
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param employee
	 * @param shift
	 * @return
	 */
	protected abstract Integer getGroupById(Employee employee, Shift shift);

	/**
	 * @return the weekDays
	 */
	public List<Date> getWeekDays() {
		if(weekDays == null) {
			this.weekDays = getWeekDaySelector().getWeekDays();
		}
		return weekDays;
	}

	/**
	 * @return the scheduleData
	 */
	public WeeklyScheduleData getWeeklyScheduleData() {
		if(weeklyScheduleData == null) {
			setWeeklyScheduleData(new WeeklyScheduleData());
		}
		return weeklyScheduleData;
	}

	/**
	 * @param weeklyScheduleData the scheduleData to set
	 */
	public void setWeeklyScheduleData(WeeklyScheduleData weeklyScheduleData) {
		this.weeklyScheduleData = weeklyScheduleData;
	}
	
	/**
	 * 
	 */
	protected void setScheduleData() {
		if(getWeeklyScheduleData().isEmpty()) {
			buildScheduleDataFor(getPosition());
		}
	}

	/**
	 * 
	 */
	protected void resetScheduleData() {
		setWeeklyScheduleData(null);
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
	 * 
	 * @param schedule
	 * @return
	 */
	private Set<Integer> getDifferentEmployeeIds() {
		Set<Integer> ids = new HashSet<Integer>();
		
		for(WeeklyScheduleRow aRow : getWeeklyScheduleData().getScheduleData()) {
			if(!ids.contains(aRow.getEmployeeId())) {
				ids.add(aRow.getEmployeeId());
			}
		}
		
		return ids;
	}
	
	/**
	 * 
	 * @param schedule
	 * @param position
	 * @param day
	 */
	protected void setSchedule() {
		int size = getStoreSchedules().size();
		StoreSchedule storeSchedule;
		for(int dayIndex = 0; dayIndex < size; dayIndex++ ) {
			if(isEditable(dayIndex)) {
				storeSchedule = getStoreSchedules().get(dayIndex);
				
				Set<Integer> employeeIds = getDifferentEmployeeIds();
				Employee employee;
				for(Integer employeeId : employeeIds) {
					employee = getEmployeeService().getEmployeeById(new Employee(employeeId));
					EmployeeSchedule employeeSchedule = storeSchedule.getEmployeeSchedule(employee);
					if(employeeSchedule == null) {
						employeeSchedule = new EmployeeSchedule();
						employeeSchedule.setEmployee(employee);
						employeeSchedule.setStoreSchedule(storeSchedule);
						storeSchedule.getEmployeeSchedules().add(employeeSchedule);
					}
					setShifts(getEmployeeSchedule(employeeId), employeeSchedule, dayIndex);
				}
				
				// Remove non existing shifts for the employee and for a position
				cleanStoreSchedule(employeeIds, storeSchedule, dayIndex);
			}
		}
	}

	/**
	 * 
	 * @param employeeIds
	 * @param employeeSchedule
	 * @param dayIndex
	 */
	private void cleanStoreSchedule(Set<Integer> employeeIds, StoreSchedule storeSchedule, int dayIndex) {
		if(storeSchedule != null && employeeIds != null) {
			Set<EmployeeSchedule> employeeSchedulesToRemove = new HashSet<EmployeeSchedule>();
			Set<Shift> shiftsToRemove = new HashSet<Shift>();
			
			for(EmployeeSchedule employeeSchedule : storeSchedule.getEmployeeSchedules()) {
				if(employeeSchedule.getEmployee() != null && !employeeIds.contains(employeeSchedule.getEmployee().getId())) {
					if(getPosition() == null) {
						// Applies for all positions
						employeeSchedulesToRemove.add(employeeSchedule);
					} else {
						for(Shift shift : employeeSchedule.getShifts()) {
							if(isEqualPosition(shift.getPosition(), getPosition())) {
								shiftsToRemove.add(shift);
							}
						}
						employeeSchedule.getShifts().removeAll(shiftsToRemove);
						// There are no more shifts, then remove the employee schedule
						//:TODO: Should remove also all employee schedules with just break shifts???
						if(employeeSchedule.getShifts().isEmpty()) {
							employeeSchedulesToRemove.add(employeeSchedule);
						}
					}
				}
			}
			storeSchedule.getEmployeeSchedules().removeAll(employeeSchedulesToRemove);
		}
	}
	
	/**
	 * 
	 * @param source
	 * @param employeeSchedule
	 * @param dayIndex
	 */
	private void setShifts(List<WeeklyScheduleRow> source, EmployeeSchedule employeeSchedule, int dayIndex) {
		if(getPosition() == null) {
			setShiftsAllPositions(source, employeeSchedule, dayIndex);
		} else {
			setShiftsForPosition(source, employeeSchedule, getPosition(), dayIndex);
		}
	}
	
	/**
	 * 
	 * @param row
	 * @param dayIndex
	 * @return
	 */
	private Shift retrieveShift(WeeklyScheduleRow row, int dayIndex) {
		if(row != null) {
			WeeklyScheduleDailyEntry entry = row.getWeeklySchedule().get(dayIndex);
			if(entry.isShift()) {
				Shift shift = new Shift();
				shift.setFromHour(entry.getInHour());
				shift.setToHour(entry.getOutHour());
				Position pos = new Position();
				pos.setId(row.getPositionId());			
				shift.setPosition(pos);
				return shift;
			}
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param position
	 * @param shift
	 * @param employeeSchedule
	 * @return
	 */
	private boolean changeShiftForPosition(Shift shift, EmployeeSchedule employeeSchedule) {
		if(shift != null && shift.getPosition() != null) {
			Date in = employeeSchedule.getFromHour(shift.getPosition());
			Date out = employeeSchedule.getToHour(shift.getPosition());
			return !CalendarUtils.equalsTime(in, shift.getFromHour()) || !CalendarUtils.equalsTime(out, shift.getToHour());
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * @param source
	 * @param employeeSchedule
	 * @param dayIndex
	 * @return
	 */
	private List<Shift> retrieveShiftsToKeep(List<WeeklyScheduleRow> source, EmployeeSchedule employeeSchedule, int dayIndex) {
		List<Shift> shiftsToKeep = new ArrayList<Shift>();
		
		for(Shift shift : employeeSchedule.getShifts()) {
			if(shift.isBreak()) {
				shiftsToKeep.add(shift);
			} else {
				Shift rowShift = retrieveShift(getWeeklyScheduleRowForPosition(source, shift.getPosition()), dayIndex);
				if(rowShift != null && !changeShiftForPosition(rowShift, employeeSchedule)) {
					shiftsToKeep.add(shift);
				}
			}
		}
		
		return shiftsToKeep;
	}

	/**
	 * 
	 * @param source
	 * @param position
	 * @return
	 */
	private WeeklyScheduleRow getWeeklyScheduleRowForPosition(List<WeeklyScheduleRow> source, Position position) {
		for(WeeklyScheduleRow row : source) {
			if(isEqualId(position.getId(), row.getPositionId())) {
				return row;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param shiftsToKeep
	 * @param shift
	 * @return
	 */
	private boolean isShiftToKeep(List<Shift> shiftsToKeep, Shift shift) {
		for(Shift aShift : shiftsToKeep) {
			if(isEqualId(shift.getId(), aShift.getId())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param source
	 * @param employeeSchedule
	 * @param dayIndex
	 */
	private void setShiftsAllPositions(List<WeeklyScheduleRow> source, EmployeeSchedule employeeSchedule, int dayIndex) {
		int shiftPosition = 0;
		Shift rowShift;
		List<Shift> currentShifts = employeeSchedule.getShifts();
		int currentShiftsSize = currentShifts.size();
		List<Shift> shiftsToKeep = retrieveShiftsToKeep(source, employeeSchedule, dayIndex);
			
		for(WeeklyScheduleRow row : source) {
			rowShift = retrieveShift(row, dayIndex);
			if(rowShift != null && changeShiftForPosition(rowShift, employeeSchedule)) {
				// Do not replace breaks
				while(shiftPosition < currentShifts.size() && isShiftToKeep(shiftsToKeep, currentShifts.get(shiftPosition))) {
					shiftPosition++;
				}				
				if(shiftPosition < currentShiftsSize) {
					updateShift(rowShift, currentShifts.get(shiftPosition));
					shiftPosition++;
				} else {
					rowShift.setEmployeeSchedule(employeeSchedule);
					employeeSchedule.addShift(rowShift);
				}					
			}
		}
		
		// Remove non-existing shifts
		if(shiftPosition < currentShiftsSize) {
			for(int i = currentShiftsSize - 1; i >= shiftPosition; i--) {
				if(!isShiftToKeep(shiftsToKeep, currentShifts.get(i))) {
					currentShifts.remove(i);
				}
			}
		}
	}
	
	/**
	 * 
	 * @param source
	 * @param employeeSchedule
	 * @param position
	 * @param dayIndex
	 */
	private void setShiftsForPosition(List<WeeklyScheduleRow> source, EmployeeSchedule employeeSchedule, Position position, int dayIndex) {
		int shiftPosition = 0;
		Shift rowShift;
		List<Shift> currentShifts = employeeSchedule.getShifts();
		int currentShiftsSize = currentShifts.size();
		List<Shift> shiftsToKeep = retrieveShiftsToKeep(source, employeeSchedule, dayIndex);

	
		for(WeeklyScheduleRow row : source) {
			rowShift = retrieveShift(row, dayIndex);
			if(rowShift != null && isEqualPosition(position, rowShift.getPosition()) && changeShiftForPosition(rowShift, employeeSchedule)) {
				while(shiftPosition < currentShifts.size() && (isShiftToKeep(shiftsToKeep, currentShifts.get(shiftPosition)) || !isEqualPosition(position, currentShifts.get(shiftPosition).getPosition()))) {
					shiftPosition++;
				}
				
				if(shiftPosition < currentShiftsSize) {
					updateShift(rowShift, currentShifts.get(shiftPosition));
					shiftPosition++;
				} else {
					rowShift.setEmployeeSchedule(employeeSchedule);
					employeeSchedule.addShift(rowShift);
				}
			}
		}
		
		// Remove non-existing shifts
		if(shiftPosition < currentShiftsSize) {
			for(int i = currentShiftsSize - 1; i >= shiftPosition; i--) {
				if(isEqualPosition(position, currentShifts.get(i).getPosition()) && !isShiftToKeep(shiftsToKeep, currentShifts.get(i))) {
					currentShifts.remove(i);
				}
			}
		}		
	}
	
	/**
	 * Prepare the data to be used on the edit page
	 */
	public void prepareEdit() {
		loadPageData();
	}
	
	/**
	 * 
	 * @see com.laborguru.action.schedule.AddShiftBaseAction#prepareChangeDay()
	 */
	@Override
	public void prepareChangeDay() {
		/*
		 * Change day does not apply for weekly view
		 * as changeDay is executed directly on daily
		 * actions
		 */ 
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
		/*
		 * Change day does not apply for weekly view
		 * as changeDay is executed directly on daily
		 * actions
		 */ 
	}

	/**
	 * 
	 * @see com.laborguru.action.schedule.AddShiftBaseAction#processChangeWeek()
	 */
	@Override
	protected void processChangeWeek() {
		setStoreSchedules(null);
		resetScheduleData();
		//resetStaffingData();
		setScheduleData();
		loadCopyTargetDay();		
	}

	/**
	 * 
	 * @return
	 */
	public String edit() {
		initializeDayWeekSelector(getSelectedDate(), getSelectedWeekDay());
		
		initializeSelectView();
		
		setScheduleData();
		
		return SpmActionResult.EDIT.getResult();
	}
	
	/**
	 * 
	 */
	private void loadPageData() {
		loadPositions();
	}

	/**
	 * 
	 * @throws Exception
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	public void prepare() throws Exception {
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
		if(getNewEmployeeId() != null) {
			newEmployee = getEmployeeService().getEmployeeById(new Employee(getNewEmployeeId()));
		}

		WeeklyScheduleRow newRow = new WeeklyScheduleRow();
		
		newRow.setEmployeeId(getNewEmployeeId());
		newRow.setOriginalEmployeeId(getNewEmployeeId());
		newRow.setPositionId(getNewEmployeePositionId());
		newRow.setPositionName(getPositionName(getNewEmployeePositionId()));
		newRow.setEmployeeName(getNewEmployeeName());
		newRow.setGroupById(getAddEmployeeGroupById());
		newRow.setWeeklySchedule(initializeWeeklySchedule());

		if(newEmployee != null) {
			newRow.setEmployeeMaxDaysWeek(newEmployee.getMaxDaysWeek());
			newRow.setEmployeeMaxHoursDay(newEmployee.getMaxHoursDay());
			newRow.setEmployeeMaxHoursWeek(newEmployee.getMaxHoursWeek());		
		}
		
		getWeeklyScheduleData().addScheduleRow(getAddEmployeeGroupById(), newRow);

		setNewEmployeeId(null);
		setNewEmployeeName(null);
		setNewEmployeePositionId(null);
		
		return SpmActionResult.EDIT.getResult();
	}

	/**
	 * 
	 * @return
	 */
	protected abstract Integer getAddEmployeeGroupById();
	
	/**
	 * Prepare the data to be used on the edit page
	 */
	public void prepareSave() {
		loadPageData();
	}
	
	/**
	 * 
	 * @return
	 */
	public String save() {
		initializeDayWeekSelector(getSelectedDate(), getSelectedWeekDay());
		
		// Build schedule for correct days
		setSchedule();
		
		int size = getStoreSchedules().size();
		StoreSchedule storeSchedule;
		for(int dayIndex = 0; dayIndex < size; dayIndex++ ) {
			if(isEditable(dayIndex)) {
				storeSchedule = getStoreSchedules().get(dayIndex);

				if(log.isDebugEnabled()) {
					log.debug("About to save schedule " + storeSchedule);
				}
				
				getScheduleService().save(storeSchedule);
				
				if(log.isDebugEnabled()) {
					log.debug("Saved schedule for date " + storeSchedule.getDay());
				}
			}
		}

		resetScheduleData();
		setScheduleData();
		
		addActionMessage(getText("schedule.addshift.save_success"));
		
		return SpmActionResult.EDIT.getResult();
	}
	
	/**
	 * 
	 * @param dayIndex
	 * @return
	 */
	public boolean isEditable(int dayIndex) {
		return CalendarUtils.isAfterToday(getWeekDays().get(dayIndex));
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isEditable() {
		for(int i = 0; i < getWeekDays().size(); i++) {
			if(isEditable(i)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getTotalScheduleRows() {
		return getWeeklyScheduleData().getScheduleData().size();
	}
	
	/**
	 * 
	 * @param employeeId
	 * @return
	 */
	protected abstract List<WeeklyScheduleRow> getEmployeeSchedule(Integer employeeId);
	
	/**
	 * Prepare the data to be used on the edit page
	 */
	public void prepareCancel() {
		loadPageData();
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
	 * 
	 */
	public void prepareSelectPosition() {
		loadPageData();
	}

	/**
	 * @param storeSchedules the storeSchedules to set
	 */
	public void setStoreSchedules(List<StoreSchedule> storeSchedules) {
		this.storeSchedules = storeSchedules;
	}
	
	/**
	 * 
	 * @return
	 */
	public String selectPosition() {
		initializeDayWeekSelector(getSelectedDate(), getSelectedWeekDay());
	
		resetScheduleData();
		//resetStaffingData();

		setScheduleData();
		
		return SpmActionResult.EDIT.getResult();
	}

	/**
	 * @return the weeklyVolume
	 */
	public BigDecimal getWeeklyVolume() {
		if(weeklyVolume == null) {
			//:TODO: Retrieve the SUM in only one query
			List<DailyProjection> dailyProjections = getProjectionService().getDailyProjections(getEmployeeStore(), getWeekDays().get(0), getWeekDays().get(getWeekDays().size() - 1));
			if(dailyProjections != null) {
				BigDecimal total = SpmConstants.BD_ZERO_VALUE;
				for(DailyProjection dp : dailyProjections) {
					total = total.add(dp.getDailyProjectionValue());
				}
				setWeeklyVolume(total);
			} else {
				setWeeklyVolume(SpmConstants.BD_ZERO_VALUE);
			}
		}
		return weeklyVolume;		
	}

	/**
	 * @param weeklyVolume the weeklyVolume to set
	 */
	public void setWeeklyVolume(BigDecimal weeklyVolume) {
		this.weeklyVolume = weeklyVolume;
	}	
	
	/**
	 * 
	 * @param position
	 * @return
	 */
	public String getTotalPositionTarget(Position position) {
		return CalendarUtils.minutesToTime(new Integer(getTotalPositionTargetInMinutes(position)));
	}
	
	/**
	 * 
	 * @return
	 */
	public String getTotalTarget() {
		int total = 0;
		for(StoreDailyStaffing storeDailyStaffing : getDailyStaffings()) {
			if(storeDailyStaffing != null) {
				total += getTotalTargetInMinutes(storeDailyStaffing.getStoreDailyStaffing());
			}
		}
		return CalendarUtils.minutesToTime(new Integer(total));
	}
	
	/**
	 * 
	 * @param position
	 * @return
	 */
	private int getTotalPositionTargetInMinutes(Position position) {
		int total = 0;
		for(StoreDailyStaffing storeDailyStaffing : getDailyStaffings()) {
			if(storeDailyStaffing != null) {
				DailyStaffing dailyStaffing = storeDailyStaffing.getDailyStaffing(position);
				total += getTotalDailyStaffingInMinutes(dailyStaffing);
			}
		}
		return total;
	}

	/**
	 * @return the dailyStaffings
	 */
	public List<StoreDailyStaffing> getDailyStaffings() {
		if(dailyStaffings == null) {
			dailyStaffings = new ArrayList<StoreDailyStaffing>();
			try {
				StoreDailyStaffing aDailyStaffing;
				//:TODO: Probably should retrieve the whole week from the database
				for(Date aDate : getWeekDays()) {
					aDailyStaffing = getStaffingService().getDailyStaffingByDate(getEmployeeStore(), aDate);
					dailyStaffings.add(aDailyStaffing);
				}
			} catch(RuntimeException ex) {
				log.error("Could not retrieve store daily staffing for week days " + getWeekDays() + " for store " + getEmployeeStore(), ex);
			}

		}
		return dailyStaffings;		
	}

	/**
	 * @param dailyStaffings the dailyStaffings to set
	 */
	public void setDailyStaffings(List<StoreDailyStaffing> dailyStaffings) {
		this.dailyStaffings = dailyStaffings;
	}
	
	/**
	 * 
	 * 
	 * @see com.laborguru.action.schedule.AddShiftBaseAction#loadScheduleViewsMap()
	 */
	@Override
	protected void loadScheduleViewsMap() {
		setScheduleViewsMap(getReferenceDataService().getWeeklyScheduleViews());
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
		//resetStaffingData();
		
		setScheduleData();
		
		return SpmActionResult.EDIT.getResult();
	}	
	
	/**
	 * 
	 */
	public void prepareCopySchedule() {
		loadPageData();
	}
	
	/**
	 * 
	 * @return
	 */
	public String copySchedule() {
		initializeDayWeekSelector(getSelectedDate(), getSelectedWeekDay());

		if(log.isDebugEnabled()) {
			log.debug("About to copy schedule data " + getWeeklyScheduleData() + " to week starting " + getCopyTargetDay());
		}

		Date currentDay = getWeekDaySelector().getSelectedDay();
		Date currentWeekDay = getWeekDaySelector().getStartingWeekDay();
		
		List<Object> params = new ArrayList<Object>();
		params.add(getCopyTargetDay());
		params.add(currentWeekDay);
		params.add(new Date());

		if(getCopyTargetDay() != null && getCopyTargetDay().after(new Date())) {
			/*
			 * Force selected date to the target date.
			 */
			setStoreSchedules(null);
			setWeekDays(null);
			getWeekDaySelector().setSelectedDay(getCopyTargetDay());
			getWeekDaySelector().setStartingWeekDay(getCopyTargetDay());
			
			setSchedule();

			if(log.isDebugEnabled()) {
				log.debug("About to copy schedules " + getStoreSchedules());
			}
		
			int size = getStoreSchedules().size();
			StoreSchedule storeSchedule;
			for(int dayIndex = 0; dayIndex < size; dayIndex++ ) {
				storeSchedule = getStoreSchedules().get(dayIndex);

				if(log.isDebugEnabled()) {
					log.debug("About to save schedule " + storeSchedule);
				}
				
				getScheduleService().save(storeSchedule);
				
				if(log.isDebugEnabled()) {
					log.debug("Saved schedule for date " + storeSchedule.getDay());
				}
			}

			/*
			 * Set selected day back again
			 */
			setWeekDays(null);
			getWeekDaySelector().setSelectedDay(currentDay);
			getWeekDaySelector().setStartingWeekDay(currentWeekDay);
			setStoreSchedules(null);
			
			addActionMessage(getText("schedule.addshift.weekly.copy_success", params));
		} else {
			addActionError(getText("error.schedule.addshift.invalid_target_day", params));
		}
		
		return SpmActionResult.EDIT.getResult();
	}

	/**
	 * @param weekDays the weekDays to set
	 */
	public void setWeekDays(List<Date> weekDays) {
		this.weekDays = weekDays;
	}	
}
