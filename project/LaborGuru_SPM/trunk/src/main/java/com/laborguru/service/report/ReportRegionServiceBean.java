package com.laborguru.service.report;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.laborguru.exception.ErrorEnum;
import com.laborguru.exception.SpmUncheckedException;
import com.laborguru.model.Area;
import com.laborguru.model.DailyProjection;
import com.laborguru.model.Region;
import com.laborguru.model.Store;
import com.laborguru.model.StoreDailyHistoricSalesStaffing;
import com.laborguru.model.report.TotalRegionManagerHour;
import com.laborguru.service.projection.ProjectionService;
import com.laborguru.service.region.RegionService;
import com.laborguru.service.report.dao.ReportDao;
import com.laborguru.service.staffing.StaffingService;
import com.laborguru.util.CalendarUtils;
import com.laborguru.util.SpmConstants;

public class ReportRegionServiceBean implements ReportRegionService {
	
public static Logger log = Logger.getLogger(ReportCustomerServiceBean.class);
	
	private ReportDao reportDao;
	private StaffingService staffingService;
	private RegionService regionService;
	private ProjectionService projectionService;	

	public List<TotalRegionManagerHour> getForecastEfficiencyReport(Region region, Date start, Date end) {
		try{
			List<TotalRegionManagerHour> actualSales = reportDao.getActualSalesByRegion(region, start, end);
			List<TotalRegionManagerHour> projections = getDailyProjections(region, start, end);
			
			return merge(actualSales, actualSales, projections);
		}catch (SQLException e) {
			log.error("An SQLError has occurred", e);
			throw new SpmUncheckedException(e.getCause(), e.getMessage(),
					ErrorEnum.GENERIC_DATABASE_ERROR);
		}
	}

	public List<TotalRegionManagerHour> getPerformanceEfficiencyReport(Region region, Date start, Date end) {

		try{
			
			List<TotalRegionManagerHour> actualSales = getReportDao().getActualSalesByRegion(region, start, end);
			List<TotalRegionManagerHour> actualHours = getReportDao().getActualHoursByRegion(region, start, end);			
			List<TotalRegionManagerHour> minimumStaffing = getActualMinimumStaffing(region, start, end);
		
			return merge(actualSales, actualHours, minimumStaffing);
		
		} catch(SQLException e) {
			log.error("An SQLError has occurred", e);
			throw new SpmUncheckedException(e.getCause(), e.getMessage(),
					ErrorEnum.GENERIC_DATABASE_ERROR);
		}
	}

	public List<TotalRegionManagerHour> getScheduleExecutionEfficiencyReport(Region region, Date start, Date end) {
		try{

			List<TotalRegionManagerHour> actualSales = getReportDao().getActualSalesByRegion(region, start, end);
			List<TotalRegionManagerHour> scheduleTotalHours = getReportDao().getScheduleTotalHourByRegion(region, start, end);
			List<TotalRegionManagerHour> actualHours = getReportDao().getActualHoursByRegion(region, start, end);
		
			return merge(actualSales, scheduleTotalHours, actualHours);
		} catch (SQLException e) {
			log.error("An SQLError has occurred", e);
			throw new SpmUncheckedException(e.getCause(), e.getMessage(),
					ErrorEnum.GENERIC_DATABASE_ERROR);
		}		
	}

	public List<TotalRegionManagerHour> getWeeklyTotalHours(Region region,Date start, Date end) {
		try {
			List<TotalRegionManagerHour> actualSales = getReportDao().getActualSalesByRegion(region, start, end);
			List<TotalRegionManagerHour> scheduleTotalHours = getReportDao().getScheduleTotalHourByRegion(region, start, end);
			List<TotalRegionManagerHour> targetTotalHours = getReportDao().getTargetTotalHourByRegion(region, start, end);
			
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
	private List<TotalRegionManagerHour> getActualMinimumStaffing(Region region, Date startDate, Date endDate){
		List<TotalRegionManagerHour> totalHours = new ArrayList<TotalRegionManagerHour>();
		
		Region tmpRegion = getRegionService().getRegionById(region);
		for(Area area: tmpRegion.getAreas()) {
			TotalRegionManagerHour totalManagerHour = new TotalRegionManagerHour();
			totalManagerHour.setArea(area);
			totalHours.add(totalManagerHour);
			BigDecimal targetArea = SpmConstants.BD_ZERO_VALUE;
			for(Store store: region.getStores()) {
				BigDecimal targetStores = SpmConstants.BD_ZERO_VALUE;
				for(Date date = startDate; endDate.after(date); date = CalendarUtils.addOrSubstractDays(date,1)){
					StoreDailyHistoricSalesStaffing saleStaffing = getStaffingService().getDailyHistoricSalesStaffingByDate(store, date);
					targetStores = targetStores.add(new BigDecimal(saleStaffing.getTotalDailyTarget()));
				}
				targetArea = targetArea.add(targetStores);
			}
			totalManagerHour.setSchedule(targetArea);
		}

		return totalHours;
	}

	private List<TotalRegionManagerHour> getDailyProjections(Region region, Date startDate, Date endDate){
		List<TotalRegionManagerHour> totalHours = new ArrayList<TotalRegionManagerHour>();
		
		Region tmpRegion = getRegionService().getRegionById(region);
		for(Area area: tmpRegion.getAreas()) {
			TotalRegionManagerHour totalManagerHour = new TotalRegionManagerHour();
			totalManagerHour.setArea(area);
			totalHours.add(totalManagerHour);
			BigDecimal targetArea = SpmConstants.BD_ZERO_VALUE;
			for(Store store: area.getStores()) {
				BigDecimal targetStores = SpmConstants.BD_ZERO_VALUE;
				for(Date date = startDate; endDate.after(date); date = CalendarUtils.addOrSubstractDays(date,1)){
					DailyProjection dailyProjection = getProjectionService().getDailyProjection(store, date);
					targetStores = targetStores.add(dailyProjection != null? dailyProjection.getDailyProjectionValue() :  SpmConstants.BD_ZERO_VALUE);
				}
				targetArea = targetArea.add(targetStores);
			}
			totalManagerHour.setSchedule(targetArea);
		}
		return totalHours;
	}	
	private List<TotalRegionManagerHour> merge(List<TotalRegionManagerHour> actualSales, List<TotalRegionManagerHour> scheduleHours, List<TotalRegionManagerHour> targetHours) {
		
		List<TotalRegionManagerHour> totalHours = new ArrayList<TotalRegionManagerHour>();
		for(TotalRegionManagerHour total: actualSales) {
			if(total.getSales() == null) total.setSales(SpmConstants.BD_ZERO_VALUE);
			TotalRegionManagerHour scheduleHour = getTotalHour(total.getArea(), scheduleHours); 
			total.setSchedule((scheduleHour.getSchedule() != null)? scheduleHour.getSchedule(): SpmConstants.BD_ZERO_VALUE);
			TotalRegionManagerHour targetHour = getTotalHour(total.getArea(), targetHours);
			total.setTarget(((targetHour.getSchedule() != null)? targetHour.getSchedule(): SpmConstants.BD_ZERO_VALUE));
			totalHours.add(total);
		}
		
		return totalHours;
	}	
	
	private TotalRegionManagerHour getTotalHour(Area area, List<TotalRegionManagerHour> totalManagerHours) {
		
		for(TotalRegionManagerHour totalManagerHour: totalManagerHours) {
			if(area.getId().equals(totalManagerHour.getArea().getId())) {
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
	 * @return the regionService
	 */
	public RegionService getRegionService() {
		return regionService;
	}

	/**
	 * @param regionService the regionService to set
	 */
	public void setRegionService(RegionService regionService) {
		this.regionService = regionService;
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
