package com.laborguru.service.manager.dao;

import java.util.List;

import com.laborguru.model.Area;
import com.laborguru.model.AreaUser;
import com.laborguru.model.Customer;
import com.laborguru.model.CustomerUser;
import com.laborguru.model.Manager;
import com.laborguru.model.Region;
import com.laborguru.model.RegionalUser;
import com.laborguru.service.dao.hibernate.SpmHibernateDao;

public class ManagerDaoHibernate extends SpmHibernateDao implements ManagerDao {

	public ManagerDaoHibernate() {
	}

	public boolean existUser(String userName) {
		List<String> result = (List<String>) getHibernateTemplate().findByNamedParam(
				"select user.userName from user where username= :searchString", "searchString", userName);
		return result.size() != 0;
	}

	public List<Manager> getAreaUsers(Area area) {
		return (List<Manager>) getHibernateTemplate().findByNamedParam(
				"from AreaUser a where a.area.id= :searchString", "searchString", area.getId());
	}

	public List<Manager> getCustomerUsers(Customer customer) {
		return (List<Manager>) getHibernateTemplate().findByNamedParam(
				"from CustomerUser c where c.customer.id= :searchString", "searchString", customer.getId());
	}

	public List<Manager> getRegionalUsers(Region region) {
		return (List<Manager>) getHibernateTemplate().findByNamedParam(
				"from RegionalUser r where r.region.id= :searchString", "searchString", region.getId());
	}

	public Manager getManagerById(Manager manager) {
		if(manager instanceof CustomerUser) return (CustomerUser)getHibernateTemplate().get(CustomerUser.class, manager.getId());
		if(manager instanceof RegionalUser) return (RegionalUser)getHibernateTemplate().get(RegionalUser.class, manager.getId());
		return (AreaUser)getHibernateTemplate().get(AreaUser.class, manager.getId());
	}

	public CustomerUser save(CustomerUser user) {
		getHibernateTemplate().saveOrUpdate(user);
		return user;
	}

	public RegionalUser save(RegionalUser user) {
		getHibernateTemplate().saveOrUpdate(user);
		return user;
	}

	public AreaUser save(AreaUser user) {
		getHibernateTemplate().saveOrUpdate(user);
		return user;
	}

	public Manager save(Manager manager) {
		getHibernateTemplate().saveOrUpdate(manager);
		return manager;
	}
	
	public void delete(Manager manager) {
		getHibernateTemplate().delete(manager);
	}
}
