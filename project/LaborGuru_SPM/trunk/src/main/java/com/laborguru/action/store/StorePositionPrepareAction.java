package com.laborguru.action.store;

import org.apache.log4j.Logger;

import com.laborguru.action.SpmAction;
import com.laborguru.action.SpmActionResult;
import com.laborguru.model.Store;
import com.laborguru.service.store.StoreService;
import com.opensymphony.xwork2.Preparable;


/**
 *
 * @author <a href="fbarrera@gmail.com">Federico Barrera Oro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
@SuppressWarnings("serial")
public class StorePositionPrepareAction extends SpmAction implements Preparable {

	private static Logger log = Logger.getLogger(StorePositionPrepareAction.class);
	
	private StoreService storeService;
	
	private Store store;
	
	private Integer storeId;
	
	/**
	 * Prepare the data to be used on the edit page
	 * 
	 * @throws Exception
	 */
	public void prepare() throws Exception {
	}
	
	public void prepareEdit(){
		loadStoreFromId();
	}
	
	public String edit(){
		return SpmActionResult.EDIT.getResult();
	}
	

	/**
	 * @return the storeService
	 */
	public StoreService getStoreService() {
		return storeService;
	}
	/**
	 * @param storeService the storeService to set
	 */
	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}

	/**
	 * Load full store from the property storeId
	 */
	private void loadStoreFromId() {
		Store tmpStore = new Store();
		tmpStore.setId(this.storeId);
		this.setStore(storeService.getStoreById(tmpStore));
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
