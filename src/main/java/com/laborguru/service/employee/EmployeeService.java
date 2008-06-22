package com.laborguru.service.employee;

import java.util.List;

import com.laborguru.exception.SpmCheckedException;
import com.laborguru.model.Employee;
import com.laborguru.model.Store;
import com.laborguru.service.Service;

/**
 * Employee Service Interface. Handles employee services for SPM employees.  
 * 
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public interface EmployeeService extends Service {
	
	/**
	 * Saves or updates employee
	 * @param employee Employee to save or update
	 * @return
	 * @throws SpmCheckedException 
	 */
	Employee save(Employee employee) throws SpmCheckedException;
	
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
