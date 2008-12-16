<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<br />

<table border="0" cellspacing="0" align="center">
	<s:iterator id="position" value="positions" status="itPosition">
		<tr>
			<td class="positionTableHeader" colspan="2"><s:property value="name" /></td>
		</tr>
		<tr>
			<s:url id="%{name}"
				value="/report/totalHoursReportByPosition_showReport.action" method="post">
				<s:param name="selectedDate" value="selectedDate"/>
				<s:param name="selectedWeekDay" value="selectedWeekDay"/>
				<s:param name="position" value="position"/>
			</s:url>
		
		
			<s:div id="reportFrame" theme="ajax" 
					href="%{name}" 
					indicator="indicator" />
		</tr>
	</s:iterator>

</table>

<img id="indicator"
	src="<s:url value="/images/indicator.gif" includeParams="none" />"
	alt="<s:text name="wait.message"/>" style="display: none" />