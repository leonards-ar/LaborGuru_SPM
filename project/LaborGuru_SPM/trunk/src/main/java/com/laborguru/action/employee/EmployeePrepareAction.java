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
	private List<Employee> storeEmployees;
	private Employee employee;
	private List<Position> positions;
	private List<KeyValuePair> statusList;
	
	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}


	public String search() throws Exception {
		
		//Getting store & getting the employee list
		//storeEmployees = employeeService.getEmployeesByStore(aStore);
		this.setStoreEmployees(EmployeeTestHelper.getEmployees("employee", 4));
				
		return SpmActionResult.LIST.getResult();
	}
	
	public String list() throws Exception {
		
		//Getting store & getting the employee list
		//storeEmployees = employeeService.getEmployeesByStore(aStore);
		this.setStoreEmployees(EmployeeTestHelper.getEmployees("employee", 4));
		
		return SpmActionResult.LIST.getResult();
	}	

	public String add() throws Exception {
		
		//Getting store & getting the employee list
		//storeEmployees = employeeService.getEmployeesByStore(aStore);
		this.setPositions(PositionTestHelper.getPositions("position", 4));
		this.setStatusList(ConstantListFactory.createStatusList());
		
		return SpmActionResult.EDIT.getResult();
	}

	
	public String edit() throws Exception {
		
		//Getting store & getting the employee list
		//storeEmployees = employeeService.getEmployeesByStore(aStore);
		this.setEmployee(EmployeeTestHelper.getEmployee("spm"));		
		this.setPositions(PositionTestHelper.getPositions("position", 4));
		this.setStatusList(ConstantListFactory.createStatusList());
		
		return SpmActionResult.EDIT.getResult();
	}

	public String save() throws Exception {
		
		//Getting store & getting the employee list
		//storeEmployees = employeeService.getEmployeesByStore(aStore);
		System.out.println(this.employee.toString());
		
		return SpmActionResult.LIST.getResult();
	}
	
	public String show() throws Exception {
		
		//Getting store & getting the employee list
		//storeEmployees = employeeService.getEmployeesByStore(aStore);
		this.setEmployee(EmployeeTestHelper.getEmployee("spm"));

		return SpmActionResult.SHOW.getResult();
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
}
