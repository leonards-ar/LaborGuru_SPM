package com.laborguru.model.helper;

import com.laborguru.model.Position;

public class PositionTestHelper {

	public static Position getPosition(String param){
		return getPosition(param, 0);
	}

	public static Position getPosition(String param, int index) {
		Position position = new Position();
		
		position.setId(index);
		position.setName("name_"+param+"_"+index);
		
		return position;
	}
}
