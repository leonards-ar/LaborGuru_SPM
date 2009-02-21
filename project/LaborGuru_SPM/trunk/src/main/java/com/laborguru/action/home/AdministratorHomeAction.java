/*
 * File name: AdministratorHomeAction.java
 * Creation date: 21/02/2009 15:28:13
 * Copyright Mindpool
 */
package com.laborguru.action.home;

import com.laborguru.action.SpmAction;
import com.laborguru.action.SpmActionResult;
import com.opensymphony.xwork2.Preparable;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class AdministratorHomeAction extends SpmAction implements Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public AdministratorHomeAction() {
	}

	/**
	 * @throws Exception
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	public void prepare() throws Exception {
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute() throws Exception {
		return SpmActionResult.SUCCESS.getResult();
	}
}
