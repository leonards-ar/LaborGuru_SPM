<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

			<br/>
	      <table border="0" cellspacing="0" align="center">
		      <tr>
			      <td>&nbsp;</td>
        	  </tr>
		      <tr>
			      <td id="titleBar">View employee</td>
			      <!--td id="titleBar">Edit employee Mariano Capurro</td-->
        	  </tr>
              <tr>
              <td>
              <table id="editFormTable" border="0" cellpadding="6" cellspacing="0" colspan="0" cellspan="0">
              	<tr class="editFormEvenRow">
                    <td width="15%" align="right" class="label" nowrap>First Name</td>
                    <td width="35%" align="left" class="value"><s:property value="employee.name"/></td>
                    <td width="15%" align="right" class="label" nowrap>Last Name</td>
                    <td width="35%" align="left" class="value"><s:property value="employee.surname"/></td>
                </tr>
              	<tr class="editFormOddRow">
                    <td width="15%" align="right" class="label" nowrap>Username</td>
                    <td width="35%" align="left" class="value"><s:property value="employee.userName"/></td>
                    <td width="15%" align="right" class="label" nowrap>Email</td>
                    <td width="35%" align="left" class="value"><s:property value="employee.email"/></td>
                </tr>
              	<tr class="editFormEvenRow">
                    <td width="15%" align="right" class="label" nowrap>Max Hours Day</td>
                    <td width="35%" align="left" class="value"><s:property value="employee.maxHoursDay"/></td>
                    <td width="15%" align="right" class="label" nowrap>Max Hours Week</td>
                    <td width="35%" align="left" class="value"><s:property value="employee.maxHoursWeek"/></td>
                </tr>
              	<tr class="editFormOddRow">
                    <td width="15%" align="right" class="label" nowrap>Default Position</td>
                    <td width="35%" align="left" class="value"><s:property value="employee.defaultPosition.name"/></td>
                    <td width="15%" align="right" class="label" nowrap>Status</td>
                    <td width="35%" align="left" class="value"><s:property value="employee.status"/></td>
                </tr>
              	<tr class="editFormEvenRow">
                    <td width="15%" align="right" class="label" nowrap>Manager</td>
                    <td width="35%" align="left" class="value"><s:property value="employee.manager"/></td>
                    <td width="15%" align="right" class="label" nowrap>Hire Date</td>
                    <td width="35%" align="left" class="value"><s:property value="employee.hireDate"/></td>
                </tr>
              	<tr class="editFormOddRow">
                    <td width="15%" align="right" class="label" nowrap>State</td>
                    <td width="35%" align="left" class="value"><s:property value="employee.state" default="######"/></td>
                    <td width="15%" align="right" class="label" nowrap>City</td>
                    <td width="35%" align="left" class="value"><s:property value="employee.city"/></td>
                </tr>
              	<tr class="editFormEvenRow">
                    <td width="15%" align="right" class="label" nowrap>Address</td>
                    <td width="35%" align="left" class="value"><s:property value="employee.address"/></td>
                    <td width="15%" align="right" class="label" nowrap>&nbsp;</td>
                    <td width="35%" align="left" class="value">&nbsp;</td>
                </tr>
              	<tr class="editFormOddRow">
                    <td width="15%" align="right" class="label" nowrap>Address 2</td>
                    <td width="35%" align="left" class="value"><s:property value="employee.address2"/></td>
                    <td width="15%" align="right" class="label" nowrap>Zip</td>
                    <td width="35%" align="left" class="value"><s:property value="employee.zip"/></td>
                </tr>
              	<tr class="editFormEvenRow">
                    <td width="15%" align="right" class="label" nowrap>Phone</td>
                    <td width="35%" align="left" class="value"><s:property value="employee.homePhone"/></td>
                    <td width="15%" align="right" class="label" nowrap>Mobile</td>
                    <td width="35%" align="left" class="value"><s:property value="employee.mobilePhone"/></td>
                </tr>
              	<tr class="editFormOddRow">
                    <td width="15%" align="right" class="label" nowrap>Comments</td>
                    <td width="85%" align="left" class="value" colspan="3"><s:property value="employee.comments"/></td>
                </tr>
              	<tr class="editFormEvenRow">
                    <td width="100%" align="right" colspan="4">
                    <s:form theme="simple"> 
                    <s:hidden name="employee.id"/>
                    <table border="0" cellpadding="1" cellspacing="5" colspan="0" cellspan="0"><tr>                  
                    <td>
                    	<s:if test="removePage">
                    		<s:submit action="employee_list" value="Cancel" theme="simple" cssClass="button"/>                    		
                  			<s:submit action="employee_delete" value="Remove" theme="simple" cssClass="button"/>
                    	</s:if>
                    	<s:else>
                    		<s:submit action="employee_list" value="Back" theme="simple" cssClass="button"/>
                    	</s:else>                    
                    </td>
                    </tr></table>
                   	</s:form>                    
                    </td>
                </tr>
              </table>
              </td>
              </tr>
          </table>
