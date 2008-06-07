			<br/><br/><br/><br/>
	      <table border="0" cellspacing="0" align="center">
		      <tr>
			      <td id="titleBar">
                  Welcome
            </td>
          </tr>
          <tr>
	          <td align="center">
              <s:form action="login">                   
              <table border="0" cellspacing="6" align="center" id="loginFormTable">
                  <!--s:textfield name="user.userName" label="User Name"/-->
                  <!--s:password name="user.password" label="Password"/-->
				<tr>
                <td class="label">User Name:</td>
                <td><input type="text" name="username"/></td>
                </tr>
                <tr>
				<tr>
                <td class="label">Password:</td>
                <td><input type="text" name="password"/></td>
                </tr>
                <tr>                
                <td colspan="2"><font color="red"><s:actionerror/></font></td>
                </tr>
                <tr>
                  <td align="right" colspan="2"><!--s:submit cssClass="btn" value="Login"/--><input type="submit" value="Login" class="button"/></td>
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