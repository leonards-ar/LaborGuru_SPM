package com.laborguru.service.actualhours.dao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.laborguru.model.ActualHours;
import com.laborguru.service.dao.hibernate.SpmHibernateDao;

public class ActualHoursDaoHibernate extends SpmHibernateDao implements ActualHoursDao {

	private static final Logger log = Logger.getLogger(ActualHoursDaoHibernate.class);
	private static final String ACTUAL_HOURS_NULL = "The actual hours object passed in as parameter is null";
	private static final String ACTUAL_HOURS_SEARCH_PARAMETERS_NULL = "The search parameters (date, store id) passed in as parameter are null";

	
	public ActualHours saveOrUpdate(ActualHours ah) {
		
		if (ah == null){
			log.error(ACTUAL_HOURS_NULL);
			throw new IllegalArgumentException(ACTUAL_HOURS_NULL);			
		}
		
		getHibernateTemplate().saveOrUpdate(ah);
		
		return ah;
	}
	

	public ActualHours getActualHoursByDateAndStore(ActualHours ah) {

		if (ah == null){
			log.error(ACTUAL_HOURS_NULL);
			throw new IllegalArgumentException(ACTUAL_HOURS_NULL);			
		}

		
		Date date = ah.getDate();
		Integer storeId = ah.getStoreId();
		
		if (date == null || storeId == null){
			log.error(ACTUAL_HOURS_SEARCH_PARAMETERS_NULL);
			throw new IllegalArgumentException(ACTUAL_HOURS_SEARCH_PARAMETERS_NULL);
		}
		
		List<ActualHours> actualHours = (List<ActualHours>)getHibernateTemplate().findByNamedParam(
				"from ActualHours actualHours where actualHours.store.id = :storeId and actualHours.date = :date", new String []{"storeId", "date"}, 
				new Object[]{storeId, date});
		
		if (actualHours.isEmpty())
			return null;
		
		return actualHours.get(0);
	}
}
