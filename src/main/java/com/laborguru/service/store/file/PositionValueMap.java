package com.laborguru.service.store.file;

import java.util.HashMap;
import java.util.Map;

public class PositionValueMap {

	private Map<String, NumberMap> positionData;

	public PositionValueMap(){		
		this.positionData = new HashMap<String,NumberMap>();		
	}
	
	public void put(String position, String data, Number value){
		
		if ((position == null) || (data == null)){
			throw new IllegalArgumentException("Parameters passed as parameter cannot be null");
		}
		
		NumberMap numberMap = this.positionData.get(position);
		
		if (numberMap == null){
			numberMap = new NumberMap();
			this.positionData.put(position, numberMap);
		}
		
		numberMap.put(data, value);
	}
	
	public Number get(String position, String data){
		
		if ((position == null) || (data == null)){
			throw new IllegalArgumentException("Parameters passed as parameter cannot be null");
		}
		
		NumberMap numberMap = this.positionData.get(position);
		
		if (numberMap != null)
			return numberMap.get(data);
		
		return null;
	}
	
}
