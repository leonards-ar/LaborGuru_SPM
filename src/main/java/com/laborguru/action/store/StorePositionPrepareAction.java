package com.laborguru.action.store;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.laborguru.action.SpmActionResult;
import com.laborguru.exception.SpmCheckedException;
import com.laborguru.model.Position;
import com.opensymphony.xwork2.Preparable;

/**
 * 
 * @author <a href="fbarrera@gmail.com">Federico Barrera Oro</a>
 * @version 1.0
 * @since SPM 1.0
 * 
 */
@SuppressWarnings("serial")
public class StorePositionPrepareAction extends StoreAdministrationBaseAction
		implements Preparable {

	private static Logger log = Logger.getLogger(StorePositionPrepareAction.class);

	private List<Position> positions;
	
	private List<Position> removePositions;

	private Integer index;

	/**
	 * Prepare the data to be used on the page
	 * 
	 * @throws Exception
	 */
	public void prepare() throws Exception {
	}

	/**
	 * Prepare the data to be used on the edit page
	 * 
	 * @throws Exception
	 */
	public void prepareEdit() throws Exception {
	}

	/**
	 * Prepare the data to be used on the show page
	 * 
	 * @throws Exception
	 */
	public void prepareShow() throws Exception {
	}

	/**
	 * Shows the edit page
	 * 
	 * @return
	 */
	public String edit() {
		loadPositions();
		getPositions().add(new Position());
		return SpmActionResult.EDIT.getResult();
	}

	/**
	 * Shows the list page
	 * 
	 * @return
	 */
	public String show() {
		loadPositions();
		return SpmActionResult.SHOW.getResult();
	}

	public void setStorePositionsName() {
		if("".equals(getPositions().get(getPositions().size() - 1).getName().trim())) {
			//remove the empty position used for add new ones
			getPositions().remove(getPositions().size() - 1);
		}
		
		for (Position position: getPositions()) {
			Position aPosition = position;
			if(position.getId() != null) {
				for(Position storePosition: getStore().getPositions()){
					if (storePosition.getId().equals(aPosition.getId())) {
						aPosition = storePosition;
						aPosition.setName(position.getName());
						break;
				}
			} 
		
			} else {
				aPosition = position;
				aPosition.setStore(getStore());
			}
				getStore().addPosition(aPosition);
		}
		
		for(Position position: getRemovePositions()) {
			position.setStore(getStore());
			getStore().getPositions().remove(position);
		}
	}
	/**
	 * Save the positions to the store.
	 * @return
	 */
	public String save() {
		try{
			
			setStorePositionsName();
			
			if(log.isDebugEnabled()) {
				log.debug("About to save store: " + getStore());
			}
			
			getStoreService().save(getStore());
			
			if(log.isInfoEnabled()) {
				log.info("Store positions successfully updated for store with id [" + getStoreId() + "]");
			}
			
			return SpmActionResult.SUCCESS.getResult();
			
		}catch(SpmCheckedException e) {
			addActionError(e.getErrorMessage());
			return SpmActionResult.INPUT.getResult();
		}
	}

	/**
	 * load the store's position
	 */
	public void loadPositions() {
		if (getStore() != null) {
			setPositions(getStore().getOrderedPositions());
		}
	}

	/**
	 * add a Blank Position
	 * 
	 * @return
	 */
	public String addPosition() {
		if(!"".equals(getPositions().get(getPositions().size() - 1).getName().trim())){
			positions.add(new Position());
		}
		return SpmActionResult.EDIT.getResult();
	}

	/**
	 * Removes an element from Positions
	 * 
	 * @return
	 */
	public String removePosition() {
		Position removePosition = getPositions().remove(getIndex().intValue());
		if(removePosition.getId() != null) {
			getRemovePositions().add(removePosition);
		}
		return SpmActionResult.EDIT.getResult();
	}

	/**
	 * validates that any name is not empty
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	 //TODO do validation by xml.
	public void validate() {
		if(getPositions() != null) {
			for(int i = 0; i < getPositions().size() - 1; i++ ) {
				if("".equals(getPositions().get(i).getName())) {
					addFieldError("name", getText("error.storeoperations.positionnames.name.required"));
					break;
				}
			}
		}
	}
	
	/**
	 * @return the positions
	 */
	public List<Position> getPositions() {
		return positions;
	}

	/**
	 * @param positions
	 *            the positions to set
	 */
	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	
	/**
	 * @return the removePositions
	 */
	public List<Position> getRemovePositions() {
		if(this.removePositions == null) {
			this.removePositions = new ArrayList<Position>();
		}
		return removePositions;
	}

	/**
	 * @param removePositions the removePositions to set
	 */
	public void setRemovePositions(List<Position> removePositions) {
		this.removePositions = removePositions;
	}

	/**
	 * @return the index
	 */
	public Integer getIndex() {
		if (index == null) {
			index = new Integer(-1);
		}
		return index;
	}

	/**
	 * @param index
	 *            the index to set
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}
	
}
