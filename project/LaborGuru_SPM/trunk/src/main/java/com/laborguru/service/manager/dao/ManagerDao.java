package com.laborguru.service.manager.dao;

import java.util.List;

import com.laborguru.model.Area;
import com.laborguru.model.AreaUser;
import com.laborguru.model.Customer;
import com.laborguru.model.CustomerUser;
import com.laborguru.model.Manager;
import com.laborguru.model.Region;
import com.laborguru.model.RegionalUser;

/**
 *
 * @author <a href="fbarreraoro@gmail.com">Federico Barrera Oro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public interface ManagerDao {

	CustomerUser save(CustomerUser user);
	
	RegionalUser save(RegionalUser user);
	
	AreaUser save(AreaUser user);

	Manager save(Manager manager);
	
	List<Manager> getCustomerUsers(Customer customer);
	
	List<Manager> getRegionalUsers(Region region);
	
	List<Manager> getAreaUsers(Area area);
	
	Manager getManagerById(Manager manager);
	
	void delete(Manager manager);

}
