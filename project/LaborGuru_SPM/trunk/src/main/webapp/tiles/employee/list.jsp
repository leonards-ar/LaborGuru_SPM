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
					<td class="label" nowrap>Employee Id</td>
					<td><s:textfield name="searchEmployee.id" size="25" theme="simple" /></td>
					<td class="label" nowrap>First Name</td>
					<td><s:textfield name="searchEmployee.name" size="30" theme="simple" /></td>
					<td class="label" nowrap>Last Name</td>
					<td><s:textfield name="searchEmployee.surname" size="30" theme="simple" /></td>
				</tr>
				<tr>
					<td colspan="6" align="right"><s:submit value="Search" theme="simple" cssClass="button" /></td>
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
		<table border="0" cellspacing="1" width="100%" align="center" id="resultsTable">
			<tr>
				<th class="resultsTableHeader">Employee Id</th>
				<th class="resultsTableHeader">First Name</th>
				<th class="resultsTableHeader">Last Name</th>
				<th class="resultsTableHeader">Email</th>
				<th class="resultsTableHeader">&nbsp;</th>
			</tr>
			<s:iterator value="storeEmployees" status="itEmployee">		
				<tr>
					<td
						class="resultsTable<s:if test="#itEmployee.even">Even</s:if><s:else>Odd</s:else>Row"><s:property
						value="id" /></td>
					<td
						class="resultsTable<s:if test="#itEmployee.even">Even</s:if><s:else>Odd</s:else>Row"><s:property
						value="name" /></td>
					<td
						class="resultsTable<s:if test="#itEmployee.even">Even</s:if><s:else>Odd</s:else>Row"><s:property
						value="surname" /></td>
					<td
						class="resultsTable<s:if test="#itEmployee.even">Even</s:if><s:else>Odd</s:else>Row"><a
						href="mailto:mcapurro@gmail.com"></a><s:property value="email" /></td>
					<td
						class="resultsTable<s:if test="#itEmployee.even">Even</s:if><s:else>Odd</s:else>Row"
						align="center">
					<table border="0" cellpadding="1" cellspacing="0">
						<tr>
							<td><a href="<s:url action="employee_show"><s:param name="employeeId" value="id" /></s:url>">
								<img src="<s:url value="/images/view.png"/>" />
							</a></td>
							<td><a href="<s:url action="employee_edit"><s:param name="employeeId" value="id" /></s:url>"> 
								<img src="<s:url value="/images/edit.png"/>" /> </a></td>
							<td><a href="<s:url action="employee_remove"><s:param name="employeeId" value="id" /></s:url>"> 
								<img src="<s:url value="/images/delete.png"/>" />
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
