package com.laborguru.exception;

/**
 * Error Enum
 * List of SPM errors. All the exceptions thrown by SPM should have an error code here.
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public enum ErrorEnum {
	GENERIC_ERROR,
	GENERIC_DATABASE_ERROR,
	USERNAME_ALREADY_EXIST_ERROR,
	OPERATION_TIME_IS_NOT_SET_FOR_STORE,
	PROJECTION_DOES_NOT_EXIST,
	CHANGES_BIGGER_THAN_PROJECTION;
			
}
