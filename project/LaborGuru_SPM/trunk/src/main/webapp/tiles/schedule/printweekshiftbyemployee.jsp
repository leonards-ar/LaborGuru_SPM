<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://acegisecurity.org/authz" prefix="authz" %>

<script language="JavaScript">
<!--

// -->
</script>

<br />
<s:form id="printweeklyshiftbyemployee_form" name="printweeklyshiftbyemployee_form" action="printweeklyshiftbyemployee_view" theme="simple">
	<s:hidden id="selectedDate" name="selectedDate"/>
	<s:hidden id="selectedWeekDay" name="selectedWeekDay"/>
	<s:hidden id="postSchedule" name="postSchedule"/>
	
	<table border="0" cellspacing="0" align="center">
		<tr>
			<td>
				<table border="0" cellspacing="0" align="center">
					<tr>
						<td align="right" class="form_label"><s:text name="schedule.printshift.view_selector.label"/></td>	
						<td align="left"><s:select name="selectedView" list="viewMap" listKey="key" listValue="%{getText(value)}" onchange="printweeklyshiftbyemployee_form.action=printweeklyshiftbyemployee_form.selectedView.value; printweeklyshiftbyemployee_form.submit()" theme="simple" /></td>				
						<td><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
						<s:if test="#session.spmUser.manager == true">
							<td align="right">
								<s:checkbox name="inTimeOnly" onchange="printweeklyshiftbyemployee_form.submit()" theme="simple" />
							</td>						
							<td align="left" class="form_label"><s:text name="schedule.printshift.in_time_only.label"/></td>
							<td><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
							<td align="center"><s:submit id="saveButton" key="schedule.printshift.weekly.save.button" action="printweeklyshiftbyemployee_save" theme="simple" cssClass="button"/></td>
						</s:if>
						<s:else>
							<s:hidden id="inTimeOnly" name="inTimeOnly"/>
						</s:else>
						<authz:authorize ifAllGranted="POST_SHIFT">
						<td><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
						<td align="center"><s:submit onclick="return confirmPostSchedule(this, '%{getText('schedule.post.schedule.confirm.msg')}');" id="postButton" key="schedule.printshift.weekly.post.button" action="printweeklyshiftbyemployee_post" theme="simple" cssClass="button"/></td>
						</authz:authorize>
						<td><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
						<td align="center">
							<s:url id="printUrl" action="printweeklyshiftbyemployee_print" escapeAmp="false" includeParams="none">
								<s:param name="selectedDate"><s:property value="selectedDate"/></s:param>
								<s:param name="selectedWeekDay"><s:property value="selectedWeekDay"/></s:param>
								<s:param name="selectedView"><s:property value="selectedView"/></s:param>
								<s:param name="selectedWeekDay"><s:property value="selectedWeekDay"/></s:param>
								<s:param name="inTimeOnly"><s:property value="inTimeOnly"/></s:param>
							</s:url>						
							<s:submit onclick="return openPopup('%{printUrl}', 'print_window');" id="printButton" key="schedule.printshift.weekly.print.button" theme="simple" cssClass="button"/>
						</td>
					</tr>				
				</table>
			</td>
		</tr>

		<tr>
			<td id="titleBar"><s:if test="!inTimeOnly"><s:text name="schedule.printshift.weekly.byemployee.title" /></s:if><s:else><s:text name="schedule.printshift.weekly.byemployee_in_time.title" /></s:else></td>
		</tr>

		<tr>
			<td align="center">
                  	<!-- Start week table -->
                  	<table align="center" id="calendarTable" border="0" cellpadding="0" cellspacing="0" colspan="0" cellspan="0">
                  		<tr>
                  			<td align="center">
                  				<table align="center" border="0" cellpadding="0" cellspacing="0" colspan="0" cellspan="0">
                  					<tr>
                  						<s:iterator id="prevDate" value="weekDaySelector.previousStartingWeekDays" status="itPrevDate">
			                  				<td class="calendarTableColumn" width="10%" nowrap="nowrap">
			                  					<a href="<s:url value="#" includeParams="none"/>" onclick="printweeklyshiftbyemployee_form_form.action='printweeklyshiftbyemployee_form_changeWeek.action'; printweeklyshiftbyemployee_form_form.selectedDate.value='<s:text name='schedule.addshift.weekdayselector.input.dateformat'><s:param value='prevDate'/></s:text>'; printweeklyshiftbyemployee_form_form.submit();" class="calendarUnselectedWeekLink">
			                  						<s:text name='schedule.printshift.weekdayselector.availableweek.dateformat'><s:param value='prevDate'/></s:text>
			                  					</a>
			                  				</td>
			                  			
				                  			<s:if test="!#itPrevDate.last">
				                  			<td class="calendarTableColumn" nowrap="nowrap"><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/>|<img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
	                  						</s:if>
                  						</s:iterator>
			                  			<td class="calendarTableColumn"><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
			                  			<td class="calendarTableColumn" nowrap="nowrap">
			                  				<table border="0" cellpadding="0" cellspacing="0" colspan="0" cellspan="0">
			                  					<tr>
			                  						<td><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
			                  						<td><a href="<s:url value="#" includeParams="none"/>" onclick="showWaitSplash(); printweeklyshiftbyemployee_form_form.action='printweeklyshiftbyemployee_form_changeWeek.action'; printweeklyshiftbyemployee_form_form.selectedDate.value='<s:text name='schedule.printshift.weekdayselector.input.dateformat'><s:param value='weekDaySelector.previousStartingWeekDay'/></s:text>'; printweeklyshiftbyemployee_form_form.submit();"><img src="<s:url value="/images/cal_prev.png" includeParams="none"/>" border="0"/></a></td>
			                  						<td><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
			                  						<td class="calendarSelectedWeekText" nowrap="nowrap"><s:text name='schedule.printshift.weekdayselector.selectedweek.dateformat'><s:param value='weekDaySelector.startingWeekDay'/></s:text></td>
			                  						<td><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
			                  						<td><a href="<s:url value="#" includeParams="none"/>" onclick="showWaitSplash(); printweeklyshiftbyemployee_form_form.action='printweeklyshiftbyemployee_form_changeWeek.action'; printweeklyshiftbyemployee_form_form.selectedDate.value='<s:text name='schedule.printshift.weekdayselector.input.dateformat'><s:param value='weekDaySelector.nextStartingWeekDay'/></s:text>'; printweeklyshiftbyemployee_form_form.submit();"><img src="<s:url value="/images/cal_next.png" includeParams="none"/>" border="0"/></a></td>
			                  						<td><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
			                  					</tr>
			                  				</table>
			                  			</td>
			                  			<td class="calendarTableColumn"><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
                  						<s:iterator id="nextDate" value="weekDaySelector.nextStartingWeekDays" status="itNextDate">
			                  				<td class="calendarTableColumn" width="10%" nowrap="nowrap">
			                  					<a href="<s:url value="#" includeParams="none"/>" onclick="showWaitSplash(); printweeklyshiftbyemployee_form_form.action='printweeklyshiftbyemployee_form_changeWeek.action'; printweeklyshiftbyemployee_form_form.selectedDate.value='<s:text name='schedule.printshift.weekdayselector.input.dateformat'><s:param value='nextDate'/></s:text>'; printweeklyshiftbyemployee_form_form.submit();" class="calendarUnselectedWeekLink">
			                  						<s:text name='schedule.printshift.weekdayselector.availableweek.dateformat'><s:param value='nextDate'/></s:text>
			                  					</a>
			                  				</td>
			                  			
				                  			<s:if test="!#itNextDate.last">
											<td class="calendarTableColumn" nowrap="nowrap"><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/>|<img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
											</s:if>
                  						</s:iterator>			                  			
			                  		</tr>
			                  	</table>
			                 </td>
			              </tr>
                  	</table>
                  	<!-- End week table -->  			
			</td>
		</tr>
		
		<tr>
			<td align="center">
			<table border="0" align="center" cellpadding="0" cellspacing="0" colspan="0" cellspan="0">
				<tr>
					<td class="errorMessage">
						<s:fielderror theme="simple" />
						<s:actionerror theme="simple" />
					</td>
				</tr>
				<tr>
					<td class="actionMessage">
						<s:actionmessage theme="simple"/>
					</td>
				</tr>
			</table>
			</td>
		</tr>	
		
		<tr>
			<td align="center">
			<!-- Schedule selection table -->
				<table class="printScheduleMainTable" id="printScheduleMainTable" cellpadding="1" cellspacing="0">
				    <!-- Header -->
				    <tr class="printScheduleMainTableHeader">
						<td class="printScheduleCellHeader"><s:text name="schedule.printshift.position"/></td> 
						<!-- Iterate week days -->
						<s:iterator id="weekDay" value="weekDaySelector.weekDays">
							<td class="printScheduleCellHeader"><s:text name='schedule.printshift.weekly.weekday.dateformat'><s:param value='weekDay'/></s:text></td>
						</s:iterator>
						<!-- End Iterate week days -->		
						<td class="printScheduleCellHeader"><s:text name="schedule.printshift.week_total_hours"/></td>		    
					</tr>
					<!-- Header -->
					
					<s:iterator id="employee" value="weeklyScheduleEmployees" status="itEmployeeStatus">
						<tr>
							<td class="printSchedulePositionTitle"><s:property value="fullName"/></td>
							<s:iterator id="weekDay" value="weekDaySelector.weekDays" status="itDayStatus">
							<td class="printSchedulePositionTotalHours"><!--s:property value="%{getDayTotalHoursFor(#employee, #itDayStatus.index)}"/-->&nbsp;</td>			
							</s:iterator>							
							<td class="printSchedulePositionTotalHours"><s:property value="%{getWeekTotalHoursFor(#employee)}"/></td>
						</tr>
						
						<s:iterator id="pos" value="getWeeklySchedulePositionsFor(#employee)" status="itPosStatus">
						<tr>
							<td class="printScheduleValueCell"><s:property value="name"/></td>
							<s:iterator id="weekDay" value="weekDaySelector.weekDays" status="itDayStatus">
							<td class="printScheduleValueCell" align="center">
							<s:set id="shifts" name="shifts" value="%{getCompleteShiftsFor(#pos, #employee, #itDayStatus.index)}"/>
							<s:if test="#shifts.size() > 0">
									<table border="0" cellpadding="1" cellspacing="0" align="center">
									<s:iterator id="shift" value="shifts" status="itShiftStatus">
										<tr>
											<td class="printScheduleValueTextCell"><s:property value="fromHourAsString"/></td>
											<s:if test="!inTimeOnly">
											<td class="printScheduleValueTextCell">-</td>
											<td class="printScheduleValueTextCell"><s:property value="toHourAsString"/></td>
											</s:if>
										</tr>
									</s:iterator>
									</table>
							</s:if>
							<s:else>
							&nbsp;
							</s:else>
							</td>
							</s:iterator>
							<td class="printScheduleValueCell"><s:property value="%{getWeekTotalHoursFor(#pos, #employee)}"/></td>
						</tr>
						</s:iterator>
					</s:iterator>
					<tr>
						<td class="printSchedulePositionTitle"><s:text name="schedule.printshift.total_hours"/></td>
						<s:iterator id="weekDay" value="weekDaySelector.weekDays" status="itDayStatus">
						<td class="printSchedulePositionTotalHours"><s:property value="%{getDayTotalHours(#itDayStatus.index)}"/></td>			
						</s:iterator>
						<td class="printSchedulePositionTotalHours"><s:property value="%{getWeekTotalHours()}"/></td>
					</tr>
				</table>			
			<!-- Schedule selection table -->
			</td>
		</tr>
	</table>
</s:form>
