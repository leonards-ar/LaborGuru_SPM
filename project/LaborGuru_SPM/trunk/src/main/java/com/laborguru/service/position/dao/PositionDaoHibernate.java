package com.laborguru.service.position.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.laborguru.model.Position;
import com.laborguru.model.Store;

/**
 * Hibernate Implementation for position Dao
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class PositionDaoHibernate extends HibernateDaoSupport implements PositionDao {


	/**
	 * Retrieves a list of positions filtered by store
	 * @param store a store with id populated
	 * @return List of positions
	 * @see com.laborguru.service.position.dao.PositionDao#getPositionsByStore(com.laborguru.model.Store)
	 */
	public List<Position> getPositionsByStore(Store store) {
		return (List<Position>)getHibernateTemplate().findByNamedParam(
				"from Position position where position.store.id = :storeId", "storeId",store.getId());
	}

}
