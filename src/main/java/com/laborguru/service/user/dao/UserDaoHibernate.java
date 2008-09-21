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

	/**
	 * True when the user with userName passed as parameter already exists in the Database 
	 * @param userName The userName 
	 * @return true if the user exist
	 * @see com.laborguru.service.user.dao.UserDao#existUser(java.lang.String)
	 */
	public Boolean existUser(String username){
		List<String> result = (List<String>)getHibernateTemplate().findByNamedParam(
				"select user.userName from User user where user.userName = :searchString", "searchString",username);
				
		return result.size() != 0;
	}

	/**
	 * Evicts the persistence instance passed in as parameter.
	 * @param auxUser
	 * @see com.laborguru.service.user.dao.UserDao#evict(com.laborguru.model.User)
	 */
	public void evict(User auxUser) {
		getHibernateTemplate().evict(auxUser);
	}
	
	/**
	 * Retrieves a User by id.
	 * @param user
	 * @return
	 * @see com.laborguru.service.user.dao.UserDao#getUserById(com.laborguru.model.User)
	 */
	public User getUserById(User user) {
		return (User)getHibernateTemplate().get(User.class, user.getId());
	}
	
	/**
	 * Finds all users that are not employee.
	 * @return
	 * @see com.laborguru.service.user.dao.UserDao#findAll()
	 */
	public List<User> findAll(){
		return (List<User>)getHibernateTemplate().find("from User");
	}
	
	public void delete(User user){
		getHibernateTemplate().delete(user);
	}
	
}
