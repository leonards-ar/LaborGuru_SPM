<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script language="JavaScript">
<!--

// -->
</script>

<br />
<s:form id="addweeklyshiftbyemployee_form" name="addweeklyshiftbyemployee_form" action="addweeklyshiftbyemployee_save" theme="simple">
	<s:hidden id="selectedDate" name="selectedDate"/>
	<s:hidden id="selectedWeekDay" name="selectedWeekDay"/>
	<s:hidden id="dailyVolume" name="dailyVolume"/>
	
	<table border="0" cellspacing="0" align="center">
		<tr>
			<td id="titleBar"><s:text name="schedule.addshift.weekly.byemployee.title" /></td>
		</tr>

		<tr>
			<td>
				<!-- Header tables -->
				<table border="0" cellpadding="2" cellspacing="2" width="100%">
					<tr>
						<td valign="top" align="right" width="45%">
							<!-- Left column -->
							<table id="fullHeightTable" border="0" cellpadding="0" cellspacing="0" colspan="0" cellspan="0">
								<tr valign="top">
									<td>
										<table id="windowTable">
											<tr>
												<td colspan="6" class="windowTableHeader"><s:text name="schedule.addshift.projection"/></td>
											</tr>
											<tr>
												<td class="windowTableLabel"><s:text name="schedule.addshift.daily_volume"/></td>
												<td class="windowTableLabel"><s:text name="schedule.addshift.schedule"/></td>
												<td class="windowTableLabel"><s:text name="schedule.addshift.target"/></td>
												<td class="windowTableLabel"><s:text name="schedule.addshift.diff"/></td>
												<td class="windowTableLabel"><s:text name="schedule.addshift.diffpercent"/></td>
											</tr>
											
											<tr>
												<td class="windowTableValue"><s:text name="currency"><s:param value="dailyVolume"/></s:text></td>
												<td class="windowTableValue" id="projection_schedule_total">&nbsp;</td>
												<td class="windowTableValue"><a href="#"><span id="projection_target_total"><s:property value="totalTarget"/></span></a></td>
												<td class="windowTableValue" id="projection_diff">&nbsp;</td>
												<td class="windowTableValue" id="projection_diff_percent">&nbsp;</td>
											</tr>
										</table>
									</td>
								</tr>
								
								<tr valign="bottom">
									<td>
										<table border="0" cellpadding="1" cellspacing="2">
											<tr>
												<td align="right" class="form_label"><s:text name="schedule.addshift.view"/></td>
												<td align="left">
													<s:select id="selectView" name="selectView" onchange="addshiftbyemployee_form.action=addshiftbyemployee_form.selectView.value; addshiftbyemployee_form.submit();" theme="simple" list="scheduleViewsMap" listKey="key" listValue="%{getText(value)}"/>
												</td>
												<td align="right" class="form_label"><s:text name="schedule.addshift.positions"/></td>
												<td align="left">
													<s:select onchange="addshiftbyemployee_form.action='addshiftbyemployee_selectPosition.action'; addshiftbyemployee_form.submit();" name="position.id" list="positions" listKey="id" listValue="name" theme="simple" headerKey="" headerValue="%{getText('schedule.addshift.positions.header.label')}"/>					
												</td>
											</tr>
										</table>
									</td>
								</tr>

								<tr valign="bottom">
									<td>
										<table border="0" cellpadding="1" cellspacing="2">
											<tr>
												<td align="right" class="form_label"><s:text name="schedule.addshift.copy.label"/></td>
												<td align="left"><s:datetimepicker displayFormat="MM/dd/yyyy" disabled="true" name="copyTargetDay" theme="simple"/></td>
												<td align="right"><s:submit id="copyButton" key="schedule.addshift.copy.button" action="addshiftbyemployee_copySchedule" theme="simple" cssClass="button"/></td>
											</tr>
										</table>
									</td>
								</tr>								
							</table>
							<!-- Left column -->
						</td>
						
						<td width="10%">&nbsp;</td>
						
						<td valign="top" width="45%" align="left">
							<!-- Right column -->
							<table id="windowTable">
								<tr>
									<td class="windowTableHeader"><s:text name='schedule.addshift.weekday.dateformat'><s:param value='weekDaySelector.selectedDay'/></s:text></td>
									<td class="windowTableHeader"><s:text name="schedule.addshift.schedule"/></td>
									<td class="windowTableHeader"><s:text name="schedule.addshift.target"/></td>
									<td class="windowTableHeader"><s:text name="schedule.addshift.diff"/></td>
								</tr>
								
								<!--  For each position -->
								<s:iterator value="positions" id="pos">
								<tr>
									<td class="windowTableValue"><s:property value="name"/></td>
									<td class="windowTableValue" id="<s:property value="id"/>_position_schedule_total">&nbsp;</td>
									<td class="windowTableValue" id="<s:property value="id"/>_position_target_total"><s:property value="%{getTotalPositionTarget(#pos)}"/></td>
									<td class="windowTableValue" id="<s:property value="id"/>_position_diff">&nbsp;</td>
								</tr>
								
								</s:iterator>
								<!-- For each position -->
								
								<tr>
									<td class="windowTableValue"><b><s:text name="schedule.addshift.total"/></b></td>
									<td class="windowTableValue" id="position_schedule_total"><b>&nbsp;</b></td>
									<td class="windowTableValue" id="position_target_total"><b>&nbsp;</b></td>
									<td class="windowTableValue" id="position_diff"><b>&nbsp;</b></td>
								</tr>
								
							</table>
							<!-- Right column -->
						</td>
					</tr>
				</table>
				<!-- Header tables -->
			</td>
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
			                  					<a href="<s:url value="#" includeParams="none"/>" onclick="addshiftbyemployee_form.action='addshiftbyemployee_changeWeek.action'; addshiftbyemployee_form.selectedDate.value='<s:text name='schedule.addshift.weekdayselector.input.dateformat'><s:param value='prevDate'/></s:text>'; addshiftbyemployee_form.submit();" class="calendarUnselectedWeekLink">
			                  						<s:text name='schedule.addshift.weekdayselector.availableweek.dateformat'><s:param value='prevDate'/></s:text>
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
			                  						<td><a href="<s:url value="#" includeParams="none"/>" onclick="addshiftbyemployee_form.action='addshiftbyemployee_changeWeek.action'; addshiftbyemployee_form.selectedDate.value='<s:text name='schedule.addshift.weekdayselector.input.dateformat'><s:param value='weekDaySelector.previousStartingWeekDay'/></s:text>'; addshiftbyemployee_form.submit();"><img src="<s:url value="/images/cal_prev.png" includeParams="none"/>" border="0"/></a></td>
			                  						<td><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
			                  						<td class="calendarSelectedWeekText" nowrap="nowrap"><s:text name='schedule.addshift.weekdayselector.selectedweek.dateformat'><s:param value='weekDaySelector.startingWeekDay'/></s:text></td>
			                  						<td><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
			                  						<td><a href="<s:url value="#" includeParams="none"/>" onclick="addshiftbyemployee_form.action='addshiftbyemployee_changeWeek.action'; addshiftbyemployee_form.selectedDate.value='<s:text name='schedule.addshift.weekdayselector.input.dateformat'><s:param value='weekDaySelector.nextStartingWeekDay'/></s:text>'; addshiftbyemployee_form.submit();"><img src="<s:url value="/images/cal_next.png" includeParams="none"/>" border="0"/></a></td>
			                  						<td><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
			                  					</tr>
			                  				</table>
			                  			</td>
			                  			<td class="calendarTableColumn"><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
                  						<s:iterator id="nextDate" value="weekDaySelector.nextStartingWeekDays" status="itNextDate">
			                  				<td class="calendarTableColumn" width="10%" nowrap="nowrap">
			                  					<a href="<s:url value="#" includeParams="none"/>" onclick="addshiftbyemployee_form.action='addshiftbyemployee_changeWeek.action'; addshiftbyemployee_form.selectedDate.value='<s:text name='schedule.addshift.weekdayselector.input.dateformat'><s:param value='nextDate'/></s:text>'; addshiftbyemployee_form.submit();" class="calendarUnselectedWeekLink">
			                  						<s:text name='schedule.addshift.weekdayselector.availableweek.dateformat'><s:param value='nextDate'/></s:text>
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
			              <tr class="calendarWeekDayTableRowSeparator">
			              	<td><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
			              </tr>
			              <tr>
			              	<td align="center" class="calendarWeekDayTableColumn">
								<table align="center" border="0" cellpadding="0" cellspacing="0" colspan="0" cellspan="0">
                  					<tr>
                  						<s:iterator id="weekDay" value="weekDaySelector.weekDays" status="itWeekDay">
	                  						<td width="12%" class="availableWeekDay"><a href="<s:url value="#" includeParams="none"/>" onclick="addshiftbyemployee_form.action='addshiftbyemployee_changeDay.action'; addshiftbyemployee_form.selectedWeekDay.value='<s:text name='schedule.addshift.weekdayselector.input.dateformat'><s:param value='weekDay'/></s:text>'; addshiftbyemployee_form.submit();" class="availableWeekDayLink">
	                  						<s:text name='schedule.addshift.weekday.dateformat'><s:param value='weekDay'/></s:text>
	                  						</a>
	                  						</td>                  						
	                  						<td><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
                  						</s:iterator>
                  						<td width="12%" class="selectedWeekDay"><s:text name="schedule.addshift.weekselector.week"/></td>
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
				<table class="scheduleMainTable" id="scheduleMainTable" cellpadding="2" cellspacing="0">
				    <!-- Header -->
				    <tr class="scheduleMainTableHeader">
						<td class="scheduleCellHeader"><s:text name="schedule.addshift.employee"/></td>    
						<td class="scheduleCellHeader"><s:text name="schedule.addshift.position"/></td>    
						<td class="scheduleCellHeader" width="45px"><s:text name="schedule.addshift.total_hours"/></td>
						<!-- Iterate week days -->
						<s:iterator id="weekDay" value="weekDaySelector.weekDays">
							<td class="scheduleCellHeader" colspan="2"><s:text name='schedule.addshift.weekly.weekday.dateformat'><s:param value='weekDay'/></s:text></td>
						</s:iterator>
						<!-- End Iterate week days -->				    
					</tr>
				    <!-- Header -->
				    
				    
				    
				    <!-- Employees -->
				    <s:iterator id="dataRow" value="weeklyScheduleData.scheduleData" status="itScheduleData">
				    <tr class="scheduleMainTableEmployeeRow">
						<s:hidden id="scheduleOriginalEmployeeId_%{#itScheduleData.index}" name="weeklyScheduleData.scheduleData[%{#itScheduleData.index}].originalEmployeeId"/>
						<s:hidden id="scheduleEmployeeMaxHoursDay_%{#itScheduleData.index}" name="weeklyScheduleData.scheduleData[%{#itScheduleData.index}].employeeMaxHoursDay"/>
						<s:hidden id="scheduleEmployeeMaxHoursWeek_%{#itScheduleData.index}" name="weeklyScheduleData.scheduleData[%{#itScheduleData.index}].employeeMaxHoursWeek"/>
						<s:hidden id="scheduleEmployeeMaxDaysWeek_%{#itScheduleData.index}" name="weeklyScheduleData.scheduleData[%{#itScheduleData.index}].employeeMaxDaysWeek"/>
						<s:hidden id="scheduleGroupById_%{#itScheduleData.index}" name="weeklyScheduleData.scheduleData[%{#itScheduleData.index}].groupById"/>
				    	<s:if test="#dataRow.firstRow">
				    	<td class="scheduleNameCell" rowspan="<s:property value="weeklyScheduleData.getCountFor(#dataRow.employeeId)"/>" valign="top">
				    		<s:if test="%{editable}">
							<s:url id="employeeList" action="scheduleemployeeautocomplete" includeParams="none"/>
							<s:autocompleter id="scheduleEmployee_%{#itScheduleData.index}" onchange="XXXreloadEmployeeMaxHoursDay('', %{#itScheduleData.index}); return true;" name="weeklyScheduleData.scheduleData[%{#itScheduleData.index}].employeeName" keyName="weeklyScheduleData.scheduleData[%{#itScheduleData.index}].employeeId" loadMinimumCount="3" forceValidOption="true" theme="ajax" href="%{employeeList}" dataFieldName="storeEmployees" autoComplete="true" searchType="substring"/>
							<script>djConfig.searchIds.push("scheduleEmployee_<s:property value="#itScheduleData.index"/>");</script>
							</s:if>
							<s:else>
							<s:property value="#dataRow.employeeName"/>
							</s:else>
				    	</td>
				    	</s:if>
						<td class="scheduleNameCell">
							<s:if test="%{editable}">
					    		<s:if test="%{position == null}">
					    			<s:select id="scheduleposition_%{#itScheduleData.index}" onchange="XXXrefreshRows(''); XXXupdatePositionTotals();" name="weeklyScheduleData.scheduleData[%{#itScheduleData.index}].positionId" list="positions" listKey="id" listValue="name" theme="simple"/>
					    		</s:if>
					    		<s:else>
					    			<s:property value="position.name"/>
					    			<s:hidden id="scheduleposition_%{#itScheduleData.index}" name="weeklyScheduleData.scheduleData[%{#itScheduleData.index}].positionId" value="%{position.id}"/>
					    		</s:else>
				    		</s:if>
				    		<s:else>
				    		<s:property value="#dataRow.positionName"/>
				    		</s:else>
				    		<s:hidden name="weeklyScheduleData.scheduleData[%{#itScheduleData.index}].positionName"/>						
						</td>    
						<td class="scheduleNameCell" id="scheduleWeeklyTotal_<s:property value="#itScheduleData.index"/>">&nbsp;</td>
						
						<s:iterator id="dayDataEntry" value="weeklySchedule" status="itDataEntry">
						<td class="scheduleValueCell">
							<table border="0" align="center" cellpadding="1" cellspacing="0" colspan="0" cellspan="0">
								<s:hidden name="weeklyScheduleData.scheduleData[%{#itScheduleData.index}].weeklySchedule[%{#itDataEntry.index}].day"/>
								<s:hidden name="weeklyScheduleData.scheduleData[%{#itScheduleData.index}].weeklySchedule[%{#itDataEntry.index}].multipleShifts"/>
								<tr>
									<s:if test="%{editable}">
									<td><s:textfield id="weeklyScheduleInHour_%{#itScheduleData.index}_%{#itDataEntry.index}" name="weeklyScheduleData.scheduleData[%{#itScheduleData.index}].weeklySchedule[%{#itDataEntry.index}].inHourAsString" onchange="updateTime(this);" size="2" maxlength="8"/></td>
									<td><s:textfield id="weeklyScheduleOutHour_%{#itScheduleData.index}_%{#itDataEntry.index}" name="weeklyScheduleData.scheduleData[%{#itScheduleData.index}].weeklySchedule[%{#itDataEntry.index}].outHourAsString" onchange="updateTime(this);" size="2" maxlength="8"/></td>
									</s:if>
									<s:else>
									<s:hidden name="weeklyScheduleData.scheduleData[%{#itScheduleData.index}].weeklySchedule[%{#itDataEntry.index}].inHourAsString"/>
									<s:hidden name="weeklyScheduleData.scheduleData[%{#itScheduleData.index}].weeklySchedule[%{#itDataEntry.index}].outHourAsString"/>
									<td class="scheduleValueCellText"><s:property value="inHourAsString"/></td>
									<td class="scheduleValueCellText">-</td>
									<td class="scheduleValueCellText"><s:property value="outHourAsString"/></td>
									</s:else>
								</tr>
							</table>
						</td>
						<td class="scheduleValueCell" width="20px" id="weeklyScheduleOutHour_<s:property value="#itScheduleData.index"/>_<s:property value="#itDataEntry.index"/>"><s:property value="totalHoursAsString"/></td>
						</s:iterator>
				    </tr>
				    </s:iterator>
				    <!-- Employees -->

					<s:if test="%{editable}">
				    <!-- New Employee -->
				    <tr>
				    	<td class="scheduleNameCell">
							<s:url id="employeeList" action="scheduleemployeeautocomplete" includeParams="none"/>
							<s:autocompleter id="newEmployeeName" name="newEmployeeName" loadMinimumCount="3" keyName="newEmployeeId" forceValidOption="true" theme="ajax" href="%{employeeList}" dataFieldName="storeEmployees" autoComplete="true" searchType="substring" />
							<script>djConfig.searchIds.push("newEmployeeName");</script>
				    	</td>
				    	<td class="scheduleNameCell">
				    		<s:if test="%{position == null}">
								<s:select name="newEmployeePositionId" list="positions" listKey="id" listValue="name" theme="simple"/>
				    		</s:if>
				    		<s:else>
				    			<s:property value="position.name"/>
				    			<s:hidden name="newEmployeePositionId" value="%{position.id}"/>
				    		</s:else>				    	
				    	</td>
						<td class="scheduleNameCell" align="center"><a href="<s:url value="#" includeParams="none"/>" onclick="showWaitSplash(); addweeklyshiftbyemployee_form.action='addweeklyshiftbyemployee_addEmployee.action'; addweeklyshiftbyemployee_form.submit();"><img src="<s:url value="/images/add.png" includeParams="none"/>" /></a></td>
						<td class="scheduleValueCell" colspan="14">&nbsp;</td>
				    </tr>				    
				    <!-- New Employee -->
				    </s:if>
				</table>			
			<!-- Schedule selection table -->
			</td>
		</tr>

		<s:if test="%{editable}">
		<tr>
			<td width="100%" align="right">
				<table border="0" cellpadding="1" cellspacing="5" colspan="0" cellspan="0">
					<tr>
						<td><s:submit onclick="return showWaitSplash();" id="saveButton" name="saveSchedule" key="save.button" theme="simple" cssClass="button"/></td>
						<td><s:submit id="cancelButton" key="cancel.button" action="addshiftbyemployee_cancel" theme="simple" cssClass="button"/></td>		                    
      				</tr>
     			</table>                    
    		</td>
		</tr>		
		</s:if>
	</table>
</s:form>
