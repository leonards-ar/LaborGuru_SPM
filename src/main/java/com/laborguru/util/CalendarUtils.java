/*
 * File name: CalendarUtils.java
 * Creation date: Sep 20, 2008 9:10:37 PM
 * Copyright Mindpool
 */
package com.laborguru.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	
	/**
	 * 
	 * @param d
	 * @return
	 */
	public static boolean isWeekendDay(Date d) {
		DayOfWeek dof = getDayOfWeek(d);
		return dof != null ? dof.equals(DayOfWeek.SATURDAY) || dof.equals(DayOfWeek.SUNDAY): false;
	}
	
	/**
	 * 
	 * @return
	 */
	public static Date todayWithoutTime() {
		return removeTimeFromDate(new Date());
	}
	
	/**
	 * 
	 * @param d
	 * @return
	 */
	public static Date removeTimeFromDate(Date d) {
		try {
			final SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			String str = df.format(d);
			return df.parse(str);
		} catch(ParseException ex) {
			// Should never happen
			return null;
		}
		
	}

	/**
	 * 
	 * @param time
	 * @return
	 */
	public static Date displayTimeToDate(String time) {
		try {
			return SpmConstants.TIME_FORMAT.parse(time);
		} catch (ParseException ex) {
			//TODO: log.error("Cannot parse time [" + time + "]", ex);
			return null;
		}
	}

	/**
	 * 
	 * @param time
	 * @return
	 */
	public static String dateToDisplayTime(Date time) {
			return SpmConstants.TIME_FORMAT.format(time);
	}

	/**
	 * 
	 * @param time
	 * @return
	 */
	public static Date stringToDate(String time, String format) {
		try {
			return new SimpleDateFormat(format).parse(time);
		} catch (ParseException ex) {
			//TODO: log.error("Cannot parse time [" + time + "]", ex);
			return null;
		}
	}

	/**
	 * 
	 * @param time
	 * @return
	 */
	public static String dateToString(Date time, String format) {
			return new SimpleDateFormat(format).format(time);
	}
	
	/**
	 * This method returns a Calendar that represents this week's weekday taking as example the date's weekday passed as parameter.
	 * For example: If we passed Monday 20/10/2008 as parameter, the method returns this week monday.
	 * 
	 * @param auxSelectedDate
	 * @return
	 */
	public static Calendar getDayOfThisWeek(Date auxSelectedDate) {
		DayOfWeek dayOfWeekCalculatedDay = CalendarUtils.getDayOfWeek(auxSelectedDate);

		Date today = CalendarUtils.todayWithoutTime();;
		DayOfWeek dayOfWeek = CalendarUtils.getDayOfWeek(today);
		
		Calendar day = CalendarUtils.getCalendar(today);
		int daysTosubstract = 0;
					
		if (dayOfWeek.ordinal() < dayOfWeekCalculatedDay.ordinal()){
			daysTosubstract = 7 - (dayOfWeekCalculatedDay.ordinal() - dayOfWeek.ordinal());
		} else if (dayOfWeek.ordinal() > dayOfWeekCalculatedDay.ordinal()){
			daysTosubstract = dayOfWeek.ordinal() - dayOfWeekCalculatedDay.ordinal();
		} 			
			
		day.add(Calendar.DAY_OF_MONTH, -1 * daysTosubstract);
		return day;
	}
}
