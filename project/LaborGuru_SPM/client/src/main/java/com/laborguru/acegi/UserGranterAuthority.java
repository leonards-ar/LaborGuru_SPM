/*package com.laborguru.acegi;

import java.security.Principal;
import java.security.acl.Group;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import org.acegisecurity.providers.jaas.AuthorityGranter;

public class UserGranterAuthority implements AuthorityGranter {

	private String roleGroupName = "Roles";
	
	public Set<String> grant(Principal principal) {
		if (principal instanceof Group) {
			Group group = ((Group) principal);
			if (roleGroupName.equals(group.getName())) {
				Enumeration members = group.members();
				Set<String> set = new HashSet<String>();
				while (members.hasMoreElements()) {
					Object role = members.nextElement();
					String roleName = ((Principal) role).getName();
					set.add(roleName);
				}
				return set.isEmpty() ? null : set;
			}
		}
		return null;
	}

	public String getRoleGroupName() {
		return roleGroupName;
	}
*/
	/**
	 * Change the role group name from the default of Roles
	 * 
	 * @param roleGroupName
	 *            new role group name
	 *
	public void setRoleGroupName(String roleGroupName) {
		this.roleGroupName = roleGroupName;
	}

}
*/