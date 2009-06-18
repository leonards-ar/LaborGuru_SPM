package com.laborguru.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.laborguru.exception.ErrorEnum;
import com.laborguru.exception.SpmUncheckedException;
import com.laborguru.model.report.TotalHour;

public class FusionXmlDataConverter {

	private static Logger log = Logger.getLogger(FusionXmlDataConverter.class);

	private HashMap<String, String> reportConfigurations;

	private static final String WEEKLY_TOTAL_HOURS_REPORT = "weeklyTotalHoursReport";
	private static final String DAILY_HALFHOUR_REPORT = "dailyHalfhourReport";
	private static final String HISTORICAL_COMPARISON_REPORT = "historicalComparisonReport";
	
	public String weeklyTotalHoursXmlConverter(List<TotalHour> totalHours, String scheduleAxisName, String targetAxisName, ResourceBundle bundle) {

		Document document = DocumentHelper.createDocument();
		Element graph = document.addElement("graph");
		Element categories = graph.addElement("categories");
		Element scheduleDataset = graph.addElement("dataset");
		Element targetDataset = graph.addElement("dataset");
		Properties props = null;

		try {
			props = getProperties(WEEKLY_TOTAL_HOURS_REPORT);
		} catch (IOException e) {
			log.error("No file found", e);
			throw new SpmUncheckedException(e.getCause(), e.getMessage(), ErrorEnum.GENERIC_ERROR);
		}
		SimpleDateFormat sdf = new SimpleDateFormat(props.getProperty("dateFormat"));

		graph.addAttribute("caption", bundle.getString("report.weeklyTotalHours.title"));
		graph.addAttribute("PYAxisMinValue", props.getProperty("defaultPYAxisMinValue"));
		graph.addAttribute("SYAxisMinValue", props.getProperty("defaultSYAxisMinValue"));
		graph.addAttribute("PYAxisName", bundle.getString("report.weeklyTotalHour.axisname"));
		graph.addAttribute("showDivLineSecondaryValue", "0");
		graph.addAttribute("showSecondaryLimits", "0");
		graph.addAttribute("showvalues", props.getProperty("showvalues"));
		graph.addAttribute("numDivLines", props.getProperty("numDivLines"));
		graph.addAttribute("formatNumberScale", props.getProperty("formatNumberScale"));
		graph.addAttribute("decimalPrecision", props.getProperty("decimalPrecision"));
		graph.addAttribute("anchorSides", props.getProperty("anchorSides"));
		graph.addAttribute("anchorRadius", props.getProperty("anchorRadius"));
		graph.addAttribute("anchorBorderColor", props.getProperty("anchorBorderColor"));
		graph.addAttribute("showYAxisValues","1");
		

		scheduleDataset.addAttribute("seriesName", bundle.getString(scheduleAxisName));
		scheduleDataset.addAttribute("color", props.getProperty("scheduleColor"));
		scheduleDataset.addAttribute("showValues", props.getProperty("scheduleShowValues"));

		targetDataset.addAttribute("seriesName", bundle.getString(targetAxisName));
		targetDataset.addAttribute("color", props.getProperty("targetColor"));
		targetDataset.addAttribute("showValues", props.getProperty("targetShowValues"));
		targetDataset.addAttribute("parentYAxis", "S");
		BigDecimal maxValue = SpmConstants.BD_ZERO_VALUE;
		for (TotalHour th : totalHours) {
			// Add column
			Element element = categories.addElement("category");
			element.addAttribute("name", sdf.format(th.getDay()));

			// Add schedule value
			element = scheduleDataset.addElement("set");
			element.addAttribute("value", th.getSchedule().toPlainString());
			//element.addAttribute("link", "http://www.google.com.ar");
			maxValue = maxValue.max(th.getSchedule());

			// Add target value
			element = targetDataset.addElement("set");
			element.addAttribute("value", th.getTarget().toPlainString());
			//element.addAttribute("link", "http://www.google.com.ar");
			maxValue = maxValue.max(th.getTarget());

		}
		
		BigDecimal defaultMaxValue = new BigDecimal(props.getProperty("defaultPYAxisMaxValue"));
		if(maxValue.compareTo(defaultMaxValue) > 0) {
			BigDecimal step = new BigDecimal(props.getProperty("step"));			
			long graphMaxValue = calculateMaxValue(maxValue.longValue(), step.longValue());
			graph.addAttribute("PYAxisMaxValue", String.valueOf(graphMaxValue));
			graph.addAttribute("SYAxisMaxValue", String.valueOf(graphMaxValue));
		} else {
			graph.addAttribute("PYAxisMaxValue", props.getProperty("defaultPYAxisMaxValue"));
			graph.addAttribute("SYAxisMaxValue", props.getProperty("defaultSYAxisMaxValue"));
		}
		
		if (log.isDebugEnabled()) {
			log.debug(document.asXML());
		}
		return document.asXML();
	}

	public String halfHoursXmlConverter(List<TotalHour> totalHours, ResourceBundle bundle) {

		Document document = DocumentHelper.createDocument();
		Element graph = document.addElement("graph");
		Element categories = graph.addElement("categories");
		Element scheduleDataset = graph.addElement("dataset");
		Element targetDataset = graph.addElement("dataset");
		Properties props = null;

		try {
			props = getProperties(DAILY_HALFHOUR_REPORT);
		} catch (IOException e) {
			log.error("No file found", e);
			throw new SpmUncheckedException(e.getCause(), e.getMessage(), ErrorEnum.GENERIC_ERROR);
		}
		SimpleDateFormat sdf = new SimpleDateFormat(props.getProperty("dateFormat"));

		graph.addAttribute("caption", bundle.getString("report.dailyHalfHour.title"));
		graph.addAttribute("PYAxisMinValue", props.getProperty("defaultPYAxisMinValue"));
		graph.addAttribute("SYAxisMinValue", props.getProperty("defaultSYAxisMinValue"));
		graph.addAttribute("showYAxisValue", "0");
		graph.addAttribute("PYAxisName", bundle.getString("report.weeklyTotalHour.axisname"));
		graph.addAttribute("showDivLineSecondaryValue", "0");
		graph.addAttribute("showSecondaryLimits", "0");
		graph.addAttribute("showvalues", props.getProperty("showvalues"));
		graph.addAttribute("numDivLines", props.getProperty("numDivLines"));
		graph.addAttribute("formatNumberScale", props.getProperty("formatNumberScale"));
		graph.addAttribute("decimalPrecision", props.getProperty("decimalPrecision"));
		graph.addAttribute("anchorSides", props.getProperty("anchorSides"));
		graph.addAttribute("anchorRadius", props.getProperty("anchorRadius"));
		graph.addAttribute("anchorBorderColor", props.getProperty("anchorBorderColor"));
		graph.addAttribute("showYAxisValues","1");
		graph.addAttribute("rotateNames", "1");

		scheduleDataset.addAttribute("seriesName", bundle.getString("report.dailyHalfHour.series.schedule"));
		scheduleDataset.addAttribute("color", props.getProperty("scheduleColor"));
		scheduleDataset.addAttribute("showValues", props.getProperty("scheduleShowValues"));

		targetDataset.addAttribute("seriesName", bundle.getString("report.dailyHalfHour.series.target"));
		targetDataset.addAttribute("color", props.getProperty("targetColor"));
		targetDataset.addAttribute("showValues", props.getProperty("targetShowValues"));
		targetDataset.addAttribute("parentYAxis", "S");

		BigDecimal maxValue = SpmConstants.BD_ZERO_VALUE;
		for (int i=0; i < totalHours.size(); i++ ) {
			// Add column
			TotalHour th = totalHours.get(i);
			Element element = categories.addElement("category");
			element.addAttribute("name", sdf.format(th.getDay()));

			// Add schedule value
			element = scheduleDataset.addElement("set");
			element.addAttribute("value", th.getSchedule().toPlainString());
			//element.addAttribute("link", "http://www.google.com.ar");
			maxValue = maxValue.max(th.getSchedule());
			// Add target value
			element = targetDataset.addElement("set");
			element.addAttribute("value", th.getTarget().toPlainString());
			//element.addAttribute("link", "http://www.google.com.ar");
			maxValue = maxValue.max(th.getTarget());
		}

		BigDecimal defaultMaxValue = new BigDecimal(props.getProperty("defaultPYAxisMaxValue"));
		if(maxValue.compareTo(defaultMaxValue) > 0) {
			BigDecimal step = new BigDecimal(props.getProperty("step"));			
			long graphMaxValue = calculateMaxValue(maxValue.longValue(), step.longValue());
			graph.addAttribute("PYAxisMaxValue", String.valueOf(graphMaxValue));
			graph.addAttribute("SYAxisMaxValue", String.valueOf(graphMaxValue));
		} else {
			graph.addAttribute("PYAxisMaxValue", props.getProperty("defaultPYAxisMaxValue"));
			graph.addAttribute("SYAxisMaxValue", props.getProperty("defaultSYAxisMaxValue"));
		}
		if (log.isDebugEnabled()) {
			log.debug(document.asXML());
		}
		return document.asXML();
	}

	public String historicalComparisonXmlConverter(List<TotalHour> totalHours, ResourceBundle bundle, String scheduleLegend, String targetLegend, String yAxisName){

		Document document = DocumentHelper.createDocument();
		Element graph = document.addElement("graph");
		Element categories = graph.addElement("categories");
		Element scheduleDataset = graph.addElement("dataset");
		Element targetDataset = graph.addElement("dataset");		
		Properties props = null;

		try {
			props = getProperties(HISTORICAL_COMPARISON_REPORT);
		} catch (IOException e) {
			log.error("No file found", e);
			throw new SpmUncheckedException(e.getCause(), e.getMessage(), ErrorEnum.GENERIC_ERROR);
		}
		SimpleDateFormat sdf = new SimpleDateFormat(props.getProperty("dateFormat"));

		graph.addAttribute("caption", "");
		graph.addAttribute("yAxisMinValue", props.getProperty("defaultYAxisMinValue"));
		graph.addAttribute("yAxisMaxValue", props.getProperty("defaultYAxisMaxValue"));
		graph.addAttribute("yAxisName", bundle.getString(yAxisName));
		graph.addAttribute("showvalues", props.getProperty("showvalues"));
		graph.addAttribute("numDivLines", props.getProperty("numDivLines"));
		graph.addAttribute("formatNumberScale", props.getProperty("formatNumberScale"));
		graph.addAttribute("decimalPrecision", props.getProperty("decimalPrecision"));
		graph.addAttribute("forceDecimals", props.getProperty("forceDecimals"));
		graph.addAttribute("anchorSides", props.getProperty("anchorSides"));
		graph.addAttribute("anchorRadius", props.getProperty("anchorRadius"));
		graph.addAttribute("anchorBorderColor", props.getProperty("anchorBorderColor"));
		graph.addAttribute("rotateNames", "1");

		
		scheduleDataset.addAttribute("seriesName", bundle.getString(scheduleLegend));
		scheduleDataset.addAttribute("color", props.getProperty("scheduleColor"));
		scheduleDataset.addAttribute("showValues", props.getProperty("scheduleShowValues"));

		targetDataset.addAttribute("seriesName", bundle.getString(targetLegend));
		targetDataset.addAttribute("color", props.getProperty("targetColor"));
		targetDataset.addAttribute("showValues", props.getProperty("targetShowValues"));

		for (TotalHour th : totalHours) {
			
			// Add column
			Element element = categories.addElement("category");
			element.addAttribute("name", sdf.format(th.getDay()));

			// Add schedule value
			element = scheduleDataset.addElement("set");
			element.addAttribute("value", th.getSchedule().toPlainString());
			//element.addAttribute("link", "http://www.google.com.ar");

			// Add target value
			element = targetDataset.addElement("set");
			element.addAttribute("value", th.getTarget().toPlainString());
			//element.addAttribute("link", "http://www.google.com.ar");
		}

		if (log.isDebugEnabled()) {
			log.debug(document.asXML());
		}
		return document.asXML();
		
	}	
	private Properties getProperties(String reportName) throws IOException {

		Properties props = new Properties();
		if (getReportConfigurations().get(reportName) == null) {
			log.error("There is no entry in the hashMap for element: " + reportName);
			throw new NoSuchElementException(
					"There is no entry for report " + reportName);
		}

		props.load(getClass().getResourceAsStream(
				getReportConfigurations().get(reportName)));

		return props;
	}

	/**
	 * @return the reportConfigurations
	 */
	public HashMap<String, String> getReportConfigurations() {
		return reportConfigurations;
	}

	/**
	 * @param reportConfigurations the reportConfigurations to set
	 */
	public void setReportConfigurations(HashMap<String, String> reportConfigurations) {
		this.reportConfigurations = reportConfigurations;
	}

	/**
	 * This method returns a max value divisible by step.
	 * @param maxValue
	 * @param step
	 * @return
	 */
	private static long calculateMaxValue(long maxValue, long step) {
		return (maxValue /step) * step + step;
	}	

}
