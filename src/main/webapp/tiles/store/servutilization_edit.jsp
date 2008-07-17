<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<br />
              <s:form action="storeGuestServiceUtilization_save" theme="simple">
              <s:hidden name="storeId" theme="simple"/>
<table border="0" cellspacing="0" align="center">


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
		<td id="subtitleBar" nowrap><s:text name="store.laborassumptions.title" /> - <s:text name="store.laborassumptions.guestserviceutilization.title" /></td>
	</tr>

	<tr>
		<td align="center">
			<table id="editFormTable" border="0" cellpadding="6" cellspacing="0" colspan="0" cellspan="0" align="center">
				<tr class="editFormEvenRow">
					<td>
						<table border="0" cellpadding="3" cellspacing="1" align="center">
							<tr class="editorTableHeader">
								<td>&nbsp;</td>
								<td colaspan="2"><s:text name="store.laborassumptions.serviceutilizationlimits.title" /></td>
							</tr>
							<tr class="editorTableHeader">
								<td><s:text name="store.laborassumptions.serviceutilization.position.title" /></td>
								<td><s:text name="store.laborassumptions.utilizationbottom.label" /></td>
								<td><s:text name="store.laborassumptions.utilizationtop.label" /></td>
							</tr>							
							<tr>
								<td class="editorTableFirstColumn">Position!!!</td>
								<td class="editorTableOddRow"><s:textfield name='bottom' size="4" maxlength="4"/></td>
								<td class="editorTableOddRow"><s:textfield name='top' size="4" maxlength="4"/></td>
							</tr>
							<tr>
								<td class="editorTableFirstColumn">Position!!!</td>
								<td class="editorTableEvenRow"><s:textfield name='bottom' size="4" maxlength="4"/></td>
								<td class="editorTableEvenRow"><s:textfield name='top' size="4" maxlength="4"/></td>
							</tr>							

							<tr>
								<td colspan="3" class="infoMessage"><s:text name="store.laborassumptions.guestserviceutilization.message" /></td>		
							</tr>						
						</table>
					</td>
				</tr>

	<tr>
		<td id="subtitleBar" nowrap><s:text name="store.laborassumptions.title" /> - <s:text name="store.laborassumptions.nonguestserviceutilization.title" /></td>
	</tr>

	<tr>
		<td align="left">
			<table id="editFormTable" border="0" cellpadding="6" cellspacing="0" colspan="0" cellspan="0">
				<tr class="editFormOddRow">
					<td>
						<table border="0" cellpadding="3" cellspacing="1" align="center">
							<tr class="editorTableHeader">
								<td>&nbsp;</td>
								<td><s:text name="store.laborassumptions.nonguestserviceutilization.title" /></td>
							</tr>
							<tr>
								<td class="editorTableFirstColumn"><s:text name="store.laborassumptions.nonguestserviceutilizationallpositions.title" /></td>
								<td class="editorTableOddRow"><s:textfield name='allpos' size="4" maxlength="4"/></td>
							</tr>
							<tr>
								<td colspan="3" class="infoMessage"><s:text name="store.laborassumptions.nonguestserviceutilization.message" /></td>		
							</tr>						
						</table>					
					</td>
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
		</td>
	</tr>
</table>
</s:form>