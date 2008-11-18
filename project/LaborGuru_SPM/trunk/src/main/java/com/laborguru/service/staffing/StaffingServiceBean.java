/*
 * File name: StaffingServiceBean.java
 * Creation date: 19/10/2008 17:00:27
 * Copyright Mindpool
 */
package com.laborguru.service.staffing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.laborguru.model.DailyProjection;
import com.laborguru.model.DailyStaffing;
import com.laborguru.model.DayOfWeekData;
import com.laborguru.model.DayPart;
import com.laborguru.model.DayPartData;
import com.laborguru.model.HalfHourProjection;
import com.laborguru.model.HalfHourStaffing;
import com.laborguru.model.Position;
import com.laborguru.model.Store;
import com.laborguru.model.StoreDailyStaffing;
import com.laborguru.service.position.dao.PositionDao;
import com.laborguru.service.projection.dao.ProjectionDao;
import com.laborguru.service.staffing.dao.StaffingDao;
import com.laborguru.service.staffing.model.DailyStaffingPositionData;
import com.laborguru.service.staffing.model.HalfHourStaffingPositionData;
import com.laborguru.service.staffing.model.HalfHourStaffingPositionDataComparator;
import com.laborguru.service.store.dao.StoreDao;
import com.laborguru.util.CalendarUtils;
import com.laborguru.util.NumberUtils;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class StaffingServiceBean implements StaffingService {
	private static final String GROUP_TOKEN = "_grp_";
	private static final String POSITION_TOKEN = "_pos_";
	
	private StaffingDao staffingDao;
	private PositionDao positionDao;
	private ProjectionDao projectionDao;
	private StoreDao storeDao;
	
	/**
	 * 
	 */
	public StaffingServiceBean() {
	}

	/**
	 * 
	 * @param store
	 * @param date
	 * @return
	 */
	public StoreDailyStaffing getDailyStaffingByDate(Store store, Date date) {
		List<DailyStaffing> managerDailyStaffings = new ArrayList<DailyStaffing>();
		double nonManagerTargetAddition = 0.0;
		
		store = getStoreDao().getStoreById(store);
		DailyProjection dailyProjection = getDailyProjection(store, date);
		
		StoreDailyStaffing storeDailyStaffing = new StoreDailyStaffing(store);
		storeDailyStaffing.setDate(date);
		
		//:TODO: Improve performance? Or complicate coding?
		for(Position position : store.getPositions()) {
			DailyStaffing dailyStaffing = getStaffingDao().getDailyStaffingByDate(position, date);
			if(dailyStaffing == null) {
				dailyStaffing = calculateDailyStaffing(position, date, dailyProjection);
			}
			if(dailyStaffing != null) {
				storeDailyStaffing.addDailyStaffing(dailyStaffing);
				
				if(isManagerDailyStaffing(dailyStaffing)) {
					managerDailyStaffings.add(dailyStaffing);
				} else {
					nonManagerTargetAddition += NumberUtils.getDoubleValue(dailyStaffing.getTotalDailyTarget());
				}
			}
		}
		
		applyOtherFactorsToManagerPositions(managerDailyStaffings, store, nonManagerTargetAddition);
		
		return storeDailyStaffing;
		
	}
	
	/**
	 * 
	 * @param managerDailyStaffing
	 * @param position
	 * @param nonManagerTargetAddition
	 */
	private void applyOtherFactorsToManagerPositions(List<DailyStaffing> managerDailyStaffings, Store store, double nonManagerTargetAddition) {
		if(managerDailyStaffings != null) {
			for(DailyStaffing managerDailyStaffing : managerDailyStaffings) {
				double floorMgmtFactor = nonManagerTargetAddition * NumberUtils.getDoubleValue(store.getFloorManagementFactor()) / 100;
				int minFloorMgmt = NumberUtils.getIntegerValue(store.getMinimumFloorManagementHours());
				floorMgmtFactor = Math.max(floorMgmtFactor, (double) minFloorMgmt);
				
				managerDailyStaffing.setTotalDailyTarget(new Double(NumberUtils.getDoubleValue(managerDailyStaffing.getBaseDailyTarget()) + floorMgmtFactor));
			}
		}
	}
	
	/**
	 * 
	 * @param dailyStaffing
	 * @return
	 */
	private boolean isManagerDailyStaffing(DailyStaffing dailyStaffing) {
		return dailyStaffing != null && dailyStaffing.getPosition() != null && dailyStaffing.getPosition().isManager();
	}
	
	/**
	 * 
	 * @param position
	 * @param date
	 * @return
	 * @see com.laborguru.service.staffing.StaffingService#getDailyStaffingByDate(com.laborguru.model.Position, java.util.Date)
	 */
	public DailyStaffing getDailyStaffingByDate(Position position, Date date) {
		DailyStaffing dailyStaffing = getStaffingDao().getDailyStaffingByDate(position, date);
		
		if(dailyStaffing == null) {
			position = getPositionDao().getPositionById(position);
			DailyProjection dailyProjection = getDailyProjection(position.getStore(), date);
			
			dailyStaffing = calculateDailyStaffing(position, date, dailyProjection);
		}
		
		return dailyStaffing;
	}

	/**
	 * 
	 * @param store
	 * @param date
	 * @return
	 */
	private DailyProjection getDailyProjection(Store store, Date date) {
		DailyProjection dailyProjection = getProjectionDao().getDailyProjection(store, date);
		if(dailyProjection == null) {
			dailyProjection = DailyProjection.getEmptyDailyProjectionInstance(store, date);
		}
		return dailyProjection;
	}
	
	/**
	 * 
	 * @param position
	 * @param date
	 * @param dailyProjection
	 * @param dailyStaffingData
	 * @return
	 */
	private DailyStaffing calculateDailyStaffing(Position position, Date date, DailyProjection dailyProjection) {
		if(dailyProjection != null) {
			DailyStaffing dailyStaffing = new DailyStaffing();
			dailyStaffing.setDate(date);
			dailyStaffing.setPosition(position);
			dailyStaffing.setStartingTime(position.getStore().getOperationTime(CalendarUtils.getDayOfWeek(date)).getOpenHour());
			
			int size = dailyProjection.getHalfHourProjections().size();
			double totalWorkContent = 0.0;
			int totalMinimumStaffing = 0;
			
			HalfHourStaffing aHalfHourStaffing;
			for(int i = 0; i < size; i++) {
				aHalfHourStaffing = calculateHalfHourStaffing(position, date, dailyProjection.getHalfHourProjections().get(i), initializeHalfHourStaffingData(position.getStore(), date, dailyProjection.getHalfHourProjections().get(i)));
				totalWorkContent += NumberUtils.getDoubleValue(aHalfHourStaffing.getWorkContent());
				totalMinimumStaffing += NumberUtils.getIntegerValue(aHalfHourStaffing.getCalculatedStaff());
				
				dailyStaffing.addHalfHourStaffing(aHalfHourStaffing);
			}
			
			dailyStaffing.setTotalMinimumStaffing(totalMinimumStaffing);
			dailyStaffing.setTotalWorkContent(totalWorkContent);
			
			calculateDailyTarget(dailyStaffing, position, date, dailyProjection, initializeDailyStaffingData(position, date, dailyProjection));
			
			return dailyStaffing;
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param dailyStaffing
	 * @param position
	 */
	private void setTotalServiceHours(DailyStaffing dailyStaffing, Position position) {
		int totalTarget = NumberUtils.getIntegerValue(dailyStaffing.getTotalHourStaffing());
		double scheduleInefficiency = (position.getStore() != null ? NumberUtils.getDoubleValue(position.getStore().getScheduleInefficiency()) : 0.0) / 100;

		dailyStaffing.setTotalServiceHours(totalTarget * (1 + scheduleInefficiency));
	}
	
	/**
	 * 
	 * @param dailyStaffing
	 * @param position
	 * @param date
	 * @param dailyProjection
	 * @param dailyStaffingData
	 */
	private void calculateDailyTarget(DailyStaffing dailyStaffing, Position position, Date date, DailyProjection dailyProjection, Map<DayPart, DailyStaffingPositionData> dailyStaffingData) {
		setTotalServiceHours(dailyStaffing, position);
		
		setVariableTotals(dailyStaffing, dailyStaffingData);
		setFixedValues(dailyStaffing, position, date);
		setTotalFlexible(dailyStaffing, position);
		setTotalOpening(dailyStaffing, position);
		
		applyOtherFactorsToPositionDailyTarget(dailyStaffing, position);
	}

	/**
	 * 
	 * @param dailyStaffing
	 * @param position
	 */
	private void applyOtherFactorsToPositionDailyTarget(DailyStaffing dailyStaffing, Position position) {
		// Managers Other Factors are tied to store totals
		if(!position.isManager() && position != null && position.getStore() != null) {
			double baseTarget = NumberUtils.getDoubleValue(dailyStaffing.getBaseDailyTarget());
			double trainingFactor = baseTarget * NumberUtils.getDoubleValue(position.getStore().getTrainingFactor()) / 100;
			double breakFactor = baseTarget * NumberUtils.getDoubleValue(position.getStore().getEarnedBreakFactor()) / 100;
			
			dailyStaffing.setTotalDailyTarget(new Double(baseTarget + trainingFactor + breakFactor));
		}
	}

	/**
	 * 
	 * @param dailyStaffing
	 * @param position
	 */
	private void setTotalOpening(DailyStaffing dailyStaffing, Position position) {
		double totalOpening = NumberUtils.getDoubleValue(dailyStaffing.getTotalVariableOpening()) + NumberUtils.getDoubleValue(dailyStaffing.getFixedOpening());
		
		dailyStaffing.setTotalFlexible(new Double(totalOpening));
		
	}
	
	/**
	 * 
	 * @param dailyStaffing
	 * @param position
	 */
	private void setTotalFlexible(DailyStaffing dailyStaffing, Position position) {
		double diffWorkContentService = NumberUtils.getDoubleValue(dailyStaffing.getTotalServiceHours()) - NumberUtils.getDoubleValue(dailyStaffing.getTotalWorkContent());
		double available = diffWorkContentService * NumberUtils.getDoubleValue(position.getStore().getFillInefficiency()) / 100;
		double totalFlexible = NumberUtils.getDoubleValue(dailyStaffing.getTotalVariableFlexible()) + NumberUtils.getDoubleValue(dailyStaffing.getFixedFlexible());
		double diff = totalFlexible - available;
		
		dailyStaffing.setTotalFlexible(new Double(diff >= 0.0 ? diff : 0.0));
	}
	
	/**
	 * 
	 * @param dailyStaffing
	 * @param position
	 * @param date
	 */
	private void setFixedValues(DailyStaffing dailyStaffing, Position position, Date date) {
		DayOfWeekData dofData = getDayOfWeekDataFor(position, date);
		if(dofData != null) {
			dailyStaffing.setFixedFlexible(dofData.getFixedFlexible());
			dailyStaffing.setFixedOpening(dofData.getFixedOpening());
			dailyStaffing.setFixedPostRush(dofData.getFixedPostRush());
			dailyStaffing.setFixedClosing(dofData.getFixedClosing());
		}
	}

	/**
	 * 
	 * @param dailyStaffing
	 * @param dailyStaffingData
	 */
	private void setVariableTotals(DailyStaffing dailyStaffing, Map<DayPart, DailyStaffingPositionData> dailyStaffingData) {
		double totalVariableFlexible = 0.0;
		double totalVariableOpening = 0.0;
		for(DailyStaffingPositionData dailyData : dailyStaffingData.values()) {
			totalVariableFlexible += dailyData.getVariableFlexible();
			totalVariableOpening += dailyData.getVariableOpening();
		}
		dailyStaffing.setTotalVariableFlexible(new Double(totalVariableFlexible));
		dailyStaffing.setTotalVariableOpening(new Double(totalVariableOpening));
		
		
	}
	
	/**
	 * 
	 * @param position
	 * @param date
	 * @param dailyProjection
	 * @return
	 */
	private Map<DayPart, DailyStaffingPositionData> initializeDailyStaffingData(Position position, Date date, DailyProjection dailyProjection) {
		Map<DayPart, DailyStaffingPositionData> data = new HashMap<DayPart, DailyStaffingPositionData>();

		DayPart aDayPart;
		DailyStaffingPositionData aDailyData;
		
		for(HalfHourProjection aHalfHourProjection : dailyProjection.getHalfHourProjections()) {
			aDayPart = getDayPartFor(position, aHalfHourProjection.getTime());
			aDailyData = data.get(aDayPart);
			if(aDailyData == null) {
				aDailyData = new DailyStaffingPositionData();
				DayPartData dayPartData = getDayPartDataFor(position, aDayPart);
				//:TODO: What if dayPartData is null???
				aDailyData.setDayPartData(dayPartData);
				aDailyData.setStore(position.getStore());
				aDailyData.setPosition(position);
				
				data.put(aDayPart, aDailyData);
			}
			aDailyData.addHalfHourProjection(aHalfHourProjection.getAdjustedValue());
		}
		
		return data;
	}
	
	/**
	 * 
	 * @param store
	 * @param date
	 * @param halfHourProjection
	 * @return
	 */
	private Map<String, List<HalfHourStaffingPositionData>> initializeHalfHourStaffingData(Store store, Date date, HalfHourProjection halfHourProjection) {
		Map<String, List<HalfHourStaffingPositionData>> data = new HashMap<String, List<HalfHourStaffingPositionData>>();
		
		HalfHourStaffingPositionData staffingData;
		String key;
		List<HalfHourStaffingPositionData> groupStaffingData;
		DayPartData dayPartData;
		
		for(Position position : store.getPositions()) {
			key = buildStaffingDataKey(position);
			if(key != null) {
				groupStaffingData = data.get(key);
				dayPartData = getDayPartDataFor(position, halfHourProjection.getTime());
				
				staffingData = new HalfHourStaffingPositionData(position);
				staffingData.setProjection(NumberUtils.getDoubleValue(halfHourProjection.getAdjustedValue()));
				staffingData.setUtilization(getUtilization(position, staffingData.getProjection()));
				staffingData.setWorkContent(getWorkContent(dayPartData, date, staffingData.getProjection(), staffingData.getUtilization()));
				staffingData.setPositionMinimumStaffing(getPositionMinimumStaffing(dayPartData));
				
				if(groupStaffingData == null) {
					groupStaffingData = new ArrayList<HalfHourStaffingPositionData>();
					groupStaffingData.add(staffingData);
					data.put(key, groupStaffingData);
				} else {
					groupStaffingData.add(staffingData);
					Collections.sort(groupStaffingData, new HalfHourStaffingPositionDataComparator());
				}
			}
		}
		
		for(List<HalfHourStaffingPositionData> groupStaffing : data.values()) {
			updateAdditionalEmployee(groupStaffing);
		}
		
		return data;
	}
	
	/**
	 * 
	 * @param dayPartData
	 * @return
	 */
	private int getPositionMinimumStaffing(DayPartData dayPartData) {
		return dayPartData != null ? NumberUtils.getIntegerValue(dayPartData.getMinimunStaffing()) : 0;
	}
	
	/**
	 * 
	 * @param groupStaffing
	 */
	private void updateAdditionalEmployee(List<HalfHourStaffingPositionData> groupStaffing) {
		int total = getTotalEmployeesForGroup(groupStaffing);
		for(int i = 0; i < total; i++) {
			groupStaffing.get(i).setAdditionalEmployee(true);
		}
	}
	
	/**
	 * 
	 * @param groupStaffing
	 * @return
	 */
	private int getTotalEmployeesForGroup(List<HalfHourStaffingPositionData> groupStaffing) {
		double total = 0.0;
		for(HalfHourStaffingPositionData staffing : groupStaffing) {
			total += staffing.getWorkContentDecimalPart();
		}
		return (int) Math.ceil(total);
	}
	
	/**
	 * 
	 * @param position
	 * @return
	 */
	private String buildStaffingDataKey(Position position) {
		if(position != null && position.getPositionGroup() != null && position.getPositionGroup().getName() != null) {
			return GROUP_TOKEN + position.getPositionGroup().getName();
		} else if(position != null && position.getName() != null) {
			return POSITION_TOKEN + position.getName();
		} else {
			return null;
		}
	}
	
	/**
	 * 
	 * @param data
	 * @param position
	 * @return
	 */
	private HalfHourStaffingPositionData getHalfHourStaffingPositionData(Map<String, List<HalfHourStaffingPositionData>> data, Position position) {
		String key = buildStaffingDataKey(position);
		List<HalfHourStaffingPositionData> groupStaffingData = data.get(key);
		if(groupStaffingData != null) {
			int index = groupStaffingData.indexOf(new HalfHourStaffingPositionData(position));
			return index >= 0 ? groupStaffingData.get(index) : null;
		} else {
			return null;
		}
	}
	
	/**
	 * 
	 * @param position
	 * @param date
	 * @param halfHourProjection
	 * @return
	 */
	private HalfHourStaffing calculateHalfHourStaffing(Position position, Date date, HalfHourProjection halfHourProjection, Map<String, List<HalfHourStaffingPositionData>> data) {
		HalfHourStaffing halfHourStaffing = new HalfHourStaffing();
		halfHourStaffing.setTime(halfHourProjection.getTime());
		halfHourStaffing.setIndex(halfHourProjection.getIndex());
	
		HalfHourStaffingPositionData staffingData = getHalfHourStaffingPositionData(data, position);
		if(data != null) {
			halfHourStaffing.setCalculatedStaff(new Integer(staffingData.getMinimumStaffing()));
			halfHourStaffing.setWorkContent(new Double(staffingData.getWorkContent()));
		}
		
		return halfHourStaffing;
	}
	
	/**
	 * 
	 * @param position
	 * @param date
	 * @param time
	 * @return
	 */
	private double getUtilization(Position position, double projection) {
		double minPercent = NumberUtils.getDoubleValue(position.getUtilizationBottom());
		double maxPercent = NumberUtils.getDoubleValue(position.getUtilizationTop());
		int min = NumberUtils.getIntegerValue(position.getUtilizationMinimum());
		int max = NumberUtils.getIntegerValue(position.getUtilizationMaximum());

		if(projection <= min) {
			return minPercent;
		} else if(projection >= max) {
			return maxPercent;
		} else {
			return minPercent + ((projection - min) * (maxPercent - minPercent) / (max - min));
		}
	}
	
	/**
	 * 
	 * @param dayPartData
	 * @param date
	 * @param projection
	 * @param utilization
	 * @return
	 */
	private double getWorkContent(DayPartData dayPartData, Date date, double projection, double utilization) {
		double variableService = getVariableService(dayPartData, date);
		double fixedService = getFixedService(dayPartData, date);
		return ((projection * variableService) + fixedService) / utilization;
	}

	/**
	 * 
	 * @param dayPartData
	 * @param date
	 * @return
	 */
	private double getVariableService(DayPartData dayPartData, Date date) {
		if(dayPartData == null) {
			return 0.0;
		} else if(CalendarUtils.isWeekendDay(date)) {
			return NumberUtils.getDoubleValue(dayPartData.getWeekendGuestService());
		} else {
			return NumberUtils.getDoubleValue(dayPartData.getWeekdayGuestService());
		}
	}
	
	/**
	 * 
	 * @param dayPartData
	 * @param date
	 * @return
	 */
	private double getFixedService(DayPartData dayPartData, Date date) {
		if(dayPartData == null) {
			return 0.0;
		} else {
			return NumberUtils.getDoubleValue(dayPartData.getFixedGuestService());
		}
	}
	
	/**
	 * 
	 * @param position
	 * @param time
	 * @return
	 */
	private DayPart getDayPartFor(Position position, Date time) {
		return position.getStore() != null ? position.getStore().getDayPartFor(time) : null;
	}
	
	/**
	 * 
	 * @param position
	 * @param time
	 * @return
	 */
	private DayPartData getDayPartDataFor(Position position, Date time) {
		return position.getDayPartDataFor(getDayPartFor(position, time));
	}
	
	/**
	 * 
	 * @param posisiton
	 * @param date
	 * @return
	 */
	private DayOfWeekData getDayOfWeekDataFor(Position position, Date date) {
		return position.getDayOfWeekDataFor(CalendarUtils.getDayOfWeek(date));
	}
	
	/**
	 * 
	 * @param position
	 * @param dayPart
	 * @return
	 */
	private DayPartData getDayPartDataFor(Position position, DayPart dayPart) {
		return position.getDayPartDataFor(dayPart);
	}
	
	/**
	 * @return the staffingDao
	 */
	public StaffingDao getStaffingDao() {
		return staffingDao;
	}

	/**
	 * @param staffingDao the staffingDao to set
	 */
	public void setStaffingDao(StaffingDao staffingDao) {
		this.staffingDao = staffingDao;
	}

	/**
	 * @return the positionDao
	 */
	public PositionDao getPositionDao() {
		return positionDao;
	}

	/**
	 * @param positionDao the positionDao to set
	 */
	public void setPositionDao(PositionDao positionDao) {
		this.positionDao = positionDao;
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
	 * @return the storeDao
	 */
	public StoreDao getStoreDao() {
		return storeDao;
	}

	/**
	 * @param storeDao the storeDao to set
	 */
	public void setStoreDao(StoreDao storeDao) {
		this.storeDao = storeDao;
	}


}
