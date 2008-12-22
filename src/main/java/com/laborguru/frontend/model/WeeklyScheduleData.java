/*
 * File name: WeeklyScheduleData.java
 * Creation date: 21/12/2008 10:31:57
 * Copyright Mindpool
 */
package com.laborguru.frontend.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class WeeklyScheduleData implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7341616935357394374L;
	
	private Map<Object, List<WeeklyScheduleRow>> indexedScheduleData = null;

	private List<WeeklyScheduleRow> scheduleData = null;
	
	/**
	 * 
	 */
	public WeeklyScheduleData() {
	}

	/**
	 * @return the indexedScheduleData
	 */
	private Map<Object, List<WeeklyScheduleRow>> getIndexedScheduleData() {
		if(indexedScheduleData == null) {
			setIndexedScheduleData(buildIndexedScheduleData());
		}
		return indexedScheduleData;
	}

	/**
	 * 
	 */
	private Map<Object, List<WeeklyScheduleRow>> buildIndexedScheduleData() {
		Map<Object, List<WeeklyScheduleRow>> indexedData = new HashMap<Object, List<WeeklyScheduleRow>>();
		Object aGroupById = null;
		List<WeeklyScheduleRow> data = null;
		
		for(WeeklyScheduleRow aRow : getScheduleData()) {
			if(aGroupById == null || !aGroupById.equals(aRow.getGroupById())) {
				if(data != null && aGroupById != null) {
					indexedData.put(aGroupById, data);
					data = null;
				}
				aGroupById = aRow.getGroupById();
				data = new ArrayList<WeeklyScheduleRow>();
			}
			data.add(aRow);
		}
		
		if(data != null && aGroupById != null) {
			indexedData.put(aGroupById, data);
			data = null;
		}		
		
		return indexedData;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return getIndexedScheduleData().isEmpty();
	}
	
	/**
	 * 
	 * @param groupById
	 * @return
	 */
	public int getCountFor(Object groupById) {
		List<WeeklyScheduleRow> data = getScheduleDataFor(groupById);
		return data != null ? data.size() : 0;
	}
	
	/**
	 * @param indexedScheduleData the indexedScheduleData to set
	 */
	private void setIndexedScheduleData(Map<Object, List<WeeklyScheduleRow>> indexedScheduleData) {
		this.indexedScheduleData = indexedScheduleData;
	}	
	
	/**
	 * 
	 * @param groupById
	 * @param row
	 */
	public void addScheduleRow(Object groupById, WeeklyScheduleRow row) {
		List<WeeklyScheduleRow> data = getScheduleDataFor(groupById);
		if(data == null) {
			data = new ArrayList<WeeklyScheduleRow>();
			getIndexedScheduleData().put(groupById, data);
		}
		data.add(row);
		//:TODO: Sort???
	}
	
	/**
	 * 
	 * @param groupById
	 * @return
	 */
	public List<WeeklyScheduleRow> getScheduleDataFor(Object groupById) {
		return getIndexedScheduleData().get(groupById);
	}
	
	/**
	 * 
	 * @param groupByIds
	 * @return
	 */
	public List<WeeklyScheduleRow> getScheduleDataFor(List<Object> groupByIds) {
		List<WeeklyScheduleRow> data = new ArrayList<WeeklyScheduleRow>();
		List<WeeklyScheduleRow> dataForId;
		for(Object anId : groupByIds) {
			dataForId = getScheduleDataFor(anId);
			if(dataForId != null) {
				data.addAll(dataForId);
			}
		}
		setScheduleData(data);
		return data;
	}

	/**
	 * 
	 * @return
	 */
	public List<WeeklyScheduleRow> getAllScheduleData() {
		List<WeeklyScheduleRow> data = new ArrayList<WeeklyScheduleRow>();
		for(List<WeeklyScheduleRow> l : getIndexedScheduleData().values()) {
			data.addAll(l);
		}
		return data;
	}
	
	/**
	 * @return the scheduleData
	 */
	public List<WeeklyScheduleRow> getScheduleData() {
		if(scheduleData == null || scheduleData.isEmpty()) {
			setScheduleData(getAllScheduleData());
		}
		return scheduleData;
	}

	/**
	 * @param scheduleData the scheduleData to set
	 */
	public void setScheduleData(List<WeeklyScheduleRow> scheduleData) {
		this.scheduleData = scheduleData;
	}	
}
