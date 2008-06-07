package com.laborguru.action.login;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

import com.laborguru.action.SpmAction;
import com.laborguru.model.User;
import com.laborguru.service.user.UserService;
import com.opensymphony.xwork2.Action;
import com.sun.tools.internal.ws.processor.model.Request;

/**
 * User Login Action. Handles Authentication for SPM Users.  
 * @author cnunez
 *
 */
public class LoginAction extends SpmAction implements RequestAware {
	private User user;
	private UserService userService;
	private Map request;
	
	public String execute() throws Exception {
		System.out.println("********** LoginAction ***********");

		/*
		if (userService.authenticateUser(user) != null){
			return Action.SUCCESS;
		}
		
		addActionError(getText("error.username.password.invalid"));
		
		return Action.INPUT;
		*/

		getRequest().put("j_username", getUser().getUserName());
		getRequest().put("j_password", getUser().getPassword());
		
		return "acegi";
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

	/**
	 * @return the request
	 */
	public Map getRequest() {
		return request;
	}

	/**
	 * @param request the request to set
	 */
	public void setRequest(Map request) {
		this.request = request;
	}
}
