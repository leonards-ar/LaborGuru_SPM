package com.laborguru.utils;

import java.util.Set;

import com.laborguru.model.User;
import com.mindpool.security.principal.SecurityUser;

public class UserAdapter implements SecurityUser {

	private User user;
	
	public UserAdapter(User user){
		this.user = user;
	}
	public Set<String> getPermissions() {
		//TODO create a method that returns user permissions
		return null;
	}

	public Set<String> getRoles() {
		// TODO create a method that returns a user permission
		return null;
	}

	public String getName() {
		return user.getUserName();
	}
	
	public User getUser() {
		return user;
	}

}
