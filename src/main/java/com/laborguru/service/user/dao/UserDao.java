package com.laborguru.service.user.dao;

import com.laborguru.model.User;


/**
 * UserDao interface
 * 
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public interface UserDao {

	/**
	 * Save or Update a user
	 * @param user object that will be save.
	 * @return user Saved.
	 */
	User save(User user);
	/**
	 * Retrieves  a user by userName
	 * @param user with username populated
	 * @return a full populated user
	 */
	User getUserByUsername(User user);
	
	
	/**
	 * True when the user with userName passed as parameter already exists in the Database 
	 * @param userName The userName 
	 * @return true if the user exist
	 */
	Boolean existUser(String userName);
	
}
