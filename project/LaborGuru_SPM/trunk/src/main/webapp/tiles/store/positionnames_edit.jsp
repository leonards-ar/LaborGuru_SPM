<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<br />
<s:form name="storePositions_form" action="storePositionNames_save" theme="simple">
	<s:hidden name="store.id" theme="simple" />
	<table border="0" cellspacing="0" align="center">

		<tr>
			<td id="subtitleBar" nowrap><s:text name="store.storeoperations.title" /> - <s:text name="store.storeoperations.positionnames.title" /></td>
		</tr>
		<tr>
			<td align="center">
			<table id="editFormTable" border="0" cellpadding="6" cellspacing="0" colspan="0" cellspan="0" align="center">
				<tr class="editorTableHeader">
					<td colspan="2"><s:text
						name="store.storeoperations.positionnames.label" /></td>
				</tr>

				<s:iterator id="position" value="positions" status="stat">
					<tr class="editorTableOddRow">
						<td class="value">
						<s:textfield name="positions[%{#stat.index}].name" value="%{name}" size="25" maxlength="150" theme="simple" /></td>
						<td>
						<table order="0" cellpadding="0" cellspacing="0" colspan="0" cellspan="0">
							<tr>
								<td><a
									href="<s:url value="#" includeParams="none"/>" onclick="storePositions_form.action='storePositionNames_removePosition.action?index=<s:property value='#stat.index'/>'; storePosition_form.submit();"><img
									src="<s:url value="/images/delete.png" includeParams="none"/>" /></a></td>
							</tr>
						</table>
						</td>
					</tr>
				</s:iterator>
				<tr class="editorTableEvenRow">
					<td class="value"><s:textfield value="%{getText('store.storeoperations.positionnames.textvalue.label')}" size="25" maxlength="150" disabled="true"/></td>
					<td>
					<table order="0" cellpadding="0" cellspacing="0" colspan="0" cellspan="0">
						<tr>
							<td><a href="<s:url value="#" includeParams="none"/>" onclick="storePositions_form.action='storePositionNames_addPosition.action'; storePositions_form.submit();"><img
								src="<s:url value="/images/add.png" includeParams="none"/>" /></a></td>
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
					<td><s:submit id="saveButton" key="save.button" theme="simple" cssClass="button" /></td>
					<td><s:submit id="cancelButton" key="cancel.button" action="store_edit" theme="simple" cssClass="button" /></td>
					<td><s:reset id="resetButton" key="reset.button" theme="simple" cssClass="button" /></td>
				</tr>
			</table>
			</td>
		</tr>
		</table>
</s:form>