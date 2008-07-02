package com.laborguru.service.region.dao;

import java.util.List;

import com.laborguru.model.Region;


/**
 *
 * @author <a href="fbarrera@gmail.com">Federico Barrera Oro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public interface RegionDao {

	/**
	 * Get all the regions
	 * @return List with the regions
	 */
	List<Region> findAll();
}
