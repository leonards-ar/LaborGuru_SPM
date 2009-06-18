<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<br/>
<s:form id="daily_form" name="daily_form" action="dailySave" theme="simple">
<s:hidden id="selectedDate" name="selectedDate"/>
<s:hidden id="selectedWeekDay" name="selectedWeekDay"/>
	      <table border="0" cellspacing="0" align="center">
		      <tr>
			      <td id="titleBar">
			      	<s:text name="projection.daily.title" />
			      </td>
        	  </tr>
              
              <tr>
              	<td class="errorMessage" align="center">
	              	<table border="0" align="center" cellpadding="0" cellspacing="0" colspan="0" cellspan="0">
              			<tr>
              				<td>
				              	<s:actionerror theme="simple"/>
				              	<s:fielderror theme="simple"/>
			              	</td>
            		  	</tr>
              		</table>
              	</td>
              </tr>

              <tr>
              	<td align="center">
                  	<!-- Start week table -->
                  	<table align="center" id="calendarTable" width="100%" border="0" cellpadding="0" cellspacing="0" colspan="0" cellspan="0">
                  		<tr>
                  			<td align="center">
                  				<table align="center" border="0" cellpadding="0" cellspacing="0" colspan="0" cellspan="0">
                  					<tr>
                  						<s:iterator id="prevDate" value="weekDaySelector.previousStartingWeekDays" status="itPrevDate">
			                  				<td class="calendarTableColumn" width="10%" nowrap="nowrap">
			                  					<a href="<s:url value="#" includeParams="none"/>" onclick="daily_form.action='daily_changeWeek.action'; daily_form.selectedDate.value='<s:text name='projection.weekdayselector.input.dateformat'><s:param value='prevDate'/></s:text>'; daily_form.submit();" class="calendarUnselectedWeekLink">
			                  						<s:text name='projection.weekdayselector.availableweek.dateformat'><s:param value='prevDate'/></s:text>
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
			                  						<td><a href="<s:url value="#" includeParams="none"/>" onclick="daily_form.action='daily_changeWeek.action'; daily_form.selectedDate.value='<s:text name='projection.weekdayselector.input.dateformat'><s:param value='weekDaySelector.previousStartingWeekDay'/></s:text>'; daily_form.submit();"><img src="<s:url value="/images/cal_prev.png" includeParams="none"/>" border="0"/></a></td>
			                  						<td><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
			                  						<td class="calendarSelectedWeekText" nowrap="nowrap"><s:text name='projection.weekdayselector.selectedweek.dateformat'><s:param value='weekDaySelector.startingWeekDay'/></s:text></td>
			                  						<td><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
			                  						<td><a href="<s:url value="#" includeParams="none"/>" onclick="daily_form.action='daily_changeWeek.action'; daily_form.selectedDate.value='<s:text name='projection.weekdayselector.input.dateformat'><s:param value='weekDaySelector.nextStartingWeekDay'/></s:text>'; daily_form.submit();"><img src="<s:url value="/images/cal_next.png" includeParams="none"/>" border="0"/></a></td>
			                  						<td><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
			                  					</tr>
			                  				</table>
			                  			</td>
			                  			<td class="calendarTableColumn"><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
                  						<s:iterator id="nextDate" value="weekDaySelector.nextStartingWeekDays" status="itNextDate">
			                  				<td class="calendarTableColumn" width="10%" nowrap="nowrap">
			                  					<a href="<s:url value="#" includeParams="none"/>" onclick="daily_form.action='daily_changeWeek.action'; daily_form.selectedDate.value='<s:text name='projection.weekdayselector.input.dateformat'><s:param value='nextDate'/></s:text>'; daily_form.submit();" class="calendarUnselectedWeekLink">
			                  						<s:text name='projection.weekdayselector.availableweek.dateformat'><s:param value='nextDate'/></s:text>
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
              	<td>              
             		<table id="editFormTable" border="0" cellpadding="6" cellspacing="0" colspan="0" cellspan="0">
		              	<tr class="editFormOddRow">
                    		<td width="100%" align="center" colspan="2">
                    			<!-- Daily Projection -->
								<table border="0" cellpadding="3" cellspacing="1" colspan="0" cellspan="0" align="center">
									<tr class="editorTableHeader">
										<td>&nbsp;</td>
										<!-- Iterate week days -->
										<s:iterator id="weekDay" value="weekDaySelector.weekDays">
											<td>
												<a href="<s:url value="#" includeParams="none"/>" onclick="daily_form.action='halfhour_edit.action'; daily_form.selectedWeekDay.value='<s:text name='projection.weekdayselector.input.dateformat'><s:param value='weekDay'/></s:text>'; daily_form.submit();" class="calendarUnselectedWeekLink">
			                  						<s:text name='projection.daily.weekday.dateformat'><s:param value='weekDay'/></s:text>
			                  					</a>
											</td>
										</s:iterator>
										<!-- End Iterate week days -->
										<td><s:text name="projection.daily.weektotal.label" /></td>
									</tr>
									<tr>
										<td class="editorTableFirstColumn"><s:text name="projection.daily.projection.label"/></td>
										<!-- Iterate week days -->
										<s:iterator value="dailyProjections" status="itProjection">
											<s:hidden name="dailyProjections[%{#itProjection.index}].calculatedProjection" value="%{calculatedProjection}"/>
											<td class="editorTableOddRow"><s:text name="currency"><s:param value="calculatedProjection"/></s:text></td>
										</s:iterator>
										<!-- End Iterate week days -->
										<s:hidden name="totalProjected" theme="simple"/>
										<td class="editorTableOddRow"><b><s:text name="currency"><s:param value="totalProjected"/></s:text></b></td>
									</tr>
									<tr>
										<td class="editorTableFirstColumn"><s:text name="projection.daily.adjusted.label" /></td>
										<s:iterator id="dailyProjection" value="dailyProjections" status="itProjection">
											<s:hidden name="dailyProjections[%{#itProjection.index}].projectionDate" theme="simple"/>
											<td class="editorTableEvenRow">
												<s:if test="%{#dailyProjection.editable}">
													<s:textfield name="dailyProjections[%{#itProjection.index}].adjustedProjection" size="7" maxlength="15" theme="simple" cssStyle="text-align: center;">
														<s:param name="value"><s:text name="currency"><s:param value="adjustedProjection"/></s:text></s:param>
													</s:textfield>
												</s:if>
												<s:else>
													<s:text name="currency"><s:param value="adjustedProjection"/></s:text>
													<s:hidden name="dailyProjections[%{#itProjection.index}].adjustedProjection" value="%{adjustedProjection}" theme="simple"/>
												</s:else>
												</td>										
										</s:iterator>
										<s:hidden name="totalAdjusted" theme="simple"/>
										<td class="editorTableEvenRow"><b><s:text name="currency"><s:param value="totalAdjusted"/></s:text></b></td>
									</tr>									
								</table>                    			
                    			<!-- End Daily Projection -->
                    		</td>
		                </tr>
						<s:if test="%{getAllowToSaveWeek()}">
		 		       	<tr class="editFormOddRow">
							<td width="100%" align="left">
								<table border="0" cellpadding="1" cellspacing="5" colspan="0" cellspan="0">
									<tr>
			                    		<td width="15%" align="right" class="form_label" nowrap="nowrap"><s:text name="projection.daily.weeksused.label" /></td>
					                    <td width="85%" align="left" class="value">
			                    			<s:select name="usedWeeks" list="usedWeeksMap" listKey="key" listValue="%{getText(value)}" theme="simple" onchange="daily_form.action='daily_edit.action'; daily_form.submit();" />
					                    </td>
									</tr>
								</table>
							</td>
		                    <td width="100%" align="right">
			                    <table border="0" cellpadding="1" cellspacing="5" colspan="0" cellspan="0">
				                    <tr>
				                		<td><s:submit id="saveButton" onclick="return showWaitSplash();" key="save.button" action="dailySave" theme="simple" cssClass="button"/></td>
				                    	<td><s:reset id="resetButton" key="reset.button" theme="simple" cssClass="button"/></td>
				                    	<td><s:submit id="cancelButton" key="cancel.button" action="daily_edit" theme="simple" cssClass="button"/></td>		                    
				                    </tr>
			                    </table>                    
		                    </td>
		                </tr>
						</s:if>
              		</table>
	              </td>
              </tr>
          </table>
</s:form>