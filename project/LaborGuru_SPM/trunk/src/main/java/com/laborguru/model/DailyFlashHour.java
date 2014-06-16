package com.laborguru.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

import com.laborguru.util.SpmConstants;

public class DailyFlashHour extends SpmObject {

	private static final long serialVersionUID = -1078811451960999576L;

	private Date day;
	private DayPart dayPart;
	private BigDecimal sales;
	private BigDecimal cumulSales;
	private BigDecimal actualSale;
	private BigDecimal cumulActualSale;
	private BigDecimal scheduleHour;
	private BigDecimal actualHour;

	private static final DecimalFormat df = new DecimalFormat("#,##0");
	
	
	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public DayPart getDayPart() {
		return dayPart;
	}

	public void setDayPart(DayPart dayPart) {
		this.dayPart = dayPart;
	}

	public BigDecimal getSales() {
		return sales;
	}

	public void setSales(BigDecimal sales) {
		this.sales = sales;
	}

	public BigDecimal getCumulSales() {
		return cumulSales;
	}

	public void setCumulSales(BigDecimal cumulSales) {
		this.cumulSales = cumulSales;
	}

	public BigDecimal getActualSale() {
		return actualSale;
	}

	public void setActualSale(BigDecimal actualSale) {
		this.actualSale = actualSale;
	}

	public BigDecimal getCumulActualSale() {
		return cumulActualSale;
	}

	public void setCumulActualSale(BigDecimal cumulActualSale) {
		this.cumulActualSale = cumulActualSale;
	}
	
	public BigDecimal getDifference() {
		return getActualSale().subtract(getSales());
	}
	
    public String getFormattedDifference() {
    	BigDecimal diff = getDifference();
    	return (diff.compareTo(SpmConstants.BD_ZERO_VALUE) < 0 ? "(" + df.format(diff.abs()) + ")" : df.format(diff));
    }
	
	public BigDecimal getCumulDifference() {
		return getCumulActualSale().subtract(getCumulSales());
	}

	public String getFormattedCumulDifference() {
		BigDecimal diff = getCumulDifference();
		return (diff.compareTo(SpmConstants.BD_ZERO_VALUE) < 0 ? "(" + df.format(diff.abs()) + ")" : df.format(diff));
	}
	
	public BigDecimal getScheduleHour() {
		return scheduleHour;
	}

	public void setScheduleHour(BigDecimal scheduleHour) {
		this.scheduleHour = scheduleHour;
	}

	public BigDecimal getActualHour() {
		return actualHour;
	}

	public void setActualHour(BigDecimal actualHour) {
		this.actualHour = actualHour;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(Object other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

}
