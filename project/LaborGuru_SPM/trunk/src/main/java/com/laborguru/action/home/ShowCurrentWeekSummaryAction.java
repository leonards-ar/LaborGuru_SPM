/*
 * File name: ShowCurrentWeekSummaryAction.java
 * Creation date: 22/02/2009 10:52:15
 * Copyright Mindpool
 */
package com.laborguru.action.home;

import java.util.Date;

import com.laborguru.action.SpmActionResult;
import com.laborguru.frontend.model.PerformanceSummaryRow;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class ShowCurrentWeekSummaryAction extends EmployeeHomeSummaryBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8935610718218090851L;
	private PerformanceSummaryRow currentWeekSummary;
	
	/**
	 * 
	 */
	public ShowCurrentWeekSummaryAction() {
	}


	/**
	 * @return
	 * @throws Exception
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		Date startingWeekDay = getWeekDaySelector().getStartingWeekDay();
		
		getCurrentWeekSummary().setDate(startingWeekDay);
		getCurrentWeekSummary().setProjectedVolume(calculateVolume(getDailyProjectionsForWeekStartingOn(startingWeekDay)));
		getCurrentWeekSummary().setProjectedTarget(calculateProjectedTarget(getDailyStaffingForWeekStartingOn(startingWeekDay)));
		getCurrentWeekSummary().setProjectedScheduled(calculateProjectedScheduled(getScheduleForWeekStartingOn(startingWeekDay)));
		
		return SpmActionResult.SUCCESS.getResult();
	}


	/**
	 * @return the currentWeekSummary
	 */
	public PerformanceSummaryRow getCurrentWeekSummary() {
		if(currentWeekSummary == null) {
			setCurrentWeekSummary(new PerformanceSummaryRow());
		}
		return currentWeekSummary;
	}


	/**
	 * @param currentWeekSummary the currentWeekSummary to set
	 */
	public void setCurrentWeekSummary(PerformanceSummaryRow currentWeekSummary) {
		this.currentWeekSummary = currentWeekSummary;
	}	

	
}
