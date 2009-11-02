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
import com.laborguru.model.Location;
import com.laborguru.model.Region;
import com.laborguru.model.Store;
import com.laborguru.model.StoreDailyHistoricSalesStaffing;
import com.laborguru.model.report.TotalManagerHour;
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

	public List<TotalManagerHour> getPerformanceEfficiencyReport(Customer customer, Date start, Date end) {

		try{
			
			List<TotalManagerHour> actualSales = reportDao.getActualSalesByCustomer(customer, start, end);
			List<TotalManagerHour> actualHours = reportDao.getActualHoursByCustomer(customer, start, end);			
			List<TotalManagerHour> minimumStaffing = getActualMinimumStaffing(customer, start, end);
		
			return merge(actualSales, actualHours, minimumStaffing);
		
		} catch(SQLException e) {
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
	private List<TotalManagerHour> getActualMinimumStaffing(Customer customer, Date startDate, Date endDate){
		List<TotalManagerHour> totalHours = new ArrayList<TotalManagerHour>();
		
		for(Region region: customer.getRegions()) {
			TotalManagerHour totalManagerHour = new TotalManagerHour();
			totalManagerHour.setLocation(region);
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

	private List<TotalManagerHour> merge(List<TotalManagerHour> actualSales, List<TotalManagerHour> scheduleHours, List<TotalManagerHour> targetHours) {
		
		List<TotalManagerHour> totalHours = new ArrayList<TotalManagerHour>();
		for(TotalManagerHour total: actualSales) {
			TotalManagerHour scheduleHour = getTotalHour(total.getLocation(), scheduleHours); 
			total.setSchedule((scheduleHour.getSchedule() != null)? scheduleHour.getSchedule(): SpmConstants.BD_ZERO_VALUE);
			TotalManagerHour targetHour = getTotalHour(total.getLocation(), targetHours);
			total.setTarget(((targetHour.getTarget() != null)? targetHour.getTarget(): SpmConstants.BD_ZERO_VALUE));
			totalHours.add(total);
		}
		
		return totalHours;
	}
	
	private TotalManagerHour getTotalHour(Location location, List<TotalManagerHour> totalManagerHours) {
		
		for(TotalManagerHour totalManagerHour: totalManagerHours) {
			if(location.getId().equals(totalManagerHour.getLocation().getId())) {
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
	

}
