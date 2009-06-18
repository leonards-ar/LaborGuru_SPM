/*
 * File name: FixedFlexiblePrepareAction.java
 * Creation date: Jul 14, 2008 4:16:18 PM
 * Copyright Mindpool
 */
package com.laborguru.action.store;

import com.laborguru.action.utils.CustomValidators;
import com.laborguru.model.DayOfWeekData;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class FixedFlexiblePrepareAction extends	StorePositionDayOfWeekDataBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2975508637629617699L;

	/**
	 * 
	 */
	public FixedFlexiblePrepareAction() {
	}

	/**
	 * @return
	 * @see com.laborguru.action.store.StorePositionDayOfWeekDataBaseAction#getValidationErrorMessageKey()
	 */
	@Override
	protected String getValidationErrorMessageKey() {
		return "error.storeoperations.fixedflexible.value.invalid";
	}

	/**
	 * @param dayOfWeekData
	 * @return
	 * @see com.laborguru.action.store.StorePositionDayOfWeekDataBaseAction#getValueToShow(com.laborguru.model.DayOfWeekData)
	 */
	@Override
	protected String getValueToShow(DayOfWeekData dayOfWeekData) {
		return doubleToDisplayDouble(dayOfWeekData.getFixedFlexible());
	}

	/**
	 * @param dayOfWeekData
	 * @param value
	 * @see com.laborguru.action.store.StorePositionDayOfWeekDataBaseAction#setDayOfWeekDataValue(com.laborguru.model.DayOfWeekData, java.lang.String)
	 */
	@Override
	protected void setDayOfWeekDataValue(DayOfWeekData dayOfWeekData, String value) {
		dayOfWeekData.setFixedFlexible(displayDoubleToDouble(value));
	}

	/**
	 * @param value
	 * @return
	 * @see com.laborguru.action.store.StorePositionDayOfWeekDataBaseAction#validateValue(java.lang.String)
	 */
	@Override
	protected boolean validateValue(String value) {
		return CustomValidators.isValidDouble(value);
	}

}
