package com.laborguru.action.report;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;

import com.laborguru.action.SpmActionResult;
import com.laborguru.model.Position;
import com.laborguru.model.report.TotalHour;
import com.laborguru.service.position.PositionService;
import com.laborguru.service.report.ReportService;
import com.laborguru.util.FusionXmlDataConverter;
import com.laborguru.util.SpmConstants;
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
	private HashMap<Position, BigDecimal> totalScheduleByPosition;
	private HashMap<Position, BigDecimal> totalTargetByPosition;
	private HashMap<Position, BigDecimal> totalDifferenceByPosition;
	private HashMap<Position, BigDecimal> totalPercentajeByPosition;
	private HashMap<Position, String> graphData;
	private List<Position> positions;
	
	private ReportService reportService;
	private PositionService positionService;
	
	private FusionXmlDataConverter fusionXmlDataConverter;
	
	private final String actionName = "totalHoursReportByPosition";
	
	@Override
	public void prepareChangeWeek() {
		pageSetup();
	}

	@Override
	protected void processChangeWeek() {
		getWeekDaySelector().setStringSelectedDay(getSelectedDate());
		showReport();
	}

	/**
	 * 
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	public void prepare(){
	}

	public void prepareShowReport(){
		pageSetup();
	}
	
	public String showReport() {
		if(getPeriod() == null || getPeriod().equals("weekly")) {
			return weeklyReport();
		} 
		//TODO show half hour report.
		return weeklyReport();
	}
	
	public String weeklyReport(){
		getWeeklyReport();
		loadCalendarData();
		return SpmActionResult.INPUT.getResult();
	}
	
	private void getWeeklyReport() {
		//setTotalHoursByPosition(reportService.getWeeklyTotalHoursByPosition(getEmployeeStore(), getPositions(), getWeekDaySelector().getStartingWeekDay()));
		calculateTotals();
		generateGraphs();
	}
	
	private void calculateTotals() {

		initTotals();
		
		for(Position position: getPositions()) {
			BigDecimal totalSchedule = SpmConstants.BD_ZERO_VALUE;
			BigDecimal totalTarget = SpmConstants.BD_ZERO_VALUE;
			BigDecimal totalDifference = SpmConstants.BD_ZERO_VALUE;
			
			for(TotalHour th: getTotalHoursByPosition().get(position)) {
				totalSchedule = totalSchedule.add(th.getSchedule());
				totalTarget = totalTarget.add(th.getTarget());
				totalDifference = totalDifference.add(th.getDifference());
			}
			
			getTotalScheduleByPosition().put(position, totalSchedule);
			getTotalTargetByPosition().put(position, totalTarget);
			getTotalDifferenceByPosition().put(position, totalDifference);
			getTotalPercentajeByPosition().put(position, totalDifference.divide(totalTarget, 2, SpmConstants.ROUNDING_MODE).multiply(new BigDecimal("100")));

		}
		
	}
	
	private void generateGraphs(){
		setGraphData(new HashMap<Position, String>());
		for(Position position: getPositions()){
			getGraphData().put(position, getFusionXmlDataConverter().weeklyTotalHoursXmlConverter(getTotalHoursByPosition().get(position)));
		}
	}
	
	private void initTotals() {
		
		setTotalScheduleByPosition(new HashMap<Position, BigDecimal>());
		setTotalTargetByPosition(new HashMap<Position, BigDecimal>());
		setTotalDifferenceByPosition(new HashMap<Position, BigDecimal>());
		setTotalPercentajeByPosition(new HashMap<Position, BigDecimal>());

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
	public HashMap<Position, BigDecimal> getTotalScheduleByPosition() {
		return totalScheduleByPosition;
	}

	/**
	 * @param totalSchedulebyPosition the totalSchedulebyPosition to set
	 */
	public void setTotalScheduleByPosition(
			HashMap<Position, BigDecimal> totalScheduleByPosition) {
		this.totalScheduleByPosition = totalScheduleByPosition;
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
	public HashMap<Position, BigDecimal> getTotalDifferenceByPosition() {
		return totalDifferenceByPosition;
	}

	/**
	 * @param totalDifference the totalDifference to set
	 */
	public void setTotalDifferenceByPosition(HashMap<Position, BigDecimal> totalDifferenceByPosition) {
		this.totalDifferenceByPosition = totalDifferenceByPosition;
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

	/**
	 * @return the positions
	 */
	public List<Position> getPositions() {
		if(positions == null) {
			positions = getPositionService().getPositionsByStore(getEmployeeStore());
		}
		return positions;
	}

	/**
	 * @param positions the positions to set
	 */
	public void setPositions(List<Position> positions) {
		this.positions = positions;
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
	
}
