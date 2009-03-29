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
		<td class="windowTableValue">
			<s:text name="total.hours"><s:param value="totalHour.schedule"/></s:text>
		</td>
	</s:iterator>
</tr>
<tr>
	<td class="windowTableHeader"><s:text name="report.weeklytotalhours.target.label"/></td>
	<s:iterator id="totalHourByPosition" value="totalHoursByPosition" status="itTotalHours">
		<td class="windowTableValue">
			<s:text name="total.hours"><s:param value="totalHour.target"/></s:text>
		</td>
	</s:iterator>
</tr>
<tr>
	<td class="windowGreyTableValue"><s:text name="report.weeklytotalhours.difference.label"/></td>
	<s:iterator id="totalHourByPosition" value="totalHoursByPosition" status="itTotalHours">
		<td class="<s:if test="totalHour.difference < 0">windowTableNegative</s:if><s:else>windowGreyTableValue</s:else>">
			<s:text name="total.hours"><s:param value="totalHour.difference"/></s:text></td>
	</s:iterator>
</tr>
<tr>
	<td class="windowGreyTableValue"><s:text name="report.weeklytotalhours.percentage.label"/></td>
	<s:iterator id="totalHourByPosition" value="totalHoursByPosition" status="itTotalHours">
		<td class="windowGreyTableValue">
			<s:text name="percentage"><s:param value="totalHour.percentage"/></s:text></td>
	</s:iterator>
</tr>
</table>
</s:if>