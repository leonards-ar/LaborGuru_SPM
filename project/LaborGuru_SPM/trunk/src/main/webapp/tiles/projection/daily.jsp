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
			                  				<table border="0" cellpadding="0" cellspacing="0">
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
             		<table id="editFormTable" border="0" cellpadding="6" cellspacing="0">
		              	<tr class="editFormOddRow">
                    		<td width="100%" align="center" colspan="2">
                    			<!-- Daily Projection -->
								<table border="0" cellpadding="3" cellspacing="1" align="center">
									<tr class="editorTableHeader">
										<td><s:property value="getVariableNames().get(0)"/></td>
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
										<td class="editorTableOddRow" style="font-weight: bold;"><s:text name="projection.daily.projection.label"/></td>
										<!-- Iterate week days -->
										<s:iterator value="dailyProjections" status="itProjection">
											<s:hidden name="dailyProjections[%{#itProjection.index}].calculatedProjection" value="%{calculatedProjection}"/>
											<td class="editorTableOddRow"><s:text name="currency"><s:param value="calculatedProjection"/></s:text></td>
										</s:iterator>
										<!-- End Iterate week days -->
										<td class="editorTableOddRow"><b><s:text name="currency"><s:param value="totalProjected"/></s:text></b></td>
									</tr>
									<tr>
										<td class="editorTableFirstColumn"><s:text name="projection.daily.adjusted.label" /></td>
										<s:iterator id="dailyProjection" value="dailyProjections" status="itProjection">
											<s:hidden name="dailyProjections[%{#itProjection.index}].projectionDate" theme="simple"/>
											<td class="editorTableEvenRow">
												<s:if test="%{#dailyProjection.editable}">
													<s:textfield id="adjustedProjection[%{#itProjection.index}]" name="dailyProjections[%{#itProjection.index}].adjustedProjection" size="7" maxlength="15" theme="simple" 
													cssStyle="text-align: center;" onchange="updateProjectionRowValue(this.id, 'adjustedProjection','totalAdjusted')">
														<s:param name="value"><s:if test="adjustedProjection != null"><s:text name="currency"><s:param value="adjustedProjection"/></s:text></s:if></s:param>
													</s:textfield>
												</s:if>
												<s:else>
													<s:text name="currency"><s:param value="adjustedProjection"/></s:text>
													<s:hidden id="adjustedProjection[%{#itProjection.index}]" name="dailyProjections[%{#itProjection.index}].adjustedProjection" value="%{adjustedProjection}" theme="simple"/>
												</s:else>
												</td>										
										</s:iterator>
										<td class="editorTableEvenRowTotal" id="totalAdjusted"><s:text name="currency"><s:param value="totalAdjusted"/></s:text></td>
									</tr>
                    			<!-- End Daily Projection -->
                    			
                    			<!-- Additional Variables -->
                    				<s:if test="secondaryVariablesConfigured">
									<tr>
										<td colspan="9" class="editorTableOddRow" style="font-weight: bold;"><s:text name="projection.daily.additionalvariables.label" /></td>
									</tr>
									</s:if>
									
									<s:if test="%{isSecondaryVariablesConfigured(1)}">								 
									<tr>
										<td class="editorTableFirstColumn"><s:property value="getVariableNames().get(1)"/></td>
										<s:iterator id="dailyProjection" value="dailyProjections" status="itProjection">
											<td class="editorTableEvenRow">
												<s:if test="%{#dailyProjection.editable}">
													<s:textfield id="projectionVariable2[%{#itProjection.index}]" name="dailyProjections[%{#itProjection.index}].projectionVariable2" size="7" maxlength="15" theme="simple" 
													cssStyle="text-align: center;" onchange="updateProjectionRowValue(this.id, 'projectionVariable2','totalVariable2')">
														<s:param name="value"><s:if test="projectionVariable2 != null"><s:text name="currency"><s:param value="projectionVariable2"/></s:text></s:if></s:param>
													</s:textfield>
												</s:if>
												<s:else>
													<s:if test="projectionVariable2 != null"><s:text name="currency"><s:param value="projectionVariable2"/></s:text></s:if><s:else>&nbsp;</s:else>
													<s:hidden id="projectionVariable2[%{#itProjection.index}]" name="dailyProjections[%{#itProjection.index}].projectionVariable2" value="%{projectionVariable2}" theme="simple"/>
												</s:else>
											</td>										
										</s:iterator>
										<td class="editorTableEvenRowTotal" id="totalVariable2"><s:if test="totalVariable2 != null"><s:text name="currency"><s:param value="totalVariable2"/></s:text></s:if><s:else>&nbsp;</s:else></td>
									</tr>
									</s:if>
									<s:else>
										<s:iterator id="dailyProjection" value="dailyProjections" status="itProjection">
											<s:hidden name="dailyProjections[%{#itProjection.index}].projectionVariable2" value="0"/>
										</s:iterator>
									</s:else>
									
									<s:if test="%{isSecondaryVariablesConfigured(2)}">
									<tr>
										<td class="editorTableFirstColumn"><s:property value="getVariableNames().get(2)"/></td>
										<s:iterator id="dailyProjection" value="dailyProjections" status="itProjection">
											<td class="editorTableEvenRow">
												<s:if test="%{#dailyProjection.editable}">
													<s:textfield id="projectionVariable3[%{#itProjection.index}]" name="dailyProjections[%{#itProjection.index}].projectionVariable3" size="7" maxlength="15" theme="simple" 
													cssStyle="text-align: center;" onchange="updateProjectionRowValue(this.id, 'projectionVariable3','totalVariable3')">
														<s:param name="value"><s:if test="projectionVariable3 != null"><s:text name="currency"><s:param value="projectionVariable3"/></s:text></s:if></s:param>
													</s:textfield>
												</s:if>
												<s:else>
													<s:if test="projectionVariable3 != null"><s:text name="currency"><s:param value="projectionVariable3"/></s:text></s:if><s:else>&nbsp;</s:else>
													<s:hidden id="projectionVariable3[%{#itProjection.index}]" name="dailyProjections[%{#itProjection.index}].projectionVariable3" value="%{projectionVariable3}" theme="simple"/>
												</s:else>
											</td>										
										</s:iterator>
										<td class="editorTableEvenRowTotal" id="totalVariable3"><s:if test="totalVariable3 != null"><s:text name="currency"><s:param value="totalVariable3"/></s:text></s:if><s:else>&nbsp;</s:else></td>
									</tr>
									</s:if>
									<s:else>
										<s:iterator id="dailyProjection" value="dailyProjections" status="itProjection">
											<s:hidden name="dailyProjections[%{#itProjection.index}].projectionVariable3" value="0"/>
										</s:iterator>
									</s:else>

									<s:if test="%{isSecondaryVariablesConfigured(3)}">																		
									<tr>
										<td class="editorTableFirstColumn"><s:property value="getVariableNames().get(3)"/></td>
										<s:iterator id="dailyProjection" value="dailyProjections" status="itProjection">
											<td class="editorTableEvenRow">
												<s:if test="%{#dailyProjection.editable}">
													<s:textfield id="projectionVariable4[%{#itProjection.index}]" name="dailyProjections[%{#itProjection.index}].projectionVariable4" size="7" maxlength="15" theme="simple" 
													cssStyle="text-align: center;" onchange="updateProjectionRowValue(this.id, 'projectionVariable4','totalVariable4')">
														<s:param name="value"><s:if test="projectionVariable4 != null"><s:text name="currency"><s:param value="projectionVariable4"/></s:text></s:if></s:param>
													</s:textfield>
												</s:if>
												<s:else>
													<s:if test="projectionVariable4 != null"><s:text name="currency"><s:param value="projectionVariable4"/></s:text></s:if><s:else>&nbsp;</s:else>
													<s:hidden id="projectionVariable4[%{#itProjection.index}]" name="dailyProjections[%{#itProjection.index}].projectionVariable4" value="%{projectionVariable4}" theme="simple"/>
												</s:else>
											</td>										
										</s:iterator>
										<td class="editorTableEvenRowTotal" id="totalVariable4"><s:if test="totalVariable4 != null"><s:text name="currency"><s:param value="totalVariable4"/></s:text></s:if><s:else>&nbsp;</s:else></td>
									</tr>
									</s:if>
									<s:else>
										<s:iterator id="dailyProjection" value="dailyProjections" status="itProjection">
											<s:hidden name="dailyProjections[%{#itProjection.index}].projectionVariable4" value="0"/>
										</s:iterator>
									</s:else>									
		                    	<!-- End of Additional variables -->
								</table>                    			
                    		</td>
		                </tr>
						<s:if test="%{getAllowToSaveWeek()}">
		 		       	<tr class="editFormOddRow">
							<s:if test="%{getDisplayWeekUsed()}">
							<td width="100%" align="left">
								<table border="0" cellpadding="1" cellspacing="5">
									<tr>
			                    		<td width="15%" align="right" class="form_label" nowrap="nowrap"><s:text name="projection.daily.weeksused.label" /></td>
					                    <td width="85%" align="left" class="value">
			                    			<s:select name="usedWeeks" list="usedWeeksMap" listKey="key" listValue="%{getText(value)}" theme="simple" onchange="daily_form.action='daily_edit.action'; daily_form.submit();" />
					                    </td>
									</tr>
								</table>
							</td>
							</s:if>
		                    <td width="100%" align="right">
			                    <table border="0" cellpadding="1" cellspacing="5">
				                    <tr>
				                		<td><s:submit id="saveButton" onclick="return showWaitSplash();" key="save.button" action="dailySave" theme="simple" cssClass="button"/></td>
				                    	<td><s:reset id="resetButton" key="reset.button" theme="simple" cssClass="button" onclick="updateAllPageTotals()"/></td>
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
<script language="javascript" type="text/javascript">

	function updateAllPageTotals(){
        document.daily_form.reset();
		updateTotalRow('adjustedProjection', 'totalAdjusted');	
		<s:if test="%{isSecondaryVariablesConfigured(1)}">		
			updateTotalRow('projectionVariable2', 'totalVariable2');
		</s:if>
		<s:if test="%{isSecondaryVariablesConfigured(2)}">					
			updateTotalRow('projectionVariable3', 'totalVariable3');
		</s:if>
		<s:if test="%{isSecondaryVariablesConfigured(3)}">					
			updateTotalRow('projectionVariable4', 'totalVariable4');	
		</s:if>
	}

	function updateProjectionRowValue(objectId, variableName, totalId){
		truncateDailyProjectionValue(objectId);
		updateTotalRow(variableName, totalId);
	}

	function truncateDailyProjectionValue(objectId){
		var truncatedValue = toInt(getObjectByIDValue(objectId,0));

		if(isNaN(truncatedValue)) {
			truncatedValue = 0;
		}

		setObjectByIDValue(objectId, truncatedValue);				
	}
		
	function updateTotalRow(variableName, totalId){
		var totalValue = 0;
		for (projectionIndexRow=0; projectionIndexRow < 7; projectionIndexRow++){
			var dailyValue = toInt(getObjectByIDValue(variableName+'['+projectionIndexRow+']',0));

			if(isNaN(dailyValue)) {
				dailyValue = 0;
			}
			
			totalValue += dailyValue;		
		}
		setObjectByIDValueAndClass(totalId, totalValue, 'editorTableEvenRowTotal');
	}
</script>