package com.laborguru.model.filter;

import java.util.ArrayList;
import java.util.List;

import com.laborguru.model.Customer;
import com.laborguru.model.SpmObject;


/**
 * Search Sotre Filter
 * @author <a href="fbarrera@gmail.com">Federico Barrera Oro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class SearchStoreFilter extends SpmObject {

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;
	
	private Customer customer;
	private String name;
	private String code;
		
	public String getHql(){
		
		List<String> hqlParams = new ArrayList<String>();
		
		StringBuilder sb = new StringBuilder("from Store store");
		
		if (customer != null){
			hqlParams.add("store.area.region.customer.id=" + customer.getId().toString());
		}

		if (code != null){
			hqlParams.add("store.code like '%"+code+"%'");
		}
		
		if (name != null){
			hqlParams.add("store.name like '%"+name+"%'");
		}
		
		int i=0;
		for (String param: hqlParams){
			if (i==0){
				sb.append(" where " + param);
			}else {
				sb.append(" and " + param);
			}
			
			i++;
		}
		
		return sb.toString();
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}


	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}


	/**
	 * Checks if there is any attributes defined for the filter
	 * @return True when there is at least one attribute set on the filter, otherwise false.
	 */
	public boolean isFilled() {		
		return (this.customer != null) || (this.name != null) || (this.code != null);
	}
	
}
