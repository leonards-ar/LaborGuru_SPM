/*
 * File name: StoreAdministrationBaseAction.java
 * Creation date: Jul 9, 2008 11:20:10 AM
 * Copyright Mindpool
 */
package com.laborguru.action.store;

import com.laborguru.action.SpmAction;
import com.laborguru.frontend.HttpRequestConstants;
import com.laborguru.model.Store;
import com.laborguru.service.store.StoreService;
import com.opensymphony.xwork2.Preparable;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public abstract class StoreAdministrationBaseAction extends SpmAction implements
		Preparable {

	private Store store;

	private Integer storeId;
	
	private StoreService storeService;

	/**
	 * 
	 */
	public StoreAdministrationBaseAction() {
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
	 * Will get the current store being edited.<br/>
	 * If the store is present in session, then
	 * retrieve this store, if not retrieve the store
	 * from it
	 * @return the store
	 */
	public Store getStore() {
		if(this.store == null) {
			loadStoreFromId();
		}
		return this.store;
	}

	/**
	 * @param store
	 *            the store to set
	 */
	public void setStore(Store store) {
		this.store = store;
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
	 * Load full store from the property storeId
	 */
	private void loadStoreFromId() {
		Integer id = getStoreId();
		//:TODO: Better way to communicate actions in Struts 2?
		if(id == null) {
			id = (Integer) getSession().get(HttpRequestConstants.STORE_TO_EDIT_ID);
			setStoreId(id);
		} else {
			getSession().put(HttpRequestConstants.STORE_TO_EDIT_ID, id);
		}
		
		Store tmpStore = new Store();
		tmpStore.setId(id);
		this.setStore(getStoreService().getStoreById(tmpStore));
	}	
}
