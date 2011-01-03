package com.laborguru.action.demo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.laborguru.action.SpmAction;
import com.laborguru.action.SpmActionResult;
import com.laborguru.exception.ErrorMessage;
import com.laborguru.exception.SpmCheckedException;
import com.laborguru.model.DayOfWeek;
import com.laborguru.model.DayOfWeekData;
import com.laborguru.model.DayPart;
import com.laborguru.model.DayPartData;
import com.laborguru.model.Employee;
import com.laborguru.model.OperationTime;
import com.laborguru.model.Position;
import com.laborguru.model.PositionGroup;
import com.laborguru.model.Store;
import com.laborguru.model.StoreVariableDefinition;
import com.laborguru.service.data.ReferenceDataService;
import com.laborguru.service.employee.EmployeeService;
import com.laborguru.service.store.StoreService;

/**
 * This class deals with the Demo Store & User Creation Wizard
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class DemoWizardPrepareAction extends SpmAction {
	private static final long serialVersionUID = 1L;

	private static final String STORE_SESSION_KEY = "_storeKey_";
	private static final String EMPLOYEE_SESSION_KEY = "_employeeKey_";

	private StoreService storeService;
	private EmployeeService employeeService;
	private ReferenceDataService referenceDataService;
	
	private List<Store> demoStores;
	
	private Integer sourceDemoStoreId;
	private Store sourceDemoStore;
	
	private String weekOperationTimeOpen[] = new String[DayOfWeek.values().length];
	private String weekOperationTimeClose[] = new String[DayOfWeek.values().length];
	
	private List<String> variableDefinitionNames;
	private String newVariableDefinitionName;

	private List<Position> positions;
	private Position newPosition;
	
	private Integer index;
	
	private String actionButton;
	
	private List<String> statesList;

	private String storeName;
	private String employeeUserName;
	
	/**
	 * This property holds an empty position set by Spring containing
	 * default values
	 */
	private Position demoPosition;
	/**
	 * This property holds and empty employee set by Spring containing
	 * default values
	 */
	private Employee demoEmployee;

	/**
	 * 
	 * @return
	 */
	public Store getDemoStore() {
		Store store = (Store) getSession().get(STORE_SESSION_KEY);
		if(store == null) {
			store = new Store();
			getSession().put(STORE_SESSION_KEY, store);
		}
		return store;
	}
	
	/**
	 * 
	 * @return
	 */
	public Employee getEmployee() {
		Employee employee = (Employee) getSession().get(EMPLOYEE_SESSION_KEY);
		if(employee == null) {
			employee = getDemoEmployee();
			if(employee.isManager()) {
				employee.setProfile(getReferenceDataService().getManagerRole());
			} else {
				employee.setProfile(getReferenceDataService().getEmployeeRole());
			}
			
			getSession().put(EMPLOYEE_SESSION_KEY, employee);
		}
		return employee;
		
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isSessionValid() {
		return getSession().get(STORE_SESSION_KEY) != null;
	}
	
	/**
	 * 
	 * @return
	 */
	public String step1() {
		getSession().remove(STORE_SESSION_KEY);
		getSession().remove(EMPLOYEE_SESSION_KEY);
		
		if(getDemoStores() == null || getDemoStores().size() <= 0) {
			addActionError(new ErrorMessage("error.demo.wizard.noDemoStore"));
			return SpmActionResult.CANCEL.getResult();
		} else if(getDemoStores().size() == 1) {
			setSourceDemoStoreId(getDemoStores().get(0).getId());
			// Start with new store
			getSession().remove(STORE_SESSION_KEY);
			getSession().remove(EMPLOYEE_SESSION_KEY);
			copyStore();
			
			return SpmActionResult.STEP_2.getResult();
		} else {
			
			return SpmActionResult.STEP_1.getResult();
		}
	}

	/**
	 * 
	 * @return
	 */
	public String step2() {
		if(getSourceDemoStore() == null) {
			addActionError(new ErrorMessage("error.demo.wizard.noDemoStore"));
			return SpmActionResult.CANCEL.getResult();
		}
		if(!isBackButton()) {
			// Start with new store
			getSession().remove(STORE_SESSION_KEY);
			getSession().remove(EMPLOYEE_SESSION_KEY);
			copyStore();
		}
		return SpmActionResult.STEP_2.getResult();
	}
	
	/**
	 * 
	 * @return
	 */
	public String step3() {
		if(!isSessionValid()) {
			copyStore();
			return SpmActionResult.RESTART.getResult();
		}
		// If BACK from step 4
		setOperationTimes();
		return SpmActionResult.STEP_3.getResult();
	}	
	
	/**
	 * 
	 * @return
	 */
	public String step4() {
		if(!isSessionValid()) {
			copyStore();
			return SpmActionResult.RESTART.getResult();
		}
		// If BACK from step 5
		setVariableDefinitionNames();

		loadOperationTimes();
		return SpmActionResult.STEP_4.getResult();
	}	

	/**
	 * 
	 * @return
	 */
	public String step5() {
		if(!isSessionValid()) {
			copyStore();
			return SpmActionResult.RESTART.getResult();
		}
		// If BACK from step 6
		setPositions();
		
		loadVariableDefinitionNames();
		return SpmActionResult.STEP_5.getResult();
	}	

	/**
	 * 
	 * @return
	 */
	public String step6() {
		if(!isSessionValid()) {
			copyStore();
			return SpmActionResult.RESTART.getResult();
		}
		
		loadPositions();
		
		return SpmActionResult.STEP_6.getResult();
	}

	/**
	 * 
	 * @return
	 */
	public String step7() {
		if(!isSessionValid()) {
			copyStore();
			return SpmActionResult.RESTART.getResult();
		}
		
		setPositions();
		setStatesList(getReferenceDataService().getStates("us"));
		
		return SpmActionResult.STEP_7.getResult();
	}
	
	
	/**
	 * 
	 * @return
	 */
	public String step8() {
		if(!isSessionValid()) {
			copyStore();
			return SpmActionResult.RESTART.getResult();
		}
		
		try {
			//:TODO: Save store & save employee should be a transaction!
			Store store = getDemoStore(); //getStoreService().save(getDemoStore()); 
			getEmployee().setStore(store);
			getEmployeeService().save(getEmployee());
			
			setStoreName(store.getName());
			setEmployeeUserName(getEmployee().getUserName());

			getSession().remove(STORE_SESSION_KEY);
			getSession().remove(EMPLOYEE_SESSION_KEY);
			
			return SpmActionResult.STEP_8.getResult();
		} catch(SpmCheckedException ex) {
			addActionError(ex.getErrorMessage());
			setStatesList(getReferenceDataService().getStates("us"));
			return SpmActionResult.STEP_7.getResult();
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public String login() {
		return SpmActionResult.LOGIN.getResult();
	}
	
	/**
	 * 
	 * @return
	 */
	public String cancel() {
		getSession().remove(STORE_SESSION_KEY);
		getSession().remove(EMPLOYEE_SESSION_KEY);
		setSourceDemoStoreId(null);
		return SpmActionResult.CANCEL.getResult();
	}
	
	/**
	 * 
	 * @return
	 */
	public String variableDefinitionOneUp() {
		if(getVariableDefinitionNames() != null) {
			int idx = getIndex() != null ? getIndex().intValue() : -1;
			if(idx > 0 && idx < getVariableDefinitionNames().size()) {
				String aux = getVariableDefinitionNames().get(idx-1);
				getVariableDefinitionNames().set(idx-1, getVariableDefinitionNames().get(idx));
				getVariableDefinitionNames().set(idx, aux);
			}
		}
		return SpmActionResult.STEP_5.getResult();
	}
	
	/**
	 * 
	 * @return
	 */
	public String variableDefinitionOneDown() {
		if(getVariableDefinitionNames() != null) {
			int idx = getIndex() != null ? getIndex().intValue() : -1;
			if(idx >= 0 && idx < getVariableDefinitionNames().size() - 1) {
				String aux = getVariableDefinitionNames().get(idx+1);
				getVariableDefinitionNames().set(idx+1, getVariableDefinitionNames().get(idx));
				getVariableDefinitionNames().set(idx, aux);
			}
		}
		return SpmActionResult.STEP_5.getResult();
	}
	
	/**
	 * 
	 * @return
	 */
	public String addVariableDefinition() {
		if(!isMaximumVariableDefinitionsReached() && !StringUtils.isBlank(getNewVariableDefinitionName())) {
			getVariableDefinitionNames().add(getNewVariableDefinitionName());
		}
		setNewVariableDefinitionName(null);
		return SpmActionResult.STEP_5.getResult();
	}
	
	/**
	 * 
	 * @return
	 */
	public String removeVariableDefinition() {
		int idx = getIndex() != null ? getIndex().intValue() : -1;
		if(idx >= 0 && idx < getVariableDefinitionNames().size()) {
			getVariableDefinitionNames().remove(idx);
		}
		return SpmActionResult.STEP_5.getResult();
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Store> getDemoStores() {
		if(demoStores == null || demoStores.size() <= 0) {
			setDemoStores(getStoreService().findAllDemo());
		}
		return demoStores;
	}

	/**
	 * 
	 * @param demoStores
	 */
	public void setDemoStores(List<Store> demoStores) {
		this.demoStores = demoStores;
	}

	/**
	 * 
	 * @return
	 */
	public StoreService getStoreService() {
		return storeService;
	}

	/**
	 * 
	 * @param storeService
	 */
	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getSourceDemoStoreId() {
		return sourceDemoStoreId;
	}

	/**
	 * 
	 * @param sourceDemoStoreId
	 */
	public void setSourceDemoStoreId(Integer sourceDemoStoreId) {
		this.sourceDemoStoreId = sourceDemoStoreId;
	}

	/**
	 * 
	 * @return
	 */
	public Store getSourceDemoStore() {
		if(sourceDemoStore == null && getSourceDemoStoreId() != null) {
			Store temp = new Store();
			temp.setId(getSourceDemoStoreId());
			temp = getStoreService().getStoreById(temp);
			temp.getPositionGroups();
			temp.getPositions();
			temp.getDayParts();
			temp.getVariableDefinitions();
			temp.getOperationTimes();
			setSourceDemoStore(temp);
		}
		return sourceDemoStore;
	}

	/**
	 * 
	 * @param sourceDemoStore
	 */
	public void setSourceDemoStore(Store sourceDemoStore) {
		this.sourceDemoStore = sourceDemoStore;
	}
	
	/**
	 * Copies default values for source store to the new demo store
	 */
	private void copyStore() {
		Store demoStore = getDemoStore();
		Store sourceStore = getSourceDemoStore();
		
		demoStore.setArea(sourceStore.getArea());
		demoStore.setAllPositionsUtilization(sourceStore.getAllPositionsUtilization());
		demoStore.setAverageVariable(sourceStore.getAverageVariable());
		demoStore.setDailyProjectionsWeeksDefault(sourceStore.getDailyProjectionsWeeksDefault());
		demoStore.setDistributionType(sourceStore.getDistributionType());
		demoStore.setEarnedBreakFactor(sourceStore.getEarnedBreakFactor());
		demoStore.setExtraScheduleHours(sourceStore.getExtraScheduleHours());
		demoStore.setFillInefficiency(sourceStore.getFillInefficiency());
		demoStore.setFirstDayOfWeek(sourceStore.getFirstDayOfWeek());
		demoStore.setFloorManagementFactor(sourceStore.getFloorManagementFactor());
		demoStore.setHalfHourProjectionsWeeksDefault(sourceStore.getHalfHourProjectionsWeeksDefault());
		demoStore.setMinimumFloorManagementHours(sourceStore.getMinimumFloorManagementHours());
		demoStore.setScheduleInefficiency(sourceStore.getScheduleInefficiency());
		demoStore.setTrainingFactor(sourceStore.getTrainingFactor());
		
		// Position Groups
		for(PositionGroup posGrp : sourceStore.getPositionGroups()) {
			PositionGroup newPosGrp = new PositionGroup();
			newPosGrp.setName(posGrp.getName());
			demoStore.addPositionGroup(newPosGrp);
			newPosGrp = null;
		}

		// Day Parts
		for(DayPart dayPart : sourceStore.getDayParts()) {
			DayPart newDayPart = new DayPart();
			newDayPart.setName(dayPart.getName());
			newDayPart.setPositionIndex(dayPart.getPositionIndex());
			newDayPart.setStartHour(dayPart.getStartHour());
			demoStore.addDayPart(newDayPart);
			newDayPart = null;
		}
		
		// Operation Times
		for(OperationTime opTime : sourceStore.getOperationTimes()) {
			OperationTime newOpTime = new OperationTime();
			newOpTime.setCloseHour(opTime.getCloseHour());
			newOpTime.setDayOfWeek(opTime.getDayOfWeek());
			newOpTime.setOpenHour(opTime.getOpenHour());
			demoStore.addOperationTime(newOpTime);
			newOpTime = null;
		}
		
		// Positions
		PositionGroup demoPosGrp = demoStore.getFirstPositionGroup();
		for(Position pos : sourceStore.getPositions()) {
			Position newPos = new Position();
			newPos.setGuestService(pos.isGuestService());
			newPos.setManager(pos.isManager());
			newPos.setName(pos.getName());
			newPos.setPositionIndex(pos.getPositionIndex());
			newPos.setUtilizationBottom(pos.getUtilizationBottom());
			newPos.setUtilizationMaximum(pos.getUtilizationMaximum());
			newPos.setUtilizationMinimum(pos.getUtilizationMinimum());
			newPos.setUtilizationTop(pos.getUtilizationTop());
			newPos.setPositionGroup(demoPosGrp);
			newPos.setVariable2Flexible(pos.getVariable2Flexible());
			newPos.setVariable2Opening(pos.getVariable2Opening());
			newPos.setVariable3Flexible(pos.getVariable3Flexible());
			newPos.setVariable3Opening(pos.getVariable3Opening());
			newPos.setVariable4Flexible(pos.getVariable4Flexible());
			newPos.setVariable4Opening(pos.getVariable4Opening());
			
			for(DayOfWeekData dof : pos.getDayOfWeekData()) {
				DayOfWeekData newDof = new DayOfWeekData();
				newDof.setDayOfWeek(dof.getDayOfWeek());
				newDof.setFixedClosing(dof.getFixedClosing());
				newDof.setFixedFlexible(dof.getFixedFlexible());
				newDof.setFixedOpening(dof.getFixedOpening());
				newDof.setFixedPostRush(dof.getFixedPostRush());
				newDof.setPosition(newPos);
				newPos.getDayOfWeekData().add(newDof);
				newDof = null;
			}
			
			for(DayPartData dp : pos.getDayPartData()) {
				DayPartData newDp = new DayPartData();
				newDp.setDayPart(dp.getDayPart());
				newDp.setFixedGuestService(dp.getFixedGuestService());
				newDp.setMinimunStaffing(dp.getMinimunStaffing());
				newDp.setVariableFlexible(dp.getVariableFlexible());
				newDp.setVariableOpening(dp.getVariableOpening());
				newDp.setWeekdayGuestService(dp.getWeekdayGuestService());
				newDp.setWeekendGuestService(dp.getWeekendGuestService());
				newDp.setPosition(newPos);
				newPos.getDayPartData().add(newDp);
				newDp = null;
			}
			
			demoStore.addPosition(newPos);
			newPos = null;
		}
		
		// Variable Definitions
		// Main Variable
		demoStore.getMainVariableDefinition().setName(sourceStore.getMainVariableDefinition().getName());
		// Additional Variables (start at index 1)
		StoreVariableDefinition varDef;
		for(int i=1; i < sourceStore.getVariableDefinitions().size(); i++) {
			varDef = sourceStore.getVariableDefinitions().get(i);
			StoreVariableDefinition newVarDef = new StoreVariableDefinition();
			newVarDef.setName(varDef.getName());
			newVarDef.setVariableIndex(new Integer(i));
			demoStore.addVariableDefinition(newVarDef);
			newVarDef = null;
		}

	}
	
	/**
	 * 
	 * @param position
	 */
	private void buildDefaultPosition(Position position) {
		PositionGroup demoPosGrp = getDemoStore().getFirstPositionGroup();
		
		position.setGuestService(false);
		position.setManager(false);
		position.setUtilizationBottom(getDemoPosition().getUtilizationBottom());
		position.setUtilizationMaximum(getDemoPosition().getUtilizationMaximum());
		position.setUtilizationMinimum(getDemoPosition().getUtilizationMinimum());
		position.setUtilizationTop(getDemoPosition().getUtilizationTop());
		position.setPositionGroup(demoPosGrp);
		position.setVariable2Flexible(getDemoPosition().getVariable2Flexible());
		position.setVariable2Opening(getDemoPosition().getVariable2Opening());
		position.setVariable3Flexible(getDemoPosition().getVariable3Flexible());
		position.setVariable3Opening(getDemoPosition().getVariable3Opening());
		position.setVariable4Flexible(getDemoPosition().getVariable4Flexible());
		position.setVariable4Opening(getDemoPosition().getVariable4Opening());
		
		position.getDayOfWeekData().clear();
		for(DayOfWeek dof : DayOfWeek.values()) {
			DayOfWeekData newDof = new DayOfWeekData();
			newDof.setDayOfWeek(dof);
			newDof.setFixedClosing(new Double(0.0));
			newDof.setFixedFlexible(new Double(0.0));
			newDof.setFixedOpening(new Double(0.0));
			newDof.setFixedPostRush(new Double(0.0));
			newDof.setPosition(position);
			position.getDayOfWeekData().add(newDof);
			newDof = null;
		}
		
		for(DayPart dp : getDemoStore().getDayParts()) {
			DayPartData newDp = new DayPartData();
			newDp.setDayPart(dp);
			newDp.setFixedGuestService(new Double(0.0));
			newDp.setMinimunStaffing(new Integer(0));
			newDp.setVariableFlexible(new Double(0.0));
			newDp.setVariableOpening(new Double(0.0));
			newDp.setWeekdayGuestService(new Double(0.0));
			newDp.setWeekendGuestService(new Double(0.0));
			newDp.setPosition(position);
			position.getDayPartData().add(newDp);
			newDp = null;
		}		
	}
	
	/**
	 * 
	 */
	private void loadVariableDefinitionNames() {
		setVariableDefinitionNames(null);
		for(StoreVariableDefinition varDef : getDemoStore().getVariableDefinitions()) {
			getVariableDefinitionNames().add(varDef.getName());
		}
	}
	
	/**
	 * 
	 */
	private void setVariableDefinitionNames() {
		if(getVariableDefinitionNames() != null && getVariableDefinitionNames().size() > 0) {
			getDemoStore().getVariableDefinitions().clear();
			// Main Variable
			getDemoStore().getMainVariableDefinition().setName(getVariableDefinitionNames().get(0));

			String name;
			for(int i=1; i < getVariableDefinitionNames().size(); i++) {
				name = getVariableDefinitionNames().get(i);
				StoreVariableDefinition newVarDef = new StoreVariableDefinition();
				newVarDef.setName(name);
				newVarDef.setVariableIndex(new Integer(i));
				getDemoStore().addVariableDefinition(newVarDef);
				newVarDef = null;
			}
			if(!isMaximumVariableDefinitionsReached() && !StringUtils.isBlank(getNewVariableDefinitionName())) {
				StoreVariableDefinition newVarDef = new StoreVariableDefinition();
				newVarDef.setName(getNewVariableDefinitionName());
				newVarDef.setVariableIndex(new Integer(getVariableDefinitionNames().size()));
				getDemoStore().addVariableDefinition(newVarDef);
				newVarDef = null;				
			}
		}
	}
	
	/**
	 * 
	 */
	private void loadPositions() {
		setPositions(null);
		for(Position pos : getDemoStore().getPositions()) {
			Position editPos = new Position();
			editPos.setName(pos.getName());
			editPos.setGuestService(pos.isGuestService());
			editPos.setManager(pos.isManager());
			getPositions().add(editPos);
		}
	}
	
	/**
	 * 
	 * @param positions
	 * @param name
	 * @return
	 */
	private Position getPositionByName(List<Position> positions, String name) {
		if(positions != null && name != null) {
			for(Position pos : positions) {
				if(name.equals(pos.getName())) {
					return pos;
				}
			}
			return null;
		} else {
			return null;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public String addPosition() {
		if(getNewPosition() != null && !StringUtils.isBlank(getNewPosition().getName())) {
			Position newPos = new Position();
			buildDefaultPosition(newPos);
			newPos.setName(getNewPosition().getName());
			newPos.setPositionIndex(new Integer(getPositions().size()));
			newPos.setGuestService(getNewPosition().isGuestService());
			newPos.setManager(getNewPosition().isManager());
			getPositions().add(newPos);
			newPos = null;				
		}
		setNewPosition(null);
		return SpmActionResult.STEP_6.getResult();
	}
	
	/**
	 * 
	 * @return
	 */
	public String removePosition() {
		int idx = getIndex() != null ? getIndex().intValue() : -1;
		if(idx >= 0 && idx < getPositions().size()) {
			getPositions().remove(idx);
		}
		return SpmActionResult.STEP_6.getResult();
	}
	
	/**
	 * 
	 * @return
	 */
	public String positionOneUp() {
		if(getPositions() != null) {
			int idx = getIndex() != null ? getIndex().intValue() : -1;
			if(idx > 0 && idx < getPositions().size()) {
				Position aux = getPositions().get(idx-1);
				getPositions().set(idx-1, getPositions().get(idx));
				getPositions().set(idx, aux);
			}
		}
		return SpmActionResult.STEP_6.getResult();
	}
	
	/**
	 * 
	 * @return
	 */
	public String positionOneDown() {
		if(getPositions() != null) {
			int idx = getIndex() != null ? getIndex().intValue() : -1;
			if(idx >= 0 && idx < getPositions().size() - 1) {
				Position aux = getPositions().get(idx+1);
				getPositions().set(idx+1, getPositions().get(idx));
				getPositions().set(idx, aux);
			}
		}
		return SpmActionResult.STEP_6.getResult();
	}
	
	/**
	 * 
	 */
	private void setPositions() {
		if(getPositions() != null && getPositions().size() > 0) {
			List<Position> current = getDemoStore().getPositions();
			getDemoStore().setPositions(null);
			int i = 0;
			for(Position pos : getPositions()) {
				Position newPos = getPositionByName(current, pos.getName());
				if(newPos == null) {
					newPos = new Position();
					buildDefaultPosition(newPos);
					newPos.setName(pos.getName());
				}
				newPos.setPositionIndex(new Integer(i));
				newPos.setGuestService(pos.isGuestService());
				newPos.setManager(pos.isManager());
				getDemoStore().addPosition(newPos);
				newPos = null;
				i++;
			}
			if(getNewPosition() != null && !StringUtils.isBlank(getNewPosition().getName())) {
				Position newPos = new Position();
				buildDefaultPosition(newPos);
				newPos.setName(getNewPosition().getName());
				newPos.setPositionIndex(new Integer(i));
				newPos.setGuestService(getNewPosition().isGuestService());
				newPos.setManager(getNewPosition().isManager());
				getDemoStore().addPosition(newPos);
				newPos = null;				
			}
		}
	}
	
	/**
	 * Initializes the container object that will handle input of
	 * open and close operation times.
	 */
	private void loadOperationTimes() {
		if(getDemoStore() != null) {
			for(OperationTime time : getDemoStore().getOperationTimes()) {
				if(time != null && time.getDayOfWeekIndex() != null) {
					weekOperationTimeOpen[time.getDayOfWeekIndex().intValue()] = dateToDisplayTime(time.getOpenHour());
					weekOperationTimeClose[time.getDayOfWeekIndex().intValue()] = dateToDisplayTime(time.getCloseHour());
				}
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	private boolean updateOperationTimes() {
		if(weekOperationTimeOpen != null && weekOperationTimeClose != null) {
			for(int i=0; i < weekOperationTimeOpen.length; i++) {
				if(weekOperationTimeOpen[i] != null) {
					return true;
				}
			}

			for(int i=0; i < weekOperationTimeClose.length; i++) {
				if(weekOperationTimeClose[i] != null) {
					return true;
				}
			}

			return false;
		} else {
			return false;
		}
	}
	
	/**
	 * Puts all the corresponding values in the Store object
	 * so it can be updated.
	 */
	private void setOperationTimes() {
		if(getDemoStore().getOperationTimes() != null && updateOperationTimes()) {
			OperationTime anOperationTime;

			for(int i=0; i < getWeekOperationTimeOpen().length; i++) {
				if(getDemoStore().getOperationTimes().size() > i && getDemoStore().getOperationTimes().get(i) != null) {
					// Already exists
					anOperationTime = getDemoStore().getOperationTimes().get(i);
					anOperationTime.setOpenHour(displayTimeToDate(weekOperationTimeOpen[i]));
					anOperationTime.setCloseHour(displayTimeToDate(weekOperationTimeClose[i]));
				} else {
					// New Operation time for the current day of week
					anOperationTime = new OperationTime();
					anOperationTime.setStore(getDemoStore());
					anOperationTime.setDayOfWeek(DayOfWeek.values()[i]);
					anOperationTime.setOpenHour(displayTimeToDate(weekOperationTimeOpen[i]));
					anOperationTime.setCloseHour(displayTimeToDate(weekOperationTimeClose[i]));
					getDemoStore().getOperationTimes().add(anOperationTime);
				}
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	public String[] getWeekOperationTimeOpen() {
		return weekOperationTimeOpen;
	}

	/**
	 * 
	 * @param weekOperationTimeOpen
	 */
	public void setWeekOperationTimeOpen(String[] weekOperationTimeOpen) {
		this.weekOperationTimeOpen = weekOperationTimeOpen;
	}

	/**
	 * 
	 * @return
	 */
	public String[] getWeekOperationTimeClose() {
		return weekOperationTimeClose;
	}

	/**
	 * 
	 * @param weekOperationTimeClose
	 */
	public void setWeekOperationTimeClose(String[] weekOperationTimeClose) {
		this.weekOperationTimeClose = weekOperationTimeClose;
	}

	/**
	 * 
	 * @return
	 */
	public List<String> getVariableDefinitionNames() {
		if(variableDefinitionNames == null) {
			setVariableDefinitionNames(new ArrayList<String>());
		}
		return variableDefinitionNames;
	}

	/**
	 * 
	 * @param variableDefinitionNames
	 */
	public void setVariableDefinitionNames(List<String> variableDefinitionNames) {
		this.variableDefinitionNames = variableDefinitionNames;
	}

	/**
	 * 
	 * @return
	 */
	public String getNewVariableDefinitionName() {
		return newVariableDefinitionName;
	}

	/**
	 * 
	 * @param newVariableDefinitionName
	 */
	public void setNewVariableDefinitionName(String newVariableDefinitionName) {
		this.newVariableDefinitionName = newVariableDefinitionName;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isMaximumVariableDefinitionsReached() {
		return getVariableDefinitionNames() != null && getVariableDefinitionNames().size() >= StoreVariableDefinition.MAX_VARIABLE_DEFINITIONS_QUANTITY;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getIndex() {
		return index;
	}

	/**
	 * 
	 * @param index
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}

	/**
	 * 
	 * @return
	 */
	public List<Position> getPositions() {
		if(positions == null) {
			setPositions(new ArrayList<Position>());
		}
		return positions;
	}

	/**
	 * 
	 * @param positions
	 */
	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	/**
	 * 
	 * @return
	 */
	public Position getNewPosition() {
		return newPosition;
	}

	/**
	 * 
	 * @param newPosition
	 */
	public void setNewPosition(Position newPosition) {
		this.newPosition = newPosition;
	}

	/**
	 * 
	 * @return
	 */
	public Position getDemoPosition() {
		return demoPosition;
	}

	/**
	 * 
	 * @param demoPosition
	 */
	public void setDemoPosition(Position demoPosition) {
		this.demoPosition = demoPosition;
	}

	/**
	 * 
	 * @return
	 */
	public String getActionButton() {
		return actionButton;
	}

	/**
	 * 
	 * @param actionButton
	 */
	public void setActionButton(String actionButton) {
		this.actionButton = actionButton;
	}	
	
	/**
	 * 
	 * @return
	 */
	public boolean isBackButton() {
		return "back".equalsIgnoreCase(getActionButton());
	}

	/**
	 * 
	 * @return
	 */
	public Employee getDemoEmployee() {
		return demoEmployee;
	}

	/**
	 * 
	 * @param demoEmployee
	 */
	public void setDemoEmployee(Employee demoEmployee) {
		this.demoEmployee = demoEmployee;
	}

	/**
	 * 
	 * @return
	 */
	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	/**
	 * 
	 * @param employeeService
	 */
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	/**
	 * 
	 * @return
	 */
	public String getStoreName() {
		return storeName;
	}

	/**
	 * 
	 * @param storeName
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	/**
	 * 
	 * @return
	 */
	public String getEmployeeUserName() {
		return employeeUserName;
	}

	/**
	 * 
	 * @param employeeUserName
	 */
	public void setEmployeeUserName(String employeeUserName) {
		this.employeeUserName = employeeUserName;
	}

	/**
	 * 
	 * @return
	 */
	public ReferenceDataService getReferenceDataService() {
		return referenceDataService;
	}

	/**
	 * 
	 * @param referenceDataService
	 */
	public void setReferenceDataService(ReferenceDataService referenceDataService) {
		this.referenceDataService = referenceDataService;
	}

	/**
	 * 
	 * @return
	 */
	public List<String> getStatesList() {
		return statesList;
	}

	/**
	 * 
	 * @param statesList
	 */
	public void setStatesList(List<String> statesList) {
		this.statesList = statesList;
	}
}
