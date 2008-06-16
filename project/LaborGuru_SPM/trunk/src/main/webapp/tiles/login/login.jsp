<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="org.acegisecurity.ui.AbstractProcessingFilter"%>
<%@ page
	import="org.acegisecurity.ui.webapp.AuthenticationProcessingFilter"%>
<%@ page import="org.acegisecurity.AuthenticationException"%>

<br />
<br />
<br />
<br />
<table border="0" cellspacing="0" align="center">
	<tr>
		<td id="titleBar"><s:text name="login.head" /></td>
	</tr>
	<tr>
		<td align="center">
		<form action="<s:url value="/j_acegi_security_check"/>" method="post" name="login">
		<table border="0" cellspacing="6" align="center" id="loginFormTable">
			<tr>
				<td class="label"><s:text name="user.userName" /></td>
				<td><input type="text" name="j_username" /></td>
			</tr>
			<tr>
			<tr>
				<td class="label"><s:text name="user.password" /></td>
				<td><input type="password" name="j_password" /></td>
			</tr>
			<%
				if (request.getParameter("login_error") != null) {
					Throwable e = (Throwable) session.getAttribute(AbstractProcessingFilter.ACEGI_SECURITY_LAST_EXCEPTION_KEY);
					String exceptionMessage = "Login failed for unknown reason.";
				    
				    if(e.getCause() != null) {
				    	e = e.getCause();
				    }
				    
				    exceptionMessage = e.getMessage();
			%>
			<tr>
				<td colspan="2"><font color="red">
				 <%= exceptionMessage %>
				</font></td>
			</tr>
			<%
				}
			%>
			<tr>
				<td align="right" colspan="2"><input type="submit"
					value="<s:text name="login.submit"/>" class="button" /></td>
			</tr>
			<tr>
				<td align="left" colspan="2"><a href="#" class="link">Forgot your password?</a></td>
			</tr>
		</table>
		</form>
		</td>
	</tr>
</table>