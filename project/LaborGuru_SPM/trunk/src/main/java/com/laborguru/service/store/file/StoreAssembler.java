package com.laborguru.service.store.file;

import java.util.EnumSet;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;

import com.laborguru.exception.InvalidUploadFileException;
import com.laborguru.model.Store;

/**
 * This class assembles a store instance from an excel file.
 * Usage:
 * Rows from the excel file are added and when is finished assemble is called.
 * 
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class StoreAssembler {
	
	private static final Logger log = Logger.getLogger(StoreAssembler.class);

	
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
	}
	
	private Store store;
	private StoreInformation storeInformation;
	private List<HSSFRow> storeOperation;
	private List<HSSFRow> laborAssumptions;
	private List<HSSFRow> storeAllowances;
	
	
	private StoreAssembler(){
		this.store = new Store();
		this.storeInformation = new StoreInformation();
	}
	
	public static StoreAssembler getStoreAssembler(){
		return new StoreAssembler();
	}
	
	/**
	 * @param row
	 */
	public void addToStore(HSSFRow row){
				
		StoreSection section = getRowSection(row);
		switch(section){
			case STORE_INFORMATION:
				addStoreInformation(row);
				break;
			case LABOR_ASSUMPTIONS:
				addLaborAssumption(row);				
				break;
			case STORE_ALLOWANCES:
				addStoreAllowance(row);
				break;
			case STORE_OPERATIONS:
				addStoreOperation(row);
				break;
			default: throw new IllegalArgumentException("The type passed as parameter is wrong");		
		}
	}
	
	/**
	 * @param row
	 * @return
	 */
	public boolean validateStoreInformationRow(HSSFRow row){
		HSSFCell fieldName = row.getCell((short)2);
		HSSFCell fieldValue = row.getCell((short)4);

		
		return (fieldName != null) && (fieldValue != null);
	}
	
	public void addStoreOperation(HSSFRow row) {
	}

	public void addStoreAllowance(HSSFRow row) {
	}

	public void addLaborAssumption(HSSFRow row) {
	}

	public void addStoreInformation(HSSFRow row) {
		HSSFCell fieldName = row.getCell((short)2);
		HSSFCell fieldValue = row.getCell((short)4);
		
		if (!validateStoreInformationRow(row)){

			String message = "Row is invalid - fieldName:"+fieldName+" - fieldValue:"+fieldValue;
			log.error(message);
			throw new InvalidUploadFileException(message);	
		}

		
		storeInformation.addField(fieldName.getStringCellValue().trim(), fieldValue.getStringCellValue().trim());
		
	}

	private StoreSection getRowSection(HSSFRow row){		
		HSSFCell cell = row.getCell((short) 0);
		
		
		
		String section = cell.getStringCellValue();
		
		for (StoreSection sectionType: EnumSet.allOf(StoreSection.class)){
			if (sectionType.getStoreSection().equalsIgnoreCase(section))
				return sectionType;
		}
		
		return null;
	}
	
	/**
	 * @return
	 */
	public Store assemble(){
		
		StoreAssemblerValidator.validate(storeInformation);
		storeInformation.assembleStore(this.store);
		
		return this.store;
	}
}
