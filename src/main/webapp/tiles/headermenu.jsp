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
						<td class="availableMenuItem"
							onmouseover="className='availableMenuItemOver'"
							onmouseout="className='availableMenuItem'"><a href="#"
							class="availableMenuItemAnchor">Opcion 1</a></td>
						<td class="selectedMenuItem">Opcion 2</td>
						<td class="availableMenuItem"
							onmouseover="className='availableMenuItemOver'"
							onmouseout="className='availableMenuItem'"><a href="#"
							class="availableMenuItemAnchor">Opcion 3</a></td>
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
				<td width="20%"><span id="headerBottom">Welcome, please
				login</span></td>
				<td width="60%" align="center"><!-- Submenu -->
				<table border="0" cellpadding="0" cellspacing="0" id="subMenuTable"
					colspan="0" cellspan="0">
					<tr>
						<td class="subMenuItem"><a href="#" class="subMenuItemAnchor">Subopcion
						1</a></td>
						<td>|</td>
						<td class="subMenuItem"><a href="#" class="subMenuItemAnchor">Subopcion
						2</a></td>
						<td>|</td>
						<td class="subMenuItem"><a href="#" class="subMenuItemAnchor">Subopcion
						3</a></td>
					</tr>
				</table>
				</td>
				<td width="20%">&nbsp;</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
