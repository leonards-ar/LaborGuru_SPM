package com.laborguru.action.projection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.laborguru.exception.ErrorEnum;
import com.laborguru.exception.ErrorMessage;
import com.laborguru.frontend.model.ActualValueElement;
import com.laborguru.model.DailyHistoricSales;
import com.laborguru.model.DistributionType;
import com.laborguru.model.Store;
import com.laborguru.service.historicsales.HistoricSalesService;
import com.laborguru.util.SpmConstants;
import com.opensymphony.xwork2.Preparable;

/**
 * This action deals with Actual Values.
 * 
 * @author <a href="cnunezre@gmail.com">Cristian Nunez</a>
 * @version 1.0
 * @since SPM 1.1
 * 
 */
@SuppressWarnings("serial")
public class ActualValuesPrepareAction extends DailyProjectionBaseAction implements Preparable {

	private List<ActualValueElement> dailyActuals = new ArrayList<ActualValueElement>(SpmConstants.DAILY_PROJECTION_PERIOD_DAYS);
		
	private Integer totalMainValue = 0;
	
	//Flag that indicates wheter the projections view allows to save a new projection to the user.
	//By default is true, the value is set in setupDailyProjectionData()
	private Boolean allowToSaveWeek = true;

	HistoricSalesService historicSalesService;
		
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
	 * 
	 */
	protected void setupDailyProjectionData() {
		// Force object initialization
		initializeDayWeekSelector(getSelectedDate(), getSelectedDate());		

		clearPageValues();

		// Get calculated projections depending on the projection type
		Store employeeStore = this.getEmployeeStore();
		
		// Set default adjusted values
		for (int i = 0; i < SpmConstants.DAILY_PROJECTION_PERIOD_DAYS; i++) {	
			DailyHistoricSales dailyHistoricSales = getHistoricSalesService().getDailyHistoricSales(employeeStore, getWeekDaySelector().getWeekDays().get(i));
			ActualValueElement actualValue = new ActualValueElement();
			
			actualValue.setMainValue(dailyHistoricSales.getDailyHistoricSalesValue());
			actualValue.setDate(getWeekDaySelector().getWeekDays().get(i));
						
			getDailyActuals().add(actualValue);
		}

				
		// calculate and set the total
		boolean shouldAllowSave = false;
		
		for (ActualValueElement actualValue : getDailyActuals()) {
			this.totalMainValue += actualValue.getMainValue().intValue();
			
			//If any of the projections for the weeks is editable 
			//so the page should render the save/calculate bottom
			if (!shouldAllowSave && actualValue.getEditable()){
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
		setupDailyProjectionData();
	}	
	
	/**
	 * 
	 * @see com.laborguru.action.projection.DailyProjectionBaseAction#customSave()
	 */
	@Override
	protected void customSave(){
		//Saving each projection
		int i=0;
		List<Date> weekDates = getWeekDaySelector().getWeekDays();
		for (ActualValueElement dailyActuals: getDailyActuals()){
			if (dailyActuals.getEditable() && (dailyActuals.getMainValue().intValue() != 0)){

				DailyHistoricSales dailyHistoricSales = new DailyHistoricSales();
				dailyHistoricSales.setStore(this.getEmployeeStore());
				dailyHistoricSales.setSalesDate(weekDates.get(i));
				
				dailyHistoricSales = getHistoricSalesService().calculateHistoricSalesStaticProjection(dailyHistoricSales, dailyActuals.getMainValue());
				getHistoricSalesService().saveDailyHistoricSales(dailyHistoricSales);
			}
			i++;
		}		
	}
	
	
	/**
	 * 
	 * @see com.laborguru.action.projection.DailyProjectionBaseAction#customEdit()
	 */
	@Override
	protected void customEdit() {
		if(!DistributionType.STATIC.equals(getEmployeeStore().getDistributionType())){
			addActionError(new ErrorMessage(ErrorEnum.STORE_NOT_STATIC.name()));
			//TODO: CN - This flag should be removed when we found the way to ask from the front if actionErrors is empty.
			setProjectionError(true);			
		}		
	}
	
	/**
	 * Clear the totals for the page
	 */
	private void clearPageValues() {
		setTotalMainValue(0);
		
		getDailyActuals().clear();
		setAllowToSaveWeek(true);
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

	/**
	 * @return the totalMainValue
	 */
	public Integer getTotalMainValue() {
		return totalMainValue;
	}

	/**
	 * @param totalMainValue the totalMainValue to set
	 */
	public void setTotalMainValue(Integer totalMainValue) {
		this.totalMainValue = totalMainValue;
	}

	/**
	 * @return the dailyActuals
	 */
	public List<ActualValueElement> getDailyActuals() {
		return dailyActuals;
	}

	/**
	 * @param dailyActuals the dailyActuals to set
	 */
	public void setDailyActuals(List<ActualValueElement> dailyActuals) {
		this.dailyActuals = dailyActuals;
	}

	/**
	 * @return the historicSalesService
	 */
	public HistoricSalesService getHistoricSalesService() {
		return historicSalesService;
	}

	/**
	 * @param historicSalesService the historicSalesService to set
	 */
	public void setHistoricSalesService(HistoricSalesService historicSalesService) {
		this.historicSalesService = historicSalesService;
	}
}
