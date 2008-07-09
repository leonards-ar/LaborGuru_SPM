<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<br />
<table border="0" cellspacing="0" align="center">
	<tr>
		<td id="titleBar"><s:text name="store.update.title" /></td>
	</tr>

	<tr>
		<td align="center">
			<table id="editFormTable" border="0" cellpadding="6" cellspacing="0"
				colspan="0" cellspan="0" align="center">
				<tr class="editFormEvenRow">
					<td align="right" class="form_label" nowrap><s:text name="store.customer.label" /></td>
					<td align="left" class="value"><s:property value="store.area.region.customer.name" /></td>
				</tr>
	
				<tr class="editFormOddRow">
					<td align="right" class="form_label" nowrap><s:text name="store.code.label" /></td>
					<td align="left" class="value"><s:property value="store.code" /></td>
				</tr>
	
				<tr class="editFormEvenRow">
					<td align="right" class="form_label" nowrap><s:text name="store.name.label" /></td>
					<td align="left" class="value"><s:property value="store.name" /></td>
				</tr>
			</table>
		</td>
	</tr>
</table>