<%@ page contentType="text/html; charset=UTF-8" %> <%@ taglib prefix="s"
uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
	<head>
	<title><s:text name="application.title" /></title>
	</head>

	<frameset rows="100%" cols="100%">
		<frame src="<s:url value="/home/home.action" />"/>
	</frameset>
</html>