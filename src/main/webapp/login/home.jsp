<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="s" uri="/struts-tags" %> 
<html> 
    <head> 
        <title><s:text name="home.title"/></title> 
    </head> 
    <body> 
        <h4> <s:text name="home.greeting"><s:param><s:property value="user.name"/></s:param></s:text></h4>
    </body> 
</html> 