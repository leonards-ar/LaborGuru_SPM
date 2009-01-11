package com.laborguru.service.store.file;

import java.util.HashMap;
import java.util.Map;

public class NumberMap {

	private Map<String, Number> dataMap;

	public NumberMap(){
		this.dataMap = new HashMap<String,Number>();
	}
	
	public void put(String key, Number value){
		dataMap.put(key, value);
	}
	
	public Number get(String key){
		return dataMap.get(key);
	}
}
