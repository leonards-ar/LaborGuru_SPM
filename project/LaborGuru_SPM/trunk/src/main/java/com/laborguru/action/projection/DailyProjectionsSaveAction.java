package com.laborguru.action.projection;

import java.math.BigDecimal;

import com.laborguru.action.SpmActionResult;
import com.laborguru.exception.ErrorEnum;
import com.laborguru.exception.ErrorMessage;
import com.laborguru.model.OperationTime;
import com.laborguru.model.Store;

/**
 * This action deals with Save for Daily Projections.
 * 
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 * 
 */
@SuppressWarnings("serial")
public class DailyProjectionsSaveAction extends DailyProjectionsPrepareAction {

	/**
	 * Prepare data to be used in the actions methods defined for this action
	 * 
	 * @throws Exception
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	public void prepare() throws Exception {
		pageSetup();
	}
	
	/**
	 * Stores an employee on the DB
	 * 
	 * @return
	 * @throws Exception
	 */
	public String execute() throws Exception {

		//TODO:CN - This should be validated in a different place/way.I'm putting it here just for now.
		//Now it's ugly, very ugly....
		Store storeAux = this.getEmployeeStore();		
		for (int j=0; j < 7; j++){
			OperationTime operationTime = storeAux.getStoreOperationTimeByDate(getWeekDaySelector().getWeekDays().get(j));
			if (operationTime == null || operationTime.getOpenHour() == null || operationTime.getCloseHour() == null){
				addActionError(new ErrorMessage(ErrorEnum.OPERATION_TIME_IS_NOT_SET_FOR_STORE.name()));
				prepareEdit();
				setupDailyProjectionData();				
				return SpmActionResult.INPUT.getResult();
			}

		}

		//Saving each projection
		int i=0;
		for (BigDecimal dailyProjection: getAdjustedProjections()){
			getProjectionService().saveDailyProjection(this.getEmployeeStore(), dailyProjection,getWeekDaySelector().getWeekDays().get(i));
			i++;
		}
		return SpmActionResult.SUCCESS.getResult();
	}

	/**
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	public void validate(){
		
		//Checking if there was any error during the conversion
		if (!this.getFieldErrors().isEmpty()){			
			for (int i=0; i < getAdjustedProjections().size(); i++){
				Object aObject = getAdjustedProjections().get(i);
				
				if (!BigDecimal.class.isAssignableFrom(aObject.getClass())){
					addActionError(getText("error.projection.daily.nonvalidnumber", new String[]{String.valueOf(i+1)}));
					getAdjustedProjections().set(i, BigDecimal.ZERO);
				}
			}
			return;
		}
		
		//Checking values exist and are greater than zero.
		for (int i=0; i < getAdjustedProjections().size(); i++){
			BigDecimal aValue = getAdjustedProjections().get(i);
			
			if (aValue == null) {
				addActionError(getText("error.projection.daily.required", new String[]{String.valueOf(i+1)}));
				getAdjustedProjections().set(i, BigDecimal.ZERO);
				
			} else if (aValue.compareTo(BigDecimal.ZERO) < 0){
				addActionError(getText("error.projection.daily.double", new String[] {aValue.toString()}));
				getAdjustedProjections().set(i, BigDecimal.ZERO);
			}
		}
			
	}
}
