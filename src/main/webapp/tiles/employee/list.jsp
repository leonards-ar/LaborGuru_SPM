<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

			<br/><br/>
	      <table border="0" cellspacing="0" align="center">
		      <tr>
			      <td>&nbsp;</td>
        	  </tr>
			  <tr>
	          	<td align="center">
				<!-- Search Form -->
                <form name="employeeList">
                <table border="0" cellspacing="6" align="center" id="searchFormTable">
                <tr>
                   <td class="label" nowrap>Employee Id</td>
                   <td><input type="text" size="10"/></td>
                   <td class="label" nowrap>First Name</td>
                   <td><input type="text" size="25" maxlength="50"/></td>
                   <td class="label" nowrap>Last Name</td>
                   <td><input type="text" size="25" maxlength="50"/></td>
                </tr>
                <tr>
                	<td colspan="6" align="right"><input type="submit" value="Search" class="button"/></td>
                </tr>
                </table>
                </form>
                <!-- End Search Form -->
		      </td>
              </tr>
              
              <!-- Search and results separator -->
              <tr>
              <td><br/></td>
              </tr>
              
              <tr>
              <td align="center">
              <!-- Search Results -->
          		<table border="0" cellspacing="1" width="100%" align="center" id="resultsTable">
                <tr>
                   <th class="resultsTableHeader">Employee Id</th>
                   <th class="resultsTableHeader">First Name</th>
                   <th class="resultsTableHeader">Last Name</th>
                   <th class="resultsTableHeader">Email</th>
                   <th class="resultsTableHeader">&nbsp;</th>
                </tr>
				<tr>
                	<td class="resultsTableOddRow">1</td>
                	<td class="resultsTableOddRow">Mariano</td>
                	<td class="resultsTableOddRow">Capurro</td>
                	<td class="resultsTableOddRow"><a href="mailto:mcapurro@gmail.com">mcapurro@gmail.com</a></td>
                	<td class="resultsTableOddRow" align="center">
                    <table border="0" cellpadding="1" cellspacing="0" colspan="0" cellspan="0"><tr>
                    <s:url value="/images/logo.png"/>
                    <td><a href="#"><img src="<s:url value="/images/view.png"/>"/></a></td>
                	<td><a href="#"><img src="<s:url value="/images/edit.png"/>"/></a></td>
                	<td><a href="#"><img src="images/delete.png"/></a></td>
                    </tr></table>
                    </td>
                </tr>                
				<tr>
                	<td class="resultsTableEvenRow">2</td>
                	<td class="resultsTableEvenRow">Cristian</td>
                	<td class="resultsTableEvenRow">Nuñez</td>
                	<td class="resultsTableEvenRow"><a href="mailto:cnunezre@gmail.com">cnunezre@gmail.com</a></td>
                	<td class="resultsTableEvenRow" align="center">
                    <table border="0" cellpadding="1" cellspacing="0" colspan="0" cellspan="0"><tr>
                    <td><a href="#"><img src="<s:url value="/images/view.png"/>"/></a></td>
                	<td><a href="#"><img src="<s:url value="/images/edit.png"/>"/></a></td>
                	<td><a href="#"><img src="images/delete.png"/></a></td>
                    </tr></table>
                    </td>
                </tr>                
				<tr>
                	<td class="resultsTableOddRow">3</td>
                	<td class="resultsTableOddRow">Federico</td>
                	<td class="resultsTableOddRow">Barrera Oro</td>
                	<td class="resultsTableOddRow"><a href="mailto:fbarrera@gmail.com">fbarrera@gmail.com</a></td>
                	<td class="resultsTableOddRow" align="center">
                    <table border="0" cellpadding="1" cellspacing="0" colspan="0" cellspan="0"><tr>
                    <td><a href="#"><img src="<s:url value="/images/view.png"/>"/></a></td>
                	<td><a href="#"><img src="<s:url value="/images/edit.png"/>"/></a></td>
                	<td><a href="#"><img src="images/delete.png"/></a></td>
                    </tr></table>
                    </td>
                </tr>                
				<tr>
                	<td class="resultsTableEvenRow">4</td>
                	<td class="resultsTableEvenRow">Ignacio</td>
                	<td class="resultsTableEvenRow">Goris</td>
                	<td class="resultsTableEvenRow"><a href="mailto:ignacio@laborguru.com">ignacio@laborguru.com</a></td>
                	<td class="resultsTableEvenRow" align="center">
                    <table border="0" cellpadding="1" cellspacing="0" colspan="0" cellspan="0"><tr>
                    <td><a href="#"><img src="<s:url value="/images/view.png"/>"/></a></td>
                	<td><a href="#"><img src="<s:url value="/images/edit.png"/>"/></a></td>
                	<td><a href="#"><img src="images/delete.png"/></a></td>
                    </tr></table>
                    </td>
                                    </tr>                
                </table>
              <!-- Search Results -->
              </td>
	        </tr>
        </table>
