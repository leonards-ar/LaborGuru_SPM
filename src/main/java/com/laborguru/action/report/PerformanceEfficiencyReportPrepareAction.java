package com.laborguru.action.report;



public class PerformanceEfficiencyReportPrepareAction extends WeeklyReportBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7643112299027218254L;

	protected void getReport() {
		setTotalHours(getReportService().getPerformanceEfficiencyReport(
				getEmployeeStore(), getWeekDaySelector().getStartingWeekDay()));		
	}

	protected void getReportByPosition() {
		//Not Implemented
	}

	protected void getReportByService() {
		//Not Implemented
	}
	
}
