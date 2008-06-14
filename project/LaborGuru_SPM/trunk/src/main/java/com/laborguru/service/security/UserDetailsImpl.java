package com.laborguru.service.security;

import java.security.Principal;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.userdetails.UserDetails;

import com.laborguru.model.User;

public class UserDetailsImpl implements UserDetails, Principal {

	private User user;
	
	public UserDetailsImpl(User user){
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}

	public GrantedAuthority[] getAuthorities() {
		/*Set<GrantedAuthority>granters = new HashSet<GrantedAuthority>();
		Set<Profile> profileSet;
		profileSet = user.getProfiles();
		for(Profile profile: profileSet) {
			Set<Permission> permissionSet = profile.getPermissions();
			for(Permission permission: permissionSet) {
				granters.add(new GrantedAuthorityImpl(permission.getKey()));
			}
		}
		return (granters.size() > 0)?(GrantedAuthority[])granters.toArray():null;
		*/
		return new GrantedAuthority[] {new GrantedAuthorityImpl("ROLE_USER")};
	}

	public String getPassword() {
		
		return user.getPassword();
	}

	public String getUsername() {
		return user.getUserName();
	}
	
	public String getName() {
		return user.getUserName();
	}

	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
