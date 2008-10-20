/*
 * File name: ScheduleDaoHibernate.java
 * Creation date: Oct 4, 2008 4:08:38 PM
 * Copyright Mindpool
 */
package com.laborguru.service.schedule.dao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.laborguru.model.Store;
import com.laborguru.model.StoreSchedule;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class ScheduleDaoHibernate extends HibernateDaoSupport implements ScheduleDao {
	private static final Logger log = Logger.getLogger(ScheduleDaoHibernate.class);	

	/**
	 * 
	 */
	public ScheduleDaoHibernate() {
	}

	/**
	 * @param store
	 * @param date
	 * @return
	 * @see com.laborguru.service.schedule.dao.ScheduleDao#getStoreScheduleByDate(com.laborguru.model.Store, java.util.Date)
	 */
	public StoreSchedule getStoreScheduleByDate(Store store, Date date) {
		if(log.isDebugEnabled()) {
			log.debug("Searching schedule for day [" + date + "] for store [" + store + "]");
		}
		
		List<StoreSchedule> schedules = (List<StoreSchedule>) getHibernateTemplate().findByNamedParam("from StoreSchedule schedule where schedule.store.id = :storeId and schedule.day = :day", new String[]{"storeId", "day"}, new Object[] {store.getId(),date});
		
		if(log.isDebugEnabled()) {
			log.debug("Found [" + (schedules != null ? schedules.size() : "null") + "] schedules for day [" + date + "]");
		}
		return schedules != null && schedules.size() > 0 ? schedules.get(0) : null;
	}

	/**
	 * @param schedule
	 * @return
	 * @see com.laborguru.service.schedule.dao.ScheduleDao#save(com.laborguru.model.StoreSchedule)
	 */
	public StoreSchedule save(StoreSchedule schedule) {
		if (schedule == null){
			throw new IllegalArgumentException("The schedule passed as parameter is null");
		}
		
		getHibernateTemplate().saveOrUpdate(schedule);
		
		return schedule;
	}

}
