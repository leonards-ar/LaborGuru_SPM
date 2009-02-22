<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<br />
<br />
	<table border="0" cellspacing="0" align="center">
		<tr>
			<td>
				<!-- Header tables -->
				<table border="0" cellpadding="2" cellspacing="2" width="100%">
					<tr>
						<td valign="top">
							<!-- Left column -->
							<table id="fullHeightTable" border="0" cellpadding="0" cellspacing="0" colspan="0" cellspan="0">
								<tr valign="top">
									<td>
										<table id="windowTable">
											<tr>
												<td class="windowTableHeader"><s:text name="home.performance_summary.title"/></td>
											</tr>
											<tr>
												<td class="windowTableTitle"><s:text name="home.this_week.title"/></td>
											</tr>
											<tr>
												<td>
													<center><img id="currentWeekSummaryIndicator" style="display: none;" src="<s:url value="/images/wait.gif" includeParams="none"/>" alt="<s:text name="wait.message"/>" title="<s:text name="wait.message"/>" border="0"/></center>
													<s:url id="currentWeekSummaryUrl" action="showCurrentWeekSummary" namespace="/home" includeParams="none" />
													<s:div id="currentWeekSummaryFrame" executeScripts="true" theme="ajax" href="%{currentWeekSummaryUrl}" indicator="currentWeekSummaryIndicator" cssClass="waitMessage" loadingText='%{getText("wait.message")}'/>
												</td>
											</tr>

											<tr>
												<td class="windowTableTitle"><s:text name="home.previous_week.title"/></td>
											</tr>
											<tr>
												<td>
													<center><img id="pastWeeksSummaryIndicator" style="display: none;" src="<s:url value="/images/wait.gif" includeParams="none"/>" alt="<s:text name="wait.message"/>" title="<s:text name="wait.message"/>" border="0"/></center>
													<s:url id="pastWeeksSummaryUrl" action="showPastWeeksSummary" namespace="/home" includeParams="none" />
													<s:div id="pastWeeksSummaryFrame" executeScripts="true" theme="ajax" href="%{pastWeeksSummaryUrl}" indicator="pastWeeksSummaryIndicator" cssClass="waitMessage" loadingText='%{getText("wait.message")}'/>
												</td>
											</tr>
											
											<tr>
												<td class="windowTableTitle"><s:text name="home.next_week.title"/></td>
											</tr>
											<tr>
												<td>
													<center><img id="nextWeeksSummaryIndicator" style="display: none;" src="<s:url value="/images/wait.gif" includeParams="none"/>" alt="<s:text name="wait.message"/>" title="<s:text name="wait.message"/>" border="0"/></center>
													<s:url id="nextWeeksSummaryUrl" action="showNextWeeksSummary" namespace="/home" includeParams="none" />
													<s:div id="nextWeeksSummaryFrame" executeScripts="true" theme="ajax" href="%{nextWeeksSummaryUrl}" indicator="nextWeeksSummaryIndicator" cssClass="waitMessage" loadingText='%{getText("wait.message")}'/>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							<!-- Left column -->
						</td>
						
						<td valign="top" align="right">
							<!-- Right column -->
							<center><img id="currentDayPositionSummaryIndicator" style="display: none;" src="<s:url value="/images/wait.gif" includeParams="none"/>" alt="<s:text name="wait.message"/>" title="<s:text name="wait.message"/>" border="0"/></center>
							<s:url id="currentDayPositionSummaryUrl" action="showCurrentDayPositionSummary" namespace="/home" includeParams="none" />
							<s:div id="currentDayPositionSummaryFrame" executeScripts="true" theme="ajax" href="%{currentDayPositionSummaryUrl}" indicator="currentDayPositionSummaryIndicator" cssClass="waitMessage" loadingText='%{getText("wait.message")}'/>
							<!-- Right column -->
						</td>
					</tr>
				</table>
				<!-- Header tables -->
			</td>
		</tr>
	</table>

	<script language="javascript" type="text/javascript">
		djConfig.searchIds.push("currentWeekSummaryFrame");	
		djConfig.searchIds.push("pastWeeksSummaryFrame");	
		djConfig.searchIds.push("nextWeeksSummaryFrame");	
		djConfig.searchIds.push("currentDayPositionSummaryFrame");	
	</script>
