package com.laborguru.service.employee;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.laborguru.exception.ErrorEnum;
import com.laborguru.exception.SpmUncheckedException;
import com.laborguru.logger.DefaultSpmLogger;
import com.laborguru.model.Employee;
import com.laborguru.model.Store;
import com.laborguru.service.employee.dao.EmployeeDao;

public class EmployeeServiceBean implements EmployeeService {

	private static final DefaultSpmLogger spmLog = DefaultSpmLogger.getInstance();
	
	EmployeeDao employeeDao;
	
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
	public Employee save(Employee employee) {
		try{
			if(employee == null) {
				spmLog.errorLog("error.null.param", new Object[]{"employee"});
				throw new IllegalArgumentException("param is null");				
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

}
