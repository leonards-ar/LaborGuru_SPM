package com.laborguru.service.projection.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.laborguru.model.DailyProjection;
import com.laborguru.model.Store;

/**
 *
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class ProjectionDaoHibernate extends HibernateDaoSupport implements ProjectionDao {

	private static final Logger log = Logger.getLogger(ProjectionDaoHibernate.class);	

	public List<DailyProjection> getAdjustedDailyProjectionForAWeek(Store store, Date startWeekDate) {
		
		DateTime startDate = new DateTime(startWeekDate);
		
		DateTime startTime = startDate.withTime(0, 0, 0, 0);
		DateTime endTime = startDate.plusDays(6).withTime(23, 59, 59, 999);
		
		log.debug("Before getting adjusted daily projections - Parameters: Store Id:"+ store.getId()+" startDate:"+startTime.toString()+" endDate:"+endTime.toString());
		
		return (List<DailyProjection>)  getHibernateTemplate().findByNamedParam("from DailyProjection dp " +
				"where dp.store.id=:storeId AND dp.projectionDate >= :startDate AND dp.projectionDate <= :endDate",
				new String[] {"storeId", "startDate", "endDate"}, new Object[] {store.getId(), startTime.toDate(), endTime.toDate()} );		
	}

	/**
	 * Returns the average daily projection values.
	 * 
	 * @param numberOfWeeks
	 * @param store
	 * @param startWeekDate
	 * @return
	 * @see com.laborguru.service.projection.dao.ProjectionDao#getAvgDailyProjectionForAWeek(java.lang.Integer, com.laborguru.model.Store, java.util.Date)
	 */
	public List<BigDecimal> getAvgDailyProjectionForAWeek(Integer numberOfWeeks, Store store, Date startWeekDate) {
		
		DateTime startDate = new DateTime(startWeekDate);
		
		DateTime startTime = startDate.minusWeeks(numberOfWeeks).withTime(0, 0, 0, 0);
		DateTime endTime = startDate.minusDays(1).withTime(23, 59, 59, 999);
		
		log.debug("Before getting avg daily projections - Parameters: Store Id:"+ store.getId()+" startDate:"+startTime.toString()+" endDate:"+endTime.toString());
		
		List<Double> avgSalesList = getHibernateTemplate().findByNamedParam("select avg(hs.salesValue) from HistoricSales hs " +
				"where hs.store.id=:storeId AND hs.dateTime >= :startDate AND hs.dateTime <= :endDate group by hs.dayOfWeek",
				new String[] {"storeId", "startDate", "endDate"}, new Object[] {store.getId(), startTime.toDate(), endTime.toDate()} );

		log.debug("After getting avg daily projections - Average Sales List size: Store Id:"+ avgSalesList.size());
		
		List<BigDecimal> retSalesList = new ArrayList<BigDecimal>(avgSalesList.size());
		
		for (Double aValue: avgSalesList){
			retSalesList.add(new BigDecimal(aValue.toString()));
		}
		
		return retSalesList;
	}

}
