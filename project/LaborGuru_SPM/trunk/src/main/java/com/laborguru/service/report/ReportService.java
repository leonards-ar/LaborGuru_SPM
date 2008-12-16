package com.laborguru.service.report;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.laborguru.model.Position;
import com.laborguru.model.Store;
import com.laborguru.model.report.TotalHour;
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
}
