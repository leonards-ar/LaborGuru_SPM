package com.laborguru.action.store;

import org.apache.log4j.Logger;

import com.laborguru.action.SpmActionResult;
import com.opensymphony.xwork2.Preparable;


/**
 *
 * @author <a href="fbarrera@gmail.com">Federico Barrera Oro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
@SuppressWarnings("serial")
public class StorePositionPrepareAction extends StoreAdministrationBaseAction implements Preparable {

	private static Logger log = Logger.getLogger(StorePositionPrepareAction.class);
	
	/**
	 * Prepare the data to be used on the edit page
	 * 
	 * @throws Exception
	 */
	public void prepare() throws Exception {
	}

	/**
	 * 
	 * @return
	 */
	public String edit(){
		return SpmActionResult.EDIT.getResult();
	}
	
	/**
	 * 
	 * @return
	 */
	public String show(){
		return SpmActionResult.SHOW.getResult();
	}

}
