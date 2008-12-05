<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<br />

<table border="0" cellspacing="0" align="center">
	<s:iterator id="map" value="totalHoursByPosition.entrySet()" status="itPosition">
		<tr>
			<td class="windowTableHeader" colspan="2"><s:property value="%{key.name}" /></td>
		</tr>
		<tr>
			<td width="50%">
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
					<td class="windowTableValue"><s:property value="%{totalSchedule.get(key)}" /></td>
				</tr>
				<tr>
					<td class="windowTableLabel"><s:text
						name="report.weeklytotalhours.target.label" /></td>
					<s:iterator id="totalHour" value="totalHours" status="itTotalHours">
						<td class="windowTableValue"><s:property value="target" /></td>
					</s:iterator>
					<td class="windowTableValue"><s:property value="%{totalTarget.get(key)}" /></td>
				</tr>
				<tr>
					<td class="windowTableLabel"><s:text
						name="report.weeklytotalhours.difference.label" /></td>
					<s:iterator id="totalHour" value="%{value}" status="itTotalHours">
						<td class="windowTableValue"><s:property value="difference" /></td>
					</s:iterator>
					<td class="windowTableValue"><s:property value="%{totalDifference.get(key)}" /></td>
				</tr>
				<tr>
					<td class="windowTableLabel"><s:text
						name="report.weeklytotalhours.percentaje.label" /></td>
					<s:iterator id="totalHour" value="%{value}" status="itTotalHours">
						<td class="windowTableValue"><s:property value="percentaje" /></td>
					</s:iterator>
					<td class="windowTableValue"><s:property value="%{totalPercentaje.get(key)}" /></td>
				</tr>
			</table>
			</td>
			<td width="50%"><p>aca viene el gráfico, pero si funciona todo lo anterior soy un genio</td>
		</tr>
	</s:iterator>
</table>