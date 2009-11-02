<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:if test="totalHours != null">
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
              <td class="greyCellLabel">&nbsp;</td>
              <td class="cellLabel">
               <s:property value="%{getText(scheduleHeader)}" />
              </td>
              <td class="cellLabel">
               <s:property value="%{getText(targetHeader)}" />
              </td>
              <td class="greyCellLabel">
               <s:text name="report.historicalComparison.difference.label" />
              </td>
              <td class="cellLabel">
                <s:text name="report.historicalComparison.difference.percentage" />
              </td>
            </tr>
            <s:iterator id="totalManagerHour" value="totalManagerHours" status="itTotalManagerHour">
              <tr>
                <td class="greyCellValue">
                  <s:property value="location.name"/>
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