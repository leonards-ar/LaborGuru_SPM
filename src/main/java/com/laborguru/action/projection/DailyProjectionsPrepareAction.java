package com.laborguru.action.projection;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.laborguru.action.SpmActionResult;
import com.laborguru.service.data.ReferenceDataService;
import com.laborguru.service.projection.ProjectionService;
import com.opensymphony.xwork2.Preparable;

/**
 * This action deals with Daily Projections.
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
@SuppressWarnings("serial")
public class DailyProjectionsPrepareAction extends ProjectionCalendarBaseAction implements Preparable {

	private ProjectionService projectionService;
	private ReferenceDataService referenceDataService;

	private List<BigDecimal> calculatedProjections = new ArrayList<BigDecimal>(7);
	private List<BigDecimal> adjustedProjections = new ArrayList<BigDecimal>(7);
	private Map<Integer, String> usedWeeksMap;

	private Integer usedWeeks;

	private BigDecimal totalProjected = new BigDecimal("0");
	
	/**
	 * Prepare the data to be used on the edit page
	 * @throws Exception
	 */
	public void prepareEdit(){
				
		if (getUsedWeeks() == null || getUsedWeeks() == 0) 
			setUsedWeeks(4);
		
		setUsedWeeksMap(referenceDataService.getUsedWeeks());
	}
	

	/**
	 * Prepare data to be used in the actions methods defined for this action
	 * @throws Exception
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	public void prepare() throws Exception {
		//It's needed by the Preparable interface, don't comment out or removed
	}

	
	/**
	 * Prepares the edit page
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		// Force object initialization
		getWeekDaySelector();
		
		//Get calculated projections
		setCalculatedProjections(projectionService.getAvgDailyProjectionForAWeek(getUsedWeeks(), this.getEmployeeStore(), getWeekDaySelector().getStartingWeekDay()));
		setAdjustedProjections(projectionService.getAdjustedDailyProjectionForAWeek(this.getEmployeeStore(), getWeekDaySelector().getStartingWeekDay()));
				
		//calculate and set the total
		for (BigDecimal aValue: getCalculatedProjections()){
			this.totalProjected = totalProjected.add(aValue);
		}
		
		return SpmActionResult.EDIT.getResult();
	}


	
	/**
	 * Stores an employee on the DB
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {		
		/*
		try {


		} catch (SpmCheckedException e) {
			addActionError(e.getErrorMessage());
		}
		*/
		return SpmActionResult.INPUT.getResult();
	}

	/**
	 * @return the calculatedProjections
	 */
	public List<BigDecimal> getCalculatedProjections() {
		return calculatedProjections;
	}

	/**
	 * @param calculatedProjections the calculatedProjections to set
	 */
	public void setCalculatedProjections(List<BigDecimal> calculatedProjections) {
		this.calculatedProjections = calculatedProjections;
	}

	/**
	 * @return the usedWeeks
	 */
	public Integer getUsedWeeks() {
		return usedWeeks;
	}

	/**
	 * @param usedWeeks the usedWeeks to set
	 */
	public void setUsedWeeks(Integer usedWeeks) {
		this.usedWeeks = usedWeeks;
	}


	/**
	 * @return the totalProjected
	 */
	public BigDecimal getTotalProjected() {
		return totalProjected;
	}


	/**
	 * @param totalProjected the totalProjected to set
	 */
	public void setTotalProjected(BigDecimal totalProjected) {
		this.totalProjected = totalProjected;
	}


	/**
	 * @return the projectionService
	 */
	public ProjectionService getProjectionService() {
		return projectionService;
	}


	/**
	 * @param projectionService the projectionService to set
	 */
	public void setProjectionService(ProjectionService projectionService) {
		this.projectionService = projectionService;
	}


	/**
	 * @return the adjustedProjections
	 */
	public List<BigDecimal> getAdjustedProjections() {
		return adjustedProjections;
	}


	/**
	 * @param adjustedProjections the adjustedProjections to set
	 */
	public void setAdjustedProjections(List<BigDecimal> adjustedProjections) {
		this.adjustedProjections = adjustedProjections;
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


	/**
	 * @return the usedWeeksMap
	 */
	public Map<Integer, String> getUsedWeeksMap() {
		return usedWeeksMap;
	}


	/**
	 * @param usedWeeksMap the usedWeeksMap to set
	 */
	public void setUsedWeeksMap(Map<Integer, String> usedWeeksMap) {
		this.usedWeeksMap = usedWeeksMap;
	}
}
