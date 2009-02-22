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
													<table border="0" cellpadding="2" cellspacing="1" colspan="0" cellspan="0">
														<tr>
															<td class="windowTableLabel">&nbsp;</td>
															<td class="windowTableLabel"><s:text name="home.week.label"/></td>
															<td class="windowTableLabel"><s:text name="home.volume.label"/></td>
															<td class="windowTableLabel"><s:text name="home.target.label"/></td>
															<td class="windowTableLabel"><s:text name="home.scheduled.label"/></td>
															<td class="windowTableLabel"><s:text name="home.difference.label"/></td>
															<td class="windowTableLabel"><s:text name="home.percentage.label"/></td>
														</tr>
														<tr>
															<td class="windowTableLabel"><s:text name="home.next_week.label"/></td>
															<td class="windowTableValue">12-02-08</td>
															<td class="windowTableValue">12,000</td>
															<td class="windowTableValue">1,353</td>
															<td class="windowTableValue">1,483</td>
															<td class="windowTableValue">130</td>
															<td class="windowTableValue">10%</td>
														</tr>
														<tr>
															<td class="windowTableLabel"><s:text name="home.next_two_weeks.label"/></td>
															<td class="windowTableValue">12-09-08</td>
															<td class="windowTableValue" colspan="5"><span class="infoMessage"><s:text name="home.no_projection.message"/></span></td>
														</tr>
													</table>
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
							<table id="windowTable">
								<tr>
									<td class="windowTableHeader"><s:text name='home.weekday.position.dateformat'><s:param value='%{new java.util.Date()}'/></s:text></td>
									<td class="windowTableHeader"><s:text name="home.scheduled.label"/></td>
									<td class="windowTableHeader"><s:text name="home.target.label"/></td>
									<td class="windowTableHeader"><s:text name="home.difference.label"/></td>
								</tr>
								
								<!--  For each position -->
								<s:iterator value="positions" id="pos">
								<tr>
									<td class="windowTableValue"><s:property value="name"/></td>
									<td class="windowTableValue">0</td>
									<td class="windowTableValue">0</td>
									<td class="windowTableValue">0</td>
								</tr>
								</s:iterator>
								<!-- For each position -->
								
								<tr>
									<td class="windowTableValue"><b><s:text name="home.total.label"/></b></td>
									<td class="windowTableValue"><b>0</b></td>
									<td class="windowTableValue"><b>0</b></td>
									<td class="windowTableValue"><b>0</b></td>
								</tr>
								
							</table>
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
	</script>
