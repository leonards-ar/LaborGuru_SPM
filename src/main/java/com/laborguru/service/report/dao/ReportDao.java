package com.laborguru.service.report.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.laborguru.model.Store;
import com.laborguru.model.TotalHour;

/**
 *
 * @author <a href="fbarreraoro@gmail.com">Federico Barrera Oro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public interface ReportDao {

	/**
	 * Retrieves all the values for the Total Hour Weekly
	 * @param store 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<TotalHour>getWeeklyTotalHour(Store store, Date startDate, Date endDate) throws SQLException;
	
}
