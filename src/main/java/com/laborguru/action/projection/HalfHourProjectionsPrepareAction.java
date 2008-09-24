package com.laborguru.action.projection;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import com.laborguru.action.SpmActionResult;
import com.laborguru.frontend.model.HalfHourElement;
import com.laborguru.model.DailyProjection;
import com.laborguru.model.HalfHourProjection;
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
	private static final Logger log = Logger.getLogger(HalfHourProjectionsPrepareAction.class);
	
	private static final int DECIMAL_SCALE = 16;
	private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;
	private static final String INIT_VALUE_ZERO = "0.00";

	private static final BigDecimal BIG_DECIMAL_ZERO = new BigDecimal(INIT_VALUE_ZERO);

	private List<HalfHourElement> projectionElements;
	private BigDecimal totalProjectedValues = new BigDecimal(INIT_VALUE_ZERO);
	private BigDecimal totalAdjustedValues = new BigDecimal(INIT_VALUE_ZERO);
	private BigDecimal totalRevisedValues = new BigDecimal(INIT_VALUE_ZERO);
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

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
		setUpHalfHourProjection();
		return SpmActionResult.EDIT.getResult();
	}

	public String save() throws Exception {		
		 setResults();
		return SpmActionResult.INPUT.getResult();
	}
	
	public String reviseProjections() {
		calculateAndSetReviseProjections();
		
		return SpmActionResult.EDIT.getResult();
	}

	public String reviseUsedWeeks() {
		getNewValues();
		
		return SpmActionResult.EDIT.getResult();		
	}
	
	/**
	 * 
	 */
	private void getNewValues(){
		List<HalfHourProjection> projections = getProjectionService().calculateDailyHalfHourProjection(getEmployeeStore(), getTotalProjectedValues(), getWeekDaySelector().getSelectedDay(), getUsedWeeks());
		BigDecimal totalProjections = new BigDecimal(INIT_VALUE_ZERO);

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
	 * Private method that calculates and sets the revised projections values for the collection ProjectionElements.
	 */
	private void calculateAndSetReviseProjections() {
		
		BigDecimal totalAdjusted = new BigDecimal(INIT_VALUE_ZERO);
		BigDecimal totalProjections = new BigDecimal(INIT_VALUE_ZERO);
		BigDecimal percentageNotChangedHours = new BigDecimal(INIT_VALUE_ZERO);
		BigDecimal totalOriginalAdjustedProjections = new BigDecimal(INIT_VALUE_ZERO);
		BigDecimal totalRevised = new BigDecimal(INIT_VALUE_ZERO);
		
		//Getting totals for Adjusted and Projected values		
		for (HalfHourElement element : getProjectionElements()) {
			if (element.getAdjustedValue() != null) {
				totalAdjusted = totalAdjusted.add(element.getAdjustedValue());
				totalOriginalAdjustedProjections = totalOriginalAdjustedProjections.add(element.getProjectedValue());
			}
			
			totalProjections = totalProjections.add(element.getProjectedValue());
		}
		
		//Calculating percentageNotChangedHours
		percentageNotChangedHours = totalProjections.subtract(totalOriginalAdjustedProjections);
		percentageNotChangedHours = percentageNotChangedHours.divide(totalProjections, DECIMAL_SCALE, ROUNDING_MODE);
		
		//Calculating revised projection value
		if (!percentageNotChangedHours.equals(BIG_DECIMAL_ZERO)){
			for (HalfHourElement element : getProjectionElements()) {
				getProjectionService().calculateRevisedValue(element, totalAdjusted, totalProjections, percentageNotChangedHours);
				totalRevised = totalRevised.add(element.getRevisedValue());
			}
		}
		setTotalRevisedValues(totalRevised);
		setTotalAdjustedValues(totalAdjusted);
		setTotalProjectedValues(totalProjections);
	}
		

	private void setResults() {
		// removed new Date(getSelectedDate())
		DailyProjection dailyProjection = getProjectionService().getDailyProjection(getEmployeeStore(), getWeekDaySelector().getSelectedDay());
		for(HalfHourElement element: getProjectionElements()) {
			HalfHourProjection halfHourProjection = getHalfHourProjectionById(element.getId());
			if(getTotalRevisedValues().intValue() > 0) {
				halfHourProjection.setAdjustedValue(element.getRevisedValue());
			} else {
				halfHourProjection.setAdjustedValue(element.getProjectedValue());
			}
			dailyProjection.addHalfHourProjection(halfHourProjection);
		}
	}
	
	private HalfHourProjection getHalfHourProjectionById(Long id) {
		HalfHourProjection element = null;
		DailyProjection dailyProjection = getProjectionService().getDailyProjection(getEmployeeStore(), getWeekDaySelector().getSelectedDay());
		for(HalfHourProjection halfHourProjection: dailyProjection.getHalfHourProjections()) {
			if(halfHourProjection.getId().equals(id)) {
				element = halfHourProjection;
				break;
			}
		}
		return element;
	}
	


	private void setUpHalfHourProjection() {
		// Force object initialization
		setSelectedDate(getWeekDaySelector().getStringStartingWeekDay());

		DailyProjection dailyProjection = getProjectionService().getDailyProjection(getEmployeeStore(), getWeekDaySelector().getSelectedDay());

		if (dailyProjection != null) {
			setHalfHourElement(dailyProjection.getHalfHourProjections(), dailyProjection.getStartingTime());
		}
	}

	private void setHalfHourElement(List<HalfHourProjection> halfHourProjections, Date startTime){
		
		BigDecimal totalProjections = new BigDecimal(INIT_VALUE_ZERO);
		for (HalfHourProjection halfHourProjection : halfHourProjections) {
			HalfHourElement element = new HalfHourElement();
			element.setId(halfHourProjection.getId());
			element.setHour(getTime(startTime, halfHourProjection.getIndex()));
			element.setProjectedValue(halfHourProjection.getAdjustedValue());
			element.setRevisedValue(new BigDecimal(0));
			this.getProjectionElements().add(element);
			totalProjections = totalProjections.add(halfHourProjection.getAdjustedValue());
		}
		setTotalProjectedValues(totalProjections);
	}
	
	private String getTime(Date startTime, Integer elementIndex) {
		DateTime datetime = new DateTime(startTime);
		return datetime.plusMinutes((30 * elementIndex.intValue())).toString("HH:mm");
	}

	private Date getFormatDate(String date) {
		try {
		return sdf.parse(date); 
		} catch (ParseException e) {
			log.error("Error trying to parse: " + date);
		}
		return null;
		
	}
	/**
	 * @return the projectionElements
	 */
	public List<HalfHourElement> getProjectionElements() {
		if (projectionElements == null) {
			projectionElements = new ArrayList<HalfHourElement>();
		}

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


}
