package com.laborguru.service.historicsales;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.laborguru.model.DailyHistoricSales;
import com.laborguru.model.HistoricSales;
import com.laborguru.model.Store;
import com.laborguru.model.UploadFile;
import com.laborguru.service.historicsales.dao.HistoricSalesDao;
import com.laborguru.service.uploadfile.dao.UploadFileDao;

/**
 * Historic Sales Services Bean
 * 
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class HistoricSalesServiceBean implements HistoricSalesService {

	private static final int MAX_TRANSACTIONS = 20; 
	private HistoricSalesDao historicSalesDao;
	private UploadFileDao uploadFileDao;
	private int maxTransactions = MAX_TRANSACTIONS;
	

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
	 * @param uploadFileDao the uploadFileDao to set
	 */
	public void setUploadFileDao(UploadFileDao uploadFileDao) {
		this.uploadFileDao = uploadFileDao;
	}


	/**
	 * @param maxTransactions the maxTransactions to set
	 */
	public void setMaxTransactions(int maxTransactions) {
		this.maxTransactions = maxTransactions;
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
	
	/**
	 * Save HistoricSales
	 * @param historicSales
	 */
	public int saveAll(List<HistoricSales> historicSales, UploadFile uploadFile){
		int processed = 0;
		
		uploadFileDao.saveOrUpdate(uploadFile);
		for(HistoricSales historicSale: historicSales){
			historicSale.setUploadFile(uploadFile);
			historicSalesDao.saveOrUpdate(historicSale);
			processed++;
			if(( processed % maxTransactions) == 0){
				historicSalesDao.flushSession();
				historicSalesDao.clearSession();
			}
		}
		
		return processed;
		
	}

}
