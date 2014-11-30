package com.laborguru.action.report;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.laborguru.action.SpmAction;
import com.laborguru.model.DailyFlashSales;
import com.laborguru.model.DailyHistoricSales;
import com.laborguru.model.DailyStaffing;
import com.laborguru.model.Position;
import com.laborguru.model.Store;
import com.laborguru.model.StoreDailyFlashStaffing;
import com.laborguru.model.report.HalfHourIdealSales;
import com.laborguru.service.staffing.StaffingService;
import com.laborguru.service.store.StoreService;
import com.opensymphony.xwork2.Action;

public class DailyFlashIdealHoursAction extends SpmAction {
	
	private static final long serialVersionUID = 1L;
	
	private StoreService storeService;
	private StaffingService staffingService;
	
	
	private transient List<HalfHourIdealSales> sales;
	private transient String storeId;
	
	private DailyStaffing dailyStaffing;
	
	public String execute() {
		
		Date today = new Date();
		Store searchStore = new Store();
		searchStore.setId(Integer.parseInt(getStoreId()));
		
		Store store = getStoreService().getStoreById(searchStore);
		DailyFlashSales dailySalesValue = new DailyFlashSales();
		
		for(HalfHourIdealSales idealSales: getSales()) {
			dailySalesValue.addHalfHourFlashSales(idealSales.getHalfHourHistoricSales());
		}
		
		dailySalesValue.setSalesDate(today);
		dailySalesValue.setStore(store);
		
		StoreDailyFlashStaffing dailyFlashStaffing = staffingService.getDailyFlashSalesStaffingByDate(store,today, dailySalesValue);
		
		int numeroQueNecesitas = dailyFlashStaffing.getHalfHourStaffing(halfHour);
		
		return Action.SUCCESS;
		
	}
	
	private DailyStaffing calculateTotals(Map<Position, DailyStaffing> projections) {
		
		//DailyStaffing dailyStaffing = new DailyStaffing();
		for(DailyStaffing dsf: projections.values()){
			
		}
		return null;
	}

	public List<HalfHourIdealSales> getSales() {
		return sales;
	}

	public void setSales(List<HalfHourIdealSales> sales) {
		this.sales = sales;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public DailyStaffing getDailyStaffing() {
		return dailyStaffing;
	}

	public void setDailyStaffing(DailyStaffing dailyStaffing) {
		this.dailyStaffing = dailyStaffing;
	}

	public StoreService getStoreService() {
		return storeService;
	}

	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}

	public StaffingService getStaffingService() {
		return staffingService;
	}

	public void setStaffingService(StaffingService staffingService) {
		this.staffingService = staffingService;
	}
	
}
