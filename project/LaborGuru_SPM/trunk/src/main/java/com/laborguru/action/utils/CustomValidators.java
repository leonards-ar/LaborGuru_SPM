/*
 * File name: CustomValidators.java
 * Creation date: 12/07/2008 08:42:27
 * Copyright Mindpool
 */
package com.laborguru.action.utils;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class CustomValidators {

	/**
	 * 
	 */
	public CustomValidators() {
	}

	/**
	 * This method checks that the value passed as a parameter is
	 * a valid military time. The accepted formats are HH:mm or
	 * HHmm (where 'HH' is hours and 'mm' minutes). If the token
	 * ':' is not present, then the time must be in the 4 digits
	 * long (completing with zeros). Ej: 0403 for four am
	 * @param time The value to check
	 * @param required If null or empty times are allowed
	 * @return if the value is correct or not
	 */
	public static boolean isValidMilitaryTime(String time, boolean required) {
		if(time != null && time.trim().length() > 0) {
			int idx;
			String h, m;
			if((idx = time.indexOf(':')) >= 0) {
				// Convert to strictly militar (HHmm)
				h = time.substring(0, idx);
				m = time.substring(idx + 1);
				// This time can be invalid too
				time = (h.length() > 1 ? h : "0" + h) + (m.length() > 1 ? m : "0" + m);
			}
			
			if(time.length() != 4) {
				return false;
			}

			h = time.substring(0, 2);
			m = time.substring(2);
			
			try {
				int hours = Integer.parseInt(h);
				int minutes = Integer.parseInt(m);
				if(hours < 0 || hours > 23) {
					return false;
				}
				
				if(minutes < 0 || minutes > 59) {
					return false;
				}
			} catch(NumberFormatException ex) {
				// Hours or minutes are not valid numbers
				return false;
			}
			// All conditions met. Valid military time
			return true;
		} else {
			// Check if requiered
			return !required;
		}
	}
	
	/**
	 * This method checks that the value passed as a parameter is
	 * a valid military time. The accepted formats are HH:mm or
	 * HHmm (where 'HH' is hours and 'mm' minutes). If the token
	 * ':' is not present, then the time must be in the 4 digits
	 * long (completing with zeros). Ej: 0403 for four am.<br/>
	 * Null or empty values are allowed.
	 * @param time The value to check
	 * @return if the value is correct or not
	 */
	public static boolean isValidMilitaryTime(String time) {	
		return isValidMilitaryTime(time, false);
	}
}