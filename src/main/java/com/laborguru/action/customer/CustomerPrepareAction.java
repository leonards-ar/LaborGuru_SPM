package com.laborguru.action.customer;

import java.util.ArrayList;
import java.util.List;

import com.laborguru.action.SpmAction;
import com.laborguru.action.SpmActionResult;
import com.laborguru.frontend.HttpRequestConstants;
import com.laborguru.model.Customer;
import com.laborguru.model.Region;
import com.laborguru.service.customer.CustomerService;
import com.laborguru.service.region.RegionService;
import com.opensymphony.xwork2.Preparable;

/**
 * This class deals with Customer CRUD
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class CustomerPrepareAction extends SpmAction implements Preparable{

	private static final long serialVersionUID = 1L;

	private CustomerService customerService;
	private RegionService regionService;

	private List<Customer> customers = new ArrayList<Customer>();
	private Customer customerSearch = new Customer();
	private Customer customer = new Customer();
	private List<Region> regions = new ArrayList<Region>();
	private String newRegionName;
	private Integer index;
	
	/**
	 * @return the index
	 */
	public Integer getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}

	/**
	 * @return the newRegionName
	 */
	public String getNewRegionName() {
		return newRegionName;
	}

	/**
	 * @param newRegionName the newRegionName to set
	 */
	public void setNewRegionName(String newRegionName) {
		this.newRegionName = newRegionName;
	}

	/**
	 * @return the regions
	 */
	public List<Region> getRegions() {
		return regions;
	}

	/**
	 * @param regions the regions to set
	 */
	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

	/**
	 * @return the regionService
	 */
	public RegionService getRegionService() {
		return regionService;
	}

	/**
	 * @param regionService the regionService to set
	 */
	public void setRegionService(RegionService regionService) {
		this.regionService = regionService;
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


	private Integer customerId;

	/**
	 * @return the customerId
	 */
	public Integer getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the customerSearch
	 */
	public Customer getCustomerSearch() {
		return customerSearch;
	}

	/**
	 * @param customerSearch the customerSearch to set
	 */
	public void setCustomerSearch(Customer customerSearch) {
		this.customerSearch = customerSearch;
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

	/**
	 * Executes the list action
	 * @return
	 */
	public String list(){
		setCustomers(customerService.getAllCustomers());
		
		return SpmActionResult.LIST.getResult();
	}


	/**
	 * Executes search action
	 * @return
	 */
	public String search(){
		
		setCustomers(customerService.filterCustomers(this.getCustomerSearch()));
		
		return SpmActionResult.LIST.getResult();
	}

	/**
	 * Prepares the edit page
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		
		loadCustomerFromId();
		setRegions(new ArrayList<Region>(customer.getRegions()));
		
		return SpmActionResult.EDIT.getResult();
	}
	
	/**
	 * Prepares the add page
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return SpmActionResult.EDIT.getResult();
	}	

	/**
	 * Adds a region to the regions list
	 * @return
	 * @throws Exception
	 */
	public String addRegion() throws Exception {
		Region regionAux = new Region();
		regionAux.setName(getNewRegionName());
		getRegions().add(regionAux);
		setNewRegionName(null);
		
		return SpmActionResult.EDIT.getResult();
	}	
	
	/**
	 * Removes a region from the regions list
	 * @return
	 * @throws Exception
	 */
	public String removeRegion() throws Exception {
		if(getIndex() != null && getIndex() >= 0 && getIndex() < getRegions().size()) {
			getRegions().remove(getIndex().intValue());
		}
		
		return SpmActionResult.EDIT.getResult();
	}	
	
	/**
	 *  Load full customer from the property customerId
	 */
	private void loadCustomerFromId() {
		Integer id = getCustomerId();
		
		//TODO: Better way to communicate actions in Struts 2?
		if(id == null) {
			id = (Integer) getSession().get(HttpRequestConstants.CUSTOMER_TO_EDIT_ID);
			setCustomerId(id);
		}
		getSession().put(HttpRequestConstants.CUSTOMER_TO_EDIT_ID, id);
		
		Customer tmpCustomer = new Customer();
		tmpCustomer.setId(this.getCustomerId());
		this.setCustomer(customerService.getCustomerById(tmpCustomer));
	}
	
	/**
	 * Prepare data to be used in the actions methods defined for this action
	 * @throws Exception
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	public void prepare() throws Exception {
		//It's needed by the Preparable interface, don't comment out or removed
	}	
}
