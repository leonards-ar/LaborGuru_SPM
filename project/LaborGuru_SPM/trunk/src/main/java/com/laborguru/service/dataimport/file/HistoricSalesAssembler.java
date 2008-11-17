package com.laborguru.service.dataimport.file;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import com.laborguru.exception.FileParserException;
import com.laborguru.model.HistoricSales;
import com.laborguru.model.Store;
import com.laborguru.util.CalendarUtils;
import com.laborguru.util.NumberUtils;

/**
 * Line[]
 * 0 - Store Number - We use this to identify the store
 * 1 - String Location Code
 * 2 - Date - M/dd/yyyy
 * 3 - Half hour - h:mm a
 * 4 - Main Value
 * 5 - Sencond Value - Optional
 * 6 - Thrid Value - Optional
 * 7 - Fourth Value - Optional
 * 
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class HistoricSalesAssembler {
	private static final Logger log = Logger.getLogger(HistoricSalesAssembler.class);

	private final static String DATE_PARSE_FORMAT = "M/dd/yyyy";
	private final static String TIME_PARSE_FORMAT = "h:mm:ss a";
	
	public static HistoricSales getHistoricSales(String[] line){		
		HistoricSales historicSale = new HistoricSales();		
		
		//Setting store code
		String storeCode = line[0];
		
		if (storeCode != null){
			Store store = new Store();		
			store.setCode(line[0]);		
			historicSale.setStore(store);
		} else {
			String message = "Parsing error: store code is null";
			log.debug(message);
			throw new FileParserException(message);
		}
		
		//Setting date time & day of the week
		Date date = CalendarUtils.stringToDate(line[2], DATE_PARSE_FORMAT);
		
		if (date != null){
			Calendar calendarDate = CalendarUtils.getCalendar(date);		
			
			Date time = CalendarUtils.stringToDate(line[3], TIME_PARSE_FORMAT);
			if (time != null){
				Calendar calendarTime = CalendarUtils.getCalendar(time);		

				calendarDate.add(Calendar.HOUR, calendarTime.get(Calendar.HOUR));
				calendarDate.add(Calendar.MINUTE, calendarTime.get(Calendar.MINUTE));
			
				historicSale.setDateTime(calendarDate.getTime());
				historicSale.setDayOfWeek(calendarDate.get(Calendar.DAY_OF_WEEK));
			} else {
				String message = "Parsing error: time "+line[3]+" is not valid";
				log.debug(message);
				throw new FileParserException(message);
			}
		} else {
			String message = "Parsing error: date "+line[2]+" is not valid";
			log.debug(message);
			throw new FileParserException(message);
		}
		
		//Setting Main Value
		Number mainValue = NumberUtils.stringToNumber(line[4], NumberUtils.DECIMAL_FORMAT);
		
		if (mainValue != null){
			BigDecimal mainBD = new BigDecimal(mainValue.toString());
			historicSale.setMainValue(mainBD);
		} else {
			String message = "Parsing error: main value "+line[4]+" is not valid";
			log.debug(message);
			throw new FileParserException(message);
		}
		
		if (line.length > 5){
			//Setting Main Value
			Number secondValue = NumberUtils.stringToNumber(line[5], NumberUtils.DECIMAL_FORMAT);
			
			if (secondValue != null){
				BigDecimal secondBD = new BigDecimal(secondValue.toString());
				historicSale.setSecondValue(secondBD);
			} else {
				String message = "Parsing error: second value "+line[5]+" is not valid";
				log.debug(message);
			}
			
			if (line.length > 6){
				//Setting Main Value
				Number thirdValue = NumberUtils.stringToNumber(line[6], NumberUtils.DECIMAL_FORMAT);
				
				if (thirdValue != null){
					BigDecimal thirdBD = new BigDecimal(thirdValue.toString());
					historicSale.setThirdValue(thirdBD);	
				} else {
					String message = "Parsing error: third value "+line[6]+" is not valid";
					log.debug(message);
				}
				
				if (line.length > 7){
					//Setting Main Value
					Number fourthValue = NumberUtils.stringToNumber(line[7], NumberUtils.DECIMAL_FORMAT);
					if (fourthValue != null){
						BigDecimal fourthBD = new BigDecimal(fourthValue.toString());
						historicSale.setFourthValue(fourthBD);					
					} else {
						String message = "Parsing error: fourth value "+line[7]+" is not valid";
						log.debug(message);
					}
				}				
				
			}
		}
		
		return historicSale;
	}
}
