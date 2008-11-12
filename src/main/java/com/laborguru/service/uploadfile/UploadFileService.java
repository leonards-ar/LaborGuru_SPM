package com.laborguru.service.uploadfile;

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
	 * It creates or updates an uploadFile instance
	 * @param uploadFile the object to persist
	 */
	void saveOrUpdate(UploadFile uploadFile);

	/**
	 * Retrieves an upload file instance by id
	 * @param id The object id
	 * @return The instance associated or null
	 */
	UploadFile getUploadFileById(Long id);

}
