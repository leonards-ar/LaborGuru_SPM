<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<br />

<table border="0" cellspacing="0" align="center">
  <tr>
    <td class="windowTableHeader">
      <s:text name="report.dailyFlashReport.title" />
    </td>
   </tr>
    <tr>
      <td>
		<table id="windowReportTable" cellspacing="0">
		  <tr>
			<td class="yellowTableHeader"><s:text name="report.dailyFlashReport.daypart.label" /></td>
			<td class="yellowTableHeader"><s:text name="report.dailyFlashReport.time.label" /></td>
			<td class="yellowTableHeader"><s:text name="report.dailyFlashReport.projectedSales.label" /></td>
			<td class="yellowTableHeader"><s:text name="report.dailyFlashReport.projectedSales.cum.label" /></td>
			<td class="yellowTableHeader"><s:text name="report.dailyFlashReport.actualSales.label" /></td>
			<td class="yellowTableHeader"><s:text name="report.dailyFlashReport.actualhour.label" /></td>
			<td class="yellowTableHeader"><s:text name="report.dailyFlashReport.actualSale.cum.label" /></td>
			<td class="yellowTableHeader"><s:text name="report.dailyFlashReport.difference.label" /></td>
			<td class="yellowTableHeader"><s:text name="report.dailyFlashReport.difference.cum.label" /></td>
		  </tr>
 		  <s:iterator id="dailyFlashHour" value="dailyFlashHours" status="itTotalHours">
 		    <tr>
			  <td class="tableValueWithLeftBottomBorder"><s:property value="dayPart.name"/></td>
			  <td class="greyTableValueWithLeftBottomBorder"><s:text name="report.dailyFlashReport.hours.format"><s:param value="day"/></s:text></td>
			  <td class="greyTableValueWithLeftBottomBorder">$<s:text name="currency"><s:param value="sales"/></s:text></td>
			  <td class="greyTableValueWithLeftBottomBorder">$<s:text name="currency"><s:param value="cumulSales"/></s:text></td>
			  <s:if test="actualSale != null">
			  	<td class="greyTableValueWithLeftBottomBorder">$<s:text name="currency"><s:param value="actualSale"/></s:text></td>
			  	<td class="tableValueWithLeftBottomBorder"><s:text name="decimal"><s:param value="actualHour"/></s:text></td>
			  	<td class="tableValueWithLeftBottomBorder">$<s:text name="currency"><s:param value="cumulActualSale"/></s:text></td>
			  	<td class="greyTableValueWithLeftBottomBorder">$<s:property value="formattedDifference"/></td>
			  	<td class="greyTableValueWithLeftBottomBorder">$<s:property value="formattedCumulDifference"/></td>
			  </s:if>
			  <s:else>
			  	<td class="greyTableValueWithLeftBottomBorder">&nbsp;</td>
			  	<td class="tableValueWithLeftBottomBorder">&nbsp;</td>
			  	<td class="tableValueWithLeftBottomBorder">$-</td>
			  	<td class="greyTableValueWithLeftBottomBorder">&nbsp;</td>
			  	<td class="greyTableValueWithLeftBottomBorder">&nbsp;</td>
			  </s:else>
			</tr>
		  </s:iterator>
		  <tr>
		  	<td class="tableValueWithLeftBottomBorder">&nbsp;</td>
		  	<td class="greyTableValueWithLeftBottomBorder"><s:text name="report.dailyFlashReport.total.label"/></td>
		  	<td class="greyTableValueWithLeftBottomBorder">$<s:text name="currency"><s:param value="totalSales"/></s:text></td>
		  	<td class="greyTableValueWithLeftBottomBorder">$<s:text name="currency"><s:param value="totalSales"/></s:text></td>
		  	<td class="greyTableValueWithLeftBottomBorder">$<s:text name="currency"><s:param value="totalActualSales"/></s:text></td>
		  	<td class="greyTableValueWithLeftBottomBorder"><s:text name="decimal"><s:param value="totalPartialLabor"/></s:text></td>
		  	<td class="greyTableValueWithLeftBottomBorder">$<s:text name="currency"><s:param value="totalActualSales"/></s:text></td>
		  	<td class="greyTableValueWithLeftBottomBorder">$<s:property value="totalFormattedDifference"/></td>
		  	<td class="greyTableValueWithLeftBottomBorder">$<s:property value="totalFormattedDifference"/></td>  	
		  </tr>
		</table>
		</td>
	</tr>
	<tr>
	  <td>&nbsp;</td>
	</tr>
	<tr>
		<td>
		<table>
		  <tr>
		    <td>
				<table class="windowReportTable" cellspacing="0" border="1">
				  <tr>
					<td class="tableValueWithBorder"><s:text name="report.dailyFlashReport.forecastChange.label"/></td>
					<td class="greyTableValueWithBorder"><s:text name="percentage"><s:param value="forecast"/></s:text>%</td>
				  </tr>
				  <tr>
					<td class="tableValueWithBorder"><s:text name="report.dailyFlashReport.percentOfDayLeft.label"/></td>
					<td class="greyTableValueWithBorder"><s:text name="percentage"><s:param value="percentOfDayLeft"/></s:text>%</td>
				  </tr>
				</table>
			</td>
			<td>
				<table class="windowReportTable" cellspacing="0" border="1">
				<tr>
					<td>&nbsp;</td>
					<td class="greyTableValueWithBorder" colspan="2"><s:text name="report.dailyFlashReport.soFar.label" /></td>
					<td class="greyTableValueWithBorder" colspan="2"><s:text name="report.dailyflashReport.restOfDay.label" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td class="greyTableValueWithLeftBottomBorder"><s:text name="report.dailyFlashReport.hours.label"/></td>
					<td class="tableValueWithLeftBottomBorder"><s:text name="report.dailyFlashReport.difference.label"/</td>
					<td class="greyTableValueWithLeftBottomBorder"><s:text name="report.dailyFlashReport.hours.label"/</td>
					<td class="tableValueWithLeftBottomBorder"><s:text name="report.dailyFlashReport.difference.label"/</td>
				</tr>
			</td>
		  </tr>
		</table>
	  </td>	
	</tr>
</table>

