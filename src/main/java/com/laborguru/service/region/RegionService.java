package com.laborguru.service.region;

import java.util.List;

import com.laborguru.model.Region;
import com.laborguru.service.Service;


/**
 *
 * @author <a href="fbarrera@gmail.com">Federico Barrera Oro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public interface RegionService extends Service {

	/**
	 * Gets all the regions
	 * @return
	 */
	List<Region> findAll();
	
	/**
	 * Retrieves a given region
	 * @param region The region to retrieve (holds the region id)
	 * @return
	 */
	Region getRegionById(Region region);
}
