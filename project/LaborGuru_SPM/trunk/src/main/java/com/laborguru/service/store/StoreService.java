package com.laborguru.service.store;

import java.util.List;

import com.laborguru.exception.SpmCheckedException;
import com.laborguru.model.Store;
import com.laborguru.model.filter.SearchStoreFilter;
import com.laborguru.service.Service;

/**
 * Store Service. Handles store services for SPM
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public interface StoreService extends Service {

	/**
	 * Saves or updates a store
	 * @param store store to save or update
	 * @return the Store updated.
	 * @throws SpmCheckedException In case there is any error during save
	 */
	Store save(Store store) throws SpmCheckedException;
	
	
	/**
	 * Removes a store
	 * @param store store to remove
	 */
	void delete(Store store);
	
	/**
	 * Retrieves a list of stores that match the filter passed in as parameter
	 * @param storeFilter the store filter
	 * @return the store list
	 */
	List<Store> filterStore(SearchStoreFilter storeFilter);
	
	/**
	 * Retrieves a store by id
	 * @param store the store with id populated
	 * @return the Store
	 */
	Store getStoreById(Store store);
	
	/**
	 * Retrieves all the stores saved on SPM
	 * @return a list of stores
	 */
	List<Store> findAll();
}
