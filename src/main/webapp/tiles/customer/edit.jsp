<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

			<br/>
	      <table border="0" cellspacing="0" align="center">
		      <tr>
			      <td id="titleBar">
			      <s:if test="customer.id == null">
			      	<s:text name="customer.create.title" />
			      </s:if>
			      <s:else>
			      	<s:text name="customer.update.title" />
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
              <td align="center">              
              <s:form name="customer_form" id="customer_form" action="customer_save" theme="simple">
              <s:hidden name="customer.id" theme="simple"/>
              <table id="editFormTable" border="0" cellpadding="6" cellspacing="0" colspan="0" cellspan="0" align="center">
              	<tr class="editFormOddRow">
                    <td align="right" class="form_label" nowrap>* <s:text name="customer.name.label" /></td>
                    <td align="left" class="value"><s:textfield name="customer.name" size="30" theme="simple"/></td>
				</tr>
                                  	
              	<tr class="editFormEvenRow">
					<table border="0" cellpadding="0" cellspacing="0" colspan="0" cellspan="0">
						<tr>
							<td id="subtitleBar" nowrap><s:text name="customer.regions.title" /></td>
						</tr>
						<tr>
							<td align="center">
							<table id="editFormTable" border="0" cellpadding="6" cellspacing="0" colspan="0" cellspan="0" align="center">
								<tr class="editorTableHeader">
									<td colspan="2"><s:text name="customer.region.label" /></td>
								</tr>
				
								<s:iterator id="region" value="regions" status="itRegion">
									<tr class="editorTable<s:if test="#itRegion.even">Even</s:if><s:else>Odd</s:else>Row">
										<td class="value">
											<s:hidden name="regions[%{#itRegion.index}].id"/>
											<s:textfield name="regions[%{#itRegion.index}].name" value="%{name}" size="30" maxlength="150" theme="simple" />
										</td>
										<td>
											<table order="0" cellpadding="0" cellspacing="0" colspan="0" cellspan="0">
												<tr>
													<td>
														<a href="<s:url value="#" includeParams="none"/>" onclick="customer_form.action='customer_removeRegion.action?index=<s:property value='#itRegion.index'/>'; customer_form.submit();">
															<img src="<s:url value="/images/delete.png" includeParams="none"/>" />
														</a>
													</td>
													<td>
														<a href="<s:url value="#" includeParams="none"/>" onclick="customer_form.action='customer_editRegion.action?index=<s:property value='#itRegion.index'/>'; customer_form.submit();">
															<img src="<s:url value="/images/edit.png" includeParams="none"/>" />
														</a>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</s:iterator>
									<tr class="editorTableEvenRow">
										<td class="value">
											<s:textfield name="newRegionName" size="25" maxlength="150" theme="simple" />
										</td>
										<td>
											<table order="0" cellpadding="0" cellspacing="0" colspan="0" cellspan="0">
												<tr>
													<td>
														<a href="<s:url value="#" includeParams="none"/>" onclick="customer_form.action='customer_addRegion.action'; customer_form.submit();"><img
														src="<s:url value="/images/add.png" includeParams="none"/>" /></a>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
                </tr>
                
              	<tr class="editFormOddRow">
                    <td width="100%" align="right" colspan="2">
	                    <table border="0" cellpadding="1" cellspacing="5" colspan="0" cellspan="0">
		                    <tr>
		                		<td><s:submit id="saveButton" key="save.button" theme="simple" cssClass="button"/></td>
		                    	<td><s:submit id="cancelButton" key="cancel.button" action="customer_list" theme="simple" cssClass="button"/></td>		                    
		                    	<td><s:reset id="resetButton" key="reset.button" theme="simple" cssClass="button"/></td>
		                    </tr>
	                    </table>                    
                    </td>
                </tr>

		      <tr>
			      <td colspan="2" class="infoMessage"><s:text name="info.mandatory.fields" /></td>
        	  </tr>	  

              </table>
              </s:form>

              </td>
              </tr>
          </table>