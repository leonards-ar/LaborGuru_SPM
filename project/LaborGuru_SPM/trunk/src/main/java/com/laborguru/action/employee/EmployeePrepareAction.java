package com.laborguru.action.employee;

import java.util.List;

import com.laborguru.action.SpmAction;
import com.laborguru.action.SpmActionResult;
import com.laborguru.action.utils.ConstantListFactory;
import com.laborguru.action.utils.KeyValuePair;
import com.laborguru.model.Employee;
import com.laborguru.model.Position;
import com.laborguru.model.helper.EmployeeTestHelper;
import com.laborguru.model.helper.PositionTestHelper;
import com.laborguru.service.employee.EmployeeService;

/**
 * This action deals with Employee CRUD.
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
@SuppressWarnings("serial")
public class EmployeePrepareAction extends SpmAction {

	private EmployeeService employeeService;
	
	private Employee employee;
	private Employee searchEmployee;
	
	private List<Employee> storeEmployees;
	private List<Position> positions;
	private List<KeyValuePair> statusList;
	
	private Integer employeeId;
	private boolean removePage;
	
	/**
	 * Performs an Employee Search
	 * @return
	 * @throws Exception
	 */
	public String search() throws Exception {
		
		//Getting store & getting the employee list
		//storeEmployees = employeeService.getEmployeesByStore(aStore);
		this.setStoreEmployees(EmployeeTestHelper.getEmployees("employee", 4));
				
		return SpmActionResult.LIST.getResult();
	}
	
	/**
	 * Retrieves all the employees that belong to the user's store.
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		
		//Getting store & getting the employee list
		//storeEmployees = employeeService.getEmployeesByStore(aStore);
		this.setStoreEmployees(EmployeeTestHelper.getEmployees("employee", 4));
		
		return SpmActionResult.LIST.getResult();
	}	

	
	/**
	 * Prepares the add page
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		
		//Getting store & getting the employee list
		//storeEmployees = employeeService.getEmployeesByStore(aStore);
		this.setPositions(PositionTestHelper.getPositions("position", 4));
		this.setStatusList(ConstantListFactory.createStatusList());
		
		return SpmActionResult.EDIT.getResult();
	}

	
	/**
	 * Prepares the edit page
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		
		//Getting store & getting the employee list
		//storeEmployees = employeeService.getEmployeesByStore(aStore);
		this.setEmployee(EmployeeTestHelper.getEmployee("spm", this.employeeId));		
		this.setPositions(PositionTestHelper.getPositions("position", 4));
		this.setStatusList(ConstantListFactory.createStatusList());
		
		return SpmActionResult.EDIT.getResult();
	}

	/**
	 * Prepare removes page
	 * @return
	 * @throws Exception
	 */
	public String remove() throws Exception {
		//Getting store & getting the employee list
		//storeEmployees = employeeService.getEmployeesByStore(aStore);
		this.setRemovePage(true);
		this.setEmployee(EmployeeTestHelper.getEmployee("spm", this.employeeId));
		
		return SpmActionResult.SHOW.getResult();
	}	
	
	/**
	 * Prepares the view page
	 * @return
	 * @throws Exception
	 */
	public String show() throws Exception {		
		//Getting store & getting the employee list
		//storeEmployees = employeeService.getEmployeesByStore(aStore);
		this.setEmployee(EmployeeTestHelper.getEmployee("spm", this.employeeId));

		return SpmActionResult.SHOW.getResult();
	}
		
	/**
	 * Stores an employee on the DB
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {		
		//Getting store & getting the employee list
		//storeEmployees = employeeService.getEmployeesByStore(aStore);
		System.out.println("ADD: "+this.employee.toString());
		
		return SpmActionResult.LISTACTION.getResult();
	}

	
	/**
	 * Stores an employee on the DB
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {		
		//Getting store & getting the employee list
		//storeEmployees = employeeService.getEmployeesByStore(aStore);
		System.out.println("DELETE:"+this.employee.toString());
		
		return SpmActionResult.LISTACTION.getResult();
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

	public List<KeyValuePair> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<KeyValuePair> statusList) {
		this.statusList = statusList;
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

	public Employee getSearchEmployee() {
		return searchEmployee;
	}

	public void setSearchEmployee(Employee searchEmployee) {
		this.searchEmployee = searchEmployee;
	}
}
