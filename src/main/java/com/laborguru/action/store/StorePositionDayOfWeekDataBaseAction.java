/*
 * File name: StorePositionDayOfWeekDataBaseAction.java
 * Creation date: 13/07/2008 21:16:39
 * Copyright Mindpool
 */
package com.laborguru.action.store;

import java.util.List;

import org.apache.log4j.Logger;

import com.laborguru.action.SpmActionResult;
import com.laborguru.exception.SpmCheckedException;
import com.laborguru.model.DayPart;
import com.laborguru.model.DayPartData;
import com.laborguru.model.Position;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public abstract class StorePositionDayOfWeekDataBaseAction extends StoreAdministrationBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4208407749729718476L;

	private static Logger log = Logger.getLogger(StorePositionDayOfWeekDataBaseAction.class);

	private String dayOfWeekValues[][];
	
	/**
	 * 
	 */
	public StorePositionDayOfWeekDataBaseAction() {
	}

	/**
	 * @return the dayOfWeekValues
	 */
	public String[][] getDayOfWeekValues() {
		if(dayOfWeekValues == null) {
			List<Position> p = getStore().getOrderedPositions();
			List<DayPart> d = getStore().getDayParts();
			setDayOfWeekValues(new String[p.size()][d.size()]);
		}
		return dayOfWeekValues;
	}

	/**
	 * @param dayOfWeekValues the weekdayGuestServices to set
	 */
	public void setDayOfWeekValues(String[][] dayOfWeekValues) {
		this.dayOfWeekValues = dayOfWeekValues;
	}
	
	/**
	 * Initializes the container object that will handle input of
	 * weekday guest services.
	 */
	private void loadDayOfWeekData() {
		if(getStore() != null) {
			List<Position> p = getStore().getOrderedPositions();
			List<DayPart> d = getStore().getDayParts();

			DayPartData aDayPartData;

			for(int i = 0; i < p.size(); i++) {
				for(int j = 0; j < d.size(); j++) {
					aDayPartData = p.get(i).getDayPartDataFor(d.get(j));
					if(aDayPartData != null) {
						getDayOfWeekValues()[i][j] = getValueToShow(aDayPartData);
					} else {
						// Leave it null. At the bigining there might be no values.
						getDayOfWeekValues()[i][j] = null;
					}
				}
			}
		}
	}	

	/**
	 * Puts all the corresponding values in the Store object
	 * so it can be updated.
	 */
	private void setDayOfWeekData() {
		if(getStore() != null) {
			List<Position> p = getStore().getOrderedPositions();
			List<DayPart> d = getStore().getDayParts();

			DayPartData aDayPartData;

			for(int i = 0; i < p.size(); i++) {
				for(int j = 0; j < d.size(); j++) {
					aDayPartData = p.get(i).getDayPartDataFor(d.get(j));
					if(aDayPartData != null) {
						// Already exists. Update value
						setDayPartDataValue(aDayPartData, getDayOfWeekValues()[i][j]);
					} else {
						// Add new day part data
						aDayPartData = new DayPartData();
						aDayPartData.setPosition(p.get(i));
						aDayPartData.setDayPart(d.get(j));
						setDayPartDataValue(aDayPartData, getDayOfWeekValues()[i][j]);
						p.get(i).getDayPartData().add(aDayPartData);
					}
				}
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
		loadDayOfWeekData();
		
		return SpmActionResult.EDIT.getResult();
	}
	
	/**
	 * Prepares the view page
	 * 
	 * @return
	 * @throws Exception
	 */
	public String show() throws Exception {
		loadDayOfWeekData();
		
		return SpmActionResult.SHOW.getResult();
	}

	/**
	 * Stores a store on the DB
	 * 
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		try {
			setDayOfWeekData();
			
			if(log.isDebugEnabled()) {
				log.debug("About to save store: " + getStore());
			}
			
			getStoreService().save(getStore());

			if(log.isInfoEnabled()) {
				log.info("Store weekday guest services successfully updated for store with id [" + getStoreId() + "]");
			}
			
			return SpmActionResult.SUCCESS.getResult();
		} catch (SpmCheckedException e) {
			addActionError(e.getErrorMessage());
			return SpmActionResult.INPUT.getResult();
		}
	}

	/**
	 * Validates all the times.
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	@Override
	public void validate() {
		
		if(this.dayOfWeekValues != null) {
			List<Position> p = getStore().getOrderedPositions();
			List<DayPart> d = getStore().getDayParts();
			
			for(int i=0; i < getDayOfWeekValues().length; i++) {
				for(int j=0; j < getDayOfWeekValues()[0].length; j++) {
					if(!validateValue(getDayOfWeekValues()[i][j])) {
						Position position = p.get(i);
						DayPart dayPart = d.get(j);
						addFieldError( "dayOfWeekData", getText(getValidationErrorMessageKey(), new String[] {getDayOfWeekValues()[i][j], position.getName(), dayPart.getName() }) );
					}
				}
			}
		}
		
	}
	
	/**
	 * 
	 * @return
	 */
	protected abstract String getValidationErrorMessageKey();
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	protected abstract boolean validateValue(String value);
	
	/**
	 * This method must be defined by the sub class to retrieve the
	 * correct value to show.
	 * @param dayPartData
	 * @return
	 */
	protected abstract String getValueToShow(DayPartData dayPartData);
	
	/**
	 * This method has to fill the day part data with the corresponding value
	 * @param dayPartData
	 * @param value
	 */
	protected abstract void setDayPartDataValue(DayPartData dayPartData, String value);
}
