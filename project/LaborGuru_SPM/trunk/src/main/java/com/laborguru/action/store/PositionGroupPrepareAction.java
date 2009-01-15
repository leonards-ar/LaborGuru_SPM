package com.laborguru.action.store;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.laborguru.action.SpmActionResult;
import com.laborguru.model.PositionGroup;

/**
*
* @author <a href="fbarrera@gmail.com">Federico Barrera Oro</a>
* @version 1.0
* @since SPM 1.0
*
*/
@SuppressWarnings("serial")
public class PositionGroupPrepareAction extends StoreAdministrationBaseAction {

	private static Logger log = Logger.getLogger(PositionGroupPrepareAction.class);
	
	private List<PositionGroup> positionGroups;
	
	private List<PositionGroup> removePositionGroups;
	
	private String newPositionGroup;
	
	private Integer index;

	/**
	 * Prepare the data to be used on the page
	 * 
	 * @throws Exception
	 */
	public void prepare() throws Exception {
	}

	/**
	 * Shows the edit page
	 * 
	 * @return
	 */
	public String edit() {
		loadPositionGroup();
		return SpmActionResult.EDIT.getResult();
	}

	/**
	 * Shows the list page
	 * 
	 * @return
	 */
	public String show() {
		loadPositionGroup();
		return SpmActionResult.SHOW.getResult();
	}
	
	/**
	 * Add a new Position Group
	 * @return
	 */
	public String addPositionGroup() {
		if(!"".equals(getNewPositionGroup().trim())){
			addNewPositionGroup();
			setNewPositionGroup(null);
		}
		return SpmActionResult.EDIT.getResult();
	}
	
	/**
	 * Remove a new Position Group
	 * @return
	 */
	public String removePositionGroup() {
		PositionGroup removePositionGroup = getPositionGroups().remove(getIndex().intValue());
		if(removePositionGroup.getId() != null) {
			getRemovePositionGroups().add(removePositionGroup);
		}
		
		return SpmActionResult.EDIT.getResult();
	}
	
	
	/**
	 * Save the positions to the store.
	 * @return
	 */
	public String save() {
		
		setStorePositionGroups();
		
		if(log.isDebugEnabled()) {
			log.debug("About to save store: " + getStore());
		}
		
		getStoreService().save(getStore());
		
		if(log.isInfoEnabled()) {
			log.info("Store positions successfully updated for store with id [" + getStoreId() + "]");
		}
		
		return SpmActionResult.SUCCESS.getResult();
	}
	
	/**
	 * Load the store's position groups.
	 */
	private void loadPositionGroup() {
		if(getStore() != null) {
			setPositionGroups(getStore().getOrderedPositionGroups());
		}
	}
	
	/**
	 * 
	 */
	private void setStorePositionGroups(){
		if (!"".equals(getNewPositionGroup().trim())) {
			addNewPositionGroup();
		}

		// Add or update existing positions
		for (PositionGroup positionGroup: getPositionGroups()) {
			PositionGroup storePositionGroup = getPositionGroupById(positionGroup.getId());
			if (storePositionGroup != null) {
				storePositionGroup.setName(positionGroup.getName());
			} else {
				getStore().addPositionGroup(positionGroup);
			}
		}

		// Delete positions
		for (PositionGroup positionGroup: getRemovePositionGroups()) {
			positionGroup.setStore(getStore());
			getStore().getPositionGroups().remove(positionGroup);
		}

	}

	/**
	 * 
	 */
	private void addNewPositionGroup() {
		PositionGroup newPositionGroup = new PositionGroup();
		newPositionGroup.setName(getNewPositionGroup());
		newPositionGroup.setStore(getStore());
		getPositionGroups().add(newPositionGroup);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	private PositionGroup getPositionGroupById(Integer id){
		if(id != null){
			for(PositionGroup positionGroup: getStore().getPositionGroups()) {
				if(id.equals(positionGroup.getId())) {
					return positionGroup;
				}
			}
		}
		return null;
	}
	
	
	/**
	 * @return the positionGroups
	 */
	public List<PositionGroup> getPositionGroups() {
		if(positionGroups == null) {
			setPositionGroups(new ArrayList<PositionGroup>());
		}
		return positionGroups;
	}

	/**
	 * @param positionGroups the positionGroups to set
	 */
	public void setPositionGroups(List<PositionGroup> positionGroups) {
		this.positionGroups = positionGroups;
	}

	/**
	 * @return the newPositionGroup
	 */
	public String getNewPositionGroup() {
		return newPositionGroup;
	}

	/**
	 * @param newPositionGroup the newPositionGroup to set
	 */
	public void setNewPositionGroup(String newPositionGroup) {
		this.newPositionGroup = newPositionGroup;
	}

	/**
	 * @return the removePositionGroups
	 */
	public List<PositionGroup> getRemovePositionGroups() {
		if(removePositionGroups == null) {
			setRemovePositionGroups(new ArrayList<PositionGroup>());
		}
		return removePositionGroups;
	}

	/**
	 * @param removePositionGroups the removePositionGroups to set
	 */
	public void setRemovePositionGroups(List<PositionGroup> removePositionGroups) {
		this.removePositionGroups = removePositionGroups;
	}

	/**
	 * @return the index
	 */
	public Integer getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}
	
}
