<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:if test="showTable">
<table border="2" width="100%" cellspacing="0" align="center">
<tr>
	<td>&nbsp;</td>
	<s:iterator id="totalHourByPosition" value="totalHoursByPosition" status="itTotalHours">
		<td class="windowGreyTableValue">
			<s:property value="position.name"/>
	</s:iterator>
</tr>
<tr>
	<td class="windowTableHeader"><s:text name="report.weeklytotalhours.scheduled.label"/></td>
	<s:iterator id="totalHourByPosition" value="totalHoursByPosition" status="itTotalHours">
		<td class="windowTableValue"><s:property value="totalHour.schedule"/></td>
	</s:iterator>
</tr>
<tr>
	<td class="windowTableHeader"><s:text name="report.weeklytotalhours.target.label"/></td>
	<s:iterator id="totalHourByPosition" value="totalHoursByPosition" status="itTotalHours">
		<td class="windowTableValue"><s:property value="totalHour.target"/></td>
	</s:iterator>
</tr>
<tr>
	<td class="windowGreyTableValue"><s:text name="report.weeklytotalhours.difference.label"/></td>
	<s:iterator id="totalHourByPosition" value="totalHoursByPosition" status="itTotalHours">
		<td class="windowGreyTableValue"><s:property value="totalHour.difference"/></td>
	</s:iterator>
</tr>
<tr>
	<td class="windowGreyTableValue"><s:text name="report.weeklytotalhours.percentaje.label"/></td>
	<s:iterator id="totalHourByPosition" value="totalHoursByPosition" status="itTotalHours">
		<td class="windowGreyTableValue"><s:property value="totalHour.percentaje"/></td>
	</s:iterator>
</tr>
</table>
</s:if>