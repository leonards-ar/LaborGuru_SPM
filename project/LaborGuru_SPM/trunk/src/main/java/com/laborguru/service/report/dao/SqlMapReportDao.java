package com.laborguru.service.report.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.laborguru.model.Position;
import com.laborguru.model.Store;
import com.laborguru.model.report.TotalHour;

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

	public List<TotalHour> getHalfHourlyMinimumStaffing(Store store, Date date) throws SQLException{
		if(log.isDebugEnabled()){
			log.debug("getHalfHourlyMinimumStaffing: before select params: store_id: " + store.getId() + " date: " + date);
		}
		
		return getSqlMapClient().queryForList("getTargetHalfHourlyTotalHours", ReportDaoHelper.mapHalfHoursReport(store, date));
	}
	
	

}
