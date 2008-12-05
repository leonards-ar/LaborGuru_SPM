<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<br />
<s:form id="report_form" name="report_form" theme="simple">
	<s:hidden id="selectedDate" name="selectedDate" />
	<s:hidden id="selectedWeekDay" name="selectedWeekDay" />

	<table border="1" cellspacing="0" align="center">
		<tr>
			<td id="titleBar">&nbsp;</td>
		</tr>

		<tr>
			<td align="center"><!-- Start week table -->
			<table align="center" id="calendarTable" width="100%" border="0"
				cellpadding="0" cellspacing="0" colspan="0" cellspan="0">
				<tr>
					<td align="center">
					<table align="center" border="0" cellpadding="0" cellspacing="0"
						colspan="0" cellspan="0">
						<tr>
							<s:iterator id="prevDate"
								value="weekDaySelector.previousStartingWeekDays"
								status="itPrevDate">
								<td class="calendarTableColumn" width="10%" nowrap="nowrap">
								<a href="<s:url value="#" includeParams="none"/>"
									onclick="report_form.action='<s:property value='actionName'/>_changeWeek.action'; report_form.selectedDate.value='<s:text name='report.weekdayselector.weekdayselector.input.dateformat'><s:param value='prevDate'/></s:text>'; report_form.submit();"
									class="calendarUnselectedWeekLink"> <s:text
									name='report.weekdayselector.availableweek.dateformat'>
									<s:param value='prevDate' />
								</s:text> </a></td>

								<s:if test="!#itPrevDate.last">
									<td class="calendarTableColumn" nowrap="nowrap"><img
										src="<s:url value="/images/transp2x1.gif" includeParams="none"/>" />|<img
										src="<s:url value="/images/transp2x1.gif" includeParams="none"/>" /></td>
								</s:if>
							</s:iterator>
							<td class="calendarTableColumn"><img
								src="<s:url value="/images/transp2x1.gif" includeParams="none"/>" /></td>
							<td class="calendarTableColumn" nowrap="nowrap">
							<table border="0" cellpadding="0" cellspacing="0" colspan="0"
								cellspan="0">
								<tr>
									<td><img
										src="<s:url value="/images/transp2x1.gif" includeParams="none"/>" /></td>
									<td><a href="<s:url value="#" includeParams="none"/>"
										onclick="report_form.action='<s:property value='actionName'/>_changeWeek.action'; report_form.selectedDate.value='<s:text name='report.weekdayselector.weekdayselector.input.dateformat'><s:param value='weekDaySelector.previousStartingWeekDay'/></s:text>'; report_form.submit();"><img
										src="<s:url value="/images/cal_prev.png" includeParams="none"/>"
										border="0" /></a></td>
									<td><img
										src="<s:url value="/images/transp2x1.gif" includeParams="none"/>" /></td>
									<td class="calendarSelectedWeekText" nowrap="nowrap"><s:text
										name='report.weekdayselector.availableweek.dateformat'>
										<s:param value='weekDaySelector.startingWeekDay' />
									</s:text></td>
									<td><img
										src="<s:url value="/images/transp2x1.gif" includeParams="none"/>" /></td>
									<td><a href="<s:url value="#" includeParams="none"/>"
										onclick="report_form.action='<s:property value='actionName'/>_changeWeek.action'; report_form.selectedDate.value='<s:text name='report.weekdayselector.weekdayselector.input.dateformat'><s:param value='weekDaySelector.nextStartingWeekDay'/></s:text>'; report_form.submit();"><img
										src="<s:url value="/images/cal_next.png" includeParams="none"/>"
										border="0" /></a></td>
									<td><img
										src="<s:url value="/images/transp2x1.gif" includeParams="none"/>" /></td>
								</tr>
							</table>
							</td>
							<td class="calendarTableColumn"><img
								src="<s:url value="/images/transp2x1.gif" includeParams="none"/>" /></td>
							<s:iterator id="nextDate"
								value="weekDaySelector.nextStartingWeekDays" status="itNextDate">
								<td class="calendarTableColumn" width="10%" nowrap="nowrap">
								<a href="<s:url value="#" includeParams="none"/>"
									onclick="report_form.action='<s:property value='actionName'/>_changeWeek.action'; report_form.selectedDate.value='<s:text name='report.weekdayselector.weekdayselector.input.dateformat'><s:param value='nextDate'/></s:text>'; report_form.submit();"
									class="calendarUnselectedWeekLink"> <s:text
									name='report.weekdayselector.availableweek.dateformat'>
									<s:param value='nextDate' />
								</s:text> </a></td>

								<s:if test="!#itNextDate.last">
									<td class="calendarTableColumn" nowrap="nowrap"><img
										src="<s:url value="/images/transp2x1.gif" includeParams="none"/>" />|<img
										src="<s:url value="/images/transp2x1.gif" includeParams="none"/>" /></td>
								</s:if>
							</s:iterator>
						</tr>
					</table>
					</td>
				</tr>
				<tr class="calendarWeekDayTableRowSeparator">
					<td><img
						src="<s:url value="/images/transp2x1.gif" includeParams="none"/>" /></td>
				</tr>
			</table>
           </td>
          </tr>
		<!-- End week table -->
	</table>

    <br/>

    <table border="0" cellspacing="0" align="center">
	<tr>
	 <td align="right" class="form_label"><s:text name="report.displaytype.label"/></td>
     <td align="left"><s:select name="selectView" list="viewMap" listKey="key" listValue="%{getText(value)}" theme="simple"/></td>
     <td>&nbsp;</td>
     <td align="right" class="form_label"><s:text name="report.period.label"/></td>
     <td align="left"><s:select name="period" list="periodMap" listKey="key" listValue="%{getText(value)}" theme="simple"/></td>
    </tr>
    </table>
   

</s:form>