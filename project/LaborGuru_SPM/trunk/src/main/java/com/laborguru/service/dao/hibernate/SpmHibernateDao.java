package com.laborguru.service.dao.hibernate;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Hibernate DAO Supperclass
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class SpmHibernateDao extends HibernateDaoSupport {

	/**
	 * 
	 */
	public SpmHibernateDao() {
	}
	
	/**
	 * This method checks if an Object value must be included
	 * in a filter.
	 * @param value The value to check.
	 * @return If the value must be taken into account in the filter
	 */
	protected boolean includeInFilter(Object value) {
		return value != null;
	}
	
	/**
	 * This method checks if a String value must be included
	 * in a filter.
	 * @param value The value to check.
	 * @return If the value must be taken into account in the filter
	 */
	protected boolean includeInFilter(String value) {
		return includeInFilter((Object)value) && value.length() > 0;
	}

	/**
	 * This method checks if a String value must be included
	 * in a filter.
	 * @param value The value to check.
	 * @return If the value must be taken into account in the filter
	 */
	protected boolean includeInFilter(Integer value) {
		return includeInFilter((Object)value) && value.intValue() >= 0;
	}		

}
