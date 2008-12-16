package com.laborguru.service.report;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.laborguru.exception.ErrorEnum;
import com.laborguru.exception.SpmUncheckedException;
import com.laborguru.model.Position;
import com.laborguru.model.Store;
import com.laborguru.model.report.TotalHour;
import com.laborguru.model.report.TotalHourByPosition;
import com.laborguru.service.report.dao.ReportDao;
import com.laborguru.util.CalendarUtils;

/**
 * 
 * @author <a href="fbarreraoro@gmail.com">Federico Barrera Oro</a>
 * @version 1.0
 * @since SPM 1.0
 * 
 */
public class ReportServiceBean implements ReportService {
	private static final Logger log = Logger.getLogger(ReportServiceBean.class);

	
	private ReportDao reportDao;

	/**
	 * @param store
	 * @param startingWeekDate
	 * @return
	 * @see com.laborguru.service.report.ReportService#getWeeklyTotalHours(com.laborguru.model.Store, java.util.Date)
	 */
	public List<TotalHour> getWeeklyTotalHours(Store store, Date startingWeekDate) {

			try {

				List<TotalHour> scheduleTotalHours = reportDao.getScheduleWeeklyTotalHour(store, startingWeekDate, CalendarUtils.addOrSubstractDays(startingWeekDate, 6));
				List<TotalHour> targetTotalHours = reportDao.getTargetWeeklyTotalHour(store, startingWeekDate, CalendarUtils.addOrSubstractDays(startingWeekDate, 6));
				List<TotalHour> totalHours = new ArrayList<TotalHour>();
				
				for (Date date=startingWeekDate; date.before(CalendarUtils.addOrSubstractDays(startingWeekDate, 7)); date=CalendarUtils.addOrSubstractDays(date, 1)) {
					
					TotalHour totalhour = new TotalHour();
					totalhour.setDay(date);
					TotalHour scheduleTotalHour = getTotalHourByDay(date, scheduleTotalHours);
					if(scheduleTotalHour != null) {
						totalhour.setSchedule(scheduleTotalHour.getSchedule());
					} else {
						totalhour.setSchedule(new BigDecimal("0"));
					}
					TotalHour targetTotalHour = getTotalHourByDay(date, targetTotalHours);
					if(targetTotalHour != null) {
						totalhour.setTarget(targetTotalHour.getTarget());
					} else {
						totalhour.setTarget(new BigDecimal("0"));
					}
					
					totalHours.add(totalhour);
				}
				
				return totalHours;
				
			} catch (SQLException e) {
				log.error("An SQLError has occurred", e);
				throw new SpmUncheckedException(e.getCause(), e.getMessage(),
						ErrorEnum.GENERIC_DATABASE_ERROR);
			}
	}

	/**
	 * @param store
	 * @param position
	 * @param startingWeekDate
	 * @return
	 * @see com.laborguru.service.report.ReportService#getWeeklyTotalHoursByPosition(com.laborguru.model.Store, com.laborguru.model.Position, java.util.Date)
	 */
	public List<TotalHour> getWeeklyTotalHoursByPosition(Store store, Position position, Date startingWeekDate){
		try {

			List<TotalHour> scheduleTotalHours = reportDao.getScheduleWeeklyTotalHourByPosition(store, position, startingWeekDate, CalendarUtils.addOrSubstractDays(startingWeekDate, 6));
			List<TotalHour> targetTotalHours = reportDao.getTargetWeeklyTotalHourByPosition(store, position, startingWeekDate, CalendarUtils.addOrSubstractDays(startingWeekDate, 6));
			List<TotalHour> totalHours = new ArrayList<TotalHour>();
			
			for (Date date=startingWeekDate; date.before(CalendarUtils.addOrSubstractDays(startingWeekDate, 7)); date=CalendarUtils.addOrSubstractDays(date, 1)) {
				
				TotalHour totalhour = new TotalHour();
				totalhour.setDay(date);
				TotalHour scheduleTotalHour = getTotalHourByDay(date, scheduleTotalHours);
				if(scheduleTotalHour != null) {
					totalhour.setSchedule(scheduleTotalHour.getSchedule());
				} else {
					totalhour.setSchedule(new BigDecimal("0"));
				}
				TotalHour targetTotalHour = getTotalHourByDay(date, targetTotalHours);
				if(targetTotalHour != null) {
					totalhour.setTarget(targetTotalHour.getTarget());
				} else {
					totalhour.setTarget(new BigDecimal("0"));
				}
				
				totalHours.add(totalhour);
			}
			
			return totalHours;
			
		} catch (SQLException e) {
			log.error("An SQLError has occurred", e);
			throw new SpmUncheckedException(e.getCause(), e.getMessage(),
					ErrorEnum.GENERIC_DATABASE_ERROR);
		}
		
	}	
	private List<TotalHour> getTotalHoursByPosition(Position position, List<TotalHourByPosition> thByPosition) {

		List<TotalHour>totalHours = new ArrayList<TotalHour>();
		for(TotalHourByPosition th: thByPosition) {
			if(position.getId().equals(th.getPosition().getId())) {
				totalHours.add(th.getTotalHour());
			}
		}
		
		return totalHours;
	}
	
	private TotalHour getTotalHourByDay(Date day, List<TotalHour> list) {
		for(TotalHour th: list) {
			if(day.equals(th.getDay())) {
				return th;
			}
		}
		return null;
	}
	
	/**
	 * @return the reportDao
	 */
	public ReportDao getReportDao() {
		return reportDao;
	}

	/**
	 * @param reportDao
	 *            the reportDao to set
	 */
	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}

}
