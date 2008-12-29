package com.laborguru.action.store;

import java.io.File;

import com.laborguru.action.SpmAction;
import com.laborguru.action.SpmActionResult;
import com.laborguru.exception.ErrorMessage;
import com.laborguru.exception.RequiredFieldUploadFileException;
import com.laborguru.exception.SpmUncheckedException;
import com.laborguru.service.store.StoreService;

public class UploadDefinitionPrepareAction extends SpmAction{

	private static final long serialVersionUID = 1L;

	//The 3 variables below, are declared following the convention defined by UploadFileInterceptor (Strust2 object that handles a file upload).
	//Don't change the names without look the documentation in struts2 for File Uploads.
	private File storeDefinition;
	private String storeDefinitionContentType;
	private String storeDefinitionFileName;
	
	private StoreService storeService;
	
	/**
	 * @return
	 */
	public String edit(){
		return SpmActionResult.EDIT.getResult();
	}

	/**
	 * @return
	 */
	public String upload(){
		
		try{
			storeService.processStoreDefinitionAndSave(storeDefinition);
			
		}catch(RequiredFieldUploadFileException fieldException){
			ErrorMessage errorMessage = new ErrorMessage("error.store.storeDefinition.file", new String[] {storeDefinitionFileName});
			this.addActionError(errorMessage);
			this.addActionError(fieldException.getErrorMessage());
		}
		catch (SpmUncheckedException e){
			ErrorMessage errorMessage = new ErrorMessage("error.store.storeDefinition.file", new String[] {storeDefinitionFileName});
			this.addActionError(errorMessage);
		}		
		return SpmActionResult.EDIT.getResult();
	}	
	
	/**
	 * @return
	 */
	public String cancel(){
		return SpmActionResult.CANCEL.getResult();
	}
	
	/**
	 * @return the storeDefinition
	 */
	public File getStoreDefinition() {
		return storeDefinition;
	}


	/**
	 * @param storeDefinition the storeDefinition to set
	 */
	public void setStoreDefinition(File storeDefinition) {
		this.storeDefinition = storeDefinition;
	}


	/**
	 * @return the storeDefinitionContentType
	 */
	public String getStoreDefinitionContentType() {
		return storeDefinitionContentType;
	}


	/**
	 * @param storeDefinitionContentType the storeDefinitionContentType to set
	 */
	public void setStoreDefinitionContentType(String storeDefinitionContentType) {
		this.storeDefinitionContentType = storeDefinitionContentType;
	}


	/**
	 * @return the storeDefinitionFileName
	 */
	public String getStoreDefinitionFileName() {
		return storeDefinitionFileName;
	}


	/**
	 * @param storeDefinitionFileName the storeDefinitionFileName to set
	 */
	public void setStoreDefinitionFileName(String storeDefinitionFileName) {
		this.storeDefinitionFileName = storeDefinitionFileName;
	}

	/**
	 * @return the storeService
	 */
	public StoreService getStoreService() {
		return storeService;
	}

	/**
	 * @param storeService the storeService to set
	 */
	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}
}
