package com.laborguru.action.store;

import org.apache.log4j.Logger;

import com.laborguru.action.SpmActionResult;
import com.laborguru.action.utils.CustomValidators;
import com.laborguru.model.DayOfWeek;
import com.laborguru.model.OperationTime;

/**
 * This action deals with Store CRUD.
 * 
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 * 
 */
@SuppressWarnings("serial")
public class OperationTimePrepareAction extends StoreAdministrationBaseAction {
	
	private static Logger log = Logger.getLogger(OperationTimePrepareAction.class);

	private Integer firstDayOfWeek;
	private Integer extraScheduleHours;
	
	private String weekOperationTimeOpen[] = new String[DayOfWeek.values().length];
	private String weekOperationTimeClose[] = new String[DayOfWeek.values().length];
	
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
	 * Initializes the container object that will handle input of
	 * open and close operation times.
	 */
	private void loadOperationTimes() {
		if(getStore() != null) {
			for(OperationTime time : getStore().getOperationTimes()) {
				if(time != null && time.getDayOfWeekIndex() != null) {
					weekOperationTimeOpen[time.getDayOfWeekIndex().intValue()] = dateToDisplayTime(time.getOpenHour());
					weekOperationTimeClose[time.getDayOfWeekIndex().intValue()] = dateToDisplayTime(time.getCloseHour());
				}
			}
		}
	}

	/**
	 * Puts all the corresponding values in the Store object
	 * so it can be updated.
	 */
	private void setOperationTimes() {
		if(getStore().getOperationTimes() != null) {
			OperationTime anOperationTime;

			for(int i=0; i < getWeekOperationTimeOpen().length; i++) {
				if(getStore().getOperationTimes().size() > i && getStore().getOperationTimes().get(i) != null) {
					// Already exists
					anOperationTime = getStore().getOperationTimes().get(i);
				} else {
					// New Operation time for the current day of week
					anOperationTime = new OperationTime();
					anOperationTime.setStore(getStore());
					anOperationTime.setDayOfWeek(DayOfWeek.values()[i]);
				}
				anOperationTime.setOpenHour(displayTimeToDate(weekOperationTimeOpen[i]));
				anOperationTime.setCloseHour(displayTimeToDate(weekOperationTimeClose[i]));
				getStore().getOperationTimes().add(anOperationTime);
			}
		}
	}
	
	/**
	 * Prepares the edit page
	 * 
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		loadOperationTimes();
		setFirstDayOfWeek(getStore().getFirstDayOfWeekAsInteger());
		setExtraScheduleHours(getStore().getExtraScheduleHours());
		return SpmActionResult.EDIT.getResult();
	}

	/**
	 * Prepares the view page
	 * 
	 * @return
	 * @throws Exception
	 */
	public String show() throws Exception {
	
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
			setOperationTimes();
			getStore().setFirstDayOfWeekAsInteger(getFirstDayOfWeek());
			getStore().setExtraScheduleHours(getExtraScheduleHours());
			if(log.isDebugEnabled()) {
				log.debug("About to save store: " + getStore());
			}
			
			saveStoreAndLoadItIntoSession(getStore());
			
			if(log.isInfoEnabled()) {
				log.info("Store hours of operation successfully updated for store with id [" + getStoreId() + "]");
			}
			
			return SpmActionResult.SUCCESS.getResult();
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

	/**
	 * @return the firstDayOfWeek
	 */
	public Integer getFirstDayOfWeek() {
		return firstDayOfWeek;
	}

	/**
	 * @param firstDayOfWeek the firstDayOfWeek to set
	 */
	public void setFirstDayOfWeek(Integer firstDayOfWeek) {
		this.firstDayOfWeek = firstDayOfWeek;
	}

	/**
	 * Validates all the times.
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	@Override
	public void validate() {
		
		if(getWeekOperationTimeOpen() != null) {
			for(int i=0; i < getWeekOperationTimeOpen().length; i++) {
				if(!CustomValidators.isValidMilitaryTime(getWeekOperationTimeOpen()[i])) {
					addFieldError( "weekOperationTimeOpen", getText("error.storeoperations.hoursofoperation.opentime.invalid", new String[] {getWeekOperationTimeOpen()[i], getText("dayofweek." + i)}) );
				}
			}
		}

		if(getWeekOperationTimeClose() != null) {
			for(int i=0; i < getWeekOperationTimeClose().length; i++) {
				if(!CustomValidators.isValidMilitaryTime(getWeekOperationTimeClose()[i])) {
					addFieldError( "weekOperationTimeClose", getText("error.storeoperations.hoursofoperation.closetime.invalid", new String[] {getWeekOperationTimeClose()[i], getText("dayofweek." + i)}) );
				}
			}
		}
	}

	/**
	 * @return the extraScheduleHours
	 */
	public Integer getExtraScheduleHours() {
		return extraScheduleHours;
	}

	/**
	 * @param extraScheduleHours the extraScheduleHours to set
	 */
	public void setExtraScheduleHours(Integer extraScheduleHours) {
		this.extraScheduleHours = extraScheduleHours;
	}
}
