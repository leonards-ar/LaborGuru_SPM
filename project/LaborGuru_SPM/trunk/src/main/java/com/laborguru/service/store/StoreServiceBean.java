package com.laborguru.service.store;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;

import com.laborguru.exception.SpmCheckedException;
import com.laborguru.model.Store;
import com.laborguru.model.filter.SearchStoreFilter;
import com.laborguru.service.store.dao.StoreDao;
import com.laborguru.service.store.file.StoreDefinitionFileParser;

/**
 * Implementation for Store Service
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class StoreServiceBean implements StoreService {

	private static final Logger log = Logger.getLogger(StoreServiceBean.class);
	
	private static final String STORE_ID_NULL = "the store id passed in as parameter is null";
	private static final String STORE_NULL = "the store passed in as parameter is null";
	private static final String STORE_FILTER_NULL = "the filter passed as parameter is null";
	
	private StoreDao storeDao;
	private StoreDefinitionFileParser storeDefinitionFileParser;
	
	
	/**
	 * Removes a store
	 * @param store
	 * @see com.laborguru.service.store.StoreService#delete(com.laborguru.model.Store)
	 */
	public void delete(Store store) {
		
		if (store == null){
			throw new IllegalArgumentException(STORE_NULL);
		}
		
		if (store.getId() == null){
			throw new IllegalArgumentException(STORE_ID_NULL);
		}
		
		storeDao.delete(store);
	}

	/**
	 * @param storeFilter
	 * @return
	 * @see com.laborguru.service.store.StoreService#filterStore(com.laborguru.model.filter.SearchStoreFilter)
	 */
	public List<Store> filterStore(SearchStoreFilter storeFilter) {
		
		if (storeFilter == null){
			throw new IllegalArgumentException(STORE_FILTER_NULL);
		}
		
		List<Store> retList = storeDao.applyFilter(storeFilter);
		
		return retList;
	}

	/**
	 * @param store
	 * @return
	 * @see com.laborguru.service.store.StoreService#getStoreById(com.laborguru.model.Store)
	 */
	public Store getStoreById(Store store) {

		if (store == null){
			throw new IllegalArgumentException(STORE_NULL);
		}
		
		if (store.getId() == null){
			throw new IllegalArgumentException(STORE_ID_NULL);
		}
		return storeDao.getStoreById(store);
	}

	/**
	 * @param store
	 * @return
	 * @throws SpmCheckedException In case there is any error during save
	 * @see com.laborguru.service.store.StoreService#save(com.laborguru.model.Store)
	 */
	public Store save(Store store) {
		
		if (store == null){
			throw new IllegalArgumentException(STORE_NULL);
		}
		
		return storeDao.save(store);
	}

	/**
	 * @return the storeDao
	 */
	public StoreDao getStoreDao() {
		return storeDao;
	}

	/**
	 * @param storeDao the storeDao to set
	 */
	public void setStoreDao(StoreDao storeDao) {
		this.storeDao = storeDao;
	}

	/**
	 * @return
	 * @see com.laborguru.service.store.StoreService#findAll()
	 */
	public List<Store> findAll() {
		return storeDao.findAll();
	}

	/**
	 * @param storeDefinition
	 * @return
	 * @see com.laborguru.service.store.StoreService#processStoreDefinitionAndSave(java.io.File)
	 */
	public Store processStoreDefinitionAndSave(File storeDefinitionFile) {
		Store store = storeDefinitionFileParser.parseStore(storeDefinitionFile);
		
		//TODO: Save the store
		System.out.println("********************** Store:"+store);
		
		return store;
	}

	/**
	 * @return the storeDefinitionFileParser
	 */
	public StoreDefinitionFileParser getStoreDefinitionFileParser() {
		return storeDefinitionFileParser;
	}

	/**
	 * @param storeDefinitionFileParser the storeDefinitionFileParser to set
	 */
	public void setStoreDefinitionFileParser(StoreDefinitionFileParser storeDefinitionFileParser) {
		this.storeDefinitionFileParser = storeDefinitionFileParser;
	}

}
