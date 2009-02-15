package com.laborguru.service.historicsales;

import java.util.Date;

import com.laborguru.model.DailyHistoricSales;
import com.laborguru.model.HistoricSales;
import com.laborguru.model.Store;

/**
 * Handles historic sales services
 * 
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public interface HistoricSalesService {

	/**
	 * Save a sales record into the database
	 * @param The record to store.
	 */
	void save(HistoricSales hs);
	
	/**
	 * Returns a DailyHistoricSales object populated with the sales information for the date passed as parameter.
	 * 
	 * @param store
	 * @param date
	 * @return The DailyHistoricSales
	 */
	DailyHistoricSales getDailyHistoricSalesByDate(Store store, Date date);
}
