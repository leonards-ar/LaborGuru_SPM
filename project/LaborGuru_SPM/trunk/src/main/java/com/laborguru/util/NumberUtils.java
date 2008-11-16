/*
 * File name: NumberUtils.java
 * Creation date: 16/11/2008 13:54:43
 * Copyright Mindpool
 */
package com.laborguru.util;

import java.math.BigDecimal;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class NumberUtils {

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
