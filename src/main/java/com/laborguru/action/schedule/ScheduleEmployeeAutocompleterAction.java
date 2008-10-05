/*
 * File name: ScheduleEmployeeAutocompleterAction.java
 * Creation date: Oct 4, 2008 8:51:07 PM
 * Copyright Mindpool
 */
package com.laborguru.action.schedule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.laborguru.action.SpmAction;
import com.laborguru.model.Employee;
import com.laborguru.service.employee.EmployeeService;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class ScheduleEmployeeAutocompleterAction extends SpmAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 577253668801585437L;

	private Map<Integer, String> storeEmployees;
	
	private EmployeeService employeeService;
	
	/**
	 * 
	 */
	public ScheduleEmployeeAutocompleterAction() {
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
    private void loadStoreEmployees() {
    	storeEmployees = new HashMap<Integer, String>();
    	List<Employee> employees = getEmployeeService().getEmployeesByStore(getEmployeeStore());
    	
    	for(Employee anEmployoee : employees) {
    		storeEmployees.put(anEmployoee.getId(), anEmployoee.getFullName());
    	}
    	
    }

    /**
     * 
     * @return
     * @throws Exception
     * @see com.opensymphony.xwork2.ActionSupport#execute()
     */
    public String execute() throws Exception {
    	loadStoreEmployees();
    	
        return SUCCESS;
    }
    
	/**
	 * @return the storeEmployees
	 */
	public Map<Integer, String> getStoreEmployees() {
		return storeEmployees;
	}
	
	/**
	 * @param storeEmployees the storeEmployees to set
	 */
	public void setStoreEmployees(Map<Integer, String> storeEmployees) {
		this.storeEmployees = storeEmployees;
	}

	/**
	 * @return the employeeService
	 */
	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	/**
	 * @param employeeService the employeeService to set
	 */
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
}
