package com.laborguru.service.report;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.laborguru.exception.ErrorEnum;
import com.laborguru.exception.SpmUncheckedException;
import com.laborguru.model.DailyProjection;
import com.laborguru.model.HistoricSales;
import com.laborguru.model.Position;
import com.laborguru.model.PositionGroup;
import com.laborguru.model.Store;
import com.laborguru.model.StoreDailyHistoricSalesStaffing;
import com.laborguru.model.report.TotalHour;
import com.laborguru.model.report.TotalHourByPosition;
import com.laborguru.service.projection.ProjectionService;
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
	private ProjectionService projectionService;
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
				List<DailyProjection> projections = projectionService.getAdjustedDailyProjectionForAWeek(store, startingWeekDate);
				List<TotalHour> scheduleTotalHours = reportDao.getScheduleWeeklyTotalHour(store, startingWeekDate, endingWeekDate);
				List<TotalHour> targetTotalHours = reportDao.getTargetWeeklyTotalHour(store, startingWeekDate, endingWeekDate);
				
				return getMergedTotalHours(scheduleTotalHours, targetTotalHours, projections, startingWeekDate, endingWeekDate); 
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
			List<DailyProjection> projections = projectionService.getAdjustedDailyProjectionForAWeek(store, startingWeekDate);
			List<TotalHour> scheduleTotalHours = reportDao.getScheduleWeeklyTotalHourByPosition(store, position, startingWeekDate, endingWeekDate);
			List<TotalHour> targetTotalHours = reportDao.getTargetWeeklyTotalHourByPosition(store, position, startingWeekDate, endingWeekDate);
			
			return getMergedTotalHours(scheduleTotalHours, targetTotalHours, projections, startingWeekDate, endingWeekDate);
			
		} catch (SQLException e) {
			log.error("An SQLError has occurred", e);
			throw new SpmUncheckedException(e.getCause(), e.getMessage(),
					ErrorEnum.GENERIC_DATABASE_ERROR);
		}
		
	}
	
	public List<TotalHour> getWeeklyTotalHoursByService(Store store, PositionGroup positiongroup, Date startingWeekDate){
		try {
			Date endingWeekDate = CalendarUtils.addOrSubstractDays(startingWeekDate, 6);
			List<DailyProjection> projections = projectionService.getAdjustedDailyProjectionForAWeek(store, startingWeekDate);
			List<TotalHour> scheduleTotalHours = reportDao.getScheduleWeeklyTotalHourByService(store, positiongroup, startingWeekDate, endingWeekDate);
			List<TotalHour> targetTotalHours = reportDao.getTargetWeeklyTotalHourByService(store, positiongroup, startingWeekDate, endingWeekDate);
			return getMergedTotalHours(scheduleTotalHours, targetTotalHours, projections, startingWeekDate, endingWeekDate);
			
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
			getStaffingService().getDailyStaffingByDate(store, date);
			
			Date startHour = store.getStoreScheduleStartHour(CalendarUtils.getDayOfWeek(date));
			Date endHour = store.getStoreScheduleEndHour(CalendarUtils.getDayOfWeek(date));
			
			List<TotalHour> schedule = reportDao.getHalfHourlySchedule(store, date, startHour, endHour);
			List<TotalHour> target = reportDao.getHalfHourlyMinimumStaffing(store, date, startHour, endHour);
			
			
			return getMergedHalfHours(startHour, endHour, schedule, target);
			
		}catch(SQLException e){
			log.error("An SQLError has occurred", e);
			throw new SpmUncheckedException(e.getCause(), e.getMessage(),
					ErrorEnum.GENERIC_DATABASE_ERROR);
		}
	}
	
	public List<TotalHour> getHalfHourlyReportByPosition(Store store, Position position, Date date){
		try{
			//Initialize Minimum staffing in case it doesn't exist.			
			getStaffingService().getDailyStaffingByDate(store, date);
			
			Date startHour = store.getStoreScheduleStartHour(CalendarUtils.getDayOfWeek(date));
			Date endHour = store.getStoreScheduleEndHour(CalendarUtils.getDayOfWeek(date));
			
			List<TotalHour> schedule = reportDao.getHalfHourlyScheduleByPosition(store, position, date, startHour, endHour);
			List<TotalHour> target = reportDao.getHalfHourlyMinimumStaffingByPosition(store, position, date, startHour, endHour);
			return getMergedHalfHours(startHour, endHour, schedule, target);
		} catch(SQLException e){
			log.error("An SQLError has occurred", e);
			throw new SpmUncheckedException(e.getCause(), e.getMessage(),
					ErrorEnum.GENERIC_DATABASE_ERROR);
		}
	}
	
	public List<TotalHour> getHalfHourlyReportByService(Store store, PositionGroup positionGroup, Date date){
		try{
			//Initialize Minimum staffing in case it doesn't exist.			
			getStaffingService().getDailyStaffingByDate(store, date);

			Date startHour = store.getStoreScheduleStartHour(CalendarUtils.getDayOfWeek(date));
			Date endHour = store.getStoreScheduleEndHour(CalendarUtils.getDayOfWeek(date));
			
			List<TotalHour> schedule = reportDao.getHalfHourlyScheduleByService(store, positionGroup, date, startHour, endHour);
			List<TotalHour> target = reportDao.getHalfHourlyMinimumStaffingByService(store, positionGroup, date, startHour, endHour);
			
			return getMergedHalfHours(startHour, endHour, schedule, target);
			
		} catch(SQLException e){
			log.error("An SQLError has occurred", e);
			throw new SpmUncheckedException(e.getCause(), e.getMessage(),
					ErrorEnum.GENERIC_DATABASE_ERROR);
		}
	}
	
	public List<TotalHour> getPerformanceEfficiencyReport(Store store, Date startingWeekDate) {
		/**
		 * 
		 * 1.- Get historic sales for a week
		 * 2.- Get Hours worked for a week
		 * 3.- Get Minimus Staffing needed for the number of 
		 */
		try{
			Date endingWeekDate = CalendarUtils.addOrSubstractDays(startingWeekDate, 6);
			List<HistoricSales> actualSales = reportDao.getActualSales(store, startingWeekDate, endingWeekDate);
			List<TotalHour> actualHours = reportDao.getActualHours(store, startingWeekDate, endingWeekDate);			
			List<TotalHour> minimumStaffing = getActualMinimumStaffing(store, startingWeekDate, endingWeekDate);
			 
			return getMergedTotalEfficiencyHours(actualSales, actualHours, minimumStaffing, startingWeekDate, endingWeekDate);
			
		} catch(SQLException e) {
			log.error("An SQLError has occurred", e);
			throw new SpmUncheckedException(e.getCause(), e.getMessage(),
					ErrorEnum.GENERIC_DATABASE_ERROR);
		}
		
	}
	
	public List<TotalHour> getScheduleExecutionEfficiencyReport(Store store, Date startingWeekDate){
		/**
		 *
		 * 1.- Get historic sales for a week
		 * 2.- Get schedule hours. (Already done) List<TotalHour> scheduleTotalHours = reportDao.getScheduleWeeklyTotalHour(store, startingWeekDate, endingWeekDate);
		 * 3.- Get Hours Worked (This is a select to that new table).
		 */
		try{
		Date endingWeekDate = CalendarUtils.addOrSubstractDays(startingWeekDate, 6);
		List<HistoricSales> actualSales = reportDao.getActualSales(store, startingWeekDate, endingWeekDate);
		List<TotalHour> scheduleTotalHours = reportDao.getScheduleWeeklyTotalHour(store, startingWeekDate, endingWeekDate);
		List<TotalHour> actualHours = reportDao.getActualHours(store, startingWeekDate, endingWeekDate);
		
		
		return getMergedTotalEfficiencyHours(actualSales, scheduleTotalHours, actualHours, startingWeekDate, endingWeekDate);
		} catch (SQLException e) {
			log.error("An SQLError has occurred", e);
			throw new SpmUncheckedException(e.getCause(), e.getMessage(),
					ErrorEnum.GENERIC_DATABASE_ERROR);
		}
	}
	
	private List<TotalHour> getActualMinimumStaffing(Store store, Date startDate, Date endDate){
		List<TotalHour> totalHours = new ArrayList<TotalHour>();
		for(Date date = startDate; !endDate.after(date); date = CalendarUtils.addOrSubstractDays(date,1)){
			TotalHour totalhour = new TotalHour();
			
			StoreDailyHistoricSalesStaffing saleStaffing = getStaffingService().getDailyHistoricSalesStaffingByDate(store, date);
			
			totalhour.setDay(date);
			totalhour.setTarget(new BigDecimal(saleStaffing.getTotalDailyTarget()));
			
			totalHours.add(totalhour);
			
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
	
	private TotalHour getTotalHourByTime(Date hour, List<TotalHour> list) {
		for(TotalHour th: list) {
			if(CalendarUtils.equalsTime(hour, th.getDay())){
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
	
	private List<TotalHour> getMergedTotalHours(List<TotalHour> scheduleTotalHours, List<TotalHour> targetTotalHours, List<DailyProjection>projections, Date startDate, Date endDate) {
		
		List<TotalHour> totalHours = new ArrayList<TotalHour>();
		
		for (Date date=startDate; date.compareTo(endDate) <= 0; date=CalendarUtils.addOrSubstractDays(date, 1)) {
			
			TotalHour totalhour = new TotalHour();
			totalhour.setDay(date);
			DailyProjection dp = getDailyProjectionByDate(date, projections);
			if(dp != null){
				totalhour.setSales(dp.getDailyProjectionValue());
			} else {
				totalhour.setSales(SpmConstants.BD_ZERO_VALUE);
			}

			totalhour.setSchedule(getScheduleValue(getTotalHourByDay(date, scheduleTotalHours)));
			totalhour.setTarget(getTargetValue(getTotalHourByDay(date, targetTotalHours)));
			
			totalHours.add(totalhour);
		}
		
		return totalHours;

	}
	
	private List<TotalHour> getMergedHalfHours(Date startHour, Date endHour, List<TotalHour> schedule, List<TotalHour> target){
		List <TotalHour> totalHours = new ArrayList<TotalHour>();
		
		for(Date hour = startHour; !CalendarUtils.equalsTime(hour, endHour); hour = CalendarUtils.addOrSubstractMinutes(hour, 30)) {
			
			
			TotalHour totalhour = new TotalHour();
			totalhour.setDay(hour);
			
			totalhour.setSchedule(getScheduleValue(getTotalHourByTime(hour, schedule)));
			totalhour.setTarget(getTargetValue(getTotalHourByTime(hour, target)));
			
			totalHours.add(totalhour);
		}
		
		return totalHours;
	}
	
	private List<TotalHour> getMergedTotalEfficiencyHours(List<HistoricSales> actualSales, List<TotalHour>scheduleTotalHours, List<TotalHour> targetTotalHours, Date startDate, Date endDate) {
		List<TotalHour> totalHours = new ArrayList<TotalHour>();
		
		for (Date date=startDate; date.compareTo(endDate) <= 0; date=CalendarUtils.addOrSubstractDays(date, 1)) {
			
			TotalHour totalhour = new TotalHour();
			totalhour.setDay(date);
			HistoricSales hs = getHistoricSalesByDate(date, actualSales);
			if(hs != null){
				totalhour.setSales(hs.getMainValue());
			} else {
				totalhour.setSales(SpmConstants.BD_ZERO_VALUE);
			}

			totalhour.setSchedule(getScheduleValue(getTotalHourByDay(date, scheduleTotalHours)));
			totalhour.setTarget(getTargetValue(getTotalHourByDay(date, targetTotalHours)));
			
			totalHours.add(totalhour);
		}
		
		return totalHours;
	}
	
	private DailyProjection getDailyProjectionByDate(Date date, List<DailyProjection> projections) {
		for(DailyProjection dp: projections) {
			if(date.equals(dp.getSalesDate())) {
				return dp;
			}
		
		}
		return null;
	}
	
	private HistoricSales getHistoricSalesByDate(Date date, List<HistoricSales> historicSales){
		for(HistoricSales hs: historicSales) {
			Date hsDate = CalendarUtils.removeTimeFromDate(hs.getDateTime());
			if(date.equals(hsDate)) {
				return hs;
			}
		}
		return null;
	}

	private BigDecimal getScheduleValue(TotalHour th) {
		if(th != null){
			return th.getSchedule();
		}
		
		return SpmConstants.BD_ZERO_VALUE;
	}

	private BigDecimal getTargetValue(TotalHour th) {
		if(th != null){
			return th.getTarget();
		}
		return SpmConstants.BD_ZERO_VALUE;
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

	/**
	 * @return the projectionService
	 */
	public ProjectionService getProjectionService() {
		return projectionService;
	}

	/**
	 * @param projectionService the projectionService to set
	 */
	public void setProjectionService(ProjectionService projectionService) {
		this.projectionService = projectionService;
	}

}
