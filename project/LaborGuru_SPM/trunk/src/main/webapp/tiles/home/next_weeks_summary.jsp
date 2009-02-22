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
		<td class="windowTableLabel"><s:text name="home.next_week.label"/></td>
		<td class="windowTableValue"><s:text name="home.weekday.performance.dateformat"><s:param value="nextWeeksSummary[0].date"/></s:text></td>
		<s:if test="nextWeeksSummary[0].projectedVolumeSet">
		<td class="windowTableValue"><s:text name="currency"><s:param value="nextWeeksSummary[0].projectedVolume"/></s:text></td>
		<td class="windowTableValue"><s:text name="total.hours"><s:param value="nextWeeksSummary[0].projectedTarget"/></s:text></td>
		<td class="windowTableValue"><s:text name="total.hours"><s:param value="nextWeeksSummary[0].projectedScheduled"/></s:text></td>
		<td class="windowTableValue"><s:text name="total.hours"><s:param value="nextWeeksSummary[0].projectedDifference"/></s:text></td>
		<td class="windowTableValue"><s:text name="percentage"><s:param value="nextWeeksSummary[0].projectedDifferencePercentage"/></s:text>%</td>
		</s:if>
		<s:else>
		<td class="windowTableValue" colspan="5"><span class="infoMessage"><s:text name="home.no_projection.message"/></span></td>
		</s:else>
	</tr>
	<tr>
		<td class="windowTableLabel"><s:text name="home.next_two_weeks.label"/></td>
		<td class="windowTableValue"><s:text name="home.weekday.performance.dateformat"><s:param value="nextWeeksSummary[1].date"/></s:text></td>
		<s:if test="nextWeeksSummary[1].projectedVolumeSet">
		<td class="windowTableValue"><s:text name="currency"><s:param value="nextWeeksSummary[1].projectedVolume"/></s:text></td>
		<td class="windowTableValue"><s:text name="total.hours"><s:param value="nextWeeksSummary[1].projectedTarget"/></s:text></td>
		<td class="windowTableValue"><s:text name="total.hours"><s:param value="nextWeeksSummary[1].projectedScheduled"/></s:text></td>
		<td class="windowTableValue"><s:text name="total.hours"><s:param value="nextWeeksSummary[1].projectedDifference"/></s:text></td>
		<td class="windowTableValue"><s:text name="percentage"><s:param value="nextWeeksSummary[1].projectedDifferencePercentage"/></s:text>%</td>
		</s:if>
		<s:else>
		<td class="windowTableValue" colspan="5"><span class="infoMessage"><s:text name="home.no_projection.message"/></span></td>
		</s:else>
	</tr>
</table>

<script language="javascript" type="text/javascript">
	setObjectByIDClass('nextWeeksSummaryFrame', '');
</script>