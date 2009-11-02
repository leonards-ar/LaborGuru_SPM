package com.laborguru.service.report;

import java.util.Date;
import java.util.List;

import com.laborguru.model.Customer;
import com.laborguru.model.report.TotalManagerHour;
import com.laborguru.service.Service;

public interface ReportCustomerService extends Service {

	public List<TotalManagerHour> getPerformanceEfficiencyReport(Customer customer, Date start, Date end);
	
	
}
