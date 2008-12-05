package com.laborguru.action.report;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;

import com.laborguru.model.Position;
import com.laborguru.model.report.TotalHour;
import com.laborguru.service.report.ReportService;
import com.laborguru.util.FusionXmlDataConverter;
import com.opensymphony.xwork2.Preparable;


/**
 *
 * @author <a href="fbarreraoro@gmail.com">Federico Barrera Oro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class TotalHoursReportByPositionPrepareAction extends ScheduleReportPrepareAction implements Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HashMap<Position, List<TotalHour>> totalHoursByPosition;
	private HashMap<Position, BigDecimal> totalSchedulebyPosition;
	private HashMap<Position, BigDecimal> totalTargetByPosition;
	private HashMap<Position, BigDecimal> totalDifference;
	private HashMap<Position, BigDecimal> totalPercentajeByPosition;
	private HashMap<Position, String> graphData;
	
	private ReportService reportService;
	private FusionXmlDataConverter fusionXmlDataConverter;
	
	private final String actionName = "totalHoursReportByPosition";
	
	@Override
	public void prepareChangeWeek() {
	}

	@Override
	protected void processChangeWeek() {
	}

	/**
	 * 
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	public void prepare(){
	}

	private void getReport() {
		setTotalHoursByPosition(reportService.getWeeklyTotalHoursByPosition(super.getEmployeeStore(), getWeekDaySelector().getStartingWeekDay()));
		calculateTotals();
		generateGraphs();
	}
	
	private void calculateTotals() {
		for(Position position: getEmployeeStore().getPositions()) {
			BigDecimal totalSchedule = new BigDecimal("0");
			BigDecimal totalTarget = new BigDecimal("0");
			BigDecimal totalDifference = new BigDecimal("0");
			
			for(TotalHour th: getTotalHoursByPosition().get(position)) {
				totalSchedule = totalSchedule.add(th.getSchedule());
				totalTarget = totalTarget.add(th.getTarget());
				totalDifference = totalDifference.add(th.getDifference());
			}
			
			getTotalSchedulebyPosition().put(position, totalSchedule);
			getTotalTargetByPosition().put(position, totalTarget);
			getTotalDifference().put(position, totalDifference);
			getTotalPercentajeByPosition().put(position, totalDifference.divide(totalTarget, 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")));
		}
	}
	
	private void generateGraphs(){
		for(Position position: getEmployeeStore().getPositions()){
			getGraphData().put(position, getFusionXmlDataConverter().weeklyTotalHoursXmlConverter(getTotalHoursByPosition().get(position)));
		}
	}
	
	/**
	 * @return the totalHoursByPosition
	 */
	public HashMap<Position, List<TotalHour>> getTotalHoursByPosition() {
		return totalHoursByPosition;
	}

	/**
	 * @param totalHoursByPosition the totalHoursByPosition to set
	 */
	public void setTotalHoursByPosition(
			HashMap<Position, List<TotalHour>> totalHoursByPosition) {
		this.totalHoursByPosition = totalHoursByPosition;
	}

	/**
	 * @return the totalSchedulebyPosition
	 */
	public HashMap<Position, BigDecimal> getTotalSchedulebyPosition() {
		return totalSchedulebyPosition;
	}

	/**
	 * @param totalSchedulebyPosition the totalSchedulebyPosition to set
	 */
	public void setTotalSchedulebyPosition(
			HashMap<Position, BigDecimal> totalSchedulebyPosition) {
		this.totalSchedulebyPosition = totalSchedulebyPosition;
	}

	/**
	 * @return the totalTargetByPosition
	 */
	public HashMap<Position, BigDecimal> getTotalTargetByPosition() {
		return totalTargetByPosition;
	}

	/**
	 * @param totalTargetByPosition the totalTargetByPosition to set
	 */
	public void setTotalTargetByPosition(
			HashMap<Position, BigDecimal> totalTargetByPosition) {
		this.totalTargetByPosition = totalTargetByPosition;
	}

	/**
	 * @return the totalDifference
	 */
	public HashMap<Position, BigDecimal> getTotalDifference() {
		return totalDifference;
	}

	/**
	 * @param totalDifference the totalDifference to set
	 */
	public void setTotalDifference(HashMap<Position, BigDecimal> totalDifference) {
		this.totalDifference = totalDifference;
	}

	/**
	 * @return the totalPercentajeByPosition
	 */
	public HashMap<Position, BigDecimal> getTotalPercentajeByPosition() {
		return totalPercentajeByPosition;
	}

	/**
	 * @param totalPercentajeByPosition the totalPercentajeByPosition to set
	 */
	public void setTotalPercentajeByPosition(
			HashMap<Position, BigDecimal> totalPercentajeByPosition) {
		this.totalPercentajeByPosition = totalPercentajeByPosition;
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
	 * @return the graphData
	 */
	public HashMap<Position, String> getGraphData() {
		return graphData;
	}

	/**
	 * @param graphData the graphData to set
	 */
	public void setGraphData(HashMap<Position, String> graphData) {
		this.graphData = graphData;
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
	 * @return the actionName
	 */
	public String getActionName() {
		return actionName;
	}
}
