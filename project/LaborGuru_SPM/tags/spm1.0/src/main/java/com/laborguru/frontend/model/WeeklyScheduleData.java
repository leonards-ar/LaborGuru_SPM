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
	
	private Map<Integer, List<WeeklyScheduleRow>> indexedScheduleData = null;

	private List<WeeklyScheduleRow> scheduleData = null;
	
	/**
	 * 
	 */
	public WeeklyScheduleData() {
	}

	/**
	 * @return the indexedScheduleData
	 */
	private Map<Integer, List<WeeklyScheduleRow>> getIndexedScheduleData() {
		if(indexedScheduleData == null) {
			setIndexedScheduleData(buildIndexedScheduleData());
		}
		return indexedScheduleData;
	}

	/**
	 * 
	 */
	private Map<Integer, List<WeeklyScheduleRow>> buildIndexedScheduleData() {
		Map<Integer, List<WeeklyScheduleRow>> indexedData = new HashMap<Integer, List<WeeklyScheduleRow>>();
		Integer aGroupById = null;
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
	public int getCountFor(Integer groupById) {
		List<WeeklyScheduleRow> data = getScheduleDataFor(groupById);
		return data != null ? data.size() : 0;
	}
	
	/**
	 * @param indexedScheduleData the indexedScheduleData to set
	 */
	private void setIndexedScheduleData(Map<Integer, List<WeeklyScheduleRow>> indexedScheduleData) {
		this.indexedScheduleData = indexedScheduleData;
	}	
	
	/**
	 * 
	 * @param groupById
	 * @param row
	 */
	public void addScheduleRow(Integer groupById, WeeklyScheduleRow row) {
		List<WeeklyScheduleRow> data = getScheduleDataFor(groupById);
		if(data == null) {
			data = new ArrayList<WeeklyScheduleRow>();
			getIndexedScheduleData().put(groupById, data);
		}
		data.add(row);
		//:TODO: Sort???
		resetScheduleData();
	}
	
	/**
	 * 
	 * @param groupById
	 * @return
	 */
	public List<WeeklyScheduleRow> getScheduleDataFor(Integer groupById) {
		return getIndexedScheduleData().get(groupById);
	}
	
	/**
	 * 
	 * @param groupByIds
	 * @return
	 */
	public List<WeeklyScheduleRow> getScheduleDataFor(List<Integer> groupByIds) {
		List<WeeklyScheduleRow> data = new ArrayList<WeeklyScheduleRow>();
		List<WeeklyScheduleRow> dataForId;
		for(Integer anId : groupByIds) {
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
			if(l != null && l.size() > 0) {
				l.get(0).setFirstRow(true);
				for(int i = 1; i < l.size(); i++) {
					l.get(i).setFirstRow(false);
				}
				data.addAll(l);
			}
		}
		return data;
	}
	
	/**
	 * @return the scheduleData
	 */
	public List<WeeklyScheduleRow> getScheduleData() {
		if((scheduleData == null || scheduleData.isEmpty()) && (indexedScheduleData != null && !indexedScheduleData.isEmpty())) {
			setScheduleData(getAllScheduleData());
		} else if(scheduleData == null) {
			setScheduleData(new ArrayList<WeeklyScheduleRow>());
		}
		return scheduleData;
	}

	/**
	 * @param scheduleData the scheduleData to set
	 */
	public void setScheduleData(List<WeeklyScheduleRow> scheduleData) {
		this.scheduleData = scheduleData;
	}	
	
	/**
	 * 
	 */
	private void resetScheduleData() {
		setScheduleData(null);
	}
	
	/**
	 * 
	 * @param employeeId
	 * @return
	 */
	public List<WeeklyScheduleRow> getEmployeeScheduleData(Integer employeeId) {
		List<WeeklyScheduleRow> employeeData = new ArrayList<WeeklyScheduleRow>();
		
		if(employeeId != null) {
			for(WeeklyScheduleRow row : getScheduleData()) {
				if(employeeId.equals(row.getEmployeeId())) {
					employeeData.add(row);
				}
			}
		}
		
		return employeeData;
	}
	
	/**
	 * 
	 * @param employeeId
	 * @param dayIndex
	 * @return
	 */
	public List<WeeklyScheduleDailyEntry> getEmployeeDailyScheduleData(Integer employeeId, int dayIndex) {
		List<WeeklyScheduleDailyEntry> employeeData = new ArrayList<WeeklyScheduleDailyEntry>();
		
		for(WeeklyScheduleRow row : getEmployeeScheduleData(employeeId)) {
			if(dayIndex >=0 && dayIndex < row.getWeeklySchedule().size() && row.getWeeklySchedule().get(dayIndex).isShift()) {
				employeeData.add(row.getWeeklySchedule().get(dayIndex));
			}
		}
		
		return employeeData;
	}
}
