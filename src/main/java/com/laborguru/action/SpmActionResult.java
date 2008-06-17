package com.laborguru.action;

/**
 * This Enum is a helper class that contains constants for common action result names.
 * 
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public enum SpmActionResult {
	LIST("list"),
	LISTACTION("listAction"),
	REMOVE("remove"),
	EDIT("edit"),
	SHOW("show"),
	DELETE("delte"),
	SAVE("save");
	
	private String result;
	
	private SpmActionResult(String result){
		this.result = result;
	}
	
	public String getResult(){
		return this.result;
	}
}
