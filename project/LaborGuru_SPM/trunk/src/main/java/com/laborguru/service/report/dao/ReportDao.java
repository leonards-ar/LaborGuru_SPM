package com.laborguru.service.report.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.laborguru.model.Position;
import com.laborguru.model.PositionGroup;
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
	
	/**
	 * Retrieves the schedule forecast of all the positions.
	 * @param store
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws SQLException
	 */
	List<TotalHourByPosition> getScheduleForecastByPosition(Store store, Date startDate, Date endDate) throws SQLException;
	
	/**
	 * Retrieves the target forecast of all the positions.
	 * @param store
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws SQLException
	 */
	List<TotalHourByPosition> getTargetForecastByPosition(Store store, Date startDate, Date endDate) throws SQLException;
	
	/**
	 * Retrieves the schedule values for Total Hour Weekly by Position Group
	 * @param store
	 * @param positionGroup
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws SQLException
	 */
	List<TotalHour> getScheduleWeeklyTotalHourByService(Store store, PositionGroup positionGroup, Date startDate, Date endDate) throws SQLException;
	
	/**
	 * Retrieves the target values for Total Hour Weekly by Position Group
	 * @param store
	 * @param positionGroup
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws SQLException
	 */
	List<TotalHour> getTargetWeeklyTotalHourByService(Store store, PositionGroup positionGroup, Date startDate, Date endDate) throws SQLException;
	
	/**
	 * Retrieves the scheduled half hours for a Store on a selected date
	 * @param store
	 * @param date
	 * @return
	 * @throws SQLException
	 */
	List<TotalHour> getHalfHourlySchedule(Store store, Date date) throws SQLException;

	/**
	 * Retrieves the target half hours for a Store on a selected date
	 * @param store
	 * @param date
	 * @return
	 * @throws SQLException
	 */
	List<TotalHour> getHalfHourlyMinimumStaffing(Store store, Date date) throws SQLException;
	
	/**
	 * Retrieves the scheduled hours for a Store on a selected date filtered by a Position
	 * @param store
	 * @param position
	 * @param date
	 * @return
	 * @throws SQLException
	 */
	List<TotalHour> getHalfHourlyScheduleByPosition(Store store, Position position, Date date) throws SQLException;
	
	/**
	 * Retrieves the target hours for a Store on a selected date filtered by a Position
	 * @param store
	 * @param position
	 * @param date
	 * @return
	 * @throws SQLException
	 */
	List<TotalHour> getHalfHourlyMinimumStaffingByPosition(Store store, Position position, Date date) throws SQLException;
	
	/**
	 * Retrieves the scheduled hours for a Store on a selected date filtered by a Position
	 * @param store
	 * @param position
	 * @param date
	 * @return
	 * @throws SQLException
	 */
	List<TotalHour> getHalfHourlyScheduleByService(Store store, PositionGroup positionGroup, Date date) throws SQLException;
	
	/**
	 * Retrieves the target hours for a Store on a selected date filtered by a Position
	 * @param store
	 * @param position
	 * @param date
	 * @return
	 * @throws SQLException
	 */
	List<TotalHour> getHalfHourlyMinimumStaffingByService(Store store, PositionGroup positionGroup, Date date) throws SQLException;	
}