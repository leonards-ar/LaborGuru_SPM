package com.laborguru.service.security;

import java.security.Principal;
import java.util.Date;

import org.springframework.dao.DataAccessException;

import com.laborguru.exception.SpmCheckedException;
import com.laborguru.logger.DefaultSpmLogger;
import com.laborguru.model.User;
import com.laborguru.service.user.UserService;
import com.mindpool.security.service.UserAuthenticationService;

public class AuthenticationService implements UserAuthenticationService {

	private static final DefaultSpmLogger log = DefaultSpmLogger.getInstance();

	private static final int MAX_LOGIN_TRIES = 3;

	private int loginTries = MAX_LOGIN_TRIES;
	
	UserService service;

	private String reason = null;

	public void setService(UserService service) {
		this.service = service;
	}

	public Principal authenticate(String username, String password) {

		UserDetailsImpl userDetails = null;
		User user = new User();
		user.setUserName(username);
		try {
			user = service.getUserByUserName(user);
		} catch (DataAccessException e) {
			log.errorLog("Error trying to get user...", e);
			return null;
		}

		if (user != null) {
			if (user.getStatus() == 1) {
				reason = UserAuthenticationService.USER_DISABLED_ERROR;
			} else if (password.equals(user.getPassword())) {
				user.setLastLogon(new Date());
				user.setLoginCount(0);
				userDetails = new UserDetailsImpl(user);
			} else {
				int loginCount = user.getLoginCount();
				user.setLoginCount(++loginCount);
				if (loginCount == loginTries) {
					user.setStatus(1);
					reason = UserAuthenticationService.USER_DISABLED_ERROR;
				} else {
					reason = UserAuthenticationService.BAD_PASSWORD;
				}
			}
			//try {
			service.save(user);
			//} catch(SpmCheckedException e){
			//	log.errorLog("Error trying to save user.");
			//}
		} else {
			reason = UserAuthenticationService.UNKNOWN_USER_ERROR;
		}

		return userDetails;

	}

	public String getReason() {
		return reason;
	}
	
	public void setLoginTries(int loginTries){
		this.loginTries = loginTries;
	}

}
