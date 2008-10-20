/*
 * File name: CalculatedPositionStaffDao.java
 * Creation date: 19/10/2008 16:55:31
 * Copyright Mindpool
 */
package com.laborguru.service.staffing.dao;

import java.util.Date;

import com.laborguru.model.DailyCalculatedPositionStaff;
import com.laborguru.model.Position;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public interface CalculatedPositionStaffDao {

	/**
	 * 
	 * @param position
	 * @param date
	 * @return
	 */
	DailyCalculatedPositionStaff getDailyCalculatedPositionStaffingByDate(Position position, Date date);
	
	/**
	 * 
	 * @param dailyCalulatedPositionStaff
	 * @return
	 */
	DailyCalculatedPositionStaff save(DailyCalculatedPositionStaff dailyCalulatedPositionStaff);	
}
