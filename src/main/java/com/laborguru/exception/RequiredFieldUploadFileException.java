package com.laborguru.exception;

/**
 * This exception is thrown when processing an invalid file 
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class RequiredFieldUploadFileException extends SpmUncheckedException {

	private static final long serialVersionUID = 1L;

	public RequiredFieldUploadFileException(String reason){
		this(reason, ErrorEnum.REQUIRED_FIELD_IN_FILE_TO_UPLOAD);
	}
	
	public RequiredFieldUploadFileException(Throwable t, String reason){
		super(t, reason, ErrorEnum.REQUIRED_FIELD_IN_FILE_TO_UPLOAD);
	}
	
	
	public RequiredFieldUploadFileException(String reason, String[] arrayObjects){
		super(reason, ErrorEnum.REQUIRED_FIELD_IN_FILE_TO_UPLOAD, arrayObjects);
	}
	
	private RequiredFieldUploadFileException(String reason, ErrorEnum errorCode) {
		super(reason, errorCode);
	}
}
