/**
 * 
 */
package com.laborguru.model;

import java.util.List;

/**
 * Menu Type
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class Menu extends SpmObject {
	private List<MenuItem> items;
	private int selectedItemIndex;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public Menu() {
	}

	/**
	 * @return the items
	 */
	public List<MenuItem> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(List<MenuItem> items) {
		this.items = items;
	}

	/**
	 * @return the selectedItem
	 */
	public MenuItem getSelectedItem() {
		if(getItems() != null && getSelectedItemIndex() >= 0 && getSelectedItemIndex() < getItems().size()) {
			return getItems().get(getSelectedItemIndex());
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param index
	 * @return
	 */
	public boolean isSelectedItemIndex(int index) {
		return index >=0 && index == getSelectedItemIndex() && index < getItems().size();
	}
	/**
	 * @return the selectedItemIndex
	 */
	public int getSelectedItemIndex() {
		return selectedItemIndex;
	}

	/**
	 * @param selectedItemIndex the selectedItemIndex to set
	 */
	public void setSelectedItemIndex(int selectedItemIndex) {
		this.selectedItemIndex = selectedItemIndex;
	}
	
}
