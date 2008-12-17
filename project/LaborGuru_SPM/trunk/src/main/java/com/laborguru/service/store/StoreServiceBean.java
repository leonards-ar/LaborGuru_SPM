package com.laborguru.service.store;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.laborguru.exception.InvalidUploadFileException;
import com.laborguru.exception.SpmCheckedException;
import com.laborguru.model.Store;
import com.laborguru.model.filter.SearchStoreFilter;
import com.laborguru.service.store.dao.StoreDao;

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
	public Store processStoreDefinitionAndSave(File storeDefinition) {
		
		try {
			 InputStream inp = new FileInputStream(storeDefinition);
			 
			 HSSFWorkbook wb = new HSSFWorkbook(inp);
			 HSSFSheet sheet = wb.getSheetAt(0);	
			 
			 Iterator<HSSFRow> rit = (Iterator<HSSFRow>)sheet.rowIterator();
			 
			 while(rit.hasNext()) {
				HSSFRow row = rit.next();
				Iterator<HSSFCell> cit = (Iterator<HSSFCell>)row.cellIterator();
				while (cit.hasNext()) {
					HSSFCell cell = cit.next();
					System.out.println("Cell:"+cell);
				}
			}

		} catch (FileNotFoundException exceptionFNF) {
			String message = "File to parse:"+storeDefinition.getName()+ "is not found";
			log.error(message);
			throw new InvalidUploadFileException(exceptionFNF, message);
		} catch (IOException e) {
			String msg = "The file " + storeDefinition.getName() +" passed in as parameter cannot be read.";
			log.error(msg);
			throw new InvalidUploadFileException(msg);			
		}

		return null;
	}

}
