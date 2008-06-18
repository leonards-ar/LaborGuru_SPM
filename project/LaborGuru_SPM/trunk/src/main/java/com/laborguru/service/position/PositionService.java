package com.laborguru.service.position;

import java.util.List;

import com.laborguru.model.Position;
import com.laborguru.model.Store;
import com.laborguru.service.Service;

/**
 * Position Service
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public interface PositionService extends Service {

	/**
	 * Retrieves a list of positions filtered by store
	 * @param store a Store with id populated
	 * @return List of positions
	 */
	List<Position> getPositionsByStore(Store store);
}
