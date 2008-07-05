package com.laborguru.action.store;

import java.util.List;

import org.apache.log4j.Logger;

import com.laborguru.action.SpmAction;
import com.laborguru.action.SpmActionResult;
import com.laborguru.model.Area;
import com.laborguru.model.Customer;
import com.laborguru.model.Region;
import com.laborguru.model.Store;
import com.laborguru.model.filter.SearchStoreFilter;
import com.laborguru.service.area.AreaService;
import com.laborguru.service.customer.CustomerService;
import com.laborguru.service.region.RegionService;
import com.laborguru.service.store.StoreService;
import com.opensymphony.xwork2.Preparable;

/**
 * This action deals with Store CRUD.
 * 
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 * 
 */
@SuppressWarnings("serial")
public class StorePrepareAction extends SpmAction implements Preparable {
	
	private static Logger log = Logger.getLogger(StorePrepareAction.class);
	
	private StoreService storeService;
	private CustomerService customerService;
	private RegionService regionService;
	private AreaService areaService;

	private Store store;

	private Area area;
	private Region region;
	private Customer customer;
	
	private List<Store> stores;

	private List<Customer> customers;
	private List<Region> regions;
	private List<Area> areas;

	private SearchStoreFilter searchStore;
	private Integer storeId;
	
	private Integer customerId;
	private Integer regionId;
	private Integer areaId;
	

	private boolean removePage;

	/**
	 * Prepare the data to be used on the edit page
	 * 
	 * @throws Exception
	 */
	public void prepareEdit() {
		loadListsForAddEditPage();
	}

	/**
	 * Prepare data to be used to display store data
	 */
	public void prepareShow() {
		loadListsForAddEditPage();
	}

	/**
	 * Prepare the data to be used on the add page
	 * 
	 * @throws Exception
	 */
	public void prepareAdd() {
		loadListsForAddEditPage();
	}

	/**
	 * Prepare the data to be used on the add page. We should preload the list
	 * needed to render the add page. When a validation fails the application
	 * goes back to the add page and this data is needed.
	 * 
	 * @throws Exception
	 */
	public void prepareSave() {
		loadListsForAddEditPage();
	}

	/**
	 * Prepare the data to be used on the list page.
	 * Loads the lists that will be used in the store
	 * search form's combo boxes.
	 */
	public void prepareSearch() {
		loadListsForListPage();
	}

	/**
	 * Prepare the data to be used on the list page.
	 * Loads the lists that will be used in the store
	 * list search form's combo boxes.
	 */
	public void prepareList() {
		loadListsForListPage();
	}
	
	/**
	 * Loads customers.
	 */
	private void loadListsForListPage() {
		setCustomers(getCustomerService().findAll());
	}
	
	/**
	 * Loads customers, regions and areas lists
	 */
	private void loadListsForAddEditPage() {
		customers = customerService.findAll();
		regions = regionService.findAll();
		areas = areaService.findAll();
		
	}

	/**
	 * Prepare data to be used in the actions methods defined for this action
	 * 
	 * @throws Exception
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	public void prepare() throws Exception {
		// It's needed by the Preparable interface, don't comment out or removed
	}

	/**
	 * Performs a Store Search
	 * 
	 * @return
	 * @throws Exception
	 */
	public String search() throws Exception {

		this.setStores(getStoreService().filterStore(getSearchStore()));

		return SpmActionResult.LIST.getResult();
	}

	/**
	 * Retrieves all the stores that must be displayed on the list page.
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		
		setStores(storeService.findAll());
		
		return SpmActionResult.LIST.getResult();
	}

	/**
	 * Prepares the add page
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return SpmActionResult.EDIT.getResult();
	}

	/**
	 * Prepares the edit page
	 * 
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		// Getting store
		loadStoreFromId();

		setArea(this.store.getArea());
		setRegion(this.area.getRegion());
		setCustomer(this.region.getCustomer());

		
		return SpmActionResult.EDIT.getResult();
	}

	/**
	 * Prepare removes page
	 * 
	 * @return
	 * @throws Exception
	 */
	public String remove() throws Exception {
		// Getting store
		loadStoreFromId();

		setArea(this.store.getArea());
		setRegion(this.area.getRegion());
		setCustomer(this.region.getCustomer());

		this.setRemovePage(true);

		return SpmActionResult.SHOW.getResult();
	}

	/**
	 * Prepares the view page
	 * 
	 * @return
	 * @throws Exception
	 */
	public String show() throws Exception {
		// Getting store
		loadStoreFromId();
		
		setArea(this.store.getArea());
		setRegion(this.area.getRegion());
		setCustomer(this.region.getCustomer());
		
		return SpmActionResult.SHOW.getResult();
	}

	/**
	 * Stores a store on the DB
	 * 
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		
		log.debug(this.store);
		Area tmpArea = new Area();
		tmpArea.setId(areaId);
		store.setArea(areaService.getAreaById(tmpArea));
		storeService.save(this.store);

		return SpmActionResult.LISTACTION.getResult();

	}

	/**
	 * Deletes a store from the DB
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		//Getting store
		Store auxStore = storeService.getStoreById(this.store);		
		storeService.delete(auxStore);
		
		return SpmActionResult.LISTACTION.getResult();
	}

	/**
	 * Load full store from the property storeId
	 */
	private void loadStoreFromId() {
		Store tmpStore = new Store();
		tmpStore.setId(this.storeId);
		this.setStore(storeService.getStoreById(tmpStore));
	}

	public boolean isRemovePage() {
		return removePage;
	}

	public void setRemovePage(boolean removePage) {
		this.removePage = removePage;
	}

	/**
	 * @return the storesSize
	 */
	public int getStoresSize() {
		return getStores() != null ? getStores().size() : 0;
	}

	/**
	 * @return the store
	 */
	public Store getStore() {
		return store;
	}

	/**
	 * @param store
	 *            the store to set
	 */
	public void setStore(Store store) {
		this.store = store;
	}

	/**
	 * @return the stores
	 */
	public List<Store> getStores() {
		return stores;
	}

	/**
	 * @param stores
	 *            the stores to set
	 */
	public void setStores(List<Store> stores) {
		this.stores = stores;
	}

	/**
	 * @return the storeId
	 */
	public Integer getStoreId() {
		return storeId;
	}

	/**
	 * @param storeId
	 *            the storeId to set
	 */
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	/**
	 * @return the storeService
	 */
	public StoreService getStoreService() {
		return storeService;
	}

	/**
	 * @param storeService
	 *            the storeService to set
	 */
	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}

	/**
	 * @return the customerService
	 */
	public CustomerService getCustomerService() {
		return customerService;
	}

	/**
	 * @param customerService
	 *            the customerService to set
	 */
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	/**
	 * @return the regionService
	 */
	public RegionService getRegionService() {
		return regionService;
	}

	/**
	 * @param regionService
	 *            the regionService to set
	 */
	public void setRegionService(RegionService regionService) {
		this.regionService = regionService;
	}

	/**
	 * @return the areaService
	 */
	public AreaService getAreaService() {
		return areaService;
	}

	/**
	 * @param areaService
	 *            the areaService to set
	 */
	public void setAreaService(AreaService areaService) {
		this.areaService = areaService;
	}

	/**
	 * @return the customers
	 */
	public List<Customer> getCustomers() {
		return customers;
	}

	/**
	 * @param customers
	 *            the customers to set
	 */
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	/**
	 * @return the regions
	 */
	public List<Region> getRegions() {
		return regions;
	}

	/**
	 * @param regions
	 *            the regions to set
	 */
	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

	/**
	 * @return the areas
	 */
	public List<Area> getAreas() {
		return areas;
	}

	/**
	 * @param areas
	 *            the areas to set
	 */
	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

	/**
	 * @return the searchStore
	 */
	public SearchStoreFilter getSearchStore() {
		return searchStore;
	}

	/**
	 * @param searchStore
	 *            the searchStore to set
	 */
	public void setSearchStore(SearchStoreFilter searchStore) {
		this.searchStore = searchStore;
	}

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
	 * @return the regionId
	 */
	public Integer getRegionId() {
		return regionId;
	}

	/**
	 * @param regionId the regionId to set
	 */
	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	/**
	 * @return the areaId
	 */
	public Integer getAreaId() {
		return areaId;
	}

	/**
	 * @param areaId the areaId to set
	 */
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
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
	
}
