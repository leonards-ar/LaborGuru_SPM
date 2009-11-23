<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

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
               <s:property value="%{getText(report.manager.sales)}" />
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
              <td class="greCellLabel">
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
                  <!--  s:text name="currency">
                    <s:param value="scheduleMPH" />
                 </s:text-->
                </td>
                <td class="cellValue">0
                  <!--  s:text name="currency">
                    <s:param value="scheduleMPH" />
                 </s:text-->
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