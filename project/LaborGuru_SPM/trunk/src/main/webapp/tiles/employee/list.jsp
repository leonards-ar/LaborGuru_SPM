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
		<s:form action="employee_search" theme="simple">
		<td align="center">
			<table border="0" cellspacing="6" align="center" id="searchFormTable">
				<tr>
					<td class="form_label" nowrap><s:text name="employee.employeeid.label" /></td>
					<td><s:textfield name="searchEmployee.employeeId" size="10" theme="simple" /></td>
					<td class="form_label" nowrap><s:text name="employee.fullname.label" /></td>
					<td><s:textfield name="searchEmployee.fullName" size="30" theme="simple" /></td>
					<td><s:submit key="search.button" theme="simple" cssClass="button" /></td>
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
				<th class="resultsTableHeader"><s:text name="employee.fullname.label" /></th>
				<th class="resultsTableHeader"><s:text name="employee.email.label" /></th>
				<th class="resultsTableHeader"><s:text name="employee.phone.label" /></th>
				<th class="resultsTableHeader">&nbsp;</th>
			</tr>
			<s:iterator value="storeEmployees" status="itEmployee">		
				<tr>
					<td
						class="resultsTable<s:if test="#itEmployee.even">Even</s:if><s:else>Odd</s:else>Row"><s:property
						value="fullName" /></td>
					<td
						class="resultsTable<s:if test="#itEmployee.even">Even</s:if><s:else>Odd</s:else>Row"><a
						href="mailto:<s:property value="email" />" class="resultsTableLink"><s:property value="email" /></a></td>
					<td
						class="resultsTable<s:if test="#itEmployee.even">Even</s:if><s:else>Odd</s:else>Row"><s:property
						value="phone" /></td>
					<td
						class="resultsTable<s:if test="#itEmployee.even">Even</s:if><s:else>Odd</s:else>Row"
						align="center">
					<table border="0" cellpadding="1" cellspacing="0">
						<tr>
							<td><a href="<s:url action="employee_show" includeParams="none"><s:param name="employeeId" value="id"/></s:url>">
								<img src="<s:url value="/images/view.png" includeParams="none"/>"/>
							</a></td>
							<td><a href="<s:url action="employee_edit" includeParams="none"><s:param name="employeeId" value="id" /></s:url>"> 
								<img src="<s:url value="/images/edit.png" includeParams="none"/>"/> </a></td>
							<td><a href="<s:url action="employee_remove" includeParams="none"><s:param name="employeeId" value="id" /></s:url>"> 
								<img src="<s:url value="/images/delete.png" includeParams="none"/>"/>
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
