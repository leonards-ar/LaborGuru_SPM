package com.laborguru.service.store.file;

import java.util.EnumSet;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;

import com.laborguru.exception.ErrorEnum;
import com.laborguru.exception.InvalidFieldUploadFileException;
import com.laborguru.util.PoiUtils;

/**
 *
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public abstract class BaseStoreSection {

	private static final Logger log = Logger.getLogger(BaseStoreSection.class);

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
}
