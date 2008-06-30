<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<br />

<table border="0" cellspacing="0" align="center">
	
	<tr>
		<td id="subtitleBar" nowrap><s:text name="store.storeoperations.title" /> - <s:text name="store.storeoperations.positionnames.title" /></td>
	</tr>

	<tr>
		<td align="center">
			<table id="editFormTable" border="0" cellpadding="6" cellspacing="0" colspan="0" cellspan="0" align="center">
				<tr>
					<td>
						<table border="0" cellpadding="3" cellspacing="1" align="center">
							<tr class="editorTableHeader">
								<td><s:text name="store.storeoperations.positionnames.label" /></td>
							</tr>
							<tr class="editorTableOddRow">
								<td class="value">Position 1</td>
							</tr>
							<tr class="editorTableEvenRow">
								<td class="value">Position 2</td>
							</tr>					
							<tr class="editorTableOddRow">
								<td class="value">Position 3</td>
							</tr>
							<tr class="editorTableEvenRow">
								<td class="value">Position 4</td>
							</tr>					
							<tr class="editorTableOddRow">
								<td class="value">Position 5</td>
							</tr>
							<tr class="editorTableEvenRow">
								<td class="value">Position 6</td>
							</tr>					

						</table>						
					</td>
				</tr>

              	<tr class="editFormOddRow">
                    <td width="100%" align="right">
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
