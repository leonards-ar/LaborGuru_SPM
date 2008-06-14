package com.laborguru.model;

import java.util.Set;

/**
 * Menu Item Type
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class MenuItem extends SpmObject {
	
	private static final long serialVersionUID = 1L;

	
	private Integer id;
	private String labelKey;
	private String helpKey;
	private String target;
	private Integer position;
	private MenuItem parentMenuItem;

	private Permission permission;
	
	private Set<MenuItem> childMenuItems;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLabelKey() {
		return labelKey;
	}

	public void setLabelKey(String labelKey) {
		this.labelKey = labelKey;
	}

	public String getHelpKey() {
		return helpKey;
	}

	public void setHelpKey(String helpKey) {
		this.helpKey = helpKey;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public MenuItem getParentMenuItem() {
		return parentMenuItem;
	}

	public void setParentMenuItem(MenuItem parent) {
		this.parentMenuItem = parent;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	
	
	/* (non-Javadoc)
	 * @see com.laborguru.model.SpmObject#hashCode()
	 */
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((labelKey == null) ? 0 : labelKey.hashCode());
		result = prime * result + ((parentMenuItem == null) ? 0 : parentMenuItem.hashCode());
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((target == null) ? 0 : target.hashCode());
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
		final MenuItem other = (MenuItem) obj;
		if (labelKey == null) {
			if (other.labelKey != null)
				return false;
		} else if (!labelKey.equals(other.labelKey))
			return false;
		if (parentMenuItem == null) {
			if (other.parentMenuItem != null)
				return false;
		} else if (!parentMenuItem.equals(other.parentMenuItem))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (target == null) {
			if (other.target != null)
				return false;
		} else if (!target.equals(other.target))
			return false;
		return true;
	}

	public Set<MenuItem> getChildMenuItems() {
		return childMenuItems;
	}

	
	/**
	 * We leave it private to enforce the cardinality with the addChildMenuItem.
	 * DO NOT MAKE IT PUBLIC
	 * @param childMenuItems
	 */
	private void setChildMenuItems(Set<MenuItem> childMenuItems) {
		this.childMenuItems = childMenuItems;
	}

	public void addChildMenuItem(MenuItem childMenuItem){
		
		if (childMenuItem == null){
			throw new IllegalArgumentException("Null child menu item passed in as parameter");
		}
		
		if (childMenuItem.getParentMenuItem() != null){
			childMenuItem.getParentMenuItem().getChildMenuItems().remove(childMenuItem);
		}
		
		childMenuItem.setParentMenuItem(this);
		this.childMenuItems.add(childMenuItem);
	}
}
