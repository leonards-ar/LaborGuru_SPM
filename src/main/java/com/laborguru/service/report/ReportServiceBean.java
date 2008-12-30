package com.laborguru.service.report;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.laborguru.exception.ErrorEnum;
import com.laborguru.exception.SpmUncheckedException;
import com.laborguru.model.Position;
import com.laborguru.model.PositionGroup;
import com.laborguru.model.Store;
import com.laborguru.model.report.TotalHour;
import com.laborguru.model.report.TotalHourByPosition;
import com.laborguru.service.report.dao.ReportDao;
import com.laborguru.service.staffing.StaffingService;
import com.laborguru.util.CalendarUtils;
import com.laborguru.util.SpmConstants;

/**
 * 
 * @author <a href="fbarreraoro@gmail.com">Federico Barrera Oro</a>
 * @version 1.0
 * @since SPM 1.0
 * 
 */
public class ReportServiceBean implements ReportService {
	private static final Logger log = Logger.getLogger(ReportServiceBean.class);

	private StaffingService staffingService;
	private ReportDao reportDao;

	/**
	 * @param store
	 * @param startingWeekDate
	 * @return
	 * @see com.laborguru.service.report.ReportService#getWeeklyTotalHours(com.laborguru.model.Store, java.util.Date)
	 */
	public List<TotalHour> getWeeklyTotalHours(Store store, Date startingWeekDate) {

			try {

				Date endingWeekDate = CalendarUtils.addOrSubstractDays(startingWeekDate, 6);
				List<TotalHour> scheduleTotalHours = reportDao.getScheduleWeeklyTotalHour(store, startingWeekDate, endingWeekDate);
				List<TotalHour> targetTotalHours = reportDao.getTargetWeeklyTotalHour(store, startingWeekDate, endingWeekDate);
				
				return getMergedTotalHours(scheduleTotalHours, targetTotalHours, startingWeekDate, endingWeekDate); 
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

			Date endingWeekDate = CalendarUtils.addOrSubstractDays(startingWeekDate, 6);
			List<TotalHour> scheduleTotalHours = reportDao.getScheduleWeeklyTotalHourByPosition(store, position, startingWeekDate, endingWeekDate);
			List<TotalHour> targetTotalHours = reportDao.getTargetWeeklyTotalHourByPosition(store, position, startingWeekDate, endingWeekDate);
			
			return getMergedTotalHours(scheduleTotalHours, targetTotalHours, startingWeekDate, endingWeekDate);
			
		} catch (SQLException e) {
			log.error("An SQLError has occurred", e);
			throw new SpmUncheckedException(e.getCause(), e.getMessage(),
					ErrorEnum.GENERIC_DATABASE_ERROR);
		}
		
	}
	
	public List<TotalHour> getWeeklyTotalHoursByService(Store store, PositionGroup positiongroup, Date startingWeekDate){
		try {
			Date endingWeekDate = CalendarUtils.addOrSubstractDays(startingWeekDate, 6);
			List<TotalHour> scheduleTotalHours = reportDao.getScheduleWeeklyTotalHourByService(store, positiongroup, startingWeekDate, endingWeekDate);
			List<TotalHour> targetTotalHours = reportDao.getTargetWeeklyTotalHourByService(store, positiongroup, startingWeekDate, endingWeekDate);
			return getMergedTotalHours(scheduleTotalHours, targetTotalHours, startingWeekDate, endingWeekDate);
			
		} catch (SQLException e) {
			log.error("An SQLError has occurred", e);
			throw new SpmUncheckedException(e.getCause(), e.getMessage(),
					ErrorEnum.GENERIC_DATABASE_ERROR);
		}
	}
	
	/**
	 * @param store
	 * @param startingWeekDate
	 * @return
	 * @see com.laborguru.service.report.ReportService#getForecastByPosition(com.laborguru.model.Store, java.util.Date)
	 */
	public List<TotalHourByPosition> getForecastByPosition(Store store, List<Position> positions, Date startingWeekDate) {
		try{
			List<TotalHourByPosition> scheduleTotalHourByPosition = reportDao.getScheduleForecastByPosition(store, startingWeekDate, CalendarUtils.addOrSubstractDays(startingWeekDate, 6));
			List<TotalHourByPosition> targetTotalHourByPosition = reportDao.getTargetForecastByPosition(store, startingWeekDate, CalendarUtils.addOrSubstractDays(startingWeekDate, 6));
			List<TotalHourByPosition> result = new ArrayList<TotalHourByPosition>();
			for(Position position: positions) {
				TotalHour schedule = getTotalHourByPosition(position, scheduleTotalHourByPosition);
				TotalHour target = getTotalHourByPosition(position, targetTotalHourByPosition);
				TotalHourByPosition thByPosition = new TotalHourByPosition();
				thByPosition.setPosition(position);
				thByPosition.setTotalHour(new TotalHour());
				if(schedule != null){
					thByPosition.getTotalHour().setSchedule(schedule.getSchedule());
				} else {
					thByPosition.getTotalHour().setSchedule(SpmConstants.BD_ZERO_VALUE);
				}
				
				if(target != null){
					thByPosition.getTotalHour().setTarget(target.getTarget());
				} else {
					thByPosition.getTotalHour().setTarget(SpmConstants.BD_ZERO_VALUE);
				}
				result.add(thByPosition);
			}
			
			return result;
			
		} catch (SQLException e) {
			log.error("An SQLError has occurred", e);
			throw new SpmUncheckedException(e.getCause(), e.getMessage(),
					ErrorEnum.GENERIC_DATABASE_ERROR);
		}
	}

	public List<TotalHour> getHalfHourlyReport(Store store, Date date) {
		try{
			//Initialize Minimum staffing in case it doesn't exist.
			staffingService.getDailyStaffingByDate(store, date);
			
			List<TotalHour> schedule = reportDao.getHalfHourlySchedule(store, date);
			List<TotalHour> target = reportDao.getHalfHourlyMinimumStaffing(store, date);
			
			for(TotalHour th: target) {
				TotalHour scheduleTotalHour = getTotalHourByDay(th.getDay(), schedule);
				if(scheduleTotalHour != null){
					th.setSchedule(scheduleTotalHour.getSchedule());
				} else {
					th.setSchedule(SpmConstants.BD_ZERO_VALUE);
				}
			}
			
			return target;
			
		}catch(SQLException e){
			log.error("An SQLError has occurred", e);
			throw new SpmUncheckedException(e.getCause(), e.getMessage(),
					ErrorEnum.GENERIC_DATABASE_ERROR);
		}
	}
	
	private TotalHour getTotalHourByDay(Date day, List<TotalHour> list) {
		for(TotalHour th: list) {
			if(day.equals(th.getDay())) {
				return th;
			}
		}
		return null;
	}
	
	private TotalHour getTotalHourByPosition(Position position, List<TotalHourByPosition> totalHours) {
		for(TotalHourByPosition th: totalHours) {
			if(th.getPosition().getId().equals(position.getId())) {
				return th.getTotalHour();
			}
		}
		return null;
	}
	
	private List<TotalHour> getMergedTotalHours(List<TotalHour> scheduleTotalHours, List<TotalHour> targetTotalHours, Date startDate, Date endDate) {
		
		List<TotalHour> totalHours = new ArrayList<TotalHour>();
		
		for (Date date=startDate; date.compareTo(endDate) <= 0; date=CalendarUtils.addOrSubstractDays(date, 1)) {

			TotalHour totalhour = new TotalHour();
			totalhour.setDay(date);
			TotalHour scheduleTotalHour = getTotalHourByDay(date, scheduleTotalHours);
			if(scheduleTotalHour != null) {
				totalhour.setSchedule(scheduleTotalHour.getSchedule());
			} else {
				totalhour.setSchedule(SpmConstants.BD_ZERO_VALUE);
			}
			TotalHour targetTotalHour = getTotalHourByDay(date, targetTotalHours);
			if(targetTotalHour != null) {
				totalhour.setTarget(targetTotalHour.getTarget());
			} else {
				totalhour.setTarget(SpmConstants.BD_ZERO_VALUE);
			}
			
			totalHours.add(totalhour);
		}
		return totalHours;

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

	/**
	 * @return the staffingService
	 */
	public StaffingService getStaffingService() {
		return staffingService;
	}

	/**
	 * @param staffingService the staffingService to set
	 */
	public void setStaffingService(StaffingService staffingService) {
		this.staffingService = staffingService;
	}
	

}
