package com.laborguru.service.store;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;

import com.laborguru.exception.ErrorEnum;
import com.laborguru.exception.InvalidFieldUploadFileException;
import com.laborguru.exception.SpmCheckedException;
import com.laborguru.model.Area;
import com.laborguru.model.Customer;
import com.laborguru.model.DayOfWeekData;
import com.laborguru.model.DayPart;
import com.laborguru.model.DayPartData;
import com.laborguru.model.Position;
import com.laborguru.model.PositionGroup;
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
/**
 *
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
			storeToSave = storeListAux.get(0);
			updateStoreInUpload(store, storeToSave);
		}
		
		storeDao.save(storeToSave);
		
		return storeToSave;
	}
	
	
	/**
	 * @param source
	 * @param destination
	 */
	private void updateStoreInUpload(Store source, Store destination){
		//The only sections that can be updated are Store Allowances and Labor Assumptions so we ignore the other sections.
		
		//Updating Labor Assumptions
		updateLaborAssumptions(source, destination);
				
		//Updating Store Allowances
		for(Position position :source.getPositions()){
			
			//DayParts data
			for(DayPartData data: position.getDayPartData()){
				updateDayPartData(data, destination);
			}
			
			for(DayOfWeekData dayData: position.getDayOfWeekData()){
				updateDayOfWeekData(dayData, destination);
			}
		}
	}

	/**
	 * @param source
	 * @param destination
	 */
	private void updateLaborAssumptions(Store source, Store destination) {
		Double value = source.getEarnedBreakFactor();		
		if (value != null){
			destination.setEarnedBreakFactor(value);
		}
		
		value = source.getScheduleInefficiency();		
		if (value != null){
			destination.setScheduleInefficiency(value);
		}

		value = source.getFillInefficiency();		
		if (value != null){
			destination.setFillInefficiency(value);
		}
		
		value = source.getTrainingFactor();		
		if (value != null){
			destination.setTrainingFactor(value);
		}
		
		value = source.getFloorManagementFactor();		
		if (value != null){
			destination.setFloorManagementFactor(value);
		}
		
		value = source.getAllPositionsUtilization();		
		if (value != null){
			destination.setAllPositionsUtilization(value);
		}
		
		Integer intValue = source.getMinimumFloorManagementHours();		
		if (intValue != null){
			destination.setMinimumFloorManagementHours(intValue);
		}

		
		for(Position sourcePosition: source.getPositions()){
			Position destinationPos = destination.getPositionByName(sourcePosition.getName());
			if (destinationPos != null){
				updatePositionUtilization(sourcePosition, destinationPos);
				
				//Update position group
				PositionGroup sourceGroup = sourcePosition.getPositionGroup();
				
				if (sourceGroup!= null){
					PositionGroup destinationGroup = destination.getPositionGroupByName(sourceGroup.getName());
					
					if (destinationGroup != null){
						PositionGroup originalDestinationGroup = destinationPos.getPositionGroup();
						originalDestinationGroup.removePosition(destinationPos);
						destinationGroup.addPosition(destinationPos);
					}
				}
			}
		}
	}
	
	/**
	 * @param source
	 * @param destination
	 */
	private void updatePositionUtilization(Position source, Position destination){
		
		Double valueAux  = source.getUtilizationBottom();		
		if (valueAux != null){
			destination.setUtilizationBottom(valueAux);
		}

		valueAux  = source.getUtilizationTop();		
		if (valueAux != null){
			destination.setUtilizationTop(valueAux);
		}

		Integer intValue  = source.getUtilizationMaximum();		
		if (intValue != null){
			destination.setUtilizationMaximum(intValue);
		}

		intValue  = source.getUtilizationMinimum();		
		if (intValue != null){
			destination.setUtilizationMinimum(intValue);
		}
	}
	
	
	
	/**
	 * @param data
	 * @param destination
	 */
	private void updateDayPartData(DayPartData data, Store destination){
		DayPart dayPart = destination.getDayPartByName(data.getDayPart().getName());
		if(dayPart != null){			
			Position position = destination.getPositionByName(data.getPosition().getName());
			
			if(position != null)
			{
				DayPartData destinationData = position.getDayPartDataFor(dayPart);
				
				if (destinationData != null){
					replaceDayPartData(data, destinationData);
				}else{
					addDayPartData(position, dayPart, data);
				}
			}
			
		}
	}
	
	
	private void updateDayOfWeekData(DayOfWeekData data, Store destination){			
		Position position = destination.getPositionByName(data.getPosition().getName());
			
		if(position != null)
		{
			DayOfWeekData destinationData = position.getDayOfWeekDataFor(data.getDayOfWeek());
			
			if (destinationData != null){
				replaceDayOfWeekData(data, destinationData);
			}else{
				addDayOfWeekData(position, data);
			}
		}
	}
	
	/**
	 * @param position
	 * @param data
	 */
	private void addDayOfWeekData(Position position, DayOfWeekData data) {
		data.setPosition(position);
		position.getDayOfWeekData().add(data);
	}

	/**
	 * @param data
	 * @param destinationData
	 */
	private void replaceDayOfWeekData(DayOfWeekData data, DayOfWeekData destinationData) {
		Double value = null;
		
		value = data.getFixedClosing();
		if (value != null){
			destinationData.setFixedClosing(value);
		}
		
		value = data.getFixedFlexible();
		if (value != null){
			destinationData.setFixedFlexible(value);
		}

		
		value = data.getFixedOpening();
		if (value != null){
			destinationData.setFixedOpening(value);
		}

		value = data.getFixedPostRush();
		if (value != null){
			destinationData.setFixedPostRush(value);
		}		
	}

	/**
	 * @param position
	 * @param dayPart
	 * @param data
	 */
	private void addDayPartData(Position position, DayPart dayPart, DayPartData data) {
		data.setPosition(position);
		data.setDayPart(dayPart);
		position.getDayPartData().add(data);
	}

	/**
	 * @param data
	 * @param destinationData
	 */
	private void replaceDayPartData(DayPartData data, DayPartData destinationData) {
		Double value = null;
		
		value = data.getFixedGuestService();
		if (value != null){
			destinationData.setFixedGuestService(value);
		}
		
		value = data.getVariableFlexible();
		if (value != null){
			destinationData.setVariableFlexible(value);
		}

		
		value = data.getVariableOpening();
		if (value != null){
			destinationData.setVariableOpening(value);
		}

		value = data.getWeekdayGuestService();
		if (value != null){
			destinationData.setWeekdayGuestService(value);
		}
		
		value = data.getWeekendGuestService();
		if (value != null){
			destinationData.setWeekendGuestService(value);
		}
		
		Integer minimumStaffing = data.getMinimunStaffing();
		if (minimumStaffing != null){
			destinationData.setMinimunStaffing(minimumStaffing);
		}

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
