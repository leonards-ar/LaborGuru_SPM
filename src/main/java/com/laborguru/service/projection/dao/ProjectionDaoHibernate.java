package com.laborguru.service.projection.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.laborguru.model.DailyProjection;
import com.laborguru.model.HalfHourProjection;
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

	private static final int DECIMAL_SCALE = 16;
	private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;
	private static final String INIT_VALUE_ZERO = "0.00";

	/**
	 * 
	 * @param store
	 * @param startWeekDate
	 * @return
	 * @see com.laborguru.service.projection.dao.ProjectionDao#getAdjustedDailyProjectionForAWeek(com.laborguru.model.Store, java.util.Date)
	 */
	public List<DailyProjection> getAdjustedDailyProjectionForAWeek(Store store, Date startWeekDate) {
		
		DateTime startDate = new DateTime(startWeekDate);
		
		DateTime startTime = startDate.withTime(0, 0, 0, 0);
		DateTime endTime = startDate.plusDays(6).withTime(23, 59, 59, 999);
		if(log.isDebugEnabled()){
			log.debug("Before getting adjusted daily projections - Parameters: Store Id:"+ store.getId()+" startDate:"+startTime.toString()+" endDate:"+endTime.toString());
		}
		
		List<DailyProjection> projections  = (List<DailyProjection>)  getHibernateTemplate().findByNamedParam("from DailyProjection dp " +
				"where dp.store.id=:storeId AND dp.projectionDate >= :startDate AND dp.projectionDate <= :endDate",
				new String[] {"storeId", "startDate", "endDate"}, new Object[] {store.getId(), startTime.toDate(), endTime.toDate()} );		
		
		if(log.isDebugEnabled()){
			log.debug("After getting adjusted daily projections - Average sales list size: Store Id:"+ projections.size());
		}
				
		//if projections are less than 7
		//we complete the days for which there is no projections.		
		if ( projections.size() < 7){
			
			List<DailyProjection> completedProjected = new ArrayList<DailyProjection>(7);

			//if projection list is empty, we fill it with empty daily projections
			if (projections.isEmpty()){
				for (int i=0; i < 7; i++){				
					completedProjected.add(new DailyProjection());
				}
			}else{			
				DateTime auxDateTime = startTime;									
				for (int i=0, j =0; i < 7; i++){
					DailyProjection auxProj = new DailyProjection();
					
					if(j < projections.size() ){
						DateTime elementDateTime = new DateTime(projections.get(j).getProjectionDate()).withTime(0, 0, 0, 0);						
						if (elementDateTime.getDayOfWeek() == auxDateTime.getDayOfWeek()){
							auxProj = projections.get(j++);
						}	
					}
					completedProjected.add(auxProj);
					
					auxDateTime = auxDateTime.plusDays(1);
				}
			}
			
			return completedProjected;			
		}
		
		
		return projections;
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
		
		List<Object[]> avgSalesList = getHibernateTemplate().findByNamedParam("select distinct sum(hs.mainValue), hs.dayOfWeek from HistoricSales hs " +
				"where hs.store.id=:storeId AND hs.dateTime >= :startDate AND hs.dateTime <= :endDate group by hs.dayOfWeek",
				new String[] {"storeId", "startDate", "endDate"}, new Object[] {store.getId(), startTime.toDate(), endTime.toDate()} );

		if(log.isDebugEnabled()){
			log.debug("After getting avg daily projections - Average Sales List size: Store Id:"+ avgSalesList.size());
		}
		
		List<BigDecimal> retSalesList = new ArrayList<BigDecimal>(7);
		
		for (int i=1, j=0; i < 8; i++){
			BigDecimal aValue = new BigDecimal(INIT_VALUE_ZERO);
			
			if (j < avgSalesList.size()){
				if (i == ((Integer)(avgSalesList.get(j)[1])).intValue()){
					aValue = ((BigDecimal)avgSalesList.get(j)[0]).divide(BigDecimal.valueOf(numberOfWeeks), DECIMAL_SCALE, ROUNDING_MODE);
					j++;
				}
			}
			
			retSalesList.add(aValue);
		}
		
		return retSalesList;
	}
	
	/**
	 * 
	 * @param store
	 * @param selectedDate
	 * @return
	 * @see com.laborguru.service.projection.dao.ProjectionDao#getDailyProjection(com.laborguru.model.Store, java.util.Date)
	 */
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
	
	/**
	 * 
	 * @param store
	 * @param startDate
	 * @param endDate
	 * @return
	 * @see com.laborguru.service.projection.dao.ProjectionDao#getDailyProjections(com.laborguru.model.Store, java.util.Date, java.util.Date)
	 */
	public List<DailyProjection> getDailyProjections(Store store, Date startDate, Date endDate) {
		DateTime from = new DateTime(startDate).withTime(0, 0, 0, 0);
		DateTime to = new DateTime(endDate).withTime(23, 59, 59, 999);
		
		if(log.isDebugEnabled()){
			log.debug("Before getting daily projections - Parameters: Store Id:" + store.getId() + " startDate: " + from.toString()+ " endDate: " + to.toString());
		}

		List<DailyProjection> projections =  (List<DailyProjection>) getHibernateTemplate().findByNamedParam("from DailyProjection dp where dp.store.id=:storeId and dp.projectionDate >= :fromProjectionDate and dp.projectionDate <= :toProjectionDate", 
				new String[]{"storeId", "fromProjectionDate", "toProjectionDate"}, 
				new Object[]{store.getId(), from.toDate(), to.toDate()});
		
		if(log.isDebugEnabled()) {
			log.debug("Retrieved " + projections.size() + " daily projections - Parameters: Store Id:" + store.getId() + " startDate: " + from.toString()+ " endDate: " + to.toString());
		}
		
		return projections;
	}
	
	/**
	 * 
	 * @param numberOfWeeks
	 * @param store
	 * @param selectedDate
	 * @return
	 * @see com.laborguru.service.projection.dao.ProjectionDao#getAvgHalfHourProjection(java.lang.Integer, com.laborguru.model.Store, java.util.Date)
	 */
	public List<HalfHourProjection> getAvgHalfHourProjection(Integer numberOfWeeks, Store store, Date selectedDate){
		
		DateTime startDate = new DateTime(selectedDate);
		
		DateTime startTime = startDate.minusWeeks(numberOfWeeks).withTime(0, 0, 0, 0);
		DateTime endTime = startDate.minusDays(1).withTime(23, 59, 59, 999);
		
		Calendar calendarDate = Calendar.getInstance();
		calendarDate.setTime(selectedDate);
		
		if(log.isDebugEnabled()) {
			log.debug("Before getting avg half hour projections - Parameters: Store Id:"+ store.getId()+" startDate:"+startTime.toString("yyyy-MM-dd HH:mm:ss") + " endDate:" + endTime.toString("yyyy-MM-dd HH:mm:ss") + " dayOfWeek:" + startDate.getDayOfWeek());
		}
		
		List sumProjections = getHibernateTemplate().findByNamedQueryAndNamedParam(
				"halfHourProjections", 
				new String[]{"storeId","startDate","endDate","dayOfWeek"}, 
				new Object[]{store.getId(),startTime.toString("yyyy-MM-dd HH:mm:ss"),endTime.toString("yyyy-MM-dd HH:mm:ss"), calendarDate.get(Calendar.DAY_OF_WEEK)});
	
		
		List<HalfHourProjection> projections = new LinkedList<HalfHourProjection>();
		
		Iterator<Object[]> iterator = sumProjections.iterator();
		BigDecimal numberOfWeeksAux = new BigDecimal(numberOfWeeks);
		
		while ( iterator.hasNext() ) {
			Object[] row = (Object[]) iterator.next();			
			BigDecimal number =  ((BigDecimal) (row[1])).divide(numberOfWeeksAux, DECIMAL_SCALE, ROUNDING_MODE);
			HalfHourProjection hhp = new HalfHourProjection();
			
			hhp.setTime((String) row[0]);
			hhp.setAdjustedValue(number);
			projections.add(hhp);
			
		}
		return projections;

	}

	/**
	 * @param projection
	 * @see com.laborguru.service.projection.dao.ProjectionDao#save(com.laborguru.model.DailyProjection)
	 */
	public void save(DailyProjection projection) {
		
		DateTime dt = new DateTime(projection.getProjectionDate());
		dt = dt.withTime(0, 0, 0, 0);
		projection.setProjectionDate(dt.toDate());
		
		getHibernateTemplate().save(projection);
	}

}
