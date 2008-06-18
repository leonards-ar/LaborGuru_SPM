package com.laborguru.service.position;

import java.util.List;

import com.laborguru.model.Position;
import com.laborguru.model.Store;
import com.laborguru.service.position.dao.PositionDao;

/**
 * Spring implementation for Position Service
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class PositionServiceBean implements PositionService {

	private PositionDao positionDao;
	
	public PositionDao getPositionDao() {
		return positionDao;
	}

	public void setPositionDao(PositionDao positionDao) {
		this.positionDao = positionDao;
	}

	/**
	 * Retrieves a list of positions filtered by store
	 * @param store a Store with id populated
	 * @return List of positions
	 * 
	 * @see com.laborguru.service.position.PositionService#getPositionsByStore(com.laborguru.model.Store)
	 */
	public List<Position> getPositionsByStore(Store store) {
		
		if (store == null)
			throw new IllegalArgumentException("Store passed in as parameter is null");

		if (store.getId() == null)
			throw new IllegalArgumentException("Store id  passed in as parameter is null");
		
		return positionDao.getPositionsByStore(store);
	}

}
