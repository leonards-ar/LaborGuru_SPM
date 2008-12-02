package com.laborguru.action.report;

import java.util.Map;

import com.laborguru.action.SpmAction;
import com.laborguru.action.SpmActionResult;
import com.laborguru.frontend.model.WeekDaySelector;
import com.laborguru.service.data.ReferenceDataService;

/**
 *
 * @author <a href="fbarreraoro@gmail.com">Federico Barrera Oro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public abstract class ScheduleReportPrepareAction extends SpmAction  {

	private static final long serialVersionUID = 1L;

	private WeekDaySelector weekDaySelector;
	private String selectedDate;
	private String selectedWeekDay;	
	private String displayType;
	private String period;

	private Map<String,String>displayMap;
	
	private ReferenceDataService referenceDataService;
	
	/**
	 * @return the weekDaySelector
	 */
	public WeekDaySelector getWeekDaySelector() {
		if(weekDaySelector == null) {
			weekDaySelector = new WeekDaySelector(getEmployeeStore().getFirstDayOfWeek());
		}
		return weekDaySelector;
	}

	public String changeWeek() {

		getWeekDaySelector().initializeChangeWeek(getSelectedDate(), getSelectedWeekDay());

		processChangeWeek();
		
		loadCalendarData();
		
		return SpmActionResult.INPUT.getResult();
	}
	
	/**
	 * 
	 */
	protected void loadCalendarData() {
		setSelectedDate(getWeekDaySelector().getStringStartingWeekDay());
		setSelectedWeekDay(getWeekDaySelector().getStringSelectedDay());
	}

	/**
	 * @param weekDaySelector the weekDaySelector to set
	 */
	public void setWeekDaySelector(WeekDaySelector weekDaySelector) {
		this.weekDaySelector = weekDaySelector;
	}

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
	 * @return the displayType
	 */
	public String getDisplayType() {
		return displayType;
	}

	/**
	 * @param displayType the displayType to set
	 */
	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}

	/**
	 * @return the period
	 */
	public String getPeriod() {
		return period;
	}

	/**
	 * @param period the period to set
	 */
	public void setPeriod(String period) {
		this.period = period;
	}

	/**
	 * @return the displayMap
	 */
	public Map<String, String> getDisplayMap() {
		return displayMap;
	}

	/**
	 * @param displayMap the displayMap to set
	 */
	public void setDisplayMap(Map<String, String> displayMap) {
		this.displayMap = displayMap;
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

	protected void pageSetup() {
		setDisplayMap(getReferenceDataService().getReportViews());
	}
	
	public abstract void prepareChangeWeek();
	protected abstract void processChangeWeek();

}
