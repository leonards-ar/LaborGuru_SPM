package com.laborguru.action.projection;

import java.math.BigDecimal;
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
	
	private List<HalfHourElement> projectionElements;
	private BigDecimal totalProjectedValues = new BigDecimal(0);
	private BigDecimal totalAdjustedValues = new BigDecimal(0);
	private BigDecimal totalRevisedValues = new BigDecimal(0);
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
	 * @throws Exception
	 */
	public void prepareSave() {
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
	 * Prepares the edit page
	 * 
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		setUpHalfHourProjection();
		return SpmActionResult.EDIT.getResult();
	}

	/**
	 * Stores an employee on the DB
	 * 
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		/*
		 * try {
		 * 
		 * 
		 * } catch (SpmCheckedException e) {
		 * addActionError(e.getErrorMessage()); }
		 */
		 setResults();
		return SpmActionResult.INPUT.getResult();
	}

	/**
	 * 
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

	public void prepareReviseProjections() {
		pageSetup();
	}
	
	public String reviseProjections() {
		calculateProjections();
		return SpmActionResult.EDIT.getResult();
	}

	public String reviseUsedWeeks() {
		getNewValues();
		return SpmActionResult.EDIT.getResult();
		
	}
	
	private void getNewValues(){
		List<HalfHourProjection> projections = getProjectionService().getAvgHalfHourProjection(getUsedWeeks(), getTotalProjectedValues(), getEmployeeStore(), this.getFormatDate(getSelectedDate()));
		
		//set new values;
		for(int i=0; i < getProjectionElements().size(); i++) {
			getProjectionElements().get(i).setProjectedValue(projections.get(i).getAdjustedValue());
		}
		
		//if there is adjusted values calculate the revised projections
		if(getTotalAdjustedValues().intValue() > 0) {
			calculateProjections();
		}
	}
	
	private void calculateProjections() {
		BigDecimal totalOldProjectedValues = new BigDecimal(0);
		setTotalAdjustedValues(new BigDecimal(0));		
		List<BigDecimal> values = new ArrayList<BigDecimal>();
		for (HalfHourElement element : getProjectionElements()) {
			if (element.getAdjustedValue() != null) {
				setTotalAdjustedValues(getTotalAdjustedValues().add(element.getAdjustedValue()));
				totalOldProjectedValues = totalOldProjectedValues.add(element.getProjectedValue());
			}
			values.add(element.getProjectedValue());
		}

		values = getProjectionService().calculateFixedDistribution(
				getTotalProjectedValues(), getTotalAdjustedValues(), totalOldProjectedValues,
				values);

		
		setRevisedValues(values);

	}
	
	private void setRevisedValues(List<BigDecimal> values) {
		setTotalRevisedValues(new BigDecimal(0));
		for (int i = 0; i < values.size(); i++) {
			if (getProjectionElements().get(i).getAdjustedValue() == null) {
				getProjectionElements().get(i).setRevisedValue(values.get(i));
				setTotalRevisedValues(getTotalRevisedValues().add(values.get(i)));
			} else {
				getProjectionElements().get(i).setRevisedValue(
						getProjectionElements().get(i).getAdjustedValue());
				setTotalRevisedValues(getTotalRevisedValues().add(getProjectionElements().get(i).getAdjustedValue()));
			}
		}
	}

	private void setResults() {
		DailyProjection dailyProjection = getProjectionService().getDailyProjection(getEmployeeStore(), new Date(getSelectedDate()));
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

	private void setUpHalfHourProjection() {
		// Force object initialization
		setSelectedDate(getWeekDaySelector().getStringStartingWeekDay());

		DailyProjection dailyProjection = getProjectionService().getDailyProjection(getEmployeeStore(), getWeekDaySelector().getSelectedDay());

		if (dailyProjection != null) {
			setHalfHourElement(dailyProjection.getHalfHourProjections(), dailyProjection.getStartingTime());
		}
	}

	private void setHalfHourElement(List<HalfHourProjection> halfHourProjections, Date startTime){
		
		BigDecimal totalProjections = new BigDecimal(0);
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
