package com.laborguru.service.employee.dao;

import java.util.List;

import com.laborguru.logger.DefaultSpmLogger;
import com.laborguru.model.Employee;
import com.laborguru.model.Store;
import com.laborguru.model.filter.SearchEmployeeFilter;
import com.laborguru.service.dao.hibernate.SpmHibernateDao;

public class EmployeeDaoHibernate extends SpmHibernateDao implements
		EmployeeDao {

	private static final DefaultSpmLogger spmLog = DefaultSpmLogger.getInstance();

	/**
	 * 
	 */
	public void delete(Employee employee) {
		getHibernateTemplate().delete(employee);
	}

	/**
	 * 
	 */
	public Employee getEmployeeById(Employee employee) {
		
		return (Employee)getHibernateTemplate().get(Employee.class, employee.getId());		
	}
	
	/**
	 * 
	 */
	public List<Employee> getEmployeesByStore(Store store) {
		return (List<Employee>)getHibernateTemplate().findByNamedParam("from Employee employee where employee.store.id = :searchString", "searchString", store.getId());
	}

	/**
	 * 
	 */
	public Employee save(Employee employee) {
		getHibernateTemplate().saveOrUpdate(employee);
		return employee;
	}

	/**
	 * Retrieves a list of employees based on the filter passed in as parameter.
	 * If there is no employees that match the criteria return an empty list.
	 * 
	 * TODO: CNR - 25/06/2008 At the moment doesn't work if there is no store. So we should refactor this method and move it up to a BaseDao class. In order to run the filter on a BaseDao.
	 * I'll work on this on the following week.
	 *       MC - 05/07/2008 Created supper class for every Dao
	 * 
	 * @param searchEmployee The filter
	 * @return The employee list
	 * @see com.laborguru.service.employee.dao.EmployeeDao#applyFilter(com.laborguru.model.filter.SearchEmployeeFilter)
	 */
	public List<Employee> applyFilter(SearchEmployeeFilter searchEmployee) {
		
		//TODO: Remove this when fix the general case
		if (searchEmployee.getStore() == null){
			throw new IllegalArgumentException("The store in the filter is empty.");
		}
		
		spmLog.debugLog("In applyFilter with search employee filter:"+ searchEmployee);
		
		StringBuilder sb = new StringBuilder("from Employee employee where");

		if (includeInFilter(searchEmployee.getStore())){
			sb.append(" employee.store.id = " + searchEmployee.getStore().getId());
		}
		
		if (includeInFilter(searchEmployee.getEmployeeId())){
			sb.append(" and employee.employeeId = "+ searchEmployee.getEmployeeId());
		}
		
		if (includeInFilter(searchEmployee.getFullName())){
			sb.append(" and employee.name like '%"+searchEmployee.getFullName()+"%'");
			sb.append(" or employee.surname like '%"+searchEmployee.getFullName()+"%'");
		}
		
		spmLog.debugLog("Calling hibernate with query:"+ sb.toString());

		
		return (List<Employee>)getHibernateTemplate().find(sb.toString());
	}

}
