/*
 * File name: StaffingService.java
 * Creation date: 19/10/2008 16:59:08
 * Copyright Mindpool
 */
package com.laborguru.service.staffing;

import java.util.Date;

import com.laborguru.model.Store;
import com.laborguru.model.StoreDailyStaffing;
import com.laborguru.service.Service;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public interface StaffingService extends Service {

	/**
	 * 
	 * @param store
	 * @param date
	 * @return
	 */
	StoreDailyStaffing getDailyStaffingByDate(Store store, Date date);
	
	/**
	 * 
	 * @param dailyStaffing
	 * @return
	 */
	StoreDailyStaffing save(StoreDailyStaffing dailyStaffing);
	
	/**
	 * 
	 * @param store
	 * @param date
	 */
	void deleteDailyStaffingForDate(Store store, Date date);	
	
	/**
	 * 
	 * @param store
	 * @param date
	 */
	void deleteDailyStaffingFromDate(Store store, Date date);
}
