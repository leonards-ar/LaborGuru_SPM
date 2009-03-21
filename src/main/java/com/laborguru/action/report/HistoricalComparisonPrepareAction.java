package com.laborguru.action.report;

import java.util.Date;
import java.util.List;

import com.laborguru.action.SpmAction;
import com.laborguru.action.SpmActionResult;
import com.laborguru.model.report.TotalHour;
import com.laborguru.service.report.ReportService;
import com.laborguru.util.CalendarUtils;
import com.laborguru.util.FusionXmlDataConverter;
import com.opensymphony.xwork2.Preparable;

/**
 *
 * @author <a href="fbarreraoro">Federico Barrera Oro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class HistoricalComparisonPrepareAction extends SpmAction implements Preparable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5732711967413965288L;

	private enum ReportTypes {
		performanceEfficiency ("report.historicalComparison.performanceEfficiency.title", 0),
		schedulingEfficiency("report.historicalComparison.scheduleEfficiency.title", 1),
		scheduleExecutionEfficiency("report.historicalComparison.scheduleExecutionEfficiency.title", 2),
		forecastEfficiency("report.historicalComparison.forecastEfficiency.title", 3);
		
		private String name;
		private Integer index;
		
		private ReportTypes(String name, Integer index) {
			this.name = name;
			this.index = index;
		}
		
		public String getName(){
			return this.name;
		}
		
		public Integer getIndex(){
			return this.index;
		}
		
		public static ReportTypes getReportTypeByIndex(int index) {
			for(ReportTypes reportType: values()){
				if(reportType.getIndex().intValue() == index ) {
					return reportType;
				}
			}
			return null;
		}
		
		public String toString() {
			return this.getName();
		}
		
	};
	
	Integer index;
	Date startDate;
	Date endDate;
	
	List<TotalHour> totalHours;
	String scheduleHeader, targetHeader, reportTitle; 
	
	String xmlValues;
	
	ReportService reportService;
	FusionXmlDataConverter fusionXmlDataConverter;

	public void prepareShowFirstReport(){
		//starts 4 weeks ago and ends today
		setEndDate(CalendarUtils.todayWithoutTime());
		
		setStartDate(CalendarUtils.addOrSubstractDays(getEndDate(), -27));
	}
	
	public String showFirstReport() {
		return SpmActionResult.INPUT.getResult();
	}
	
	public String showReport() {
		ReportTypes reportType = ReportTypes.getReportTypeByIndex(getIndex());
		switch(reportType) {
		case performanceEfficiency: setTotalHours(getReportService().getPerformanceEfficiencyReport(getEmployeeStore(), getStartDate(), getEndDate(), false));
									setReportTitle("report.historicalcomparison.performanceEfficiency.title.label");
									setScheduleHeader("report.historicalComparison.performanceEfficiency.schedule.label");
									setTargetHeader("report.historicalComparison.performanceEfficiency.target.label");
									break;
		case schedulingEfficiency: setTotalHours(getReportService().getWeeklyTotalHours(getEmployeeStore(), getStartDate(), getEndDate(), false));
									setReportTitle("report.historicalcomparison.scheduleEfficiency.title.label");
									setScheduleHeader("report.historicalComparison.scheduleEfficiency.schedule.label");
									setTargetHeader("report.historicalComparison.scheduleEfficiency.target.label");
									break;
		case scheduleExecutionEfficiency: setTotalHours(getReportService().getScheduleExecutionEfficiencyReport(getEmployeeStore(), getStartDate(), getEndDate(), false));
									setReportTitle("report.historicalcomparison.performanceEfficiency.title.label");
									setScheduleHeader("report.historicalComparison.performanceEfficiency.schedule.label");
									setTargetHeader("report.historicalComparison.scheduleExecutionEfficiency.target.label");
									break;
		case forecastEfficiency: setTotalHours(getReportService().getForecastEfficiencyReport(getEmployeeStore(), getStartDate(), getEndDate()));
								setReportTitle("report.historicalcomparison.forecastEfficiency.title.label");
								setScheduleHeader("report.historicalComparison.forecastEfficiency.schedule.label");
								setTargetHeader("report.historicalComparison.forecastEfficiency.target.label");
								break;
		default: setTotalHours(null);
				 break;
		}
		
		generateXmlGraph();
		return SpmActionResult.SHOW.getResult();
	}
	
	private void generateXmlGraph(){
		
		setXmlValues(getFusionXmlDataConverter().historicalComparisonXmlConverter(getTotalHours(), getTexts("defaultmessages"), getScheduleHeader(), getTargetHeader(), getReportTitle()));
	}
	
	public ReportTypes[] getReportTypes(){
		return ReportTypes.values();
	}
	

	/**
	 * @return the index
	 */
	public Integer getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(Integer index) {
		this.index = index;
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
	 * @return the xmlValues
	 */
	public String getXmlValues() {
		return xmlValues;
	}

	/**
	 * @return the fusionXmlDataConverter
	 */
	public FusionXmlDataConverter getFusionXmlDataConverter() {
		return fusionXmlDataConverter;
	}

	/**
	 * @param fusionXmlDataConverter the fusionXmlDataConverter to set
	 */
	public void setFusionXmlDataConverter(
			FusionXmlDataConverter fusionXmlDataConverter) {
		this.fusionXmlDataConverter = fusionXmlDataConverter;
	}

	/**
	 * @param xmlValues the xmlValues to set
	 */
	public void setXmlValues(String xmlValues) {
		this.xmlValues = xmlValues;
	}

	public void prepare() throws Exception {
	}
	
}

