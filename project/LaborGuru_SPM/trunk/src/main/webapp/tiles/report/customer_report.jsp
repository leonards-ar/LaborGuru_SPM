<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<br />
<table border="0" cellspacing="0" align="center">
  <tr>
    <td id="titleBar"><s:text
      name="report.customer.title" /></td>
  </tr>
  <tr>
    <td class="errorMessage" align="center">
    <table border="0" align="center" cellpadding="0" cellspacing="0"
      colspan="0" cellspan="0">
      <tr>
        <td>
          <s:fielderror theme="simple" /> 
          <s:actionerror theme="simple" />
        </td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td align="center">
    <table border="0" cellspacing="0" align="center">
    <s:form id="customerReport_form" name="customerReport_form" action="customerReport_showReport" theme="simple" method="post">
      <tr>
        <td>
	          <table border="0" cellspacing="0" align="center">
	            <tr>
	              <td align="right" class="form_label">
	                <s:text name="report.reportType.label"/>
                </td>
	              <td align="left">
	                <s:select name="selectView" list="reportTypes" listKey="name" listValue="%{getText(title)}" theme="simple" />
	              </td>
	              <td align="right" class="form_label">
	                <s:text name="report.historicalComparison.startDate.label" />
	              </td>
	              <td>
	                <s:datetimepicker id="startDate" name="startDate" adjustWeeks="true" theme="simple" />
	              </td>
	              <td align="right" class="form_label">
	                <s:text name="report.historicalComparison.endDate.label" />
	              </td>
	              <td>
	                <s:datetimepicker id="endDate" name="endDate" adjustWeeks="true" theme="simple" />
	              </td>
	            </tr>
	          </table>
          </td>
        </tr>
        <tr>
          <td align="right">
            <s:submit id="submit" key="report.historicalComparison.submit.label" cssClass="button" loadingText='%{getText("wait.message")}' title="wait.message" type="button" onclick="return showWaitSplash();" theme="ajax"/>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>
					<s:if test="totalManagerHours != null">
					  <table border="0" width="100%" cellspacing="0" align="center">
					    <tr>
					      <td>
					      <table id="windowReportTable" cellspacing="0">
					        <tr>
					          <td id="titleBar"><s:property value="%{getText(reportTitle)}" />
					          </td>
					        </tr>
					        <tr>
					          <td>
					          <table  border="0" width="100%" cellspacing="0" align="center">
					            <tr>
					              <td class="cellLabel" colspan=2>&nbsp;</td>
					              <td class="greyCellLabel" colspan="4"><s:text name="report.manager.laborHours"/></td>
					              <td class=cellLabel colspan="2"><s:text name="report.manager.variable1PMH"/></td>
					              <td class="greyCellLabel" colspan="2"><s:text name="report.manager.laborPercentage"/></td>
					            </tr>
					            <tr>
					              <td class="cellLabel"><s:text name="report.manager.region"/></td>
					              <td class="greyCellLabel">
					               <s:text name="report.manager.sales" />
					              </td>
					              <td class="cellLabel">
					               <s:property value="%{getText(scheduleHeader)}" />
					              </td>
					              <td class="cellLabel">
					               <s:property value="%{getText(targetHeader)}" />
					              </td>
					              <td class="cellLabel">
					               <s:text name="report.manager.difference.label" />
					              </td>
					              <td class="cellLabel">
					                <s:text name="report.manager.difference.percentage" />
					              </td>
					              <td class="greyCellLabel">
					               <s:property value="%{getText(scheduleHeader)}" />
					              </td>
					              <td class="greyCellLabel">
					               <s:property value="%{getText(targetHeader)}" />
					              </td>
					              <td class="cellLabel">
					               <s:property value="%{getText(scheduleHeader)}" />
					              </td>
					              <td class="cellLabel">
					               <s:property value="%{getText(targetHeader)}" />
					              </td>
					            </tr>
					            <s:iterator id="totalManagerHour" value="totalManagerHours" status="itTotalManagerHour">
					              <tr>
					                <td class="cellValue">
					                  <s:property value="region.name"/>
					                </td>
					                <td class="greyCellValue">
					                  <s:text name="currency">
					                    <s:param value="sales" />
					                 </s:text>
					                </td>
					                <td class="cellValue">
					                  <s:text name="currency">
					                    <s:param value="schedule" />
					                 </s:text>
					                </td>
					                <td class="cellValue">
					                  <s:text name="currency">
					                   <s:param value="target" />
					                  </s:text>
					                </td>
					                <td class="greyCellValue">
					                  <s:text name="currency">
					                   <s:param value="difference" />
					                  </s:text>
					                </td>
					                <td class="cellValue">
					                  <s:text name="report.percentage">
					                   <s:param value="percentage" />
					                  </s:text>
					                </td>
					                <td class="greyCellValue">
					                  <s:text name="currency">
					                    <s:param value="scheduleMPH" />
					                 </s:text>
					                </td>                
					                <td class="greyCellValue">
					                  <s:text name="currency">
					                    <s:param value="targetMPH" />
					                 </s:text>
					                </td>
					                <td class="cellValue">0
					                </td>
					                <td class="cellValue">0
					                </td>
					              </tr>
					            </s:iterator>
					          </table>
					          </td>
					        </tr>
					      </table>
					      </td>
					    </tr>
					  </table>
					</s:if>
         </td>
        </tr>
      </s:form>        
     </table>
    </td>
	  </tr>
  </table>
