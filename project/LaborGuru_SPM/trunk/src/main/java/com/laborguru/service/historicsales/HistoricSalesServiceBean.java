package com.laborguru.service.historicsales;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import com.laborguru.model.DailyHistoricSales;
import com.laborguru.model.HalfHourHistoricSales;
import com.laborguru.model.HalfHourProjection;
import com.laborguru.model.HistoricSales;
import com.laborguru.model.Store;
import com.laborguru.model.UploadFile;
import com.laborguru.service.historicsales.dao.HistoricSalesDao;
import com.laborguru.service.projection.ProjectionService;
import com.laborguru.service.uploadfile.UploadEnumType;
import com.laborguru.service.uploadfile.dao.UploadFileDao;
import com.laborguru.util.CalendarUtils;

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
	private ProjectionService projectionService;
	
	private int maxTransactions = MAX_TRANSACTIONS;
		

	/**
	 * 
	 * @param store
	 * @param date
	 * @return
	 */
	public DailyHistoricSales getDailyHistoricSales(Store store, Date date) {
		DailyHistoricSales dailyHistoricSales = getHistoricSalesDao().getDailyHistoricSales(store, date);
		if(dailyHistoricSales == null) {
			dailyHistoricSales = DailyHistoricSales.getEmptyDailyHistoricSalesInstance(store, date);
		}
		return dailyHistoricSales;
	}	

	/**
	 * @return the historicSalesDao
	 */
	private HistoricSalesDao getHistoricSalesDao() {
		return historicSalesDao;
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

	
	public DailyHistoricSales calculateHistoricSalesStaticProjection(DailyHistoricSales dailyHistoricSales, BigDecimal projectionAmount) {
		
		List<HalfHourProjection> calculatedHalfHourList = getProjectionService().calculateStaticHalfHourProjections(dailyHistoricSales.getStore(), projectionAmount, dailyHistoricSales.getSalesDate());		
		
		for(HalfHourProjection aHalfHourProjection: calculatedHalfHourList ){
			
			HalfHourHistoricSales halfhourHS = new HalfHourHistoricSales();
			halfhourHS.setTime(aHalfHourProjection.getTime());
			halfhourHS.setIndex(aHalfHourProjection.getIndex());
			halfhourHS.setValue(aHalfHourProjection.getAdjustedValue());
			
			dailyHistoricSales.addHalfHourHistoricSales(halfhourHS);
		}
				
		return dailyHistoricSales;
	}

	public void saveDailyHistoricSales(DailyHistoricSales dailyHistoricSales){
		
		Store store = dailyHistoricSales.getStore();
		List<HalfHourHistoricSales> halfHourSalesList = dailyHistoricSales.getHalfHourHistoricSales();
		
		Date selectedDate = dailyHistoricSales.getSalesDate();
		Calendar calendarDate = CalendarUtils.getCalendar(selectedDate);
		int dayOfWeek = calendarDate.get(Calendar.DAY_OF_WEEK);
						
		UploadFile uploadFile = getUploadFile(dailyHistoricSales);
		
		getUploadFileDao().saveOrUpdate(uploadFile);
		
		for (HalfHourHistoricSales aHalfHourHistoricSales: halfHourSalesList){			
			HistoricSales aHistoricSales = new HistoricSales();
			aHistoricSales.setStore(store);
			aHistoricSales.setMainValue(aHalfHourHistoricSales.getValue());
			
			Date time = aHalfHourHistoricSales.getTime();
			aHistoricSales.setDateTime(CalendarUtils.setTimeToDate(selectedDate, time));
			aHistoricSales.setDayOfWeek(dayOfWeek);	
			aHistoricSales.setUploadFile(uploadFile);
			
			getHistoricSalesDao().saveOrUpdate(aHistoricSales);
		}
	}
	
	
	private UploadFile getUploadFile(DailyHistoricSales dailyHistoricSales) {
		UploadFile uploadFile = new UploadFile();
		DateTime dateTime = new DateTime();
		String fileName = "WEB_UI_" + dailyHistoricSales.getStore().getCode()+ "_"+
			dateTime.getHourOfDay() + dateTime.getMinuteOfDay() + dateTime.getSecondOfDay();
		uploadFile.setUploadType(UploadEnumType.WEB_UI);
		uploadFile.setFilename(fileName);
		uploadFile.setUploadDate(dateTime.toDate());
		return uploadFile;
	}
	
	
	/**
	 * @return the projectionService
	 */
	public ProjectionService getProjectionService() {
		return projectionService;
	}

	/**
	 * @param projectionService the projectionService to set
	 */
	public void setProjectionService(ProjectionService projectionService) {
		this.projectionService = projectionService;
	}

	/**
	 * @return the uploadFileDao
	 */
	private UploadFileDao getUploadFileDao() {
		return uploadFileDao;
	}
	
}
