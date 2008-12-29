package com.laborguru.action.report;

import java.util.List;

import com.laborguru.action.SpmActionResult;
import com.laborguru.model.report.TotalHourByPosition;
import com.laborguru.service.position.PositionService;
import com.laborguru.service.report.ReportService;
import com.opensymphony.xwork2.Preparable;

/**
 *
 * @author <a href="fbarreraoro@gmail.com">Federico Barrera Oro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class ForecastByPositionPrepareAction extends ScheduleReportPrepareAction implements Preparable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2260516967324989873L;

	private List <TotalHourByPosition> totalHoursByPosition;
	
	private ReportService reportService;
	
	private PositionService positionService;

	
	/**
	 * @throws Exception
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	public void prepare() throws Exception {
	}

	public void prepareShowTable() throws Exception {
	}
	
	public String showTable(){
		initWeekSelectorValues();
		getForecastReport();
		return SpmActionResult.SHOW.getResult();
	}
	
	private void getForecastReport() {
		
		setTotalHoursByPosition(reportService.getForecastByPosition(getEmployeeStore(), positionService.getPositionsByStore(getEmployeeStore()), getWeekDaySelector().getStartingWeekDay()));
	}
	/**
	 * @return the totalHoursByPosition
	 */
	public List<TotalHourByPosition> getTotalHoursByPosition() {
		return totalHoursByPosition;
	}

	
	/**
	 * @param totalHoursByPosition the totalHoursByPosition to set
	 */
	public void setTotalHoursByPosition(
			List<TotalHourByPosition> totalHoursByPosition) {
		this.totalHoursByPosition = totalHoursByPosition;
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
