package com.laborguru.service.user;

import com.laborguru.service.user.dao.UserDao;
import com.mindpool.laborguru.model.User;

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
	public User authenticateUser(User userLoggingOn) {
		
		User user = getUserByUserName(userLoggingOn);
		
		if (user != null){

			//Verifying the password
			//TODO Setting the Business Response with the error
			if (!user.getPassword().equals(userLoggingOn.getPassword())){
				user = null;
			}
		} else {
			//Invalid username
			//TODO Doing nothing for now, in the near future setting the business response with the Invalid Username error
		}
		
		return user;
	}

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

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
