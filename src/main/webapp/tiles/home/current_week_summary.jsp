<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<table border="0" cellpadding="2" cellspacing="1">
	<tr>
		<td class="windowTableLabel">&nbsp;</td>
		<td class="windowTableLabel"><s:text name="home.week.label"/></td>
		<td class="windowTableLabel"><s:text name="home.volume.label"/></td>
		<td class="windowTableLabel"><s:text name="home.target.label"/></td>
		<td class="windowTableLabel"><s:text name="home.scheduled.label"/></td>
		<td class="windowTableLabel"><s:text name="home.difference.label"/></td>
		<td class="windowTableLabel"><s:text name="home.percentage.label"/></td>
	</tr>
	<tr>
		<td class="windowTableLabel"><s:text name="home.this_week.label"/></td>
		<td class="windowTableValue"><s:text name="home.weekday.performance.dateformat"><s:param value="currentWeekSummary.date"/></s:text></td>
		<td class="windowTableValue"><s:text name="currency"><s:param value="currentWeekSummary.projectedVolume"/></s:text></td>
		<td class="windowTableValue"><s:text name="total.hours"><s:param value="currentWeekSummary.projectedTarget"/></s:text></td>
		<td class="windowTableValue"><s:text name="total.hours"><s:param value="currentWeekSummary.projectedScheduled"/></s:text></td>
		<td class="windowTableValue"><s:text name="total.hours"><s:param value="currentWeekSummary.projectedDifference"/></s:text></td>
		<td class="windowTableValue"><s:text name="percentage"><s:param value="currentWeekSummary.projectedDifferencePercentage"/></s:text>%</td>
	</tr>
</table>

<script language="javascript" type="text/javascript">
	setObjectByIDClass('currentWeekSummaryFrame', '');
</script>