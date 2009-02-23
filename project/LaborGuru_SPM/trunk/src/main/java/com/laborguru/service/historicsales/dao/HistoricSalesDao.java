package com.laborguru.service.historicsales.dao;

import java.math.BigDecimal;
import java.util.Date;

import com.laborguru.model.DailyHistoricSales;
import com.laborguru.model.HistoricSales;
import com.laborguru.model.Store;

/**
 * Defines the data access operations for a sales record on SPM
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public interface HistoricSalesDao {

	/**
	 * Saves or updates a sales record into the database
	 * @param The record to store.
	 */
	void saveOrUpdate(HistoricSales hs);
	
	/**
	 * 
	 * @param store
	 * @param date
	 * @return
	 */
	DailyHistoricSales getDailyHistoricSales(Store store, Date date);
	
	/**
	 * 
	 * @param store
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	BigDecimal getTotalHistoricSalesForTimePeriod(Store store, Date startDate, Date endDate);
}
