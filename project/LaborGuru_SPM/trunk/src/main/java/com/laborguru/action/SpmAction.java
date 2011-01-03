package com.laborguru.action;

import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.laborguru.exception.ErrorEnum;
import com.laborguru.exception.ErrorMessage;
import com.laborguru.exception.SpmUncheckedException;
import com.laborguru.frontend.HttpRequestConstants;
import com.laborguru.model.Customer;
import com.laborguru.model.CustomerUser;
import com.laborguru.model.Employee;
import com.laborguru.model.Region;
import com.laborguru.model.RegionalUser;
import com.laborguru.model.Store;
import com.laborguru.model.User;
import com.laborguru.util.CalendarUtils;
import com.laborguru.util.SpmConstants;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Spm Action Type
 * General point where we define common settings and task for SPM actions. 
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
@SuppressWarnings("serial")
public class SpmAction extends ActionSupport implements SessionAware,RequestAware{
	private static Logger log = Logger.getLogger(SpmAction.class);
	
	private Map session;
	private Map request;

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
				//Setting back the store to the session
				getSession().put(HttpRequestConstants.STORE,store);
			} else {
				throw new SpmUncheckedException("There is no store present in session. Called from the admin interface or session timed out?", ErrorEnum.NO_STORE_IN_SESSION);
			}
		}
		return store;
	}
	
	protected Customer getCustomer() {
		Customer customer = (Customer) getSession().get(HttpRequestConstants.CUSTOMER);
		if(customer == null) {
			CustomerUser customerUser = (CustomerUser)getLoggedUser();
			customer = customerUser.getCustomer();
			if(customer != null) {
				getSession().put(HttpRequestConstants.CUSTOMER, customer);
			} else {
				throw new SpmUncheckedException("There is no customer present in session. Called from a user that is not a Customer Manager interface or session timed out?", ErrorEnum.NO_STORE_IN_SESSION);
			}
		}
		return customer;

	}
	
	protected Region getRegion() {
		Region region = (Region) getSession().get(HttpRequestConstants.REGION);
		if(region == null) {
			RegionalUser regionalUser = (RegionalUser)getLoggedUser();
			region = regionalUser.getRegion();
			if(region != null) {
				getSession().put(HttpRequestConstants.REGION, region);
			} else {
				throw new SpmUncheckedException("There is no regin present in session. Called from a user that is not a Regional Manager interface or session timed out?", ErrorEnum.NO_STORE_IN_SESSION);
			}
		}
		return region;
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
	
	/**
	 * 
	 * @param d
	 * @return
	 */
	protected String dateToDisplayTime(Date d) {
		if(d != null) {
			return SpmConstants.TIME_FORMAT.format(d);
		} else {
			return null;
		}
	}	
	
	/**
	 * 
	 * @param time
	 * @return
	 */
	protected Date displayTimeToDate(String time) {
		try {
			if(time != null) {
				return CalendarUtils.inputTimeToDate(time);
			} else {
				return null;
			}
		} catch (Exception ex) {
			log.error("Cannot parse time [" + time + "]", ex);
			return null;
		}
	}	
}
