<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<br />

<table border="0" cellspacing="0" align="center">
	<s:iterator id="map" value="totalHoursByPosition.entrySet()" status="itPosition">
		<tr>
			<td class="positionTableHeader" colspan="2"><s:property value="%{key.name}" /></td>
		</tr>
		
		<tr>
			<td>
			<table border="0" cellspacing="0" align="center">
			<tr>
				<td class="windowTableHeader">
					<s:text name="report.weeklytotalhours.title" />
				</td>
			</tr>
			<tr><td>
			<table border="2" cellspacing="0" align="center">
				<tr>
					<td class="windowTableLabel"><b><s:text	name="report.weeklytotalhours.totalhours.label" /></b></td>
					<s:iterator id="totalHour" value="%{value}" status="itTotalHours">
						<td class="windowTableValue">
						<s:text name="report.weekday.dateformat">
							<s:property value="day" />
						</s:text></td>
					</s:iterator>
					<td class="windowTableLabel"><s:text
						name="report.weeklytotalhours.totalweek.label" /></td>
				</tr>
				<tr>
					<td class="windowTableLabel"><s:text
						name="report.weeklytotalhours.scheduled.label" /></td>
					<s:iterator id="totalHour" value="%{value}" status="itTotalHours">
						<td class="windowTableValue"><s:property value="schedule" /></td>
					</s:iterator>
					<td class="windowTableValue"><s:property value="%{totalScheduleByPosition[key]}" /></td>
				</tr>
				<tr>
					<td class="windowTableLabel"><s:text
						name="report.weeklytotalhours.target.label" /></td>
					<s:iterator id="totalHour" value="%{value}" status="itTotalHours">
						<td class="windowTableValue"><s:property value="target" /></td>
					</s:iterator>
					<td class="windowTableValue"><s:property value="%{totalTargetByPosition[key]}" /></td>
				</tr>
				<tr>
					<td class="windowTableLabel"><s:text
						name="report.weeklytotalhours.difference.label" /></td>
					<s:iterator id="totalHour" value="%{value}" status="itTotalHours">
						<td class="windowTableValue"><s:property value="difference" /></td>
					</s:iterator>
					<td class="windowTableValue"><s:property value="%{totalDifferenceByPosition[key]}" /></td>
				</tr>
				<tr>
					<td class="windowTableLabel"><s:text
						name="report.weeklytotalhours.percentaje.label" /></td>
					<s:iterator id="totalHour" value="%{value}" status="itTotalHours">
						<td class="windowTableValue"><s:property value="percentaje" /></td>
					</s:iterator>
					<td class="windowTableValue"><s:property value="%{totalPercentajeByPosition[key]}" /></td>
				</tr>
			</table>
			<td>
			</tr>
			</table>
			</td>
			<td>
				
				<OBJECT classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase=http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" width="550" height="200" id="Column3D" >
			   	<param name="movie" value="<s:url value='/fusionCharts/FCF_MSColumn3DLineDY.swf?chartWidth=550&chartHeight=200' includeParams="none"/>" />
   				<param name="FlashVars" value="&dataXML=<s:property value="%{graphData[key]}"/>">
   				<param name="quality" value="high" />
   				<embed src="<s:url value='/fusionCharts/FCF_MSColumn3DLineDY.swf?chartWidth=550&chartHeight=200' includeParams="none"/>" flashVars="&dataXML=<s:property value="%{graphData[key]}"/>" quality="high" width="550" height="200" name="Column3D" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
				</object>
			</td>
		</tr>
	</s:iterator>
</table>