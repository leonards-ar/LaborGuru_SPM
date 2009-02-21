/*
 * File name: EmployeeHomeAction.java
 * Creation date: 21/02/2009 15:22:11
 * Copyright Mindpool
 */
package com.laborguru.action.home;

import java.util.ArrayList;
import java.util.List;

import com.laborguru.action.SpmAction;
import com.laborguru.model.Position;
import com.laborguru.service.position.PositionService;
import com.opensymphony.xwork2.Preparable;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class EmployeeHomeAction extends SpmAction implements Preparable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1123623916987413899L;
	
	private List<Position> positions;
	private PositionService positionService;

	/**
	 * 
	 */
	public EmployeeHomeAction() {
	}

	/**
	 * @throws Exception
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	public void prepare() throws Exception {
		if(getLoggedEmployeeOrNull() != null) {
			setPositions(getPositionService().getPositionsByStore(getEmployeeStore()));
		}
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
	 * @return the positionService
	 */
	public PositionService getPositionService() {
		return positionService;
	}

	/**
	 * @param positionService the positionService to set
	 */
	public void setPositionService(PositionService positionService) {
		this.positionService = positionService;
	}	
}