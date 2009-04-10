package com.laborguru.action.report;

import java.util.Date;

import com.laborguru.model.Position;
import com.laborguru.model.PositionGroup;
import com.laborguru.util.CalendarUtils;

public class SchedulingEfficiencyReportPrepareAction extends WeeklyReportBaseAction {

	private static final long serialVersionUID = 1L;

	protected void getReport() {
		Date end = CalendarUtils.addOrSubstractDays(getWeekDaySelector().getStartingWeekDay(), 6);
		setTotalHours(getReportService().getWeeklyTotalHours(
				getEmployeeStore(), getWeekDaySelector().getStartingWeekDay(), end, true));
	}

	protected void getReportByPosition() {
		Date end = CalendarUtils.addOrSubstractDays(getWeekDaySelector().getStartingWeekDay(), 6);
		Position position = new Position();
		position.setId(getItemId());
		setTotalHours(getReportService().getWeeklyTotalHoursByPosition(
				getEmployeeStore(), position,
				getWeekDaySelector().getStartingWeekDay(), end));
	}

	protected void getReportByService() {
		Date end = CalendarUtils.addOrSubstractDays(getWeekDaySelector().getStartingWeekDay(), 6);
		PositionGroup positionGroup = new PositionGroup();
		positionGroup.setId(getItemId());
		setTotalHours(getReportService().getWeeklyTotalHoursByService(
				getEmployeeStore(), positionGroup,
				getWeekDaySelector().getStartingWeekDay(), end));
	}
	
	protected void setAxisLabels() {
		setScheduleAxisName("report.weeklytotalhours.scheduled.label");
		setTargetAxisName("report.weeklytotalhours.target.label");
	}
	
}
