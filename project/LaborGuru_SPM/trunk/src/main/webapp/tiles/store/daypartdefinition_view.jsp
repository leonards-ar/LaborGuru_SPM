<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<br />

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
								<td><s:text name="store.storeoperations.daypartdefinition.daypart.label" /></td>
								<td><s:text name="store.storeoperations.daypartdefinition.starttime.label" /></td>
							</tr>
							<tr class="editorTableOddRow">
								<td class="value">Day Part 1</td>
								<td class="value">08:00</td>
							</tr>
							<tr class="editorTableEvenRow">
								<td class="value">Day Part 2</td>
								<td class="value">10:00</td>
							</tr>
							<tr class="editorTableOddRow">
								<td class="value">Day Part 3</td>
								<td class="value">08:00</td>
							</tr>
							<tr class="editorTableEvenRow">
								<td class="value">Day Part 4</td>
								<td class="value">10:00</td>
							</tr>
							<tr class="editorTableOddRow">
								<td class="value">Day Part 5</td>
								<td class="value">08:00</td>
							</tr>														
						</table>						
					</td>
				</tr>

              	<tr class="editFormOddRow">
                    <td width="100%" align="right">
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
