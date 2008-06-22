package com.laborguru.service.employee;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.laborguru.exception.ErrorEnum;
import com.laborguru.exception.SpmCheckedException;
import com.laborguru.exception.SpmUncheckedException;
import com.laborguru.logger.DefaultSpmLogger;
import com.laborguru.model.Employee;
import com.laborguru.model.Store;
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
		spmLog.debugLog("Entering to save with employee:"+ employee);
		
		if(employee == null) 
		{
			spmLog.errorLog("error.null.param", new Object[]{"employee"});
			throw new IllegalArgumentException("Employee passed in as parameter is null");				
		}
		
		if(employee.getUserName() == null) 
		{
			spmLog.errorLog("Employee's username passed in as parameter is null");
			throw new IllegalArgumentException("Employee's username passed in as parameter is null");				
		}

		try{			
			if (userDao.existUser(employee.getUserName()))
			{
				String exMgs = "username: "+ employee.getUserName()+" already exist in the database";
				spmLog.errorLog(exMgs);
				throw new SpmCheckedException(exMgs, ErrorEnum.USERNAME_ALREADY_EXIST_ERROR, new String[]{employee.getUserName()});
			}
			
			return employeeDao.save(employee);
			
		} catch(DataAccessException e) {
			spmLog.errorLog("Error saving employee...", e);
			throw new SpmUncheckedException(e, e.getMessage(), ErrorEnum.GENERIC_DATABASE_ERROR);
		}
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

}
