<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<br/>
<s:form action="halfhour_save" theme="simple">
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
													<td class="editorTableFirstColumn">9:00</td>
													<td class="editorTableEvenRow">$</td>
													<td class="editorTableEvenRow">$<input type="text" size="10" value=""/></td>
													<td class="editorTableEvenRow">$</td>
												</tr>
												<tr>
													<td class="editorTableFirstColumn">9:30</td>
													<td class="editorTableOddRow">$ 200</td>
													<td class="editorTableOddRow">$<input type="text" size="10" value="200"/></td>
													<td class="editorTableOddRow">$ 200</td>
												</tr>												
												<tr>
													<td class="editorTableFirstColumn">10:00</td>
													<td class="editorTableEvenRow">$</td>
													<td class="editorTableEvenRow">$<input type="text" size="10" value=""/></td>
													<td class="editorTableEvenRow">$</td>
												</tr>
												<tr>
													<td class="editorTableFirstColumn">10:30</td>
													<td class="editorTableOddRow">$ 200</td>
													<td class="editorTableOddRow">$<input type="text" size="10" value="200"/></td>
													<td class="editorTableOddRow">$ 200</td>
												</tr>												
												<tr>
													<td class="editorTableFirstColumn">11:00</td>
													<td class="editorTableEvenRow">$</td>
													<td class="editorTableEvenRow">$<input type="text" size="10" value=""/></td>
													<td class="editorTableEvenRow">$</td>
												</tr>
												<tr>
													<td class="editorTableFirstColumn">11:30</td>
													<td class="editorTableOddRow">$ 200</td>
													<td class="editorTableOddRow">$<input type="text" size="10" value="200"/></td>
													<td class="editorTableOddRow">$ 200</td>
												</tr>												
												<tr>
													<td class="editorTableFirstColumn">12:00</td>
													<td class="editorTableEvenRow">$</td>
													<td class="editorTableEvenRow">$<input type="text" size="10" value=""/></td>
													<td class="editorTableEvenRow">$</td>
												</tr>
												<tr>
													<td class="editorTableFirstColumn">12:30</td>
													<td class="editorTableOddRow">$ 200</td>
													<td class="editorTableOddRow">$<input type="text" size="10" value="200"/></td>
													<td class="editorTableOddRow">$ 200</td>
												</tr>												
												<tr>
													<td class="editorTableFirstColumn">13:00</td>
													<td class="editorTableEvenRow">$</td>
													<td class="editorTableEvenRow">$<input type="text" size="10" value=""/></td>
													<td class="editorTableEvenRow">$</td>
												</tr>
												<tr>
													<td class="editorTableFirstColumn">13:30</td>
													<td class="editorTableOddRow">$ 200</td>
													<td class="editorTableOddRow">$<input type="text" size="10" value="200"/></td>
													<td class="editorTableOddRow">$ 200</td>
												</tr>												
												<tr>
													<td class="editorTableFirstColumn">14:00</td>
													<td class="editorTableEvenRow">$</td>
													<td class="editorTableEvenRow">$<input type="text" size="10" value=""/></td>
													<td class="editorTableEvenRow">$</td>
												</tr>
												<tr>
													<td class="editorTableFirstColumn">14:30</td>
													<td class="editorTableOddRow">$ 200</td>
													<td class="editorTableOddRow">$<input type="text" size="10" value="200"/></td>
													<td class="editorTableOddRow">$ 200</td>
												</tr>												
												<tr>
													<td class="editorTableFirstColumn">15:00</td>
													<td class="editorTableEvenRow">$</td>
													<td class="editorTableEvenRow">$<input type="text" size="10" value=""/></td>
													<td class="editorTableEvenRow">$</td>
												</tr>
												<tr>
													<td class="editorTableFirstColumn">15:30</td>
													<td class="editorTableOddRow">$ 200</td>
													<td class="editorTableOddRow">$<input type="text" size="10" value="200"/></td>
													<td class="editorTableOddRow">$ 200</td>
												</tr>												
												<tr>
													<td class="editorTableFirstColumn">16:00</td>
													<td class="editorTableEvenRow">$</td>
													<td class="editorTableEvenRow">$<input type="text" size="10" value=""/></td>
													<td class="editorTableEvenRow">$</td>
												</tr>
												<tr>
													<td class="editorTableFirstColumn">16:30</td>
													<td class="editorTableOddRow">$ 200</td>
													<td class="editorTableOddRow">$<input type="text" size="10" value="200"/></td>
													<td class="editorTableOddRow">$ 200</td>
												</tr>												
												<tr>
													<td class="editorTableFirstColumn">17:00</td>
													<td class="editorTableEvenRow">$</td>
													<td class="editorTableEvenRow">$<input type="text" size="10" value=""/></td>
													<td class="editorTableEvenRow">$</td>
												</tr>
												<tr>
													<td class="editorTableFirstColumn">17:30</td>
													<td class="editorTableOddRow">$ 200</td>
													<td class="editorTableOddRow">$<input type="text" size="10" value="200"/></td>
													<td class="editorTableOddRow">$ 200</td>
												</tr>												
												<tr>
													<td class="editorTableFirstColumn">18:00</td>
													<td class="editorTableEvenRow">$</td>
													<td class="editorTableEvenRow">$<input type="text" size="10" value=""/></td>
													<td class="editorTableEvenRow">$</td>
												</tr>
												<tr>
													<td class="editorTableFirstColumn">18:30</td>
													<td class="editorTableOddRow">$ 200</td>
													<td class="editorTableOddRow">$<input type="text" size="10" value="200"/></td>
													<td class="editorTableOddRow">$ 200</td>
												</tr>												
												<tr>
													<td class="editorTableFirstColumn">19:00</td>
													<td class="editorTableEvenRow">$</td>
													<td class="editorTableEvenRow">$<input type="text" size="10" value=""/></td>
													<td class="editorTableEvenRow">$</td>
												</tr>
												<tr>
													<td class="editorTableFirstColumn">19:30</td>
													<td class="editorTableOddRow">$ 200</td>
													<td class="editorTableOddRow">$<input type="text" size="10" value="200"/></td>
													<td class="editorTableOddRow">$ 200</td>
												</tr>												
												<tr>
													<td class="editorTableFirstColumn">20:00</td>
													<td class="editorTableEvenRow">$</td>
													<td class="editorTableEvenRow">$<input type="text" size="10" value=""/></td>
													<td class="editorTableEvenRow">$</td>
												</tr>
												<tr>
													<td class="editorTableFirstColumn">20:30</td>
													<td class="editorTableOddRow">$ 200</td>
													<td class="editorTableOddRow">$<input type="text" size="10" value="200"/></td>
													<td class="editorTableOddRow">$ 200</td>
												</tr>												
												<tr>
													<td class="editorTableFirstColumn">21:00</td>
													<td class="editorTableEvenRow">$</td>
													<td class="editorTableEvenRow">$<input type="text" size="10" value=""/></td>
													<td class="editorTableEvenRow">$</td>
												</tr>
												<tr>
													<td class="editorTableFirstColumn">21:30</td>
													<td class="editorTableOddRow">$ 200</td>
													<td class="editorTableOddRow">$<input type="text" size="10" value="200"/></td>
													<td class="editorTableOddRow">$ 200</td>
												</tr>												
												<tr>
													<td class="editorTableFirstColumn">22:00</td>
													<td class="editorTableEvenRow">$</td>
													<td class="editorTableEvenRow">$<input type="text" size="10" value=""/></td>
													<td class="editorTableEvenRow">$</td>
												</tr>
												<!-- End Iterate for each half hour from open to close hour -->							
											</table>                    			
			                    			<!-- End Half Hour Projection -->
                    					</td>
                    					<td valign="top">
			                    			<!-- Action buttons -->
			                    			<br/>
			                    			<br/>
						                    <table border="0" cellpadding="5" cellspacing="5" colspan="0" cellspan="0">
							                    <tr>
							                		<td align="center"><s:submit id="saveButton" key="projection.halfhour.revise.button" theme="simple" cssClass="button"/></td>
							                	</tr>
							                    <tr>
							                		<td align="center"><s:submit id="saveButton" key="save.button" theme="simple" cssClass="button"/></td>
							                	</tr>
							                	<tr>
							                    	<td align="center"><s:reset id="resetButton" key="reset.button" theme="simple" cssClass="button"/></td>
							                    </tr>
							                    <tr>
							                    	<td align="center"><s:submit id="cancelButton" key="cancel.button" action="halfhour_edit" theme="simple" cssClass="button"/></td>		                    
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