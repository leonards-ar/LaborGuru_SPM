package com.laborguru.action.report;

import java.math.BigDecimal;

import com.laborguru.model.Position;
import com.laborguru.model.PositionGroup;
import com.laborguru.model.report.TotalHour;
import com.laborguru.util.SpmConstants;


public class PerformanceEfficiencyReportPrepareAction extends WeeklyReportBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7643112299027218254L;

	protected void getReport() {
		setTotalHours(getReportService().getPerformanceEfficiencyReport(
				getEmployeeStore(), getWeekDaySelector().getStartingWeekDay()));		
	}

	protected void getReportByPosition() {
		Position position = new Position();
		position.setId(getItemId());
		setTotalHours(getReportService().getPerformanceEfficiencyReportByPosition(
				getEmployeeStore(), position, getWeekDaySelector().getStartingWeekDay()));
	}

	protected void getReportByService() {
		PositionGroup positionGroup = new PositionGroup();
		positionGroup.setId(getItemId());
		setTotalHours(getReportService().getPerformanceEfficiencyReportByService(
				getEmployeeStore(), positionGroup, getWeekDaySelector().getStartingWeekDay()));
	}

	
}
