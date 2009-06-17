package com.laborguru.action.projection;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.laborguru.action.SpmActionResult;
import com.laborguru.frontend.model.DailyProjectionElement;
import com.laborguru.model.DailyProjection;
import com.laborguru.util.CalendarUtils;
import com.laborguru.util.SpmConstants;
import com.opensymphony.xwork2.Preparable;

/**
 * This action deals with Daily Projections.
 * 
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 * 
 */
@SuppressWarnings("serial")
public class DailyProjectionsPrepareAction extends ProjectionCalendarBaseAction implements Preparable {

	private List<DailyProjectionElement> dailyProjections = new ArrayList<DailyProjectionElement>(SpmConstants.DAILY_PROJECTION_PERIOD_DAYS);
	
	
	private BigDecimal totalProjected = new BigDecimal(SpmConstants.INIT_VALUE_ZERO);
	private BigDecimal totalAdjusted = new BigDecimal(SpmConstants.INIT_VALUE_ZERO);

	//Flag that indicates wheter the projections view allows to save a new projection to the user.
	//By default is true, the value is set in setupDailyProjectionData()
	private Boolean allowToSaveWeek = true;
	
	/**
	 * Prepare the data to be used on the edit page
	 * 
	 * @throws Exception
	 */
	public void prepareEdit() {
		pageSetup();
	}
	
	/**
	 * 
	 * 
	 * @see com.laborguru.action.projection.ProjectionCalendarBaseAction#prepareChangeDay()
	 */
	@Override
	public void prepareChangeDay() {
		//It's need it as it was declared abstract in the super class. We are not using change day for daily projection behaviour.
	}

	/**
	 * 
	 * 
	 * @see com.laborguru.action.projection.ProjectionCalendarBaseAction#prepareChangeWeek()
	 */
	@Override
	public void prepareChangeWeek() {
		prepareEdit();
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
	 * Clear the totals for the page
	 */
	private void clearPageValues() {
		setTotalAdjusted(new BigDecimal(SpmConstants.INIT_VALUE_ZERO));
		setTotalProjected(new BigDecimal(SpmConstants.INIT_VALUE_ZERO));
		
		getDailyProjections().clear();
		setAllowToSaveWeek(true);
	}
		
	
	/**
	 * 
	 */
	protected void setupDailyProjectionData() {		
		// Force object initialization
		initializeDayWeekSelector(getSelectedDate(), getSelectedDate());		

		clearPageValues();

		//Setting the date for the weekly calculation
		Date calculatedDate = getWeekDaySelector().getStartingWeekDay();
		Date firstDayThisWeek =  getWeekDaySelector().getFirstDayOfWeek(CalendarUtils.todayWithoutTime());

		if (calculatedDate.compareTo(firstDayThisWeek) >= 0){
			calculatedDate = firstDayThisWeek;			
		} 
		
		//TODO: confirm that there is no need of substracting the week
		//calculatedDate = CalendarUtils.addOrSubstractDays(calculatedDate, -7);			
		
		
		// Get calculated projections		
		List<BigDecimal> calculatedProjections = getProjectionService().getAvgDailyProjectionForAWeek(getUsedWeeks(), this.getEmployeeStore(), calculatedDate);		
		List<DailyProjection> adjustedProjections = getProjectionService().getAdjustedDailyProjectionForAWeek(this.getEmployeeStore(), getWeekDaySelector().getStartingWeekDay());

		Calendar  auxCalendar = Calendar.getInstance();
		// Set default adjusted values
		for (int i = 0; i < SpmConstants.DAILY_PROJECTION_PERIOD_DAYS; i++) {
			DailyProjectionElement dailyProjection = new DailyProjectionElement();
			
			dailyProjection.setAdjustedProjection(adjustedProjections.get(i).getDailyProjectionValue());			
			dailyProjection.setProjectionDate(getWeekDaySelector().getWeekDays().get(i));
			
			auxCalendar.setTime(dailyProjection.getProjectionDate());
			BigDecimal auxValue = calculatedProjections.get(auxCalendar.get(Calendar.DAY_OF_WEEK)-1);
			
			dailyProjection.setCalculatedProjection(auxValue);
			
			if (dailyProjection.getAdjustedProjection() == null)
				dailyProjection.setAdjustedProjection(auxValue);
			
			getDailyProjections().add(dailyProjection);
		}

				
		// calculate and set the total
		boolean shouldAllowSave = false;
		for (DailyProjectionElement projection : getDailyProjections()) {
			this.totalProjected = totalProjected.add(projection.getCalculatedProjection());
			this.totalAdjusted = totalAdjusted.add(projection.getAdjustedProjection());
			
			//If any of the projections for the weeks is editable 
			//so the page should render the save/calculate bottom
			if (!shouldAllowSave && projection.getEditable()){
				shouldAllowSave = true;
			}
		}
		setAllowToSaveWeek(shouldAllowSave);
	}

	/**
	 * 
	 * 
	 * @see com.laborguru.action.projection.ProjectionCalendarBaseAction#processChangeDay()
	 */
	@Override
	protected void processChangeDay() {
		//It's need it as it was declared abstract in the super class. We are not using change day for daily projection behaviour.
	}

	/**
	 * 
	 * 
	 * @see com.laborguru.action.projection.ProjectionCalendarBaseAction#processChangeWeek()
	 */
	@Override
	protected void processChangeWeek() {
		setUsedWeeks(this.getEmployeeStore().getDailyProjectionsWeeksDefault());
		setupDailyProjectionData();
	}	
	
	/**
	 * Prepares the edit page
	 * 
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		setupDailyProjectionData();

		return SpmActionResult.EDIT.getResult();
	}


	/**
	 * @return the totalProjected
	 */
	public BigDecimal getTotalProjected() {
		return totalProjected;
	}

	/**
	 * @param totalProjected
	 *            the totalProjected to set
	 */
	public void setTotalProjected(BigDecimal totalProjected) {
		this.totalProjected = totalProjected;
	}

	/**
	 * @return the totalAdjusted
	 */
	public BigDecimal getTotalAdjusted() {
		return totalAdjusted;
	}

	/**
	 * @param totalAdjusted
	 *            the totalAdjusted to set
	 */
	public void setTotalAdjusted(BigDecimal totalAdjusted) {
		this.totalAdjusted = totalAdjusted;
	}

	/**
	 * @return the dailyProjections
	 */
	public List<DailyProjectionElement> getDailyProjections() {
		return dailyProjections;
	}

	/**
	 * @param dailyProjections the dailyProjections to set
	 */
	public void setDailyProjections(List<DailyProjectionElement> dailyProjections) {
		this.dailyProjections = dailyProjections;
	}

	/**
	 * @return the allowToSave
	 */
	public Boolean getAllowToSaveWeek() {
		return allowToSaveWeek;
	}

	/**
	 * @param allowToSave the allowToSave to set
	 */
	public void setAllowToSaveWeek(Boolean allowToSave) {
		this.allowToSaveWeek = allowToSave;
	}
}
