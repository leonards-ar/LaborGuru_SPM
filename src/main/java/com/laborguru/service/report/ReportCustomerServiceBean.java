package com.laborguru.service.report;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.laborguru.exception.ErrorEnum;
import com.laborguru.exception.SpmUncheckedException;
import com.laborguru.model.Customer;
import com.laborguru.model.Region;
import com.laborguru.model.Store;
import com.laborguru.model.StoreDailyHistoricSalesStaffing;
import com.laborguru.model.report.TotalCustomerManagerHour;
import com.laborguru.model.report.TotalHour;
import com.laborguru.service.customer.CustomerService;
import com.laborguru.service.report.dao.ReportDao;
import com.laborguru.service.staffing.StaffingService;
import com.laborguru.util.CalendarUtils;
import com.laborguru.util.SpmConstants;

/**
 *
 * @author <a href="fbarreraoro@gmail.com">Federico Barrera Oro</a>
 * @version 1.1
 * @since SPM 1.1
 *
 */
public class ReportCustomerServiceBean implements ReportCustomerService {
	public static Logger log = Logger.getLogger(ReportCustomerServiceBean.class);
	
	private ReportDao reportDao;
	private StaffingService staffingService;
	private CustomerService customerService;

	

	public List<TotalCustomerManagerHour> getPerformanceEfficiencyReport(Customer customer, Date start, Date end) {

		try{
			
			List<TotalCustomerManagerHour> actualSales = reportDao.getActualSalesByCustomer(customer, start, end);
			List<TotalCustomerManagerHour> actualHours = reportDao.getActualHoursByCustomer(customer, start, end);			
			List<TotalCustomerManagerHour> minimumStaffing = getActualMinimumStaffing(customer, start, end);
		
			return merge(actualSales, actualHours, minimumStaffing);
		
		} catch(SQLException e) {
			log.error("An SQLError has occurred", e);
			throw new SpmUncheckedException(e.getCause(), e.getMessage(),
					ErrorEnum.GENERIC_DATABASE_ERROR);
		}
		
	}
	
	public List<TotalCustomerManagerHour> getWeeklyTotalHours(Customer customer, Date start, Date end) {

		try {
			List<TotalCustomerManagerHour> actualSales = reportDao.getActualSalesByCustomer(customer, start, end);
			List<TotalCustomerManagerHour> scheduleTotalHours = reportDao.getScheduleTotalHourByCustomer(customer, start, end);
			List<TotalCustomerManagerHour> targetTotalHours = reportDao.getTargetTotalHourByCustomer(customer, start, end);
			
			return merge(actualSales, scheduleTotalHours, targetTotalHours); 
		} catch (SQLException e) {
			log.error("An SQLError has occurred", e);
			throw new SpmUncheckedException(e.getCause(), e.getMessage(),
					ErrorEnum.GENERIC_DATABASE_ERROR);
		}
	}

	
	
	/**
	 * Returns the minimum staffing for each region. This is the sum of all the stores of each region
	 * @param customer
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	private List<TotalCustomerManagerHour> getActualMinimumStaffing(Customer customer, Date startDate, Date endDate){
		List<TotalCustomerManagerHour> totalHours = new ArrayList<TotalCustomerManagerHour>();
		
		Customer tmpCustomer = customerService.getCustomerById(customer);
		for(Region region: tmpCustomer.getRegions()) {
			TotalCustomerManagerHour totalManagerHour = new TotalCustomerManagerHour();
			totalManagerHour.setRegion(region);
			totalHours.add(totalManagerHour);
			BigDecimal targetRegion = SpmConstants.BD_ZERO_VALUE;
			for(Store store: region.getStores()) {
				BigDecimal targetStores = SpmConstants.BD_ZERO_VALUE;
				for(Date date = startDate; endDate.after(date); date = CalendarUtils.addOrSubstractDays(date,1)){
					StoreDailyHistoricSalesStaffing saleStaffing = getStaffingService().getDailyHistoricSalesStaffingByDate(store, date);
					targetStores = targetStores.add(new BigDecimal(saleStaffing.getTotalDailyTarget()));
				}
				targetRegion = targetRegion.add(targetStores);
			}
			totalManagerHour.setTarget(targetRegion);
		}

		return totalHours;
	}

	private List<TotalCustomerManagerHour> merge(List<TotalCustomerManagerHour> actualSales, List<TotalCustomerManagerHour> scheduleHours, List<TotalCustomerManagerHour> targetHours) {
		
		List<TotalCustomerManagerHour> totalHours = new ArrayList<TotalCustomerManagerHour>();
		for(TotalCustomerManagerHour total: actualSales) {
			if(total.getSales() == null) total.setSales(SpmConstants.BD_ZERO_VALUE);
			TotalCustomerManagerHour scheduleHour = getTotalHour(total.getRegion(), scheduleHours); 
			total.setSchedule((scheduleHour.getSchedule() != null)? scheduleHour.getSchedule(): SpmConstants.BD_ZERO_VALUE);
			TotalCustomerManagerHour targetHour = getTotalHour(total.getRegion(), targetHours);
			total.setTarget(((targetHour.getTarget() != null)? targetHour.getTarget(): SpmConstants.BD_ZERO_VALUE));
			totalHours.add(total);
		}
		
		return totalHours;
	}
	
	private TotalCustomerManagerHour getTotalHour(Region region, List<TotalCustomerManagerHour> totalManagerHours) {
		
		for(TotalCustomerManagerHour totalManagerHour: totalManagerHours) {
			if(region.getId().equals(totalManagerHour.getRegion().getId())) {
				return totalManagerHour;
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
	 * @param reportDao the reportDao to set
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
	 * @return the customerService
	 */
	public CustomerService getCustomerService() {
		return customerService;
	}


	/**
	 * @param customerService the customerService to set
	 */
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	
}

