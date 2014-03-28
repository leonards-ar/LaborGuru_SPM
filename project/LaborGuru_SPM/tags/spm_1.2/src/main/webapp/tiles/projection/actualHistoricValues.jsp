<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<br/>
<s:form id="daily_form" name="daily_form" action="actualValues_save" theme="simple">
<s:hidden id="selectedDate" name="selectedDate"/>
<s:hidden id="selectedWeekDay" name="selectedWeekDay"/>
	      <table border="0" cellspacing="0" align="center">
		      <tr>
			      <td id="titleBar">
			      	<s:text name="projection.submenu.actualvalues" />
			      </td>
        	  </tr>
              
              <tr>
              	<td class="errorMessage" align="center">
	              	<table border="0" align="center" cellpadding="0" cellspacing="0">
              			<tr>
              				<td>
				              	<s:actionerror theme="simple"/>
				              	<s:fielderror theme="simple"/>
			              	</td>
            		  	</tr>
              		</table>
              	</td>
              </tr>
              <s:if test="%{!projectionError}">
              <tr>
              	<td align="center">
                  	<!-- Start week table -->
                  	<table align="center" id="calendarTable" width="100%" border="0" cellpadding="0" cellspacing="0">
                  		<tr>
                  			<td align="center">
                  				<table align="center" border="0" cellpadding="0" cellspacing="0">
                  					<tr>
                  						<s:iterator id="prevDate" value="weekDaySelector.previousStartingWeekDays" status="itPrevDate">
			                  				<td class="calendarTableColumn" width="10%" nowrap="nowrap">
			                  					<a href="<s:url value="#" includeParams="none"/>" onclick="daily_form.action='actualValues_changeWeek.action'; daily_form.selectedDate.value='<s:text name='projection.weekdayselector.input.dateformat'><s:param value='prevDate'/></s:text>'; daily_form.submit();" class="calendarUnselectedWeekLink">
			                  						<s:text name='projection.weekdayselector.availableweek.dateformat'><s:param value='prevDate'/></s:text>
			                  					</a>
			                  				</td>
			                  			
				                  			<s:if test="!#itPrevDate.last">
				                  			<td class="calendarTableColumn" nowrap="nowrap"><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/>|<img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
	                  						</s:if>
                  						</s:iterator>
			                  			<td class="calendarTableColumn"><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
			                  			<td class="calendarTableColumn" nowrap="nowrap">
			                  				<table border="0" cellpadding="0" cellspacing="0">
			                  					<tr>
			                  						<td><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
			                  						<td><a href="<s:url value="#" includeParams="none"/>" onclick="daily_form.action='actualValues_changeWeek.action'; daily_form.selectedDate.value='<s:text name='projection.weekdayselector.input.dateformat'><s:param value='weekDaySelector.previousStartingWeekDay'/></s:text>'; daily_form.submit();"><img src="<s:url value="/images/cal_prev.png" includeParams="none"/>" border="0"/></a></td>
			                  						<td><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
			                  						<td class="calendarSelectedWeekText" nowrap="nowrap"><s:text name='projection.weekdayselector.selectedweek.dateformat'><s:param value='weekDaySelector.startingWeekDay'/></s:text></td>
			                  						<td><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
			                  						<td><a href="<s:url value="#" includeParams="none"/>" onclick="daily_form.action='actualValues_changeWeek.action'; daily_form.selectedDate.value='<s:text name='projection.weekdayselector.input.dateformat'><s:param value='weekDaySelector.nextStartingWeekDay'/></s:text>'; daily_form.submit();"><img src="<s:url value="/images/cal_next.png" includeParams="none"/>" border="0"/></a></td>
			                  						<td><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
			                  					</tr>
			                  				</table>
			                  			</td>
			                  			<td class="calendarTableColumn"><img src="<s:url value="/images/transp2x1.gif" includeParams="none"/>"/></td>
                  						<s:iterator id="nextDate" value="weekDaySelector.nextStartingWeekDays" status="itNextDate">
			                  				<td class="calendarTableColumn" width="10%" nowrap="nowrap">
			                  					<a href="<s:url value="#" includeParams="none"/>" onclick="daily_form.action='actualValues_changeWeek.action'; daily_form.selectedDate.value='<s:text name='projection.weekdayselector.input.dateformat'><s:param value='nextDate'/></s:text>'; daily_form.submit();" class="calendarUnselectedWeekLink">
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
             		<table id="editFormTable" border="0" cellpadding="6" cellspacing="0">
		              	<tr class="editFormOddRow">
                    		<td width="100%" align="center">
                    			<!-- Daily Projection -->
								<table border="0" cellpadding="3" cellspacing="1" align="center">
									<tr class="editorTableHeader">
										<s:hidden name="variableNames[0]"/>
										<td><s:property value="getVariableNames().get(0)"/></td>
										<!-- Iterate week days -->
										<s:iterator id="weekDay" value="weekDaySelector.weekDays">
											<td><s:text name='projection.daily.weekday.dateformat'><s:param value='weekDay'/></s:text></td>
										</s:iterator>
										<!-- End Iterate week days -->
										<td><s:text name="projection.daily.weektotal.label" /></td>
									</tr>
									<tr>
										<td class="editorTableFirstColumn" style="font-weight: bold;"><s:text name="projection.actual.label"/></td>
										<s:iterator id="displayMain" value="mainValueBeforeUpdate" status="itProjection">
											<s:hidden name="mainValueBeforeUpdate[%{#itProjection.index}]" value="%{displayMain}" />
											<td class="editorTableOddRow"><s:text name="currency"><s:param value="displayMain"/></s:text></td>
										</s:iterator>
										<s:hidden name="totalMainBeforeUpdate"/>
										<td class="editorTableOddRow"><b><s:text name="currency"><s:param value="totalMainBeforeUpdate"/></s:text></b></td>
									</tr>
									<tr>
										<td class="editorTableFirstColumn"><s:text name="projection.actual.modify.label"/></td>
										<s:iterator id="dailyActual" value="dailyActuals" status="itProjection">
											<s:hidden name="dailyActuals[%{#itProjection.index}].date" theme="simple"/>
											<td class="editorTableEvenRow">
												<s:if test="%{#dailyActual.editable}">
													<s:textfield id="dailyMainValue[%{#itProjection.index}]" name="dailyActuals[%{#itProjection.index}].mainValue" size="7" maxlength="15" theme="simple" 
													cssStyle="text-align: center;" onchange="updateProjectionRowValue(this.id, 'dailyMainValue','totalMainValue')">
														<s:param name="value"><s:if test="mainValue != null"><s:text name="currency"><s:param value="mainValue"/></s:text></s:if></s:param>
													</s:textfield>
												</s:if>
												<s:else>
													<s:text name="currency"><s:param value="mainValue"/></s:text>
													<s:hidden id="dailyMainValue[%{#itProjection.index}]" name="dailyActuals[%{#itProjection.index}].mainValue" value="%{mainValue}" theme="simple"/>
												</s:else>
											</td>										
										</s:iterator>
										<!--  We need these hidden fields for totals and variable names, so input values are retained for rendering the page in the case of a validation error 
											By convention the total inputs id must finish in 'Input'. They are updated by the js that calculates and updates the row-->
										<s:hidden id="totalMainValueInput" name="totalMainValue"/>
										<td class="editorTableEvenRow" id="totalMainValue"><b><s:text name="currency"><s:param value="totalMainValue"/></s:text></b></td>
									</tr>
                    			<!-- End Actual Main Value -->
									<tr>
										<td colspan="9" class="editorTableOddRow" style="font-weight: bold;"><br/></td>
									</tr>
                    			<!-- Actual Hours -->
									<tr>
										<td class="editorTableFirstColumn"><s:text name="projection.actual.hours.label" /></td>
										<s:iterator id="originalHours" value="actualHoursBeforeUpdate" status="itProjection">
											<s:hidden name="actualHoursBeforeUpdate[%{#itProjection.index}]" value="%{originalHours}" />
											<td class="editorTableOddRow"><s:text name="hours"><s:param value="originalHours"/></s:text></td>
										</s:iterator>
										<s:hidden name="totalHoursBeforeUpdate"/>
										<td class="editorTableOddRow"><b><s:text name="hours"><s:param value="totalHoursBeforeUpdate"/></s:text></b></td>
									</tr>
									<tr>
										<td class="editorTableFirstColumn"><s:text name="projection.actual.modify.label"/></td>
										<s:iterator id="dailyActual" value="dailyActuals" status="itProjection">
											<td class="editorTableEvenRow">
												<s:if test="%{#dailyActual.editable}">
													<s:textfield id="dailyHours[%{#itProjection.index}]" name="dailyActuals[%{#itProjection.index}].hours" size="7" maxlength="15" theme="simple" 
													cssStyle="text-align: center;" onchange="updateDoubleRowValue(this.id, 'dailyHours','totalDailyHours')">
														<s:param name="value"><s:text name="hours"><s:param value="hours"/></s:text></s:param>
													</s:textfield>
												</s:if>
												<s:else>
													<s:text name="hours"><s:param value="hours"/></s:text>
													<s:hidden id="dailyHours[%{#itProjection.index}]" name="dailyActuals[%{#itProjection.index}].hours" value="%{hours}" theme="simple"/>
												</s:else>
											</td>										
										</s:iterator>
										<!--  We need these hidden fields for totals and variable names, so input values are retained for rendering the page in the case of a validation error 
											By convention the total inputs id must finish in 'Input'. They are updated by the js that calculates and updates the row-->
										<s:hidden id="totalDailyHoursInput" name="totalActualHours"/>
										<td class="editorTableEvenRow" id="totalDailyHours"><b><s:text name="hours"><s:param value="totalActualHours"/></s:text></b></td>
									</tr>
                    			<!-- End actual Hours -->
								</table>                    			
                    		</td>
		                </tr>
						<s:if test="%{getAllowToSaveWeek()}">
		 		       	<tr class="editFormOddRow">
		                    <td width="100%" align="right">
			                    <table border="0" cellpadding="1" cellspacing="5">
				                    <tr>
				                		<td><s:submit id="saveButton" onclick="return showWaitSplash();" key="save.button" action="actualValues_save" theme="simple" cssClass="button"/></td>
				                    	<td><s:reset id="resetButton" key="reset.button" onclick="updateAllPageTotalsReset()" theme="simple" cssClass="button"/></td>
				                    	<td><s:submit id="cancelButton" key="cancel.button" action="actualValues_edit" theme="simple" cssClass="button"/></td>		                    
				                    </tr>
			                    </table>                    
		                    </td>
		                </tr>
						</s:if>
              		</table>
	              </td>
              </tr>
			</s:if>
          </table>
</s:form>
<script language="javascript" type="text/javascript">

	function updateAllPageTotalsReset(){
	    document.daily_form.reset();
		updateTotalRow('dailyMainValue', 'totalMainValue','i');
		updateTotalRow('dailyHours', 'totalDailyHours','d');
	}
</script>