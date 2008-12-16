package com.laborguru.service.report.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.laborguru.model.Position;
import com.laborguru.model.Store;
import com.laborguru.model.report.TotalHour;
import com.laborguru.model.report.TotalHourByPosition;

/**
 *
 * @author <a href="fbarreraoro@gmail.com">Federico Barrera Oro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public interface ReportDao {

	/**
	 * Retrieves all the schedule values for the Total Hour Weekly
	 * @param store 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<TotalHour>getScheduleWeeklyTotalHour(Store store, Date startDate, Date endDate) throws SQLException;
	
	/**
	 * Retrieves all the target values for the Total Hour Weekly
	 * @param store 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<TotalHour>getTargetWeeklyTotalHour(Store store, Date startDate, Date endDate) throws SQLException;
	
	/**
	 * Retrieves all the schedule values for the Total Hour Weekly By Position.
	 * @param store
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws SQLException
	 */
	List<TotalHour> getScheduleWeeklyTotalHourByPosition(Store store, Position position, Date startDate, Date endDate) throws SQLException;
	
	/**
	 * Retrieves all the target values for Total Hour Weekly By Position.
	 * @param store
	 * @param position
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws SQLException
	 */
	List<TotalHour> getTargetWeeklyTotalHourByPosition(Store store, Position position, Date startDate, Date endDate) throws SQLException;
}
