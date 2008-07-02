package com.laborguru.service.customer;

import java.util.List;

import com.laborguru.model.Customer;
import com.laborguru.service.Service;


/**
 *
 * @author <a href="fbarrera@gmail.com">Federico Barrera Oro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public interface CustomerService extends Service {

	/**
	 * Get all Customers
	 * @return List with all the customers.
	 */
	List<Customer> findAll();
}
