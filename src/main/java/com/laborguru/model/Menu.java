/**
 * 
 */
package com.laborguru.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Menu Type
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class Menu implements Serializable {
	
	private List<MenuItem> items;
	private int selectedItemIndex = 0;
	
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
	 * Menu toString
	 * @return string version of the object 
	 * @see com.laborguru.model.SpmObject#toString()
	 */
	public String toString()
	{
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
	   	.append("selectedItemIndex" , selectedItemIndex)
	   	.append("items",items)
	   	.toString();		
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
