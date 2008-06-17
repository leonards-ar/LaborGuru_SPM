<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="s" uri="/struts-tags" %> 

<table width="100%" border="0" cellpadding="0" cellspacing="0"
	id="headerTable" colspan="0" cellspan="0">
	<tr>
		<td id="headerTop" valign="bottom">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			colspan="0" cellspan="0">
			<tr>
				<td width="20%"><img src="<s:url value="/images/logo.png"/>" /></td>
				<td width="60%" valign="bottom" align="center">

				<table border="0" cellpadding="0" cellspacing="0" id="menuTable"
					colspan="0" cellspan="0">
					<tr>
					<s:iterator value="#request.spmMenu.items" status="itCtx">
						<s:if test="#request.spmMenu.selectedItemIndex == #itCtx.index">
						<td class="selectedMenuItem"><s:text name="#labelKey"/></td>
						</s:if>
						<s:else>
						<td class="availableMenuItem"
							onmouseover="className='availableMenuItemOver'"
							onmouseout="className='availableMenuItem'"><a href="<s:url action="#target"/>"
							class="availableMenuItemAnchor"><s:text name="#labelKey"/></a></td>						
						</s:else>
						
					</s:iterator>
					</tr>
				</table>

				</td>
				<td width="20%">&nbsp;</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td id="headerBottom">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			colspan="0" cellspan="0">
			<tr>
				<td width="20%"><span id="headerBottom">Welcome <s:property value="#request.spmUser.name"/> <s:property value="#request.spmUser.surname"/></span></td>
				<td width="60%" align="center">
				<!-- Submenu -->
				<table border="0" cellpadding="0" cellspacing="0" id="subMenuTable"
					colspan="0" cellspan="0">
					<tr>
					<s:iterator value="#request.spmMenu.selectedItem" status="itCtx">
						<td class="subMenuItem"><a href="<s:url action="#target"/>" class="subMenuItemAnchor"><s:text name="#labelKey"/></a></td>
						<s:if test="#itCtx.index - 1 < #itCtx.count">
						<td>|</td>
						</s:if>
					</s:iterator>
					</tr>
				</table>
				</td>
				<td width="20%">&nbsp;</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
