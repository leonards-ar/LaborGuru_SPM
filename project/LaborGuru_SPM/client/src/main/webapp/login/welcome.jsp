<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="s" uri="/struts-tags" %> 
<html>

<head>
	<title>Scheduling Manager</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link rel="stylesheet" href="/client/style.css" type="text/css" charset="utf-8" />
</head>

<body>
<div id="contenidos">
	<div id="wrapper">
		<div id="header">
			<h1><img src="/client/images/logo.png"  alt="SPM"></h1>
		</div>
	  <br>
 	  <br>
	  <br>
	  <br>
    <div id="body">
  		<div id="body-left">

	      <table border="0" cellspacing="0" align="center">
		      <tr>
			      <td>
	            <div align="left"  id="datostrabajo"><p>Welcome</p></div>
            </td>
          </tr>
          <tr><td><br></td></tr>
          <tr>
	          <td align="center">
              <s:form action="login">                   
              <table border="0" cellspacing="6" align="center">
                  <s:textfield name="user.userName" label="User Name"/>
                  <s:password name="user.password" label="Password"/>
                <tr>
                <td colspan="2"><font color="red"><s:actionerror/></font></td>
                </tr>
                <tr>
                  <td align="right" colspan="2"><s:submit cssClass="btn" value="Login"/></td>
                </tr>
                <tr>
                  <td align="left" colspan="2">
                  	<a href="#">
                  	Forgot your password?
                  </a>
                  </td>
                </tr>
              </table>
              </s:form> 
		      </td>
	        </tr>
        </table>
		  </div>
		</div>
	</div>
</div>
</body>
</html>
