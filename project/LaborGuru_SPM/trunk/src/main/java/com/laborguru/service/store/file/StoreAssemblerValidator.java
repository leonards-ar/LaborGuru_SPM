package com.laborguru.service.store.file;

import org.apache.log4j.Logger;

import com.laborguru.exception.RequiredFieldUploadFileException;

public class StoreAssemblerValidator {

	private static final Logger log = Logger.getLogger(StoreAssembler.class);
	
	public StoreAssemblerValidator(){
	}

	/**
	 * Validates a store information
	 * @return
	 */
	public static void validate(StoreInformation storeInformation){
		validateRequiredField(storeInformation.getArea(), StoreInformation.StoreInformationField.AREA.getFieldName());
		validateRequiredField(storeInformation.getRegion(), StoreInformation.StoreInformationField.REGION.getFieldName());
		validateRequiredField(storeInformation.getNumber(), StoreInformation.StoreInformationField.NUMBER.getFieldName());
		validateRequiredField(storeInformation.getName(), StoreInformation.StoreInformationField.NAME.getFieldName());
		validateRequiredField(storeInformation.getClientCode(), StoreInformation.StoreInformationField.CLIENT_CODE.getFieldName());
	}
	
	/**
	 * Check the string passed as parameter is not empty
	 * @param field
	 * @return
	 */
	private static void validateRequiredField(String field, String fieldName){
		if ((field == null) || (field.equals(""))){
			String message = fieldName+" is invalid - "+fieldName+" is empty or null";
			log.error(message);
			throw new RequiredFieldUploadFileException(message, new String[]{fieldName});	
		}
	}
}
