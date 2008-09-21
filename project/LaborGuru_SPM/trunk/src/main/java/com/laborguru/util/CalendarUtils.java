/*
 * File name: CalendarUtils.java
 * Creation date: Sep 20, 2008 9:10:37 PM
 * Copyright Mindpool
 */
package com.laborguru.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.laborguru.model.DayOfWeek;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class CalendarUtils {

	/**
	 * 
	 */
	public CalendarUtils() {
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar getCalendar(Date d) {
		if(d != null) {
			Calendar cal = GregorianCalendar.getInstance();
			cal.setTime(d);
			return cal;
		} else {
			return null;
		}			
	}
	
	/**
	 * 
	 * @param d
	 * @param daysToAddOrSubstract
	 * @return
	 */
	public static Date addOrSubstractDays(Date d, int daysToAddOrSubstract) {
		Calendar cal = CalendarUtils.getCalendar(d);
		cal.add(Calendar.DAY_OF_MONTH, daysToAddOrSubstract);
		return cal.getTime();
	}
	
	/**
	 * 
	 * @param d
	 * @param hoursToAddOrSubstract
	 * @return
	 */
	public static Date addOrSubstractHours(Date d, int hoursToAddOrSubstract) {
		Calendar cal = CalendarUtils.getCalendar(d);
		cal.add(Calendar.HOUR_OF_DAY, hoursToAddOrSubstract);
		return cal.getTime();		
	}
	
	/**
	 * 
	 * @param d
	 * @return
	 */
	public static DayOfWeek getDayOfWeek(Date d) {
		Calendar cal = CalendarUtils.getCalendar(d);
		if(cal != null) {
			int dof = cal.get(Calendar.DAY_OF_WEEK);
			return DayOfWeek.values()[dof - 1];
		} else {
			return null;
		}		
	}	
}
