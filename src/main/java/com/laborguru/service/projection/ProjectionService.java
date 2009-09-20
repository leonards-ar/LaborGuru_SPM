package com.laborguru.service.projection;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.laborguru.frontend.model.HalfHourElement;
import com.laborguru.model.DailyProjection;
import com.laborguru.model.HalfHourProjection;
import com.laborguru.model.Store;
import com.laborguru.service.Service;
import com.laborguru.service.projection.dao.ProjectionDao;
import com.laborguru.service.staffing.StaffingService;

/**
 * Deals with the projection behaviour
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public interface ProjectionService extends Service {

	/**
	 * 
	 * @param numberOfWeeks
	 * @param store
	 * @param startWeekDate
	 * @return
	 */
	List<BigDecimal> getAvgDailyProjectionForAWeek(Integer numberOfWeeks, Store store, Date startWeekDate); 

	/**
	 * 
	 * @param store
	 * @param startWeekDate
	 * @return
	 */
	List<DailyProjection> getAdjustedDailyProjectionForAWeek(Store store, Date startWeekDate);
	
	/**
	 * 
	 * @param store
	 * @param selectedDate
	 * @return
	 */
	DailyProjection getDailyProjection(Store store, Date selectedDate);

	/**
	 * This method returns the projection value for a week, starting since the "startWeekDate" and using "numberOfWeeks" weeks as source for the
	 * calculations. It takes the distribution to used in the calculations form the store field distribution type. 
	 * TODO: if more optional paramaters depending on the projection are needed, consider refactor and introduce a parameterObject or
	 * a builder.
	 * @param numberOfWeeks This field is optional depending on the distribution type of the store.
	 * @param store
	 * @param startWeekDate
	 * @return
	 * @see com.laborguru.service.projection.ProjectionService#getAvgDailyProjectionForAWeek(java.lang.Integer, com.laborguru.model.Store, java.util.Date)
	 */
	List<BigDecimal> calculateWeeklyProjectionValues(Integer numberOfWeeks, Store store, Date startWeekDate);
	
	/**
	 * 
	 * @param halfHourElement
	 * @param totalAdjusted
	 * @param totalProjected
	 * @param percentageNotChangedHours
	 * @return
	 */
	HalfHourElement calculateRevisedValue(HalfHourElement halfHourElement, BigDecimal totalAdjusted, BigDecimal totalProjected, BigDecimal percentageNotChangedHours);
	
	/**
	 * 
	 * @param store
	 * @param projectionAmount
	 * @param selectedDate
	 * @param numberOfWeeks
	 * @return
	 */
	List<HalfHourProjection> calculateDailyHalfHourProjection(Store store, BigDecimal projectionAmount, Date selectedDate, Integer numberOfWeeks);
		
	/**
	 * This method calculate and saves the set of half hour projections for a day.
	 * 
	 * @param store the store
	 * @param projectionAmount total amount for the day
	 * @param selectedDate save projection date
	 * @param dateForCalculation date in which we based the avg calculation for the projection
	 */
	void saveDailyProjection(Store store, BigDecimal projectionAmount, Date selectedDate, Date dateForCalculation);
	
	/**
	 * 
	 * @param store
	 * @param halfHourProjectionList
	 * @param selectedDate
	 */
	void saveProjection(Store store, List<HalfHourProjection> halfHourProjectionList, Date selectedDate);

	/**
	 * 
	 * @param projectionDao
	 */
	void setProjectionDao(ProjectionDao projectionDao);

	/**
	 * 
	 * @param store
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<DailyProjection> getDailyProjections(Store store, Date startDate, Date endDate);
	
	/**
	 * 
	 * @param staffingService
	 */
	void setStaffingService(StaffingService staffingService);
	
	/**
	 * 
	 * @return
	 */
	List<DailyProjection> updateAll();
}
