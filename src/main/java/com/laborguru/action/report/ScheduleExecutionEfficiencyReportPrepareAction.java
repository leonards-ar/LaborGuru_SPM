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
		setTotalHours(getReportService().getScheduleExecutionEfficiencyReport(
				getEmployeeStore(), getWeekDaySelector().getSelectedDay()));
	}

	@Override
	protected void getReportByPosition() {
		//Not Implemented in this version
	}

	@Override
	protected void getReportByService() {
		//Not Implemented in this version
	}

}
