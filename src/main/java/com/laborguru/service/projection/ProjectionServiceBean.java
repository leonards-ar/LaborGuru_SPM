package com.laborguru.service.projection;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.laborguru.model.DailyProjection;
import com.laborguru.model.Store;
import com.laborguru.service.projection.dao.ProjectionDao;

/**
 *
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class ProjectionServiceBean implements ProjectionService {

	private ProjectionDao projectionDao;

	public List<BigDecimal> getAvgDailyProjectionForAWeek(Integer numberOfWeeks, Store store, Date startWeekDate) {
		
		List<BigDecimal> retProjections = projectionDao.getAvgDailyProjectionForAWeek(numberOfWeeks, store, startWeekDate);
		
		if (retProjections == null  || retProjections.isEmpty()){
			retProjections = new ArrayList<BigDecimal>(7);
		
			for (int i=0; i<7; i++){
				retProjections.add(new BigDecimal("0"));
			}
		}
		
		return retProjections;
	}


	public List<BigDecimal> getAdjustedDailyProjectionForAWeek(Store store, Date startWeekDate) {		
		
		List<DailyProjection> projections = projectionDao.getAdjustedDailyProjectionForAWeek(store, startWeekDate);
		List<BigDecimal> retProjections = new ArrayList<BigDecimal>(7);
		
		if (projections == null  || projections.isEmpty()){
		
			for (int i=0; i<7; i++){
				retProjections.add(new BigDecimal("0"));
			}
		} else {
			for (DailyProjection aProjection: projections){
				retProjections.add(aProjection.getDailyProjectionValue());
			}			
		}
		
		return retProjections;	
	}

	/**
	 * @return the projectionDao
	 */
	public ProjectionDao getProjectionDao() {
		return projectionDao;
	}

	/**
	 * @param projectionDao the projectionDao to set
	 */
	public void setProjectionDao(ProjectionDao projectionDao) {
		this.projectionDao = projectionDao;
	}	
}
