package com.laborguru.action.store;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.laborguru.action.SpmAction;
import com.laborguru.action.SpmActionResult;
import com.laborguru.model.DayOfWeek;
import com.laborguru.model.OperationTime;
import com.laborguru.model.Store;
import com.laborguru.service.store.StoreService;
import com.opensymphony.xwork2.Preparable;

/**
 * This action deals with Store CRUD.
 * 
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 * 
 */
@SuppressWarnings("serial")
public class OperationTimePrepareAction extends SpmAction implements Preparable {
	
	private static Logger log = Logger.getLogger(OperationTimePrepareAction.class);
	
	private StoreService storeService;

	private Store store;

	private Integer storeId;

	private String weekOperationTimeOpen[] = new String[DayOfWeek.values().length];
	private String weekOperationTimeClose[] = new String[DayOfWeek.values().length];
	
	private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm");
	
	/**
	 * Prepare the data to be used on the edit page
	 * 
	 * @throws Exception
	 */
	public void prepareEdit() {
	}

	/**
	 * Prepare data to be used to display store data
	 */
	public void prepareShow() {
	}

	/**
	 * Prepare the data to be used on the add page. We should preload the list
	 * needed to render the add page. When a validation fails the application
	 * goes back to the add page and this data is needed.
	 * 
	 * @throws Exception
	 */
	public void prepareSave() {
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
	 * Initializes the container object that will handle input of
	 * open and close operation times.
	 */
	private void loadOperationTimes() {
		if(getStore() != null) {
			for(OperationTime time : getStore().getOperationTimes()) {
				if(time.getDayOfWeekAsInteger() != null) {
					weekOperationTimeOpen[time.getDayOfWeek().ordinal()] = dateToDisplayTime(time.getOpenHour());
					weekOperationTimeClose[time.getDayOfWeek().ordinal()] = dateToDisplayTime(time.getCloseHour());
				}
			}
		}
	}

	/**
	 * Puts all the corresponding values in the Store object
	 * so it can be updated.
	 */
	private void setOperationTimes() {
		boolean exists[] = new boolean[DayOfWeek.values().length];
		
		if(getStore().getOperationTimes() != null) {
			/*
			 * Update open and close hours of existing operation times 
			 */
			for(OperationTime operationTime : getStore().getOperationTimes()) {
				if(operationTime.getDayOfWeek() != null) {
					operationTime.setOpenHour(displayTimeToDate(weekOperationTimeOpen[operationTime.getDayOfWeek().ordinal()]));
					operationTime.setCloseHour(displayTimeToDate(weekOperationTimeClose[operationTime.getDayOfWeek().ordinal()]));
					exists[operationTime.getDayOfWeek().ordinal()] = true;
				}
			}
		}
		
		/*
		 * Handle new operation times
		 */
		OperationTime anOperationTime;
		for(int i=0; i < DayOfWeek.values().length; i++) {
			if(!exists[i]) {
				anOperationTime = new OperationTime();
				anOperationTime.setStore(getStore());
				anOperationTime.setDayOfWeek(DayOfWeek.values()[i]);
				anOperationTime.setOpenHour(displayTimeToDate(weekOperationTimeOpen[i]));
				anOperationTime.setCloseHour(displayTimeToDate(weekOperationTimeClose[i]));
				getStore().addOperationTime(anOperationTime);
			}

		}
	}
	
	/**
	 * 
	 * @param d
	 * @return
	 */
	private String dateToDisplayTime(Date d) {
		return TIME_FORMAT.format(d);
	}
	
	/**
	 * 
	 * @param time
	 * @return
	 */
	private Date displayTimeToDate(String time) {
		try {
			return TIME_FORMAT.parse(time);
		} catch (ParseException ex) {
			log.error("Cannot parse time [" + time + "]", ex);
			return null;
		}
	}
	
	/**
	 * Prepares the edit page
	 * 
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		// Getting store
		loadStoreFromId();
		
		loadOperationTimes();
		
		return SpmActionResult.EDIT.getResult();
	}

	/**
	 * Prepares the view page
	 * 
	 * @return
	 * @throws Exception
	 */
	public String show() throws Exception {
		// Getting store
		loadStoreFromId();
		
		loadOperationTimes();
		
		return SpmActionResult.SHOW.getResult();
	}

	/**
	 * Stores a store on the DB
	 * 
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		
		log.debug(this.store);

		setOperationTimes();
		
		storeService.save(this.store);

		return SpmActionResult.LISTACTION.getResult();

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
	 * @return the weekOperationTimeOpen
	 */
	public String[] getWeekOperationTimeOpen() {
		return weekOperationTimeOpen;
	}

	/**
	 * @param weekOperationTimeOpen the weekOperationTimeOpen to set
	 */
	public void setWeekOperationTimeOpen(String[] weekOperationTimeOpen) {
		this.weekOperationTimeOpen = weekOperationTimeOpen;
	}

	/**
	 * @return the weekOperationTimeClose
	 */
	public String[] getWeekOperationTimeClose() {
		return weekOperationTimeClose;
	}

	/**
	 * @param weekOperationTimeClose the weekOperationTimeClose to set
	 */
	public void setWeekOperationTimeClose(String[] weekOperationTimeClose) {
		this.weekOperationTimeClose = weekOperationTimeClose;
	}
}
