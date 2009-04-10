package com.laborguru.action.report;

import java.util.Date;

import com.laborguru.exception.ErrorEnum;
import com.laborguru.exception.SpmUncheckedException;
import com.laborguru.util.CalendarUtils;



public class PerformanceEfficiencyReportPrepareAction extends WeeklyReportBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7643112299027218254L;

	protected void getReport() {
		Date end = CalendarUtils.addOrSubstractDays(getWeekDaySelector().getStartingWeekDay(), 6);
		setTotalHours(getReportService().getPerformanceEfficiencyReport(
				getEmployeeStore(), getWeekDaySelector().getStartingWeekDay(), end, true));		
	}

	protected void getReportByPosition() {
		throw new SpmUncheckedException("Method not implemented", ErrorEnum.NOT_IMPLEMENTED_METHOD);
	}

	protected void getReportByService() {
		throw new SpmUncheckedException("Method not implemented", ErrorEnum.NOT_IMPLEMENTED_METHOD);
	}
	
	protected void setAxisLabels() {
		setScheduleAxisName("report.performance.scheduled.label");
		setTargetAxisName("report.performance.target.label");
	}
	
}
