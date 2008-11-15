/*
 * File name: ForgotPasswordAction.java
 * Creation date: 15/11/2008 11:27:23
 * Copyright Mindpool
 */
package com.laborguru.action.login;

import com.laborguru.action.SpmAction;
import com.laborguru.action.SpmActionResult;
import com.laborguru.model.User;
import com.laborguru.service.user.UserService;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class ForgotPasswordAction extends SpmAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3508141526333375102L;
	private String username;
	private UserService userService;
	
	/**
	 * 
	 */
	public ForgotPasswordAction() {
	}

	/**
	 * 
	 * @return
	 */
	public String execute() {
		
		if(getUsername() != null && getUsername().trim().length() > 0) {
			User user = new User();
			user.setUserName(getUsername());
			user = getUserService().getUserByUserName(user);
			
			if(user != null) {
				//:TODO: Send password by email
				
				addActionMessage(getText("login.forgot_password.message_sent", new String[] {user.getEmail()}));
			} else {
				addActionError(getText("login.forgot_password.username.invalid", new String[] {getUsername()}));
			}
		} else {
			addActionError(getText("login.forgot_password.username.empty"));
		}
		
		return SpmActionResult.LOGIN.getResult();
	}
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
