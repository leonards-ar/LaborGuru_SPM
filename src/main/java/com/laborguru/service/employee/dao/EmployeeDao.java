package com.laborguru.service.employee.dao;

import java.util.List;

import com.laborguru.model.Employee;
import com.laborguru.model.Store;

public interface EmployeeDao {

	/**
	 * Saves or updates employee
	 * @param employee Employee to save or update
	 * @return
	 */
	Employee save(Employee employee);
	
	/**
	 * Deletes employee
	 * @param employee Employee to Delete
	 */
	void delete(Employee employee);
	
	/**
	 * Retrieves an employee by Id
	 * @param employee Employee with id populated.
	 * @return Employee the employee
	 */
	Employee getEmployeeById(Employee employee);
	
	/**
	 * Retrieves an employee by Store
	 * @param store The store that is going to be used to retrieves the employees
	 * @return List<Employee> the list of employee
	 */
	List<Employee> getEmployeesByStore(Store store);
	
}
