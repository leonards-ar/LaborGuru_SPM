<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script language="javascript" type="text/javascript">

	djConfig.searchIds.push("reportFrame");
	djConfig.searchIds.push("foreCast");					
</script>

<table>
<tr>
<td>
<s:url id="forecastReport" value="/report/foreCastReport_showTable.action" includeParams="none">
				<s:param name="positionId" value="%{id}"/>
				<s:param name="selectedDate" value="selectedDate"/>
				<s:param name="selectedWeekDay" value="selectedWeekDay"/>
</s:url>

<s:div id="foreCast" theme="ajax" href="%{forecastReport}" />
</td>
</tr>
<tr>
<td>
<s:url id="totalHourReportUrl" action="totalHoursReport_showReport"
	namespace="/report" includeParams="none"/>


<s:div id="reportFrame" theme="ajax" 
			href="%{totalHourReportUrl}" 
			formId="report_form"
			listenTopics="/refresh,/Changed"
			indicator="indicator" />
</td></tr>
</table>

<img id="indicator"
	src="<s:url value="/images/indicator.gif" includeParams="none" />"
	alt="<s:text name="wait.message"/>" style="display: none" />
