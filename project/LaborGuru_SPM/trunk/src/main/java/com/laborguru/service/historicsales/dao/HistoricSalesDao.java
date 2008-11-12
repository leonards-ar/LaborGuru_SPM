package com.laborguru.service.historicsales.dao;

import com.laborguru.model.HistoricSales;

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
}
