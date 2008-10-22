package com.laborguru.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class SpmConstants {

	public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm");
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	
	public static final String WEEKDAY_SELECTOR_DATEFORMAT= "yyyyMMdd";
	
	public static final NumberFormat DOUBLE_FORMAT_5 = DecimalFormat.getInstance(Locale.US);
	public static final NumberFormat DOUBLE_FORMAT_2 = DecimalFormat.getInstance(Locale.US);
	
	public static final String SCHEDULE_BREAK = "__break__";
	public static final String SCHEDULE_FREE = "";
	
	public static final String INIT_VALUE_ZERO = "0.00";
	public static final int DECIMAL_SCALE = 16;
	public static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;
	public static final int DAILY_PROJECTION_PERIOD_DAYS = 7;
	public static final BigDecimal BD_ZERO_VALUE = new BigDecimal(INIT_VALUE_ZERO);
}
