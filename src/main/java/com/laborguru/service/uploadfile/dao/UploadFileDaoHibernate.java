package com.laborguru.service.uploadfile.dao;

import org.apache.log4j.Logger;

import com.laborguru.model.UploadFile;
import com.laborguru.service.dao.hibernate.SpmHibernateDao;

/**
 * 
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class UploadFileDaoHibernate extends SpmHibernateDao implements UploadFileDao{

	private static final Logger log = Logger.getLogger(UploadFileDaoHibernate.class);
	private static final String UPLOAD_FILE_NULL = "The upload file object passed in as parameter is null";
	private static final String UPLOAD_FILE_ID_NULL = "The upload file object passed in as parameter has ID null";
	
	/**
	 * Retrieves an upload file instance by id. Takes the id of the instance passed as parameter.
	 * @param id An upload file instance with id not null.
	 * @return The full instance associated with the id or null
	 */
	public UploadFile getUploadFileById(UploadFile uploadFile) {
		
		
		if (uploadFile == null){
			log.error(UPLOAD_FILE_NULL);
			throw new IllegalArgumentException(UPLOAD_FILE_NULL);
		}
		
		if (uploadFile.getId() == null){
			log.error(UPLOAD_FILE_ID_NULL);
			throw new IllegalArgumentException(UPLOAD_FILE_ID_NULL);
		}		
		
		return (UploadFile) getHibernateTemplate().get(UploadFile.class, uploadFile.getId());		
	}


	/**
	 * It creates or updates an uploadFile instance
	 * @param uploadFile the object to persist
	 */
	public void saveOrUpdate(UploadFile uploadFile) {
		
		if (uploadFile == null){
			log.error(UPLOAD_FILE_NULL);
			throw new IllegalArgumentException(UPLOAD_FILE_NULL);
		}
		
		getHibernateTemplate().saveOrUpdate(uploadFile);
	}	

}
