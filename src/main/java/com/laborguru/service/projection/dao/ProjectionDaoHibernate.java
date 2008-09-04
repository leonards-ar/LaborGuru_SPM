package com.laborguru.service.projection.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.laborguru.model.DailyProjection;
import com.laborguru.model.HalfHourCalculated;
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
		if(log.isDebugEnabled()){
			log.debug("Before getting adjusted daily projections - Parameters: Store Id:"+ store.getId()+" startDate:"+startTime.toString()+" endDate:"+endTime.toString());
		}
		
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
		
		if(log.isDebugEnabled()){
			log.debug("Before getting avg daily projections - Parameters: Store Id:"+ store.getId()+" startDate:"+startTime.toString()+" endDate:"+endTime.toString());
		}
		
		List<Double> avgSalesList = getHibernateTemplate().findByNamedParam("select avg(hs.salesValue) from HistoricSales hs " +
				"where hs.store.id=:storeId AND hs.dateTime >= :startDate AND hs.dateTime <= :endDate group by hs.dayOfWeek",
				new String[] {"storeId", "startDate", "endDate"}, new Object[] {store.getId(), startTime.toDate(), endTime.toDate()} );

		if(log.isDebugEnabled()){
			log.debug("After getting avg daily projections - Average Sales List size: Store Id:"+ avgSalesList.size());
		}
		
		List<BigDecimal> retSalesList = new ArrayList<BigDecimal>(avgSalesList.size());
		
		for (Double aValue: avgSalesList){
			retSalesList.add(new BigDecimal(aValue.toString()));
		}
		
		return retSalesList;
	}
	
	public DailyProjection getDailyProjection(Store store, Date selectedDate) {
		
		DateTime dt = new DateTime(selectedDate);
		dt = dt.withTime(0, 0, 0, 0);

		if(log.isDebugEnabled()) {
			log.debug("Before getting daily projections - Parameters: Store Id:"+ store.getId()+" selectedDate:"+dt.toString());
		}

		List<DailyProjection> projections =  getHibernateTemplate().findByNamedParam("from DailyProjection dp where dp.store.id=:storeId and dp.projectionDate=:projectionDate" , 
				new String[]{"storeId", "projectionDate"}, 
				new Object[]{store.getId(), dt.toDate()});
		
		if(projections.size() == 0) {
			return null;
		}
		
		if(projections.size() > 1) {
			log.warn("There is more than one result for store Id: " + store.getId() + " and date: " + selectedDate);
		}
		
		return projections.get(0);
	}
	
	public List<HalfHourCalculated> getAvgHalfHourProjection(Integer numberOfWeeks, Store store, Date selectedDate){
		
		DateTime startDate = new DateTime(selectedDate);
		
		DateTime startTime = startDate.minusWeeks(numberOfWeeks).withTime(0, 0, 0, 0);
		DateTime endTime = startDate.minusDays(1).withTime(23, 59, 59, 999);
		
		if(log.isDebugEnabled()) {
			log.debug("Before getting avg daily projections - Parameters: Store Id:"+ store.getId()+" startDate:"+startTime.toString("yyyy-MM-dd HH:mm:ss") + " endDate:" + endTime.toString("yyyy-MM-dd HH:mm:ss") + " dayOfWeek:" + startDate.getDayOfWeek());
		}
		
		List sumProjections = getHibernateTemplate().findByNamedQueryAndNamedParam("halfHourProjections", new String[]{"storeId","startDate","endDate","dayOfWeek"}, new Object[]{store.getId(),startTime.toString("yyyy-MM-dd HH:mm:ss"),endTime.toString("yyyy-MM-dd HH:mm:ss"),startDate.getDayOfWeek()});
	
		
		List<HalfHourCalculated> projections = new ArrayList<HalfHourCalculated>();
		
		Iterator i = sumProjections.iterator();
		
		while ( i.hasNext() ) {
			Object[] row = (Object[]) i.next();
			
			BigDecimal number = new BigDecimal((Double)row[1]/numberOfWeeks.intValue());
			HalfHourCalculated hhc = new HalfHourCalculated((String)row[0], number);
			projections.add(hhc);

		}
		return sumProjections;

	}

}
