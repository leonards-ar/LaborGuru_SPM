package com.laborguru.action.report;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
	
	public String weeklyReport() {
		//String selectedDate = super.getSelectedWeekDay();
		
		setTotalHours(getReportService().getWeeklyTotalHours(super.getEmployeeStore(), getWeekDaySelector().getStartingWeekDay()));
		calculateTotals();
		return SpmActionResult.INPUT.getResult();
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
	
	public String getXmlValues() {
		//return "<graph caption=\"Total Schedule Assessment\" PYAxisName=\"Schedule\" SYAxisName=\"Target\" showvalues=\"0\" numDivLines=\"4\" formatNumberScale=\"0\" decimalPrecision=\"0\" anchorSides=\"10\" anchorRadius=\"3\" anchorBorderColor=\"009900\"><categories><category name=\"Mon\" /><category name=\"Tue\" /><category name=\"Wed\" /><category name=\"Thu\" /><category name=\"Fri\" /><category name=\"Sat\" /><category name=\"Sun\" /></categories><dataset seriesName=\"Schedule\" color=\"AFD8F8\" showValues=\"0\"><set value=\"172\" /><set value=\"189\" /><set value=\"216\" /><set value=\"190\" /><set value=\"236\" /><set value=\"265\" /><set value=\"215\" /></dataset><dataset seriesName=\"Target\" color=\"8BBA00\" showValues=\"0\" parentYAxis=\"S\"><set value=\"156\" /><set value=\"179\" /><set value=\"183\" /><set value=\"168\" /><set value=\"205\" /><set value=\"250\" /><set value=\"211\" /></dataset></graph>";
		return "<graph caption=\"Total Hours (Weekly)\" PYAxisName=\"Hours\" SYAxisName=\"Target\" showvalues=\"1\" numDivLines=\"4\" formatNumberScale=\"0\" decimalPrecision=\"0\" anchorSides=\"10\" anchorRadius=\"3\" anchorBorderColor=\"009900\"><categories><category name=\"Mon\"/><category name=\"Tue\" /><category name=\"Wed\" /><category name=\"Thu\" /><category name=\"Fri\" /><category name=\"Sat\" /><category name=\"Sun\" /></categories><dataset seriesName=\"Schedule\" color=\"8BBA00\" showValues=\"0\"><set value=\"172\" link=\"http://www.google.com.ar\"/><set value=\"189\" link=\"http://www.google.com.ar\"/><set value=\"216\" link=\"http://www.google.com.ar\"/><set value=\"190\" link=\"http://www.google.com.ar\"/><set value=\"236\" link=\"http://www.google.com.ar\"/><set value=\"265\" link=\"http://www.google.com.ar\"/><set value=\"215\" link=\"http://www.google.com.ar\"/></dataset><dataset seriesName=\"Target\" color=\"666666\" showValues=\"0\" parentYAxis=\"S\"><set value=\"156\" /><set value=\"179\" /><set value=\"183\" /><set value=\"168\" /><set value=\"205\" /><set value=\"250\" /><set value=\"211\" /></dataset></graph>";
	}
	

}
