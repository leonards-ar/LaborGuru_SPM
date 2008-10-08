package com.laborguru.frontend.struts2;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.util.TypeConversionException;

/**
 * Struts converter that handles the String-BigDecimal conversion for values
 * in SPM. It is used on the projections module.
 * 
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class SpmBigDecimalConverter extends StrutsTypeConverter {

	static Logger log = Logger.getLogger(SpmBigDecimalConverter.class);

	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,##0");

	/**
	 * @see org.apache.struts2.util.StrutsTypeConverter#convertFromString(java.util.Map, java.lang.String[], java.lang.Class)
	 */
	public Object convertFromString(Map context, String[] values, Class toClass) {
		String value = values[0];
		
		if (value == null || "".equals(value.trim())){
			return null;
		}
					
		try {
			Number auxValue = DECIMAL_FORMAT.parse(value.trim());
			return  new BigDecimal(auxValue.toString());
			
		} catch (ParseException e) {
			log.error("Not number valid expression [" + value + "]");
			throw new TypeConversionException("Not number valid expression [" + value + "]", e);	
		}		
	}


	/**
	 * @see org.apache.struts2.util.StrutsTypeConverter#convertToString(java.util.Map, java.lang.Object)
	 */
	public String convertToString(Map context, Object arg1) {
		return DECIMAL_FORMAT.format((BigDecimal)arg1);
	}	
}
