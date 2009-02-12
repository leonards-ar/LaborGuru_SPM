package com.laborguru.service.report;

import java.util.Date;
import java.util.List;

import com.laborguru.model.Position;
import com.laborguru.model.PositionGroup;
import com.laborguru.model.Store;
import com.laborguru.model.report.TotalHour;
import com.laborguru.model.report.TotalHourByPosition;
import com.laborguru.service.Service;

/**
 *
 * @author <a href="fbarreraoro@gmail.com">Federico Barrera Oro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public interface ReportService extends Service {

	/**
	 * Retrieves the Hours that has been projected and the hours that has been consumed.
	 * @param store
	 * @param startingWeekDate
	 * @return
	 */
	List<TotalHour> getWeeklyTotalHours(Store store, Date startingWeekDate);
	
	/**
	 * Retrieves the Hours that has been projected and the hours that has been consumed by position.
	 * @param store
	 * @param position
	 * @param startingWeekDate
	 * @return
	 */
	List<TotalHour> getWeeklyTotalHoursByPosition(Store store, Position position, Date startingWeekDate);
	
	/**
	 * Retrieves the Hours that has been projected and the hours that has been consumed by position group.
	 * @param store
	 * @param positiongroup
	 * @param startingWeekDate
	 * @return
	 */
	List<TotalHour> getWeeklyTotalHoursByService(Store store, PositionGroup positiongroup, Date startingWeekDate);	
	/**
	 * Retrieves the total forecast for each position in the store.
	 * @param store
	 * @param startingWeekDate
	 * @return
	 */
	List<TotalHourByPosition> getForecastByPosition(Store store, List<Position> positions, Date startingWeekDate);
	
	/**
	 * Retrieves the half hours that has been projected and the hours that has been consumed
	 * @param store
	 * @param date
	 * @return
	 */
	List<TotalHour> getHalfHourlyReport(Store store, Date date);
	
	/**
	 * Retrieves the half hours filtered by a Position.
	 * @param store
	 * @param position
	 * @param date
	 * @return
	 */
	List<TotalHour> getHalfHourlyReportByPosition(Store store, Position position, Date date);
	
	/**
	 * Retrieves the half hours filtered by a Position Group.
	 * @param store
	 * @param positionGroup
	 * @param date
	 * @return
	 */
	List<TotalHour> getHalfHourlyReportByService(Store store, PositionGroup positionGroup, Date date);
	
	/**
	 * Retrieves the Performance Efficiency Report
	 * @param store
	 * @param startingWeekDate
	 * @return
	 */
	List<TotalHour> getPerformanceEfficiencyReport(Store store, Date startingWeekDate);
	
	/**
	 * Retrieves the Schedule Execution Efficiency
	 * @param store
	 * @param startingWeekDate
	 * @return
	 */
	List<TotalHour> getScheduleExecutionEfficiencyReport(Store store, Date startingWeekDate);
	
}
