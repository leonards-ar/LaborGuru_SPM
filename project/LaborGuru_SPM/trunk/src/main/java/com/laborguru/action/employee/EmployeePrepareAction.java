package com.laborguru.action.employee;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.laborguru.action.SpmAction;
import com.laborguru.action.SpmActionResult;
import com.laborguru.action.utils.ConstantListFactory;
import com.laborguru.action.utils.KeyValuePair;
import com.laborguru.model.Employee;
import com.laborguru.model.Position;
import com.laborguru.model.Store;
import com.laborguru.model.helper.EmployeeTestHelper;
import com.laborguru.service.employee.EmployeeService;
import com.laborguru.service.position.PositionService;
import com.opensymphony.xwork2.Preparable;

/**
 * This action deals with Employee CRUD.
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
@SuppressWarnings("serial")
public class EmployeePrepareAction extends SpmAction implements Preparable, SessionAware {

	private EmployeeService employeeService;
	private PositionService positionService;
	private Map session;
	
	private Employee employee;
	private Employee searchEmployee;
	
	private List<Employee> storeEmployees;
	private List<Position> positions;
	private List<KeyValuePair> statusList;
	
	private Integer employeeId;
	private boolean removePage;

	
	/**
	 * Prepare the data to be used on the edit page
	 * Loads position and status list
	 * @throws Exception
	 */
	public void prepareEdit() throws Exception {
		prepareData();
	}

	/**
	 * Prepare the data to be used on the edit page
	 * Loads position and status list
	 * @throws Exception
	 */
	public void prepareAdd() throws Exception {
		prepareData();
	}
	
	/**
	 * Prepare the data to be used on the edit page
	 * Loads position and status list
	 * @throws Exception
	 */
	public void prepareSave() throws Exception {
		prepareData();
	}

	
	/**
	 * Loads position and status list
	 */
	private void prepareData() {
		Store store = new Store();
		store.setId(0);
		
		this.setPositions(positionService.getPositionsByStore(store));
		this.setStatusList(ConstantListFactory.createStatusList());
	}


	/**
	 * Prepare data to be used in the actions methods defined for this action
	 * @throws Exception
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	public void prepare() throws Exception {
		//It's needed by the Preparable interface, don't comment out or removed
	}
	
	/**
	 * TODO Performs an Employee Search
	 * @return
	 * @throws Exception
	 */
	public String search() throws Exception {
		
		//Getting store &  employee list
		/*		Employee sessionEmployee = (Employee)session.get("spmUSer");				
				if (sessionEmployee != null && sessionEmployee.getStore() != null){
					this.setStoreEmployees(this.employeeService.getEmployeesByStore(sessionEmployee.getStore()));
				}
		*/		this.setStoreEmployees(EmployeeTestHelper.getEmployees("employee", 4));
				
		return SpmActionResult.LIST.getResult();
	}
	
	/**
	 * Retrieves all the employees that belong to the user's store.
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		
		//Getting store &  employee list
/*		Employee sessionEmployee = (Employee)session.get("spmUSer");				
		if (sessionEmployee != null && sessionEmployee.getStore() != null){
			this.setStoreEmployees(this.employeeService.getEmployeesByStore(sessionEmployee.getStore()));
		}
*/		
		this.setStoreEmployees(EmployeeTestHelper.getEmployees("employee", 4));
		
		return SpmActionResult.LIST.getResult();
	}	

	
	/**
	 * Prepares the add page
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		
		//Getting store & getting the employee list
		//storeEmployees = employeeService.getEmployeesByStore(aStore);
		
		return SpmActionResult.EDIT.getResult();
	}

	
	/**
	 * Prepares the edit page
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		
		//Getting employee
/*		Employee tmpEmployee = new Employee();
		tmpEmployee.setId(this.employeeId);
		this.setEmployee(employeeService.getEmployeeById(tmpEmployee));
*/	
		this.setEmployee(EmployeeTestHelper.getEmployee("spm", this.employeeId));		
		
		return SpmActionResult.EDIT.getResult();
	}

	/**
	 * Prepare removes page
	 * @return
	 * @throws Exception
	 */
	public String remove() throws Exception {
		//Getting employee
/*		Employee tmpEmployee = new Employee();
		tmpEmployee.setId(this.employeeId);
		this.setEmployee(employeeService.getEmployeeById(tmpEmployee));
*/	
		this.setRemovePage(true);
		this.setEmployee(EmployeeTestHelper.getEmployee("spm", this.employeeId));
		
		return SpmActionResult.SHOW.getResult();
	}	
	
	/**
	 * Prepares the view page
	 * @return
	 * @throws Exception
	 */
	public String show() throws Exception {		
		//Getting employee
/*		Employee tmpEmployee = new Employee();
		tmpEmployee.setId(this.employeeId);
		this.setEmployee(employeeService.getEmployeeById(tmpEmployee));
*/	
		this.setEmployee(EmployeeTestHelper.getEmployee("spm", this.employeeId));

		return SpmActionResult.SHOW.getResult();
	}
		
	/**
	 * Stores an employee on the DB
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {		

		System.out.println("ADD: "+this.employee.toString());
		//employeeService.save(this.employee);
		
		return SpmActionResult.LISTACTION.getResult();
	}

	/**
	 * Stores an employee on the DB
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {		

		System.out.println("DELETE:"+this.employee.toString());
		//employeeService.delete(this.employee);
		
		return SpmActionResult.LISTACTION.getResult();
	}
	

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public List<Employee> getStoreEmployees() {
		return storeEmployees;
	}

	public void setStoreEmployees(List<Employee> storeEmployees) {
		this.storeEmployees = storeEmployees;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	public List<KeyValuePair> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<KeyValuePair> statusList) {
		this.statusList = statusList;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public boolean isRemovePage() {
		return removePage;
	}

	public void setRemovePage(boolean removePage) {
		this.removePage = removePage;
	}

	public Employee getSearchEmployee() {
		return searchEmployee;
	}

	public void setSearchEmployee(Employee searchEmployee) {
		this.searchEmployee = searchEmployee;
	}

	public PositionService getPositionService() {
		return positionService;
	}

	public void setPositionService(PositionService positionService) {
		this.positionService = positionService;
	}

	public void setSession(Map session) {
		this.session = session;
	}
}
