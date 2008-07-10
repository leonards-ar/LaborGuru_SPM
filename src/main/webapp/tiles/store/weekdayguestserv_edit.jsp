<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<br />
<table border="0" cellspacing="0" align="center">
	<tr>
		<td id="subtitleBar" nowrap><s:text name="store.laborallowance.title" /> - <s:text name="store.laborallowance.weekdayguestservice.title" /></td>
	</tr>

	<tr>
		<td align="center">
              <s:form action="store_save" theme="simple">
              <s:hidden name="store.id" theme="simple"/>
		
			<table id="editFormTable" border="0" cellpadding="6" cellspacing="0" colspan="0" cellspan="0" align="center">
				<tr class="editFormEvenRow">
					<td>
						<table border="0" cellpadding="3" cellspacing="1" align="center">
							<tr class="editorTableHeader">
								<td>&nbsp;</td>
								<td>Breakfast</td>
								<td>Lunch</td>
								<td>Afternoon</td>
								<td>Evening</td>
								<td>Dinner</td>
							</tr>
							<tr>
								<td class="editorTableFirstColumn">Position 1</td>
								<td class="editorTableOddRow"><input type="text" size="8"/></td>
								<td class="editorTableOddRow"><input type="text" size="8"/></td>
								<td class="editorTableOddRow"><input type="text" size="8"/></td>
								<td class="editorTableOddRow"><input type="text" size="8"/></td>
								<td class="editorTableOddRow"><input type="text" size="8"/></td>
							</tr>
							<tr>
								<td class="editorTableFirstColumn">Position 2</td>
								<td class="editorTableEvenRow"><input type="text" size="8"/></td>
								<td class="editorTableEvenRow"><input type="text" size="8"/></td>
								<td class="editorTableEvenRow"><input type="text" size="8"/></td>
								<td class="editorTableEvenRow"><input type="text" size="8"/></td>
								<td class="editorTableEvenRow"><input type="text" size="8"/></td>
							</tr>						
							<tr>
								<td class="editorTableFirstColumn">Position 3</td>
								<td class="editorTableOddRow"><input type="text" size="8"/></td>
								<td class="editorTableOddRow"><input type="text" size="8"/></td>
								<td class="editorTableOddRow"><input type="text" size="8"/></td>
								<td class="editorTableOddRow"><input type="text" size="8"/></td>
								<td class="editorTableOddRow"><input type="text" size="8"/></td>
							</tr>
							<tr>
								<td class="editorTableFirstColumn">Position 4</td>
								<td class="editorTableEvenRow"><input type="text" size="8"/></td>
								<td class="editorTableEvenRow"><input type="text" size="8"/></td>
								<td class="editorTableEvenRow"><input type="text" size="8"/></td>
								<td class="editorTableEvenRow"><input type="text" size="8"/></td>
								<td class="editorTableEvenRow"><input type="text" size="8"/></td>
							</tr>	
							<tr>
								<td class="editorTableFirstColumn">Position 5</td>
								<td class="editorTableOddRow"><input type="text" size="8"/></td>
								<td class="editorTableOddRow"><input type="text" size="8"/></td>
								<td class="editorTableOddRow"><input type="text" size="8"/></td>
								<td class="editorTableOddRow"><input type="text" size="8"/></td>
								<td class="editorTableOddRow"><input type="text" size="8"/></td>
							</tr>
							<tr>
								<td class="editorTableFirstColumn">Position 6</td>
								<td class="editorTableEvenRow"><input type="text" size="8"/></td>
								<td class="editorTableEvenRow"><input type="text" size="8"/></td>
								<td class="editorTableEvenRow"><input type="text" size="8"/></td>
								<td class="editorTableEvenRow"><input type="text" size="8"/></td>
								<td class="editorTableEvenRow"><input type="text" size="8"/></td>
							</tr>														
							<tr>
								<td class="editorTableFirstColumn">Position 7</td>
								<td class="editorTableOddRow"><input type="text" size="8"/></td>
								<td class="editorTableOddRow"><input type="text" size="8"/></td>
								<td class="editorTableOddRow"><input type="text" size="8"/></td>
								<td class="editorTableOddRow"><input type="text" size="8"/></td>
								<td class="editorTableOddRow"><input type="text" size="8"/></td>
							</tr>
							<tr>
								<td class="editorTableFirstColumn">Position 8</td>
								<td class="editorTableEvenRow"><input type="text" size="8"/></td>
								<td class="editorTableEvenRow"><input type="text" size="8"/></td>
								<td class="editorTableEvenRow"><input type="text" size="8"/></td>
								<td class="editorTableEvenRow"><input type="text" size="8"/></td>
								<td class="editorTableEvenRow"><input type="text" size="8"/></td>
							</tr>	
							<tr>
								<td colspan="6" class="infoMessage"><s:text name="store.laborallowance.message" /></td>		
							</tr>						
						</table>
					</td>
				</tr>
	
              	<tr class="editFormOddRow">
                    <td width="100%" align="right" colspan="2">
	                    <table border="0" cellpadding="1" cellspacing="5" colspan="0" cellspan="0">
		                    <tr>
		                    	<td><s:submit id="saveButton" key="save.button" theme="simple" cssClass="button"/></td>
		                    	<td><s:submit id="cancelButton" key="cancel.button" action="store_edit" theme="simple" cssClass="button"/></td>		                    
		                    	<td><s:reset id="resetButton" key="reset.button" theme="simple" cssClass="button"/></td>
		                    </tr>
	                    </table>                    
                    </td>
                </tr>

			</table>
			</s:form>
		</td>
	</tr>
</table>