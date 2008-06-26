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
						<select>
							<option>Client 1</option>
							<option>Client 2</option>
						</select>                    
                    </td>
                </tr>
              	<tr class="editFormOddRow">
                    <td width="100%" align="right" colspan="4">
	                    <table border="0" cellpadding="1" cellspacing="5" colspan="0" cellspan="0">
		                    <tr>
		                    	<td><s:submit id="cancelButton" key="cancel.button" action="store_list" theme="simple" cssClass="button"/></td>		                    
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
        	  		<table border="0" cellpadding="0" cellspacing="0" colspan="0" cellspan="0" width="100%">
					  <tr>
					    <td width="33%" valign="top">
					    	<!-- Labor Allowances -->
					    		<table id="actionsTable">
					    			<tr class="actionsTableHeader"><td><s:text name="store.laborallowance.title" /></td></tr>
					    			<tr class="actionsTableValue">
						    			<td>
						    				<ul>
						    					<li><a class="actionsLink" href="<s:url value="#" includeParams="none"/>"><s:text name="store.laborallowance.weekdayguestservice.title" /></a></li>
						    					<li><a class="actionsLink" href="<s:url value="#" includeParams="none"/>"><s:text name="store.laborallowance.weekendguestservice.title" /></a></li>
						    					<li><a class="actionsLink" href="<s:url value="#" includeParams="none"/>"><s:text name="store.laborallowance.variableflexible.title" /></a></li>
						    					<li><a class="actionsLink" href="<s:url value="#" includeParams="none"/>"><s:text name="store.laborallowance.variableopening.title" /></a></li>
						    					<li><a class="actionsLink" href="<s:url value="#" includeParams="none"/>"><s:text name="store.laborallowance.fixedflexible.title" /></a></li>
						    					<li><a class="actionsLink" href="<s:url value="#" includeParams="none"/>"><s:text name="store.laborallowance.fixedopening.title" /></a></li>
						    					<li><a class="actionsLink" href="<s:url value="#" includeParams="none"/>"><s:text name="store.laborallowance.fixedpostrush.title" /></a></li>
						    					<li><a class="actionsLink" href="<s:url value="#" includeParams="none"/>"><s:text name="store.laborallowance.fixedclosing.title" /></a></li>
						    				</ul>
						    			</td>
					    			</tr>
					    		</table>
					    	<!-- End Allowances -->
					    </td>
					    <td width="34%" valign="top">
					    	<!-- Labor Assumptions -->
					    		<table id="actionsTable">
					    			<tr class="actionsTableHeader"><td><s:text name="store.laborassumptions.title" /></td></tr>
					    			<tr class="actionsTableValue">
						    			<td>
						    				<ul>
						    					<li><a class="actionsLink" href="<s:url value="#" includeParams="none"/>"><s:text name="store.laborassumptions.maxgsutilization.title" /></a></li>
						    					<li><a class="actionsLink" href="<s:url value="#" includeParams="none"/>"><s:text name="store.laborassumptions.mingsutilization.title" /></a></li>
						    					<li><a class="actionsLink" href="<s:url value="#" includeParams="none"/>"><s:text name="store.laborassumptions.nongsutilization.title" /></a></li>
						    					<li><a class="actionsLink" href="<s:url value="#" includeParams="none"/>"><s:text name="store.laborassumptions.utilizationbottom.title" /></a></li>
						    					<li><a class="actionsLink" href="<s:url value="#" includeParams="none"/>"><s:text name="store.laborassumptions.utilizationtop.title" /></a></li>
						    					<li><a class="actionsLink" href="<s:url value="#" includeParams="none"/>"><s:text name="store.laborassumptions.minimumstaffing.title" /></a></li>
						    					<li><a class="actionsLink" href="<s:url value="#" includeParams="none"/>"><s:text name="store.laborassumptions.activitysharing.title" /></a></li>
						    				</ul>
						    			</td>
					    			</tr>
					    		</table>
					    	<!-- End Labor Assumptions -->
					    </td>
					    <td width="33%" valign="top">
					    	<!-- Store Operations -->
					    		<table id="actionsTable">
					    			<tr class="actionsTableHeader"><td><s:text name="store.storeoperations.title" /></td></tr>
					    			<tr class="actionsTableValue">
						    			<td>
						    				<ul>
						    					<li><a class="actionsLink" href="<s:url value="#" includeParams="none"/>"><s:text name="store.storeoperations.hoursofoperation.title" /></a></li>
						    					<li><a class="actionsLink" href="<s:url value="#" includeParams="none"/>"><s:text name="store.storeoperations.daypartdefinition.title" /></a></li>
						    					<li><a class="actionsLink" href="<s:url value="#" includeParams="none"/>"><s:text name="store.storeoperations.firstdayofweek.title" /></a></li>
						    					<li><a class="actionsLink" href="<s:url value="#" includeParams="none"/>"><s:text name="store.storeoperations.positionnames.title" /></a></li>
						    				</ul>
						    			</td>
					    			</tr>
					    		</table>
					    	<!-- End Store Operations -->
					    </td>
					  </tr>
					</table>
        	  	</td>
        	  </tr>         
          </table>