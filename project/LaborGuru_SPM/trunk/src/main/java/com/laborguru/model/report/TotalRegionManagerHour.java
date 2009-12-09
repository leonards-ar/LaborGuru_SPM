package com.laborguru.model.report;

import com.laborguru.model.Area;

/**
 *
 * @author <a href="fbarreraoro@gmail.com">Federico Barrera Oro</a>
 * @version 1.1
 * @since SPM 1.1
 *
 */
public class TotalRegionManagerHour extends TotalManagerHour {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4859066865015430608L;
	
	Area area;

	/**
	 * @return the area
	 */
	public Area getArea() {
		return area;
	}

	/**
	 * @param area the area to set
	 */
	public void setArea(Area area) {
		this.area = area;
	}
	
	
}
