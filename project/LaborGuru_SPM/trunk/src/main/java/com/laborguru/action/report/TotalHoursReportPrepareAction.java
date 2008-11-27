package com.laborguru.action.report;

import java.util.Date;
import java.util.List;

import com.laborguru.action.SpmActionResult;
import com.laborguru.model.TotalHour;
import com.laborguru.service.report.ReportService;
import com.opensymphony.xwork2.Preparable;

public class TotalHoursReportPrepareAction extends ScheduleReportPrepareAction implements Preparable{

	private static final long serialVersionUID = 1L;

	private List<TotalHour> totalHours;
	
	private ReportService reportService;

	private final String actionName = "totalHoursReport";
	
	/**
	 * @throws Exception
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	public void prepare() throws Exception {
	}
	
	public void prepareWeeklyReport() throws Exception {
		pageSetup();
	}
	
	public String weeklyReport() {
		//String selectedDate = super.getSelectedWeekDay();
		
		setTotalHours(reportService.getWeeklyTotalHours(super.getEmployeeStore(), new Date()));
		return SpmActionResult.INPUT.getResult();
	}
	
	/**
	 * @return the totalHours
	 */
	public List<TotalHour> getTotalHours() {
		return totalHours;
	}

	/**
	 * @param totalHours the totalHours to set
	 */
	public void setTotalHours(List<TotalHour> totalHours) {
		this.totalHours = totalHours;
	}
	/**
	 * @return the reportService
	 */
	public ReportService getReportService() {
		return reportService;
	}
	/**
	 * @param reportService the reportService to set
	 */
	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	/**
	 * @return the actionName
	 */
	public String getActionName() {
		return actionName;
	}

	/**
	 * @param actionName the actionName to set
	 */
	public void setActionName(String actionName) {
		//Not implemented
	}

}
