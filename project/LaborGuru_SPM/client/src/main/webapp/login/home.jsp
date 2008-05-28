<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="s" uri="/struts-tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>Scheduling Manager</title>
	<link rel="stylesheet" href="/client/style.css" type="text/css" charset="utf-8" />
</head>

<body>
<div id="contenidos">
	<div id="wrapper">
		<div id="header">
			<h1><img src="/client/images/logo.png"  alt="SPM"></h1>
			<div id="nav">
			<div class="blue">
			<div id="slatenav">
	<ul>
	<li><a href="index.htm" title="Home" class="current">Home</a></li>
	<li><a href="#" title="Add">Add</a></li>
	<li><a href="#" title="Search" >Search</a></li>
	<li><a href="#" title="Hours">Schedule</a></li>
	<li><a href="#" title="Reports">Reports</a></li>
	</ul>
	</div>
	</div>				
	</div>
	</div>
</div>
	<table width="100%">
		<tr>
			<td width="50%" align="left">Congratulations, <b><s:property value="user.userName"/></b>, welcome to SPM</td>
			<td width="50%" align="right"><a href="welcome.jsp">Logout</a></td>
		</tr>
</table>
</body>
</html>
