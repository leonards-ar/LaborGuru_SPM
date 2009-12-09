package com.laborguru.model.report;

import java.math.BigDecimal;

import com.laborguru.model.Region;
import com.laborguru.model.SpmObject;

/**
 *
 * @author <a href="fbarreraoro@gmail.com">Federico Barrera Oro</a>
 * @version 1.1
 * @since SPM 1.1
 *
 */
public class TotalCustomerManagerHour extends TotalManagerHour {

	private static final long serialVersionUID = -4263736677595272709L;
	
	Region region;
	
	
	/**
	 * @return the customer
	 */
	public Region getRegion() {
		return region;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setRegion(Region region) {
		this.region = region;
	}

	
}
