package com.laborguru.service.projection;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import com.laborguru.model.DailyProjection;
import com.laborguru.model.DayOfWeek;
import com.laborguru.model.HalfHourCalculated;
import com.laborguru.model.HalfHourProjection;
import com.laborguru.model.OperationTime;
import com.laborguru.model.Store;
import com.laborguru.service.projection.dao.ProjectionDao;

/**
 *
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class ProjectionServiceBean implements ProjectionService {

	private static final int HALF_HOUR = 30;
	
	private ProjectionDao projectionDao;

	public List<BigDecimal> getAvgDailyProjectionForAWeek(Integer numberOfWeeks, Store store, Date startWeekDate) {
		
		List<BigDecimal> retProjections = projectionDao.getAvgDailyProjectionForAWeek(numberOfWeeks, store, startWeekDate);
		
		if (retProjections == null  || retProjections.isEmpty()){
			retProjections = new ArrayList<BigDecimal>(7);
		
			for (int i=0; i<7; i++){
				retProjections.add(new BigDecimal("0"));
			}
		}
		
		return retProjections;
	}


	public List<BigDecimal> getAdjustedDailyProjectionForAWeek(Store store, Date startWeekDate) {		
		
		List<DailyProjection> projections = projectionDao.getAdjustedDailyProjectionForAWeek(store, startWeekDate);
		List<BigDecimal> retProjections = new ArrayList<BigDecimal>(7);
		
		if (projections == null  || projections.isEmpty()){
		
			for (int i=0; i<7; i++){
				retProjections.add(new BigDecimal("0"));
			}
		} else {
			for (DailyProjection aProjection: projections){
				retProjections.add(aProjection.getDailyProjectionValue());
			}			
		}
		
		return retProjections;	
	}

	public DailyProjection getDailyProjection(Store store, Date selectedDate) {
		
		return projectionDao.getDailyProjection(store, selectedDate);
	}
	
	public List<BigDecimal> calculateFixedDistribution(BigDecimal totalSales, BigDecimal totalAdjusted, BigDecimal totalChangedValues, List<BigDecimal> elements){

		List<BigDecimal> calculatedValues = new ArrayList<BigDecimal>();
		
		BigDecimal porcentajeAdjusted = totalAdjusted.divide(totalSales, 16, BigDecimal.ROUND_UP).subtract(new BigDecimal(1));
		BigDecimal porcentajeNotChanged = totalChangedValues.divide(totalSales, 16, BigDecimal.ROUND_UP).subtract(new BigDecimal(1));
		
		for(BigDecimal value: elements) {
			calculatedValues.add(value.multiply(porcentajeAdjusted.divide(porcentajeNotChanged, 16, BigDecimal.ROUND_HALF_EVEN)).setScale(16, BigDecimal.ROUND_HALF_EVEN)); 
			
		}
		
		return calculatedValues;
	}
	
	public List<HalfHourProjection> getAvgHalfHourProjection(Integer numberOfWeeks, BigDecimal amountProjection, Store store, Date selectedDate){
		List<HalfHourCalculated> avgProjections = projectionDao.getAvgHalfHourProjection(numberOfWeeks, store, selectedDate);
		
		List<HalfHourProjection> projections = new ArrayList<HalfHourProjection>();
		
		OperationTime operationTime = OperationTime.getOperationTimeByDayOfWeek(store.getOperationTimes(), DayOfWeek.values()[selectedDate.getDay()]);
		
		BigDecimal sumProjections = new BigDecimal("0");
		
		DateTime openHour = new DateTime(operationTime.getOpenHour());
		DateTime nextHour = new DateTime(openHour);

		for(HalfHourCalculated halfHourElement: avgProjections) {
			DateTime hour = new DateTime(halfHourElement.getTime());
			if(hour.getHourOfDay() > nextHour.getHourOfDay()){
				while(hour.getHourOfDay() > nextHour.getHourOfDay()) {
					HalfHourProjection aHalfHourProjection = new HalfHourProjection();
					aHalfHourProjection.setAdjustedValue(new BigDecimal("0"));
					int index = getIndex(nextHour, openHour);
					aHalfHourProjection.setIndex(index);
					projections.add(aHalfHourProjection);
					nextHour = nextHour.plusMinutes(HALF_HOUR);
				}
			}
			HalfHourProjection halfHourProjection = new HalfHourProjection();
			BigDecimal newValue = new BigDecimal(halfHourElement.getValue().doubleValue() / numberOfWeeks.intValue());
			sumProjections = sumProjections.add(newValue);
			halfHourProjection.setAdjustedValue(newValue);
			halfHourProjection.setIndex(getIndex(new DateTime(halfHourElement.getTime()), openHour));
			projections.add(halfHourProjection);
			nextHour = nextHour.plusMinutes(HALF_HOUR);
		}
		
		for(int i=0; i < projections.size(); i++) {
			BigDecimal calculatedValue = projections.get(i).getAdjustedValue().multiply(amountProjection.divide(sumProjections));
			projections.get(i).setAdjustedValue(calculatedValue);
		}
		return projections;
	}
	
	
	
	/**
	 * @return the projectionDao
	 */
	public ProjectionDao getProjectionDao() {
		return projectionDao;
	}

	/**
	 * @param projectionDao the projectionDao to set
	 */
	public void setProjectionDao(ProjectionDao projectionDao) {
		this.projectionDao = projectionDao;
	}
	
	private int getIndex(DateTime hour, DateTime startHour) {
		DateTime indexHour = hour.minusMinutes(startHour.getMinuteOfDay() + startHour.getMinuteOfHour());
		return (indexHour.getMinuteOfDay() + indexHour.getMinuteOfHour()) / HALF_HOUR;
		
	}
	
	
}
