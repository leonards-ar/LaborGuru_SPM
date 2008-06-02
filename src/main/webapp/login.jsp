<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ page import="org.acegisecurity.ui.AbstractProcessingFilter" %>
<%@ page import="org.acegisecurity.ui.webapp.AuthenticationProcessingFilter" %>
<%@ page import="org.acegisecurity.AuthenticationException" %>

<html> 
    <head> 
        <title>SPM - Login Page</title> 
    </head> 
    <body> 
        <h3>SPM - Login Page</h3>
        <h4>Enter your name </h4> 
        <s:form method="post" action="j_acegi_security_check">                   
            <s:textfield name="j_username" label="Username"/> 
            <s:password name="j_password" label="Password"/>
            <s:if test="${not empty param.login_error}">
            	<%= ((AuthenticationException) session.getAttribute(AbstractProcessingFilter.ACEGI_SECURITY_LAST_EXCEPTION_KEY)).getMessage() %>
            </s:if>
            <s:submit/> 
        </s:form> 
    </body> 
</html> 