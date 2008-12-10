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
															<td class="windowTableLabel"><s:text name="home.this_week.label"/></td>
															<td class="windowTableValue">11-25-08</td>
															<td class="windowTableValue">12,000</td>
															<td class="windowTableValue">1,353</td>
															<td class="windowTableValue">1,483</td>
															<td class="windowTableValue">130</td>
															<td class="windowTableValue">10%</td>
														</tr>
													</table>
												</td>
											</tr>

											<tr>
												<td class="windowTableTitle"><s:text name="home.previous_week.title"/></td>
											</tr>
											<tr>
												<td>
													<table border="0" cellpadding="2" cellspacing="1" colspan="0" cellspan="0">
														<tr>
															<td class="windowTableLabel">&nbsp;</td>
															<td class="windowTableLabel">&nbsp;</td>
															<td class="windowTableLabel" colspan="3"><s:text name="home.projection_labor.label"/></td>
															<td class="windowTableLabel" colspan="3"><s:text name="home.actual_labor.label"/></td>
														</tr>
														<tr>
															<td class="windowTableLabel">&nbsp;</td>
															<td class="windowTableLabel"><s:text name="home.week.label"/></td>
															<td class="windowTableLabel"><s:text name="home.volume.label"/></td>
															<td class="windowTableLabel"><s:text name="home.target.label"/></td>
															<td class="windowTableLabel"><s:text name="home.scheduled.label"/></td>
															<td class="windowTableLabel"><s:text name="home.volume.label"/></td>
															<td class="windowTableLabel"><s:text name="home.target.label"/></td>
															<td class="windowTableLabel"><s:text name="home.scheduled.label"/></td>
														</tr>
														<tr>
															<td class="windowTableLabel"><s:text name="home.last_week.label"/></td>
															<td class="windowTableValue">11-18-08</td>
															<td class="windowTableValue">12,000</td>
															<td class="windowTableValue">720</td>
															<td class="windowTableValue">750</td>
															<td class="windowTableValue">11,725</td>
															<td class="windowTableValue">716</td>
															<td class="windowTableValue">753</td>
														</tr>
														<tr>
															<td class="windowTableLabel"><s:text name="home.two_weeks.label"/></td>
															<td class="windowTableValue">11-11-08</td>
															<td class="windowTableValue">12,000</td>
															<td class="windowTableValue">730</td>
															<td class="windowTableValue">750</td>
															<td class="windowTableValue">11,725</td>
															<td class="windowTableValue">742</td>
															<td class="windowTableValue">753</td>
														</tr>
														<tr>
															<td class="windowTableLabel">&nbsp;</td>
															<td class="windowTableLabel"><s:text name="home.week.label"/></td>
															<td class="windowTableLabel"><s:text name="home.performance.label"/></td>
															<td class="windowTableLabel"><s:text name="home.scheduled.label"/></td>
															<td class="windowTableLabel"><s:text name="home.projection.label"/></td>
															<td class="windowTableLabel"><s:text name="home.execution.label"/></td>
															<td class="windowTableLabel">&nbsp;</td>
															<td class="windowTableLabel">&nbsp;</td>
														</tr>
														<tr>
															<td class="windowTableLabel"><s:text name="home.last_week.label"/></td>
															<td class="windowTableValue">11-18-08</td>
															<td class="windowTableValue">-15%</td>
															<td class="windowTableValue">-8%</td>
															<td class="windowTableValue">7%</td>
															<td class="windowTableValue">-1%</td>
															<td class="windowTableValue">&nbsp;</td>
															<td class="windowTableValue">&nbsp;</td>
														</tr>
														<tr>
															<td class="windowTableLabel"><s:text name="home.two_weeks.label"/></td>
															<td class="windowTableValue">11-11-08</td>
															<td class="windowTableValue">-20%</td>
															<td class="windowTableValue">-10%</td>
															<td class="windowTableValue">10%</td>
															<td class="windowTableValue">-5%</td>
															<td class="windowTableValue">&nbsp;</td>
															<td class="windowTableValue">&nbsp;</td>
														</tr>
													</table>
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
									<td class="windowTableHeader"><s:text name='home.weekday.dateformat'><s:param value='%{new java.util.Date()}'/></s:text></td>
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

