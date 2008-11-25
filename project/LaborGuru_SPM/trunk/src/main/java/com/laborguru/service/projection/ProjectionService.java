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
public interface ProjectionService extends Service{

	List<BigDecimal> getAvgDailyProjectionForAWeek(Integer numberOfWeeks, Store store, Date startWeekDate);

	List<DailyProjection> getAdjustedDailyProjectionForAWeek(Store store, Date startWeekDate);
	
	DailyProjection getDailyProjection(Store store, Date selectedDate);

	HalfHourElement calculateRevisedValue(HalfHourElement halfHourElement, BigDecimal totalAdjusted, BigDecimal totalProjected, BigDecimal percentageNotChangedHours);
	
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
	
	void saveProjection(Store store, List<HalfHourProjection> halfHourProjectionList, Date selectedDate);

	void setProjectionDao(ProjectionDao projectionDao);
	
	void setStaffingService(StaffingService staffingService);
}
