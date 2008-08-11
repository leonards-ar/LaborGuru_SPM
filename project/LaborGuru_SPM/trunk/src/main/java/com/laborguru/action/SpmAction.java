package com.laborguru.action;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.laborguru.exception.ErrorMessage;
import com.laborguru.frontend.HttpRequestConstants;
import com.laborguru.model.Employee;
import com.laborguru.model.Store;
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

	
   /**
    * @return the Client IP Address
    */
   public String getRemoteAddress() {
           return ServletActionContext.getRequest().getRemoteAddr();
   }

	/**
	 * Returns the store from the logged user if he is an employee or
	 * the store must be received as a parameter when an Administrator
	 * is creating store employees
	 * @return The store the employee belongs to
	 */
	protected Store getEmployeeStore() {
		Store store = (Store) getSession().get(HttpRequestConstants.STORE);
		if(store == null) {
			Employee employee = getLoggedEmployeeOrNull();
			if(employee != null) {
				store = employee.getStore();
			} else {
				//:TODO: Remove this, as it only works for now
				store = new Store();
				store.setId(new Integer(1));
			}
		}
		return store;
	}   
	
}
