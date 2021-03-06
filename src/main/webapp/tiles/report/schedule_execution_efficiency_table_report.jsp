<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<br />

<table border="0" cellspacing="0" align="center">
  <tr>
    <td class="windowTableHeader">
      <s:text name="report.schedule.execution.title" />
    </td>
   </tr>
    <tr>
      <td>
		<table id="windowReportTable" cellspacing="0">
		  <tr>
			<td class="tableLabel">&nbsp;</td>
			<s:iterator id="totalHour" value="totalHours" status="itTotalHours">
			  <td class="tableLabelWithLeftBorder">
			   <s:text name="report.weeklytotalhours.dateformat"><s:param value="day"/></s:text>
        </td>
			</s:iterator>
			<td class="greyTableLabelWithLeftBorder">
			   <s:text name="report.schedule.execution.totalweek.label" />
      </td>
		  </tr>
		  <tr>
			<td class="greyTableLabel">
			  <s:text name="report.schedule.execution.checks.label" />
			</td>
			<s:iterator id="totalHour" value="totalHours" status="itTotalHours">
			  <td class="greyTableValueWithLeftBorder">
			  	<s:text name="currency"><s:param value="sales"/></s:text>
			  </td>
			</s:iterator>
			<td class="tableValueWithLeftBorder">
				<s:text name="currency"><s:param value="totalSales"/></s:text>
			</td>
		  </tr>
		  <tr>
				<td class="tableLabelWithBottomBorder">
				  <s:text name="report.schedule.execution.scheduled.label" />
				</td>
				<s:iterator id="totalHour" value="totalHours" status="itTotalHours">
				  <td class="tableValueWithLeftBottomBorder">
				  	<s:text name="report.total.hours"><s:param value="schedule"/></s:text></td>
				</s:iterator>
				<td class="greyTableValueWithLeftBottomBorder">
				   <s:text name="report.total.hours"><s:param value="totalSchedule"/></s:text>
	      </td>
		  </tr>
		  <tr>
				<td class="tableLabel">
				  <s:text name="report.schedule.execution.target.label" />
				</td>
				<s:iterator id="totalHour" value="totalHours" status="itTotalHours">
				  <td class="tableValueWithLeftBorder">
				  	<s:text name="report.total.hours"><s:param value="target"/></s:text></td>
				</s:iterator>
				<td class="greyTableValueWithLeftBorder">
					<s:text name="report.total.hours"><s:param value="totalTarget"/></s:text>
	      </td>
		  </tr>
		  <tr>
				<td class="greyTableLabel">
				  <s:text name="report.schedule.execution.difference.label" />
				</td>
				<s:iterator id="totalHour" value="totalHours" status="itTotalHours">
				  <td class="greyTableValueWithLeftBorder">
				  	<s:text name="report.total.hours"><s:param value="difference"/></s:text></td>
				</s:iterator>
				<td class="tableValueWithLeftBorder">
					<s:text name="report.total.hours"><s:param value="totalDifference"/></s:text>
				</td>
		  </tr>
		  <tr>
				<td class="tableLabel">
				  <s:text name="report.schedule.execution.percentage.label" />
				</td>
				<s:iterator id="totalHour" value="totalHours" status="itTotalHours">
				  <td class="tableValueWithLeftBorder">
				  	<s:text name="report.percentage"><s:param value="percentage"/></s:text></td>
				</s:iterator>
				<td class="greyTableValueWithLeftBorder">
					<s:text name="report.percentage"><s:param value="totalPercentage"/></s:text>
				</td>
		  </tr>
		</table>
      </td>
    </tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>
			<OBJECT classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase=http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" width="600" height="350" id="Column3D" >
		   	<param name="movie" value="<s:url value='/fusionCharts/FCF_MSColumn3DLineDY.swf?chartWidth=600&chartHeight=300' includeParams="none"/>" />
   			<param name="FlashVars" value="&dataXML=<s:property value="xmlValues"/>">
   			<param name="quality" value="high" />
   			<embed src="<s:url value='/fusionCharts/FCF_MSColumn3DLineDY.swf?chartWidth=600&chartHeight=300' includeParams="none"/>" flashVars="&dataXML=<s:property value="xmlValues"/>" quality="high" width="600" height="300" name="Column3D" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
			</object>
		</td>
	</tr>
</table>
