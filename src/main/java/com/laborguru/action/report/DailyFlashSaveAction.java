package com.laborguru.action.report;

import java.util.Date;
import java.util.List;

import com.laborguru.action.SpmAction;
import com.laborguru.exception.SpmCheckedException;
import com.laborguru.model.DailyFlash;
import com.laborguru.model.DailyFlashDetail;
import com.laborguru.model.Store;
import com.laborguru.service.report.DailyFlashService;
import com.laborguru.service.store.StoreService;
import com.opensymphony.xwork2.Action;

public class DailyFlashSaveAction extends SpmAction{
	
	private static final long serialVersionUID = 1L;
	private transient DailyFlashService dailyFlashService;
	private transient StoreService storeService;
	
	private transient String preOpenHour;
	private transient String closeHour;
	private transient List<DailyFlashDetail> details;
	private transient String storeId;
	
	private String responseMessage;
	
	public void setPreOpenHour(String preOpenHour) {
		this.preOpenHour = preOpenHour;
	}
	
	public String getPreOpenHour() {
		return preOpenHour;
	}
	
	public String getCloseHour() {
		return closeHour;
	}

	public void setCloseHour(String closeHour) {
		this.closeHour = closeHour;
	}

	public List<DailyFlashDetail> getDetails() {
		return details;
	}


	public void setDetails(List<DailyFlashDetail> details) {
		this.details = details;
	}

	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	
	public DailyFlashService getDailyFlashService() {
		return dailyFlashService;
	}

	public void setDailyFlashService(DailyFlashService dailyFlashService) {
		this.dailyFlashService = dailyFlashService;
	}

	public StoreService getStoreService() {
		return storeService;
	}

	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String execute(){
		try {
			Store searchStore = new Store();
			searchStore.setId(Integer.parseInt(getStoreId()));
			
			Store store = storeService.getStoreById(searchStore);
			Date today = new Date();
			DailyFlash dailyFlash = getDailyFlashService().getDailyFlashByDate(store,today);
			if(dailyFlash == null){
				dailyFlash = new DailyFlash();
				if(!"".equals(getPreOpenHour())) dailyFlash.setOpenHours(Double.parseDouble(getPreOpenHour()));
				if(!"".equals(getCloseHour())) dailyFlash.setCloseHours(Double.parseDouble(getCloseHour()));
				dailyFlash.setStore(store);
				dailyFlash.setDate(today);
			} else {
				if(!"".equals(getPreOpenHour())) dailyFlash.setOpenHours(Double.parseDouble(getPreOpenHour()));
				if(!"".equals(getCloseHour())) dailyFlash.setCloseHours(Double.parseDouble(getCloseHour()));
			}

			for(DailyFlashDetail dfDetail: getDetails()){
				if(!dfDetail.isEmpty()){
					dailyFlash.addDailyFlashDetail(dfDetail);
				}
			}
			
			getDailyFlashService().save(dailyFlash);
		
			setResponseMessage(getText("report.dailyFlashReport.save.success"));
			return Action.SUCCESS;
		}catch(SpmCheckedException e){
			setResponseMessage(e.getMessage());
			e.printStackTrace();
			return Action.ERROR;
		}
	}

}
