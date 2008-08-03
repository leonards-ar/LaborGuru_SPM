<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<br/>
<s:form action="employee_save" theme="simple">
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
              	<td>              
             		<table id="editFormTable" border="0" cellpadding="6" cellspacing="0" colspan="0" cellspan="0">
		              	<tr class="editFormOddRow">
		                    <td width="20%" align="right" class="form_label" nowrap><s:text name="projection.daily.week.label" /></td>
		                    <td width="80%" align="left" class="value"><s:datetimepicker displayFormat="MM/dd/yyyy" disabled="true" name="object.field" theme="simple"/></td>
		                </tr>
		              	<tr class="editFormEvenRow">
		                    <td width="20%" align="right" class="form_label" nowrap><s:text name="projection.daily.weeksused.label" /></td>
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
										<td>Mon 12/10</td>
										<td>Tue 13/10</td>
										<td>Wed 14/10</td>
										<td>Thu 15/10</td>
										<td>Fri 16/10</td>
										<td>Sat 17/10</td>
										<td>Sun 18/10</td>
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