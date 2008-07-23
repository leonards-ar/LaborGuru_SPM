package com.laborguru.action.store;

import com.laborguru.model.Position;


/**
 *
 * @author <a href="fbarrera@gmail.com">Federico Barrera Oro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
@SuppressWarnings("serial")
public class ActivitySharingPrepareAction extends StoreUtilizationBaseAction {

	public ActivitySharingPrepareAction() {
		
	}

	/**
	 * Gets the destination Position and Sets a SharedPosition
	 */
	@Override
	protected void setPositionUtilizationValues(Position src, Position dest) {
		dest.setSharedPosition(getPositionById(src.getSharedPosition().getId()));
		src.setSharedPosition(dest);
	}
	
	
	/**
	 * Gets a Position by id.
	 * @param id
	 * @return
	 */
	private Position getPositionById(Integer id) {
		for(Position position: getStore().getPositions()) {
			if(position.getId().equals(id)) {
				return position;
			}
		}
		return null;
	}
}
