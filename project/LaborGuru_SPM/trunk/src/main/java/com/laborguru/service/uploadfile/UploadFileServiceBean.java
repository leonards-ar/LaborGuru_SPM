package com.laborguru.service.uploadfile;

import com.laborguru.model.UploadFile;
import com.laborguru.service.uploadfile.dao.UploadFileDao;

/**
 * Defines the implementation for UploadFile operations on the spm
 *
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class UploadFileServiceBean implements UploadFileService {

	UploadFileDao uploadFileDao;
	
	public UploadFile getUploadFileById(Long id) {
		return null;
	}

	public void saveOrUpdate(UploadFile uploadFile) {
	}

}
