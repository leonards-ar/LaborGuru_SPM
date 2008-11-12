package com.laborguru.action.sales;



import java.io.File;

import com.laborguru.action.SpmAction;
import com.laborguru.action.SpmActionResult;
import com.laborguru.model.UploadFile;
import com.laborguru.service.dataimport.file.SalesFileProcessorService;

public class UploadPrepareAction extends SpmAction {

	private static final long serialVersionUID = 1L;

	private Integer storeId;

	private File salesFile;
	private String salesFileContentType;
	private String salesFileFileName;
	private Integer numberOfRecordsAdded;
	

	private SalesFileProcessorService salesFileProcessorService;
	
	
	public String edit(){
		return SpmActionResult.EDIT.getResult();
	}
	
	public String upload(){
		
		UploadFile retSales = salesFileProcessorService.processAndSaveFile(salesFile);
		setNumberOfRecordsAdded(retSales.getSalesRecords().size());
		
		return SpmActionResult.SUCCESS.getResult();
	}
	
	
	public String cancel(){
		return SpmActionResult.CANCEL_EDIT.getResult();
	}
	
	/**
	 * @return the storeId
	 */
	public Integer getStoreId() {
		return storeId;
	}
	/**
	 * @param storeId the storeId to set
	 */
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	/**
	 * @return the salesFile
	 */
	public File getSalesFile() {
		return salesFile;
	}
	/**
	 * @param salesFile the salesFile to set
	 */
	public void setSalesFile(File salesFile) {
		this.salesFile = salesFile;
	}
	/**
	 * @return the salesFileContentType
	 */
	public String getSalesFileContentType() {
		return salesFileContentType;
	}
	/**
	 * @param salesFileContentType the salesFileContentType to set
	 */
	public void setSalesFileContentType(String salesFileContentType) {
		this.salesFileContentType = salesFileContentType;
	}
	/**
	 * @return the salesFileFileName
	 */
	public String getSalesFileFileName() {
		return salesFileFileName;
	}
	/**
	 * @param salesFileFileName the salesFileFileName to set
	 */
	public void setSalesFileFileName(String salesFileFileName) {
		this.salesFileFileName = salesFileFileName;
	}
	
	/**
	 * @return the numberOfRecordsAdded
	 */
	public Integer getNumberOfRecordsAdded() {
		return numberOfRecordsAdded;
	}

	/**
	 * @param retSales the numberOfRecordsAdded to set
	 */
	public void setNumberOfRecordsAdded(Integer retSales) {
		this.numberOfRecordsAdded = retSales;
	}

	/**
	 * @return the salesFileProcessor
	 */
	public SalesFileProcessorService getSalesFileProcessorService() {
		return salesFileProcessorService;
	}

	/**
	 * @param salesFileProcessor the salesFileProcessor to set
	 */
	public void setSalesFileProcessorService(SalesFileProcessorService salesFileProcessor) {
		this.salesFileProcessorService = salesFileProcessor;
	}
	
}
