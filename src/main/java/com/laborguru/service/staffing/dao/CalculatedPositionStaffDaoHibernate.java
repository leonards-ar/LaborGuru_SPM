/*
 * File name: CalculatedPositionStaffDaoHibernate.java
 * Creation date: 19/10/2008 16:57:47
 * Copyright Mindpool
 */
package com.laborguru.service.staffing.dao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.laborguru.model.DailyCalculatedPositionStaff;
import com.laborguru.model.Position;


/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class CalculatedPositionStaffDaoHibernate extends HibernateDaoSupport implements CalculatedPositionStaffDao {
	private static final Logger log = Logger.getLogger(CalculatedPositionStaffDaoHibernate.class);
	
	/**
	 * 
	 */
	public CalculatedPositionStaffDaoHibernate() {
	}

	/**
	 * 
	 * @param position
	 * @param date
	 * @return
	 * @see com.laborguru.service.staffing.dao.CalculatedPositionStaffDao#getDailyCalculatedPositionStaffingByDate(com.laborguru.model.Position, java.util.Date)
	 */
	public DailyCalculatedPositionStaff getDailyCalculatedPositionStaffingByDate(Position position, Date date) {
		if(log.isDebugEnabled()) {
			log.debug("Searching calculated position staff for day [" + date + "] for position [" + position + "]");
		}
		
		List<DailyCalculatedPositionStaff> staffing = (List<DailyCalculatedPositionStaff>) getHibernateTemplate().findByNamedParam("from DailyCalculatedPositionStaff staff where staff.position.id = :positionId and staff.date = :date", new String[]{"positionId", "date"}, new Object[] {position.getId(),date});
		
		if(log.isDebugEnabled()) {
			log.debug("Found [" + (staffing != null ? staffing.size() : "null") + "] calculated position staff for day [" + date + "]");
		}
		return staffing != null && staffing.size() > 0 ? staffing.get(0) : null;
	}

	/**
	 * 
	 * @param dailyCalulatedPositionStaff
	 * @return
	 * @see com.laborguru.service.staffing.dao.CalculatedPositionStaffDao#save(com.laborguru.model.DailyCalculatedPositionStaff)
	 */
	public DailyCalculatedPositionStaff save(DailyCalculatedPositionStaff dailyCalulatedPositionStaff) {
		if (dailyCalulatedPositionStaff == null){
			throw new IllegalArgumentException("The dailyCalulatedPositionStaff passed as parameter is null");
		}
		
		getHibernateTemplate().saveOrUpdate(dailyCalulatedPositionStaff);
		
		return dailyCalulatedPositionStaff;
	}

}
