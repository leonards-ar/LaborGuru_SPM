<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:if test="totalHours != null">
	<table border="0" width="100%" cellspacing="0" align="center">
		<tr>
			<td>
			<table border="2" width="100%" cellspacing="0" align="center">
				<tr>
					<td id="titleBar"><s:property value="%{getText(reportTitle)}" />
					</td>
				</tr>

				<tr>
					<td>
					<table border="2" width="100%" cellspacing="0" align="center">
						<tr>
							<td class="windowTableLabel">&nbsp;</td>
							<td class="windowTableLabel"><s:property
								value="%{getText(scheduleHeader)}" /></td>
							<td class="windowTableLabel"><s:property
								value="%{getText(targetHeader)}" /></td>
							<td class="windowTableLabel"><s:text
								name="report.historicalComparison.difference.label" /></td>
							<td class="windowTableLabel">&nbsp;</td>
						</tr>
						<s:iterator id="totalHour" value="totalHours" status="itTotalHour">
							<tr>
								<td class="windowTableValue"><s:text
									name="report.historicalComparison.date">
									<s:param value="day" />
								</s:text></td>
								<td class="windowTableValue"><s:text name="currency">
									<s:param value="schedule" />
								</s:text></td>
								<td class="windowTableValue"><s:text name="currency">
									<s:param value="target" />
								</s:text></td>
								<td class="<s:if test="difference < 0">windowTableNegative</s:if><s:else>windowTableValue</s:else>"><s:text name="currency">
									<s:param value="difference" />
								</s:text></td>
								<td class="windowTableValue"><s:text
									name="report.historicalComparison.percentage">
									<s:param value="percentaje" />
								</s:text></td>
							</tr>
						</s:iterator>
					</table>
					</td>
				</tr>
			</table>
			</td>
			<td>
			<table border="0" width="100%" cellspacing="0" align="center">
				<tr>
					<td></td>
					<td><OBJECT
						classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
						codebase=http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0
						" width="600" height="350" id="Column3D">
						<param name="movie"
							value="<s:url value='/fusionCharts/FCF_MSLine.swf?chartWidth=600&chartHeight=300' includeParams="none"/>" />
						<param name="FlashVars"
							value="&dataXML=<s:property value="xmlValues"/>">
						<param name="quality" value="high" />
						<embed
							src="<s:url value='/fusionCharts/FCF_MSLine.swf?chartWidth=600&chartHeight=300' includeParams="none"/>"
							flashVars="&dataXML=<s:property value="xmlValues"/>"
							quality="high" width="600" height="300" name="Column3D"
							type="application/x-shockwave-flash"
							pluginspage="http://www.macromedia.com/go/getflashplayer" /></object></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:if>