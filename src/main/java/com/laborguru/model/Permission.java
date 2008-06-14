package com.laborguru.model;

import java.util.List;

/**
 * Permission Type
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class Permission extends SpmObject {
	
	private static final long serialVersionUID = 1L;

	
	private Integer id;
	private String key;
	private String description;
	
	private List<MenuItem> menuItems;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see com.laborguru.model.SpmObject#hashCode()
	 */
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((key == null) ? 0 : key.hashCode());
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
		final Permission other = (Permission) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}
	
	
	public void addMenuItem(MenuItem menuItem){
		
		if (menuItem == null){
			throw new IllegalArgumentException("Null child menu item passed in as parameter");
		}

		if (menuItem.getPermission() != null){
			menuItem.getPermission().menuItems.remove(menuItem);
		}
		
		menuItem.setPermission(this);
		this.menuItems.add(menuItem);
	}
}
