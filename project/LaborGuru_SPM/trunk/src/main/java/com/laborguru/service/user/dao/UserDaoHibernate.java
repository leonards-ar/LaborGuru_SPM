package com.laborguru.service.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.laborguru.model.User;

 
/**
 * Implementation for UserDao
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class UserDaoHibernate extends HibernateDaoSupport implements UserDao {

	
	
	/* (non-Javadoc)
	 * @see com.laborguru.service.user.dao.UserDao#save(com.laborguru.model.User)
	 */
	public User save(User user) {
		getHibernateTemplate().saveOrUpdate(user);
		return user;
	}

	/* (non-Javadoc)
	 * @see com.laborguru.service.user.dao.UserDao#getUserByUsername(com.laborguru.model.User)
	 */
	public User getUserByUsername(User user) {

		List<User> result = (List<User>)getHibernateTemplate().findByNamedParam(
				"from User user where user.userName = :searchString", "searchString",user.getUserName());

		User retUser = null;
		
		if ( result.size() > 0)
			retUser = result.get(0);
		
		return retUser;
	}

}
