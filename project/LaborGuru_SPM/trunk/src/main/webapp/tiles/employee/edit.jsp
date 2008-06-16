<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

			<br/>
	      <table border="0" cellspacing="0" align="center">
		      <tr>
			      <td class="infoMessage">Mandatory fields are marked with *.</td>
        	  </tr>
		      <tr>
			      <td id="titleBar">Edit new employee</td>
			      <!--td id="titleBar">Edit employee Mariano Capurro</td-->
        	  </tr>
              <tr>
              <td>
              <form name="editEmployeeForm">
              <table id="editFormTable" border="0" cellpadding="6" cellspacing="0" colspan="0" cellspan="0">
              	<tr class="editFormEvenRow">
                    <td width="15%" align="right" class="label" nowrap>* First Name</td>
                    <td width="35%" align="left" class="value"><input type="text" size="30"/></td>
                    <td width="15%" align="right" class="label" nowrap>* Last Name</td>
                    <td width="35%" align="left" class="value"><input type="text" size="30"/></td>
                </tr>
              	<tr class="editFormOddRow">
                    <td width="15%" align="right" class="label" nowrap>* User Name</td>
                    <td width="35%" align="left" class="value"><input type="text"/></td>
                    <td width="15%" align="right" class="label" nowrap>Email</td>
                    <td width="35%" align="left" class="value"><input type="text"/></td>
                </tr>
              	<tr class="editFormEvenRow">
                    <td width="15%" align="right" class="label" nowrap>Max Hours Day</td>
                    <td width="35%" align="left" class="value"><input type="text" size="2"/></td>
                    <td width="15%" align="right" class="label" nowrap>Max Hours Week</td>
                    <td width="35%" align="left" class="value"><input type="text" size="3"/></td>
                </tr>
              	<tr class="editFormOddRow">
                    <td width="15%" align="right" class="label" nowrap>Default Position</td>
                    <td width="35%" align="left" class="value">
                    <select>
                    <option value="1">Cook</option>
                    <option value="2">Waiter</option>
                    <option value="3">Position A</option>
                    <option value="4">Position B</option>
                    </select>
                    </td>
                    <td width="15%" align="right" class="label" nowrap>Status</td>
                    <td width="35%" align="left" class="value">
                    <select>
                    <option value="e">Enabled</option>
                    <option value="d">Disabled</option>
                    </select>
                    </td>
                </tr>
              	<tr class="editFormEvenRow">
                    <td width="15%" align="right" class="label" nowrap>Manager</td>
                    <td width="35%" align="left" class="value"><input type="checkbox"/></td>
                    <td width="15%" align="right" class="label" nowrap>Hire Date</td>
                    <td width="35%" align="left" class="value"><input type="text" size="10"/></td>
                </tr>
              	<tr class="editFormOddRow">
                    <td width="15%" align="right" class="label" nowrap>State</td>
                    <td width="35%" align="left" class="value"><input type="text"/></td>
                    <td width="15%" align="right" class="label" nowrap>City</td>
                    <td width="35%" align="left" class="value"><input type="text"/></td>
                </tr>
              	<tr class="editFormEvenRow">
                    <td width="15%" align="right" class="label" nowrap>Address</td>
                    <td width="35%" align="left" class="value"><textarea cols="40" rows="2"></textarea></td>
                    <td width="15%" align="right" class="label" nowrap>&nbsp;</td>
                    <td width="35%" align="left" class="value">&nbsp;</td>
                </tr>
              	<tr class="editFormOddRow">
                    <td width="15%" align="right" class="label" nowrap>Address 2</td>
                    <td width="35%" align="left" class="value"><textarea cols="40" rows="2"></textarea></td>
                    <td width="15%" align="right" class="label" nowrap>Zip</td>
                    <td width="35%" align="left" class="value"><input type="text"/></td>
                </tr>
              	<tr class="editFormEvenRow">
                    <td width="15%" align="right" class="label" nowrap>Phone</td>
                    <td width="35%" align="left" class="value"><input type="text"/></td>
                    <td width="15%" align="right" class="label" nowrap>Mobile</td>
                    <td width="35%" align="left" class="value"><input type="text"/></td>
                </tr>
              	<tr class="editFormOddRow">
                    <td width="15%" align="right" class="label" nowrap>Comments</td>
                    <td width="35%" align="left" class="value"><textarea cols="40" rows="3"></textarea></td>
                    <td width="15%" align="right" class="label" nowrap>&nbsp;</td>
                    <td width="35%" align="left" class="value">&nbsp;</td>
                </tr>
              	<tr class="editFormEvenRow">
                    <td width="100%" align="right" colspan="4">
                    <table border="0" cellpadding="1" cellspacing="5" colspan="0" cellspan="0"><tr>
                    <td><input type="submit" value="Cancel" class="button"/></td>
                	<td><input type="submit" value="Save" class="button"/></td>
                    </tr></table>
                    
                    </td>
                </tr>
              </table>
              </form>
              </td>
              </tr>
          </table>