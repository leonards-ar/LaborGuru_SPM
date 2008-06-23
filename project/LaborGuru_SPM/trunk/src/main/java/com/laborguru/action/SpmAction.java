package com.laborguru.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.laborguru.action.utils.KeyValuePair;
import com.laborguru.exception.ErrorMessage;
import com.laborguru.frontend.HttpRequestConstants;
import com.laborguru.model.Employee;
import com.laborguru.model.User;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Spm Action Type
 * General point where we define common settings and task for SPM actions. 
 * @author cnunez
 *
 */
@SuppressWarnings("serial")
public class SpmAction extends ActionSupport implements SessionAware {
	private Map session;

	/**
	 * Returns the logged user from session scope
	 * @param session The http request session
	 * @return The logged user
	 */
	protected User getLoggedUser() {
		return (User) getSession().get(HttpRequestConstants.USER);
	}
	
	/**
	 * Returns the logged employee only and only if it is an employee.
	 * If not, null is returned.
	 * @param session The http request session
	 * @return The logged employee
	 */
	protected Employee getLoggedEmployeeOrNull() {
		User user = getLoggedUser();
		return user instanceof Employee ? (Employee) user : null;
	}

	/**
	 * 
	 * @return
	 */
	public Map getSession() {
		return this.session;
	}
	
	/**
	 * 
	 * @param session
	 */
	public void setSession(Map session) {
		this.session = session;
	}
	
	/**
	 * Add error message to action errors from an ErrorMessage object
	 * @param errorMessage
	 */
	protected void addActionError(ErrorMessage errorMessage){
		addActionError(getText(errorMessage.getMessageKey(), errorMessage.getParameters()));
	}
	
}
