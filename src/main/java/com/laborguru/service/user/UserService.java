package com.laborguru.service.user;

import com.laborguru.model.User;
import com.laborguru.service.Service;
import com.laborguru.service.user.dao.UserDao;

/**
 * User Service Interface. Handles Authentication & Logon for SPM Users.  
 * @author cnunez
 *
 */
public interface UserService extends Service {

	/**
	 * Retrieves  a user by userName
	 * @param A User object containing a username.
	 * @return A full populated User object.
	 */
	User getUserByUserName(User user);
	 
	void setUserDao(UserDao userDao);
}
