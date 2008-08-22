package com.laborguru.action.projection;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
public class HalfHourProjectionsPrepareAction extends
		ProjectionCalendarBaseAction implements Preparable {

	private List<HalfHourElement> projectionElements;
	private BigDecimal sumProjectedValues = new BigDecimal(0);
	private BigDecimal sumAdjustedValues = new BigDecimal(0);
	private BigDecimal sumRevisedValues = new BigDecimal(0);

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
		try{
		getWeekDaySelector().setSelectedDay(new SimpleDateFormat("yyyy-MM-dd").parse("2008-08-20"));
		} catch(Exception e) {
			
		}
		
		//setSelectedDate(getWeekDaySelector().getStringStartingWeekDay());
		
		DailyProjection dailyProjection = getProjectionService().getDailyProjection(getEmployeeStore(),
						getWeekDaySelector().getSelectedDay());

		if (dailyProjection != null) {
			BigDecimal sumProjections = new BigDecimal(0);
			for (HalfHourProjection halfHourProjection : dailyProjection.getHalfHourProjections()) {
				HalfHourElement element = new HalfHourElement();
				element.setHour(getTime(dailyProjection.getStartingTime(), halfHourProjection.getIndex()));
				element.setProjectedValue(halfHourProjection.getAdjustedValue());
				element.setRevisedValue(new BigDecimal(0));
				this.getProjectionElements().add(element);
				sumProjections = sumProjections.add(halfHourProjection.getAdjustedValue());
			}
			setSumProjectedValues(sumProjections);
		}
	}

	private String getTime(Date startTime, Integer elementIndex) {
		DateTime datetime = new DateTime(startTime);
		return datetime.plusMinutes((30 * elementIndex.intValue())).toString(
				"HH:mm");
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
	 * @return the sumProjectedValues
	 */
	public BigDecimal getSumProjectedValues() {
		return sumProjectedValues;
	}

	/**
	 * @param sumProjectedValues
	 *            the sumProjectedValues to set
	 */
	public void setSumProjectedValues(BigDecimal sumProjectedValues) {
		this.sumProjectedValues = sumProjectedValues;
	}

	/**
	 * @return the sumAdjustedValues
	 */
	public BigDecimal getSumAdjustedValues() {
		return sumAdjustedValues;
	}

	/**
	 * @param sumAdjustedValues
	 *            the sumAdjustedValues to set
	 */
	public void setSumAdjustedValues(BigDecimal sumAdjustedValues) {
		this.sumAdjustedValues = sumAdjustedValues;
	}

	/**
	 * @return the sumRevisedValues
	 */
	public BigDecimal getSumRevisedValues() {
		return sumRevisedValues;
	}

	/**
	 * @param sumRevisedValues
	 *            the sumRevisedValues to set
	 */
	public void setSumRevisedValues(BigDecimal sumRevisedValues) {
		this.sumRevisedValues = sumRevisedValues;
	}

}
