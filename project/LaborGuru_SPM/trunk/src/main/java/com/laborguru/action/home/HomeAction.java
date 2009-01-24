/*
 * File name: HomeAction.java
 * Creation date: 22/11/2008 12:48:27
 * Copyright Mindpool
 */
package com.laborguru.action.home;

import java.util.ArrayList;
import java.util.List;

import com.laborguru.action.SpmAction;
import com.laborguru.action.SpmActionResult;
import com.laborguru.model.Position;
import com.laborguru.model.Profile;
import com.laborguru.service.position.PositionService;
import com.opensymphony.xwork2.Preparable;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class HomeAction extends SpmAction implements Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5686734450935309249L;
	private List<Position> positions;
	private PositionService positionService;

	/**
	 * 
	 */
	public HomeAction() {
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
	 * @return
	 * @throws Exception
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		Profile loggedUserProfile = getLoggedUser().getProfile();
		String result = loggedUserProfile != null ? loggedUserProfile.getHomeResult() : null;
		if(result != null) {
			return result;
		} else {
			return SpmActionResult.SUCCESS.getResult();
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
