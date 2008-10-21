/*
 * File name: StaffingServiceBean.java
 * Creation date: 19/10/2008 17:00:27
 * Copyright Mindpool
 */
package com.laborguru.service.staffing;

import java.util.Date;

import com.laborguru.model.DailyStaffing;
import com.laborguru.model.Position;
import com.laborguru.service.staffing.dao.StaffingDao;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class StaffingServiceBean implements StaffingService {
	private StaffingDao staffingDao;
	
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
			
		}
		
		return dailyStaffing;
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


}
