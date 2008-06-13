package com.laborguru.service.security;

import java.security.Principal;

import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.springframework.dao.DataAccessException;

import com.laborguru.model.User;
import com.laborguru.service.user.UserService;
import com.laborguru.utils.UserDetailsImpl;
import com.mindpool.security.service.UserAuthenticationService;

public class AuthenticationService implements UserAuthenticationService {

	UserService service;

	private String reason = null;

	public void setService(UserService service) {
		this.service = service;
	}

	public Principal authenticate(String username, String password)
			throws UsernameNotFoundException, DataAccessException {
		UserDetailsImpl userDetails = null;
		User user = new User();
		user.setUserName(username);
		user = service.getUserByUserName(user);

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
