/*
 * File name: ScheduleServiceBean.java
 * Creation date: Oct 4, 2008 4:06:28 PM
 * Copyright Mindpool
 */
package com.laborguru.service.schedule;

import java.util.Date;

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
}
