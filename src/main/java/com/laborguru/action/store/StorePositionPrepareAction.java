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

	private String newPositionName;
	
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

	private void setStorePositionsName() {
		if(!"".equals(getNewPositionName().trim())) {
			
			getPositions().add(getNewPosition(getNewPositionName()));
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
	 * 
	 * @param name
	 * @return
	 */
	private Position getNewPosition(String name) {
		Position newPosition = new Position();
		newPosition.setName(getNewPositionName());		
		return newPosition;
	}
	
	/**
	 * add a Blank Position
	 * 
	 * @return
	 */
	public String addPosition() {
		if(!"".equals(getNewPositionName().trim())){
			getPositions().add(getNewPosition(getNewPositionName()));
			setNewPositionName(null);
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
	 * @return the newPositionName
	 */
	public String getNewPositionName() {
		return newPositionName;
	}

	/**
	 * @param newPosition the newPosition to set
	 */
	public void setNewPositionName(String newPositionName) {
		this.newPositionName = newPositionName;
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
