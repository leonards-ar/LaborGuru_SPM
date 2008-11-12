package com.laborguru.service.dataimport.file;

import java.io.File;

import com.laborguru.model.HistoricSales;
import com.laborguru.service.store.StoreService;

/**
 * Defines the Sales File Parser interface.
 * This defines the parsing mechanism when uploading a Sales File to Spm.
 * Implementation of this interface can allow the system to import: csv files, fixed-lenght files, excel or xml files.
 *  
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public interface SalesFileParser {
	
	/**
	 * Factory method that initalizes a SalesFileParser instance.
	 * 
	 * @return A salesFileParser initialized with the file passed in as parameter.
	 */ 
	SalesFileParser assembleSalesFileParser(File fileToParse);
	
	/**
	 * Returns the next Historic Sales Record from the file. When the end of file is reached or if there is no more valid records to return.
	 * this method returns NULL
	 * @return HistoricSales a HistoricSales instance;
	 */
	HistoricSales getNextRecord();
	
	/**
	 * Returns the filename for the file that set
	 * @return
	 */
	String getFilename();
	
	/**
	 * Set the store service to be used when creating the HistoricSales.
	 * @param storeService
	 */
	void setStoreService(StoreService storeService);
	
	/**
	 * Check whether the file is valid
	 * @return whether the file is valid
	 */
	boolean isFileValid();
}
