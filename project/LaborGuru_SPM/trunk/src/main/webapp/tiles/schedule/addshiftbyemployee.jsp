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
						<s:iterator>     
						<td class="scheduleCellHeader" colspan="4">4</td>
						</s:iterator>         
						<td class="scheduleCellHeader" colspan="4">5</td>            
						<td class="scheduleCellHeader" colspan="4">6</td>            
						<td class="scheduleCellHeader" colspan="4">7</td>            
						<td class="scheduleCellHeader" colspan="4">8</td>            
				    </tr>
				    <!-- Header -->
				    
				    <!-- Employees -->
				    <tr class="scheduleMainTableEmployeeRow">
						<td class="scheduleNameCell">Ignacio Goris</td>    
						<td class="scheduleValueCell" id="inHour_0">4</td>    
						<td class="scheduleValueCell" id="outHour_0">8</td>
						<td class="scheduleValueCell" id="totalHours_0">4</td>            
				
						<td id='cell_0_0' onclick="scheduleClick(this, 0,0, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden" id="schedule_0_0" name="schedule_0_0" value=""/></td>            
						<td id='cell_0_1' onclick="scheduleClick(this, 0,1, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden" id="schedule_0_1" name="schedule_0_1" value=""/></td>            
						<td id='cell_0_2' onclick="scheduleClick(this, 0,2, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden" id="schedule_0_2" name="schedule_0_2" value=""/></td>            
						<td id='cell_0_3' onclick="scheduleClick(this, 0,3, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden" id="schedule_0_3" name="schedule_0_3" value=""/></td>            
				
						<td id='cell_0_4' onclick="scheduleClick(this, 0,4, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden" id="schedule_0_4" name="schedule_0_4" value=""/></td>            
						<td id='cell_0_5' onclick="scheduleClick(this, 0,5, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden" id="schedule_0_5" name="schedule_0_5" value=""/></td>            
						<td id='cell_0_6' onclick="scheduleClick(this, 0,6, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden" id="schedule_0_6" name="schedule_0_6" value=""/></td>            
						<td id='cell_0_7' onclick="scheduleClick(this, 0,7, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden" id="schedule_0_7" name="schedule_0_7" value=""/></td>            
				
						<td id='cell_0_8' onclick="scheduleClick(this, 0,8, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_0_8" value=""/></td>            
						<td id='cell_0_9' onclick="scheduleClick(this, 0,9, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_0_9" value=""/></td>            
						<td id='cell_0_10' onclick="scheduleClick(this, 0,10, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_0_10" value=""/></td>            
						<td id='cell_0_11' onclick="scheduleClick(this, 0,11, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_0_11" value=""/></td>            
				
						<td id='cell_0_12' onclick="scheduleClick(this, 0,12, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_0_12" value=""/></td>            
						<td id='cell_0_13' onclick="scheduleClick(this, 0,13, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_0_13" value=""/></td>            
						<td id='cell_0_14' onclick="scheduleClick(this, 0,14, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_0_14" value=""/></td>            
						<td id='cell_0_15' onclick="scheduleClick(this, 0,15, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_0_15" value=""/></td>            
				
						<td id='cell_0_16' onclick="scheduleClick(this, 0,16, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_0_16" value=""/></td>            
						<td id='cell_0_17' onclick="scheduleClick(this, 0,17, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_0_17" value=""/></td>            
						<td id='cell_0_18' onclick="scheduleClick(this, 0,18, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_0_18" value=""/></td>            
						<td id='cell_0_19' onclick="scheduleClick(this, 0,19, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_0_19" value=""/></td>            
				    </tr>
				    
				    <tr class="scheduleMainTableEmployeeRow">
						<td class="scheduleNameCell">Federico Barrera Oro</td>    
						<td class="scheduleValueCell" id="inHour_1">4</td>    
						<td class="scheduleValueCell" id="outHour_1">8</td>
						<td class="scheduleValueCell" id="totalHours_1">4</td>             
				
						<td id='cell_1_0' onclick="scheduleClick(this, 1,0, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_1_0" value=""/></td>            
						<td id='cell_1_1' onclick="scheduleClick(this, 1,1, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_1_1" value=""/></td>            
						<td id='cell_1_2' onclick="scheduleClick(this, 1,2, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_1_2" value=""/></td>            
						<td id='cell_1_3' onclick="scheduleClick(this, 1,3, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_1_3" value=""/></td>            
				
						<td id='cell_1_4' onclick="scheduleClick(this, 1,4, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_1_4" value=""/></td>            
						<td id='cell_1_5' onclick="scheduleClick(this, 1,5, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_1_5" value=""/></td>            
						<td id='cell_1_6' onclick="scheduleClick(this, 1,6, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_1_6" value=""/></td>            
						<td id='cell_1_7' onclick="scheduleClick(this, 1,7, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_1_7" value=""/></td>            
				
						<td id='cell_1_8' onclick="scheduleClick(this, 1,8, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_1_8" value=""/></td>            
						<td id='cell_1_9' onclick="scheduleClick(this, 1,9, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_1_9" value=""/></td>            
						<td id='cell_1_10' onclick="scheduleClick(this, 1,10, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_1_10" value=""/></td>            
						<td id='cell_1_11' onclick="scheduleClick(this, 1,11, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_1_11" value=""/></td>            
				
						<td id='cell_1_12' onclick="scheduleClick(this, 1,12, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_1_12" value=""/></td>            
						<td id='cell_1_13' onclick="scheduleClick(this, 1,13, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_1_13" value=""/></td>            
						<td id='cell_1_14' onclick="scheduleClick(this, 1,14, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_1_14" value=""/></td>            
						<td id='cell_1_15' onclick="scheduleClick(this, 1,15, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_1_15" value=""/></td>            
				
						<td id='cell_1_16' onclick="scheduleClick(this, 1,16, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_1_16" value=""/></td>            
						<td id='cell_1_17' onclick="scheduleClick(this, 1,17, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_1_17" value=""/></td>            
						<td id='cell_1_18' onclick="scheduleClick(this, 1,18, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_1_18" value=""/></td>            
						<td id='cell_1_19' onclick="scheduleClick(this, 1,19, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_1_19" value=""/></td>            
				    </tr>
				    
				    <tr class="scheduleMainTableEmployeeRow">
						<td class="scheduleNameCell">Cristian Nuñez Rebolledo</td>    
						<td class="scheduleValueCell" id="inHour_2">4</td>    
						<td class="scheduleValueCell" id="outHour_2">8</td>
						<td class="scheduleValueCell" id="totalHours_2">4</td>             
				
						<td id='cell_2_0' onclick="scheduleClick(this, 2,0, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_2_0" value=""/></td>            
						<td id='cell_2_1' onclick="scheduleClick(this, 2,1, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_2_1" value=""/></td>            
						<td id='cell_2_2' onclick="scheduleClick(this, 2,2, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_2_2" value=""/></td>            
						<td id='cell_2_3' onclick="scheduleClick(this, 2,3, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_2_3" value=""/></td>            
				
						<td id='cell_2_4' onclick="scheduleClick(this, 2,4, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_2_4" value=""/></td>            
						<td id='cell_2_5' onclick="scheduleClick(this, 2,5, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_2_5" value=""/></td>            
						<td id='cell_2_6' onclick="scheduleClick(this, 2,6, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_2_6" value=""/></td>            
						<td id='cell_2_7' onclick="scheduleClick(this, 2,7, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_2_7" value=""/></td>            
				
						<td id='cell_2_8' onclick="scheduleClick(this, 2,8, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_2_8" value=""/></td>            
						<td id='cell_2_9' onclick="scheduleClick(this, 2,9, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_2_9" value=""/></td>            
						<td id='cell_2_10' onclick="scheduleClick(this, 2,10, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_2_10" value=""/></td>            
						<td id='cell_2_11' onclick="scheduleClick(this, 2,11, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_2_11" value=""/></td>            
				
						<td id='cell_2_12' onclick="scheduleClick(this, 2,12, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_2_12" value=""/></td>            
						<td id='cell_2_13' onclick="scheduleClick(this, 2,13, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_2_13" value=""/></td>            
						<td id='cell_2_14' onclick="scheduleClick(this, 2,14, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_2_14" value=""/></td>            
						<td id='cell_2_15' onclick="scheduleClick(this, 2,15, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_2_15" value=""/></td>            
				
						<td id='cell_2_16' onclick="scheduleClick(this, 2,16, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_2_16" value=""/></td>            
						<td id='cell_2_17' onclick="scheduleClick(this, 2,17, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_2_17" value=""/></td>            
						<td id='cell_2_18' onclick="scheduleClick(this, 2,18, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_2_18" value=""/></td>            
						<td id='cell_2_19' onclick="scheduleClick(this, 2,19, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_2_19" value=""/></td>            
				    </tr>    
				    
				    <tr class="scheduleMainTableEmployeeRow">
						<td class="scheduleNameCell">Mariano Capurro</td>    
						<td class="scheduleValueCell" id="inHour_3">4</td>    
						<td class="scheduleValueCell" id="outHour_3">8</td>
						<td class="scheduleValueCell" id="totalHours_3">4</td>           
				
						<td id='cell_3_0' onclick="scheduleClick(this, 3,0, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_3_0" value=""/></td>            
						<td id='cell_3_1' onclick="scheduleClick(this, 3,1, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_3_1" value=""/></td>            
						<td id='cell_3_2' onclick="scheduleClick(this, 3,2, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_3_2" value=""/></td>            
						<td id='cell_3_3' onclick="scheduleClick(this, 3,3, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_3_3" value=""/></td>            
				        
						<td id='cell_3_4' onclick="scheduleClick(this, 3,4, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_3_4" value=""/></td>            
						<td id='cell_3_5' onclick="scheduleClick(this, 3,5, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_3_5" value=""/></td>            
						<td id='cell_3_6' onclick="scheduleClick(this, 3,6, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_3_6" value=""/></td>            
						<td id='cell_3_7' onclick="scheduleClick(this, 3,7, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_3_7" value=""/></td>            
				
						<td id='cell_3_8' onclick="scheduleClick(this, 3,8, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_3_8" value=""/></td>            
						<td id='cell_3_9' onclick="scheduleClick(this, 3,9, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_3_9" value=""/></td>            
						<td id='cell_3_10' onclick="scheduleClick(this, 3,10, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_3_10" value=""/></td>            
						<td id='cell_3_11' onclick="scheduleClick(this, 3,11, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_3_11" value=""/></td>            
				        
						<td id='cell_3_12' onclick="scheduleClick(this, 3,12, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_3_12" value=""/></td>            
						<td id='cell_3_13' onclick="scheduleClick(this, 3,13, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_3_13" value=""/></td>            
						<td id='cell_3_14' onclick="scheduleClick(this, 3,14, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_3_14" value=""/></td>            
						<td id='cell_3_15' onclick="scheduleClick(this, 3,15, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_3_15" value=""/></td>            
				
						<td id='cell_3_16' onclick="scheduleClick(this, 3,16, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_3_16" value=""/></td>            
						<td id='cell_3_17' onclick="scheduleClick(this, 3,17, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_3_17" value=""/></td>            
						<td id='cell_3_18' onclick="scheduleClick(this, 3,18, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_3_18" value=""/></td>            
						<td id='cell_3_19' onclick="scheduleClick(this, 3,19, 'Default Position Name');" onMouseOver="scheduleOnMouseOver(this);" onMouseOut="scheduleOnMouseOut(this);" class="scheduleEmpty">&nbsp;<input type="hidden"  name="schedule_3_19" value=""/></td>            
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
