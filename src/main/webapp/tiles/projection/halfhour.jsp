<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<br/>
<s:form action="employee_save" theme="simple">
	      <table border="0" cellspacing="0" align="center">
		      <tr>
			      <td id="titleBar">
			      	<s:text name="projection.halfhour.title" />
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
		                    <td width="20%" align="right" class="form_label" nowrap><s:text name="projection.halfhour.day.label" /></td>
		                    <td width="80%" align="left" class="value"><s:datetimepicker displayFormat="MM/dd/yyyy" disabled="true" name="object.field" theme="simple"/></td>
		                </tr>
		              	<tr class="editFormEvenRow">
		                    <td width="20%" align="right" class="form_label" nowrap><s:text name="projection.halfhour.weeksused.label" /></td>
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
                    			<table border="0" cellpadding="0" cellspacing="0" colspan="0" cellspan="0" align="center">
                    				<tr>
                    					<td>
			                    			<!-- Half Hour Projection -->
											<table border="0" cellpadding="3" cellspacing="1" colspan="0" cellspan="0" align="center">
												<tr class="editorTableHeader">
													<td><s:text name="projection.halfhour.hour.label" /></td>
													<td><s:text name="projection.halfhour.projection.label" /></td>
													<td><s:text name="projection.halfhour.changes.label" /></td>
													<td><s:text name="projection.halfhour.revisedprojection.label" /></td>
												</tr>
												<tr>
													<td class="editorTableFirstColumn"><b><s:text name="projection.halfhour.daytotal.label" /></b></td>
													<td class="editorTableOddRow"><b>$ 28,380</b></td>
													<td class="editorTableOddRow"><b>$ 2,100</b></td>
													<td class="editorTableOddRow"><b>$ 2,100</b></td>
												</tr>
												<!-- Iterate for each half hour from open to close hour -->
												<tr>
													<td class="editorTableFirstColumn"></td>
													<td class="editorTableEvenRow">9:00</td>
													<td class="editorTableEvenRow">$<input type="text" size="10" value=""/></td>
													<td class="editorTableEvenRow">$</td>
												</tr>
												<tr>
													<td class="editorTableFirstColumn"></td>
													<td class="editorTableOddRow">9:30</td>
													<td class="editorTableOddRow">$<input type="text" size="10" value="200"/></td>
													<td class="editorTableOddRow">$ 200</td>
												</tr>												
												<tr>
													<td class="editorTableFirstColumn"></td>
													<td class="editorTableEvenRow">10:00</td>
													<td class="editorTableEvenRow">$<input type="text" size="10" value=""/></td>
													<td class="editorTableEvenRow">$</td>
												</tr>
												<tr>
													<td class="editorTableFirstColumn"></td>
													<td class="editorTableOddRow">10:30</td>
													<td class="editorTableOddRow">$<input type="text" size="10" value="200"/></td>
													<td class="editorTableOddRow">$ 200</td>
												</tr>												
												<tr>
													<td class="editorTableFirstColumn"></td>
													<td class="editorTableEvenRow">11:00</td>
													<td class="editorTableEvenRow">$<input type="text" size="10" value=""/></td>
													<td class="editorTableEvenRow">$</td>
												</tr>
												<tr>
													<td class="editorTableFirstColumn"></td>
													<td class="editorTableOddRow">11:30</td>
													<td class="editorTableOddRow">$<input type="text" size="10" value="200"/></td>
													<td class="editorTableOddRow">$ 200</td>
												</tr>												
												<tr>
													<td class="editorTableFirstColumn"></td>
													<td class="editorTableEvenRow">12:00</td>
													<td class="editorTableEvenRow">$<input type="text" size="10" value=""/></td>
													<td class="editorTableEvenRow">$</td>
												</tr>
												<tr>
													<td class="editorTableFirstColumn"></td>
													<td class="editorTableOddRow">12:30</td>
													<td class="editorTableOddRow">$<input type="text" size="10" value="200"/></td>
													<td class="editorTableOddRow">$ 200</td>
												</tr>												
												<tr>
													<td class="editorTableFirstColumn"></td>
													<td class="editorTableEvenRow">13:00</td>
													<td class="editorTableEvenRow">$<input type="text" size="10" value=""/></td>
													<td class="editorTableEvenRow">$</td>
												</tr>
												<tr>
													<td class="editorTableFirstColumn"></td>
													<td class="editorTableOddRow">13:30</td>
													<td class="editorTableOddRow">$<input type="text" size="10" value="200"/></td>
													<td class="editorTableOddRow">$ 200</td>
												</tr>												
												<tr>
													<td class="editorTableFirstColumn"></td>
													<td class="editorTableEvenRow">14:00</td>
													<td class="editorTableEvenRow">$<input type="text" size="10" value=""/></td>
													<td class="editorTableEvenRow">$</td>
												</tr>
												<tr>
													<td class="editorTableFirstColumn"></td>
													<td class="editorTableOddRow">14:30</td>
													<td class="editorTableOddRow">$<input type="text" size="10" value="200"/></td>
													<td class="editorTableOddRow">$ 200</td>
												</tr>												
												<tr>
													<td class="editorTableFirstColumn"></td>
													<td class="editorTableEvenRow">15:00</td>
													<td class="editorTableEvenRow">$<input type="text" size="10" value=""/></td>
													<td class="editorTableEvenRow">$</td>
												</tr>
												<tr>
													<td class="editorTableFirstColumn"></td>
													<td class="editorTableOddRow">15:30</td>
													<td class="editorTableOddRow">$<input type="text" size="10" value="200"/></td>
													<td class="editorTableOddRow">$ 200</td>
												</tr>												
												<tr>
													<td class="editorTableFirstColumn"></td>
													<td class="editorTableEvenRow">16:00</td>
													<td class="editorTableEvenRow">$<input type="text" size="10" value=""/></td>
													<td class="editorTableEvenRow">$</td>
												</tr>
												<tr>
													<td class="editorTableFirstColumn"></td>
													<td class="editorTableOddRow">16:30</td>
													<td class="editorTableOddRow">$<input type="text" size="10" value="200"/></td>
													<td class="editorTableOddRow">$ 200</td>
												</tr>												
												<tr>
													<td class="editorTableFirstColumn"></td>
													<td class="editorTableEvenRow">17:00</td>
													<td class="editorTableEvenRow">$<input type="text" size="10" value=""/></td>
													<td class="editorTableEvenRow">$</td>
												</tr>
												<tr>
													<td class="editorTableFirstColumn"></td>
													<td class="editorTableOddRow">17:30</td>
													<td class="editorTableOddRow">$<input type="text" size="10" value="200"/></td>
													<td class="editorTableOddRow">$ 200</td>
												</tr>												
												<tr>
													<td class="editorTableFirstColumn"></td>
													<td class="editorTableEvenRow">18:00</td>
													<td class="editorTableEvenRow">$<input type="text" size="10" value=""/></td>
													<td class="editorTableEvenRow">$</td>
												</tr>
												<tr>
													<td class="editorTableFirstColumn"></td>
													<td class="editorTableOddRow">18:30</td>
													<td class="editorTableOddRow">$<input type="text" size="10" value="200"/></td>
													<td class="editorTableOddRow">$ 200</td>
												</tr>												
												<tr>
													<td class="editorTableFirstColumn"></td>
													<td class="editorTableEvenRow">19:00</td>
													<td class="editorTableEvenRow">$<input type="text" size="10" value=""/></td>
													<td class="editorTableEvenRow">$</td>
												</tr>
												<tr>
													<td class="editorTableFirstColumn"></td>
													<td class="editorTableOddRow">19:30</td>
													<td class="editorTableOddRow">$<input type="text" size="10" value="200"/></td>
													<td class="editorTableOddRow">$ 200</td>
												</tr>												
												<tr>
													<td class="editorTableFirstColumn"></td>
													<td class="editorTableEvenRow">20:00</td>
													<td class="editorTableEvenRow">$<input type="text" size="10" value=""/></td>
													<td class="editorTableEvenRow">$</td>
												</tr>
												<tr>
													<td class="editorTableFirstColumn"></td>
													<td class="editorTableOddRow">20:30</td>
													<td class="editorTableOddRow">$<input type="text" size="10" value="200"/></td>
													<td class="editorTableOddRow">$ 200</td>
												</tr>												
												<tr>
													<td class="editorTableFirstColumn"></td>
													<td class="editorTableEvenRow">21:00</td>
													<td class="editorTableEvenRow">$<input type="text" size="10" value=""/></td>
													<td class="editorTableEvenRow">$</td>
												</tr>
												<tr>
													<td class="editorTableFirstColumn"></td>
													<td class="editorTableOddRow">21:30</td>
													<td class="editorTableOddRow">$<input type="text" size="10" value="200"/></td>
													<td class="editorTableOddRow">$ 200</td>
												</tr>												
												<tr>
													<td class="editorTableFirstColumn"></td>
													<td class="editorTableEvenRow">22:00</td>
													<td class="editorTableEvenRow">$<input type="text" size="10" value=""/></td>
													<td class="editorTableEvenRow">$</td>
												</tr>
												<!-- End Iterate for each half hour from open to close hour -->							
											</table>                    			
			                    			<!-- End Half Hour Projection -->
                    					</td>
                    					<td>
			                    			<!-- Action buttons -->
			                    			<br/>
			                    			<br/>
						                    <table border="0" cellpadding="5" cellspacing="5" colspan="0" cellspan="0">
							                    <tr>
							                		<td><s:submit id="saveButton" key="projection.halfhour.revise.button" theme="simple" cssClass="button"/></td>
							                	</tr>
							                    <tr>
							                		<td><s:submit id="saveButton" key="save.button" theme="simple" cssClass="button"/></td>
							                	</tr>
							                	<tr>
							                    	<td><s:reset id="resetButton" key="reset.button" theme="simple" cssClass="button"/></td>
							                    </tr>
							                    <tr>
							                    	<td><s:submit id="cancelButton" key="cancel.button" action="daily_edit" theme="simple" cssClass="button"/></td>		                    
							                    </tr>
						                    </table>                    
			                    			<!-- End Action buttons -->
                    					</td>
                    				</tr>
                    			</table>
                    		</td>
		                </tr>
 
              		</table>
	              </td>
              </tr>
          </table>
</s:form>