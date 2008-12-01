package com.laborguru.service.report.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.laborguru.model.Store;
import com.laborguru.model.TotalHour;

public class SqlMapReportDao extends SqlMapClientDaoSupport implements ReportDao {
	private static final Logger log = Logger.getLogger(SqlMapReportDao.class);
	
	public List<TotalHour> getWeeklyTotalHour(Store store, Date startDate,Date endDate) throws SQLException {
		if(log.isDebugEnabled()) {
			log.debug("getWeeklyTotalHour params: store_id:" + store.getId() + " startDate: " + startDate + " endDate" + endDate);
		}
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("store_id", store.getId());
		map.put("start_date", startDate);
		map.put("end_date", endDate);
		
		return getSqlMapClient().queryForList("getTargetWeeklyTotalHours", map);
		
	}

}
