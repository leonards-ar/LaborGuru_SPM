<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
                    			
<!-- Daily Projection -->
<table border="0" cellpadding="3" cellspacing="1" colspan="0" cellspan="0" align="center">
	<tr class="editorTableHeader">
		<td>&nbsp;</td>
		<!-- Iterate week days -->
		<s:iterator id="weekDay" value="weekDaySelector.weekDays">
			<td class="windowTableValue"> 
				<s:text name='projection.daily.weekday.dateformat'><s:param value='weekDay'/></s:text>
			</td>
		</s:iterator>
		<!-- End Iterate week days -->
		<td class="windowTableValue"><s:text name="projection.daily.weektotal.label" /></td>
	</tr>
	<tr>
		<td class="windowTableValue"><s:text name="projection.daily.adjusted.label" /></td>
		<s:iterator id="dailyProjection" value="dailyProjections" status="itProjection">
			<td class="windowTableValue">
				<s:text name="currency"><s:param value="adjustedProjection"/></s:text>
			</td>										
		</s:iterator>
		<td class="editorTableEvenRow"><b><s:text name="currency"><s:param value="totalAdjusted"/></s:text></b></td>
	</tr>									
</table>                    			
<!-- End Daily Projection -->
