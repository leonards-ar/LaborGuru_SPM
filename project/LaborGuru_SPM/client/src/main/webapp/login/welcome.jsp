<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="s" uri="/struts-tags" %> 
<html> 
    <head> 
        <title>SPM - Login Page</title> 
    </head> 
    <body> 
        <h3>SPM - Login Page</h3>
        <h4>Enter your name </h4> 
        <s:form action="login">                   
            <s:textfield name="user.userName" label="Username"/> 
            <s:password name="user.password" label="Password"/>
            <s:actionerror/>
            <s:submit/> 
        </s:form> 
    </body> 
</html> 