package com.laborguru.action.report;

import java.util.Date;

import com.laborguru.action.SpmAction;
import com.laborguru.action.SpmActionResult;
import com.laborguru.frontend.model.ReportTypes;
import com.laborguru.model.report.TotalManagerHour;
import com.laborguru.service.report.ReportService;
import com.laborguru.util.CalendarUtils;
import com.opensymphony.xwork2.Preparable;

/**
 *
 * @author <a href="fbarreraoro@gmail.com">Federico Barrera Oro</a>
 * @version 1.1
 * @since SPM 1.1
 *
 */
public class ReportCustomerPrepareAction extends SpmAction implements Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2748619782373332120L;
	
	private ReportService reportService;
	
	private TotalManagerHour totalManagerHours;
	private Date startDate;
	private Date endDate;
	private String selectView;
	
	
	private ReportTypes reportType;
	
	public void prepareShowFirstReport(){
		//starts 7 days ago and ends today
		setEndDate(CalendarUtils.todayWithoutTime());
		
		setStartDate(CalendarUtils.addOrSubstractDays(getEndDate(), -7));
		
	}
	
	public String showFirstReport() {
		return SpmActionResult.INPUT.getResult();
	}

	public String showReport() {

		switch(reportType) {
		case performanceEfficiency: //Performance Efficiency Report
									break;
		case schedulingEfficiency: //Scheduling Efficiency Report
									break;
		case scheduleExecutionEfficiency: //Schedule Execution Efficiency
									break;
		case forecastEfficiency: //forecast efficiency report
								break;
		default: setTotalManagerHours(null);
				 break;
		}
		
		return SpmActionResult.SHOW.getResult();
		
	}
	public String schedulingEfficiency() {
		System.out.println("I'm here");
		return SpmActionResult.INPUT.getResult();
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
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the reportType
	 */
	public ReportTypes getReportType() {
		return reportType;
	}

	/**
	 * @param reportType the reportType to set
	 */
	public void setReportType(ReportTypes reportType) {
		this.reportType = reportType;
	}

	/**
	 * @return the selectView
	 */
	public String getSelectView() {
		return selectView;
	}

	/**
	 * @param selectView the selectView to set
	 */
	public void setSelectView(String selectView) {
		this.selectView = selectView;
	}
	
	/**
	 * @return the totalManagerHours
	 */
	public TotalManagerHour getTotalManagerHours() {
		return totalManagerHours;
	}

	/**
	 * @param totalManagerHours the totalManagerHours to set
	 */
	public void setTotalManagerHours(TotalManagerHour totalManagerHours) {
		this.totalManagerHours = totalManagerHours;
	}

	public void prepare() throws Exception {
	}	
}
