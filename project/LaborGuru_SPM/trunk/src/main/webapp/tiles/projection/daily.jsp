<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<br/>
<s:form id="daily_form" name="daily_form" action="daily_save" theme="simple">
<s:hidden id="selectedDate" name="selectedDate"/>
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
				              	<s:fielderror theme="simple"/>
				              	<s:actionerror theme="simple"/>
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
		              	<tr class="editFormEvenRow">
		                    <td width="20%" align="right" class="form_label" nowrap="nowrap"><s:text name="projection.daily.weeksused.label" /></td>
		                    <td width="80%" align="left" class="value">
		                    	<select>
		                    		<option value="1">Last week</option>
		                    		<option value="2">2 weeks</option>
		                    		<option value="3">3 weeks</option>
		                    		<option value="4">4 weeks</option>
		                    	</select>
		                    </td>
		                </tr>
		              	<tr class="editFormOddRow">
                    		<td width="100%" align="center" colspan="2">
                    			<!-- Daily Projection -->
								<table border="0" cellpadding="3" cellspacing="1" colspan="0" cellspan="0" align="center">
									<tr class="editorTableHeader">
										<td><s:text name="projection.daily.sales.label" /></td>
										<!-- Iterate week days -->
										<s:iterator id="weekDay" value="weekDaySelector.weekDays">
										<td><s:text name='projection.daily.weekday.dateformat'><s:param value='weekDay'/></s:text></td>
										</s:iterator>
										<!-- End Iterate week days -->
										<td><s:text name="projection.daily.weektotal.label" /></td>
									</tr>
									<tr>
										<td class="editorTableFirstColumn"><s:text name="projection.daily.projection.label" /></td>
										<td class="editorTableOddRow">$ 2,400</td>
										<td class="editorTableOddRow">$ 3,400</td>
										<td class="editorTableOddRow">$ 2,100</td>
										<td class="editorTableOddRow">$ 6,100</td>
										<td class="editorTableOddRow">$ 4,250</td>
										<td class="editorTableOddRow">$ 8,800</td>
										<td class="editorTableOddRow">$ 1,330</td>
										<td class="editorTableOddRow"><b>$ 28,380</b></td>
									</tr>
									<tr>
										<td class="editorTableFirstColumn"><s:text name="projection.daily.adjusted.label" /></td>
										<td class="editorTableEvenRow"><input type="text" value="$ 2,400" size="10"/></td>
										<td class="editorTableEvenRow"><input type="text" value="$ 3,400" size="10"/></td>
										<td class="editorTableEvenRow"><input type="text" value="$ 2,100" size="10"/></td>
										<td class="editorTableEvenRow"><input type="text" value="$ 6,100" size="10"/></td>
										<td class="editorTableEvenRow"><input type="text" value="$ 4,250" size="10"/></td>
										<td class="editorTableEvenRow"><input type="text" value="$ 8,800" size="10"/></td>
										<td class="editorTableEvenRow"><input type="text" value="$ 1,330" size="10"/></td>
										<td class="editorTableEvenRow">&nbsp;</td>
									</tr>									
								</table>                    			
                    			<!-- End Daily Projection -->
                    		</td>
		                </tr>
 
		 		       	<tr class="editFormOddRow">
		                    <td width="100%" align="right" colspan="2">
			                    <table border="0" cellpadding="1" cellspacing="5" colspan="0" cellspan="0">
				                    <tr>
				                		<td><s:submit id="saveButton" key="save.button" theme="simple" cssClass="button"/></td>
				                    	<td><s:reset id="resetButton" key="reset.button" theme="simple" cssClass="button"/></td>
				                    	<td><s:submit id="cancelButton" key="cancel.button" action="daily_edit" theme="simple" cssClass="button"/></td>		                    
				                    </tr>
			                    </table>                    
		                    </td>
		                </tr>
              		</table>
	              </td>
              </tr>
          </table>
</s:form>