/*
 * File name: ReferenceDataService.java
 * Creation date: 22/06/2008 20:01:19
 * Copyright Mindpool
 */
package com.laborguru.service.data;

import java.util.List;
import java.util.Map;

import com.laborguru.model.Profile;
import com.laborguru.service.Service;

/**
 * This interface defines the behaviour of the service in charge 
 * of retrieving reference data.<br/>
 * Reference data is for example States
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public interface ReferenceDataService extends Service {

	/**
	 * Returns the map of states for a given country. The key is
	 * the State code and the value the State description.
	 * @param country Country ISO code (AR, US, CL, etc.)
	 * @return States map.
	 */
	List<String> getStates(String country);
	
	/**
	 * Retrieves the different status codes. The map key is the
	 * code and the map value is the message bundle key to the
	 * label
	 * @return Status map.
	 */
	Map<String, String> getStatus();
	
	/**
	 * Retrieves the different values for weeks used. The map key is the
	 * number of weeks and the map value is the message bundle key to the
	 * label
	 * @return Usedweeks map.
	 */
	Map<Integer, String> getUsedWeeks();
	
	/**
	 * Retrieves the manager role id.
	 * @return
	 */
	Profile getManagerRole();
	
	/**
	 * Retrieves the default employee role id.
	 * @return
	 */
	Profile getEmployeeRole();
}
