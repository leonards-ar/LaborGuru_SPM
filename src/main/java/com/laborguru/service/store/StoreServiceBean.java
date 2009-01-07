package com.laborguru.service.store;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;

import com.laborguru.exception.ErrorEnum;
import com.laborguru.exception.InvalidFieldUploadFileException;
import com.laborguru.exception.InvalidUploadFileException;
import com.laborguru.exception.SpmCheckedException;
import com.laborguru.model.Area;
import com.laborguru.model.Customer;
import com.laborguru.model.DayPart;
import com.laborguru.model.OperationTime;
import com.laborguru.model.Region;
import com.laborguru.model.Store;
import com.laborguru.model.filter.SearchStoreFilter;
import com.laborguru.service.customer.CustomerService;
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
	private CustomerService customerService;
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
		Store storeToSave = null;
		
		//Checking if store already exist
		SearchStoreFilter storeFilter = new SearchStoreFilter();
		storeFilter.setCode(store.getCode());
		storeFilter.setCustomerCode(store.getArea().getRegion().getCustomer().getCode());
		
		List<Store> storeListAux = filterStore(storeFilter); 
		
		if(storeListAux.isEmpty()){
			setPersistentArea(store);			
			storeToSave = store;
		}else{
			//storeToSave = storeListAux.get(0);
			//updateStoreInUpload(store, storeToSave);
			String message ="It is not possbile to upload an store that already exist";
			log.error(message);
			throw new InvalidUploadFileException(message, ErrorEnum.INVALID_UPLOAD_STORE_ALREADY_EXISTS);		
		}
		
		storeDao.save(storeToSave);
		
		return storeToSave;
	}
	
	
	private void updateStoreInUpload(Store source, Store destination){
		//Information section
		destination.setName(source.getName());
		
		//Store Operations
		destination.setFirstDayOfWeek(source.getFirstDayOfWeek());
		
		//Operation Times
		for (OperationTime operationTime: source.getOperationTimes()){
			OperationTime existingOperationTime = destination.getOperationTime(operationTime.getDayOfWeek());
			if (existingOperationTime != null){
				//already exists
				existingOperationTime.setCloseHour(operationTime.getCloseHour());
				existingOperationTime.setOpenHour(operationTime.getOpenHour());
			}else{
				//new
				destination.addOperationTime(operationTime);
			}
		}
		
		//Day Parts
		for (DayPart dayPart: source.getDayParts()){
			DayPart existingDayPart = destination.getDayPartByName(dayPart.getName());
			if (existingDayPart != null){
				//already exists
				existingDayPart.setStartHour(dayPart.getStartHour());
			}else{
				//new
				destination.addDayPart(dayPart);
			}
		}
		//End store operations
	}
	
	
	/**
	 * @param store
	 * @return
	 */
	private void setPersistentArea(Store store) {
		String customerCode = store.getArea().getRegion().getCustomer().getCode();
		Customer customer = customerService.getCustomerByCode(store.getArea().getRegion().getCustomer());
		validateStoreField(customer, "Customer Code", customerCode);

		String regionName = store.getArea().getRegion().getName();
		Region region = customer.getRegionByName(regionName);
		validateStoreField(region, "Region", regionName);
								
		String areaName = store.getArea().getName();
		Area area = region.getAreaByName(areaName);
		validateStoreField(area, "Area", areaName);
		
		store.setArea(area);		
	}

	
	/**
	 * Check the string passed as parameter is not empty
	 * @param field
	 * @return
	 */
	private static void validateStoreField(Object field, String fieldIdName, String fieldIdValue){
		if (field == null){
			String message = fieldIdName+": "+fieldIdValue+" does not exist";
			log.error(message);
			throw new InvalidFieldUploadFileException(message, ErrorEnum.FIELD_DOES_NOT_EXIST, new String[]{fieldIdName, fieldIdValue});	
		}
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

}
