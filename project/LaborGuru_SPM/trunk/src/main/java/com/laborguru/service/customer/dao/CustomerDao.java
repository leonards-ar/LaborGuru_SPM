package com.laborguru.service.customer.dao;

import java.util.List;

import com.laborguru.model.Customer;


/**
 *
 * @author <a href="fbarrera@gmail.com">Federico Barrera Oro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public interface CustomerDao {
	List<Customer> findAll();
	
	Customer getCustomerById(Customer customer);
}
