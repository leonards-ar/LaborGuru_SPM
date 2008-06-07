package com.laborguru.model;

import java.util.Set;

/**
 * Profile Type
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class Profile extends SpmObject {
	
	private static final long serialVersionUID = 1L;

	
	private Integer id;
	private String name;
	private String description;
	
	private Set<Permission> permissions;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}

	
	/**
	 * We leave it private to enforce the cardinality with the addPermission.
	 * DO NOT MAKE IT PUBLIC
	 * @param permissions
	 */
	private void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	/**
	 * @param permission
	 */
	public void addPermission(Permission permission){
		
		if (permission == null){
			throw new IllegalArgumentException("Null permission passed in as parameter");
		}

		this.permissions.add(permission);
	}	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see com.laborguru.model.SpmObject#hashCode()
	 */
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see com.laborguru.model.SpmObject#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Profile other = (Profile) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
