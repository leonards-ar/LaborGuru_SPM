package com.laborguru.service.user;

import com.laborguru.model.User;
import com.laborguru.service.user.dao.UserDao;

/**
 * Spring Implementation for UserService
 * @author cnunez
 *
 */
public class UserServiceBean implements UserService {

	public static final String USER_NULL = "The user passed as parameter cannot be can not be null";
	public static final String USER_NAME_NULL = "The user passed as parameter cannot have null username";
	
	private UserDao userDao;
	
	/* (non-Javadoc)
	 * @see com.mindpool.laborguru.service.user.UserService#authenticateUser(com.mindpool.laborguru.model.User)
	 */

	/**
	 * @param userLoggingOn
	 * @return
	 */
	public User getUserByUserName(User userLoggingOn) {
		if (userLoggingOn == null)
			throw new IllegalArgumentException(USER_NULL);
		
		if (userLoggingOn.getUserName() == null)
			throw new IllegalArgumentException(USER_NAME_NULL);
		
				
		return userDao.getUserByUsername(userLoggingOn);
	}

	/* (non-Javadoc)
	 * @see com.laborguru.service.user.UserService#setUserDao(com.laborguru.service.user.dao.UserDao)
	 */
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

}
