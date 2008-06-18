package com.laborguru.service.security;

import java.security.Principal;

import org.springframework.dao.DataAccessException;

import com.laborguru.logger.DefaultSpmLogger;
import com.laborguru.model.User;
import com.laborguru.service.user.UserService;
import com.mindpool.security.service.UserAuthenticationService;

public class AuthenticationService implements UserAuthenticationService {

	private static final DefaultSpmLogger log = DefaultSpmLogger.getInstance();
	
	UserService service;

	private String reason = null;

	public void setService(UserService service) {
		this.service = service;
	}

	public Principal authenticate(String username, String password) {
		
		UserDetailsImpl userDetails = null;
		User user = new User();
		user.setUserName(username);
		try{
		user = service.getUserByUserName(user);
		} catch(DataAccessException e) {
			log.errorLog("Error trying to get user...", e);
			return null;
		}

		if (user != null) {
			if (password.equals(user.getPassword())) {
				userDetails = new UserDetailsImpl(user);
			} else {
				reason = UserAuthenticationService.BAD_PASSWORD;
			}

		} else {
			reason = UserAuthenticationService.UNKNOWN_USER_ERROR;
		}

		return userDetails;
		
	}

	public String getReason() {
		return reason;
	}

}
