package com.laborguru.service.uploadfile;

import java.util.List;

import com.laborguru.model.UploadFile;

/**
 * Defines the business interface for UploadFile operations on the spm
 * 
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public interface UploadFileService {

	/**
	 * Retrieves an upload file instance by id
	 * @param id The object id
	 * @return The instance associated or null
	 */
	UploadFile getUploadFileById(Long id);

	/**
	 * Retrieves a list with all upload files in the system
	 * @return
	 */
	List<UploadFile> findAllUploadFiles();
	
	/**
	 * Deletes an upload file from the spm
	 * @param uploadFile The upload file with id not null
	 * @return the UploadFile removed
	 */
	UploadFile delete(UploadFile uploadFile);
}
