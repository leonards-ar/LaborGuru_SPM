package com.laborguru.service.area.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.laborguru.model.Area;

/**
 *
 * @author <a href="fbarrera@gmail.com">Federico Barrera Oro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class AreaDaoHibernate extends HibernateDaoSupport implements AreaDao {

	public List<Area> findAll(){
		return getHibernateTemplate().loadAll(Area.class);
	}
}
