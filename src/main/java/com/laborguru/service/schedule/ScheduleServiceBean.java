/*
 * File name: ScheduleServiceBean.java
 * Creation date: Oct 4, 2008 4:06:28 PM
 * Copyright Mindpool
 */
package com.laborguru.service.schedule;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.laborguru.model.EmployeeSchedule;
import com.laborguru.model.Shift;
import com.laborguru.model.Store;
import com.laborguru.model.StoreSchedule;
import com.laborguru.service.schedule.dao.ScheduleDao;
import com.laborguru.util.CalendarUtils;

/**
 *
 * @author <a href="mcapurro@gmail.com">Mariano Capurro</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class ScheduleServiceBean implements ScheduleService {
	private ScheduleDao scheduleDao;
	
	/**
	 * 
	 */
	public ScheduleServiceBean() {
	}

	/**
	 * @param store
	 * @param date
	 * @return
	 * @see com.laborguru.service.schedule.ScheduleService#getStoreScheduleByDate(com.laborguru.model.Store, java.util.Date)
	 */
	public StoreSchedule getStoreScheduleByDate(Store store, Date date) {
		return getScheduleDao().getStoreScheduleByDate(store, date);
	}

	/**
	 * @param schedule
	 * @return
	 * @see com.laborguru.service.schedule.ScheduleService#save(com.laborguru.model.StoreSchedule)
	 */
	public StoreSchedule save(StoreSchedule schedule) {
		return getScheduleDao().save(schedule);
	}

	/**
	 * @return the scheduleDao
	 */
	public ScheduleDao getScheduleDao() {
		return scheduleDao;
	}

	/**
	 * @param scheduleDao the scheduleDao to set
	 */
	public void setScheduleDao(ScheduleDao scheduleDao) {
		this.scheduleDao = scheduleDao;
	}

	/**
	 * 
	 * @param schedule
	 * @return
	 * @see com.laborguru.service.schedule.ScheduleService#detach(com.laborguru.model.StoreSchedule)
	 */
	public StoreSchedule detach(StoreSchedule schedule) {
		return getScheduleDao().detach(schedule);
	}

	/**
	 * 
	 * @param store
	 * @param startDate
	 * @param endDate
	 * @return
	 * @see com.laborguru.service.schedule.ScheduleService#getTotalScheduledHoursForTimePeriod(com.laborguru.model.Store, java.util.Date, java.util.Date)
	 */
	public BigDecimal getTotalScheduledHoursForTimePeriod(Store store,
			Date startDate, Date endDate) {
		return getScheduleDao().getTotalScheduledHoursForTimePeriod(store, startDate, endDate);
	}

	/**
	 * 
	 * @param store
	 * @param startDate
	 * @param endDate
	 * @return
	 * @see com.laborguru.service.schedule.ScheduleService#getTotalScheduledLaborCostForTimePeriod(com.laborguru.model.Store, java.util.Date, java.util.Date)
	 */
	public BigDecimal getTotalScheduledLaborCostForTimePeriod(Store store,
			Date startDate, Date endDate) {
		return getScheduleDao().getTotalScheduledLaborCostForTimePeriod(store, startDate, endDate);
	}

	/**
	 * 
	 * @param store
	 * @param startDate
	 * @param endDate
	 * @return
	 * @see com.laborguru.service.schedule.ScheduleService#getTotalScheduledHoursByPositionForTimePeriod(com.laborguru.model.Store, java.util.Date, java.util.Date)
	 */
	public Map<Integer, BigDecimal> getTotalScheduledHoursByPositionForTimePeriod(
			Store store, Date startDate, Date endDate) {
		return getScheduleDao().getTotalScheduledHoursByPositionForTimePeriod(store, startDate, endDate);
	}
	
	/**
	 * 
	 * 
	 * @see com.laborguru.service.schedule.ScheduleService#updateAllStoreSchedules()
	 */
	public void updateAllStoreSchedules() {
		List<StoreSchedule> schedules = getScheduleDao().getAllStoreSchedules();
		
		for(StoreSchedule aSchedule : schedules) {
			for(EmployeeSchedule employeeSchedule : aSchedule.getEmployeeSchedules()) {
				employeeSchedule.reindexShifts();
			}			
			getScheduleDao().save(aSchedule);
		}
	}

	public StoreSchedule copyScheduleTo(StoreSchedule sourceSchedule, Date date, StoreSchedule destinationNextDaySchedule) {
		StoreSchedule destinationSchedule = getStoreScheduleByDate(sourceSchedule.getStore(), date);
		if(destinationSchedule == null) {
			destinationSchedule = new StoreSchedule();
			destinationSchedule.setDay(date);
			destinationSchedule.setStore(sourceSchedule.getStore());
		}
		
		copyEmployeeSchedule(sourceSchedule, destinationSchedule, destinationNextDaySchedule);
		
		return destinationSchedule;
	}
	
	private void copyEmployeeSchedule(StoreSchedule sourceSchedule, StoreSchedule destinationSchedule, StoreSchedule destinationNextDaySchedule) {
		prepareEmployeeSchedule(destinationSchedule, sourceSchedule.getEmployeeSchedules().size());
		
		Iterator<EmployeeSchedule> srcIt = sourceSchedule.getEmployeeSchedules().iterator();
		Iterator<EmployeeSchedule> destIt = destinationSchedule.getEmployeeSchedules().iterator();
		while(srcIt.hasNext() && destIt.hasNext()) {
			EmployeeSchedule src = srcIt.next();
			EmployeeSchedule dest = destIt.next();
			
			dest.setEmployee(src.getEmployee());
			dest.setStoreSchedule(destinationSchedule);
			
			copyShifts(src, dest, destinationNextDaySchedule.getEmployeeSchedule(src.getEmployee()));
		}
	}

	private void copyShifts(EmployeeSchedule sourceEmployeeSchedule, EmployeeSchedule destinationEmployeeSchedule, EmployeeSchedule destinationNextDayEmployeeSchedule) {
		prepareShifts(destinationEmployeeSchedule, sourceEmployeeSchedule.getShifts().size());
		
		for(int i = 0; i < sourceEmployeeSchedule.getShifts().size(); i++) {
			Shift src = sourceEmployeeSchedule.getShifts().get(i);
			Shift dest = destinationEmployeeSchedule.getShifts().get(i);
			
			dest.setClosingHours(src.getClosingHours());
			dest.setFromHour(src.getFromHour());
			dest.setOpeningHours(src.getOpeningHours());
			dest.setPosition(src.getPosition());
			dest.setServiceHours(src.getServiceHours());
			dest.setToHour(src.getToHour());
			
			if(src.hasContiguousShift()) {
				Shift candidateShif = destinationNextDayEmployeeSchedule.getFirstShiftFor(src.getPosition());
				if(CalendarUtils.equalsTime(candidateShif.getFromHour(), dest.getToHour())) {
					dest.setContiguousShift(candidateShif);
				}
			}
		}
	}
	
	private void prepareShifts(EmployeeSchedule destinationEmployeeSchedule, int size) {
		if(size > destinationEmployeeSchedule.getShifts().size()) {
			for(int i = 0; i < size - destinationEmployeeSchedule.getShifts().size(); i++) {
				destinationEmployeeSchedule.addShift(new Shift());
			}			
		} else if(size < destinationEmployeeSchedule.getShifts().size()) {
			for(int i = 0; i < destinationEmployeeSchedule.getShifts().size() - size; i++) {
				destinationEmployeeSchedule.removeLastShift();
			}				
		}
	}
	
	private void prepareEmployeeSchedule(StoreSchedule destinationSchedule, int size) {
		if(size > destinationSchedule.getEmployeeSchedules().size()) {
			for(int i = 0; i < size - destinationSchedule.getEmployeeSchedules().size(); i++) {
				destinationSchedule.getEmployeeSchedules().add(new EmployeeSchedule());
			}
		} else if(size < destinationSchedule.getEmployeeSchedules().size()) {
			Iterator<EmployeeSchedule> it = destinationSchedule.getEmployeeSchedules().iterator();
			for(int i = 0; i < destinationSchedule.getEmployeeSchedules().size() - size; i++) {
				it.next();
				it.remove();
			}
		}
	}
}
