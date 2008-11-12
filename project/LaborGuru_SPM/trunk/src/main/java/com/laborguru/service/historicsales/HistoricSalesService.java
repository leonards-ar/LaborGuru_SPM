package com.laborguru.service.historicsales;

import com.laborguru.model.HistoricSales;

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
}
