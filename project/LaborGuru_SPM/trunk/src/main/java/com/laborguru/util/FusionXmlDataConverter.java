package com.laborguru.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.laborguru.exception.ErrorEnum;
import com.laborguru.exception.SpmUncheckedException;
import com.laborguru.model.TotalHour;

public class FusionXmlDataConverter {

	private static Logger log = Logger.getLogger(FusionXmlDataConverter.class);

	private HashMap<String, String> reportConfigurations;

	public String weeklyTotalHoursXmlConverter(List<TotalHour> totalHours) {

		Document document = DocumentHelper.createDocument();
		Element graph = document.addElement("graph");
		Element categories = graph.addElement("categories");
		Element scheduleDataset = graph.addElement("dataset");
		Element targetDataset = graph.addElement("dataset");
		Properties props = null;

		try {
			props = getProperties("weeklyTotalHoursReport");
		} catch (IOException e) {
			log.error("No file found", e);
			throw new SpmUncheckedException(e.getCause(), e.getMessage(), ErrorEnum.GENERIC_ERROR);
		}
		SimpleDateFormat sdf = new SimpleDateFormat(props.getProperty("dateFormat"));

		graph.addAttribute("caption", "Total Hours (Weekly)");
		graph.addAttribute("PYAxisName", "Hours");
		graph.addAttribute("SYAxisName", "Hours");
		graph.addAttribute("showvalues", props.getProperty("showvalues"));
		graph.addAttribute("numDivLines", props.getProperty("numDivLines"));
		graph.addAttribute("formatNumberScale", props.getProperty("formatNumberScale"));
		graph.addAttribute("decimalPrecision", props.getProperty("decimalPrecision"));
		graph.addAttribute("anchorSides", props.getProperty("anchorSides"));
		graph.addAttribute("anchorRadius", props.getProperty("anchorRadius"));
		graph.addAttribute("anchorBorderColor", props.getProperty("anchorBorderColor"));

		scheduleDataset.addAttribute("seriesName", "Schedule");
		scheduleDataset.addAttribute("color", props.getProperty("scheduleColor"));
		scheduleDataset.addAttribute("showValues", props.getProperty("scheduleShowValues"));

		targetDataset.addAttribute("seriesName", "Target");
		targetDataset.addAttribute("color", props.getProperty("targetColor"));
		targetDataset.addAttribute("showValues", props.getProperty("targetShowValues"));
		targetDataset.addAttribute("parentYAxis", "S");

		for (TotalHour th : totalHours) {
			// Add column
			Element element = categories.addElement("category");
			element.addAttribute("name", sdf.format(th.getDay()));

			// Add schedule value
			element = scheduleDataset.addElement("set");
			element.addAttribute("value", th.getSchedule().toPlainString());
			element.addAttribute("link", "http://www.google.com.ar");

			// Add target value
			element = targetDataset.addElement("set");
			element.addAttribute("value", th.getTarget().toPlainString());
			element.addAttribute("link", "http://www.google.com.ar");
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
					"There is no entry for report weeklyTotalHoursReport");
		}

		props.load(getClass().getResourceAsStream(
				getReportConfigurations().get("weeklyTotalHoursReport")));

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

	
}
