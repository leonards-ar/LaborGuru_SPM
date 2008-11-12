package com.laborguru.service.historicsales.dao;

import org.apache.log4j.Logger;

import com.laborguru.model.HistoricSales;
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

	
	public void saveOrUpdate(HistoricSales hs) {
		
		if (hs == null){
			log.error(HISTORIC_SALES_NULL);
			throw new IllegalArgumentException(HISTORIC_SALES_NULL);			
		}
		
		getHibernateTemplate().saveOrUpdate(hs);
	}

}
