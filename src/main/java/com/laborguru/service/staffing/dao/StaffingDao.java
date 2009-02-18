/*
 * File name: StaffingDao.java
 * Creation date: 19/10/2008 16:55:31
 * Copyright Mindpool
 */
package com.laborguru.service.staffing.dao;

import java.util.Date;
import java.util.List;

import com.laborguru.model.DailyHistoricSalesStaffing;
import com.laborguru.model.DailyProjectedStaffing;
import com.laborguru.model.Position;
import com.laborguru.model.Store;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public interface StaffingDao {

	/**
	 * 
	 * @param position
	 * @param date
	 * @return
	 */
	DailyProjectedStaffing getDailyStaffingByDate(Position position, Date date);
	
	/**
	 * 
	 * @param dailyStaffing
	 * @return
	 */
	DailyProjectedStaffing save(DailyProjectedStaffing dailyStaffing);	
	
	/**
	 * 
	 * @param storeDailyStaffing
	 */
	void deleteAll(List<DailyProjectedStaffing> storeDailyStaffing);	
	
	/**
	 * 
	 * @param store
	 * @param date
	 * @return
	 */
	List<DailyProjectedStaffing> getStoreDailyStaffingByDate(Store store, Date date);
	
	/**
	 * 
	 * @param store
	 * @param date
	 * @return
	 */
	List<DailyHistoricSalesStaffing> getDailyHistoricSalesStaffingByDate(Store store, Date date);
	
	/**
	 * 
	 * @param position
	 * @param date
	 * @return
	 */
	DailyHistoricSalesStaffing getDailyHistoricSalesStaffingByDate(Position position, Date date);
	
	/**
	 * 
	 * @param store
	 * @param date
	 * @return
	 */
	List<DailyProjectedStaffing> getStoreDailyStaffingFromDate(Store store, Date date);
	
	/**
	 * 
	 * @param dailyStaffing
	 * @return
	 */
	DailyHistoricSalesStaffing save(DailyHistoricSalesStaffing dailyStaffing);
	
	/**
	 * 
	 * @param storeDailyStaffing
	 */
	void deleteAllHistoricSalesStaffing(List<DailyHistoricSalesStaffing> storeDailyStaffing);		
}
