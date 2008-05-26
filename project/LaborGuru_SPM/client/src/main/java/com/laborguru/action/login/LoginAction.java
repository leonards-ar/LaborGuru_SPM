package com.laborguru.action.login;

import com.laborguru.action.SpmAction;
import com.laborguru.model.User;
import com.opensymphony.xwork2.Action;

/**
 * User Login Action. Handles Authentication for SPM Users.  
 * @author cnunez
 *
 */
public class LoginAction extends SpmAction {

	private User user;
	

	public String execute() throws Exception {
		return Action.SUCCESS;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}	
}
