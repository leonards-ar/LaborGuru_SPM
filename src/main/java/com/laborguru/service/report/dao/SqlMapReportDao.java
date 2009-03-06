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
import com.laborguru.model.report.FixedLaborHours;
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
	
	public List<TotalHour> getHalfHourlySchedule(Store store, Date date, Date startHour, Date endHour) throws SQLException{
		if(log.isDebugEnabled()){
			log.debug("getHalfHourlySchedule: before select params: store_id: " + store.getId() + " date: " + date + " startHour: " + CalendarUtils.removeDateFromTime(startHour) + "endHour: " + CalendarUtils.removeDateFromTime(endHour));
		}
		
		String namedQuery = "getScheduleHalfHourlyTotalHours";
		
		//if close hour is smaller or equals  than open hour, it means that its opens on one day and closes on the following day.		
		if(isNextDay(startHour, endHour)) {
			namedQuery = "getScheduleHalfHourlyTotalHoursSplitInTwoDays";
		}
		
		return getSqlMapClient().queryForList(namedQuery, ReportDaoHelper.mapHalfHoursReport(store, date, startHour, endHour));
	}
	
	public List<TotalHour> getHalfHourlyMinimumStaffing(Store store, Date date) throws SQLException{
		if(log.isDebugEnabled()){
			log.debug("getHalfHourlyMinimumStaffing: before select params: store_id: " + store.getId() + " date: " + date);
		}

		String namedQuery = "getTargetHalfHourlyTotalHours";
		
		//if close hour is smaller or equals  than open hour, it means that its opens on one day and closes on the following day.
		Date openHour = store.getOperationTime(CalendarUtils.getDayOfWeek(date)).getOpenHour();
		Date closeHour = store.getOperationTime(CalendarUtils.getDayOfWeek(date)).getCloseHour();
		if(isNextDay(openHour, closeHour)) {
			namedQuery = "getTargetHalfHourlyTotalHoursSplitInTwoDays";
		}
		
		return getSqlMapClient().queryForList(namedQuery, ReportDaoHelper.mapHalfHoursReport(store, date, openHour, closeHour));
	}
	
	public List<TotalHour> getHalfHourlyScheduleByPosition(Store store, Position position, Date date, Date startHour, Date endHour) throws SQLException{
		if(log.isDebugEnabled()){
			log.debug("getHalfHourlyScheduleByPosition: before select params: store_id: " + store.getId() + " position_id: " + position.getId() + " date: " + date + " startHour: " + CalendarUtils.removeDateFromTime(startHour) + "endHour: " + CalendarUtils.removeDateFromTime(endHour));
		}

		String namedQuery = "getScheduleHalfHourlyTotalHoursByPosition"; 
		
		//if close hour is smaller or equals  than open hour, it means that its opens on one day and closes on the following day. 
		if(isNextDay(startHour, endHour)) {
			namedQuery = "getScheduleHalfHourlyTotalHoursByPositionSplitInTwoDays";
		}
		return getSqlMapClient().queryForList(namedQuery, ReportDaoHelper.mapHalfHoursReportByPosition(store, position, date, startHour, endHour));
	}
	
	public List<TotalHour> getHalfHourlyMinimumStaffingByPosition(Store store, Position position, Date date) throws SQLException{
		if(log.isDebugEnabled()){
			log.debug("getHalfHourlyTargetByPosition: before select params: store_id: " + store.getId() + " position_id: " + position.getId() + " date: " + date);
		}
		
		String namedQuery = "getTargetHalfHourlyTotalHoursByPosition";
		
		Date openHour = store.getOperationTime(CalendarUtils.getDayOfWeek(date)).getOpenHour();
		Date closeHour = store.getOperationTime(CalendarUtils.getDayOfWeek(date)).getCloseHour();

		//if close hour is smaller or equals  than open hour, it means that its opens on one day and closes on the following day. 
		if(isNextDay(openHour, closeHour)) {
			namedQuery = "getTargetHalfHourlyTotalHoursByPositionSplitInTwoDays";
		}
		
		return getSqlMapClient().queryForList(namedQuery, ReportDaoHelper.mapHalfHoursReportByPosition(store, position, date, openHour, closeHour));
	}

	public List<TotalHour> getHalfHourlyScheduleByService(Store store, PositionGroup positionGroup, Date date, Date startHour, Date endHour) throws SQLException{
		if(log.isDebugEnabled()){
			log.debug("getHalfHourlyScheduleByService: before select params: store_id: " + store.getId() + " positionGroup_id: " + positionGroup.getId() + " date: " + date + " startHour: " + CalendarUtils.removeDateFromTime(startHour) + "endHour: " + CalendarUtils.removeDateFromTime(endHour));
		}

		String namedQuery = "getScheduleHalfHourlyTotalHoursByService";
		
		if(isNextDay(startHour, endHour)) {
			namedQuery = "getScheduleHalfHourlyTotalHoursByServiceSplitInTwoDays";
		}
		
		return getSqlMapClient().queryForList(namedQuery, ReportDaoHelper.mapHalfHoursReportByService(store, positionGroup, date, startHour, endHour));
	}
	
	public List<TotalHour> getHalfHourlyMinimumStaffingByService(Store store, PositionGroup positionGroup, Date date) throws SQLException{
		if(log.isDebugEnabled()){
			log.debug("getHalfHourlyTargetByService: before select params: store_id: " + store.getId() + " position_id: " + positionGroup.getId() + " date: " + date);
		}

		String namedQuery = "getTargetHalfHourlyTotalHoursByService";
		
		//if close hour is smaller or equals  than open hour, it means that its opens on one day and closes on the following day.
		Date openHour = store.getOperationTime(CalendarUtils.getDayOfWeek(date)).getOpenHour();
		Date closeHour = store.getOperationTime(CalendarUtils.getDayOfWeek(date)).getCloseHour();

		if(isNextDay(openHour, closeHour)) {
			namedQuery = "getTargetHalfHourlyTotalHoursByServiceSplitInTwoDays";
		}

		return getSqlMapClient().queryForList(namedQuery, ReportDaoHelper.mapHalfHoursReportByService(store, positionGroup, date, openHour, closeHour));
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
	
	public FixedLaborHours getScheduleFixedLaborHours(Store store, Date date) throws SQLException {
		Date openHour = store.getOperationTime(CalendarUtils.getDayOfWeek(date)).getOpenHour();
		Date closeHour = store.getOperationTime(CalendarUtils.getDayOfWeek(date)).getCloseHour();

		// If the store opens one day and closes the other day the closeHour has to be '1970-01-02'
		if(isNextDay(openHour, closeHour)){
			closeHour = CalendarUtils.addOrSubstractDays(closeHour, 1);
		}

		if(log.isDebugEnabled()) {
			log.debug("getScheduleFixedLaborHours: before select params: store_id: " + store.getId() + " date: " + date + " open_hour: " + openHour + " close_hour: " + closeHour);
		}

		FixedLaborHours fixedLaborHours = new FixedLaborHours();
		
		fixedLaborHours.setServiceHours((Double)getSqlMapClient().queryForObject("getScheduleServiceHour", ReportDaoHelper.mapServiceHours(store, date, openHour, closeHour)));
		
		fixedLaborHours.setOpenHours((Double)getSqlMapClient().queryForObject("getScheduleOpenHour", ReportDaoHelper.mapOpenHours(store, date, openHour)));
		
		fixedLaborHours.setCloseHours((Double)getSqlMapClient().queryForObject("getScheduleCloseHour", ReportDaoHelper.mapCloseHours(store, date, closeHour)));

		//It's always zero.
		fixedLaborHours.setFlexHours(0.0);
		
		return fixedLaborHours;
	}
	
	public FixedLaborHours getTargetFixedLaborHours(Store store, Date date) throws SQLException {
		if(log.isDebugEnabled()) {
			log.debug("getScheduleFixedLaborHours: before select params: store_id: " + store.getId() + " date: " + date);
		}
		
		return (FixedLaborHours)getSqlMapClient().queryForObject("getTargetFixedLaborHours", ReportDaoHelper.mapTargetFixedLaborHours(store, date));
	}
	
	public FixedLaborHours getScheduleFixedLaborHoursByPosition(Store store, Date date, Position position) throws SQLException {
		Date openHour = store.getOperationTime(CalendarUtils.getDayOfWeek(date)).getOpenHour();
		Date closeHour = store.getOperationTime(CalendarUtils.getDayOfWeek(date)).getCloseHour();
		
		// If the store opens one day and closes the other day the closeHour has to be '1970-01-02'
		if(isNextDay(openHour, closeHour)){
			closeHour = CalendarUtils.addOrSubstractDays(closeHour, 1);
		}

		if(log.isDebugEnabled()) {
			log.debug("getScheduleFixedLaborHoursByPosition: before select params: store_id: " + store.getId() + " date: " + date + " open_hour: " + openHour + " close_hour: " + closeHour + " position: " + position);
		}

		FixedLaborHours fixedLaborHours = new FixedLaborHours();
		
		fixedLaborHours.setServiceHours((Double)getSqlMapClient().queryForObject("getScheduleServiceHourByPosition", ReportDaoHelper.mapServiceHoursByPosition(store, date, openHour, closeHour, position)));
		
		fixedLaborHours.setOpenHours((Double)getSqlMapClient().queryForObject("getScheduleOpenHourByPosition", ReportDaoHelper.mapOpenHoursByPosition(store, date, openHour, position)));
		
		fixedLaborHours.setCloseHours((Double)getSqlMapClient().queryForObject("getScheduleCloseHourByPosition", ReportDaoHelper.mapCloseHoursByPosition(store, date, closeHour, position)));

		//It's always zero.
		fixedLaborHours.setFlexHours(0.0);
		
		return fixedLaborHours;
	}
	
	public FixedLaborHours getTargetFixedLaborHoursByPosition(Store store, Date date, Position position) throws SQLException {
		if(log.isDebugEnabled()) {
			log.debug("getTargetFixedLaborHoursByPosition: before select params: store_id: " + store.getId() + " date: " + date + " position: " + position);
		}
		
		return (FixedLaborHours)getSqlMapClient().queryForObject("getTargetFixedLaborHoursByPosition", ReportDaoHelper.mapTargetFixedLaborHoursByPosition(store, date, position));
	}	

	public FixedLaborHours getScheduleFixedLaborHoursByService(Store store, Date date, PositionGroup positionGroup) throws SQLException {
		Date openHour = store.getOperationTime(CalendarUtils.getDayOfWeek(date)).getOpenHour();
		Date closeHour = store.getOperationTime(CalendarUtils.getDayOfWeek(date)).getCloseHour();

		// If the store opens one day and closes the other day the closeHour has to be '1970-01-02' 
		if(isNextDay(openHour, closeHour)){
			closeHour = CalendarUtils.addOrSubstractDays(closeHour, 1);
		}

		if(log.isDebugEnabled()) {
			log.debug("getScheduleFixedLaborHoursByService: before select params: store_id: " + store.getId() + " date: " + date + " open_hour: " + openHour + " close_hour: " + closeHour + " positionGroup: " + positionGroup);
		}

		FixedLaborHours fixedLaborHours = new FixedLaborHours();
		
		fixedLaborHours.setServiceHours((Double)getSqlMapClient().queryForObject("getScheduleServiceHourByService", ReportDaoHelper.mapServiceHoursByService(store, date, openHour, closeHour, positionGroup)));
		
		fixedLaborHours.setOpenHours((Double)getSqlMapClient().queryForObject("getScheduleOpenHourByService", ReportDaoHelper.mapOpenHoursByService(store, date, openHour, positionGroup)));
		
		fixedLaborHours.setCloseHours((Double)getSqlMapClient().queryForObject("getScheduleCloseHourByService", ReportDaoHelper.mapCloseHoursByService(store, date, closeHour, positionGroup)));

		//It's always zero.
		fixedLaborHours.setFlexHours(0.0);
		
		return fixedLaborHours;
	}
	
	public FixedLaborHours getTargetFixedLaborHoursByService(Store store, Date date, PositionGroup positionGroup) throws SQLException {
		if(log.isDebugEnabled()) {
			log.debug("getTargetFixedLaborHoursByService: before select params: store_id: " + store.getId() + " date: " + date + " positionGroup: " + positionGroup);
		}
		
		return (FixedLaborHours)getSqlMapClient().queryForObject("getTargetFixedLaborHoursByService", ReportDaoHelper.mapTargetFixedLaborHoursByService(store, date, positionGroup));
	}	
	
	private boolean isNextDay(Date startHour, Date endHour){
		return CalendarUtils.equalsOrSmallerTime(endHour, startHour);
	}
	
	

}
