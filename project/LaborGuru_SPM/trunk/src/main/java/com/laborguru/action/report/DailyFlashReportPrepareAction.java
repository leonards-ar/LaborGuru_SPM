package com.laborguru.action.report;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import com.laborguru.action.SpmActionResult;
import com.laborguru.model.DailyFlashHour;
import com.laborguru.util.CalendarUtils;
import com.laborguru.util.SpmConstants;
import com.opensymphony.xwork2.Preparable;

public class DailyFlashReportPrepareAction extends ScheduleReportPrepareAction implements Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7417726746002576268L;
	private List<DailyFlashHour> dailyFlashHours;
	private BigDecimal totalSales;
	private BigDecimal totalActualSales;
	private BigDecimal totalActualLabor;
	private BigDecimal partialActualLabor;
	private BigDecimal totalScheduleHour;
	private BigDecimal totalDifference;
	private DailyFlashHour lastDailyFlashHour;
	
	public void prepareShowReport(){
		
	}

	public void prepareShowFirstReport() {
		
	}

	public String showFirstReport() {
		Date now = new Date();
		
		setDailyFlashHours(getReportService().getDailyFlashReport(getEmployeeStore(), CalendarUtils.addOrSubstractHours(now, 0)));
		calculateTotals();
		return SpmActionResult.INPUT.getResult();
	}
	
	private void calculateTotals(){
		setTotalSales(SpmConstants.BD_ZERO_VALUE);
		setTotalActualSales(SpmConstants.BD_ZERO_VALUE);
		setTotalActualLabor(SpmConstants.BD_ZERO_VALUE);
		setPartialActualLabor(SpmConstants.BD_ZERO_VALUE);
		setTotalDifference(SpmConstants.BD_ZERO_VALUE);
		setTotalScheduleHour(SpmConstants.BD_ZERO_VALUE);
		setLastDailyFlashHour(null);
		
		List<DailyFlashHour> flashHours = getDailyFlashHours();
		for(int i=0; i < flashHours.size(); i++){
			DailyFlashHour fs =  flashHours.get(i); 
			setTotalSales(getTotalSales().add(fs.getSales()));
			if(fs.getActualSale() != null) {
				setTotalActualSales(getTotalActualSales().add(fs.getActualSale()));
				setTotalScheduleHour(getTotalScheduleHour().add(fs.getScheduleHour()));
				setPartialActualLabor(getPartialHalfActualLabor().add(fs.getActualHour()));
				setTotalDifference(getTotalDifference().add(fs.getDifference()));
			}else {
				
				if(getLastDailyFlashHour() == null){
					setLastDailyFlashHour( i == 0 ? flashHours.get(i) : flashHours.get(i-1));
				}
			}
			setTotalActualLabor(getTotalHalfActualLabor().add(fs.getActualHour()));
			setTotalScheduleHour(getTotalHalfScheduleHour().add(fs.getScheduleHour()));
		}
	}

	public void prepare() throws Exception {
	}

	public List<DailyFlashHour> getDailyFlashHours() {
		return dailyFlashHours;
	}

	public void setDailyFlashHours(List<DailyFlashHour> dailyFlashHours) {
		this.dailyFlashHours = dailyFlashHours;
	}

	public BigDecimal getTotalSales() {
		return totalSales;
	}

	public void setTotalSales(BigDecimal totalSales) {
		this.totalSales = totalSales;
	}

	public BigDecimal getTotalActualSales() {
		return totalActualSales;
	}

	
	public BigDecimal getTotalHalfScheduleHour(){
		return totalScheduleHour;
	}
	
	public BigDecimal getTotalScheduleHour() {
		return totalScheduleHour.divide(new BigDecimal(2), 0, 0);
	}

	public void setTotalScheduleHour(BigDecimal totalScheduleHour) {
		this.totalScheduleHour = totalScheduleHour;
	}
	
	public void setTotalActualSales(BigDecimal totalActualSales) {
		this.totalActualSales = totalActualSales;
	}

	public BigDecimal getTotalHalfActualLabor(){
		return totalActualLabor;
	}
	
	public BigDecimal getTotalActualLabor() {
		return totalActualLabor.divide(new BigDecimal(2), 0, 0);
	}

	public void setTotalActualLabor(BigDecimal totalActualLabor) {
		this.totalActualLabor = totalActualLabor;
	}
	
	public BigDecimal getPartialHalfActualLabor() {
		return partialActualLabor;
	}
	
	public BigDecimal getPartialActualLabor() {
		return partialActualLabor.divide(new BigDecimal(2), 0, 0);
	}

	public void setPartialActualLabor(BigDecimal partialActualLabor) {
		this.partialActualLabor = partialActualLabor;
	}

	public BigDecimal getTotalDifference() {
		return totalDifference;
	}

	public void setTotalDifference(BigDecimal totalDifference) {
		this.totalDifference = totalDifference;
	}
	
	public String getTotalFormattedDifference(){
		BigDecimal diff = getTotalDifference();
		return (diff.compareTo(SpmConstants.BD_ZERO_VALUE) < 0 ? "(" + (new DecimalFormat("#,##0")).format(diff.abs()) + ")" : (new DecimalFormat("#,##0")).format(diff));		
	}

	public DailyFlashHour getLastDailyFlashHour() {
		return lastDailyFlashHour;
	}

	public void setLastDailyFlashHour(DailyFlashHour lastDailyFlashHour) {
		this.lastDailyFlashHour = lastDailyFlashHour;
	}
	
	public BigDecimal getForecast(){
		if(getLastDailyFlashHour().getCumulDifference().compareTo(SpmConstants.BD_ZERO_VALUE) != 0){
			return getLastDailyFlashHour().getCumulDifference().divide(getLastDailyFlashHour().getCumulSales()).multiply(new BigDecimal(100));
		}
		return SpmConstants.BD_ZERO_VALUE;
	}
	
	public BigDecimal getPercentOfDayLeft(){
		if(getTotalDifference().compareTo(SpmConstants.BD_ZERO_VALUE) != 0){
			return new BigDecimal("1").subtract(getLastDailyFlashHour().getCumulDifference().divide(getTotalSales(),0).abs()).multiply(new BigDecimal(100));
		}
		return SpmConstants.BD_ZERO_VALUE;
	}

}
