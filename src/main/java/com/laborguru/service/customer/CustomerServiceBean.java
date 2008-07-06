package com.laborguru.service.customer;

import java.util.List;

import com.laborguru.model.Customer;
import com.laborguru.service.customer.dao.CustomerDao;


/**
 *
 * @author <a href="fbarrera@gmail.com">Federico Barrera Oro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class CustomerServiceBean implements CustomerService {
	
	CustomerDao customerDao;
	
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

	/**
	 * @return the customerDao
	 */
	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	/**
	 * @param customerDao the customerDao to set
	 */
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public Customer getCustomerById(Customer customer) {
		return getCustomerDao().getCustomerById(customer);
	}
	
}
