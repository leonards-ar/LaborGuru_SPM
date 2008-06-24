<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

			<br/>
	      <table border="0" cellspacing="0" align="center">
		      <tr>
			      <td id="titleBar">
			      <s:if test="store.id == null">
			      	<s:text name="store.create.title" />
			      </s:if>
			      <s:else>
			      	<s:text name="store.update.title" />
			      </s:else>
			      </td>
        	  </tr>
              
              <tr>
              	<td class="errorMessage" align="center">
	              	<table border="0" align="center" cellpadding="0" cellspacing="0" colspan="0" cellspan="0">
              			<tr>
              				<td>
				              	<s:fielderror theme="simple"/>
				              	<s:actionerror theme="simple"/>
			              	</td>
            		  	</tr>
              		</table>
              	</td>
              </tr>
              
              <tr>                            
              <td>              
              <s:form action="store_save" theme="simple">
              <s:hidden name="store.id" theme="simple"/>
              <table id="editFormTable" border="0" cellpadding="6" cellspacing="0" colspan="0" cellspan="0">
              	<tr class="editFormEvenRow">
                    <td width="10%" align="right" class="form_label" nowrap>* <s:text name="store.code.label" /></td>
                    <td width="20%" align="left" class="value"><s:textfield name="store.code" size="10" theme="simple"/></td>
                    <td width="10%" align="right" class="form_label" nowrap>* <s:text name="store.name.label" /></td>
                    <td width="20%" align="left" class="value"><s:textfield name="store.name" size="20" theme="simple"/></td>
                    <td width="10%" align="right" class="form_label" nowrap>* <s:text name="store.office.label" /></td>
                    <td width="20%" align="left" class="value">
                    </td>
                </tr>
              	<tr class="editFormOddRow">
                    <td width="100%" align="right" colspan="4">
	                    <table border="0" cellpadding="1" cellspacing="5" colspan="0" cellspan="0">
		                    <tr>
		                    	<td><s:submit id="cancelButton" key="cancel.button" action="employee_list" theme="simple" cssClass="button"/></td>		                    
		                    	<td><s:reset id="resetButton" key="reset.button" theme="simple" cssClass="button"/></td>
		                		<td><s:submit id="saveButton" key="save.button" theme="simple" cssClass="button"/></td>
		                    </tr>
	                    </table>                    
                    </td>
                </tr>
              </table>
              </s:form>
              </td>
              </tr>
		      <tr>
			      <td class="infoMessage"><s:text name="info.mandatory.fields" /></td>
        	  </tr>	  
        	   
        	  <tr>
        	  	  <td><br/></td>
        	  </tr>  
        	  
        	  <tr>
        	  	<td width="100%">
        	  		<table border="0" cellpadding="0" cellspacing="0" colspan="0" cellspan="0">
					  <tr>
					    <td width="33%">
					    	<!-- Labor Allowances -->
					    		<table id="actionsTable">
					    			<tr class="actionsTableHeader"><td><s:text name="store.laborAllowance.title" /></td></tr>
					    			<tr class="actionsTableValue">
						    			<td>
						    				<ul>
						    					<li><a href="<s:url action="" includeParams="none">"><s:text name="store.laborAllowance.title" /></a></li>
						    				</ul>
						    			</td>
					    			</tr>
					    		</table>
					    	<!-- End Allowances -->
					    </td>
					    <td width="34%">
					    	<!-- Labor Assumptions -->
					    	<!-- End Labor Assumptions -->
					    </td>
					    <td width="33%">
					    	<!-- Store Operations -->
					    	<!-- End Store Operations -->
					    </td>
					  </tr>
					</table>

        	  	</td>
        	  </tr>         
          </table>