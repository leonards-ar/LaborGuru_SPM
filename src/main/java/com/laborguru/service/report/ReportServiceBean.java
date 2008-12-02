package com.laborguru.service.report;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.laborguru.exception.ErrorEnum;
import com.laborguru.exception.SpmUncheckedException;
import com.laborguru.model.Store;
import com.laborguru.model.TotalHour;
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
	
	public List<TotalHour> getWeeklyTotalHours(Store store, Date startingWeekDate) {
		List<TotalHour> totalHours = new ArrayList<TotalHour>();

		TotalHour th = new TotalHour();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd");
			th.setDay(sdf.parse("2008/11/30"));
			th.setSchedule(new BigDecimal("172"));
			th.setTarget(new BigDecimal("156"));
			totalHours.add(th);

			th = new TotalHour();
			th.setDay(sdf.parse("2008/12/01"));
			th.setSchedule(new BigDecimal("189"));
			th.setTarget(new BigDecimal("179"));
			totalHours.add(th);

			th = new TotalHour();
			th.setDay(sdf.parse("2008/12/02"));
			th.setSchedule(new BigDecimal("216"));
			th.setTarget(new BigDecimal("183"));
			totalHours.add(th);

			th = new TotalHour();
			th.setDay(sdf.parse("2008/12/03"));
			th.setSchedule(new BigDecimal("190"));
			th.setTarget(new BigDecimal("168"));
			totalHours.add(th);

			th = new TotalHour();
			th.setDay(sdf.parse("2008/12/04"));
			th.setSchedule(new BigDecimal("236"));
			th.setTarget(new BigDecimal("205"));
			totalHours.add(th);

			th = new TotalHour();
			th.setDay(sdf.parse("2008/12/05"));
			th.setSchedule(new BigDecimal("265"));
			th.setTarget(new BigDecimal("250"));
			totalHours.add(th);

			th = new TotalHour();
			th.setDay(sdf.parse("2008/12/06"));
			th.setSchedule(new BigDecimal("215"));
			th.setTarget(new BigDecimal("211"));
			totalHours.add(th);

			try {
				Store tmpstore = new Store();
				tmpstore.setId(1);
				List<TotalHour> sqlTotalHour = reportDao.getWeeklyTotalHour(
						store, startingWeekDate, CalendarUtils.addOrSubstractDays(startingWeekDate, 7));
				for (int i = 0; i < totalHours.size(); i++) {

					for (TotalHour to : sqlTotalHour) {
						if (totalHours.get(i).getDay().equals(to.getDay())) {
							totalHours.get(i).setTarget(to.getTarget());
							break;
						}
					}
				}
			} catch (SQLException e) {
				log.error("An SQLError has occurred", e);
				throw new SpmUncheckedException(e.getCause(), e.getMessage(),
						ErrorEnum.GENERIC_DATABASE_ERROR);
			}

		} catch (ParseException e) {
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

}
