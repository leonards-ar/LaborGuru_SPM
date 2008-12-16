package com.laborguru.action.report;

import java.math.BigDecimal;
import java.util.List;

import com.laborguru.action.SpmActionResult;
import com.laborguru.model.Position;
import com.laborguru.model.report.TotalHour;
import com.laborguru.service.position.PositionService;
import com.laborguru.service.report.ReportService;
import com.laborguru.util.FusionXmlDataConverter;
import com.laborguru.util.SpmConstants;
import com.opensymphony.xwork2.Preparable;

public class TotalHoursReportPrepareAction extends ScheduleReportPrepareAction implements Preparable{

	private static final long serialVersionUID = 1L;
	
	private List<TotalHour> totalHours;
	private List<Position> positionList;
	private Integer positionId;
	
	
	private ReportService reportService;
	private PositionService positionService;
	private FusionXmlDataConverter fusionXmlDataConverter;

	private BigDecimal totalSchedule = SpmConstants.BD_ZERO_VALUE;
	private BigDecimal totalTarget = SpmConstants.BD_ZERO_VALUE;
	private BigDecimal totalDifference = SpmConstants.BD_ZERO_VALUE;
	private BigDecimal totalPercentaje = SpmConstants.BD_ZERO_VALUE;
	
	private final String actionName = "totalHoursReport";
	
	/**
	 * @throws Exception
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	public void prepare() throws Exception {
	}
	
	public void prepareShowReport() throws Exception {
		pageSetup();
	}
	
	public void prepareChangeWeek(){
		pageSetup();
	}
	
	public void prepareShowFirstReport() {
		pageSetup();
	}
	
	public String showFirstReport(){
		setSelectView("totalHoursReport_changeWeek.action");
		setPeriod("weekly");
		
		return SpmActionResult.INPUT.getResult();
	}
	
	public String showReport() {
		initWeekSelectorValues();
		if(getSelectView().equals("totalHoursReport_changeWeek.action")) {
			return weeklyReport();
		} 
	
		return weeklyReportByPosition();
	}
	
	public String weeklyReport(){
		getWeeklyReport();
		//loadCalendarData();
		return SpmActionResult.SHOW_WEEKLY_TOTAL.getResult();
	}
	
	public String weeklyReportByPosition(){
		setPositionList(getPositionService().getPositionsByStore(getEmployeeStore()));
		return SpmActionResult.SHOW_WEEKLY_TOTAL_BY_POSITION.getResult();
	}
	
	public String showWeeklyReportByPosition() {
		initWeekSelectorValues();
		getWeeklyReportByPosition();
		return SpmActionResult.SHOW.getResult();
	}
	
	protected void processChangeWeek() {
		getWeekDaySelector().setStringSelectedDay(getSelectedDate());
	}
	
	private void getWeeklyReport() {
		setTotalHours(getReportService().getWeeklyTotalHours(getEmployeeStore(), getWeekDaySelector().getStartingWeekDay()));
		calculateTotals();
	}
	
	private void getWeeklyReportByPosition() {
		Position position = new Position();
		//System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!estoy para traer esto y el id es: " + getPositionId());
		position.setId(getPositionId());
		
		setTotalHours(getReportService().getWeeklyTotalHoursByPosition(getEmployeeStore(), position, getWeekDaySelector().getStartingWeekDay()));
		calculateTotals();
	}
	
	private void calculateTotals(){

		for(TotalHour th: getTotalHours()) {
			setTotalSchedule(getTotalSchedule().add(th.getSchedule()));
			setTotalTarget(getTotalTarget().add(th.getTarget()));
			setTotalDifference(getTotalDifference().add(th.getDifference()));
		}
		// totalDifference/totalTarget * 100
		if(getTotalTarget().compareTo(SpmConstants.BD_ZERO_VALUE) == 0) {
			setTotalPercentaje(SpmConstants.BD_ZERO_VALUE);
		} else {
			setTotalPercentaje(getTotalDifference().divide(getTotalTarget(), 2, SpmConstants.ROUNDING_MODE).multiply(new BigDecimal("100")).abs());
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
	 * @return the positionId
	 */
	public Integer getPositionId() {
		return positionId;
	}

	/**
	 * @param positionId the positionId to set
	 */
	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
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

	/**
	 * @return the positionService
	 */
	public PositionService getPositionService() {
		return positionService;
	}

	/**
	 * @param positionService the positionService to set
	 */
	public void setPositionService(PositionService positionService) {
		this.positionService = positionService;
	}

	/**
	 * @return the positionList
	 */
	public List<Position> getPositionList() {
		return positionList;
	}

	/**
	 * @param positionList the positionList to set
	 */
	public void setPositionList(List<Position> positionList) {
		this.positionList = positionList;
	}

}
