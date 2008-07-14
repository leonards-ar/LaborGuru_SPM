/*
 * File name: WeekdayGuestServicesPrepareAction.java
 * Creation date: 13/07/2008 17:09:39
 * Copyright Mindpool
 */
package com.laborguru.action.store;

import com.laborguru.action.utils.CustomValidators;
import com.laborguru.model.DayPartData;

/**
 * This action deals with Store CRUD.
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class WeekdayGuestServicesPrepareAction extends StorePositionDayOfWeekDataBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4407332066625642491L;

	/**
	 * 
	 */
	public WeekdayGuestServicesPrepareAction() {
	}

	/**
	 * 
	 * @return
	 * @see com.laborguru.action.store.StorePositionDayOfWeekDataBaseAction#getValidationErrorMessageKey()
	 */
	@Override
	protected String getValidationErrorMessageKey() {
		return "error.storeoperations.weekdayguestservice.value.invalid";
	}
	
	
	/**
	 * 
	 * @param dayPartData
	 * @return
	 * @see com.laborguru.action.store.StorePositionDayOfWeekDataBaseAction#getValueToShow(com.laborguru.model.DayPartData)
	 */
	@Override
	protected String getValueToShow(DayPartData dayPartData) {
		return doubleToDisplayDouble(dayPartData.getWeekdayGuestService());
	}

	/**
	 * 
	 * @param dayPartData
	 * @param value
	 * @see com.laborguru.action.store.StorePositionDayOfWeekDataBaseAction#setDayPartDataValue(com.laborguru.model.DayPartData, java.lang.String)
	 */
	@Override
	protected void setDayPartDataValue(DayPartData dayPartData, String value) {
		dayPartData.setWeekdayGuestService(displayDoubleToDouble(value));
	}

	/**
	 * 
	 * @param value
	 * @return
	 * @see com.laborguru.action.store.StorePositionDayOfWeekDataBaseAction#validateValue(java.lang.String)
	 */
	@Override
	protected boolean validateValue(String value) {
		return CustomValidators.isValidDouble(value);
	}
}

