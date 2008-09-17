package com.laborguru.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class SpmConstants {

	public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm");
	public static final NumberFormat DOUBLE_FORMAT_5 = DecimalFormat.getInstance(Locale.US);
	public static final NumberFormat DOUBLE_FORMAT_2 = DecimalFormat.getInstance(Locale.US);

}
