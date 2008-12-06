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
	private static final String DEFAULT_VIEW="total";
	private static final String DEFAULT_PERIOD = "weekly";
	
	private WeekDaySelector weekDaySelector;
	private String selectedDate;
	private String selectedWeekDay;	
	private String selectView;
	private String period;

	private Map<String,String>viewMap;
	private Map<String,String>periodMap;
	
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
	 * @return the viewMap
	 */
	public Map<String, String> getViewMap() {
		return viewMap;
	}

	/**
	 * @param viewMap the viewMap to set
	 */
	public void setViewMap(Map<String, String> viewMap) {
		this.viewMap = viewMap;
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
	 * @return the periodMap
	 */
	public Map<String, String> getPeriodMap() {
		return periodMap;
	}

	/**
	 * @param periodMap the periodMap to set
	 */
	public void setPeriodMap(Map<String, String> periodMap) {
		this.periodMap = periodMap;
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
		setViewMap(getReferenceDataService().getReportViews());
		setPeriodMap(getReferenceDataService().getReportPeriods());
		if(getSelectView() == null) {
			setSelectView(DEFAULT_VIEW);
		} else {
			setSelectView(getSelectView());
		}
		
		if(getPeriod() == null) {
			setPeriod(DEFAULT_PERIOD);
		} else {
			setPeriod(getPeriod());
		}
		
	}
	
	public abstract void prepareChangeWeek();
	protected abstract void processChangeWeek();

}
