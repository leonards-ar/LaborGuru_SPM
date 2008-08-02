package com.laborguru.action.store;

import java.util.List;

import com.laborguru.model.Position;
import com.laborguru.model.PositionGroup;


/**
 *
 * @author <a href="fbarrera@gmail.com">Federico Barrera Oro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
@SuppressWarnings("serial")
public class ActivitySharingPrepareAction extends StoreUtilizationBaseAction {

	private List<PositionGroup> positionGroups;
	
	public ActivitySharingPrepareAction() {
		
	}

	public void loadExtraInformation() {
		setPositionGroups(getStore().getOrderedPositionGroups());
	}
	/**
	 * Gets the destination Position and Sets a SharedPosition
	 */
	@Override
	protected void setPositionUtilizationValues(Position src, Position dest) {
		dest.setPositionGroup(getPositionGroupById(src.getPositionGroup().getId()));
	}
	
	
	/**
	 * Gets a Position by id.
	 * @param id
	 * @return
	 */
	private PositionGroup getPositionGroupById(Integer id) {
		for(PositionGroup positionGroup: getStore().getPositionGroups()) {
			if(positionGroup.getId().equals(id)) {
				return positionGroup;
			}
		}
		return null;
	}

	/**
	 * @return the positionGroup
	 */
	public List<PositionGroup> getPositionGroups() {
		return positionGroups;
	}

	/**
	 * @param positionGroup the positionGroup to set
	 */
	public void setPositionGroups(List<PositionGroup> positionGroup) {
		this.positionGroups = positionGroup;
	}
	
	
}
