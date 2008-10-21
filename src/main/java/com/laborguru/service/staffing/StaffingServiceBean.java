/*
 * File name: StaffingServiceBean.java
 * Creation date: 19/10/2008 17:00:27
 * Copyright Mindpool
 */
package com.laborguru.service.staffing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.laborguru.model.DailyProjection;
import com.laborguru.model.DailyStaffing;
import com.laborguru.model.HalfHourProjection;
import com.laborguru.model.HalfHourStaffing;
import com.laborguru.model.Position;
import com.laborguru.service.position.dao.PositionDao;
import com.laborguru.service.projection.dao.ProjectionDao;
import com.laborguru.service.staffing.dao.StaffingDao;
import com.laborguru.util.CalendarUtils;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class StaffingServiceBean implements StaffingService {
	private StaffingDao staffingDao;
	private PositionDao positionDao;
	private ProjectionDao projectionDao;
	
	
	/**
	 * 
	 */
	public StaffingServiceBean() {
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
	 * @return
	 */
	private DailyStaffing calculateDailyStaffing(Position position, Date date, DailyProjection dailyProjection) {
		if(dailyProjection != null) {
			DailyStaffing dailyStaffing = new DailyStaffing();
			dailyStaffing.setDate(date);
			dailyStaffing.setPosition(position);
			dailyStaffing.setStartingTime(position.getStore().getOperationTime(CalendarUtils.getDayOfWeek(date)).getOpenHour());
			
			int size = dailyProjection.getHalfHourProjections().size();
			
			HalfHourStaffing aHalfHourStaffing;
			for(int i = 0; i < size; i++) {
				aHalfHourStaffing = calculateHalfHourStaffing(position, date, dailyProjection.getHalfHourProjections().get(i));
				dailyStaffing.addHalfHourProjection(aHalfHourStaffing);
			}
			
			return dailyStaffing;
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
	private HalfHourStaffing calculateHalfHourStaffing(Position position, Date date, HalfHourProjection halfHourProjection) {
		HalfHourStaffing halfHourStaffing = new HalfHourStaffing();
		halfHourStaffing.setTime(halfHourProjection.getTime());
		halfHourStaffing.setIndex(halfHourProjection.getIndex());
		
		double projection = halfHourProjection.getAdjustedValue().doubleValue();	
		//:TODO: Calculate here!
		halfHourStaffing.setCalculatedStaff(null);
		
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
		//:TODO: Validate this!
		double minPercent = position.getUtilizationBottom() != null ? position.getUtilizationBottom().doubleValue() : 0.0;
		double maxPercent = position.getUtilizationTop() != null ? position.getUtilizationTop().doubleValue() : 0.0;
		int min = position.getUtilizationMinimum() != null ? position.getUtilizationMinimum().intValue() : 0;
		int max = position.getUtilizationMaximum() != null ? position.getUtilizationMaximum().intValue() : 0;

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
	private double getWorkhandCount(Position position, HalfHourProjection halfHourProjection, Date date, Date time, double projection, double utilization) {
		//:TODO: Where to take this!
		double variableService = 0.0;
		double fixedService = 0.0;
		return ((projection * variableService) + fixedService) / utilization;
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


}
