package com.laborguru.action.report;

import java.math.BigDecimal;

import com.laborguru.model.Position;
import com.laborguru.model.PositionGroup;
import com.laborguru.model.report.TotalHour;
import com.laborguru.util.SpmConstants;

public class SchedulingEfficiencyReportPrepareAction extends WeeklyReportBaseAction {

	private static final long serialVersionUID = 1L;

	protected void getReport() {
		setTotalHours(getReportService().getWeeklyTotalHours(
				getEmployeeStore(), getWeekDaySelector().getStartingWeekDay()));
	}

	protected void getReportByPosition() {
		Position position = new Position();
		position.setId(getItemId());
		setTotalHours(getReportService().getWeeklyTotalHoursByPosition(
				getEmployeeStore(), position,
				getWeekDaySelector().getStartingWeekDay()));
	}

	protected void getReportByService() {
		PositionGroup positionGroup = new PositionGroup();
		positionGroup.setId(getItemId());
		setTotalHours(getReportService().getWeeklyTotalHoursByService(
				getEmployeeStore(), positionGroup,
				getWeekDaySelector().getStartingWeekDay()));
	}
	
}
