<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<br/>
<table border="0" cellspacing="0" align="center">
    <tr>
      <td id="titleBar"><s:text name="report.historicalComparison.title" /></td>
    </tr>
    <tr>
      <td align="center">
				<table border="0" cellspacing="0" align="center">
          <tr>
          <td>
          <s:form id="historicalComparison_form" name="historicalComparison_form" theme="simple" method="post" action="historicalComparisonReport_showReport">					
          <table border="0" cellspacing="0" align="center">
					<tr>
						<td align="right" class="form_label">
							<s:text name="report.reportType.label"/>
						</td>
						<td align="left">
							<s:select name="index" list="reportTypes" listKey="index" listValue="%{getText(name)}" theme="simple"/>
						</td>
						<td align="right" class="form_label"><s:text name="report.historicalComparison.startDate.label" /></td>
						<td><s:datetimepicker id="startDate" name="startDate" displayWeeks="4" /></td>
						<td align="right" class="form_label"><s:text name="report.historicalComparison.endDate.label" /></td>
						<td><s:datetimepicker id="endDate" name="endDate" displayWeeks="4" /></td>
					</tr>
          </table>
          </td>
          </tr>
					<tr>
				    <td align="center"><s:submit id="submit" value="Get Report" theme="ajax" targets="tableFrame" /></td>
				  </tr>
					</s:form>
					<tr>
						<td align="center">
							<!--  s:url id="historicalComparisonReportUrl" action="historicalComparisonReport_showReport" namespace="/report" includeParams="none"/ -->
								<s:div id="tableFrame" theme="ajax" />
				
						</td>
					</tr>
				</table>
      </td>
    </tr>
</table>

<script language="javascript" type="text/javascript">

	controller = {
		refresh : function(){
		  alert("pepe");
		  return false;
		}
	}
	dojo.event.topic.registerPublisher("/refresh", controller, "refresh");

	djConfig.searchIds.push("startDate");	
	djConfig.searchIds.push("endDate");
	djConfig.searchIds.push("tableFrame");
	djConfig.searchIds.push("submit");
	djConfig.searchIds.push("historicalComparison_form");
</script>
