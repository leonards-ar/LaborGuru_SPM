package com.laborguru.action.report;

import java.math.BigDecimal;
import java.util.List;

import com.laborguru.action.SpmActionResult;
import com.laborguru.model.report.TotalHour;
import com.laborguru.service.position.PositionService;
import com.laborguru.util.NumberUtils;
import com.laborguru.util.SpmConstants;
import com.opensymphony.xwork2.Preparable;

@SuppressWarnings("serial")
public abstract class WeeklyReportBaseAction extends ScheduleReportPrepareAction implements Preparable{

	private List<TotalHour> totalHours;
	private Integer positionId;

	private PositionService positionService;

	private BigDecimal totalSchedule = SpmConstants.BD_ZERO_VALUE;
	private BigDecimal totalTarget = SpmConstants.BD_ZERO_VALUE;
	private BigDecimal totalDifference = SpmConstants.BD_ZERO_VALUE;
	private BigDecimal totalPercentage = SpmConstants.BD_ZERO_VALUE;
	private BigDecimal totalSales = SpmConstants.BD_ZERO_VALUE;
	// Extended totals
	private BigDecimal totalVplhSchedule = SpmConstants.BD_ZERO_VALUE;
	private BigDecimal totalVplhTarget = SpmConstants.BD_ZERO_VALUE;
	private BigDecimal totalProjectedSales = SpmConstants.BD_ZERO_VALUE;
	private BigDecimal totalScheduleLaborPercentage = SpmConstants.BD_ZERO_VALUE;
	private BigDecimal totalTargetLaborPercentage = SpmConstants.BD_ZERO_VALUE;
	// End of extended totals
	
	private String scheduleAxisName;
	private String targetAxisName;
	
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
		setAxisLabels();
		generateXmlGraph();
		return SpmActionResult.SHOW.getResult();
	}

	private void calculateTotals() {
		double totalWage = 0.0;
		for (TotalHour th : getTotalHours()) {
			setTotalSales(getTotalSales().add(th.getSales()));
			setTotalSchedule(getTotalSchedule().add(th.getSchedule()));
			setTotalTarget(getTotalTarget().add(th.getTarget()));
			setTotalDifference(getTotalDifference().add(th.getDifference()));
			totalWage += NumberUtils.getDoubleValue(th.getStoreTotalWage());
		}
		// totalDifference/totalTarget
		if (getTotalTarget().compareTo(SpmConstants.BD_ZERO_VALUE) == 0) {
			setTotalPercentage(SpmConstants.BD_ZERO_VALUE);
		} else {
			setTotalPercentage(getTotalDifference().divide(getTotalTarget(), 2,
					SpmConstants.ROUNDING_MODE).multiply(new BigDecimal(100)));
		}
		
		if(calculateExtendedTotals()) {
			if(getTotalSchedule().compareTo(SpmConstants.BD_ZERO_VALUE) != 0) {
				setTotalVplhSchedule(getTotalSales().divide(getTotalSchedule(), 2, SpmConstants.ROUNDING_MODE));
			}

			if(getTotalTarget().compareTo(SpmConstants.BD_ZERO_VALUE) != 0) {
				setTotalVplhTarget(getTotalSales().divide(getTotalTarget(), 2, SpmConstants.ROUNDING_MODE));
			}
			
			if(getTotalSales() != null && getEmployeeStore() != null) {
				setTotalProjectedSales(getTotalSales().multiply(new BigDecimal(NumberUtils.getDoubleValue(getEmployeeStore().getAverageVariable()))));
			}
			double averageWage = 0.0;
			if(getTotalSchedule().compareTo(SpmConstants.BD_ZERO_VALUE) != 0 && getTotalProjectedSales().compareTo(SpmConstants.BD_ZERO_VALUE) != 0) {
				averageWage = totalWage / NumberUtils.getDoubleValue(getTotalSchedule());
				
				setTotalScheduleLaborPercentage(getTotalSchedule().multiply(new BigDecimal(averageWage)).divide(getTotalProjectedSales(), 2, SpmConstants.ROUNDING_MODE));
				setTotalTargetLaborPercentage(getTotalTarget().multiply(new BigDecimal(averageWage)).divide(getTotalProjectedSales(), 2, SpmConstants.ROUNDING_MODE));
			}
			
		}
	}
	
	private void generateXmlGraph(){
		setXmlValues(getFusionXmlDataConverter().weeklyTotalHoursXmlConverter(
				getTotalHours(), getScheduleAxisName(), getTargetAxisName(), getTexts("defaultmessages")));
	}
	
	protected abstract void getReport();
	protected abstract void getReportByPosition();
	protected abstract void getReportByService();
	protected abstract void setAxisLabels();

	/**
	 * Weather the following totals should be calculated:
	 * totalVplhSchedule, totalVplhTarget, totalProjectedSales, totalScheduleLaborPercentage, totalTargetLaborPercentage
	 * @return
	 */
	protected boolean calculateExtendedTotals() {
		return false;
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
	 * @return the totalpercentage
	 */
	public BigDecimal getTotalPercentage() {
		return totalPercentage;
	}

	/**
	 * @param totalpercentage the totalpercentage to set
	 */
	public void setTotalPercentage(BigDecimal totalPercentage) {
		this.totalPercentage = totalPercentage;
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

	/**
	 * @return the scheduleAxisName
	 */
	public String getScheduleAxisName() {
		return scheduleAxisName;
	}

	/**
	 * @param scheduleAxisName the scheduleAxisName to set
	 */
	public void setScheduleAxisName(String scheduleAxisName) {
		this.scheduleAxisName = scheduleAxisName;
	}

	/**
	 * @return the targetAxisName
	 */
	public String getTargetAxisName() {
		return targetAxisName;
	}

	/**
	 * @param targetAxisName the targetAxisName to set
	 */
	public void setTargetAxisName(String targetAxisName) {
		this.targetAxisName = targetAxisName;
	}

	public BigDecimal getTotalVplhSchedule() {
		return totalVplhSchedule;
	}

	public void setTotalVplhSchedule(BigDecimal totalVplhSchedule) {
		this.totalVplhSchedule = totalVplhSchedule;
	}

	public BigDecimal getTotalVplhTarget() {
		return totalVplhTarget;
	}

	public void setTotalVplhTarget(BigDecimal totalVplhTarget) {
		this.totalVplhTarget = totalVplhTarget;
	}

	public BigDecimal getTotalProjectedSales() {
		return totalProjectedSales;
	}

	public void setTotalProjectedSales(BigDecimal totalProjectedSales) {
		this.totalProjectedSales = totalProjectedSales;
	}

	public BigDecimal getTotalScheduleLaborPercentage() {
		return totalScheduleLaborPercentage;
	}

	public void setTotalScheduleLaborPercentage(
			BigDecimal totalScheduleLaborPercentage) {
		this.totalScheduleLaborPercentage = totalScheduleLaborPercentage;
	}

	public BigDecimal getTotalTargetLaborPercentage() {
		return totalTargetLaborPercentage;
	}

	public void setTotalTargetLaborPercentage(BigDecimal totalTargetLaborPercentage) {
		this.totalTargetLaborPercentage = totalTargetLaborPercentage;
	}

}
