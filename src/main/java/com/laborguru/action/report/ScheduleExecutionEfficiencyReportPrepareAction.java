package com.laborguru.action.report;

import com.laborguru.model.Position;
import com.laborguru.model.PositionGroup;

public class ScheduleExecutionEfficiencyReportPrepareAction extends
		WeeklyReportBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1871349385162461610L;

	@Override
	protected void getReport() {
		setTotalHours(getReportService().getScheduleExecutionEfficiency(
				getEmployeeStore(), getWeekDaySelector().getSelectedDay()));
	}

	@Override
	protected void getReportByPosition() {
		Position position = new Position();
		position.setId(getItemId());
		setTotalHours(getReportService()
				.getScheduleExecutionEfficiencyByPosition(getEmployeeStore(),
						position, getWeekDaySelector().getSelectedDay()));
	}

	@Override
	protected void getReportByService() {
		PositionGroup positionGroup = new PositionGroup();
		positionGroup.setId(getItemId());
		setTotalHours(getReportService()
				.getScheduleExecutionEfficiencyByService(getEmployeeStore(),
						positionGroup, getWeekDaySelector().getSelectedDay()));
	}

}
