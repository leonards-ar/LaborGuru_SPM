/**
 * 
 */
package com.laborguru.action.demo;

import com.laborguru.action.SpmActionResult;
import com.laborguru.exception.SpmCheckedException;
import com.laborguru.model.Store;

/**
 * @author mariano
 *
 */
public class DemoWizardStepEightPrepareAction extends DemoWizardBaseAction {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public DemoWizardStepEightPrepareAction() {
	}

	/**
	 * @see com.laborguru.action.demo.DemoWizardBaseAction#validateSession()
	 */
	@Override
	public boolean validateSession() {
		return true;
	}

	/**
	 * @see com.laborguru.action.demo.DemoWizardBaseAction#executeStep()
	 */
	@Override
	public String executeStep() throws Exception {
		try {
			//:TODO: Save store & save employee should be a transaction!
			Store store = getDemoStore();
			getEmployee().setStore(store);
			getEmployeeService().save(getEmployee());
			
			setStoreName(store.getName());
			setEmployeeUserName(getEmployee().getUserName());

			getSession().remove(STORE_SESSION_KEY);
			getSession().remove(EMPLOYEE_SESSION_KEY);
			
			return SpmActionResult.STEP_8.getResult();
		} catch(SpmCheckedException ex) {
			addActionError(ex.getErrorMessage());
			return SpmActionResult.INPUT.getResult();
		}
	}
}
