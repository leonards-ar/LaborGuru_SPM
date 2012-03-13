package com.laborguru.action.report;

import java.util.List;

import com.laborguru.model.Area;
import com.laborguru.model.report.TotalAreaManagerHour;
import com.laborguru.service.report.ReportAreaService;

public class ReportAreaPrepareAction extends ReportManagerBaseAction {

    private static final long serialVersionUID = 1L;

    private ReportAreaService reportAreaService;
    
    private List<TotalAreaManagerHour> totalAreaHours;
    
    private Area area;    
    @Override
    protected void performanceEfficiency() {
        setTotalAreaHours(reportAreaService.getPerformanceEfficiencyReport(getArea(), getStartDate(), getEndDate()));
    }

    @Override
    protected void schedulingEfficiency() {
        setTotalAreaHours(reportAreaService.getWeeklyTotalHours(getArea(), getStartDate(), getEndDate()));
    }

    @Override
    protected void scheduleExecutionEfficiency() {
        setTotalAreaHours(reportAreaService.getScheduleExecutionEfficiencyReport(getArea(), getStartDate(), getEndDate()));
    }

    @Override
    protected void forecastEfficiency() {
        setTotalAreaHours(reportAreaService.getForecastEfficiencyReport(getArea(), getStartDate(), getEndDate()));
    }

    /**
     * @param reportAreaService the reportAreaService to set
     */
    public void setReportAreaService(ReportAreaService reportAreaService) {
        this.reportAreaService = reportAreaService;
    }

    /**
     * @return the totalAreaHours
     */
    public List<TotalAreaManagerHour> getTotalAreaHours() {
        return totalAreaHours;
    }

    /**
     * @param totalAreaHours the totalAreaHours to set
     */
    public void setTotalAreaHours(List<TotalAreaManagerHour> totalAreaHours) {
        this.totalAreaHours = totalAreaHours;
    }
    
    /**
     * @return the region
     */
    public Area getArea() {
        if(area == null) {
            return super.getArea();
        }
        return area;
    }

    /**
     * @param region the region to set
     */
    public void setArea(Area area) {
        this.area= area;
    }
    
}
