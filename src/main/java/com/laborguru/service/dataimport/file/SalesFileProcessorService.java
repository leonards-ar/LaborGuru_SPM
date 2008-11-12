package com.laborguru.service.dataimport.file;

import java.io.File;

import com.laborguru.model.UploadFile;

/**
 * This interface define the sale file processor used to import sales data to the SPM
 * 
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public interface SalesFileProcessorService {
	
	/**
	 * This method process the file passed as parameter, creates and persist an Upload File entity and the sales records contained in the file.
	 * @param file
	 * @return The upload file entity created.
	 */
	UploadFile processAndSaveFile(File file);
	
	/**
	 * Sets the file parser to use when processing the file
	 * @param fileParser The file parser
	 */
	void setFileParser(SalesFileParser fileParser);
}
