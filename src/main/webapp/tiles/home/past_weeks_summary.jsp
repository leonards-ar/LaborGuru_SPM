<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<table border="0" cellpadding="2" cellspacing="1">
	<tr>
		<td class="windowTableLabel">&nbsp;</td>
		<td class="windowTableLabel">&nbsp;</td>
		<td class="windowTableLabel" colspan="3"><s:text name="home.projection_labor.label"/></td>
		<td class="windowTableLabel" colspan="3"><s:text name="home.actual_labor.label"/></td>
	</tr>
	<tr>
		<td class="windowTableLabel">&nbsp;</td>
		<td class="windowTableLabel"><s:text name="home.week.label"/></td>
		<td class="windowTableLabel"><s:text name="home.volume.label"/></td>
		<td class="windowTableLabel"><s:text name="home.target.label"/></td>
		<td class="windowTableLabel"><s:text name="home.scheduled.label"/></td>
		<td class="windowTableLabel"><s:text name="home.volume.label"/></td>
		<td class="windowTableLabel"><s:text name="home.target.label"/></td>
		<td class="windowTableLabel"><s:text name="home.scheduled.label"/></td>
	</tr>
	<tr>
		<td class="windowTableLabel"><s:text name="home.last_week.label"/></td>
		<td class="windowTableValue">11-18-08</td>
		<td class="windowTableValue">12,000</td>
		<td class="windowTableValue">720</td>
		<td class="windowTableValue">750</td>
		<td class="windowTableValue">11,725</td>
		<td class="windowTableValue">716</td>
		<td class="windowTableValue">753</td>
	</tr>
	<tr>
		<td class="windowTableLabel"><s:text name="home.two_weeks.label"/></td>
		<td class="windowTableValue">11-11-08</td>
		<td class="windowTableValue">12,000</td>
		<td class="windowTableValue">730</td>
		<td class="windowTableValue">750</td>
		<td class="windowTableValue">11,725</td>
		<td class="windowTableValue">742</td>
		<td class="windowTableValue">753</td>
	</tr>
	<tr>
		<td class="windowTableLabel">&nbsp;</td>
		<td class="windowTableLabel"><s:text name="home.week.label"/></td>
		<td class="windowTableLabel"><s:text name="home.performance.label"/></td>
		<td class="windowTableLabel"><s:text name="home.scheduled.label"/></td>
		<td class="windowTableLabel"><s:text name="home.projection.label"/></td>
		<td class="windowTableLabel"><s:text name="home.execution.label"/></td>
		<td class="windowTableLabel">&nbsp;</td>
		<td class="windowTableLabel">&nbsp;</td>
	</tr>
	<tr>
		<td class="windowTableLabel"><s:text name="home.last_week.label"/></td>
		<td class="windowTableValue">11-18-08</td>
		<td class="windowTableValue">-15%</td>
		<td class="windowTableValue">-8%</td>
		<td class="windowTableValue">7%</td>
		<td class="windowTableValue">-1%</td>
		<td class="windowTableValue">&nbsp;</td>
		<td class="windowTableValue">&nbsp;</td>
	</tr>
	<tr>
		<td class="windowTableLabel"><s:text name="home.two_weeks.label"/></td>
		<td class="windowTableValue">11-11-08</td>
		<td class="windowTableValue">-20%</td>
		<td class="windowTableValue">-10%</td>
		<td class="windowTableValue">10%</td>
		<td class="windowTableValue">-5%</td>
		<td class="windowTableValue">&nbsp;</td>
		<td class="windowTableValue">&nbsp;</td>
	</tr>
</table>

<script language="javascript" type="text/javascript">
	setObjectByIDClass('currentWeekSummaryFrame', '');
</script>