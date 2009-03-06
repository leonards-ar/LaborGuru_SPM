package com.laborguru.service.report.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.laborguru.model.Position;
import com.laborguru.model.PositionGroup;
import com.laborguru.model.Store;
import com.laborguru.util.CalendarUtils;

/**
 *
 * @author <a href="fbarreraoro@gmail.com">Federico Barrera Oro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class ReportDaoHelper {

	private static final int DELTA_HOUR = 2;

	/**
	 * Retrieves a Map with the parameters needed to execute the report.
	 * @param store
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static Map<String, Object> mapTotalHoursReport(Store store, Date startDate, Date endDate){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("store_id", store.getId());
		map.put("start_date", startDate);
		map.put("end_date", endDate);
		
		return map;
	}
	
	/**
	 * Retrieves a Map with the parameters needed to execute the report 
	 * @param store
	 * @param date
	 * @return
	 */
	public static Map<String, Object> mapHalfHoursReport(Store store, Date date, Date openHour, Date closeHour){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("store_id", store.getId());
		map.put("date", date);
		map.put("open_hour", openHour);
		map.put("close_hour", closeHour);
		
		return map;
	}

	/**
	 * @param store
	 * @param position
	 * @param date
	 * @return
	 */
	public static Map<String, Object> mapHalfHoursReportByPosition(Store store, Position position, Date date, Date openHour, Date closeHour){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("store_id", store.getId());
		map.put("date", date);
		map.put("open_hour", openHour);
		map.put("close_hour", closeHour);
		map.put("position_id", position.getId());
		
		return map;
	}

	public static Map<String, Object> mapHalfHoursReportByService(Store store, PositionGroup positionGroup, Date date, Date openHour, Date closeHour){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("store_id", store.getId());
		map.put("date", date);
		
		map.put("open_hour", openHour);
		map.put("close_hour", closeHour);
		map.put("position_group_id", positionGroup.getId());
		
		return map;
	}
	
	public static Map<String, Object>mapTotalHoursReportByPosition(Store store, Position position, Date startDate, Date endDate) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("store_id", store.getId());
		map.put("position_id", position.getId());
		map.put("start_date", startDate);
		map.put("end_date", endDate);

		return map;
	}
	
	public static Map<String, Object>mapTotalHoursReportByService(Store store, PositionGroup positionGroup, Date startDate, Date endDate){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("store_id", store.getId());
		map.put("position_group_id", positionGroup.getId());
		map.put("start_date", startDate);
		map.put("end_date", endDate);
		
		return map;
	}

	public static Map<String, Object> mapActualSalesReport(Store store, Date startDate, Date endDate){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("store_id", store.getId());
		map.put("start_date", startDate);
		map.put("end_date", endDate);
		
		return map;
	}
	
	public static Map<String, Object> mapActualHoursReport(Store store, Date startDate, Date endDate){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("store_id", store.getId());
		map.put("start_date", startDate);
		map.put("end_date", endDate);
		
		return map;
	}	

	public static Map<String, Object> mapMinimumStaffingReport(Store store, Date startDate, Date endDate){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("store_id", store.getId());
		map.put("start_date", startDate);
		map.put("end_date", endDate);
		
		return map;
	}
	
	public static Map<String, Object>mapOpenHours(Store store, Date date, Date openHour) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("store_id", store.getId());
		map.put("date", date);
		map.put("open_hour", openHour);
		
		return map;
	}
	
	public static Map<String, Object>mapCloseHours(Store store, Date date, Date closeHour) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("store_id", store.getId());
		map.put("date", date);
		map.put("close_hour", closeHour);
		
		return map;
	}

	public static Map<String, Object>mapServiceHours(Store store, Date date, Date openHour, Date closeHour) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("store_id", store.getId());
		map.put("date", date);
		map.put("open_hour", openHour);
		map.put("close_hour", closeHour);
		
		return map;
	}	
	public static Map<String, Object>mapTargetFixedLaborHours(Store store, Date date) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("store_id", store.getId());
		map.put("date", date);

		return map;
	}
	
	public static Map<String, Object>mapOpenHoursByPosition(Store store, Date date, Date openHour, Position position) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("store_id", store.getId());
		map.put("date", date);
		map.put("open_hour", openHour);
		map.put("position_id", position.getId());
		return map;
	}
	
	public static Map<String, Object>mapCloseHoursByPosition(Store store, Date date, Date closeHour, Position position) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("store_id", store.getId());
		map.put("date", date);
		map.put("close_hour", closeHour);
		map.put("position_id", position.getId());
		
		return map;
	}

	public static Map<String, Object>mapServiceHoursByPosition(Store store, Date date, Date openHour, Date closeHour, Position position) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("store_id", store.getId());
		map.put("date", date);
		map.put("open_hour", openHour);
		map.put("close_hour", closeHour);
		map.put("position_id", position.getId());
		
		return map;
	}	
	public static Map<String, Object>mapTargetFixedLaborHoursByPosition(Store store, Date date, Position position) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("store_id", store.getId());
		map.put("date", date);
		map.put("position_id", position.getId());
		
		return map;
	}	

	public static Map<String, Object>mapOpenHoursByService(Store store, Date date, Date openHour, PositionGroup positionGroup) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("store_id", store.getId());
		map.put("date", date);
		map.put("open_hour", openHour);
		map.put("position_group_id", positionGroup.getId());
		return map;
	}
	
	public static Map<String, Object>mapCloseHoursByService(Store store, Date date, Date closeHour, PositionGroup positionGroup) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("store_id", store.getId());
		map.put("date", date);
		map.put("close_hour", closeHour);
		map.put("position_group_id", positionGroup.getId());
		
		return map;
	}

	public static Map<String, Object>mapServiceHoursByService(Store store, Date date, Date openHour, Date closeHour, PositionGroup positionGroup) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("store_id", store.getId());
		map.put("date", date);
		map.put("open_hour", openHour);
		map.put("close_hour", closeHour);
		map.put("position_group_id", positionGroup.getId());
		
		return map;
	}	
	public static Map<String, Object>mapTargetFixedLaborHoursByService(Store store, Date date, PositionGroup positionGroup) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("store_id", store.getId());
		map.put("date", date);
		map.put("position_group_id", positionGroup.getId());
		
		return map;
	}
	
}
