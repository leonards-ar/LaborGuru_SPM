package com.laborguru.service.historicsales.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import com.laborguru.model.DailyHistoricSales;
import com.laborguru.model.HalfHourHistoricSales;
import com.laborguru.model.HistoricSales;
import com.laborguru.model.Store;
import com.laborguru.service.dao.hibernate.SpmHibernateDao;

/**
 * Hibernate implementation for Historic Sales Dao
 *  
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class HistoricSalesDaoHibernate extends SpmHibernateDao implements HistoricSalesDao {

	private static final Logger log = Logger.getLogger(HistoricSalesDaoHibernate.class);
	private static final String HISTORIC_SALES_NULL = "The historic sales object passed in as parameter is null";
	private static final String STORE_NULL = "The store passed in as parameter is null";
	private static final String DATE_NULL = "The date passed in as parameter is null";

	
	public void saveOrUpdate(HistoricSales hs) {
		
		if (hs == null){
			log.error(HISTORIC_SALES_NULL);
			throw new IllegalArgumentException(HISTORIC_SALES_NULL);			
		}
		
		getHibernateTemplate().saveOrUpdate(hs);
	}

	/**
	 * 
	 * @param store
	 * @param date
	 * @return
	 * @see com.laborguru.service.historicsales.dao.HistoricSalesDao#getDailyHistoricSales(com.laborguru.model.Store, java.util.Date)
	 */
	public DailyHistoricSales getDailyHistoricSales(Store store, Date date) {
		
		if (store == null){
			throw new IllegalArgumentException(STORE_NULL);
		}
		
		if (date == null){
			throw new IllegalArgumentException(DATE_NULL);
		}

		DateTime selectedDate = new DateTime(date);
		
		
		if(log.isDebugEnabled()) {
			log.debug("Before getting avg half hour sales - Parameters: Store Id:"+ store.getId()+" selectedDate:"+selectedDate.toString("yyyy-MM-dd"));
		}
		
		List halfHourSales = getHibernateTemplate().findByNamedQueryAndNamedParam("halfHourByDate",
			new String[]{"storeId","selectedDate"}, new Object[]{store.getId(),selectedDate.toString("yyyy-MM-dd")});

		DailyHistoricSales retValue = new DailyHistoricSales();
		
		retValue.setStore(store);
		retValue.setHistoricSalesDate(date);
	
		int size = halfHourSales.size();
	
		for (int i=0; i < size; i++){
			Object[] row = (Object[])halfHourSales.get(i);			
			String time = (String) row[0];
			BigDecimal value =  (BigDecimal)row[1];
			
			HalfHourHistoricSales halfHour = new HalfHourHistoricSales();
			halfHour.setTime(time);
			halfHour.setValue(value);
			
			retValue.addHalfHourHistoricSales(halfHour);
		}
		
		return retValue;
	}

}
