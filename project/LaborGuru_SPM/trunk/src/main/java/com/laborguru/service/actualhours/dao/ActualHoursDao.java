package com.laborguru.service.actualhours.dao;

import com.laborguru.model.ActualHours;

public interface ActualHoursDao {

	ActualHours getActualHoursByDateAndStore(ActualHours objectToPersist);

	ActualHours saveOrUpdate(ActualHours actualHoursAux);
}
