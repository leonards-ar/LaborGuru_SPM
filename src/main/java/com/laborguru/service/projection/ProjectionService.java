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

/**
 * Deals with the projection behaviour
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public interface ProjectionService extends Service{

	List<BigDecimal> getAvgDailyProjectionForAWeek(Integer numberOfWeeks, Store store, Date startWeekDate);

	List<BigDecimal> getAdjustedDailyProjectionForAWeek(Store store, Date startWeekDate);
	
	DailyProjection getDailyProjection(Store store, Date selectedDate);

	HalfHourElement calculateRevisedValue(HalfHourElement halfHourElement, BigDecimal totalAdjusted, BigDecimal totalProjected, BigDecimal percentageNotChangedHours);
	
	List<BigDecimal> calculateFixedDistribution(BigDecimal totalSales, BigDecimal totalAdjusted, BigDecimal totalChangedValues, List<BigDecimal> elements);

	List<HalfHourProjection> getAvgHalfHourProjection(Integer numberOfWeeks, BigDecimal amoutProjection, Store store, Date selectedDate);
	
	void saveDailyProjection(Store store, BigDecimal projectionAmount, Date selectedDate);
	
	void setProjectionDao(ProjectionDao projectionDao);
	
}
