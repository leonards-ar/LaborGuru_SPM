<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<br />
<s:form id="addshiftbyemployee_form" name="addshiftbyemployee_form" action="addshiftbyemployee_save" theme="simple">
	<s:hidden id="selectedDate" name="selectedDate"/>
	<s:hidden id="selectedWeekDay" name="selectedWeekDay"/>
	<s:hidden id="dailyVolume" name="dailyVolume"/>
	
	<table border="0" cellspacing="0" align="center">
		<tr>
			<td id="titleBar"><s:text name="schedule.addshift.byemployee.title" /></td>
		</tr>

		<tr>
			<td>
				<!-- Header tables -->
				<table border="0" cellpadding="2" cellspacing="2">
					<tr>
						<td valign="top">
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
													<select name="selectView">
														<option>--- Select a Weekly ---</option>
													</select>
												</td>
												<td align="right" class="form_label"><s:text name="schedule.addshift.positions"/></td>
												<td align="left">
													<s:select onchange="addshiftbyemployee_form.action='addshiftbyemployee_selectPosition.action'; addshiftbyemployee_form.submit();" name="position.id" list="positions" listKey="id" listValue="name" theme="simple" headerKey="" headerValue="%{getText('schedule.addshift.positions.header.label')}"/>					
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							<!-- Left column -->
						</td>
						
						<td valign="top">
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
									<td class="windowTableValue" id="<s:property value="id"/>_position_target_total"><s:property value="getTotalPositionTarget(#pos)"/></td>
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
                  						<s:if test="%{weekDaySelector.isSelectedWeekDay(#weekDay)}">
                  						<td width="12%" class="selectedWeekDay"><s:text name='schedule.addshift.weekday.dateformat'><s:param value='weekDay'/></s:text></td>
                  						</s:if>
                  						<s:else>
                  						<td width="12%" class="availableWeekDay"><a href="<s:url value="#" includeParams="none"/>" onclick="addshiftbyemployee_form.action='addshiftbyemployee_changeDay.action'; addshiftbyemployee_form.selectedWeekDay.value='<s:text name='schedule.addshift.weekdayselector.input.dateformat'><s:param value='weekDay'/></s:text>'; addshiftbyemployee_form.submit();" class="availableWeekDayLink">
                  						<s:text name='schedule.addshift.weekday.dateformat'><s:param value='weekDay'/></s:text>
                  						</a>
                  						</td>                  						
                  						</s:else>
                  						<td><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
                  						</s:iterator>
                  						<td width="12%" class="availableWeekDay"><a href="#" class="availableWeekDayLink"><s:text name="schedule.addshift.weekselector.week"/></a></td>
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
		
		<s:if test="%{!weekDaySelector.isSelectedDateBeforeToday()}">
		<tr>
			<td align="center" width="100%">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td align="center" width="100%">
							<!-- Schedule selection action table -->
							<table id="scheduleActionsTable" border="0" cellpadding="0" cellspacing="0">
								<tr>
							    	<td nowrap><a href="#" onclick="changeAction(1, '');"><img title="<s:text name="schedule.addshift.shift.alt"/>" alt="<s:text name="schedule.addshift.shift.alt"/>" id="scheduleShiftImage" class="scheduleActionImageOff" border="0" src="<s:url value="/images/add_shift.gif" includeParams="none"/>"/></a></td>
							    	<td class="scheduleActionLabel" valign="middle">&nbsp;</td>
							    	<td class="scheduleActionLabel" valign="middle"><a href="#" class="scheduleActionLink" onclick="changeAction(1, '');"><s:text name="schedule.addshift.shift.alt"/></a></td>
							        <td class="scheduleActionLabel" valign="middle">&nbsp;|&nbsp;</td>
							    	<td nowrap><a href="#" onclick="changeAction(3, '');"><img title="<s:text name="schedule.addshift.break.alt"/>" alt="<s:text name="schedule.addshift.break.alt"/>" id="scheduleBreakImage" class="scheduleActionImageOff" border="0" src="<s:url value="/images/add_break.gif" includeParams="none"/>"/></a></td>
							    	<td class="scheduleActionLabel" valign="middle">&nbsp;</td>
							    	<td class="scheduleActionLabel" valign="middle"><a href="#" class="scheduleActionLink" onclick="changeAction(3, '');"><s:text name="schedule.addshift.break.alt"/></a></td>
							        <td class="scheduleActionLabel" valign="middle">&nbsp;|&nbsp;</td>
							    	<td nowrap><a href="#" onclick="changeAction(5, '');"><img title="<s:text name="schedule.addshift.free.alt"/>" alt="<s:text name="schedule.addshift.free.alt"/>" id="scheduleDeleteImage" class="scheduleActionImageOff" border="0" src="<s:url value="/images/del_shift.gif" includeParams="none"/>"/></a></td>
							    	<td class="scheduleActionLabel" valign="middle">&nbsp;</td>
							    	<td class="scheduleActionLabel" valign="middle"><a href="#" class="scheduleActionLink" onclick="changeAction(5, '');"><s:text name="schedule.addshift.free.alt"/></a></td>
							        <td class="scheduleActionMessage">&nbsp;&nbsp;</td>
									<td id="actionMessage" class="scheduleActionMessage"></td>							    
							    </tr>
							</table>				
							<!-- Schedule selection action table -->
						</td>
					</tr>
				</table>
			</td>
		</tr>
		</s:if>
		
		<tr>
			<td align="center">
			<!-- Schedule selection table -->
				<table class="scheduleMainTable" id="scheduleMainTable" cellpadding="0" cellspacing="0">
				    <!-- Header -->
				    <tr class="scheduleMainTableHeader">
						<td class="scheduleCellHeader"><s:text name="schedule.addshift.position"/></td>    
						<td class="scheduleCellHeader"><s:text name="schedule.addshift.employee"/></td>    
						<td class="scheduleCellHeader" width="45px"><s:text name="schedule.addshift.in_hour"/></td>    
						<td class="scheduleCellHeader" width="45px"><s:text name="schedule.addshift.out_hour"/></td>
						<td class="scheduleCellHeader" width="45px"><s:text name="schedule.addshift.total_hours"/></td>
						<s:iterator id="hourLabel" value="scheduleLabelHours" status="#itHourLabel">     
							<td class="scheduleCellHeader" colspan="<s:property value="colspan"/>"><s:text name='schedule.addshift.hour.dateformat'><s:param value='hour'/></s:text></td>
						</s:iterator>         
				    </tr>
				    <!-- Header -->
				    
				    <!-- Employees -->
				    <s:iterator id="data" value="scheduleData" status="itScheduleData">
					    <tr class="scheduleMainTableEmployeeRow">
					    	<td class="scheduleNameCell">
					    	<s:if test="%{weekDaySelector.isSelectedDateBeforeToday()}">
					    		<s:property value="#data.positionName"/>
					    	</s:if>
					    	<s:else>
					    		<s:if test="%{position == null}">
					    			<s:select id="scheduleposition_%{#itScheduleData.index}" onchange="refreshRows('');" name="scheduleData[%{#itScheduleData.index}].positionId" list="positions" listKey="id" listValue="name" theme="simple"/>
					    		</s:if>
					    		<s:else>
					    			<s:property value="position.name"/>
					    			<s:hidden id="scheduleposition_%{#itScheduleData.index}" name="scheduleData[%{#itScheduleData.index}].positionId" value="%{position.id}"/>
					    		</s:else>
					    		<s:hidden name="scheduleData[%{#itScheduleData.index}].positionName"/>
					    	</s:else>
					    	</td>
							<td class="scheduleNameCell">
							<s:if test="%{weekDaySelector.isSelectedDateBeforeToday()}">
					    		<s:property value="#data.employeeName"/>
							</s:if>
							<s:else>
								<s:hidden id="scheduleOriginalEmployeeId_%{#itScheduleData.index}" name="scheduleData[%{#itScheduleData.index}].originalEmployeeId"/>
								<s:hidden id="scheduleEmployeeMaxWeekHours_%{#itScheduleData.index}" name="scheduleData[%{#itScheduleData.index}].employeeMaxHoursDay"/>
								<s:url id="employeeList" action="scheduleemployeeautocomplete" includeParams="none"/>
								<s:autocompleter id="scheduleEmployee_%{#itScheduleData.index}" onchange="reloadEmployeeMaxHoursDay('', %{#itScheduleData.index}); return true;" name="scheduleData[%{#itScheduleData.index}].employeeName" keyName="scheduleData[%{#itScheduleData.index}].employeeId" loadMinimumCount="3" forceValidOption="true" theme="ajax" href="%{employeeList}" dataFieldName="storeEmployees" autoComplete="true" searchType="substring"/>
							</s:else> 
							</td>    
							<td class="scheduleValueCell" id="inHour_<s:property value="#itScheduleData.index"/>"><s:hidden id="inHourInput_%{#itScheduleData.index}" name="scheduleData[%{#itScheduleData.index}].inHour"/><s:property value="#data.inHour"/></td>    
							<td class="scheduleValueCell" id="outHour_<s:property value="#itScheduleData.index"/>"><s:hidden id="outHourInput_%{#itScheduleData.index}" name="scheduleData[%{#itScheduleData.index}].outHour"/><s:property value="#data.outHour"/></td>
							<td class="scheduleValueCell" id="totalHours_<s:property value="#itScheduleData.index"/>"><s:hidden id="totalHoursInput_%{#itScheduleData.index}" name="scheduleData[%{#itScheduleData.index}].totalHours"/><s:property value="#data.totalHours"/></td>            
							<s:iterator id="hour" value="scheduleIndividualHours" status="itHour">
								<s:if test="#itHour.first">
									<s:iterator id="startToIgnore" value="scheduleIndividualStartHoursToIgnore">
										<td class="scheduleUnavailable">
											<img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/>
											<s:hidden id="schedulehour_%{#itScheduleData.index}_%{#itHour.index}" name="scheduleData[%{#itScheduleData.index}].hours[%{#itHour.index}]"/>
											<s:hidden id="schedule_%{#itScheduleData.index}_%{#itHour.index}" name="scheduleData[%{#itScheduleData.index}].schedule[%{#itHour.index}]"/>
										</td>
									</s:iterator>
								</s:if>  
								<td id='cell_<s:property value="#itScheduleData.index"/>_<s:property value="#itHour.index"/>' <s:if test="%{!weekDaySelector.isSelectedDateBeforeToday()}">onclick="scheduleClick(this, <s:property value="#itScheduleData.index"/>,<s:property value="#itHour.index"/>,'<s:property value="#data.positionId"/>', '');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);"</s:if> class='<s:if test="%{#data.isBreakShift(#itHour.index)}">scheduleBreak</s:if><s:else><s:if test="%{#data.isFreeShift(#itHour.index)}">scheduleEmpty</s:if><s:else>scheduleSelected</s:else></s:else>'><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/>
									<s:hidden id="schedulehour_%{#itScheduleData.index}_%{#itHour.index}" name="scheduleData[%{#itScheduleData.index}].hours[%{#itHour.index}]"/>
									<s:hidden id="schedule_%{#itScheduleData.index}_%{#itHour.index}" name="scheduleData[%{#itScheduleData.index}].schedule[%{#itHour.index}]"/>
								</td>            
								<s:if test="#itHour.last">
									<s:iterator id="startToIgnore" value="scheduleIndividualEndHoursToIgnore">
										<td class="scheduleUnavailable">
											<img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/>
											<s:hidden id="schedulehour_%{#itScheduleData.index}_%{#itHour.index}" name="scheduleData[%{#itScheduleData.index}].hours[%{#itHour.index}]"/>
											<s:hidden id="schedule_%{#itScheduleData.index}_%{#itHour.index}" name="scheduleData[%{#itScheduleData.index}].schedule[%{#itHour.index}]"/>
										</td>
									</s:iterator>
								</s:if> 
							</s:iterator>
					    </tr>
					</s:iterator>
					
					<s:if test="%{!weekDaySelector.isSelectedDateBeforeToday()}">
					<!-- Add new employee to schedule -->
				    <tr class="scheduleMainTableEmployeeRow">
				    	<td class="scheduleNameCell">
					    		<s:if test="%{position == null}">
									<s:select name="newEmployeePositionId" list="positions" listKey="id" listValue="name" theme="simple"/>
					    		</s:if>
					    		<s:else>
					    			<s:property value="position.name"/>
					    			<s:hidden name="newEmployeePositionId" value="%{position.id}"/>
					    		</s:else>
				    	</td>
						<td class="scheduleNameCell">
							<table border="0" cellpadding="0" cellspacing="0" colspan="0" cellspan="0">
								<tr>
									<td>
										<s:url id="employeeList" action="scheduleemployeeautocomplete" includeParams="none"/>
										<s:autocompleter name="newEmployeeName" loadMinimumCount="3" keyName="newEmployeeId" forceValidOption="true" theme="ajax" href="%{employeeList}" dataFieldName="storeEmployees" autoComplete="true" searchType="substring" />
									</td>
									<td>
										<img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/>
									</td>
									<td><a href="<s:url value="#" includeParams="none"/>" onclick="addshiftbyemployee_form.action='addshiftbyemployee_addEmployee.action'; addshiftbyemployee_form.submit();"><img src="<s:url value="/images/add.png" includeParams="none"/>" /></a></td>
								</tr>
							</table>
						</td>    
						<td class="scheduleValueCell"><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>    
						<td class="scheduleValueCell"><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
						<td class="scheduleValueCell"><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>            
						<s:iterator id="hour" value="scheduleIndividualHours" status="itHour">
							<s:if test="#itHour.first">
								<s:iterator id="startToIgnore" value="scheduleIndividualStartHoursToIgnore">
									<td class="scheduleUnavailable"><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
								</s:iterator>
							</s:if>  
							<td class="scheduleUnavailable"><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
							<s:if test="#itHour.last">
								<s:iterator id="startToIgnore" value="scheduleIndividualEndHoursToIgnore">
									<td class="scheduleUnavailable"><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
								</s:iterator>
							</s:if> 
						</s:iterator>
				    </tr>					
				    <!-- Employees -->
				    </s:if>
				    
				    <!-- Staffing -->
				    <tr class="scheduleMainTableEmployeeRow">
				    	<td colspan="4" class="scheduleStaffingNameCell"><s:text name="schedule.addshift.staffing.total"/>&nbsp;&nbsp;</td>
				    	<td class="scheduleStaffingValueCell" id='total_cell'>&nbsp;</td>
							<s:iterator id="staffing" value="minimumStaffing" status="itStaffing">
								<s:if test="#itStaffing.first">
									<s:iterator id="startToIgnore" value="scheduleIndividualStartHoursToIgnore">
										<td id='total_cell_<s:property value="#itStaffing.index"/>' class="scheduleStaffingUnavailable"><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
									</s:iterator>
								</s:if>
								<td id='total_cell_<s:property value="#itStaffing.index"/>' class="scheduleTotalValueCell"><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
								<s:if test="#itStaffing.last">
									<s:iterator id="startToIgnore" value="scheduleIndividualEndHoursToIgnore">
										<td id='total_cell_<s:property value="#itStaffing.index"/>' class="scheduleStaffingUnavailable"><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
									</s:iterator>
								</s:if> 
							</s:iterator>				    	
				    </tr>
				    
				    <tr class="scheduleMainTableEmployeeRow">
				    	<td colspan="4" class="scheduleStaffingNameCell"><s:text name="schedule.addshift.staffing.minimum_staffing"/>&nbsp;&nbsp;</td>
				    	<td class="scheduleStaffingValueCell" id='total_staffing_cell'><s:property value='totalMinimutStaffingTime'/></td>
							<s:iterator id="staffing" value="minimumStaffing" status="itStaffing">
								<s:if test="#itStaffing.first">
									<s:iterator id="startToIgnore" value="scheduleIndividualStartHoursToIgnore">
										<td id='staffing_cell_<s:property value="#itStaffing.index"/>' class="scheduleStaffingUnavailable"><s:hidden name="minimumStaffing[%{#itStaffing.index}]" value="0"/><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
									</s:iterator>
								</s:if>
								<td id='staffing_cell_<s:property value="#itStaffing.index"/>' class="scheduleStaffingCalculated">
									<div id='staffing_div_<s:property value="#itStaffing.index"/>'><s:property/></div>
									<s:hidden name="minimumStaffing[%{#itStaffing.index}]"/>
								</td>
								<s:if test="#itStaffing.last">
									<s:iterator id="startToIgnore" value="scheduleIndividualEndHoursToIgnore">
										<td id='staffing_cell_<s:property value="#itStaffing.index"/>' class="scheduleStaffingUnavailable"><s:hidden name="minimumStaffing[%{#itStaffing.index}]" value="0"/><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
									</s:iterator>
								</s:if> 
							</s:iterator>				    	
				    </tr>	

				    <tr class="scheduleMainTableEmployeeRow">
				    	<td colspan="4" class="scheduleStaffingNameCell"><s:text name="schedule.addshift.staffing.difference"/>&nbsp;&nbsp;</td>
				    	<td class="scheduleStaffingValueCell" id='total_difference_cell'>&nbsp;</td>
							<s:iterator id="staffing" value="minimumStaffing" status="itStaffing">
								<s:if test="#itStaffing.first">
									<s:iterator id="startToIgnore" value="scheduleIndividualStartHoursToIgnore">
										<td id='difference_cell_<s:property value="#itStaffing.index"/>' class="scheduleStaffingUnavailable"><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
									</s:iterator>
								</s:if>
								<td id='difference_cell_<s:property value="#itStaffing.index"/>' class="scheduleStaffingDifferenceEqual"><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
								<s:if test="#itStaffing.last">
									<s:iterator id="startToIgnore" value="scheduleIndividualEndHoursToIgnore">
										<td id='difference_cell_<s:property value="#itStaffing.index"/>' class="scheduleStaffingUnavailable"><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
									</s:iterator>
								</s:if> 
							</s:iterator>				    	
				    </tr>				    			    
				    <!--  Staffing -->
				</table>			
			<!-- Schedule selection table -->
			</td>
		</tr>
		
		<tr>
			<td width="100%" align="left">
				<!-- Caption -->
				<table border="0" cellpadding="1" cellspacing="2">
					<tr>
						<td class="scheduleSelectedCaption"><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
						<td class="captionText"><s:text name="schedule.addshift.shift.caption"/></td>
						<td>&nbsp;</td>
						<td class="scheduleBreakCaption"><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
						<td class="captionText"><s:text name="schedule.addshift.break.caption"/></td>
						<td>&nbsp;</td>
						<td class="scheduleEmptyCaption"><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
						<td class="captionText"><s:text name="schedule.addshift.free.caption"/></td>
					</tr>
				</table>
				<!-- Caption -->			
			</td>
		</tr>
				
		<s:if test="%{!weekDaySelector.isSelectedDateBeforeToday()}">
		<tr>
			<td width="100%" align="right">
				<table border="0" cellpadding="1" cellspacing="5" colspan="0" cellspan="0">
					<tr>
						<td><s:submit id="saveButton" name="saveEmployee" key="save.button" theme="simple" cssClass="button"/></td>
						<td><s:submit id="cancelButton" key="cancel.button" action="addshiftbyemployee_cancel" theme="simple" cssClass="button"/></td>		                    
      				</tr>
     			</table>                    
    		</td>
		</tr>
		</s:if>
	</table>
</s:form>




<script language="javascript" type="text/javascript">
initialize(<s:property value="totalIndividualHours"/>, <s:property value="scheduleRows"/>, '<s:property value="breakId"/>', '<s:text name="schedule.addshift.cannot_change_row_message"/>', '<s:text name="schedule.addshift.start_time_message"/>', '<s:text name="schedule.addshift.end_time_message"/>', <s:property value="positions.size()"/>);
<s:iterator id="pos" value="positions" status="posStatus">
	addPositionId(<s:property value="#posStatus.index"/>, '<s:property value="id"/>');
</s:iterator>
refreshRows('');
changeAction(1, '');
updateProjectionScheduleTotal();
</script>
