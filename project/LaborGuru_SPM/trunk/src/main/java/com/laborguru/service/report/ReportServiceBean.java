package com.laborguru.service.report;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.laborguru.model.Store;
import com.laborguru.model.TotalHour;

public class ReportServiceBean implements ReportService {

	public List<TotalHour> getWeeklyTotalHours(Store store, Date startinWeekDate) {
		List<TotalHour> totalHours = new ArrayList<TotalHour>();

		TotalHour th = new TotalHour();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd");
			th.setDay(sdf.parse("2008/05/19"));
			th.setSchedule(new BigDecimal("172"));
			th.setTarget(new BigDecimal("156"));
			totalHours.add(th);
			
			th = new TotalHour();
			th.setDay(sdf.parse("2008/05/20"));
			th.setSchedule(new BigDecimal("189"));
			th.setTarget(new BigDecimal("179"));
			totalHours.add(th);
			
			th = new TotalHour();
			th.setDay(sdf.parse("2008/05/21"));
			th.setSchedule(new BigDecimal("216"));
			th.setTarget(new BigDecimal("183"));
			totalHours.add(th);

			th = new TotalHour();
			th.setDay(sdf.parse("2008/05/22"));
			th.setSchedule(new BigDecimal("190"));
			th.setTarget(new BigDecimal("168"));
			totalHours.add(th);
			
			th = new TotalHour();
			th.setDay(sdf.parse("2008/05/23"));
			th.setSchedule(new BigDecimal("236"));
			th.setTarget(new BigDecimal("205"));
			totalHours.add(th);
			
			th = new TotalHour();
			th.setDay(sdf.parse("2008/05/24"));
			th.setSchedule(new BigDecimal("265"));
			th.setTarget(new BigDecimal("250"));
			totalHours.add(th);

			th = new TotalHour();
			th.setDay(sdf.parse("2008/05/25"));
			th.setSchedule(new BigDecimal("215"));
			th.setTarget(new BigDecimal("211"));
			totalHours.add(th);

		} catch (ParseException e) {
		}
		return totalHours;
	}

}
