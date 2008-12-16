<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<br />

<table border="0" cellspacing="0" align="center">
	<s:iterator id="position" value="positionList" status="itPosition">
	<tr>
			<td class="positionTableHeader" colspan="2"><s:property value="%{name}" /></td>
		</tr>
		
		<tr>
			<td>
			<s:url id="reportByPosition" value="/report/totalHoursReport_showWeeklyReportByPosition.action" method="post">
				<s:param name="positionId" value="%{id}"/>
				<s:param name="selectedDate" value="selectedDate"/>
				<s:param name="selectedWeekDay" value="selectedWeekDay"/>
			</s:url>
		
<script language="javascript" type="text/javascript">
		djConfig.searchIds.push("reportFrame_<s:property value='%{id}' />");	
</script>
			<s:div id="reportFrame_%{id}" theme="ajax" 
				href="%{reportByPosition}" 
				indicator="indicator" />

	</s:iterator>
</table>

<img id="indicator"
	src="<s:url value="/images/indicator.gif" includeParams="none" />"
	alt="<s:text name="wait.message"/>" style="display: none" />		
