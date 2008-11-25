package com.laborguru.service.report;

import java.util.Date;
import java.util.List;

import com.laborguru.model.Store;
import com.laborguru.model.TotalHour;
import com.laborguru.service.Service;

public interface ReportService extends Service {

	public List<TotalHour> getWeeklyTotalHours(Store store, Date startinWeekDate);
}
