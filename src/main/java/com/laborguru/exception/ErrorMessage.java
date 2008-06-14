package com.laborguru.exception;

public class ErrorMessage {
	String messageKey;
	Object[] arrayObject;
	

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
	public ErrorMessage(String message, Object[] arrayObject) {
		super();
		this.messageKey = message;
		this.arrayObject = arrayObject;
	}
	
	public ErrorMessage(String message) {
		super();
		this.messageKey = message;
	}
		
}
