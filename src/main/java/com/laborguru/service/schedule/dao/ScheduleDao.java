/*
 * File name: ScheduleDao.java
 * Creation date: Oct 4, 2008 4:06:56 PM
 * Copyright Mindpool
 */
package com.laborguru.service.schedule.dao;

import java.util.Date;

import com.laborguru.model.Store;
import com.laborguru.model.StoreSchedule;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public interface ScheduleDao {

	/**
	 * 
	 * @param store
	 * @param date
	 * @return
	 */
	StoreSchedule getStoreScheduleByDate(Store store, Date date);
	
	/**
	 * 
	 * @param schedule
	 * @return
	 */
	StoreSchedule save(StoreSchedule schedule);
	
	/**
	 * 
	 * @param schedule
	 * @return
	 */
	StoreSchedule detach(StoreSchedule schedule);		
}
