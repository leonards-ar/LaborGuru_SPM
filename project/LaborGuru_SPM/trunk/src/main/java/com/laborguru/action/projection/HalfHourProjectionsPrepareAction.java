package com.laborguru.action.projection;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.laborguru.action.SpmActionResult;
import com.laborguru.exception.ErrorEnum;
import com.laborguru.exception.ErrorMessage;
import com.laborguru.frontend.model.HalfHourElement;
import com.laborguru.model.DailyProjection;
import com.laborguru.model.HalfHourProjection;
import com.laborguru.util.SpmConstants;
import com.opensymphony.xwork2.Preparable;

/**
 * This action deals with Half Hours Projections.
 * 
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 * 
 */
@SuppressWarnings("serial")
public class HalfHourProjectionsPrepareAction extends ProjectionCalendarBaseAction implements Preparable {
	
	private List<HalfHourElement> projectionElements = new ArrayList<HalfHourElement>();
	private BigDecimal totalProjectedValues = new BigDecimal(SpmConstants.INIT_VALUE_ZERO);
	private BigDecimal totalAdjustedValues = new BigDecimal(SpmConstants.INIT_VALUE_ZERO);
	private BigDecimal totalRevisedValues = new BigDecimal(SpmConstants.INIT_VALUE_ZERO);

	private Boolean projectionError = false;
	
	/**
	 * Prepare the data to be used on the edit page
	 * 
	 * @throws Exception
	 */
	public void prepareEdit() {
		pageSetup();
	}

	/**
	 * Prepare the data to be used on the add page
	 * 
	 */
	public void prepareSave() {
		pageSetup();
	}

	/**
	 *  Prepare the data to be used on the revise used weeks result page (edit page)
	 * 
	 */
	public void prepareReviseUsedWeeks(){
		pageSetup();
	}
	
	/**
	 * 
	 * @see com.laborguru.action.projection.ProjectionCalendarBaseAction#prepareChangeDay()
	 */
	@Override
	public void prepareChangeDay() {
		pageSetup();
	}

	/**
	 * 
	 * @see com.laborguru.action.projection.ProjectionCalendarBaseAction#prepareChangeWeek()
	 */
	@Override
	public void prepareChangeWeek() {
		pageSetup();
	}

	/**
	 * 
	 */
	public void prepareReviseProjections() {
		pageSetup();
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
	 * 
	 * @see com.laborguru.action.projection.ProjectionCalendarBaseAction#processChangeDay()
	 */
	@Override
	protected void processChangeDay() {
		setUpHalfHourProjection();
	}

	/**
	 * 
	 * 
	 * @see com.laborguru.action.projection.ProjectionCalendarBaseAction#processChangeWeek()
	 */
	@Override
	protected void processChangeWeek() {
		setUpHalfHourProjection();
	}	
	
	
	/**
	 * Prepares the edit page
	 * 
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		//Calendar initialization
		initializeDayWeekSelector(getSelectedDate(), getSelectedWeekDay());
		
		setUpHalfHourProjection();
		
		setSelectedDate(getWeekDaySelector().getStringSelectedDay());
		setSelectedWeekDay(getWeekDaySelector().getStringStartingWeekDay());
		
		return SpmActionResult.EDIT.getResult();
	}


	public String save() throws Exception {
		//Calendar initialization
		initializeDayWeekSelector(getSelectedDate(), getSelectedWeekDay());

		getProjectionService().saveProjection(this.getEmployeeStore(), getElementsAsHalfHourProjectionList(getProjectionElements()), getWeekDaySelector().getSelectedDay());

		return SpmActionResult.EDIT.getResult();
	}
	
	public String reviseProjections() {
		//Calendar initialization
		initializeDayWeekSelector(getSelectedDate(), getSelectedWeekDay());
				
		calculateAndSetReviseProjections();
		
		return SpmActionResult.EDIT.getResult();
	}

	public String reviseUsedWeeks() {
		//Calendar initialization
		initializeDayWeekSelector(getSelectedDate(), getSelectedWeekDay());
		
		getNewValues();
		
		return SpmActionResult.EDIT.getResult();		
	}
	
	/**
	 * 
	 */
	private void getNewValues(){
		
		List<HalfHourProjection> projections = getProjectionService().calculateDailyHalfHourProjection(getEmployeeStore(), getTotalProjectedValues(), getWeekDaySelector().getSelectedDay(), getUsedWeeks());
		BigDecimal totalProjections = new BigDecimal(SpmConstants.INIT_VALUE_ZERO);

		//set new values;
		for(int i=0; i < getProjectionElements().size(); i++) {
			getProjectionElements().get(i).setProjectedValue(projections.get(i).getAdjustedValue());
			totalProjections = totalProjections.add(projections.get(i).getAdjustedValue());
		}
		
		setTotalProjectedValues(totalProjections);
		
		//if there is adjusted values calculate the revised projections
		if(getTotalAdjustedValues().doubleValue() > 0.00) {
			calculateAndSetReviseProjections();
		}
	}

	/**
	 * Setup the the projections element and the projected total from the daily projection
	 */
	private void setUpHalfHourProjection() {
		
		//clear Totals
		clearTotalPageValues();
				
		DailyProjection dailyProjection = getProjectionService().getDailyProjection(getEmployeeStore(), getWeekDaySelector().getSelectedDay());

		if (dailyProjection != null) {	
			//Setting projection element array
			setProjectionElements(getHalfHourElementList(dailyProjection.getHalfHourProjections()));
			
			//Setting total value
			for (HalfHourElement element: getProjectionElements()){
				setTotalProjectedValues(getTotalProjectedValues().add(element.getProjectedValue()));
			}
		}else {
			addActionError(new ErrorMessage(ErrorEnum.PROJECTION_DOES_NOT_EXIST.name()));
			//TODO: CN - This flag should be removed when we found the way to ask from the front if actionErrors is empty.
			setProjectionError(true);
		}
	}	
	
	/**
	 * Clear the totals for the page
	 */
	private void clearTotalPageValues() {
		totalProjectedValues = new BigDecimal(SpmConstants.INIT_VALUE_ZERO);
		totalAdjustedValues = new BigDecimal(SpmConstants.INIT_VALUE_ZERO);
		totalRevisedValues = new BigDecimal(SpmConstants.INIT_VALUE_ZERO);
	}
	
	/**
	 * Private method that calculates and sets the revised projections values for the collection ProjectionElements.
	 */
	private void calculateAndSetReviseProjections() {
		
		BigDecimal totalAdjusted = new BigDecimal(SpmConstants.INIT_VALUE_ZERO);
		BigDecimal totalProjections = new BigDecimal(SpmConstants.INIT_VALUE_ZERO);
		BigDecimal percentageNotChangedHours = new BigDecimal(SpmConstants.INIT_VALUE_ZERO);
		BigDecimal totalOriginalAdjustedProjections = new BigDecimal(SpmConstants.INIT_VALUE_ZERO);
		BigDecimal totalRevised = new BigDecimal(SpmConstants.INIT_VALUE_ZERO);
		
		//Getting totals for Adjusted and Projected values		
		for (HalfHourElement element : getProjectionElements()) {
			if (element.getAdjustedValue() != null) {
				totalAdjusted = totalAdjusted.add(element.getAdjustedValue());
				totalOriginalAdjustedProjections = totalOriginalAdjustedProjections.add(element.getProjectedValue());
			}
			
			totalProjections = totalProjections.add(element.getProjectedValue());
		}
		
		if(totalAdjusted.compareTo(totalProjections) > 0){
			addActionError(new ErrorMessage(ErrorEnum.CHANGES_BIGGER_THAN_PROJECTION.name()));
			setProjectionError(true);
			return;
		}
		
		//Calculating percentageNotChangedHours
		percentageNotChangedHours = totalProjections.subtract(totalOriginalAdjustedProjections);
		percentageNotChangedHours = percentageNotChangedHours.divide(totalProjections, SpmConstants.DECIMAL_SCALE, SpmConstants.ROUNDING_MODE);
		
		//Calculating revised projection value
		if (!percentageNotChangedHours.equals(BigDecimal.ZERO)){
			for (HalfHourElement element : getProjectionElements()) {
				getProjectionService().calculateRevisedValue(element, totalAdjusted, totalProjections, percentageNotChangedHours);
				totalRevised = totalRevised.add(element.getRevisedValue());
			}
		}
		setTotalRevisedValues(totalRevised);
		setTotalAdjustedValues(totalAdjusted);
		setTotalProjectedValues(totalProjections);		
	}
		

	/**
	 * @param halfHourProjections
	 * @param startTime
	 */
	private List<HalfHourElement>  getHalfHourElementList(List<HalfHourProjection> halfHourProjections){

		if (halfHourProjections == null)
			return null;
		
		List<HalfHourElement> arrayList = new ArrayList<HalfHourElement>(halfHourProjections.size());
		
		for (HalfHourProjection halfHourProjection : halfHourProjections) {
			HalfHourElement element = new HalfHourElement();
			element.setId(halfHourProjection.getId());
			element.setHour(halfHourProjection.getTimeAsString());
			element.setProjectedValue(halfHourProjection.getAdjustedValue());
			element.setRevisedValue(BigDecimal.ZERO);
			arrayList.add(element);
		}
		
		return arrayList;
	}	
	
	/**
	 * @param projectionElements2 TODO
	 * @return
	 */
	private List<HalfHourProjection> getElementsAsHalfHourProjectionList(List<HalfHourElement> auxElements) {
		
		List<HalfHourProjection> retList = new ArrayList<HalfHourProjection>(auxElements.size());
		
		int index=0;
		for (HalfHourElement element : auxElements) {
			HalfHourProjection aHalfHourProjection = new HalfHourProjection();
			aHalfHourProjection.setIndex(index++);
			aHalfHourProjection.setTime(element.getHour());
			aHalfHourProjection.setAdjustedValue(element.getRevisedValue());
			retList.add(aHalfHourProjection);
		}
		
		return retList;
	}
	
	/**
	 * @return the projectionElements
	 */
	public List<HalfHourElement> getProjectionElements() {
		return projectionElements;
	}

	/**
	 * @param projectionElements
	 *            the projectionElements to set
	 */
	public void setProjectionElements(List<HalfHourElement> projectionElements) {
		this.projectionElements = projectionElements;
	}

	/**
	 * @return the totalProjectedValues
	 */
	public BigDecimal getTotalProjectedValues() {
		return totalProjectedValues;
	}

	/**
	 * @param totalProjectedValues the totalProjectedValues to set
	 */
	public void setTotalProjectedValues(BigDecimal totalProjectedValues) {
		this.totalProjectedValues = totalProjectedValues;
	}

	/**
	 * @return the totalAdjustedValues
	 */
	public BigDecimal getTotalAdjustedValues() {
		return totalAdjustedValues;
	}

	/**
	 * @param totalAdjustedValues the totalAdjustedValues to set
	 */
	public void setTotalAdjustedValues(BigDecimal totalAdjustedValues) {
		this.totalAdjustedValues = totalAdjustedValues;
	}

	/**
	 * @return the totalRevisedValues
	 */
	public BigDecimal getTotalRevisedValues() {
		return totalRevisedValues;
	}

	/**
	 * @param totalRevisedValues the totalRevisedValues to set
	 */
	public void setTotalRevisedValues(BigDecimal totalRevisedValues) {
		this.totalRevisedValues = totalRevisedValues;
	}

	public void setProjectionError(Boolean projectionError) {
		this.projectionError = projectionError;
	}

	public Boolean getProjectionError() {
		return projectionError;
	}


}
