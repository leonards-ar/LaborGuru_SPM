<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
                    			
<table border="2" width="100%" cellspacing="0" align="center">
	<tr>
		<td class="windowTableLabel">&nbsp;</td>
		<td class="windowTableLabel"><s:text name="report.fixedlabor.total.label" /></td>
		<td class="windowTableLabel"><s:text name="report.fixedlabor.service.label" /></td>
		<td class="windowTableLabel"><s:text name="report.fixedlabor.opening.label" /></td>
		<td class="windowTableLabel"><s:text name="report.fixedlabor.flexible.label" /></td>
		<td class="windowTableLabel"><s:text name="report.fixedlabor.closing.label" /></td>
	</tr>

	<tr>
		<td class="windowTableLabel"><s:text name="report.fixedlabor.schedule.label" /></td>
		<td class="windowTableValue"><s:text name="report.total.hours"><s:param value="fixedLaborHoursReport.totalSchedule" /></s:text>
		<s:push value="fixedLaborHoursReport.schedule">
			<td class="windowTableValue"><s:text name="report.total.hours"><s:param value="serviceHours"/></s:text></td>
			<td class="windowTableValue"><s:text name="report.total.hours"><s:param value="openHours"/></s:text></td>
			<td class="windowTableValue"><s:text name="report.total.hours"><s:param value="flexHours"/></s:text></td>
			<td class="windowTableValue"><s:text name="report.total.hours"><s:param value="closeHours"/></s:text></td>		
		</s:push>
	<tr>
	<tr>
		<td class="windowTableLabel"><s:text name="report.fixedlabor.target.label" /></td>
		<td class="windowTableValue"><s:text name="report.total.hours"><s:param value="fixedLaborHoursReport.totalTarget" /></s:text>
		<s:push value="fixedLaborHoursReport.target">
			<td class="windowTableValue"><s:text name="report.total.hours"><s:param value="serviceHours"/></s:text></td>
			<td class="windowTableValue"><s:text name="report.total.hours"><s:param value="openHours"/></s:text></td>
			<td class="windowTableValue"><s:text name="report.total.hours"><s:param value="flexHours"/></s:text></td>
			<td class="windowTableValue"><s:text name="report.total.hours"><s:param value="closeHours"/></s:text></td>		
		</s:push>
	</tr>
	<tr>
		<td class="windowTableLabel"><s:text name="report.fixedlabor.difference.label" /></td>
		<td class="windowTableValue"><s:text name="report.total.hours"><s:param value="fixedLaborHoursReport.totalDifference" /></s:text>
		<s:push value="fixedLaborHoursReport.difference">
			<td class="windowTableValue"><s:text name="report.total.hours"><s:param value="serviceHours"/></s:text></td>
			<td class="windowTableValue"><s:text name="report.total.hours"><s:param value="openHours"/></s:text></td>
			<td class="windowTableValue"><s:text name="report.total.hours"><s:param value="flexHours"/></s:text></td>
			<td class="windowTableValue"><s:text name="report.total.hours"><s:param value="closeHours"/></s:text></td>		
		</s:push>		
	</tr>
</table>