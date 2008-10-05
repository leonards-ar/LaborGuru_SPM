package com.laborguru.action.employee;

import java.util.List;
import java.util.Map;

import com.laborguru.action.SpmAction;
import com.laborguru.action.SpmActionResult;
import com.laborguru.exception.SpmCheckedException;
import com.laborguru.model.Employee;
import com.laborguru.model.Position;
import com.laborguru.model.Profile;
import com.laborguru.model.filter.SearchEmployeeFilter;
import com.laborguru.service.data.ReferenceDataService;
import com.laborguru.service.employee.EmployeeService;
import com.laborguru.service.position.PositionService;
import com.opensymphony.xwork2.Preparable;

/**
 * This action deals with Employee CRUD.
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
@SuppressWarnings("serial")
public class EmployeePrepareAction extends SpmAction implements Preparable {

	private EmployeeService employeeService;
	private ReferenceDataService referenceDataService;
	
	private PositionService positionService;
	
	private Employee employee;
	private SearchEmployeeFilter searchEmployee;
	
	private List<Employee> storeEmployees;
	private List<Position> positions;
	private Map<String, String> statusMap;
	private List<String> statesList;
	
	private String passwordConfirmation;	
	private Integer employeeId;
	private boolean removePage;

	
	/**
	 * Prepare the data to be used on the edit page
	 * Loads position and status list
	 * @throws Exception
	 */
	public void prepareEdit(){
		loadListsForAddEditPage();
	}

	/**
	 * Prepare data to be used to display employee data
	 */
	public void prepareShow() {
		loadListsForAddEditPage();
	}
	
	/**
	 * Prepare data to be used to display employee data
	 * before removal.
	 */
	public void prepareRemove() {
		loadListsForAddEditPage();
	}
	
	/**
	 * Prepare the data to be used on the add page
	 * Loads position and status list
	 * @throws Exception
	 */
	public void prepareAdd(){
		loadListsForAddEditPage();
	}
	
	/**
	 * Prepare the data to be used on the add page
	 * We should preload the list needed to render the add page.
	 * When a validation fails the application goes back to the add page and this data is needed.
	 * Loads position and status list
	 * @throws Exception
	 */
	public void prepareSave(){
		loadListsForAddEditPage();
	}

	/**
	 * Loads position and status list
	 */
	private void loadListsForAddEditPage() {
		this.setPositions(positionService.getPositionsByStore(getEmployeeStore()));
		this.setStatusMap(getReferenceDataService().getStatus());
		//:TODO: Add country support
		this.setStatesList(getReferenceDataService().getStates("us"));
	}
	
	/**
	 * Prepare data to be used in the actions methods defined for this action
	 * @throws Exception
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	public void prepare() throws Exception {
		//It's needed by the Preparable interface, don't comment out or removed
	}
	
	/**
	 * TODO Performs an Employee Search
	 * @return
	 * @throws Exception
	 */
	public String search() throws Exception {
		
		//Setting the store on the filter
		this.searchEmployee.setStore(getEmployeeStore());
		
		
		this.setStoreEmployees(this.employeeService.filterEmployee(this.searchEmployee));
		
		return SpmActionResult.LIST.getResult();
	}
	
	/**
	 * Retrieves all the employees that belong to the user's store.
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		
		this.setStoreEmployees(this.employeeService.getEmployeesByStore(getEmployeeStore()));
				
		return SpmActionResult.LIST.getResult();
	}	

	
	/**
	 * Prepares the add page
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return SpmActionResult.EDIT.getResult();
	}

	
	/**
	 * Prepares the edit page
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		
		loadEmployeeFromId();
			
		return SpmActionResult.EDIT.getResult();
	}

	/**
	 * Prepare removes page
	 * @return
	 * @throws Exception
	 */
	public String remove() throws Exception {

		//Getting employee
		loadEmployeeFromId();
	
		this.setRemovePage(true);
		
		return SpmActionResult.SHOW.getResult();
	}
	
	/**
	 * Prepares the view page
	 * @return
	 * @throws Exception
	 */
	public String show() throws Exception {	
		
		loadEmployeeFromId();
	
		return SpmActionResult.SHOW.getResult();
	}
		
	/**
	 * Stores an employee on the DB
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {		
		
		if (this.employee.getId() == null){
			this.employee.setStore(getEmployeeStore());
		}
		
		try {

			if(getEmployee().isManager()) {
				//Set Employee Manager Role
				getEmployee().setProfile(getReferenceDataService().getManagerRole());
				
			} else {
				getEmployee().setProfile(getReferenceDataService().getEmployeeRole());
			}
			
			employeeService.save(this.employee);

			return SpmActionResult.LISTACTION.getResult();

		} catch (SpmCheckedException e) {
			addActionError(e.getErrorMessage());
		}
		
		return SpmActionResult.INPUT.getResult();
	}
		

	/**
	 * Stores an employee on the DB
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {		
		
		//Getting employee
		Employee auxEmployee = employeeService.getEmployeeById(this.employee);		
		employeeService.delete(auxEmployee);
		
		return SpmActionResult.LISTACTION.getResult();
	}
	

	/**
	 *  Load full employee from the property employeeId
	 */
	private void loadEmployeeFromId() {
		Employee tmpEmployee = new Employee();
		tmpEmployee.setId(this.employeeId);
		this.setEmployee(employeeService.getEmployeeById(tmpEmployee));
		setPasswordConfirmation(getEmployee().getPassword());
	}	
	
	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public List<Employee> getStoreEmployees() {
		return storeEmployees;
	}

	public void setStoreEmployees(List<Employee> storeEmployees) {
		this.storeEmployees = storeEmployees;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public boolean isRemovePage() {
		return removePage;
	}

	public void setRemovePage(boolean removePage) {
		this.removePage = removePage;
	}

	public SearchEmployeeFilter getSearchEmployee() {
		return searchEmployee;
	}

	public void setSearchEmployee(SearchEmployeeFilter searchEmployee) {
		this.searchEmployee = searchEmployee;
	}

	public PositionService getPositionService() {
		return positionService;
	}

	public void setPositionService(PositionService positionService) {
		this.positionService = positionService;
	}

	/**
	 * @return the statusMap
	 */
	public Map<String, String> getStatusMap() {
		return statusMap;
	}

	/**
	 * @param statusMap the statusMap to set
	 */
	public void setStatusMap(Map<String, String> statusMap) {
		this.statusMap = statusMap;
	}

	/**
	 * @return the referenceDataService
	 */
	public ReferenceDataService getReferenceDataService() {
		return referenceDataService;
	}

	/**
	 * @param referenceDataService the referenceDataService to set
	 */
	public void setReferenceDataService(ReferenceDataService referenceDataService) {
		this.referenceDataService = referenceDataService;
	}

	/**
	 * @return the storeEmployeesSize
	 */
	public int getStoreEmployeesSize() {
		return getStoreEmployees()!=null? getStoreEmployees().size():0;
	}

	/**
	 * @return the statesList
	 */
	public List<String> getStatesList() {
		return statesList;
	}

	/**
	 * @param statesList the statesList to set
	 */
	public void setStatesList(List<String> statesList) {
		this.statesList = statesList;
	}

	/**
	 * @return the passwordConfirmation
	 */
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	/**
	 * @param passwordConfirmation the passwordConfirmation to set
	 */
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
	
}
