package com.laborguru.service.store.file;

import java.util.EnumSet;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;

import com.laborguru.exception.ErrorEnum;
import com.laborguru.exception.InvalidFieldUploadFileException;
import com.laborguru.model.DayOfWeek;
import com.laborguru.util.PoiUtils;

/**
 * Base class for upload store from an excel file
 * 
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public abstract class BaseStoreSection {

	private static final Logger log = Logger.getLogger(BaseStoreSection.class);
	
	private static final double PERCENTAGE_FACTOR = 100.00;

	public enum StoreSection{
		
		STORE_INFORMATION("Store Information"),
		STORE_OPERATIONS("Store Operations"),
		LABOR_ASSUMPTIONS("Labor Assumptions"),
		STORE_ALLOWANCES("Store Allowances");		
		
		private String storeSection;
		
		StoreSection(String storeSection){
			this.storeSection = storeSection;
		}
		
		public String getStoreSection(){
			return this.storeSection;
		}
		
		public static StoreSection getSection(String section){		
			for (StoreSection sectionType: EnumSet.allOf(StoreSection.class)){
				if (sectionType.getStoreSection().equalsIgnoreCase(section))
					return sectionType;
			}
			
			return null;
		}
	}
	
	protected enum UploadWeekDays {
			SUNDAY("Sunday", DayOfWeek.SUNDAY),
			MONDAY("Monday", DayOfWeek.MONDAY),
			TUESDAY("Tuesday", DayOfWeek.TUESDAY),
			WEDNESDAY("Wednesday", DayOfWeek.WEDNESDAY),
			THURSDAY("Thursday", DayOfWeek.THURSDAY),
			FRIDAY("Friday", DayOfWeek.FRIDAY),
			SATURDAY("Saturday", DayOfWeek.SATURDAY);
			
			private String dayName;
			private DayOfWeek dayOfWeek;
	
			/**
			 * @return the dayName
			 */
			public String getDayName() {
				return dayName;
			}
	
			/**
			 * @return the dayOfWeek
			 */
			public DayOfWeek getDayOfWeek() {
				return dayOfWeek;
			}
	
	
			private UploadWeekDays(String day, DayOfWeek dayOfWeek){
				this.dayOfWeek = dayOfWeek;
				this.dayName = day;
			}
			
			/**
			 * @param fieldName
			 * @return
			 */
			public static UploadWeekDays getUploadWeekDay(String fieldName){
				for (UploadWeekDays uploadWeekDay: EnumSet.allOf(UploadWeekDays.class)){
					if (uploadWeekDay.getDayName().equalsIgnoreCase(fieldName)){
						return uploadWeekDay;
					}
				}
				
				return null;
			}
			
			/**
			 * @param fieldName
			 * @return
			 */
			public static UploadWeekDays getUploadWeekDay(DayOfWeek dayOfWeek){
				return UploadWeekDays.values()[dayOfWeek.ordinal()];
			}
		}

	private StoreSection section;
	
	/**
	 * @return the section
	 */
	public StoreSection getSection() {
		return section;
	}

	/**
	 * @param section the section to set
	 */
	public void setSection(StoreSection section) {
		this.section = section;
	}

	/**
	 * @param row
	 * @return
	 */
	public static StoreSection getRowSection(HSSFRow row){		
		HSSFCell cell = row.getCell((short) 0);
		
		String section = PoiUtils.getStringValue(cell);
		
		StoreSection retVal = StoreSection.getSection(section);
		
		if (retVal != null){
			return retVal;
		}
		
		String message = "Section: "+section+" is invalid - It is not possible to parse the row - Row:"+row;
		log.error(message);
		throw new InvalidFieldUploadFileException(message, ErrorEnum.INVALID_SECTION_ROW, new String[] {section});	
	}	
	
	/**
	 * @param fieldName
	 * @param fieldValue
	 */
	public void addField(HSSFRow row) {		
		validateRow(row);
		addRowToSection(row);
	}
	
	/**
	 * @param row
	 */
	protected abstract void addRowToSection(HSSFRow row);

	/**
	 * @param row
	 * @return
	 */
	protected void validateRow(HSSFRow row){
		HSSFCell category = row.getCell((short)1);
		HSSFCell fieldName = row.getCell((short)2);
		HSSFCell fieldValue = row.getCell((short)4);
		
		if ((category == null) || (fieldName == null) || (fieldValue == null)){
			String message = getSection().getStoreSection()+" row is invalid - category:"+category+" fieldName:"+fieldName+" - fieldValue:"+fieldValue;
			log.error(message);
			throw new InvalidFieldUploadFileException(message, ErrorEnum.STORE_INVALID_ROW, new String[] {getSection().getStoreSection()});
		}
	}

	/**
	 * @param value
	 * @return
	 */
	protected Double makePercentage(Double value) {
		return Double.valueOf(value.doubleValue() * PERCENTAGE_FACTOR);
	}
}
