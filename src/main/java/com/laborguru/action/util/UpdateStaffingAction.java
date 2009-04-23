package com.laborguru.action.util;

import com.laborguru.action.SpmAction;
import com.laborguru.action.SpmActionResult;
import com.laborguru.service.projection.ProjectionService;

public class UpdateStaffingAction extends SpmAction {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4235753113788304917L;

	private ProjectionService projectionService;
	
	/**
	 * 
	 * @return
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() {
		getProjectionService().updateAll();
		
		return SpmActionResult.SUCCESS.getResult();
	}

	/**
	 * @return the projectionService
	 */
	public ProjectionService getProjectionService() {
		return projectionService;
	}

	/**
	 * @param projectionService the projectionService to set
	 */
	public void setProjectionService(ProjectionService projectionService) {
		this.projectionService = projectionService;
	}

}
