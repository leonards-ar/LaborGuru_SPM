package com.laborguru.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;

import org.apache.log4j.Logger;

/**
 * Utility class to handle number related methods
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class NumberUtils {

	private final static Logger log = Logger.getLogger(NumberUtils.class);

	public static final String DECIMAL_FORMAT = "#,##0";

	
	/**
	 * Parse a number with a specific format passed as parameter.
	 * 
	 * @param the number to parser
	 * @return
	 */
	public static Number stringToNumber(String number, String format) {
		try {
			return new DecimalFormat(format).parse(number);
		} catch (ParseException ex) {
			log.error("Cannot parse time [" + number + "]", ex);
			return null;
		}
	}

	/**
	 * Returns the string representation for a given number and format
	 * @param time
	 * @return
	 */
	public static String numberToString(Number number, String format) {
			return new DecimalFormat(format).format(number);
	}
	
	/**
	 * 
	 */
	private NumberUtils() {
	}

	/**
	 * 
	 * @param d
	 * @return
	 */
	public static double getDoubleValue(Double d) {
		return d != null ? d.doubleValue() : 0.0;
	}
	
	/**
	 * 
	 * @param d
	 * @return
	 */
	public static double getDoubleValue(BigDecimal bd) {
		return bd != null ? bd.doubleValue() : 0.0;
	}
	
	/**
	 * 
	 * @param i
	 * @return
	 */
	public static int getIntegerValue(Integer i) {
		return i != null ? i.intValue() : 0;
	}	
}
