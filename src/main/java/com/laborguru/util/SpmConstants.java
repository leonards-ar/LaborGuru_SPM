package com.laborguru.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class SpmConstants {
	public static final int MINUTES_INTERVAL = 15;

	public static final String DEBUG = "spmDebug";
	
	public static final String TIME_FORMAT = "HH:mm";
	public static final String DISPLAY_TIME_FORMAT = "h:mma";
	public static final String TIME_NUMBER_FORMAT = "HHmm";
	public static final String TIME_HOUR_FORMAT = "HH";
	public static final String TIME_MINUTE_FORMAT = "mm";
	public static final String DATE_FORMAT = "dd/MM/yyyy";
	public static final String REMOVE_TIME_FORMAT = "yyyyMMdd";
	public static final String REMOVE_DATE_FORMAT = "HHmmss";
	
	public static final String WEEKDAY_SELECTOR_DATEFORMAT= "yyyyMMdd";

	public final static String TIME_PARSE_FORMAT = "h:mm:ss a";

	
	public static final NumberFormat DOUBLE_FORMAT_5 = DecimalFormat.getInstance(Locale.US);
	public static final NumberFormat DOUBLE_FORMAT_2 = DecimalFormat.getInstance(Locale.US);
	
	public static final String SCHEDULE_BREAK = "__break__";
	public static final String SCHEDULE_FREE = "";
	
	public static final String INIT_VALUE_ZERO = "0.00";
	public static final int DECIMAL_SCALE = 16;
	public static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;
	public static final int DAILY_PROJECTION_PERIOD_DAYS = 7;
	public static final int HALF_HOUR = 30;
	public static final int HALF_HOURS_IN_A_DAY=48;
	
	public static final BigDecimal BD_ZERO_VALUE = new BigDecimal(INIT_VALUE_ZERO);
	public static final Double DOUBLE_ZERO_VALUE = new Double(INIT_VALUE_ZERO);
	public static final Double DOUBLE_ONE_VALUE = new Double(1.0);
}
