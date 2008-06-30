<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<br />
<table border="0" cellspacing="0" align="center">
	<tr>
		<td id="subtitleBar" nowrap><s:text name="store.storeoperations.title" /> - <s:text name="store.storeoperations.firstdayofweek.title" /></td>
	</tr>

	<tr>
		<td align="left">
			<table id="editFormTable" border="0" cellpadding="6" cellspacing="0" colspan="0" cellspan="0">
				<tr class="editFormOddRow">
                    <td align="right" class="form_label" nowrap><s:text name="store.storeoperations.firstdayofweek.label" /></td>
                    <td align="left" class="value">
                    	<s:text name="%{'dayofweek.'+store.firstDayOfWeek.toString()}" />
                    </td>
				</tr>
			</table>
		</td>
	</tr>
	
	<tr>
		<td>&nbsp;</td>
	</tr>
	
	<tr>
		<td id="subtitleBar" nowrap><s:text name="store.storeoperations.title" /> - <s:text name="store.storeoperations.hoursofoperation.title" /></td>
	</tr>

	<tr>
		<td align="center">
			<table id="editFormTable" border="0" cellpadding="6" cellspacing="0" colspan="0" cellspan="0" align="center">
				<tr class="editFormEvenRow">
					<td>
						<table border="0" cellpadding="3" cellspacing="1" align="center">
							<tr class="editorTableHeader">
								<td>&nbsp;</td>
								<td><s:text name="dayofweek.0" /></td>
								<td><s:text name="dayofweek.1" /></td>
								<td><s:text name="dayofweek.2" /></td>
								<td><s:text name="dayofweek.3" /></td>
								<td><s:text name="dayofweek.4" /></td>
								<td><s:text name="dayofweek.5" /></td>
								<td><s:text name="dayofweek.6" /></td>
							</tr>
							<tr>
								<td class="editorTableFirstColumn"><s:text name="store.storeoperations.hoursofoperation.open" /></td>
								<td class="editorTableOddRow">8:00</td>
								<td class="editorTableOddRow">8:00</td>
								<td class="editorTableOddRow">8:00</td>
								<td class="editorTableOddRow">8:00</td>
								<td class="editorTableOddRow">8:00</td>
								<td class="editorTableOddRow">8:00</td>
								<td class="editorTableOddRow">8:00</td>
							</tr>
							<tr>
								<td class="editorTableFirstColumn"><s:text name="store.storeoperations.hoursofoperation.close" /></td>
								<td class="editorTableEvenRow">21:00</td>
								<td class="editorTableEvenRow">21:00</td>
								<td class="editorTableEvenRow">21:00</td>
								<td class="editorTableEvenRow">21:00</td>
								<td class="editorTableEvenRow">21:00</td>
								<td class="editorTableEvenRow">21:00</td>
								<td class="editorTableEvenRow">21:00</td>
							</tr>						

							<tr>
								<td colspan="6" class="infoMessage"><s:text name="store.storeoperations.hoursofoperation.message" /></td>		
							</tr>						
						</table>
					</td>
				</tr>
	
              	<tr class="editFormOddRow">
                    <td width="100%" align="right" colspan="2">
		              <s:form action="store_save" theme="simple">
		              <s:hidden name="store.id" theme="simple"/>
	                    <table border="0" cellpadding="1" cellspacing="5" colspan="0" cellspan="0">
		                    <tr>
								<td><s:submit action="store_view" key="back.button" theme="simple" cssClass="button"/></td>
		                    </tr>
	                    </table> 
	                    </s:form>                   
                    </td>
                </tr>

			</table>
		</td>
	</tr>
</table>
