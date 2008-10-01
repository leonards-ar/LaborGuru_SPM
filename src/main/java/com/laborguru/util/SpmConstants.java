package com.laborguru.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SpmConstants {

	public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm");
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	
	public static final String WEEKDAY_SELECTOR_DATEFORMAT= "yyyyMMdd";
	
	public static final NumberFormat DOUBLE_FORMAT_5 = DecimalFormat.getInstance(Locale.US);
	public static final NumberFormat DOUBLE_FORMAT_2 = DecimalFormat.getInstance(Locale.US);
	
	public static final String SCHEDULE_BREAK = "__break__";
	public static final String SCHEDULE_FREE = "";
	
	
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
}
