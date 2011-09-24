/*
 * File name: ScheduleServiceBean.java
 * Creation date: Oct 4, 2008 4:06:28 PM
 * Copyright Mindpool
 */
package com.laborguru.service.schedule;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.laborguru.model.EmployeeSchedule;
import com.laborguru.model.Store;
import com.laborguru.model.StoreSchedule;
import com.laborguru.service.schedule.dao.ScheduleDao;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class ScheduleServiceBean implements ScheduleService {
	private ScheduleDao scheduleDao;
	
	/**
	 * 
	 */
	public ScheduleServiceBean() {
	}

	/**
	 * @param store
	 * @param date
	 * @return
	 * @see com.laborguru.service.schedule.ScheduleService#getStoreScheduleByDate(com.laborguru.model.Store, java.util.Date)
	 */
	public StoreSchedule getStoreScheduleByDate(Store store, Date date) {
		return getScheduleDao().getStoreScheduleByDate(store, date);
	}

	/**
	 * @param schedule
	 * @return
	 * @see com.laborguru.service.schedule.ScheduleService#save(com.laborguru.model.StoreSchedule)
	 */
	public StoreSchedule save(StoreSchedule schedule) {
		return getScheduleDao().save(schedule);
	}

	/**
	 * @return the scheduleDao
	 */
	public ScheduleDao getScheduleDao() {
		return scheduleDao;
	}

	/**
	 * @param scheduleDao the scheduleDao to set
	 */
	public void setScheduleDao(ScheduleDao scheduleDao) {
		this.scheduleDao = scheduleDao;
	}

	/**
	 * 
	 * @param schedule
	 * @return
	 * @see com.laborguru.service.schedule.ScheduleService#detach(com.laborguru.model.StoreSchedule)
	 */
	public StoreSchedule detach(StoreSchedule schedule) {
		return getScheduleDao().detach(schedule);
	}

	/**
	 * 
	 * @param store
	 * @param startDate
	 * @param endDate
	 * @return
	 * @see com.laborguru.service.schedule.ScheduleService#getTotalScheduledHoursForTimePeriod(com.laborguru.model.Store, java.util.Date, java.util.Date)
	 */
	public BigDecimal getTotalScheduledHoursForTimePeriod(Store store,
			Date startDate, Date endDate) {
		return getScheduleDao().getTotalScheduledHoursForTimePeriod(store, startDate, endDate);
	}

	/**
	 * 
	 * @param store
	 * @param startDate
	 * @param endDate
	 * @return
	 * @see com.laborguru.service.schedule.ScheduleService#getTotalScheduledLaborCostForTimePeriod(com.laborguru.model.Store, java.util.Date, java.util.Date)
	 */
	public BigDecimal getTotalScheduledLaborCostForTimePeriod(Store store,
			Date startDate, Date endDate) {
		return getScheduleDao().getTotalScheduledLaborCostForTimePeriod(store, startDate, endDate);
	}

	/**
	 * 
	 * @param store
	 * @param startDate
	 * @param endDate
	 * @return
	 * @see com.laborguru.service.schedule.ScheduleService#getTotalScheduledHoursByPositionForTimePeriod(com.laborguru.model.Store, java.util.Date, java.util.Date)
	 */
	public Map<Integer, BigDecimal> getTotalScheduledHoursByPositionForTimePeriod(
			Store store, Date startDate, Date endDate) {
		return getScheduleDao().getTotalScheduledHoursByPositionForTimePeriod(store, startDate, endDate);
	}
	
	/**
	 * 
	 * 
	 * @see com.laborguru.service.schedule.ScheduleService#updateAllStoreSchedules()
	 */
	public void updateAllStoreSchedules() {
		List<StoreSchedule> schedules = getScheduleDao().getAllStoreSchedules();
		
		for(StoreSchedule aSchedule : schedules) {
			for(EmployeeSchedule employeeSchedule : aSchedule.getEmployeeSchedules()) {
				employeeSchedule.reindexShifts();
			}			
			getScheduleDao().save(aSchedule);
		}
	}
}
