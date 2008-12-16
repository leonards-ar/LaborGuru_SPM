<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
	<head>
		<title><s:text name="application.title"/></title>
		<tiles:insertAttribute name="htmlExtHeader" />
		<link rel="stylesheet" href="<s:url value="/css/style.css" includeParams="none" />" type="text/css" charset="utf-8" />
		<meta http-equiv="refresh" content="2;url=<s:url/>"/>
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
				<td id="bodyRow">
					<table border="0" cellspacing="0" align="center">
					<tr>
						<td>
							<tiles:insertAttribute name="weekSelector" />	  
						</td>
					</tr>
					
					<tr>
						<td>
							<tiles:insertAttribute name="reportData" />	
						</td>
					</tr>
					</table>  				
				</td>
			</tr>
			<!-- Footer -->
			<tr>
				<td id="footerRow"><tiles:insertAttribute name="footer" /></td>
			</tr>
		</table>
	</body>
</html>

