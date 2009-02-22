package com.laborguru.action.report;

import java.math.BigDecimal;
import java.util.List;

import com.laborguru.action.SpmActionResult;
import com.laborguru.model.report.TotalHour;
import com.laborguru.service.position.PositionService;
import com.laborguru.util.SpmConstants;
import com.opensymphony.xwork2.Preparable;

public abstract class WeeklyReportBaseAction extends ScheduleReportPrepareAction implements Preparable{

	private List<TotalHour> totalHours;
	private Integer positionId;

	private PositionService positionService;

	private BigDecimal totalSchedule = SpmConstants.BD_ZERO_VALUE;
	private BigDecimal totalTarget = SpmConstants.BD_ZERO_VALUE;
	private BigDecimal totalDifference = SpmConstants.BD_ZERO_VALUE;
	private BigDecimal totalPercentaje = SpmConstants.BD_ZERO_VALUE;
	private BigDecimal totalSales = SpmConstants.BD_ZERO_VALUE;
	
	private String xmlValues;

	/**
	 * @throws Exception
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	public void prepare() throws Exception {
	}

	public void prepareShowReport(){
		pageSetup();
	}

	public void prepareShowFirstReport() {
		pageSetup();
	}

	public String showFirstReport() {
		initWeekSelectorValues();
		return SpmActionResult.INPUT.getResult();
	}

	public String showReport() {
		initWeekSelectorValues();
		if (getItemId() == null) {
			getReport();
			
		} else {
			if ("byPosition".equals(getSelectedGrouping())) {
				getReportByPosition();
			} else if ("byService".equals(getSelectedGrouping())) {
				getReportByService();
			} else {
				getReport();
			}
		}
		calculateTotals();
		generateXmlGraph();
		return SpmActionResult.SHOW.getResult();
	}

	private void calculateTotals() {
		for (TotalHour th : getTotalHours()) {
			setTotalSales(getTotalSales().add(th.getSales()));
			setTotalSchedule(getTotalSchedule().add(th.getSchedule()));
			setTotalTarget(getTotalTarget().add(th.getTarget()));
			setTotalDifference(getTotalDifference().add(th.getDifference()));
		}
		// totalDifference/totalTarget * 100
		if (getTotalTarget().compareTo(SpmConstants.BD_ZERO_VALUE) == 0) {
			setTotalPercentaje(SpmConstants.BD_ZERO_VALUE);
		} else {
			setTotalPercentaje(getTotalDifference().divide(getTotalTarget(), 2,
					SpmConstants.ROUNDING_MODE).multiply(new BigDecimal("100"))
					.abs());
		}
	}
	
	private void generateXmlGraph(){
		setXmlValues(getFusionXmlDataConverter().weeklyTotalHoursXmlConverter(
				getTotalHours(), getTexts("reportmessages")));
	}
	
	protected abstract void getReport();
	protected abstract void getReportByPosition();
	protected abstract void getReportByService();

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
	 * @return the totalPercentaje
	 */
	public BigDecimal getTotalPercentaje() {
		return totalPercentaje;
	}

	/**
	 * @param totalPercentaje the totalPercentaje to set
	 */
	public void setTotalPercentaje(BigDecimal totalPercentaje) {
		this.totalPercentaje = totalPercentaje;
	}

	/**
	 * @return the totalSales
	 */
	public BigDecimal getTotalSales() {
		return totalSales;
	}

	/**
	 * @param totalSales the totalSales to set
	 */
	public void setTotalSales(BigDecimal totalSales) {
		this.totalSales = totalSales;
	}

	/**
	 * @return the xmlValues
	 */
	public String getXmlValues() {
		return xmlValues;
	}

	/**
	 * @param xmlValues the xmlValues to set
	 */
	public void setXmlValues(String xmlValues) {
		this.xmlValues = xmlValues;
	}

}
