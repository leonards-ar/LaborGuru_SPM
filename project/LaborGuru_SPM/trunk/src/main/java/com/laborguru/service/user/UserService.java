package com.laborguru.service.user;

import com.laborguru.model.User;
import com.laborguru.service.Service;
import com.laborguru.service.user.dao.UserDao;

/**
 * User Service Interface. Handles services for SPM Users.  
 *
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public interface UserService extends Service {

	/**
	 * Save or Update a user
	 * @param user object that will be save.
	 * @return user Saved.
	 */
	User save(User user);
	/**
	 * Retrieves  a user by userName
	 * @param A User object containing a username.
	 * @return A full populated User object.
	 */
	User getUserByUserName(User user);
	 
	/**
	 * Setter for User Dao
	 * @param userDao UserDao
	 */
	void setUserDao(UserDao userDao);
}
