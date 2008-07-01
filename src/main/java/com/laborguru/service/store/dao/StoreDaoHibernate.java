package com.laborguru.service.store.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.laborguru.model.Store;
import com.laborguru.model.filter.SearchStoreFilter;

/**
 * Hibernate implementation for Store Dao 
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class StoreDaoHibernate extends HibernateDaoSupport implements StoreDao {

	private static final String STORE_ID_NULL = "the store id passed in as parameter is null";
	private static final String STORE_NULL = "the store passed in as parameter is null";
		
	/**
	 * @param store
	 * @see com.laborguru.service.store.dao.StoreDao#delete(com.laborguru.model.Store)
	 */
	public void delete(Store store) {
		
		if (store == null){
			throw new IllegalArgumentException(STORE_NULL);
		}
		
		if (store.getId() == null){
			throw new IllegalArgumentException(STORE_ID_NULL);
		}
		
		getHibernateTemplate().delete(store);
	}

	/**
	 * @param storeFilter
	 * @return
	 * @see com.laborguru.service.store.dao.StoreDao#filterStore(com.laborguru.model.filter.SearchStoreFilter)
	 */
	public List<Store> filterStore(SearchStoreFilter storeFilter) {
		return null;
	}

	/**
	 * @param store
	 * @return
	 * @see com.laborguru.service.store.dao.StoreDao#getStoreById(com.laborguru.model.Store)
	 */
	public Store getStoreById(Store store) {
		
		if (store == null){
			throw new IllegalArgumentException(STORE_NULL);
		}
		
		if (store.getId() == null){
			throw new IllegalArgumentException(STORE_ID_NULL);
		}		
		
		return (Store)getHibernateTemplate().get(Store.class, store.getId());
	}

	/**
	 * @param store
	 * @return
	 * @see com.laborguru.service.store.dao.StoreDao#save(com.laborguru.model.Store)
	 */
	public Store save(Store store) {

		if (store == null){
			throw new IllegalArgumentException(STORE_NULL);
		}
		
		getHibernateTemplate().saveOrUpdate(store);
		
		return store;
	}

	/**
	 * 
	 * @return
	 * @see com.laborguru.service.store.dao.StoreDao#findAll()
	 */
	public List<Store> findAll() {
		return (List<Store>)getHibernateTemplate().find("from Store");
	}

}
