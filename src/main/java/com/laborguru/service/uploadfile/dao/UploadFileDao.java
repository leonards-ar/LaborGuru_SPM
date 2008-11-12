package com.laborguru.service.uploadfile.dao;

import com.laborguru.model.UploadFile;

/**
 * Defines the business interface for the data access operations for Upload File
 * 
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public interface UploadFileDao {

	/**
	 * It creates or updates an uploadFile instance
	 * @param uploadFile the object to persist
	 */
	void saveOrUpdate(UploadFile uploadFile);

	/**
	 * Retrieves an upload file instance by id. Takes the id of the instance passed as parameter.
	 * @param id An upload file instance with id not null.
	 * @return The full instance associated with the id or null
	 */
	UploadFile getUploadFileById(UploadFile uploadFile);
}