/*
 * File name: ReferenceDataService.java
 * Creation date: 22/06/2008 20:01:19
 * Copyright Mindpool
 */
package com.laborguru.service.data;

import java.util.Map;

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
	Map<String, String> getStates(String country);
	
	/**
	 * Retrieves the different status codes. The map key is the
	 * code and the map value is the message bundle key to the
	 * label
	 * @return Status map.
	 */
	Map<String, String> getStatus();
}
