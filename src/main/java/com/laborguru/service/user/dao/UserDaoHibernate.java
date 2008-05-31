package com.laborguru.service.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.laborguru.model.User;

 
public class UserDaoHibernate extends HibernateDaoSupport implements UserDao {

	
	public User getUserByUsername(User user) {

		List<User> result = (List<User>)getHibernateTemplate().findByNamedParam(
				"from User user where user.userName = :searchString", "searchString",user.getUserName());

		User retUser = null;
		
		if ( result.size() > 0)
			retUser = result.get(0);
		
		return retUser;
	}

}
