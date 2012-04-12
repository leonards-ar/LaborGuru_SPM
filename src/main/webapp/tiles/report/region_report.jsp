<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<br />
<table border="0" cellspacing="0" align="center">
  <tr>
    <td id="titleBar"><s:text
      name="report.regional.title" /></td>
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
    <s:form id="regionalReport_form" name="regionalReport_form" theme="simple" method="post">
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
            <s:submit id="submit" onclick="return showWaitSplash();" key="report.historicalComparison.submit.label" action="regionalReport_showReport" theme="simple" cssClass="button"/>
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
                        <td class="cellLabel" <s:if test='selectView != "forecastEfficiency"'>colspan="2"</s:if>>&nbsp;</td>
                        <td class="greyCellLabel" colspan="4">
                          <s:if test='selectView != "forecastEfficiency"'>
                           <s:text name="report.manager.laborHours"/>
                          </s:if>
                          <s:else>
                           <s:text name="report.manager.sales"/>
                          </s:else>
                        </td>
                       <s:if test='selectView != "forecastEfficiency"'>
                        <td class="cellLabel" colspan="2"><s:text name="report.manager.variable1PMH"/></td>
                        <td class="greyCellLabel" colspan="2"><s:text name="report.manager.laborPercentage"/></td>
                       </s:if>
                      </tr>
                      <tr>
                        <td class="cellLabel"><s:text name="report.manager.area"/></td>
                        <s:if test='selectView != "forecastEfficiency"'>
                          <td class="greyCellLabel">
                           <s:text name="report.manager.sales" />
                          </td>
                        </s:if>
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
                        <s:if test='selectView != "forecastEfficiency"'>
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
                        </s:if>
                      </tr>
                      <s:iterator id="totalManagerHour" value="totalManagerHours" status="itTotalManagerHour">
                        <tr>
                          <td class="cellValue">
                            <s:property value="area.name"/>
                          </td>
                        <s:if test='selectView != "forecastEfficiency"'>
                          <td class="greyCellValue">
                            <s:text name="currency">
                              <s:param value="sales" />
                           </s:text>
                          </td>
                        </s:if>
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
                          <s:if test='selectView != "forecastEfficiency"'>
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
                          </s:if>
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
