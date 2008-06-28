package com.laborguru.service.store;

import java.util.List;

import com.laborguru.model.Store;
import com.laborguru.service.Service;

/**
 * Store Service
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public interface StoreService extends Service {

	Store save(Store store);
	
	void delete(Store store);
	
	//TODO: Receives a StoreFilter as parameter
	List<Store> filterStore();
	
	Store getStoreById(Store store);
}
