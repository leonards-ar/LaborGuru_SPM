package com.laborguru.service.projection;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
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
import com.laborguru.util.SpmConstants;

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
				retProjections.add(new BigDecimal("0"));
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
				retProjections.add(new BigDecimal("0"));
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
		
		BigDecimal totalAvgProjections = new BigDecimal(0);
		
		for(HalfHourCalculated halfHour: avgCalculatedHalfHourList){
			totalAvgProjections = totalAvgProjections.add(halfHour.getValue());
		}		
		
		List<HalfHourProjection> projectionHalfHourList = new ArrayList<HalfHourProjection>(avgCalculatedHalfHourList.size());
		
		int index=0;
		
		for(HalfHourCalculated halfHour: avgCalculatedHalfHourList){
			HalfHourProjection aHalfHourProjection = new HalfHourProjection();
			aHalfHourProjection.setAdjustedValue(projectionAmount.multiply(halfHour.getValue().divide(totalAvgProjections, 2, RoundingMode.HALF_DOWN)));
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
				HalfHourCalculated aHalfHourCalculated = new HalfHourCalculated(nextTime.toString("HH:mm"), new BigDecimal("0"));
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
			HalfHourCalculated aHalfHourCalculated = new HalfHourCalculated(nextTime.toString("HH:mm"), new BigDecimal("0"));
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
		
		DateTime selectedDateJoda = new DateTime(selectedDate.getTime());
		OperationTime operationTime = OperationTime.getOperationTimeByDayOfWeek(store.getOperationTimes(), DayOfWeek.values()[selectedDateJoda.getDayOfWeek()]);
		
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
			BigDecimal newValue = new BigDecimal(halfHourElement.getValue().doubleValue());
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
	
	private int getIndex(DateTime hour, DateTime startHour) {
		DateTime indexHour = hour.minusMinutes(startHour.getMinuteOfDay() + startHour.getMinuteOfHour());
		return (indexHour.getMinuteOfDay() + indexHour.getMinuteOfHour()) / HALF_HOUR;
		
	}
	
	
}
