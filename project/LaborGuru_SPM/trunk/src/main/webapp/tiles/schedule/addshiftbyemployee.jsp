<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<br />
<s:form id="addshiftbyemployee_form" name="addshiftbyemployee_form" action="addshiftbyemployee_save" theme="simple">
	<s:hidden id="selectedDate" name="selectedDate"/>
	<s:hidden id="selectedWeekDay" name="selectedWeekDay"/>
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
												<td class="windowTableLabel"><s:text name="schedule.addshift.sales"/></td>
												<td class="windowTableLabel"><s:text name="schedule.addshift.checks"/></td>
												<td class="windowTableLabel"><s:text name="schedule.addshift.schedule"/></td>
												<td class="windowTableLabel"><s:text name="schedule.addshift.target"/></td>
												<td class="windowTableLabel"><s:text name="schedule.addshift.diff"/></td>
												<td class="windowTableLabel"><s:text name="schedule.addshift.diffpercent"/></td>
											</tr>
											
											<tr>
												<td class="windowTableValue">$2,500</td>
												<td class="windowTableValue">800</td>
												<td class="windowTableValue">80</td>
												<td class="windowTableValue"><a href="#">74.2</a></td>
												<td class="windowTableValue">5.8</td>
												<td class="windowTableValue">8%</td>
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
													<select name="selectPositions">
														<option>--- All Positions ---</option>
													</select>
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
								<tr>
									<td class="windowTableValue">Cook</td>
									<td class="windowTableValue">55</td>
									<td class="windowTableValue">51.2</td>
									<td class="windowTableValue">3.8</td>
								</tr>
								<!-- For each position -->
								
								<tr>
									<td class="windowTableValue"><b><s:text name="schedule.addshift.total"/></b></td>
									<td class="windowTableValue"><b>55</b></td>
									<td class="windowTableValue"><b>51.2</b></td>
									<td class="windowTableValue"><b>3.8</b></td>
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
			<td class="errorMessage" align="center">
			<table border="0" align="center" cellpadding="0" cellspacing="0" colspan="0" cellspan="0">
				<tr>
					<td>
						<s:fielderror theme="simple" />
						<s:actionerror theme="simple" />
					</td>
				</tr>
			</table>
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
				<!-- Schedule selection action table -->
				<table id="scheduleActionsTable" border="0" cellpadding="0" cellspacing="0">
					<tr>
				    	<td>[<a href="#" onclick="changeAction(1);">Set time</a>]</td>
				        <td>&nbsp;</td>
				    	<td>[<a href="#" onclick="changeAction(3);">Set break</a>]</td>
				        <td>&nbsp;</td>
				    	<td>[<a href="#" onclick="changeAction(5);">Delete</a>]</td>
				        <td>&nbsp;</td>
						<td id="actionMessage" class="scheduleActionMessage"></td>        
				    </tr>
				</table>				
				<!-- Schedule selection action table -->
			</td>
		</tr>

		<tr>
			<td align="center">
			<!-- Schedule selection table -->
				<table class="scheduleMainTable" id="scheduleMainTable" cellpadding="0" cellspacing="0">
				    <!-- Header -->
				    <tr class="scheduleMainTableHeader">
						<td class="scheduleCellHeader"><s:text name="schedule.addshift.position"/></td>    
						<td class="scheduleCellHeader"><s:text name="schedule.addshift.employee"/></td>    
						<td class="scheduleCellHeader"><s:text name="schedule.addshift.in_hour"/></td>    
						<td class="scheduleCellHeader"><s:text name="schedule.addshift.out_hour"/></td>
						<td class="scheduleCellHeader"><s:text name="schedule.addshift.total_hours"/></td>
						<s:iterator id="hourLabel" value="scheduleLabelHours" status="#itHourLabel">     
							<td class="scheduleCellHeader" colspan="<s:property value="colspan"/>"><s:text name='schedule.addshift.hour.dateformat'><s:param value='hour'/></s:text></td>
						</s:iterator>         
				    </tr>
				    <!-- Header -->
				    
				    <!-- Employees -->
				    <tr class="scheduleMainTableEmployeeRow">
				    	<td class="scheduleNameCell"><select></select></td>
						<td class="scheduleNameCell">Add</td>    
						<td class="scheduleValueCell" id="inHour_0">XX</td>    
						<td class="scheduleValueCell" id="outHour_0">XX</td>
						<td class="scheduleValueCell" id="totalHours_0">XX</td>            
						<s:iterator id="hour" value="scheduleIndividualHours" status="itHour">
							<s:if test="#itHour.first">
								<s:iterator id="startToIgnore" value="scheduleIndividualStartHoursToIgnore">
									<td class="scheduleUnavailable"><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
								</s:iterator>
							</s:if>  
							<td id='cell_<s:property value="#itHour.index"/>_<s:property value="#itHour.index"/>' onclick="scheduleClick(this, <s:property value="#itHour.index"/>,<s:property value="#itHour.index"/>, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden" id="schedule_<s:property value="#itHour.index"/>_<s:property value="#itHour.index"/>" name="schedule_<s:property value="#itHour.index"/>_<s:property value="#itHour.index"/>" value=""/></td>            
							<s:if test="#itHour.last">
								<s:iterator id="startToIgnore" value="scheduleIndividualEndHoursToIgnore">
									<td class="scheduleUnavailable"><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
								</s:iterator>
							</s:if> 
						</s:iterator>
				    </tr>

				    <!-- Employees -->
				    
				</table>			
			<!-- Schedule selection table -->
			</td>
		</tr>		
		<tr>
			<td width="100%" align="right">
				<table border="0" cellpadding="1" cellspacing="5" colspan="0" cellspan="0">
					<tr>
						<td><s:submit id="saveButton" key="save.button" theme="simple" cssClass="button"/></td>
						<td><s:submit id="cancelButton" key="cancel.button" action="daily_edit" theme="simple" cssClass="button"/></td>		                    
      				</tr>
     			</table>                    
    		</td>
		</tr>
	</table>
</s:form>
