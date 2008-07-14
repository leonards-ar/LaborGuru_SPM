package com.laborguru.action.store;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.laborguru.action.SpmActionResult;
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
public class StorePositionPrepareAction extends StoreAdministrationBaseAction implements Preparable {

	private static Logger log = Logger.getLogger(StorePositionPrepareAction.class);
	
	private List<Position> positions;
	
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
	 * @throws Exception
	 */
	public void prepareEdit() throws Exception {
	}

	/**
	 * Prepare the data to be used on the show page
	 * @throws Exception
	 */
	public void prepareShow() throws Exception {
	}
	/**
	 * Shows the edit page
	 * @return
	 */
	public String edit(){
		loadPositions();
		return SpmActionResult.EDIT.getResult();
	}
	
	/**
	 * Shows the list page
	 * @return
	 */
	public String show(){
		loadPositions();
		return SpmActionResult.SHOW.getResult();
	}

	/**
	 * load the store's position
	 */
	public void loadPositions() {
		if(getStore() != null) {
			setPositions(getStore().getOrderedPositions());
		}
	}
	
	/**
	 * add a Blank Position
	 * @return
	 */
	public String addPosition() {
		List<Position> tmpPositions = getPositions();
		tmpPositions.add(new Position());
		setPositions(tmpPositions);
		
		return SpmActionResult.EDIT.getResult();
	}
	
	/**
	 * Removes an element from Positions 
	 * @return
	 */
	public String removePosition() {
		List<Position> tmpPositions = getPositions();
		tmpPositions.remove(getIndex());
		setPositions(tmpPositions);
		
		return SpmActionResult.EDIT.getResult();
	}
	
	/**
	 * @return the positions
	 */
	public List<Position> getPositions() {
		if(positions == null) {
			positions = new ArrayList<Position>();
		}
		return positions;
	}

	/**
	 * @param positions the positions to set
	 */
	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	/**
	 * @return the index
	 */
	public Integer getIndex() {
		if(index==null) {
			index = new Integer(-1);
		}
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}
	
	

}
