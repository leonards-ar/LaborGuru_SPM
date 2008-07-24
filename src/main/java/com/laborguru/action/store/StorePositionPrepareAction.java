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
	
	/**
	 * This property holds an empty position set by Spring containing
	 * default values
	 */
	private Position position;
	
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

	/**
	 * 
	 * @param id
	 * @return
	 */
	private Position getPositionById(Integer id) {
		if(id != null) {
			for(Position storePosition: getStore().getPositions()){
				if (id.equals(storePosition.getId())) {
					return storePosition;
				}
			}		
		}
		return null;
	}
	
	/**
	 * 
	 */
	private void setStorePositionsName() {
		if (!"".equals(getNewPositionName().trim())) {
			addNewPosition();
		}

		// Add or update existing positions
		for (Position position : getPositions()) {
			Position storePosition = getPositionById(position.getId());
			if (storePosition != null) {
				storePosition.setName(position.getName());
				storePosition.setPositionIndex(position.getPositionIndex());
			} else {
				getStore().addPosition(position);
			}
		}

		// Delete positions
		for (Position position : getRemovePositions()) {
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
			setPositions(getStore().getPositions());
		}
	}

	/**
	 * Get a New Position with the name passed as a parameter; 
	 * @param name
	 * @return
	 */
	private Position addNewPosition() {
		Position newPosition = getPosition();
		newPosition.setName(getNewPositionName());
		newPosition.setPositionIndex(getPositions().size());
		getPositions().add(newPosition);
		return newPosition;
	}
	
	
	
	/**
	 * add a Blank Position
	 * 
	 * @return
	 */
	public String addPosition() {
		if(!"".equals(getNewPositionName().trim())){
			addNewPosition();
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
			fixPositionIndex();
		}
		return SpmActionResult.EDIT.getResult();
	}

	/**
	 * This method sets the index Position of the dayParts elements with the element index of list.
	 */
	private void fixPositionIndex(){
		int i = 0;
		for (Position position: getPositions()){
			position.setPositionIndex(i++);
		}
	}
	
	/**
	 * Moves a Position up.
	 * @return
	 */
	public String oneUp() {
		if (getIndex() != null && getIndex() > 0) {
			int indexAux = getIndex(); 
			int indexPrev = indexAux - 1;
			
			swapPositions(indexAux, indexPrev);			
		}
		return SpmActionResult.EDIT.getResult();
	}
	
	/**
	 * Moves a Position Down
	 * @return
	 */
	public String oneDown() {
		if (getIndex() !=null && getIndex() < getPositions().size() - 1) {
			int indexAux = getIndex();
			int indexNext = indexAux + 1;
			if(indexNext < getPositions().size()) {
				swapPositions(indexNext, indexAux);
			}
		}
		return SpmActionResult.EDIT.getResult();
	}
	
	/**
	 * This method swaps 2 positions on the positions list. 
	 * Indexes passed as parameter should be valid indexes.
	 * 
	 * It also updates the index Positions on the dayParts references.
	 * 
	 * @param index1 index of the dayPart to swap 
	 * @param index0 index of the dayPart to swap
	 */
	private void swapPositions(int index1, int index0) {
		Position positionAux = getPositions().get(index1);
		Position positionPrev = getPositions().get(index0);
		
		positionAux.setPositionIndex(index0); 			
		positionPrev.setPositionIndex(index1);
					
		positionAux = getPositions().set(index1, positionPrev);
		getPositions().set(index0, positionAux);
	}	
	
	
	/**
	 * @return the positions
	 */
	public List<Position> getPositions() {
		if(positions == null) {
			setPositions(new ArrayList<Position>());
		}
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

	/**
	 * @return the position
	 */
	public Position getPosition() {
		if(position == null) {
			position = new Position();
		}
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Position position) {
		this.position = position;
	}
	
}
