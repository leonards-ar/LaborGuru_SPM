package com.laborguru.service.customer.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.laborguru.model.Customer;

public class CustomerDaoHibernate extends HibernateDaoSupport implements CustomerDao {

	public List<Customer> findAll() {
		return getHibernateTemplate().loadAll(Customer.class);
	}
	
	/**
	 * 
	 */
	public Customer getCustomerById(Customer customer) {
		
		return (Customer)getHibernateTemplate().get(Customer.class, customer.getId());		
	}	

}
