package com.laborguru.action.employee;

import java.util.List;

import com.laborguru.action.SpmAction;
import com.laborguru.action.SpmActionResult;
import com.laborguru.model.Employee;
import com.laborguru.model.helper.EmployeeTestHelper;
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
	
	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public String home() throws Exception {
		
		//Getting store & getting the employee list
		//storeEmployees = employeeService.getEmployeesByStore(aStore);
		
		
		return SpmActionResult.HOME.getResult();
	}

	public String list() throws Exception {
		
		//Getting store & getting the employee list
		//storeEmployees = employeeService.getEmployeesByStore(aStore);
		
		
		return SpmActionResult.LIST.getResult();
	}	

	public String edit() throws Exception {
		
		//Getting store & getting the employee list
		//storeEmployees = employeeService.getEmployeesByStore(aStore);
		
		
		return SpmActionResult.EDIT.getResult();
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
}
