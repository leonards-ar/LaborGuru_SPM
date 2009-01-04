package com.laborguru.action.report;

import java.math.BigDecimal;
import java.util.List;

import com.laborguru.action.SpmActionResult;
import com.laborguru.model.report.TotalHour;
import com.laborguru.util.SpmConstants;
import com.opensymphony.xwork2.Preparable;

/**
 *
 * @author <a href="fbarreraoro@gmail.com">Federico Barrera Oro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class HalfHoursReportPrepareAction extends ScheduleReportPrepareAction implements Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -101891023525910101L;


	List<TotalHour>totalHours;
	
	private BigDecimal totalSchedule = SpmConstants.BD_ZERO_VALUE;
	private BigDecimal totalTarget = SpmConstants.BD_ZERO_VALUE;
	private BigDecimal totalDifference = SpmConstants.BD_ZERO_VALUE;
	private BigDecimal totalPercentaje = SpmConstants.BD_ZERO_VALUE;
	
	public void prepareShowReport(){
		pageSetup();
	}

	public void prepareShowFirstReport() {
		pageSetup();
	}

	public String showFirstReport() {
		return SpmActionResult.INPUT.getResult();
	}
	
	public String showReport(){
		initWeekSelectorValues();
		
		if (getItemId() == null) {
			getDailyReport();
		} else {
			if ("byPosition".equals(getSelectedGrouping())) {
				getDailyReportByPosition();
			} else if ("byService".equals(getSelectedGrouping())) {
				getDailyReportByService();
			} else {
				getDailyReport();
			}
		}		
		
		return SpmActionResult.SHOW.getResult();
	}
	
	private void getDailyReport(){
		setTotalHours(getReportService().getHalfHourlyReport(getEmployeeStore(), getWeekDaySelector().getSelectedDay()));
		calculateTotals();
	}
	
	private void getDailyReportByPosition(){
		
	}
	
	private void getDailyReportByService(){
		
	}

	private void calculateTotals() {

		for (TotalHour th : getTotalHours()) {
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
	
	/**
	 * Gets the data to display in the graphic chart.
	 * 
	 * @return
	 */
	public String getXmlValues() {
		return getFusionXmlDataConverter().halfHoursXmlConverter(
				getTotalHours());
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

	public void prepare() throws Exception {
	}
	
	

}