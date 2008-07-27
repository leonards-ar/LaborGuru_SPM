package com.laborguru.action.customer;

import com.laborguru.action.SpmAction;
import com.laborguru.action.SpmActionResult;

public class CustomerPrepareAction extends SpmAction {

	private static final long serialVersionUID = 1L;

	public String list(){
		return SpmActionResult.LIST.getResult();
	}
}
