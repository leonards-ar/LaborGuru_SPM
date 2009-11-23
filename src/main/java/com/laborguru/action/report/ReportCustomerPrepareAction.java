package com.laborguru.action.report;

import java.util.Date;
import java.util.List;

import com.laborguru.action.SpmAction;
import com.laborguru.action.SpmActionResult;
import com.laborguru.frontend.model.ReportTypes;
import com.laborguru.model.report.TotalCustomerManagerHour;
import com.laborguru.service.report.ReportCustomerService;
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
	
	private ReportCustomerService reportCustomerService;
	
	private List<TotalCustomerManagerHour> totalManagerHours;
	private Date startDate;
	private Date endDate;
	private String selectView;
	
	private String scheduleHeader, targetHeader, reportTitle;
	
	public void prepareShowFirstReport(){
		//starts 7 days ago and ends today
		setEndDate(CalendarUtils.todayWithoutTime());
		
		setStartDate(CalendarUtils.addOrSubstractDays(getEndDate(), -7));
		
	}
	
	public String showFirstReport() {
		return SpmActionResult.INPUT.getResult();
	}

	public String showReport() {
		ReportTypes reportType = ReportTypes.performanceEfficiency;//ReportTypes.valueOf(getSelectView());
		
		switch(reportType) {
		case performanceEfficiency: setTotalManagerHours(reportCustomerService.getPerformanceEfficiencyReport(getCustomer(), getStartDate(), getEndDate()));
									setReportTitle("report.historicalcomparison.performanceEfficiency.title.label");
									setScheduleHeader("report.historicalComparison.performanceEfficiency.schedule.label");
									setTargetHeader("report.historicalComparison.performanceEfficiency.target.label");									
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
		
		return "show";
		
	}

	/**
	 * @return the reportService
	 */
	public ReportCustomerService getReportCustomerService() {
		return reportCustomerService;
	}

	/**
	 * @param reportService the reportService to set
	 */
	public void setReportCustomerService(ReportCustomerService reportCustomerService) {
		this.reportCustomerService = reportCustomerService;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		//return startDate;
		return CalendarUtils.addOrSubstractDays(new Date(), -7);
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
		//return endDate;
		return new Date();
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	public List<TotalCustomerManagerHour> getTotalManagerHours() {
		return totalManagerHours;
	}

	/**
	 * @param totalManagerHours the totalManagerHours to set
	 */
	public void setTotalManagerHours(List<TotalCustomerManagerHour> totalManagerHours) {
		this.totalManagerHours = totalManagerHours;
	}

	public ReportTypes[] getReportTypes() {
		return ReportTypes.values();
	}
		
	/**
	 * @return the scheduleHeader
	 */
	public String getScheduleHeader() {
		return scheduleHeader;
	}

	/**
	 * @param scheduleHeader the scheduleHeader to set
	 */
	public void setScheduleHeader(String scheduleHeader) {
		this.scheduleHeader = scheduleHeader;
	}

	/**
	 * @return the targetHeader
	 */
	public String getTargetHeader() {
		return targetHeader;
	}

	/**
	 * @param targetHeader the targetHeader to set
	 */
	public void setTargetHeader(String targetHeader) {
		this.targetHeader = targetHeader;
	}

	/**
	 * @return the reportTitle
	 */
	public String getReportTitle() {
		return reportTitle;
	}

	/**
	 * @param reportTitle the reportTitle to set
	 */
	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}

	public void prepare() throws Exception {
	}	
}
