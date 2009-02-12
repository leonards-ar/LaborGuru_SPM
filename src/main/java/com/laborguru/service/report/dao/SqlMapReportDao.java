package com.laborguru.service.report.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.laborguru.model.HistoricSales;
import com.laborguru.model.Position;
import com.laborguru.model.PositionGroup;
import com.laborguru.model.Store;
import com.laborguru.model.report.TotalHour;
import com.laborguru.model.report.TotalHourByPosition;
import com.laborguru.util.CalendarUtils;

public class SqlMapReportDao extends SqlMapClientDaoSupport implements ReportDao {
	private static final Logger log = Logger.getLogger(SqlMapReportDao.class);
	
	public List<TotalHour> getScheduleWeeklyTotalHour(Store store, Date startDate,Date endDate) throws SQLException {
		if(log.isDebugEnabled()) {
			log.debug("getWeeklyTotalHour: before select params: store_id:" + store.getId() + " startDate: " + startDate + " endDate" + endDate);
		}
		
		return getSqlMapClient().queryForList("getScheduleWeeklyTotalHours", ReportDaoHelper.mapTotalHoursReport(store, startDate, endDate));
		
	}
	
	public List<TotalHour> getTargetWeeklyTotalHour(Store store, Date startDate, Date endDate) throws SQLException {
		if(log.isDebugEnabled()) {
			log.debug("getWeeklyTotalHour: before select params: store_id:" + store.getId() + " startDate: " + startDate + " endDate" + endDate);
		}
		
		return getSqlMapClient().queryForList("getTargetWeeklyTotalHours", ReportDaoHelper.mapTotalHoursReport(store, startDate, endDate));
		
	}
	
	public List<TotalHour> getScheduleWeeklyTotalHourByPosition(Store store, Position position, Date startDate, Date endDate) throws SQLException {
		if(log.isDebugEnabled()) {
			log.debug("getScheduleWeeklyTotalHourByPosition: before select params: store_id:" + store.getId() + " position: " + position + " startDate: " + startDate + " endDate: " + endDate);
		}
		
		return getSqlMapClient().queryForList("getScheduleWeeklyTotalHoursByPosition", ReportDaoHelper.mapTotalHoursReportByPosition(store, position, startDate, endDate));
	}
	
	public List<TotalHour> getTargetWeeklyTotalHourByPosition(Store store, Position position, Date startDate, Date endDate) throws SQLException {
		if(log.isDebugEnabled()) {
			log.debug("getTargetWeeklyTotalHourByPosition: before select params: store_id:" + store.getId() + " position: " + position + " startDate: " + startDate + " endDate" + endDate);
		}
		
		return getSqlMapClient().queryForList("getTargetWeeklyTotalHoursByPosition", ReportDaoHelper.mapTotalHoursReportByPosition(store, position, startDate, endDate));
	}

	public List<TotalHour> getScheduleWeeklyTotalHourByService(Store store, PositionGroup positionGroup, Date startDate, Date endDate) throws SQLException {
		if(log.isDebugEnabled()){
			log.debug("getScheduleWeeklyTotalHourByService: before select params: store_id: " + store.getId() + " positon_group_id: " + positionGroup.getId() + " startDate: " + startDate + " endDate: " + endDate);
		}
		
		return getSqlMapClient().queryForList("getScheduleWeeklyTotalHoursByService", ReportDaoHelper.mapTotalHoursReportByService(store, positionGroup, startDate, endDate));
	}
	
	public List<TotalHour> getTargetWeeklyTotalHourByService(Store store, PositionGroup positionGroup, Date startDate, Date endDate) throws SQLException {
		if(log.isDebugEnabled()){
			log.debug("getTargetWeeklyTotalHourByService: before select params: store_id: " + store.getId() + " positon_group_id: " + positionGroup.getId() + " startDate: " + startDate + " endDate: " + endDate);
		}
		
		return getSqlMapClient().queryForList("getTargetWeeklyTotalHoursByService", ReportDaoHelper.mapTotalHoursReportByService(store, positionGroup, startDate, endDate));
	}
	
	public List<TotalHour> getHalfHourlySchedule(Store store, Date date) throws SQLException{
		if(log.isDebugEnabled()){
			log.debug("getHalfHourlySchedule: before select params: store_id: " + store.getId() + " date: " + date);
		}
		
		Date openHour= ReportDaoHelper.getOpenHour(store, date);
		Date closeHour = ReportDaoHelper.getCloseHour(store, date);
		String namedQuery = "getScheduleHalfHourlyTotalHours";
		
		//if close hour is smaller or equals  than open hour, it means that its opens on one day and closes on the following day.		
		if(CalendarUtils.equalsOrSmallerTime(closeHour, openHour)) {
			namedQuery = "getScheduleHalfHourlyTotalHoursSplitInTwoDays";
		}
		
		return getSqlMapClient().queryForList(namedQuery, ReportDaoHelper.mapHalfHoursReport(store, date));
	}
	
	public List<TotalHour> getHalfHourlyMinimumStaffing(Store store, Date date) throws SQLException{
		if(log.isDebugEnabled()){
			log.debug("getHalfHourlyMinimumStaffing: before select params: store_id: " + store.getId() + " date: " + date);
		}

		Date openHour = ReportDaoHelper.getOpenHour(store, date);
		Date closeHour = ReportDaoHelper.getCloseHour(store, date);
		String namedQuery = "getTargetHalfHourlyTotalHours";
		
		//if close hour is smaller or equals  than open hour, it means that its opens on one day and closes on the following day.		
		if(CalendarUtils.equalsOrSmallerTime(closeHour, openHour)) {
			namedQuery = "getTargetHalfHourlyTotalHoursSplitInTwoDays";
		}
		
		return getSqlMapClient().queryForList(namedQuery, ReportDaoHelper.mapHalfHoursReport(store, date));
	}
	
	public List<TotalHour> getHalfHourlyScheduleByPosition(Store store, Position position, Date date) throws SQLException{
		if(log.isDebugEnabled()){
			log.debug("getHalfHourlyScheduleByPosition: before select params: store_id: " + store.getId() + " position_id: " + position.getId() + " date: " + date );
		}

		Date openHour = ReportDaoHelper.getOpenHour(store, date);
		Date closeHour = ReportDaoHelper.getCloseHour(store, date);
		String namedQuery = "getScheduleHalfHourlyTotalHoursByPosition"; 
		
		//if close hour is smaller or equals  than open hour, it means that its opens on one day and closes on the following day. 
		if(CalendarUtils.equalsOrSmallerTime(closeHour, openHour)) {
			namedQuery = "getScheduleHalfHourlyTotalHoursByPositionSplitInTwoDays";
		}
		return getSqlMapClient().queryForList(namedQuery, ReportDaoHelper.mapHalfHoursReportByPosition(store, position, date));
	}
	
	public List<TotalHour> getHalfHourlyMinimumStaffingByPosition(Store store, Position position, Date date) throws SQLException{
		if(log.isDebugEnabled()){
			log.debug("getHalfHourlyTargetByPosition: before select params: store_id: " + store.getId() + " position_id: " + position.getId() + " date: " + date );
		}
		
		Date openHour = ReportDaoHelper.getOpenHour(store, date);
		Date closeHour = ReportDaoHelper.getCloseHour(store, date);
		String namedQuery = "getTargetHalfHourlyTotalHoursByPosition";
		
		//if close hour is smaller or equals  than open hour, it means that its opens on one day and closes on the following day. 
		if(CalendarUtils.equalsOrSmallerTime(closeHour, openHour)) {
			namedQuery = "getTargetHalfHourlyTotalHoursByPositionSplitInTwoDays";
		}
		
		return getSqlMapClient().queryForList(namedQuery, ReportDaoHelper.mapHalfHoursReportByPosition(store, position, date));
	}

	public List<TotalHour> getHalfHourlyScheduleByService(Store store, PositionGroup positionGroup, Date date) throws SQLException{
		if(log.isDebugEnabled()){
			log.debug("getHalfHourlyScheduleByService: before select params: store_id: " + store.getId() + " position_id: " + positionGroup.getId() + " date: " + date );
		}

		Date openHour = ReportDaoHelper.getOpenHour(store, date);
		Date closeHour = ReportDaoHelper.getCloseHour(store, date);
		String namedQuery = "getScheduleHalfHourlyTotalHoursByService";
		
		//if close hour is smaller or equals  than open hour, it means that its opens on one day and closes on the following day. 
		if(CalendarUtils.equalsOrSmallerTime(closeHour, openHour)) {
			namedQuery = "getScheduleHalfHourlyTotalHoursByServiceSplitInTwoDays";
		}
		
		return getSqlMapClient().queryForList(namedQuery, ReportDaoHelper.mapHalfHoursReportByService(store, positionGroup, date));
	}
	
	public List<TotalHour> getHalfHourlyMinimumStaffingByService(Store store, PositionGroup positionGroup, Date date) throws SQLException{
		if(log.isDebugEnabled()){
			log.debug("getHalfHourlyTargetByService: before select params: store_id: " + store.getId() + " position_id: " + positionGroup.getId() + " date: " + date );
		}

		Date openHour = ReportDaoHelper.getOpenHour(store, date);
		Date closeHour = ReportDaoHelper.getCloseHour(store, date);
		String namedQuery = "getTargetHalfHourlyTotalHoursByService";
		
		//if close hour is smaller or equals  than open hour, it means that its opens on one day and closes on the following day. 
		if(CalendarUtils.equalsOrSmallerTime(closeHour, openHour)) {
			namedQuery = "getTargetHalfHourlyTotalHoursByServiceSplitInTwoDays";
		}

		return getSqlMapClient().queryForList(namedQuery, ReportDaoHelper.mapHalfHoursReportByService(store, positionGroup, date));
	}
	
	public List<TotalHourByPosition> getScheduleForecastByPosition(Store store, Date startDate, Date endDate) throws SQLException {
		if(log.isDebugEnabled()) {
			log.debug("getScheduleForecastByPosition: before select params: store_id:" + store.getId() + " startDate: " + startDate + " endDate" + endDate);
		}
		
		return getSqlMapClient().queryForList("getScheduleForecastByPosition", ReportDaoHelper.mapTotalHoursReport(store, startDate, endDate));

	}

	public List<TotalHourByPosition> getTargetForecastByPosition(Store store, Date startDate, Date endDate) throws SQLException {
		if(log.isDebugEnabled()) {
			log.debug("getTargetForecastByPosition: before select params: store_id:" + store.getId() + " startDate: " + startDate + " endDate" + endDate);
		}
		
		return getSqlMapClient().queryForList("getTargetForecastByPosition", ReportDaoHelper.mapTotalHoursReport(store, startDate, endDate));
	}
	
	public List<HistoricSales> getActualSales(Store store, Date startDate, Date endDate) throws SQLException {
		if(log.isDebugEnabled()) {
			log.debug("getActualSales: before select params: store_id: " + store.getId() + " startDate: " + startDate + " endDate: " + endDate);
		}
		
		return getSqlMapClient().queryForList("getActualSales", ReportDaoHelper.mapActualSalesReport(store, startDate, endDate));
	}
	
	public List<TotalHour> getActualHours(Store store, Date startDate, Date endDate) throws SQLException{
		if(log.isDebugEnabled()) {
			log.debug("getActualHours: before select params: store_id: " + store.getId() + " startDate: " + startDate + " endDate: " + endDate);
		}
		
		return getSqlMapClient().queryForList("getActualHours", ReportDaoHelper.mapActualHoursReport(store, startDate, endDate));
	}
	
	public List<TotalHour> getMinimumStaffing(Store store, Date startDate, Date endDate) throws SQLException{
		if(log.isDebugEnabled()) {
			log.debug("getActualHours: before select params: store_id: " + store.getId() + " startDate: " + startDate + " endDate: " + endDate);
		}
		
		return getSqlMapClient().queryForList("getMinimumStaffing", ReportDaoHelper.mapMinimumStaffingReport(store, startDate, endDate));
		
	}
	
	
	
	

}
