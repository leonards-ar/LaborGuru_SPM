package com.laborguru.action.projection;

import com.laborguru.action.SpmAction;
import com.laborguru.action.SpmActionResult;
import com.laborguru.model.StoreVariableDefinition;
import com.laborguru.service.store.StoreService;
import com.opensymphony.xwork2.Preparable;

/**
 * This action deals with Projections Settings.
 * 
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 * 
 */
@SuppressWarnings("serial")
public class ProjectionSettingsPrepareAction extends SpmAction implements Preparable {
	
	private Integer dailyWeeksUsedDefault;
	private Integer halfHourWeeksUsedDefault;
	private Double averageVariable;
	
	private StoreService storeService;

	/**
	 * Prepare the data to be used on the edit page
	 * 
	 * @throws Exception
	 */
	public void prepareEdit() {
		setDailyWeeksUsedDefault(this.getEmployeeStore().getDailyProjectionsWeeksDefault());
		setHalfHourWeeksUsedDefault(this.getEmployeeStore().getHalfHourProjectionsWeeksDefault());
		setAverageVariable(this.getEmployeeStore().getAverageVariable());
	}

	/**
	 * 
	 * @return
	 */
	public String getMainVariableName() {
		StoreVariableDefinition varDef = this.getEmployeeStore().getMainVariableDefinition();
		return varDef != null ? varDef.getName() : "";
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
	 * Prepares the edit page
	 * 
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {

		return SpmActionResult.EDIT.getResult();
	}

	/**
	 * Stores an employee on the DB
	 * 
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		
		getEmployeeStore().setDailyProjectionsWeeksDefault(getDailyWeeksUsedDefault());
		getEmployeeStore().setHalfHourProjectionsWeeksDefault(getHalfHourWeeksUsedDefault());
		getEmployeeStore().setAverageVariable(getAverageVariable());
		
		storeService.save(getEmployeeStore());
		
		return SpmActionResult.SUCCESS.getResult();
	}

	/**
	 * @return the dailyWeeksUsedDefault
	 */
	public Integer getDailyWeeksUsedDefault() {
		return dailyWeeksUsedDefault;
	}

	/**
	 * @param dailyWeeksUsedDefault the dailyWeeksUsedDefault to set
	 */
	public void setDailyWeeksUsedDefault(Integer dailyWeeksUsedDefault) {
		this.dailyWeeksUsedDefault = dailyWeeksUsedDefault;
	}

	/**
	 * @return the halfHourWeeksUsedDefault
	 */
	public Integer getHalfHourWeeksUsedDefault() {
		return halfHourWeeksUsedDefault;
	}

	/**
	 * @param halfHourWeeksUsedDefault the halfHourWeeksUsedDefault to set
	 */
	public void setHalfHourWeeksUsedDefault(Integer halfHourWeeksUsedDefault) {
		this.halfHourWeeksUsedDefault = halfHourWeeksUsedDefault;
	}

	/**
	 * @return the storeService
	 */
	public StoreService getStoreService() {
		return storeService;
	}

	/**
	 * @param storeService the storeService to set
	 */
	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}

	/**
	 * @return the averageVariable
	 */
	public Double getAverageVariable() {
		return averageVariable;
	}

	/**
	 * @param averageVariable the averageVariable to set
	 */
	public void setAverageVariable(Double averageVariable) {
		this.averageVariable = averageVariable;
	}
}
