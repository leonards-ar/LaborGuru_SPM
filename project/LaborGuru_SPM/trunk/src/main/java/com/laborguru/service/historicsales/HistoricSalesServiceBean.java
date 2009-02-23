package com.laborguru.service.historicsales;

import java.math.BigDecimal;
import java.util.Date;

import com.laborguru.model.DailyHistoricSales;
import com.laborguru.model.Store;
import com.laborguru.service.historicsales.dao.HistoricSalesDao;

/**
 * Historic Sales Services Bean
 * 
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class HistoricSalesServiceBean implements HistoricSalesService {

	HistoricSalesDao historicSalesDao;
	

	/**
	 * @param store
	 * @param date
	 * @return
	 * @see com.laborguru.service.historicsales.HistoricSalesService#getDailyHistoricSalesByDate(com.laborguru.model.Store, java.util.Date)
	 */
	public DailyHistoricSales getDailyHistoricSalesByDate(Store store, Date date) {
		return historicSalesDao.getDailyHistoricSales(store, date);
	}


	/**
	 * @param dao
	 * @see com.laborguru.service.historicsales.HistoricSalesService#setHistoricSalesDao(com.laborguru.service.historicsales.dao.HistoricSalesDao)
	 */
	public void setHistoricSalesDao(HistoricSalesDao dao) {
		this.historicSalesDao = dao;
	}

	/**
	 * 
	 * @param store
	 * @param startDate
	 * @param endDate
	 * @return
	 * @see com.laborguru.service.historicsales.HistoricSalesService#getTotalHistoricSalesForTimePeriod(com.laborguru.model.Store, java.util.Date, java.util.Date)
	 */
	public BigDecimal getTotalHistoricSalesForTimePeriod(Store store, Date startDate, Date endDate) {
		return historicSalesDao.getTotalHistoricSalesForTimePeriod(store, startDate, endDate);
	}

}
