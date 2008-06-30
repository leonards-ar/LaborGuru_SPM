<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<br />
              <s:form action="store_save" theme="simple">
              <s:hidden name="store.id" theme="simple"/>
<table border="0" cellspacing="0" align="center">
	
	<tr>
		<td id="subtitleBar" nowrap><s:text name="store.storeoperations.title" /> - <s:text name="store.storeoperations.daypartdefinition.title" /></td>
	</tr>

	<tr>
		<td align="center">
			<table id="editFormTable" border="0" cellpadding="6" cellspacing="0" colspan="0" cellspan="0" align="center">
				<tr>
					<td>
						<table border="0" cellpadding="3" cellspacing="1" align="center">
							<tr class="editorTableHeader">
								<td>&nbsp;</td>
								<td><s:text name="store.storeoperations.daypartdefinition.daypart.label" /></td>
								<td><s:text name="store.storeoperations.daypartdefinition.starttime.label" /></td>
								<td>&nbsp;</td>
							</tr>
							<tr class="editorTableOddRow">
								<td>
									<table order="0" cellpadding="0" cellspacing="0" colspan="0" cellspan="0">
										<tr>
											<td><a href="<s:url value="#" includeParams="none"/>"><img src="<s:url value="/images/up.png" includeParams="none"/>"/></a></td>
											<td><a href="<s:url value="#" includeParams="none"/>"><img src="<s:url value="/images/down.png" includeParams="none"/>"/></a></td>
										</tr>
									</table>
								</td>							
								<td class="value"><input type="text" value="Day Part 1" size="25" maxlength="150"/></td>
								<td class="value"><input type="text" value="08:00" size="5" maxlength="5"/></td>
								<td>
									<table order="0" cellpadding="0" cellspacing="0" colspan="0" cellspan="0">
										<tr>
											<td><a href="<s:url value="#" includeParams="none"/>"><img src="<s:url value="/images/delete.png" includeParams="none"/>"/></a></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr class="editorTableEvenRow">
								<td>
									<table order="0" cellpadding="0" cellspacing="0" colspan="0" cellspan="0">
										<tr>
											<td><a href="<s:url value="#" includeParams="none"/>"><img src="<s:url value="/images/up.png" includeParams="none"/>"/></a></td>
											<td><a href="<s:url value="#" includeParams="none"/>"><img src="<s:url value="/images/down.png" includeParams="none"/>"/></a></td>
										</tr>
									</table>
								</td>								
								<td class="value"><input type="text" value="Day Part 2" size="25" maxlength="150"/></td>
								<td class="value"><input type="text" value="10:00" size="5" maxlength="5"/></td>
								<td>
									<table order="0" cellpadding="0" cellspacing="0" colspan="0" cellspan="0">
										<tr>
											<td><a href="<s:url value="#" includeParams="none"/>"><img src="<s:url value="/images/delete.png" includeParams="none"/>"/></a></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr class="editorTableOddRow">
								<td>
									<table order="0" cellpadding="0" cellspacing="0" colspan="0" cellspan="0">
										<tr>
											<td><a href="<s:url value="#" includeParams="none"/>"><img src="<s:url value="/images/up.png" includeParams="none"/>"/></a></td>
											<td><a href="<s:url value="#" includeParams="none"/>"><img src="<s:url value="/images/down.png" includeParams="none"/>"/></a></td>
										</tr>
									</table>
								</td>								
								<td class="value"><input type="text" value="Day Part 3" size="25" maxlength="150"/></td>
								<td class="value"><input type="text" value="08:00" size="5" maxlength="5"/></td>
								<td>
									<table order="0" cellpadding="0" cellspacing="0" colspan="0" cellspan="0">
										<tr>
											<td><a href="<s:url value="#" includeParams="none"/>"><img src="<s:url value="/images/delete.png" includeParams="none"/>"/></a></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr class="editorTableEvenRow">
								<td>
									<table order="0" cellpadding="0" cellspacing="0" colspan="0" cellspan="0">
										<tr>
											<td><a href="<s:url value="#" includeParams="none"/>"><img src="<s:url value="/images/up.png" includeParams="none"/>"/></a></td>
											<td><a href="<s:url value="#" includeParams="none"/>"><img src="<s:url value="/images/down.png" includeParams="none"/>"/></a></td>
										</tr>
									</table>
								</td>								
								<td class="value"><input type="text" value="Day Part 4" size="25" maxlength="150"/></td>
								<td class="value"><input type="text" value="10:00" size="5" maxlength="5"/></td>
								<td>
									<table order="0" cellpadding="0" cellspacing="0" colspan="0" cellspan="0">
										<tr>
											<td><a href="<s:url value="#" includeParams="none"/>"><img src="<s:url value="/images/delete.png" includeParams="none"/>"/></a></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr class="editorTableOddRow">
								<td>
									<table order="0" cellpadding="0" cellspacing="0" colspan="0" cellspan="0">
										<tr>
											<td><a href="<s:url value="#" includeParams="none"/>"><img src="<s:url value="/images/up.png" includeParams="none"/>"/></a></td>
											<td><a href="<s:url value="#" includeParams="none"/>"><img src="<s:url value="/images/down.png" includeParams="none"/>"/></a></td>
										</tr>
									</table>
								</td>								
								<td class="value"><input type="text" value="Day Part 5" size="25" maxlength="150"/></td>
								<td class="value"><input type="text" value="08:00" size="5" maxlength="5"/></td>
								<td>
									<table order="0" cellpadding="0" cellspacing="0" colspan="0" cellspan="0">
										<tr>
											<td><a href="<s:url value="#" includeParams="none"/>"><img src="<s:url value="/images/delete.png" includeParams="none"/>"/></a></td>
										</tr>
									</table>
								</td>
							</tr>														
							<tr class="editorTableEvenRow">
								<td class="value" colspan="3">&nbsp;</td>
								<td>
									<table order="0" cellpadding="0" cellspacing="0" colspan="0" cellspan="0">
										<tr>
											<td><a href="<s:url value="#" includeParams="none"/>"><img src="<s:url value="/images/add.png" includeParams="none"/>"/></a></td>
										</tr>
									</table>
								</td>
							</tr>							
						</table>						
					</td>
				</tr>

              	<tr class="editFormOddRow">
                    <td width="100%" align="right">
	                    <table border="0" cellpadding="1" cellspacing="5" colspan="0" cellspan="0">
		                    <tr>
		                    	<td><s:submit id="saveButton" key="save.button" theme="simple" cssClass="button"/></td>
		                    	<td><s:submit id="cancelButton" key="cancel.button" action="store_list" theme="simple" cssClass="button"/></td>		                    
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