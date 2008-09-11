<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<table>
	<tr>
		<td id="titleBar"><s:text name="schedule.addShift.title" /></td>
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

	<table align="center" width="100%" border="0" cellpadding="0"
		cellspacing="0" colspan="0" cellspan="0">

		<tr>
			<td width="70%">
			<table align="center" width="100%" border="0" cellpadding="0" cellspacing="0" colspan="0" cellspan="0">
				<tr>
					<td id="titleBar2" colspan="6"><s:text name="schedule.addShift.projection"/></td>
				</tr>
				<tr>
					<td><s:text name="schedule.addShift.sales"/></td>
					<td><s:text name="schedule.addShift.checks"/></td>
					<td><s:text name="schedule.addShift.scheduled"/></td>
					<td><s:text name="schedule.addShift.projected"/></td>
					<td><s:text name="schedule.addShift.delta"/></td>
					<td><s:text name="schedule.addShift.efficiency"/></td>
				</tr>
				<tr>
					<td>$2600</td>
					<td><a href="#">805</a></td>
					<td>65</td>
					<td><a href="#">62</a></td>
					<td>+2</td>
					<td><a href="#">95%</a></td>
				</tr>
			</table>
			<table align="center" width="100%" border="0" cellpadding="'" cellspacing="0" colspan="0">
				<tr>
					<td><s:text name="schedule.addShift.view"/></td>
					<td>
						<select name="selectView">
							<option>--- Select a Weekly ---</option>
						</select>
					</td>
					<td><s:text name="schedule.addShift.positions"/></td>
					<td>						
						<select name="selectPositions">
							<option>--- All Positions ---</option>
						</select>
					</td>
				</tr>
			</table>
			</td>
			<td width="30%"></td>
		</tr>
		<tr>
			<td colspan="2"></td>
		</tr>
	</table>
</table>
