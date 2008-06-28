package com.laborguru.model.filter;

import com.laborguru.model.Area;
import com.laborguru.model.Customer;
import com.laborguru.model.Region;
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
	private Region region;
	private Area area;
	
	
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
	 * @return the region
	 */
	public Region getRegion() {
		return region;
	}


	/**
	 * @param region the region to set
	 */
	public void setRegion(Region region) {
		this.region = region;
	}


	/**
	 * @return the area
	 */
	public Area getArea() {
		return area;
	}


	/**
	 * @param area the area to set
	 */
	public void setArea(Area area) {
		this.area = area;
	}


	/**
	 * Checks if there is any attributes defined for the filter
	 * @return True when there is at least one attribute set on the filter, otherwise false.
	 */
	public boolean isFilled() {		
		return (this.customer != null) || (this.region != null) || (this.area != null);
	}
	
}
