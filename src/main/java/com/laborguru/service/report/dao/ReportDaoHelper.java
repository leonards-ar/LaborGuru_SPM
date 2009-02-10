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
	public static Map<String, Object> mapHalfHoursReport(Store store, Date date){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("store_id", store.getId());
		map.put("date", date);
		
		map.put("open_hour", getOpenHour(store, date));
		map.put("close_hour", getCloseHour(store, date));
		
		return map;
	}

	/**
	 * @param store
	 * @param position
	 * @param date
	 * @return
	 */
	public static Map<String, Object> mapHalfHoursReportByPosition(Store store, Position position, Date date){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("store_id", store.getId());
		map.put("date", date);
		
		map.put("open_hour", getOpenHour(store, date));
		map.put("close_hour", getCloseHour(store, date));
		map.put("position_id", position.getId());
		
		return map;
	}

	public static Map<String, Object> mapHalfHoursReportByService(Store store, PositionGroup positionGroup, Date date){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("store_id", store.getId());
		map.put("date", date);
		
		map.put("open_hour", getOpenHour(store, date));
		map.put("close_hour", getCloseHour(store, date));
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
	
	public static Date getOpenHour(Store store, Date date) {
		
		Date openHour = store.getOperationTime(CalendarUtils.getDayOfWeek(date)).getOpenHour();
		int extraHour = getExtraHours(store);
		return CalendarUtils.addOrSubstractHours(openHour, (-1)*extraHour);
	}
	
	public static Date getCloseHour(Store store, Date date) {
		Date closeHour = store.getOperationTime(CalendarUtils.getDayOfWeek(date)).getCloseHour();
		int extraHour = getExtraHours(store);
		return CalendarUtils.addOrSubstractHours(closeHour, extraHour);
	}
	
	private static int getExtraHours(Store store){
		return (store.getExtraScheduleHours() != null)?store.getExtraScheduleHours() : DELTA_HOUR; 
	}
	
	
}
