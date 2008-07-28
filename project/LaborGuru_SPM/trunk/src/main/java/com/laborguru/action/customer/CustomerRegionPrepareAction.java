package com.laborguru.action.customer;

import java.util.HashSet;
import java.util.Set;

import com.laborguru.action.SpmAction;
import com.laborguru.action.SpmActionResult;
import com.laborguru.frontend.HttpRequestConstants;
import com.laborguru.model.Area;
import com.laborguru.model.Region;
import com.laborguru.service.region.RegionService;
import com.opensymphony.xwork2.Preparable;

public class CustomerRegionPrepareAction extends SpmAction implements Preparable{

	private static final long serialVersionUID = 1L;

	private RegionService regionService;

	private Region region = new Region();
	
	private Set<Area> areas = new HashSet<Area>();
	
	private Integer regionId;
	
	private Integer customerId;


	/**
	 * @return the regionService
	 */
	public RegionService getRegionService() {
		return regionService;
	}

	/**
	 * @param regionService the regionService to set
	 */
	public void setRegionService(RegionService regionService) {
		this.regionService = regionService;
	}

	/**
	 * @return the customerId
	 */
	public Integer getCustomerId() {
		return customerId;
	}

	/**
	 * Prepares the edit page
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		loadCustomerId();
		loadRegionFromId();
		setAreas(getRegion().getAreas());

		return SpmActionResult.EDIT.getResult();
	}
	
	/**
	 * 
	 */
	private void loadCustomerId() {
		Integer id = getCustomerId();
		//:TODO: Better way to communicate actions in Struts 2?
		if(id == null) {
			id = (Integer) getSession().get(HttpRequestConstants.CUSTOMER_TO_EDIT_ID);
			setCustomerId(id);
		} else {
			getSession().put(HttpRequestConstants.CUSTOMER_TO_EDIT_ID, id);
		}		
	}
	/**
	 * @return the region
	 */
	public Region getRegion() {
		return region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(Region region) {
		this.region = region;
	}

	/**
	 * @return the areas
	 */
	public Set<Area> getAreas() {
		return areas;
	}

	/**
	 * @param areas the areas to set
	 */
	public void setAreas(Set<Area> areas) {
		this.areas = areas;
	}

	/**
	 *  Load full customer from the property customerId
	 */
	private void loadRegionFromId() {
		Region tmpRegion = new Region();
		tmpRegion.setId(getRegionId());
		this.setRegion(getRegionService().getRegionById(tmpRegion));
	}
	
	/**
	 * Prepare data to be used in the actions methods defined for this action
	 * @throws Exception
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	public void prepare() throws Exception {
		//It's needed by the Preparable interface, don't comment out or removed
	}

	/**
	 * @return the regionId
	 */
	public Integer getRegionId() {
		return regionId;
	}

	/**
	 * @param regionId the regionId to set
	 */
	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}	
}
