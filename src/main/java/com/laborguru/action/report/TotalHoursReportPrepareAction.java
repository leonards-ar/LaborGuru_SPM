package com.laborguru.action.report;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.laborguru.action.SpmActionResult;
import com.laborguru.model.report.TotalHour;
import com.laborguru.service.report.ReportService;
import com.laborguru.util.FusionXmlDataConverter;
import com.opensymphony.xwork2.Preparable;

public class TotalHoursReportPrepareAction extends ScheduleReportPrepareAction implements Preparable{

	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_VIEW="total";
	private static final String DEFAULT_PERIOD = "weekly";
	
	private List<TotalHour> totalHours;
	
	private ReportService reportService;
	private FusionXmlDataConverter fusionXmlDataConverter;

	private BigDecimal totalSchedule = new BigDecimal("0");
	private BigDecimal totalTarget = new BigDecimal("0");
	private BigDecimal totalDifference = new BigDecimal("0");
	private BigDecimal totalPercentaje = new BigDecimal("0");
	
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
	
	public void prepareChangeWeek(){
		pageSetup();
	}
	
	public String weeklyReport() {
		getReport();
		loadCalendarData();
		return SpmActionResult.INPUT.getResult();
	}
	
	protected void processChangeWeek() {
		getWeekDaySelector().setStringSelectedDay(getSelectedDate());
		getReport();
	}
	
	private void getReport() {
		setTotalHours(getReportService().getWeeklyTotalHours(getEmployeeStore(), getWeekDaySelector().getStartingWeekDay()));
		calculateTotals();
	}
	
	private void calculateTotals(){

		for(TotalHour th: getTotalHours()) {
			setTotalSchedule(getTotalSchedule().add(th.getSchedule()));
			setTotalTarget(getTotalTarget().add(th.getTarget()));
			setTotalDifference(getTotalDifference().add(th.getDifference()));
		}
		// totalDifference/totalTarget * 100
		setTotalPercentaje(getTotalDifference().divide(getTotalTarget(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")));
	}
	
	protected void pageSetup() {
		super.pageSetup();
		if(getSelectView() == null) {
			setSelectView(DEFAULT_VIEW);
		}
		
		if(getPeriod() == null) {
			setPeriod(DEFAULT_PERIOD);
		}
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

	/**
	 * @return the totalSchedule
	 */
	public BigDecimal getTotalSchedule() {
		return totalSchedule;
	}

	/**
	 * @param totalSchedule the totalSchedule to set
	 */
	public void setTotalSchedule(BigDecimal totalSchedule) {
		this.totalSchedule = totalSchedule;
	}

	/**
	 * @return the totalTarget
	 */
	public BigDecimal getTotalTarget() {
		return totalTarget;
	}

	/**
	 * @param totalTarget the totalTarget to set
	 */
	public void setTotalTarget(BigDecimal totalTarget) {
		this.totalTarget = totalTarget;
	}

	/**
	 * @return the totalDifference
	 */
	public BigDecimal getTotalDifference() {
		return totalDifference;
	}

	/**
	 * @param totalDifference the totalDifference to set
	 */
	public void setTotalDifference(BigDecimal totalDifference) {
		this.totalDifference = totalDifference;
	}

	/**
	 * @return the totalPorcentaje
	 */
	public BigDecimal getTotalPercentaje() {
		return totalPercentaje;
	}

	/**
	 * @param totalPorcentaje the totalPorcentaje to set
	 */
	public void setTotalPercentaje(BigDecimal totalPorcentaje) {
		this.totalPercentaje = totalPorcentaje;
	}
	
	/**
	 * Gets the data to display in the graphic chart.
	 * @return
	 */
	public String getXmlValues() {
		return getFusionXmlDataConverter().weeklyTotalHoursXmlConverter(getTotalHours());
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

	

	

}
