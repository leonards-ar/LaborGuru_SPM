package com.mindpool.laborguru.login;

import com.mindpool.laborguru.model.User;
import com.opensymphony.xwork2.Action;

public class LoginAction implements Action {

	private User user;
		
	public String execute() throws Exception {
		return "SUCCESS";
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
