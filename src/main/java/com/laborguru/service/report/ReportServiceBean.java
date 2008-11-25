package com.laborguru.service.report;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.laborguru.model.Store;
import com.laborguru.model.TotalHour;

public class ReportServiceBean implements ReportService{
	
	public List<TotalHour> getWeeklyTotalHours(Store store, Date startinWeekDate){
		List<TotalHour> totalHours = new ArrayList<TotalHour>();
		
		TotalHour th = new TotalHour();
		
		th.setColumnName("Mon 5/19");
		th.setSchedule(new BigDecimal("172"));
		th.setTarget(new BigDecimal("156"));
		th.setDifference(new BigDecimal("16"));
		th.setPercentaje(new BigDecimal("10"));
		totalHours.add(th);
		
		th = new TotalHour();
		th.setColumnName("Tue 5/20");
		th.setSchedule(new BigDecimal("189"));
		th.setTarget(new BigDecimal("179"));
		th.setDifference(new BigDecimal("10"));
		th.setPercentaje(new BigDecimal("6"));
		totalHours.add(th);
		
		th = new TotalHour();
		th.setColumnName("Wed 5/21");
		th.setSchedule(new BigDecimal("216"));
		th.setTarget(new BigDecimal("183"));
		th.setDifference(new BigDecimal("33"));
		th.setPercentaje(new BigDecimal("18"));
		totalHours.add(th);
		
		th = new TotalHour();
		th.setColumnName("Thu 5/22");
		th.setSchedule(new BigDecimal("190"));
		th.setTarget(new BigDecimal("168"));
		th.setDifference(new BigDecimal("22"));
		th.setPercentaje(new BigDecimal("13"));
		totalHours.add(th);
		
		th = new TotalHour();
		th.setColumnName("Fri 5/23");
		th.setSchedule(new BigDecimal("236"));
		th.setTarget(new BigDecimal("205"));
		th.setDifference(new BigDecimal("31"));
		th.setPercentaje(new BigDecimal("15"));
		totalHours.add(th);
		
		th = new TotalHour();
		th.setColumnName("Sat 5/24");
		th.setSchedule(new BigDecimal("265"));
		th.setTarget(new BigDecimal("250"));
		th.setDifference(new BigDecimal("15"));
		th.setPercentaje(new BigDecimal("6"));
		totalHours.add(th);
		
		th = new TotalHour();
		th.setColumnName("Sun 5/25");
		th.setSchedule(new BigDecimal("215"));
		th.setTarget(new BigDecimal("211"));
		th.setDifference(new BigDecimal("4"));
		th.setPercentaje(new BigDecimal("2"));
		totalHours.add(th);
		
		return totalHours;
	}

}
