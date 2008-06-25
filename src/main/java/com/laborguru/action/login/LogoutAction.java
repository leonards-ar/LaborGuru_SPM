package com.laborguru.action.login;

import org.apache.struts2.dispatcher.SessionMap;

import com.laborguru.action.SpmAction;
import com.laborguru.action.SpmActionResult;

@SuppressWarnings("serial")
public class LogoutAction extends SpmAction {

	/**
	 * 
	 */
	public String execute() {
		((SessionMap)super.getSession()).invalidate();
		return SpmActionResult.LOGOUT.getResult();
	}
}
