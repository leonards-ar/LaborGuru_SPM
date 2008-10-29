/*
 * File name: StaffingDaoHibernate.java
 * Creation date: 19/10/2008 16:57:47
 * Copyright Mindpool
 */
package com.laborguru.service.staffing.dao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.laborguru.model.DailyStaffing;
import com.laborguru.model.Position;


/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class StaffingDaoHibernate extends HibernateDaoSupport implements StaffingDao {
	private static final Logger log = Logger.getLogger(StaffingDaoHibernate.class);
	
	/**
	 * 
	 */
	public StaffingDaoHibernate() {
	}

	/**
	 * 
	 * @param position
	 * @param date
	 * @return
	 * @see com.laborguru.service.staffing.dao.StaffingDao#getDailyStaffingByDate(com.laborguru.model.Position, java.util.Date)
	 */
	public DailyStaffing getDailyStaffingByDate(Position position, Date date) {
		if(log.isDebugEnabled()) {
			log.debug("Searching staffing for day [" + date + "] and for position [" + position + "]");
		}
		
		List<DailyStaffing> staffing = (List<DailyStaffing>) getHibernateTemplate().findByNamedParam("from DailyStaffing staff where staff.position.id = :positionId and staff.date = :date", new String[]{"positionId", "date"}, new Object[] {position.getId(),date});
		
		if(log.isDebugEnabled()) {
			log.debug("Found [" + (staffing != null ? staffing.size() : "null") + "] staffing for day [" + date + "] and position [" + position + "]");
		}
		return staffing != null && staffing.size() > 0 ? staffing.get(0) : null;
	}

	/**
	 * 
	 * @param dailyStaffing
	 * @return
	 * @see com.laborguru.service.staffing.dao.StaffingDao#save(com.laborguru.model.DailyStaffing)
	 */
	public DailyStaffing save(DailyStaffing dailyStaffing) {
		if (dailyStaffing == null){
			throw new IllegalArgumentException("The dailyStaffing passed as parameter is null");
		}
		
		getHibernateTemplate().saveOrUpdate(dailyStaffing);
		
		return dailyStaffing;
	}

}