<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

			<br/>
	      <table border="0" cellspacing="0" align="center">
		      <tr>
			      <td class="infoMessage"><s:text name="info.mandatory.fields" /></td>
        	  </tr>			     
		      <tr>
			      <td id="titleBar"><s:text name="employee.create.title" /></td>
        	  </tr>
              
              <tr>
              	<td class="errorMessage" align="center"><s:fielderror theme="simple"/></td>
              </tr>
              
              <tr>                            
              <td>              
              <s:form action="employee_save" theme="simple">
              <s:hidden name="employee.id" theme="simple"/>
              <s:hidden name="employee.store.id" theme="simple"/>              
              <table id="editFormTable" border="0" cellpadding="6" cellspacing="0" colspan="0" cellspan="0">
              	<tr class="editFormEvenRow">
                    <td width="15%" align="right" class="label" nowrap>* <s:text name="employee.firstname.label" /></td>
                    <td width="35%" align="left" class="value"><s:textfield name="employee.name" size="30" theme="simple"/></td>
                    <td width="15%" align="right" class="label" nowrap>* <s:text name="employee.surname.label" /></td>
                    <td width="35%" align="left" class="value"><s:textfield name="employee.surname" size="30" theme="simple"/></td>
                </tr>
              	<tr class="editFormOddRow">
                    <td width="15%" align="right" class="label" nowrap>* <s:text name="employee.username.label" /></td>
                    <td width="35%" align="left" class="value"><s:textfield name="employee.userName" size="30" theme="simple"/></td>
                    <td width="15%" align="right" class="label" nowrap><s:text name="employee.email.label" /></td>
                    <td width="35%" align="left" class="value"><s:textfield name="employee.email" size="30" theme="simple"/></td>
                </tr>
              	<tr class="editFormEvenRow">
                    <td width="15%" align="right" class="label" nowrap><s:text name="employee.phone.label" /></td>
                    <td width="35%" align="left" class="value"><s:textfield name="employee.homePhone" size="30" theme="simple"/></td>
                    <td width="15%" align="right" class="label" nowrap><s:text name="employee.mobile.label" /></td>
                    <td width="35%" align="left" class="value"><s:textfield name="employee.mobilePhone" size="30" theme="simple"/></td>
                </tr>

              	<tr class="editFormOddRow">
                    <td width="15%" align="right" class="label" nowrap><s:text name="employee.employeeid.label" /></td>
                    <td width="35%" align="left" class="value"><s:textfield name="employee.employeeId" size="10" theme="simple"/></td>
                    <td width="15%" align="right" class="label" nowrap><s:text name="employee.wage.label" /></td>
                    <td width="35%" align="left" class="value"><s:textfield name="employee.wage" size="3" theme="simple"/></td>
                </tr>
                
              	<tr class="editFormEvenRow">
              	<td colspan="4" align="center">
					<table border="0" cellpadding="0" cellspacing="0" colspan="0" cellspan="0"><tr>
                    <td align="right" class="label" nowrap><s:text name="employee.maxdaysweek.label" /></td>
                    <td><img src="<s:url value="/images/transp6x1.gif"/>"/></td>
                    <td align="left" class="value"><s:textfield name="employee.maxDaysWeek" size="2" theme="simple"/></td>
                    <td><img src="<s:url value="/images/transp6x1.gif"/>"/></td>
                    <td align="right" class="label" nowrap><s:text name="employee.maxhoursday.label" /></td>
                    <td><img src="<s:url value="/images/transp6x1.gif"/>"/></td>
                    <td align="left" class="value"><s:textfield name="employee.maxHoursDay" size="2" theme="simple"/></td>
                    <td><img src="<s:url value="/images/transp6x1.gif"/>"/></td>
                    <td align="right" class="label" nowrap><s:text name="employee.maxhoursweek.label" /></td>
                    <td><img src="<s:url value="/images/transp6x1.gif"/>"/></td>
                    <td align="left" class="value"><s:textfield name="employee.maxHoursWeek" size="3" theme="simple"/></td>
					</tr></table>
              	</td>
                </tr>
              	<tr class="editFormOddRow">
                    <td width="15%" align="right" class="label" nowrap><s:text name="employee.defaultposition.label" /></td>
                    <td width="35%" align="left" class="value">
                    <s:select name="employee.defaultPosition.id" list="positions" listKey="id" listValue="name" theme="simple"/>
                    </td>
                    <td width="15%" align="right" class="label" nowrap><s:text name="employee.status.label" /></td>
                    <td width="35%" align="left" class="value">
                    <s:select name="employee.status" list="statusList" listKey="key" listValue="value" theme="simple"/>
                    </td>
                </tr>
              	<tr class="editFormEvenRow">
                    <td width="15%" align="right" class="label" nowrap><s:text name="employee.ismanager.label" /></td>
                    <td width="35%" align="left" class="value"><s:checkbox name="employee.manager" fieldValue="true" theme="simple"/></td>
                    <td width="15%" align="right" class="label" nowrap><s:text name="employee.hiredate.label" /></td>
                    <td width="35%" align="left" class="value"><s:textfield  name="employee.hireDate" size="30" theme="simple"/></td>
                </tr>

              	<tr class="editFormOddRow">
                    <td width="15%" align="right" class="label" nowrap><s:text name="employee.address.label" /></td>
                    <td width="35%" align="left" class="value"><s:textarea name="employee.address" cols="40" rows="2" theme="simple"/></td>
                    <td width="15%" align="right" class="label" nowrap>&nbsp;</td>
                    <td width="35%" align="left" class="value">&nbsp;</td>
                </tr>
              	<tr class="editFormEvenRow">
                    <td width="15%" align="right" class="label" nowrap><s:text name="employee.address2.label" /></td>
                    <td width="35%" align="left" class="value"><s:textarea name="employee.address2" cols="40" rows="2" theme="simple"/></td>
                    <td width="15%" align="right" class="label" nowrap>&nbsp;</td>
                    <td width="35%" align="left" class="value">&nbsp;</td>
                </tr>
              	<tr class="editFormOddRow">
                    <td width="15%" align="right" class="label" nowrap><s:text name="employee.city.label" /></td>
              	<td width="35%" align="left" class="value"><s:textfield name="employee.city" size="30" theme="simple"/></td>
                    <td width="15%" align="right" class="label" nowrap><s:text name="employee.state.label" /></td>
              	<td width="35%" align="left">
					<table border="0" cellpadding="0" cellspacing="0" colspan="0" cellspan="0"><tr>
                    <td align="left" class="value"><s:textfield name="employee.state" size="20" theme="simple"/></td>
                    <td><img src="<s:url value="/images/transp6x1.gif"/>"/></td>
                    <td><img src="<s:url value="/images/transp6x1.gif"/>"/></td>
                    <td align="right" class="label" nowrap><s:text name="employee.zip.label" /></td>
                    <td><img src="<s:url value="/images/transp6x1.gif"/>"/></td>
                    <td><img src="<s:url value="/images/transp6x1.gif"/>"/></td>
                    <td align="left" class="value"><s:textfield name="employee.zip" size="5" theme="simple"/></td>
					</tr></table>
              	</td>
              	
                </tr>                
              	<tr class="editFormEvenRow">
                    <td width="15%" align="right" class="label" nowrap><s:text name="employee.comments.label" /></td>
                    <td width="35%" align="left" class="value"><s:textarea name="employee.comments" cols="40" rows="2" theme="simple"/></td>
                    <td width="15%" align="right" class="label" nowrap>&nbsp;</td>
                    <td width="35%" align="left" class="value">&nbsp;</td>
                </tr>
              	<tr class="editFormOddRow">
                    <td width="100%" align="right" colspan="4">
	                    <table border="0" cellpadding="1" cellspacing="5" colspan="0" cellspan="0">
		                    <tr>
		                    	<td><s:submit key="cancel.button" action="employee_list" theme="simple" cssClass="button"/></td>		                    
		                    	<td><s:reset key="reset.button" theme="simple" cssClass="button"/></td>
		                		<td><s:submit key="save.button" theme="simple" cssClass="button"/></td>
		                    </tr>
	                    </table>                    
                    </td>
                </tr>
              </table>
              </s:form>
              </td>
              </tr>
          </table>