package com.laborguru.service.actualhours;

import com.laborguru.model.ActualHours;
import com.laborguru.service.actualhours.dao.ActualHoursDao;

public class ActualHoursServiceBean implements ActualHoursService{

	private ActualHoursDao actualHoursDao;
	
	public ActualHours saveOrUpdate(ActualHours objectToPersist){
		
		if (objectToPersist == null){
			throw new IllegalArgumentException("Object passed as parameter is null");
		}
		
		ActualHours actualHoursAux = actualHoursDao.getActualHoursByDateAndStore(objectToPersist);
		ActualHours actualHoursResponse = null;
		
		if (actualHoursAux != null){
			actualHoursAux.setHours(objectToPersist.getHours());
			actualHoursResponse = actualHoursDao.saveOrUpdate(actualHoursAux);
		}else{
			actualHoursResponse = actualHoursDao.saveOrUpdate(objectToPersist);
		}
		
		return actualHoursResponse;
	}

	/**
	 * @param actualHoursDao the actualHoursDao to set
	 */
	public void setActualHoursDao(ActualHoursDao actualHoursDao) {
		this.actualHoursDao = actualHoursDao;
	}

}
