package com.laborguru.service.employee.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.laborguru.model.Employee;
import com.laborguru.model.Store;

public class EmployeeDaoHibernate extends HibernateDaoSupport implements
		EmployeeDao {

	public void delete(Employee employee) {
		getHibernateTemplate().delete(employee);
	}

	public Employee getEmployeeById(Employee employee) {
		
		List<Employee> employees = (List<Employee>)getHibernateTemplate().findByNamedParam("from Employee employee where employee.id = :searchString", "searchString", employee.getId());
		
		Employee retEmployee = null;
		
		if(employees.size() > 0) {
			retEmployee = employees.get(0);
		}
		
		return retEmployee;
	}

	public List<Employee> getEmployeesByStore(Store store) {
		return (List<Employee>)getHibernateTemplate().findByNamedParam("from Employee employee where employee.store.id = :searchString", "searchString", store.getId());
		

	}

	public Employee save(Employee employee) {
		getHibernateTemplate().saveOrUpdate(employee);
		return employee;
		
	}

}
