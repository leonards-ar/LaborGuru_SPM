<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
	<head>
		<title><s:text name="application.title"/></title>
		<link rel="stylesheet" href="<s:url value="/css/style.css"/>" type="text/css" charset="utf-8" />
    <link rel="stylesheet" type="text/css" media="screen" href="<s:url value="/css/menu/tabs.css"/>" />    
    <script type="text/javascript" src="<s:url value="/js/menu/tabs.js"/>"></script>
		
	</head>
	<body>
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			id="bodyTable" colspan="0" cellspan="0">
			<!-- Header and Menu -->
			<tr>
				<td id="headerRow"><tiles:insertAttribute name="header" /></td>
			</tr>
			<!-- Body -->
			<tr>
				<td id="bodyRow"><tiles:insertAttribute name="body" /></td>
			</tr>
			<!-- Footer -->
			<tr>
				<td id="footerRow"><tiles:insertAttribute name="footer" /></td>
			</tr>
		</table>
	</body>
</html>
