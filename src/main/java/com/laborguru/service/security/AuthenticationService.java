package com.laborguru.service.security;

import java.util.Set;

import com.laborguru.model.User;
import com.laborguru.service.user.UserService;
import com.laborguru.utils.UserAdapter;
import com.mindpool.security.principal.SecurityUser;
import com.mindpool.security.service.UserAuthenticationService;

public class AuthenticationService implements UserAuthenticationService{

	UserService service;
	
	public SecurityUser authenticateUser(String username, String password)
			throws Exception {
		User user = new User();
		user.setUserName(username);
		user.setPassword(password);
		return new UserAdapter(service.authenticateUser(user));
	}

	public Set<String> getPermissions(SecurityUser user) {
		return user.getPermissions();
	}

	public Set<String> getRoles(SecurityUser user) {
		return user.getRoles();
	}
	
	public void setService(UserService service){
		this.service = service;
	}
	

}
