package com.laborguru.service.projection;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import com.laborguru.frontend.model.HalfHourElement;
import com.laborguru.model.DailyProjection;
import com.laborguru.model.HalfHourProjection;
import com.laborguru.model.OperationTime;
import com.laborguru.model.Store;
import com.laborguru.service.projection.dao.ProjectionDao;
import com.laborguru.service.staffing.StaffingService;
import com.laborguru.util.SpmConstants;

/**
 *
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class ProjectionServiceBean implements ProjectionService {

	private ProjectionDao projectionDao;
	private StaffingService staffingService;
	
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
	 * 
	 * @param store
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<DailyProjection> getDailyProjections(Store store, Date startDate, Date endDate) {
		return getProjectionDao().getDailyProjections(store, startDate, endDate);
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
			projection.setSalesDate(selectedDate);
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
		
		//Delete staffing calculations associated with the projection saved.
		staffingService.updateDailyStaffingForDate(store,selectedDate);
	}
	/**
	 * 
	 * @return
	 * @see com.laborguru.service.projection.ProjectionService#updateAll()
	 */
	public List<DailyProjection> updateAll() {
		List<DailyProjection> projections = getProjectionDao().loadAll();
		
		for(DailyProjection projection: projections) {
			getStaffingService().deleteDailyStaffingForDate(projection.getStore(), projection.getProjectionDate());
			getStaffingService().getDailyStaffingByDate(projection.getStore(), projection.getProjectionDate());
			getProjectionDao().save(projection);
		}
		
		return projections;
	}
	
	

	/**
	 * This method calculate and saves the set of half hour projections for a day.
	 * 
	 * @param store the store
	 * @param projectionAmount total amount for the day
	 * @param selectedDate save projection date
	 * @param dateForCalculation date in which we based the avg calculation for the projection
	 * @see com.laborguru.service.projection.ProjectionService#saveDailyProjection(com.laborguru.model.Store, java.math.BigDecimal, java.util.Date, java.util.Date)
	 */
	public void saveDailyProjection(Store store, BigDecimal projectionAmount, Date selectedDate, Date dateForCalculation) {
		
		if (store.getHalfHourProjectionsWeeksDefault() == null){
			throw new IllegalArgumentException("HalfHourProjectionsWeeksDefault cannot be null");
		}
		
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
		
		BigDecimal totalAvgProjections = new BigDecimal(SpmConstants.INIT_VALUE_ZERO);
		
		//Calculating the total
		for(HalfHourProjection halfHour: avgCalculatedHalfHourList){
			totalAvgProjections = totalAvgProjections.add(halfHour.getAdjustedValue());
		}		
				
		//Calculating the weight of each half hour (the distribution) and getting the value for the projection.
		//If total avg projections is zero, set all the values with constant distribution.
		if (totalAvgProjections.doubleValue() > 0.0){
			for(HalfHourProjection halfHour: avgCalculatedHalfHourList){
				BigDecimal halfHourWeight = halfHour.getAdjustedValue().divide(totalAvgProjections, SpmConstants.DECIMAL_SCALE, SpmConstants.ROUNDING_MODE);
				halfHour.setAdjustedValue(projectionAmount.multiply(halfHourWeight));			
			}
		}else{
			//If the avg total is zero we distribute the projection constantly between the store open and close hours.
			OperationTime operationTime = store.getStoreOperationTimeByDate(selectedDate);
			
			BigDecimal avgProjectionValue = calculateAvgDistributionValue(operationTime, projectionAmount);
			
			Date closeHour = operationTime.getCloseHour();
			Date openHour = operationTime.getOpenHour();
			
			//Setting the values when open and close time are in the same day
			if (!operationTime.endsTomorrow()){
				for(HalfHourProjection halfHour: avgCalculatedHalfHourList){
					if ((halfHour.getTime().compareTo(openHour) >=0) && halfHour.getTime().before(closeHour)){
						halfHour.setAdjustedValue(new BigDecimal(avgProjectionValue.toString()));	
					}
				}
			}else{			
				//TODO: The values for the hours that falls in the following day are not set. This is because in projections we
				//manage the calendar dates and not the operation defined day. Should we moved to the operation day? LOADS of Changes!!!
				//The workaround is to complete the half hours list for the same day with the values that we were left behind.				
				for(HalfHourProjection halfHour: avgCalculatedHalfHourList){
					if ((halfHour.getTime().compareTo(openHour) >=0) || (halfHour.getTime().before(closeHour))){
						halfHour.setAdjustedValue(new BigDecimal(avgProjectionValue.toString()));	
					}
				}
			}
		}
				
		//TODO:Put an assertion to check that the total is the same to projection amount.
		return avgCalculatedHalfHourList;
	}

	
	/**
	 * @param operationTime
	 * @param projectionAmount
	 * @return
	 */
	private BigDecimal calculateAvgDistributionValue(OperationTime operationTime, BigDecimal projectionAmount){
		DateTime openTime = new DateTime(operationTime.getOpenHour());			
		DateTime closeTime = new DateTime(operationTime.getCloseHour());

		int minutesOpenTime = openTime.getMinuteOfDay();
		int minutesCloseTime = closeTime.getMinuteOfDay();
		
		//If the close time falls in the following day we have to add a day
		if (operationTime.endsTomorrow()){
			minutesCloseTime += SpmConstants.HALF_HOUR * SpmConstants.HALF_HOURS_IN_A_DAY;
		}
		
		int sizeListOfHalfHours = ((minutesCloseTime - minutesOpenTime)/SpmConstants.HALF_HOUR);			
		
		BigDecimal numberOfHalfHours = new BigDecimal(sizeListOfHalfHours);
		BigDecimal valueToSet = projectionAmount.divide(numberOfHalfHours, SpmConstants.DECIMAL_SCALE, SpmConstants.ROUNDING_MODE);
		
		return valueToSet;
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
		DateTime openHour = new DateTime().withDate(1970, 1, 1).withTime(0,0,0,0);
		DateTime closeHour = new DateTime().withDate(1970, 1, 1).withTime(23,30,0,0);
				
		List<HalfHourProjection> retList = new ArrayList<HalfHourProjection>(SpmConstants.HALF_HOURS_IN_A_DAY);

		DateTime nextTime = new DateTime(openHour);
		
		int index=0;
		
		for(HalfHourProjection currentHalfHour: avgProjections) {
			DateTime currentTime = new DateTime(currentHalfHour.getTime().getTime());
			
			while(currentTime.isAfter(nextTime)) {
				HalfHourProjection aHalfHourProjection = new HalfHourProjection();
				aHalfHourProjection.setTime(nextTime.toDate());
				aHalfHourProjection.setAdjustedValue(new BigDecimal(SpmConstants.INIT_VALUE_ZERO));
				aHalfHourProjection.setIndex(index++);
				retList.add(aHalfHourProjection);
				nextTime = nextTime.plusMinutes(SpmConstants.HALF_HOUR);
			}
			
			currentHalfHour.setIndex(index++);
			retList.add(currentHalfHour);

			nextTime = nextTime.plusMinutes(SpmConstants.HALF_HOUR);
		}
		
		
		while(nextTime.isBefore(closeHour) || nextTime.isEqual(closeHour)){
			HalfHourProjection aHalfHourProjection = new HalfHourProjection();
			aHalfHourProjection.setTime(nextTime.toDate());
			aHalfHourProjection.setAdjustedValue(new BigDecimal(SpmConstants.INIT_VALUE_ZERO));
			aHalfHourProjection.setIndex(index++);
			retList.add(aHalfHourProjection);
			nextTime = nextTime.plusMinutes(SpmConstants.HALF_HOUR);
		}
		
		return retList;
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
			BigDecimal auxVal = (new BigDecimal("1").subtract(totalAdjusted.divide(totalProjected, SpmConstants.DECIMAL_SCALE, SpmConstants.ROUNDING_MODE)));
			halfHourElement.setRevisedValue(auxVal.multiply(halfHourElement.getProjectedValue()).divide(hoursNotChanged, SpmConstants.DECIMAL_SCALE, SpmConstants.ROUNDING_MODE));
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


	/**
	 * @return the staffingService
	 */
	public StaffingService getStaffingService() {
		return staffingService;
	}


	/**
	 * @param staffingService the staffingService to set
	 */
	public void setStaffingService(StaffingService staffingService) {
		this.staffingService = staffingService;
	}
}