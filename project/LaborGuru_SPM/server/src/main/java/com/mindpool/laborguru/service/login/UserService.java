package com.mindpool.laborguru.service.login;

import com.mindpool.laborguru.model.User;
import com.mindpool.laborguru.service.Service;

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
	 * TODO: See if we can change the User return for a BusinessResponse?
	 */
	public User authenticateUser(User userLoggingOn);
}
