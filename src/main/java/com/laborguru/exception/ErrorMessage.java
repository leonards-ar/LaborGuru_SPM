package com.laborguru.exception;

/**
 * Placeholder for Error messages. 
 * MessageKey contains the i18n error message we want to display
 * Objec[] parameters passed to the message
 * 
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class ErrorMessage {
	String messageKey;
	Object[] arrayObject;
	
	/**
	 * Constructor
	 * 
	 * @param message The message key
	 * @param arrayObject The parameters to pass.
	 */
	public ErrorMessage(String message, Object[] arrayObject) {
		super();
		this.messageKey = message;
		this.arrayObject = arrayObject;
	}
	
	/**
	 * Constructor
	 * @param message The message key
	 */
	public ErrorMessage(String message) {
		super();
		this.messageKey = message;
	}
	
	public String getMessageKey() {
		return messageKey;
	}
	public void setMessageKey(String message) {
		this.messageKey = message;
	}
	public Object[] getArrayObject() {
		return arrayObject;
	}
	public void setArrayObject(Object[] arrayObject) {
		this.arrayObject = arrayObject;
	}	
}
