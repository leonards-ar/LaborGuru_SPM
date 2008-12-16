<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script language="javascript" type="text/javascript">
	

	controller = {
			refresh : function(){
					}
	 	};
	 
	dojo.event.topic.registerPublisher("/refresh", controller, "refresh");


	djConfig.searchIds.push("reportFrame");
				
</script>
<s:url id="totalHourReportUrl"
	value="/report/totalHoursReport_showReport.action" includeParams="none"/>


<s:div id="reportFrame" theme="ajax" 
			href="%{totalHourReportUrl}" 
			formId="report_form"
			listenTopics="/refresh"
			separateScripts="true"
			indicator="indicator" />

<img id="indicator"
	src="<s:url value="/images/indicator.gif" includeParams="none" />"
	alt="<s:text name="wait.message"/>" style="display: none" />
