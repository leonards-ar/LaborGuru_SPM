/*
 * File name: UtilizationPrepareAction.java
 * Creation date: Jul 18, 2008 4:57:38 PM
 * Copyright Mindpool
 */
package com.laborguru.action.store;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.laborguru.action.SpmActionResult;
import com.laborguru.exception.SpmCheckedException;
import com.laborguru.model.Position;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class UtilizationPrepareAction extends StoreAdministrationBaseAction {
	private static Logger log = Logger.getLogger(UtilizationPrepareAction.class);
	private List<Position> storePositions;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7232005512019173339L;

	/**
	 * 
	 */
	public UtilizationPrepareAction() {
	}
	
	/**
	 * 
	 */
	private void loadPositions() {
		setStorePositions(getStore().getOrderedPositions());
	}
	
	/**
	 * 
	 */
	private void setPositions() {
		for(Position pos : getStore().getPositions()) {
			int idx = getStorePositions().indexOf(pos);
			if(idx >= 0) {
				pos.setUtilizationBottom(getStorePositions().get(idx).getUtilizationBottom());
				pos.setUtilizationTop(getStorePositions().get(idx).getUtilizationTop());
			}
		}
	}
	
	/**
	 * Prepares the edit page
	 * 
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		loadPositions();
		
		return SpmActionResult.EDIT.getResult();
	}
	
	/**
	 * Prepares the view page
	 * 
	 * @return
	 * @throws Exception
	 */
	public String show() throws Exception {
		loadPositions();
		
		return SpmActionResult.SHOW.getResult();
	}	
	
	/**
	 * Stores a store on the DB
	 * 
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		try {
			
			setPositions();
			
			if(log.isDebugEnabled()) {
				log.debug("About to save store: " + getStore());
			}
			
			getStoreService().save(getStore());

			if(log.isInfoEnabled()) {
				log.info("Store utilization successfully updated for store with id [" + getStoreId() + "]");
			}
			
			return SpmActionResult.SUCCESS.getResult();
		} catch (SpmCheckedException e) {
			addActionError(e.getErrorMessage());
			return SpmActionResult.INPUT.getResult();
		}
	}

	/**
	 * @return the storePositions
	 */
	public List<Position> getStorePositions() {
		if(storePositions == null) {
			setStorePositions(new ArrayList<Position>());
		}
		return storePositions;
	}

	/**
	 * @param storePositions the storePositions to set
	 */
	public void setStorePositions(List<Position> storePositions) {
		this.storePositions = storePositions;
	}	

}
