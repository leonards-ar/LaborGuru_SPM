package com.laborguru.action.customer;

import java.util.ArrayList;
import java.util.List;

import com.laborguru.action.SpmAction;
import com.laborguru.action.SpmActionResult;
import com.laborguru.model.Customer;
import com.laborguru.service.customer.CustomerService;

public class CustomerPrepareAction extends SpmAction {

	private static final long serialVersionUID = 1L;
	private List<Customer> customers = new ArrayList<Customer>();
	private CustomerService customerService;
	
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

	/**
	 * @return the customers
	 */
	public List<Customer> getCustomers() {
		return customers;
	}

	/**
	 * @param customers the customers to set
	 */
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public String list(){
		setCustomers(customerService.getAllCustomers());
		
		return SpmActionResult.LIST.getResult();
	}
}
