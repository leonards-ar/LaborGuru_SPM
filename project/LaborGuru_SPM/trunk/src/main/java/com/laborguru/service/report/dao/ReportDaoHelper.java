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
		map.put("open_hour", store.getOperationTime(CalendarUtils.getDayOfWeek(date)).getOpenHour());
		map.put("close_hour", store.getOperationTime(CalendarUtils.getDayOfWeek(date)).getCloseHour());
		
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
	
}