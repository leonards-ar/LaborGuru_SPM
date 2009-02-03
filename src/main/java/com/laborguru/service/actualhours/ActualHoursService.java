package com.laborguru.service.actualhours;

import com.laborguru.model.ActualHours;
import com.laborguru.service.actualhours.dao.ActualHoursDao;

public interface ActualHoursService {

	ActualHours saveOrUpdate(ActualHours objectToPersist);
	
	void setActualHoursDao(ActualHoursDao actualHoursDao);

}
