<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<br />
<table border="0" cellspacing="0" align="center">
	<tr>
		<td id="titleBar"><s:text
			name="report.historicalComparison.title" /></td>
	</tr>
	<tr>
		<td align="center">
		<table border="0" cellspacing="0" align="center">
			<tr>
				<td>
				<s:form id="historicalComparison_form"	name="historicalComparison_form" theme="simple" method="post"
					action="historicalComparisonReport_showReport">
					<table border="0" cellspacing="0" align="center">
						<tr>
							<td align="right" class="form_label"><s:text
								name="report.reportType.label" /></td>
							<td align="left"><s:select name="index" list="reportTypes"
								listKey="index" listValue="%{getText(name)}" theme="simple" /></td>
							<td align="right" class="form_label"><s:text
								name="report.historicalComparison.startDate.label" /></td>
							<td><s:datetimepicker id="startDate" name="startDate"
								adjustWeeks="true" theme="simple" /></td>
							<td align="right" class="form_label"><s:text
								name="report.historicalComparison.endDate.label" /></td>
							<td><s:datetimepicker id="endDate" name="endDate"
								adjustWeeks="true" theme="simple" /></td>
						</tr>
					</table></td>
			</tr>
			<tr>
				<td align="right">
					<s:submit id="submit" key="report.historicalComparison.submit.label" type="button"	theme="ajax" targets="tableFrame" indicator="historicalComparisonIndicator" formId="historicalComparison_form" cssClass="button"/>
				</td>
			</tr>
			</s:form>
			</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td align="center"><s:div id="tableFrame" theme="ajax"
			indicator="historicalComparisonIndicator" /></td>
	</tr>

</table>

<center><img id="historicalComparisonIndicator"
	style="display: none;"
	src="<s:url value="/images/wait.gif" includeParams="none"/>"
	alt="<s:text name="wait.message"/>"
	title="<s:text name="wait.message"/>" border="0" /></center>

<script language="javascript" type="text/javascript">
	djConfig.searchIds.push("startDate");
	djConfig.searchIds.push("endDate");
	djConfig.searchIds.push("tableFrame");
	djConfig.searchIds.push("submit");
	//djConfig.searchIds.push("historicalComparison_form");
</script>
