package com.laborguru.service.user;

import com.laborguru.service.Service;
import com.laborguru.service.user.dao.UserDao;
import com.mindpool.laborguru.model.User;

/**
 * User Service Interface. Handles Authentication & Logon for SPM Users.  
 * @author cnunez
 *
 */
public interface UserService extends Service {
	/**
	 * Authenticates that a user logging in exists in the system and correct credentials have been given.
	 * @param A User object containing a username and password (no ID).
	 * @return A full populated User object.
	 * TODO: Change User return for a BusinessResponse?
	 */
	User authenticateUser(User userLoggingOn);

	/**
	 * Retrieves  a user by userName
	 * @param A User object containing a username.
	 * @return A full populated User object.
	 */
	User getUserByUserName(User user);
	 
	void setUserDao(UserDao userDao);
}
