package com.laborguru.service.region.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.laborguru.model.Region;


/**
 *
 * @author <a href="fbarrera@gmail.com">Federico Barrera Oro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class RegionDaoHibernate extends HibernateDaoSupport implements RegionDao {

	public List<Region> findAll() {
		return getHibernateTemplate().loadAll(Region.class);
	}

}
