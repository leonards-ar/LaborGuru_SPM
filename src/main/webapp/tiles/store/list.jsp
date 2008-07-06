<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

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
					<td><s:textfield name="searchStore.name" size="20" theme="simple" /></td>
					<td class="form_label" nowrap><s:text name="store.customer.label" /></td>
					<td>
						<s:select name="searchStore.customerId" list="customers" headerKey="" headerValue="%{getText('store.search.customer.header.label')}" listKey="id" listValue="name" theme="simple"/>
					</td>
					<td><s:submit key="search.button" theme="simple" cssClass="button" /></td>
				</tr>
			</table>
		</td>
		</s:form>
		<!-- End Search Form -->
	</tr>

	<!-- Search and results separator -->
	<tr>
		<td><br/></td>
	</tr>

	<tr>
		<td align="center">
		<!-- Search Results -->
		<s:set name="storesList" value="stores" scope="request"/>
		<display:table name="storesList" class="results" pagesize="20" requestURI="store_search.action" sort="list" defaultsort="1">
			<display:column property="code" titleKey="store.code.label" sortable="true" />
		    <display:column property="name" titleKey="store.name.label" />
		    <display:column property="area.name" titleKey="store.customer.label" />
		    <display:column href="store_show.action" paramId="storeId" paramProperty="id" class="resultsColumnCentered">
		    	<img src="<s:url value="/images/view.png" includeParams="none"/>"/>
		    </display:column>
		    <display:column href="store_edit.action" paramId="storeId" paramProperty="id" class="resultsColumnCentered">
		    	<img src="<s:url value="/images/edit.png" includeParams="none"/>"/>
		    </display:column>		    
		    <display:column href="store_remove.action" paramId="storeId" paramProperty="id" class="resultsColumnCentered"> 
		    	<img src="<s:url value="/images/delete.png" includeParams="none"/>"/>
		    </display:column>
		</display:table>
		<!-- Search Results -->
		</td>
	</tr>
</table>
