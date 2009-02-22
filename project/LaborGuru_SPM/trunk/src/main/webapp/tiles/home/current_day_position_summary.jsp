<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<table id="windowTable">
	<tr>
		<td class="windowTableHeader"><s:text name='home.weekday.position.dateformat'><s:param value='%{new java.util.Date()}'/></s:text></td>
		<td class="windowTableHeader"><s:text name="home.scheduled.label"/></td>
		<td class="windowTableHeader"><s:text name="home.target.label"/></td>
		<td class="windowTableHeader"><s:text name="home.difference.label"/></td>
	</tr>
	
	<!--  For each position -->
	<s:iterator value="currentDayPositionSummary" id="row">
	<tr>
		<td class="windowTableValue"><s:property value="position.name"/></td>
		<td class="windowTableValue"><s:text name="total.hours"><s:param value="projectedScheduled"/></s:text></td>
		<td class="windowTableValue"><s:text name="total.hours"><s:param value="projectedTarget"/></s:text></td>
		<td class="windowTableValue"><s:text name="total.hours"><s:param value="projectedDifference"/></s:text></td>
	</tr>
	</s:iterator>
	<!-- For each position -->
	
	<tr>
		<td class="windowTableValue"><b><s:text name="home.total.label"/></b></td>
		<td class="windowTableValue"><b><s:text name="total.hours"><s:param value="totalProjectedScheduled"/></s:text></b></td>
		<td class="windowTableValue"><b><s:text name="total.hours"><s:param value="totalProjectedTarget"/></s:text></b></td>
		<td class="windowTableValue"><b><s:text name="total.hours"><s:param value="totalProjectedDifference"/></s:text></b></td>
	</tr>
	
</table>

<script language="javascript" type="text/javascript">
	setObjectByIDClass('currentDayPositionSummaryFrame', '');
</script>