package com.laborguru.action.report;

import java.util.Date;

import com.laborguru.exception.ErrorEnum;
import com.laborguru.exception.SpmUncheckedException;
import com.laborguru.util.CalendarUtils;

public class ScheduleExecutionEfficiencyReportPrepareAction extends
		WeeklyReportBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1871349385162461610L;

	@Override
	protected void getReport() {
		Date end = CalendarUtils.addOrSubstractDays(getWeekDaySelector().getStartingWeekDay(), 6);
		setTotalHours(getReportService().getScheduleExecutionEfficiencyReport(
				getEmployeeStore(), getWeekDaySelector().getStartingWeekDay(), end, true));
	}

	@Override
	protected void getReportByPosition() {
		throw new SpmUncheckedException("Method not implemented", ErrorEnum.NOT_IMPLEMENTED_METHOD);
	}

	@Override
	protected void getReportByService() {
		throw new SpmUncheckedException("Method not implemented", ErrorEnum.NOT_IMPLEMENTED_METHOD);
	}
	
	@Override
	protected void setAxisLabels() {
		setScheduleAxisName("report.schedule.execution.scheduled.label");
		setTargetAxisName("report.schedule.execution.target.label");
	}

}