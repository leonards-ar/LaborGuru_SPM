<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="s" uri="/struts-tags" %> 
<html> 
    <head> 
        <title><s:text name="login.title"/></title> 
    </head> 
    <body> 
        <h3><s:text name="login.head"/></h3>
        <h4><s:text name="login.form.title"/></h4> 
        <s:form action="login">                   
            <s:textfield key="user.userName"/> 
            <s:password key="user.password"/>
            <s:submit/> 
        </s:form>
        <s:actionerror /> 
    </body> 
</html> 