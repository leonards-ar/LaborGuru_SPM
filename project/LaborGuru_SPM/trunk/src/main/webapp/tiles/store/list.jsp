<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<br />
<br />
<table border="0" cellspacing="0" align="center">
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<!-- Search Form --> 
		<s:form action="store_search" theme="simple">
		<td align="center">
			<table border="0" cellspacing="6" align="center" id="searchFormTable">
				<tr>
					<td class="form_label" nowrap><s:text name="store.code.label" /></td>
					<td><s:textfield name="searchStore.code" size="10" theme="simple" /></td>
					<td class="form_label" nowrap><s:text name="store.name.label" /></td>
					<td><s:textfield name="searchStore.name" size="30" theme="simple" /></td>
					<td><s:submit key="search.button" theme="simple" cssClass="button" /></td>
					<td class="form_label" nowrap><s:text name="store.office.label" /></td>
					<td><select><option>Cliente 1</option><option>Cliente 2</option></select></td>
				</tr>
			</table>
		</td>
		</s:form> <!-- End Search Form -->
	</tr>

	<!-- Search and results separator -->
	<tr>
		<td><br/></td>
	</tr>

	<tr>
		<td align="center">
		<!-- Search Results -->
		<table border="0" cellspacing="1" width="100%" align="center" cellpadding="2" id="resultsTable">
			<tr>
				<th class="resultsTableHeader"><s:text name="store.code.label" /></th>
				<th class="resultsTableHeader"><s:text name="store.code.label" /></th>
				<th class="resultsTableHeader"><s:text name="store.office.label" /></th>
				<th class="resultsTableHeader">&nbsp;</th>
			</tr>
			<s:iterator value="stores" status="itStore">		
				<tr>
					<td
						class="resultsTable<s:if test="#itStore.even">Even</s:if><s:else>Odd</s:else>Row"><s:property
						value="code" /></td>
					<td
						class="resultsTable<s:if test="#itStore.even">Even</s:if><s:else>Odd</s:else>Row"><a
						href="mailto:<s:property value="email" />" class="resultsTableLink"><s:property value="name" /></a></td>
					<td
						class="resultsTable<s:if test="#itStore.even">Even</s:if><s:else>Odd</s:else>Row"><s:property
						value="office.name" /></td>
					<table border="0" cellpadding="1" cellspacing="0">
						<tr>
							<td><a href="<s:url action="store_show" includeParams="none"><s:param name="storeId" value="id"/></s:url>">
								<img src="<s:url value="/images/view.png" includeParams="none"/>"/>
							</a></td>
							<td><a href="<s:url action="store_edit" includeParams="none"><s:param name="storeId" value="id" /></s:url>"> 
								<img src="<s:url value="/images/edit.png" includeParams="none"/>"/> </a></td>
							<td><a href="<s:url action="store_remove" includeParams="none"><s:param name="storeId" value="id" /></s:url>"> 
								<img src="<s:url value="/images/delete.png"/>" includeParams="none"/>
							</a></td>
						</tr>
					</table>
					</td>
				</tr>
			</s:iterator>
		</table>
		<!-- Search Results -->
		</td>
	</tr>
</table>
