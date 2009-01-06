<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<br />

<table border="0" cellspacing="0" align="center">
  <tr>
    <td class="windowTableHeader">
      <s:text name="report.dailyHoursTable.title" />
    </td>
   </tr>
    <tr>
      <td>
		<table border="2" width="100%" cellspacing="0" align="center">
		  <tr>
			<td class="windowTableLabel">&nbsp;</td>
			<td class="windowTableLabel"><s:text name="report.dailyHours.total.label" /></td>
			<s:iterator id="totalHour" value="totalHours" status="itTotalHours">
				<s:if test="#itTotalHours.odd">
			  		<td class="windowTableValue" colspan="2"><s:text name="report.dailyHours.dateformat"><s:param value="day"/></s:text></td>
				</s:if>
			</s:iterator>
			
		  </tr>
		  <tr>
			<td class="windowTableLabel">
			  <s:text name="report.weeklytotalhours.scheduled.label" />
			</td>
			<td class="windowTableValue"><s:property value="totalSchedule"/></td>
			<s:iterator id="totalHour" value="totalHours" status="itTotalHours">
			  <td class="windowTableValue"><s:property value="schedule"/></td>
			</s:iterator>

		  </tr>
		  <tr>
			<td class="windowTableLabel">
			  <s:text name="report.weeklytotalhours.target.label" />
			</td>
			<td class="windowTableValue"><s:property value="totalTarget"/></td>
			<s:iterator id="totalHour" value="totalHours" status="itTotalHours">
			  <td class="windowTableValue"><s:property value="target"/></td>
			</s:iterator>
		  </tr>
		  <tr>
			<td class="windowTableLabel">
			  <s:text name="report.weeklytotalhours.difference.label" />
			</td>
			<td class="windowTableValue"><s:property value="totalDifference"/></td>
			<s:iterator id="totalHour" value="totalHours" status="itTotalHours">
			  <td class="windowTableValue"><s:property value="difference"/></td>
			</s:iterator>
		  </tr>
		</table>
      </td>
    </tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="center">
			<table border="0" cellspacing="0" align="center">
			<tr>
				<td>
					
				</td>
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
		</td>
	</tr>
</table>
