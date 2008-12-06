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
import com.laborguru.service.position.PositionService;
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

	public List<TotalHour> getWeeklyTotalHours(Store store,
			Date startingWeekDate) {
		List<TotalHour> totalHours = new ArrayList<TotalHour>();

		TotalHour th = new TotalHour();

			th.setDay(startingWeekDate);
			th.setSchedule(new BigDecimal("172"));
			th.setTarget(new BigDecimal("156"));
			totalHours.add(th);

			th = new TotalHour();
			th.setDay(CalendarUtils.addOrSubstractDays(startingWeekDate, 1));
			th.setSchedule(new BigDecimal("189"));
			th.setTarget(new BigDecimal("179"));
			totalHours.add(th);

			th = new TotalHour();
			th.setDay(CalendarUtils.addOrSubstractDays(startingWeekDate, 2));
			th.setSchedule(new BigDecimal("216"));
			th.setTarget(new BigDecimal("183"));
			totalHours.add(th);

			th = new TotalHour();
			th.setDay(CalendarUtils.addOrSubstractDays(startingWeekDate, 3));
			th.setSchedule(new BigDecimal("190"));
			th.setTarget(new BigDecimal("168"));
			totalHours.add(th);

			th = new TotalHour();
			th.setDay(CalendarUtils.addOrSubstractDays(startingWeekDate, 4));
			th.setSchedule(new BigDecimal("236"));
			th.setTarget(new BigDecimal("205"));
			totalHours.add(th);

			th = new TotalHour();
			th.setDay(CalendarUtils.addOrSubstractDays(startingWeekDate, 5));
			th.setSchedule(new BigDecimal("265"));
			th.setTarget(new BigDecimal("250"));
			totalHours.add(th);

			th = new TotalHour();
			th.setDay(CalendarUtils.addOrSubstractDays(startingWeekDate, 6));
			th.setSchedule(new BigDecimal("215"));
			th.setTarget(new BigDecimal("211"));
			totalHours.add(th);

			try {
				Store tmpstore = new Store();
				tmpstore.setId(1);
				List<TotalHour> sqlTotalHour = reportDao.getWeeklyTotalHour(store, startingWeekDate, CalendarUtils.addOrSubstractDays(startingWeekDate, 7));
				for (int i = 0; i < totalHours.size(); i++) {
					
					TotalHour targetTotalHour = getTotalHourByDay(totalHours.get(i).getDay(), sqlTotalHour);
					if(targetTotalHour != null) {
						totalHours.get(i).setTarget(targetTotalHour.getTarget());
					}
					
				}
			} catch (SQLException e) {
				log.error("An SQLError has occurred", e);
				throw new SpmUncheckedException(e.getCause(), e.getMessage(),
						ErrorEnum.GENERIC_DATABASE_ERROR);
			}


		return totalHours;
	}

	public HashMap<Position, List<TotalHour>> getWeeklyTotalHoursByPosition(
			Store store, List<Position> positions, Date startingWeekDate) {

		HashMap<Position, List<TotalHour>> totalHourByPosition = new HashMap<Position, List<TotalHour>>();
		List<TotalHourByPosition> totalHoursScheduleByPosition = new ArrayList<TotalHourByPosition>();

			for (Position position : positions) {
				
				TotalHourByPosition tp = new TotalHourByPosition();
				TotalHour th = new TotalHour();
				th.setDay(startingWeekDate);
				th.setSchedule(new BigDecimal("172"));
				th.setTarget(new BigDecimal("156"));
				tp.setPosition(position);
				tp.setTotalHour(th);
				totalHoursScheduleByPosition.add(tp);

				tp = new TotalHourByPosition();
				th = new TotalHour();
				th.setDay(CalendarUtils.addOrSubstractDays(startingWeekDate, 1));
				th.setSchedule(new BigDecimal("189"));
				th.setTarget(new BigDecimal("179"));
				tp.setPosition(position);
				tp.setTotalHour(th);
				totalHoursScheduleByPosition.add(tp);

				tp = new TotalHourByPosition();
				th = new TotalHour();
				th.setDay(CalendarUtils.addOrSubstractDays(startingWeekDate, 2));
				th.setSchedule(new BigDecimal("216"));
				th.setTarget(new BigDecimal("183"));
				tp.setPosition(position);
				tp.setTotalHour(th);
				totalHoursScheduleByPosition.add(tp);

				tp = new TotalHourByPosition();
				th = new TotalHour();
				th.setDay(CalendarUtils.addOrSubstractDays(startingWeekDate, 3));
				th.setSchedule(new BigDecimal("190"));
				th.setTarget(new BigDecimal("168"));
				tp.setPosition(position);
				tp.setTotalHour(th);
				totalHoursScheduleByPosition.add(tp);

				tp = new TotalHourByPosition();
				th = new TotalHour();
				th.setDay(CalendarUtils.addOrSubstractDays(startingWeekDate, 4));
				th.setSchedule(new BigDecimal("236"));
				th.setTarget(new BigDecimal("205"));
				tp.setPosition(position);
				tp.setTotalHour(th);
				totalHoursScheduleByPosition.add(tp);

				tp = new TotalHourByPosition();
				th = new TotalHour();
				th.setDay(CalendarUtils.addOrSubstractDays(startingWeekDate, 5));
				th.setSchedule(new BigDecimal("265"));
				th.setTarget(new BigDecimal("250"));
				tp.setPosition(position);
				tp.setTotalHour(th);
				totalHoursScheduleByPosition.add(tp);

				tp = new TotalHourByPosition();
				th = new TotalHour();
				th.setDay(CalendarUtils.addOrSubstractDays(startingWeekDate, 6));
				th.setSchedule(new BigDecimal("215"));
				th.setTarget(new BigDecimal("211"));
				tp.setPosition(position);
				tp.setTotalHour(th);
				totalHoursScheduleByPosition.add(tp);

			}

			//System.out.println("totalScheduleHour: " + totalHoursScheduleByPosition);
			
		try {
			List<TotalHourByPosition> totalHoursTargetByPosition;
			totalHoursTargetByPosition = reportDao.getWeeklyTotalHourByPosition(store, startingWeekDate, CalendarUtils.addOrSubstractDays(startingWeekDate, 7));

			for(Position position: positions) {
				List<TotalHour> totalHoursScheduleList = getTotalHoursByPosition(position, totalHoursScheduleByPosition);
				List<TotalHour> totalHoursTargetList = getTotalHoursByPosition(position, totalHoursTargetByPosition);
				
				for (int i = 0; i < totalHoursScheduleList.size(); i++) {
					
					TotalHour targetTotalHour = getTotalHourByDay(totalHoursScheduleList.get(i).getDay(), totalHoursTargetList);
					if(targetTotalHour != null) {
						totalHoursScheduleList.get(i).setTarget(targetTotalHour.getTarget());
					}
					
				}
				totalHourByPosition.put(position, totalHoursScheduleList);
			}

		} catch (SQLException e) {
			log.error("An SQLError has occurred", e);
			throw new SpmUncheckedException(e.getCause(), e.getMessage(),
					ErrorEnum.GENERIC_DATABASE_ERROR);
		}
		return totalHourByPosition;		
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
				System.out.println("TotalHour Found:" + th);
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
