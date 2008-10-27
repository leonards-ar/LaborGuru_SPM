package com.laborguru.action.customer;

import java.util.List;

import com.laborguru.model.Customer;
import com.laborguru.model.CustomerUser;
import com.laborguru.model.Manager;
import com.laborguru.service.customer.CustomerService;
import com.opensymphony.xwork2.Preparable;

/**
 *
 * @author <a href="fbarreraoro@gmail.com">Federico Barrera Oro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
@SuppressWarnings("serial")
public class CustomerUserPrepareAction extends UserBaseAction implements Preparable {

	private static String actionName = "customerUser";
	private static String previousActionName = "customer";
	
	private Customer customer;
	
	private CustomerService customerService;
	
	@Override
	protected void setSaveObject() {
		CustomerUser customerUser = new CustomerUser(getUser());
		customerUser.addProfile(getReferenceDataService().getCustomerRole());
		Customer auxCustomer = new Customer();
		auxCustomer.setId(getParamId());
		customerUser.setCustomer(auxCustomer);
		setManager(customerUser);
	}

	@Override
	protected List<Manager> getUserList() {
		if(getCustomer() == null) {
			loadCustomer();
		}
		return (List<Manager>)getManagerService().getCustomerUsersByCustomer(getCustomer());
	}

	@Override
	protected void setExtraInformation() {
		if(getParamId() != null) {
			loadCustomer();
		} else {
			setCustomer(((CustomerUser)getManager()).getCustomer());
		}
		setParamId(getCustomer().getId());
		
	}

	protected Manager getUserById() {
		CustomerUser tmpUser = new CustomerUser();
		tmpUser.setId(getUserId());
		return getManagerService().getManagerById((Manager)tmpUser);
	}

	public void prepare() throws Exception {
	}

	private void loadCustomer() {
		Customer auxCustomer = new Customer();
		auxCustomer.setId(getParamId());
		setCustomer(getCustomerService().getCustomerById(auxCustomer));		
	}
	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the actionName
	 */
	public static String getActionName() {
		return actionName;
	}

	/**
	 * @param actionName the actionName to set
	 */
	public static void setActionName(String actionName) {
		CustomerUserPrepareAction.actionName = actionName;
	}

	/**
	 * @return the previousActionName
	 */
	public static String getPreviousActionName() {
		return previousActionName;
	}

	/**
	 * @param previousActionName the previousActionName to set
	 */
	public static void setPreviousActionName(String previousActionName) {
		CustomerUserPrepareAction.previousActionName = previousActionName;
	}

	/**
	 * @return the customerService
	 */
	public CustomerService getCustomerService() {
		return customerService;
	}

	/**
	 * @param customerService the customerService to set
	 */
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	

}
