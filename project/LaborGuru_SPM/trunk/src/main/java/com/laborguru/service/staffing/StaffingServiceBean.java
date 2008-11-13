/*
 * File name: StaffingServiceBean.java
 * Creation date: 19/10/2008 17:00:27
 * Copyright Mindpool
 */
package com.laborguru.service.staffing;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.laborguru.model.DailyProjection;
import com.laborguru.model.DailyStaffing;
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
import com.laborguru.service.staffing.model.HalfHourStaffingPositionData;
import com.laborguru.service.staffing.model.DailyStaffingPositionData;
import com.laborguru.service.staffing.model.HalfHourStaffingPositionDataComparator;
import com.laborguru.service.store.dao.StoreDao;
import com.laborguru.util.CalendarUtils;

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
		store = getStoreDao().getStoreById(store);
		DailyProjection dailyProjection = getProjectionDao().getDailyProjection(store, date);
		
		StoreDailyStaffing storeDailyStaffing = new StoreDailyStaffing(store);
		storeDailyStaffing.setDate(date);
		
		//:TODO: Improve performance? Or complicate coding?
		for(Position position : store.getPositions()) {
			DailyStaffing dailyStaffing = getStaffingDao().getDailyStaffingByDate(position, date);
			if(dailyStaffing == null) {
				dailyStaffing = calculateDailyStaffing(position, date, dailyProjection);
			}

			storeDailyStaffing.addDailyStaffing(dailyStaffing);
		}
		
		return storeDailyStaffing;
		
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
			DailyProjection dailyProjection = getProjectionDao().getDailyProjection(position.getStore(), date);
			
			dailyStaffing = calculateDailyStaffing(position, date, dailyProjection);
		}
		
		return dailyStaffing;
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
				totalWorkContent += aHalfHourStaffing.getWorkContent().doubleValue();
				totalMinimumStaffing += aHalfHourStaffing.getCalculatedStaff().intValue();
				
				dailyStaffing.addHalfHourProjection(aHalfHourStaffing);
			}
			
			dailyStaffing.setTotalMinimumStaffing(totalMinimumStaffing);
			dailyStaffing.setTotalWorkContent(totalWorkContent);
			
			calculateDailyStaffingHours(dailyStaffing, position, date, dailyProjection, initializeDailyStaffingData(position, date, dailyProjection));
			
			return dailyStaffing;
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param dailyStaffing
	 * @param position
	 * @param date
	 * @param dailyProjection
	 * @param dailyStaffingData
	 */
	private void calculateDailyStaffingHours(DailyStaffing dailyStaffing, Position position, Date date, DailyProjection dailyProjection, Map<DayPart, DailyStaffingPositionData> dailyStaffingData) {
		
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
				aDailyData.setDayPartData(getDayPartDataFor(position, aDayPart));
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
		
		for(Position position : store.getPositions()) {
			key = buildStaffingDataKey(position);
			if(key != null) {
				groupStaffingData = data.get(key);

				staffingData = new HalfHourStaffingPositionData(position);
				staffingData.setProjection(getDoubleValue(halfHourProjection.getAdjustedValue()));
				staffingData.setUtilization(getUtilization(position, staffingData.getProjection()));
				staffingData.setWorkContent(getWorkContent(position, halfHourProjection, date, halfHourProjection.getTime(), staffingData.getProjection(), staffingData.getUtilization()));

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
			halfHourStaffing.setCalculatedStaff(staffingData.getMinimunStaffing());
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
		double minPercent = getDoubleValue(position.getUtilizationBottom());
		double maxPercent = getDoubleValue(position.getUtilizationTop());
		int min = getIntegerValue(position.getUtilizationMinimum());
		int max = getIntegerValue(position.getUtilizationMaximum());

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
	 * @param position
	 * @param halfHourProjection
	 * @param date
	 * @param time
	 * @return
	 */
	private double getWorkContent(Position position, HalfHourProjection halfHourProjection, Date date, Date time, double projection, double utilization) {
		double variableService = getVariableService(position, date, time);
		double fixedService = getFixedService(position, date, time);
		return ((projection * variableService) + fixedService) / utilization;
	}
	
	/**
	 * 
	 * @param position
	 * @param date
	 * @param time
	 * @return
	 */
	private double getVariableService(Position position, Date date, Date time) {
		DayPartData dayPartData = getDayPartDataFor(position, time);
		if(dayPartData == null) {
			return 0.0;
		} else if(CalendarUtils.isWeekendDay(date)) {
			return getDoubleValue(dayPartData.getWeekendGuestService());
		} else {
			return getDoubleValue(dayPartData.getWeekdayGuestService());
		}
	}
	
	/**
	 * 
	 * @param position
	 * @param date
	 * @param time
	 * @return
	 */
	private double getFixedService(Position position, Date date, Date time) {
		DayPartData dayPartData = getDayPartDataFor(position, time);
		if(dayPartData == null) {
			return 0.0;
		} else {
			return getDoubleValue(dayPartData.getFixedGuestService());
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
	 * @param position
	 * @param dayPart
	 * @return
	 */
	private DayPartData getDayPartDataFor(Position position, DayPart dayPart) {
		return position.getDayPartDataFor(dayPart);
	}
	
	/**
	 * 
	 * @param d
	 * @return
	 */
	private double getDoubleValue(Double d) {
		return d != null ? d.doubleValue() : 0.0;
	}
	
	/**
	 * 
	 * @param d
	 * @return
	 */
	private double getDoubleValue(BigDecimal bd) {
		return bd != null ? bd.doubleValue() : 0.0;
	}
	
	/**
	 * 
	 * @param i
	 * @return
	 */
	private int getIntegerValue(Integer i) {
		return i != null ? i.intValue() : 0;
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
