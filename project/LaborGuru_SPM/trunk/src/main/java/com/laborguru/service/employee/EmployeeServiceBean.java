package com.laborguru.service.employee;

import java.util.Date;
import java.util.List;

import com.laborguru.exception.ErrorEnum;
import com.laborguru.exception.SpmCheckedException;
import com.laborguru.logger.DefaultSpmLogger;
import com.laborguru.model.Customer;
import com.laborguru.model.Employee;
import com.laborguru.model.Store;
import com.laborguru.model.User;
import com.laborguru.model.filter.SearchEmployeeFilter;
import com.laborguru.service.employee.dao.EmployeeDao;
import com.laborguru.service.user.dao.UserDao;

public class EmployeeServiceBean implements EmployeeService {

	private static final DefaultSpmLogger spmLog = DefaultSpmLogger.getInstance();
	
	EmployeeDao employeeDao;
	UserDao userDao;
	
	/* (non-Javadoc)
	 * @see com.laborguru.service.employee.EmployeeService#delete(com.laborguru.model.Employee)
	 */
	public void delete(Employee employee) {
		if(employee == null) {
			spmLog.errorLog("error.null.param", new Object[]{"employee"});
			throw new IllegalArgumentException("param is null");
		}
		employeeDao.delete(employee);
		
	}

	/* (non-Javadoc)
	 * @see com.laborguru.service.employee.EmployeeService#getEmployeeById(com.laborguru.model.Employee)
	 */
	public Employee getEmployeeById(Employee employee) {
		if(employee == null) {
			spmLog.errorLog("error.null.param", new Object[]{"employee"});
			throw new IllegalArgumentException("param is null");
		}
		return employeeDao.getEmployeeById(employee);
	}

	/* (non-Javadoc)
	 * @see com.laborguru.service.employee.EmployeeService#getEmployeesByStore(com.laborguru.model.Store)
	 */
	public List<Employee> getEmployeesByStore(Store store) {

		if(store == null) {
			spmLog.errorLog("error.null.param", new Object[]{"store"});
			throw new IllegalArgumentException("param is null");
		}
		List<Employee> employees = employeeDao.getEmployeesByStore(store);

		return employees;
	}

	/* (non-Javadoc)
	 * @see com.laborguru.service.employee.EmployeeService#save(com.laborguru.model.Employee)
	 */
	public Employee save(Employee employee) throws SpmCheckedException {

		Employee retEmployee = null;
		
		if(employee == null) 
		{
			spmLog.errorLog("Employee passed in as parameter is null");
			throw new IllegalArgumentException("Employee passed in as parameter is null");				
		}
		
		spmLog.debugLog("before save employee:"+ employee);

		retEmployee = employee.getId()!= null? update(employee):create(employee);

		spmLog.debugLog("after save employee:"+ retEmployee);
		
		return retEmployee;
	}
	
	/**
	 * Creates employee
	 */
	private Employee create(Employee employee) throws SpmCheckedException {		
							
		//Checking if user name already exist
		if (userDao.existUser(employee.getUserName()))
		{
			String exMgs = "username: "+ employee.getUserName()+" already exist in the database";
			spmLog.errorLog(exMgs);
			throw new SpmCheckedException(exMgs, ErrorEnum.USERNAME_ALREADY_EXIST_ERROR, new String[]{employee.getUserName()});
		}
		employee.setCreationDate(new Date());
		return  employeeDao.save(employee);
	}

	/**
	 * Updates employee
	 */
	private Employee update(Employee employee) throws SpmCheckedException {
							
		//Checking if user name already exist
		User auxUser = userDao.getUserByUsername(employee);
		
		if ((auxUser != null) && !auxUser.getId().equals(employee.getId()))
		{
			String exMgs = "username: "+ employee.getUserName()+" already exist in the database";
			spmLog.errorLog(exMgs);
			throw new SpmCheckedException(exMgs, ErrorEnum.USERNAME_ALREADY_EXIST_ERROR, new String[]{employee.getUserName()});
			
		}
		
		//Evicting the user
		userDao.evict(auxUser);
		
		employee.setLastUpdateDate(new Date());
		return employeeDao.save(employee);
	}

	/**
	 * @return the employeeDao
	 */
	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}

	/**
	 * @param employeeDao the employeeDao to set
	 */
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	/**
	 * @return the userDao
	 */
	public UserDao getUserDao() {
		return userDao;
	}

	/**
	 * @param userDao the userDao to set
	 */
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * Retrieves a list of employees filtered by the SearchEmployeeFilter
	 * @param searchEmployee The filter for the employee search
	 * @return The employee list
	 * @see com.laborguru.service.employee.EmployeeService#filterEmployee(com.laborguru.model.filter.SearchEmployeeFilter)
	 */
	public List<Employee> filterEmployee(SearchEmployeeFilter searchEmployee) {
		
		if(searchEmployee == null) {
			spmLog.errorLog("The filter passed as parameter is null");
			throw new IllegalArgumentException("The filter passed as parameter is null");
		}
		
		
		List<Employee> employees = employeeDao.applyFilter(searchEmployee);

		return employees;	
	}
	
	public List<Employee> getEmployeeByCustomer(Customer customer){
		return null;
	}
}
