<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ page import="org.acegisecurity.context.SecurityContextHolder"%>
<%@ page import="org.acegisecurity.Authentication"%>
<%@ page import="org.acegisecurity.ui.AccessDeniedHandlerImpl"%>

<!--
Debug information 
<%= request.getAttribute(AccessDeniedHandlerImpl.ACEGI_SECURITY_ACCESS_DENIED_EXCEPTION_KEY)%>
<%
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	if (auth != null) {
%>
		Authentication object as a String: <%= auth.toString() %>
<%
	}
%>
 -->
<br />
<br />
<table align="center" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td class="errorSecurityTitle" align="center">
			<s:text	name="error.security.accessdenied.title" />
		</td>
	</tr>

	<tr>
		<td>&nbsp;</td>
	</tr>

	<tr>
		<td class="errorSecurityMessage" align="center">
			<s:text name="error.security.accessdenied.message" />
		</td>
	</tr>

	<tr>
		<td>
			<br />
			<br />
		</td>
	</tr>

	<tr>
		<td align="center">
			<a class="link" href="<s:url action="home" namespace="/home" includeParams="none"/>"><s:text	name="error.security.back.label" /></a>
		</td>
	</tr>
</table>