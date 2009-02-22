/*
 * File name: EmployeeHomeSummaryBaseAction.java
 * Creation date: 22/02/2009 11:00:06
 * Copyright Mindpool
 */
package com.laborguru.action.home;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.laborguru.action.SpmAction;
import com.laborguru.frontend.model.WeekDaySelector;
import com.laborguru.model.DailyProjection;
import com.laborguru.model.DailySalesValue;
import com.laborguru.model.DayOfWeek;
import com.laborguru.model.StoreDailyHistoricSalesStaffing;
import com.laborguru.model.StoreDailyStaffing;
import com.laborguru.model.StoreSchedule;
import com.laborguru.service.projection.ProjectionService;
import com.laborguru.service.schedule.ScheduleService;
import com.laborguru.service.staffing.StaffingService;
import com.laborguru.util.CalendarUtils;
import com.laborguru.util.SpmConstants;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public abstract class EmployeeHomeSummaryBaseAction extends SpmAction {
	private WeekDaySelector weekDaySelector;

	private ScheduleService scheduleService;
	private StaffingService staffingService;
	private ProjectionService projectionService;
	
	/**
	 * 
	 */
	public EmployeeHomeSummaryBaseAction() {
	}

	/**
	 * @return the weekDaySelector
	 */
	public WeekDaySelector getWeekDaySelector() {
		if(weekDaySelector == null) {
			weekDaySelector = new WeekDaySelector(getEmployeeStore().getFirstDayOfWeek());
		}
		return weekDaySelector;
	}

	/**
	 * @param weekDaySelector the weekDaySelector to set
	 */
	public void setWeekDaySelector(WeekDaySelector weekDaySelector) {
		this.weekDaySelector = weekDaySelector;
	}

	/**
	 * @return the scheduleService
	 */
	public ScheduleService getScheduleService() {
		return scheduleService;
	}

	/**
	 * @param scheduleService the scheduleService to set
	 */
	public void setScheduleService(ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}

	/**
	 * @return the staffingService
	 */
	public StaffingService getStaffingService() {
		return staffingService;
	}

	/**
	 * @param staffingService the staffingService to set
	 */
	public void setStaffingService(StaffingService staffingService) {
		this.staffingService = staffingService;
	}

	/**
	 * @return the projectionService
	 */
	public ProjectionService getProjectionService() {
		return projectionService;
	}

	/**
	 * @param projectionService the projectionService to set
	 */
	public void setProjectionService(ProjectionService projectionService) {
		this.projectionService = projectionService;
	}
	
	/**
	 * 
	 * @param day
	 * @return
	 */
	protected List<StoreSchedule> getScheduleForWeekStartingOn(Date day) {
		List<StoreSchedule> schedules = new ArrayList<StoreSchedule>(DayOfWeek.values().length);
		
		for(int i = 0; i < DayOfWeek.values().length; i++) {
			schedules.add(getScheduleService().getStoreScheduleByDate(getEmployeeStore(), CalendarUtils.addOrSubstractDays(day, i)));
		}
		
		return schedules;
	}
	
	/**
	 * 
	 * @param day
	 * @return
	 */
	protected List<StoreDailyStaffing> getDailyStaffingForWeekStartingOn(Date day) {
		List<StoreDailyStaffing> dailyStaffings = new ArrayList<StoreDailyStaffing>(DayOfWeek.values().length);
		
		for(int i = 0; i < DayOfWeek.values().length; i++) {
			dailyStaffings.add(getStaffingService().getDailyStaffingByDate(getEmployeeStore(), CalendarUtils.addOrSubstractDays(day, i)));
		}
		
		return dailyStaffings;
	}
	
	/**
	 * 
	 * @param day
	 * @return
	 */
	protected List<StoreDailyHistoricSalesStaffing> getDailyHistoricSalesStaffingForWeekStartingOn(Date day) {
		List<StoreDailyHistoricSalesStaffing> dailyStaffings = new ArrayList<StoreDailyHistoricSalesStaffing>(DayOfWeek.values().length);
		
		for(int i = 0; i < DayOfWeek.values().length; i++) {
			dailyStaffings.add(getStaffingService().getDailyHistoricSalesStaffingByDate(getEmployeeStore(), CalendarUtils.addOrSubstractDays(day, i)));
		}
		
		return dailyStaffings;
	}

	/**
	 * 
	 * @param day
	 * @return
	 */
	protected List<DailyProjection> getDailyProjectionsForWeekStartingOn(Date day) {
		return getProjectionService().getDailyProjections(getEmployeeStore(), day, CalendarUtils.addOrSubstractDays(day, DayOfWeek.values().length - 1));
	}

	/**
	 * 
	 * @param salesValues
	 * @return
	 */
	protected BigDecimal calculateVolume(List<? extends DailySalesValue> salesValues) {
		if(salesValues != null) {
			BigDecimal total = new BigDecimal(SpmConstants.INIT_VALUE_ZERO);
			for(DailySalesValue sv : salesValues) {
				total = total.add(sv.getDailySalesValue());
			}
			return total;
		} else {
			return SpmConstants.BD_ZERO_VALUE;
		}
	}
	
	/**
	 * 
	 * @param dailyStaffings
	 * @return
	 */
	protected Double calculateProjectedTarget(List<StoreDailyStaffing> dailyStaffings) {
		double total = 0.0;
		
		if(dailyStaffings != null) {
			for(StoreDailyStaffing dailyStaffing : dailyStaffings) {
				total += dailyStaffing.getTotalDailyTarget().doubleValue();
			}
		}
		
		return new Double(total);
	}

	/**
	 * 
	 * @param dailyStaffings
	 * @return
	 */
	protected Double calculateHistoricSalesTarget(List<StoreDailyHistoricSalesStaffing> dailyStaffings) {
		double total = 0.0;
		
		if(dailyStaffings != null) {
			for(StoreDailyHistoricSalesStaffing dailyStaffing : dailyStaffings) {
				total += dailyStaffing.getTotalDailyTarget().doubleValue();
			}
		}
		
		return new Double(total);
	}	
	
	/**
	 * 
	 * @param dailyStaffings
	 * @return
	 */
	protected Double calculateProjectedScheduled(List<StoreSchedule> schedules) {
		double total = 0.0;
		
		if(schedules != null) {
			for(StoreSchedule schedule : schedules) {
				//:TODO: Confirm if getTotalShiftHoursWithContiguous should be used instead
				total += schedule.getTotalShiftHours().doubleValue();
			}
		}
		
		return new Double(total);
	}	
}
