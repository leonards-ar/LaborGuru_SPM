package com.laborguru.service.projection.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.laborguru.model.DailyProjection;
import com.laborguru.model.HalfHourCalculated;
import com.laborguru.model.Store;

public interface ProjectionDao {
	
	List<BigDecimal> getAvgDailyProjectionForAWeek(Integer numberOfWeeks, Store store, Date startWeekDate);

	List<DailyProjection> getAdjustedDailyProjectionForAWeek(Store store, Date startWeekDate);
	
	DailyProjection getDailyProjection(Store store, Date selectedDate);
	
	List<HalfHourCalculated> getAvgHalfHourProjection(Integer numberOfWeeks, Store store, Date selectedDate);

	void save(DailyProjection projection);
}
