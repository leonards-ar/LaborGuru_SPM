<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<br />

<table border="0" cellspacing="0" align="center">
  <tr>
    <td class="windowTableHeader">
      <s:text name="report.weeklytotalhours.title" />
    </td>
   </tr>
    <tr>
      <td>
		<table border="2" width="100%" cellspacing="0" align="center">
		  <tr>
			<td class="windowTableLabel">&nbsp;</td>
			<s:iterator id="totalHour" value="totalHours" status="itTotalHours">
			  <td class="windowTableValue"><s:text name="report.weeklytotalhours.dateformat"><s:param value="day"/></s:text></td>
			</s:iterator>
			<td class="windowTableLabel"><s:text name="report.performance.totalweek.label" /></td>
		  </tr>
		  <tr>
			<td class="windowTableLabel">
			  <s:text name="report.performance.checks.label" />
			</td>
			<s:iterator id="totalHour" value="totalHours" status="itTotalHours">
			  <td class="windowTableValue">
			  	<s:text name="currency"><s:param value="sales"/></s:text>
			  </td>
			</s:iterator>
			<td class="windowTableValue">
				<s:text name="currency"><s:param value="totalSales"/></s:text>
			</td>
		  </tr>
		  <tr>
			<td class="windowTableLabel">
			  <s:text name="report.performance.scheduled.label" />
			</td>
			<s:iterator id="totalHour" value="totalHours" status="itTotalHours">
			  <td class="windowTableValue">
			  	<s:text name="total.hours"><s:param value="schedule"/></s:text>
			  </td>
			</s:iterator>
			<td class="windowTableValue">
				<s:text name="total.hours"><s:param value="totalSchedule"/></s:text></td>
		  </tr>
		  <tr>
			<td class="windowTableLabel">
			  <s:text name="report.performance.target.label" />
			</td>
			<s:iterator id="totalHour" value="totalHours" status="itTotalHours">
			  <td class="windowTableValue">
			  	<s:text name="total.hours"><s:param value="target"/></s:text>
			  </td>
			</s:iterator>
			<td class="windowTableValue"><s:property value="totalTarget"/></td>
		  </tr>
		  <tr>
			<td class="windowTableLabel">
			  <s:text name="report.performance.difference.label" />
			</td>
			<s:iterator id="totalHour" value="totalHours" status="itTotalHours">
			  <td class="<s:if test="difference < 0">windowTableNegative</s:if><s:else>windowTableValue</s:else>">
			  	<s:text name="total.hours"><s:param value="difference"/></s:text></td>
			</s:iterator>
			<td class="<s:if test="totalDifference < 0">windowTableNegative</s:if><s:else>windowTableValue</s:else>">
				<s:text name="total.hours"><s:param value="totalDifference"/></s:text></td>
		  </tr>
		  <tr>
			<td class="windowTableLabel">
			  <s:text name="report.performance.percentage.label" />
			</td>
			<s:iterator id="totalHour" value="totalHours" status="itTotalHours">
			  <td class="windowTableValue">
			  	<s:text name="percentage"><s:param value="percentage"/></s:text>
			  </td>
			</s:iterator>
			<td class="windowTableValue">
				<s:text name="percentage"><s:param value="totalPercentage"/></s:text>
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
