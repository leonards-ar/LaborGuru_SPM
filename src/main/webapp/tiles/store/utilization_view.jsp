<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<br />
<table border="0" cellspacing="0" align="center">
	<tr>
		<td id="subtitleBar" nowrap><s:text name="store.laborassumptions.title" /> - <s:text name="store.laborassumptions.utilization.guest.title" /></td>
	</tr>

	<tr>
		<td align="center">
			<table id="editFormTable" border="0" cellpadding="6" cellspacing="0" colspan="0" cellspan="0" align="center">
				<tr class="editFormEvenRow">
					<td>
						<table border="0" cellpadding="3" cellspacing="1" align="center">
							<tr class="editorTableHeader">
								<td>&nbsp;</td>
								<td colaspan="2"><s:text name="store.laborassumptions.utilization.guest.label" /></td>
							</tr>
							<tr class="editorTableHeader">
								<td><s:text name="store.laborassumptions.utilization.guest.position.label" /></td>
								<td><s:text name="store.laborassumptions.utilization.bottom.label" /></td>
								<td><s:text name="store.laborassumptions.utilization.top.label" /></td>
							</tr>							
							<tr>
								<td class="editorTableFirstColumn">Position!!!</td>
								<td class="editorTableOddRow"><s:property value='bottom'/></td>
								<td class="editorTableOddRow"><s:property value='top'/></td>
							</tr>
							<tr>
								<td class="editorTableFirstColumn">Position!!!</td>
								<td class="editorTableEvenRow"><s:property value='bottom'/></td>
								<td class="editorTableEvenRow"><s:property value='top'/></td>
							</tr>							
						</table>
					</td>
				</tr>

	<tr>
		<td id="subtitleBar" nowrap><s:text name="store.laborassumptions.title" /> - <s:text name="store.laborassumptions.utilization.nonguest.title" /></td>
	</tr>

	<tr>
		<td align="left">
			<table id="editFormTable" border="0" cellpadding="6" cellspacing="0" colspan="0" cellspan="0">
				<tr class="editFormOddRow">
					<td>
						<table border="0" cellpadding="3" cellspacing="1" align="center">
							<tr class="editorTableHeader">
								<td>&nbsp;</td>
								<td><s:text name="store.laborassumptions.utilization.nonguest.label" /></td>
							</tr>
							<tr>
								<td class="editorTableFirstColumn"><s:text name="store.laborassumptions.utilization.nonguestallpos.title" /></td>
								<td class="editorTableOddRow"><s:property name='allpos'/></td>
							</tr>
					
						</table>					
					</td>
				</tr>
			</table>
		</td>
	</tr>
		
              	<tr class="editFormOddRow">
                    <td width="100%" align="right" colspan="2">
						<s:form action="store_show" theme="simple">
              			<s:hidden name="store.id" theme="simple"/>                    
	                    <table border="0" cellpadding="1" cellspacing="5" colspan="0" cellspan="0">
		                    <tr>
								<td><s:submit action="store_show" key="back.button" theme="simple" cssClass="button"/></td>
		                    </tr>
	                    </table>   
	                    </s:form>                 
                    </td>
                </tr>

			</table>
		</td>
	</tr>
</table>
