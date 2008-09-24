package com.laborguru.service.projection;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import com.laborguru.frontend.model.HalfHourElement;
import com.laborguru.model.DailyProjection;
import com.laborguru.model.HalfHourCalculated;
import com.laborguru.model.HalfHourProjection;
import com.laborguru.model.OperationTime;
import com.laborguru.model.Store;
import com.laborguru.service.projection.dao.ProjectionDao;
import com.laborguru.util.SpmConstants;

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
		
		if (retProjections == null  || retProjections.isEmpty()){
			retProjections = new ArrayList<BigDecimal>(7);
		
			for (int i=0; i<7; i++){
				retProjections.add(new BigDecimal(INIT_VALUE_ZERO));
			}
		}
		
		return retProjections;
	}


	/**
	 * This methods returns a list with the adjusted daily projections values for a complete week (7 days) starting since the parameter "startWeekDate" 
	 * @param store
	 * @param startWeekDate
	 * @return
	 * @see com.laborguru.service.projection.ProjectionService#getAdjustedDailyProjectionForAWeek(com.laborguru.model.Store, java.util.Date)
	 */
	public List<BigDecimal> getAdjustedDailyProjectionForAWeek(Store store, Date startWeekDate) {		
		
		List<DailyProjection> projections = projectionDao.getAdjustedDailyProjectionForAWeek(store, startWeekDate);
		List<BigDecimal> retProjections = new ArrayList<BigDecimal>(7);
		
		if (projections == null  || projections.isEmpty()){
		
			for (int i=0; i<7; i++){
				retProjections.add(new BigDecimal(INIT_VALUE_ZERO));
			}
		} else {
			for (DailyProjection aProjection: projections){
				retProjections.add(aProjection.getDailyProjectionValue());
			}			
		}
		
		return retProjections;	
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
	 * @param selectedDate
	 * @return
	 */
	public void saveDailyProjection(Store store, BigDecimal projectionAmount, Date selectedDate) {
		
		DailyProjection projection =  projectionDao.getDailyProjection(store, selectedDate);
		
		if (projection == null){
			projection = new DailyProjection();
			projection.setProjectionDate(selectedDate);
			projection.setStore(store);
		} else {		
			HalfHourProjection[] tmpProjections = projection.getHalfHourProjections().toArray(new HalfHourProjection[projection.getHalfHourProjections().size()]);
			
			for (int i=0; i < tmpProjections.length; i++){
				projection.removeHalfHourProjection(tmpProjections[i]);
			}		
		}
				
		List<HalfHourProjection> calculatedHalfHourList = calculateDailyHalfHourProjection(store, projectionAmount, selectedDate, store.getHalfHourProjectionsWeeksDefault());		
		
		for (HalfHourProjection aHalfHour: calculatedHalfHourList){
			projection.addHalfHourProjection(aHalfHour);
		}
		
		projection.setStartingTime(store.getStoreOperationTimeByDate(selectedDate).getOpenHour());

		projectionDao.save(projection);
	}	
	
	/**
	 * @param projectionAmount
	 * @param selectedDate
	 * @param store
	 */
	public List<HalfHourProjection> calculateDailyHalfHourProjection(Store store, BigDecimal projectionAmount, Date selectedDate, Integer numberOfWeeks){
		
		List<HalfHourCalculated> avgCalculatedHalfHourList = getAvgHalfHourListByStoreAndDate(store, selectedDate, numberOfWeeks);
		
		BigDecimal totalAvgProjections = new BigDecimal(INIT_VALUE_ZERO);
		
		for(HalfHourCalculated halfHour: avgCalculatedHalfHourList){
			totalAvgProjections = totalAvgProjections.add(halfHour.getValue());
		}		
		
		List<HalfHourProjection> projectionHalfHourList = new ArrayList<HalfHourProjection>(avgCalculatedHalfHourList.size());
		
		int index=0;
		
		for(HalfHourCalculated halfHour: avgCalculatedHalfHourList){
			HalfHourProjection aHalfHourProjection = new HalfHourProjection();
			
			if (totalAvgProjections.doubleValue() > 0.0){
				aHalfHourProjection.setAdjustedValue(projectionAmount.multiply(halfHour.getValue().divide(totalAvgProjections, DECIMAL_SCALE, ROUNDING_MODE)));				
			}else {
				aHalfHourProjection.setAdjustedValue(new BigDecimal(INIT_VALUE_ZERO));
			}
			
			aHalfHourProjection.setIndex(index++);
			projectionHalfHourList.add(aHalfHourProjection);
		}		
		
		//TODO:Put an assertion to check that the total is the same to projection amount.
		return projectionHalfHourList;
	}


	/**
	 * @param store
	 * @param selectedDate
	 * @param numberOfWeeks
	 * @return
	 */
	public List<HalfHourCalculated> getAvgHalfHourListByStoreAndDate(Store store, Date selectedDate, Integer numberOfWeeks) {
		
		//TODO:Throw an exception if operation time is not set
		List<HalfHourCalculated> avgProjections = projectionDao.getAvgHalfHourProjection(numberOfWeeks, store, selectedDate);
		
		return  completeHalfHourProjectionsList(avgProjections, store.getStoreOperationTimeByDate(selectedDate));
		
	}
	
	/**
	 * @param avgProjections
	 * @param store
	 */
	private List<HalfHourCalculated> completeHalfHourProjectionsList(List<HalfHourCalculated> avgProjections, OperationTime operationTime) {
		DateTime openHour = new DateTime(operationTime.getOpenHour().getTime());
		DateTime closeHour = new DateTime(operationTime.getCloseHour().getTime());
				
		List<HalfHourCalculated> auxListStartIndex = getSubListStartingInOpenHour(avgProjections, openHour);		
		List<HalfHourCalculated> retList = new ArrayList<HalfHourCalculated>(auxListStartIndex.size());

		DateTime nextTime = new DateTime(openHour);
		
		for(HalfHourCalculated currentHalfHour: auxListStartIndex) {
			DateTime currentTime = new DateTime(displayTimeToDate(currentHalfHour.getTime()).getTime());
			
			while(currentTime.isAfter(nextTime)) {
				HalfHourCalculated aHalfHourCalculated = new HalfHourCalculated(nextTime.toString("HH:mm"), new BigDecimal(INIT_VALUE_ZERO));
				retList.add(aHalfHourCalculated);
				nextTime = nextTime.plusMinutes(HALF_HOUR);
			}

			if (currentTime.isAfter(closeHour)){
				break;
			}
			
			HalfHourCalculated aHalfHourCalculated = new HalfHourCalculated(currentTime.toString("HH:mm"), currentHalfHour.getValue());
			retList.add(aHalfHourCalculated);
			nextTime = nextTime.plusMinutes(HALF_HOUR);
		}
		
		
		while(nextTime.isBefore(closeHour) || nextTime.isEqual(closeHour)){
			HalfHourCalculated aHalfHourCalculated = new HalfHourCalculated(nextTime.toString("HH:mm"), new BigDecimal(INIT_VALUE_ZERO));
			retList.add(aHalfHourCalculated);
			nextTime = nextTime.plusMinutes(HALF_HOUR);
		}
		
		return retList;
	}


	/**
	 * @param avgProjections
	 * @param openHour
	 * @return
	 */
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
	}
	
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
	 * 
	 * @param time
	 * @return
	 */
	private Date displayTimeToDate(String time) {
		try {
			return SpmConstants.TIME_FORMAT.parse(time);
		} catch (ParseException ex) {
			//TODO: log.error("Cannot parse time [" + time + "]", ex);
			return null;
		}
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
