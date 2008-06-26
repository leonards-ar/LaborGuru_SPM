package com.laborguru.action.store;

import java.util.List;

import com.laborguru.action.SpmAction;
import com.laborguru.action.SpmActionResult;
import com.laborguru.model.Store;
import com.opensymphony.xwork2.Preparable;

/**
 * This action deals with Store CRUD.
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
@SuppressWarnings("serial")
public class StorePrepareAction extends SpmAction implements Preparable {

	private Store store;
	
	private List<Store> stores;
	
	private Integer storeId;
	private boolean removePage;

	
	/**
	 * Prepare the data to be used on the edit page
	 * @throws Exception
	 */
	public void prepareEdit(){
		loadListsForAddEditPage();
	}

	/**
	 * Prepare data to be used to display store data
	 */
	public void prepareShow() {
	}
	
	/**
	 * Prepare the data to be used on the add page
	 * @throws Exception
	 */
	public void prepareAdd(){
		loadListsForAddEditPage();
	}
	
	/**
	 * Prepare the data to be used on the add page
	 * We should preload the list needed to render the add page.
	 * When a validation fails the application goes back to the add page and this data is needed.
	 * @throws Exception
	 */
	public void prepareSave(){
		loadListsForAddEditPage();
	}

	/**
	 * Loads company, region and area lists
	 */
	private void loadListsForAddEditPage() {
	}
	
	/**
	 * Prepare data to be used in the actions methods defined for this action
	 * @throws Exception
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	public void prepare() throws Exception {
		//It's needed by the Preparable interface, don't comment out or removed
	}
	
	/**
	 * TODO Performs an Store Search
	 * @return
	 * @throws Exception
	 */
	public String search() throws Exception {
		return SpmActionResult.LIST.getResult();
	}
	
	/**
	 * Retrieves all the stores that must be displayed on the
	 * list page.
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		return SpmActionResult.LIST.getResult();
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
	 * Prepares the edit page
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		//Getting employee
		loadStoreFromId();
		
		return SpmActionResult.EDIT.getResult();
	}

	/**
	 * Prepare removes page
	 * @return
	 * @throws Exception
	 */
	public String remove() throws Exception {
		//Getting employee
		loadStoreFromId();
		
		this.setRemovePage(true);
		
		return SpmActionResult.SHOW.getResult();
	}
	
	/**
	 * Prepares the view page
	 * @return
	 * @throws Exception
	 */
	public String show() throws Exception {	
		//Getting employee
		loadStoreFromId();
		
		return SpmActionResult.SHOW.getResult();
	}
		
	/**
	 * Stores a store on the DB
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {		
		return SpmActionResult.LISTACTION.getResult();
	}
		

	/**
	 * Deletes a store from the DB
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {		
		return SpmActionResult.LISTACTION.getResult();
	}
	

	/**
	 *  Load full store from the property storeId
	 */
	private void loadStoreFromId() {
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
		return getStores()!=null? getStores().size():0;
	}

	/**
	 * @return the store
	 */
	public Store getStore() {
		return store;
	}

	/**
	 * @param store the store to set
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
	 * @param stores the stores to set
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
	 * @param storeId the storeId to set
	 */
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
}
