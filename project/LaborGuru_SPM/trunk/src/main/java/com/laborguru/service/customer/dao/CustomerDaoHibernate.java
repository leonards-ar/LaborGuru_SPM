package com.laborguru.service.customer.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.laborguru.model.Customer;
import com.laborguru.service.dao.hibernate.SpmHibernateDao;

public class CustomerDaoHibernate extends SpmHibernateDao implements CustomerDao {

	private static final Logger log = Logger.getLogger(CustomerDaoHibernate.class);	

	public List<Customer> findAll() {
		return (List<Customer>) getHibernateTemplate().loadAll(Customer.class);
	}
	
	/**
	 * 
	 */
	public Customer getCustomerById(Customer customer) {
		
		return (Customer)getHibernateTemplate().get(Customer.class, customer.getId());		
	}

	/**
	 * Retrieves a list of customers based on the filter passed in as parameter.
	 * If there is no customers that match the criteria return an empty list.
	 *  
	 * @param customerSearch The filter
	 * @return
	 * @see com.laborguru.service.customer.dao.CustomerDao#applyFilter(com.laborguru.model.Customer)
	 */
	public List<Customer> applyFilter(Customer customerSearch) {
		
		if (customerSearch == null){
			throw new IllegalArgumentException("The customer filter passed as parameter is null.");
		}
		
		log.debug("Customer filter name:"+ customerSearch.getName());
		
		StringBuilder sb = new StringBuilder("from Customer customer");
		
		if (includeInFilter(customerSearch.getName())){
			sb.append(" where customer.name like '%"+customerSearch.getName()+"%'");
		}
		
		log.debug("Calling hibernate with query:"+ sb.toString());

		
		return (List<Customer>)getHibernateTemplate().find(sb.toString());
	}	

}
