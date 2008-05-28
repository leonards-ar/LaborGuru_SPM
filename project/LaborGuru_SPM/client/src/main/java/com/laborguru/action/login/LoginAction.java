package com.laborguru.action.login;

import java.util.Map;

import org.apache.struts2.interceptor.ApplicationAware;
import org.springframework.context.ApplicationContext;

import com.laborguru.action.SpmAction;
import com.laborguru.model.User;
import com.laborguru.service.user.UserService;
import com.laborguru.service.user.UserServiceBean;
import com.opensymphony.xwork2.Action;

/**
 * User Login Action. Handles Authentication for SPM Users.  
 * @author cnunez
 *
 */
public class LoginAction extends SpmAction {
	private User user;
	private UserService userService;

	public String execute() throws Exception {

		if (userService.authenticateUser(user) != null){
			return Action.SUCCESS;
		}
		
		addActionError("invalid username or password");
		
		return Action.INPUT;	
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

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
