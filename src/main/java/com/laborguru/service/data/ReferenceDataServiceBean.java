/*
 * File name: ReferenceDataServiceBean.java
 * Creation date: 22/06/2008 20:36:13
 * Copyright Mindpool
 */
package com.laborguru.service.data;

import java.util.List;
import java.util.Map;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class ReferenceDataServiceBean implements ReferenceDataService {
	private Map<String, Object> referenceData;
	
	/**
	 * 
	 */
	public ReferenceDataServiceBean() {
	}

	/**
	 * @param country
	 * @return
	 * @see com.laborguru.service.data.ReferenceDataService#getStates(java.lang.String)
	 */
	public List<String> getStates(String country) {
		return (List<String>) getReferenceData().get("states-" + country.toLowerCase());
	}

	/**
	 * @return
	 * @see com.laborguru.service.data.ReferenceDataService#getStatus()
	 */
	public Map<String, String> getStatus() {
		return (Map<String, String>) getReferenceData().get("status");
	}

	/**
	 * @return the referenceData
	 */
	public Map<String, Object> getReferenceData() {
		return referenceData;
	}

	/**
	 * @param referenceData the referenceData to set
	 */
	public void setReferenceData(Map<String, Object> referenceData) {
		this.referenceData = referenceData;
	}

}
