package com.laborguru.frontend.struts2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.util.StrutsTypeConverter;

import com.laborguru.action.utils.CustomValidators;
import com.opensymphony.xwork2.util.TypeConversionException;

public class SpmDisplayTimeConverter extends StrutsTypeConverter {

	static Logger log = Logger.getLogger(SpmDisplayTimeConverter.class);

	private static final SimpleDateFormat DISPLAY_TIME_FORMAT = new SimpleDateFormat("HH:mm");

	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		String time = values[0];
		
		if (time == null || "".equals(time.trim())){
			return null;
		}
		
		if (CustomValidators.isValidMilitaryTime(time, true))
		{
			Date date = displayTimeToDate(time);
			return date;
		}
		
		log.error("Not time valid expression [" + time + "]");
		throw new TypeConversionException("Not time valid expression [" + time + "]");		
	}


	@Override
	public String convertToString(Map context, Object arg1) {
		return dateToDisplayTime((Date)arg1);
	}

	/**
	 * 
	 * @param d
	 * @return
	 */
	private String dateToDisplayTime(Date d) {
		if (d != null) {
			return DISPLAY_TIME_FORMAT.format(d);
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param time
	 * @return
	 */
	private Date displayTimeToDate(String time) {
		try {
			return DISPLAY_TIME_FORMAT.parse(time);
		} catch (ParseException ex) {
			log.error("Cannot parse time [" + time + "]", ex);
			throw new TypeConversionException("Cannot parse time [" + time + "]", ex);
		}
	}	
	
}
