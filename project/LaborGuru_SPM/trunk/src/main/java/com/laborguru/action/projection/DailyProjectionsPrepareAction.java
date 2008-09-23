package com.laborguru.action.projection;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.laborguru.action.SpmActionResult;
import com.laborguru.exception.ErrorEnum;
import com.laborguru.exception.ErrorMessage;
import com.laborguru.model.OperationTime;
import com.laborguru.model.Store;
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

	private List<BigDecimal> calculatedProjections = new ArrayList<BigDecimal>(7);
	private List<BigDecimal> adjustedProjections = new ArrayList<BigDecimal>(7);

	private BigDecimal totalProjected = new BigDecimal("0");
	private BigDecimal totalAdjusted = new BigDecimal("0");

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
		prepareEdit();
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
	private void setupDailyProjectionData() {
		// Force object initialization
		getWeekDaySelector();

		// Get calculated projections
		setCalculatedProjections(getProjectionService().getAvgDailyProjectionForAWeek(getUsedWeeks(), this.getEmployeeStore(), getWeekDaySelector().getStartingWeekDay()));
		setAdjustedProjections(getProjectionService().getAdjustedDailyProjectionForAWeek(this.getEmployeeStore(), getWeekDaySelector().getStartingWeekDay()));

		// Set default adjusted values
		for (int i = 0; i < getAdjustedProjections().size(); i++) {
			if (getAdjustedProjections().get(i).intValue() == 0) {
				getAdjustedProjections().set(i, getCalculatedProjections().get(i));
			}
		}

		// calculate and set the total
		for (BigDecimal aValue : getCalculatedProjections()) {
			this.totalProjected = totalProjected.add(aValue);
		}

		// calculate and set the total
		for (BigDecimal aValue : getAdjustedProjections()) {
			this.totalAdjusted = totalAdjusted.add(aValue);
		}

	}


	/**
	 * 
	 * 
	 * @see com.laborguru.action.projection.ProjectionCalendarBaseAction#processChangeDay()
	 */
	@Override
	protected void processChangeDay() {
		setupDailyProjectionData();
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
	 * Stores an employee on the DB
	 * 
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {

		//TODO:This should be validated in a different place/way. As we have to show something to Nioko tomorrow I'm putting it here just for now.
		//Now it's ugly, very ugly....
		Store storeAux = this.getEmployeeStore();		
		for (int j=0; j < 7; j++){
			OperationTime operationTime = storeAux.getStoreOperationTimeByDate(getWeekDaySelector().getWeekDays().get(j));
			if (operationTime == null || operationTime.getOpenHour() == null || operationTime.getCloseHour() == null){
				addActionError(new ErrorMessage(ErrorEnum.OPERATION_TIME_IS_NOT_SET_FOR_STORE.name()));
				prepareEdit();
				setupDailyProjectionData();				
				return SpmActionResult.INPUT.getResult();
			}

		}

		//Saving each projection
		int i=0;
		for (BigDecimal dailyProjection: getAdjustedProjections()){
			getProjectionService().saveDailyProjection(this.getEmployeeStore(), dailyProjection,getWeekDaySelector().getWeekDays().get(i));
			i++;
		}
		return SpmActionResult.SUCCESS.getResult();
	}

	/**
	 * @return the calculatedProjections
	 */
	public List<BigDecimal> getCalculatedProjections() {
		return calculatedProjections;
	}

	/**
	 * @param calculatedProjections
	 *            the calculatedProjections to set
	 */
	public void setCalculatedProjections(List<BigDecimal> calculatedProjections) {
		this.calculatedProjections = calculatedProjections;
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
	 * @return the adjustedProjections
	 */
	public List<BigDecimal> getAdjustedProjections() {
		return adjustedProjections;
	}

	/**
	 * @param adjustedProjections
	 *            the adjustedProjections to set
	 */
	public void setAdjustedProjections(List<BigDecimal> adjustedProjections) {
		this.adjustedProjections = adjustedProjections;
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
}
