package com.laborguru.service.projection;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import com.laborguru.frontend.model.HalfHourElement;
import com.laborguru.model.DailyProjection;
import com.laborguru.model.HalfHourProjection;
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

	private static final int DECIMAL_SCALE = 16;
	private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;
	private static final String INIT_VALUE_ZERO = "0.00";
	private static final int HALF_HOUR = 30;
	private static final int HALF_HOURS_IN_A_DAY=48;
	
	private ProjectionDao projectionDao;
	
	/**
	 * This method returns a list with the historic average sales value for a week, starting since the "startWeekDate" and using "numberOfWeeks" weeks as source for the
	 * calculations. 
	 * @param numberOfWeeks
	 * @param store
	 * @param startWeekDate
	 * @return
	 * @see com.laborguru.service.projection.ProjectionService#getAvgDailyProjectionForAWeek(java.lang.Integer, com.laborguru.model.Store, java.util.Date)
	 */
	public List<BigDecimal> getAvgDailyProjectionForAWeek(Integer numberOfWeeks, Store store, Date startWeekDate) {
		
		List<BigDecimal> retProjections = projectionDao.getAvgDailyProjectionForAWeek(numberOfWeeks, store, startWeekDate);
				
		return retProjections;
	}


	/**
	 * This methods returns a list with the adjusted daily projections values for a complete week (7 days) starting since the parameter "startWeekDate" 
	 * @param store
	 * @param startWeekDate
	 * @return
	 * @see com.laborguru.service.projection.ProjectionService#getAdjustedDailyProjectionForAWeek(com.laborguru.model.Store, java.util.Date)
	 */
	public List<DailyProjection> getAdjustedDailyProjectionForAWeek(Store store, Date startWeekDate) {		
		
		return projectionDao.getAdjustedDailyProjectionForAWeek(store, startWeekDate);	
	}

	/**
	 * @param store
	 * @param selectedDate
	 * @return
	 * @see com.laborguru.service.projection.ProjectionService#getDailyProjection(com.laborguru.model.Store, java.util.Date)
	 */
	public DailyProjection getDailyProjection(Store store, Date selectedDate) {
		
		return projectionDao.getDailyProjection(store, selectedDate);
	}
	

	/**
	 * @param store
	 * @param halfHourProjectionList
	 * @param selectedDate
	 */
	public void saveProjection(Store store, List<HalfHourProjection> halfHourProjectionList, Date selectedDate){
		DailyProjection projection =  projectionDao.getDailyProjection(store, selectedDate);
		
		if (projection == null){
			projection = new DailyProjection();
			projection.setProjectionDate(selectedDate);
			projection.setStore(store);

			for (HalfHourProjection aHalfHour: halfHourProjectionList){
				projection.addHalfHourProjection(aHalfHour);
			}
		} else {		
			int i=0;
			for (HalfHourProjection aHalfHour: projection.getHalfHourProjections()){
				aHalfHour.setAdjustedValue(halfHourProjectionList.get(i).getAdjustedValue());
				i++;
			}		
		}
		
		projection.setStartingTime(store.getStoreOperationTimeByDate(selectedDate).getOpenHour());

		projectionDao.save(projection);		
	}
	
	
	
	/**
	 * @param store
	 * @param selectedDate
	 * @return
	 */
	public void saveDailyProjection(Store store, BigDecimal projectionAmount, Date selectedDate, Date dateForCalculation) {
		List<HalfHourProjection> calculatedHalfHourList = calculateDailyHalfHourProjection(store, projectionAmount, dateForCalculation, store.getHalfHourProjectionsWeeksDefault());		
		saveProjection(store, calculatedHalfHourList, selectedDate);
	}	
	
	/**
	 * @param projectionAmount
	 * @param selectedDate
	 * @param store
	 */
	public List<HalfHourProjection> calculateDailyHalfHourProjection(Store store, BigDecimal projectionAmount, Date selectedDate, Integer numberOfWeeks){		
		
		//Getting the average half hours values for the last "numberOfWeeks" weeks.
		List<HalfHourProjection> avgCalculatedHalfHourList = getAvgHalfHourListByStoreAndDate(store, selectedDate, numberOfWeeks);
		
		BigDecimal totalAvgProjections = new BigDecimal(INIT_VALUE_ZERO);
		
		//Calculating the total
		for(HalfHourProjection halfHour: avgCalculatedHalfHourList){
			totalAvgProjections = totalAvgProjections.add(halfHour.getAdjustedValue());
		}		
				
		//Calculating the weight of each half hour (the distribution) and getting the value for the projection.
		//If total avg projections is zero, set all the values to zero.
		if (totalAvgProjections.doubleValue() > 0.0){
			for(HalfHourProjection halfHour: avgCalculatedHalfHourList){
				halfHour.setAdjustedValue(projectionAmount.multiply(halfHour.getAdjustedValue().divide(totalAvgProjections, DECIMAL_SCALE, ROUNDING_MODE)));			
			}
		} 
				
		//TODO:Put an assertion to check that the total is the same to projection amount.
		return avgCalculatedHalfHourList;
	}


	/**
	 * @param store
	 * @param selectedDate
	 * @param numberOfWeeks
	 * @return
	 */
	public List<HalfHourProjection> getAvgHalfHourListByStoreAndDate(Store store, Date selectedDate, Integer numberOfWeeks) {
		
		List<HalfHourProjection> avgProjections = projectionDao.getAvgHalfHourProjection(numberOfWeeks, store, selectedDate);
		
		return completeTimeHalfHourProjectionsList(avgProjections);		
	}
	
	/**
	 * @param avgProjections
	 * @param store
	 */
	private List<HalfHourProjection> completeTimeHalfHourProjectionsList(List<HalfHourProjection> avgProjections) {
		DateTime openHour = new DateTime().withDate(1970, 1, 1).withTime(0,30,0,0);
		DateTime closeHour = new DateTime().withDate(1970, 1, 2).withTime(0,0,0,0);
				
		List<HalfHourProjection> retList = new ArrayList<HalfHourProjection>(HALF_HOURS_IN_A_DAY);

		DateTime nextTime = new DateTime(openHour);
		
		int index=0;
		
		for(HalfHourProjection currentHalfHour: avgProjections) {
			DateTime currentTime = new DateTime(currentHalfHour.getTime().getTime());
			
			while(currentTime.isAfter(nextTime)) {
				HalfHourProjection aHalfHourProjection = new HalfHourProjection();
				aHalfHourProjection.setTime(nextTime.toDate());
				aHalfHourProjection.setAdjustedValue(new BigDecimal(INIT_VALUE_ZERO));
				aHalfHourProjection.setIndex(index++);
				retList.add(aHalfHourProjection);
				nextTime = nextTime.plusMinutes(HALF_HOUR);
			}
			
			currentHalfHour.setIndex(index++);
			retList.add(currentHalfHour);

			nextTime = nextTime.plusMinutes(HALF_HOUR);
		}
		
		
		while(nextTime.isBefore(closeHour) || nextTime.isEqual(closeHour)){
			HalfHourProjection aHalfHourProjection = new HalfHourProjection();
			aHalfHourProjection.setTime(nextTime.toDate());
			aHalfHourProjection.setAdjustedValue(new BigDecimal(INIT_VALUE_ZERO));
			aHalfHourProjection.setIndex(index++);
			retList.add(aHalfHourProjection);
			nextTime = nextTime.plusMinutes(HALF_HOUR);
		}
		
		return retList;
	}


	/**
	 * @param avgProjections
	 * @param openHour
	 * @return
	 *
	private List<HalfHourCalculated> getSubListStartingInOpenHour(List<HalfHourCalculated> avgProjections, DateTime openHour) {
		int startIndex=0;
		
		//Getting a sublist that starts in openHour time
		for(HalfHourCalculated halfHour: avgProjections) {
			DateTime halfHourTime = new DateTime(displayTimeToDate(halfHour.getTime()).getTime());
			if (halfHourTime.isAfter(openHour) || halfHourTime.isEqual(openHour)){
				break;
			}
			startIndex++;
		}
		
		List<HalfHourCalculated> auxListStartIndex = avgProjections.subList(startIndex, avgProjections.size());
		return auxListStartIndex;
	}/
	
	/**
	 * @param halfHourElement
	 * @param totalAdjusted
	 * @param totalProjected
	 * @param hoursNotChanged
	 * @return
	 * @see com.laborguru.service.projection.ProjectionService#calculateRevisedValue(com.laborguru.frontend.model.HalfHourElement, java.math.BigDecimal, java.math.BigDecimal, java.math.BigDecimal)
	 */
	public HalfHourElement calculateRevisedValue(HalfHourElement halfHourElement, BigDecimal totalAdjusted, BigDecimal totalProjected, BigDecimal hoursNotChanged){

		if (halfHourElement.getAdjustedValue() != null ) {
			halfHourElement.setRevisedValue(halfHourElement.getAdjustedValue());
		}else {
			BigDecimal auxVal = (new BigDecimal("1").subtract(totalAdjusted.divide(totalProjected, DECIMAL_SCALE, ROUNDING_MODE)));
			halfHourElement.setRevisedValue(auxVal.multiply(halfHourElement.getProjectedValue()).divide(hoursNotChanged, DECIMAL_SCALE, ROUNDING_MODE));
		}
		
		return halfHourElement;
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
	
	
}
