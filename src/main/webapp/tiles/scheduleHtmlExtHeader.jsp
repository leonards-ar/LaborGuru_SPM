<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="s" uri="/struts-tags" %> 

<s:head theme="ajax"/>

<style type="text/css">
	.dojoComboBoxItem
	{
	    font-size: 11px;
	}

</style>

<link rel="stylesheet" href="<s:url value="/css/schedule_style.css" includeParams="none" />" type="text/css" charset="utf-8" />

<script language="javascript" type="text/javascript">
/*
   Possible Actions are:
     1: set working time
	 2: set break
	 3: delete
*/
var scheduleAction = 1;

/*
   Possible States are:
     1: Ready to set working time
	 2: Setting working time
	 3: Ready to set break
	 4: Setting break
	 5: Ready to delete
	 6: Deleting
*/
var scheduleState = 1;

var fromColumn;
var toColumn;

var currentRow;
var currentScheduleId;
var previousClassName;
var onMouseOutEnabled = true;

var TOTAL_COLS = 0;
var TOTAL_ROWS = 0;
var TIME_INTERVAL = 15;
var BREAK = null;
var CANNOT_CHANGE_ROW_MSG = null;
var END_TIME_MSG = null;
var START_TIME_MSG = null;

function initialize(totalCols, totalRows, breakTxt, cannotChangeRowMsg, startTimeMsg, endTimeMsg) {
	TOTAL_COLS = totalCols;
	TOTAL_ROWS = totalRows;
	BREAK = breakTxt;
	CANNOT_CHANGE_ROW_MSG = cannotChangeRowMsg;
	START_TIME_MSG = startTimeMsg;
	END_TIME_MSG = endTimeMsg;
}

function getObjectByID(objectId) {
	if(document.getElementById) {
		return document.getElementById(objectId);
	} else {
		return null;
	}
}

function scheduleClick(tdObj, rowNum, colNum, defaultPositionId, scheduleId)
{
	setMessage('', scheduleId);
	if(scheduleState == 1 || scheduleState == 3 || scheduleState == 5) {
		currentScheduleId = scheduleId;
		currentRow = rowNum;
		fromColumn = colNum;
		toColumn = null;
		tdObj.className = 'scheduleStartSelection';
		onMouseOutEnabled = false;
		scheduleState += 1;
		setMessage(END_TIME_MSG, scheduleId);
	} else if(scheduleState == 2 || scheduleState == 4 || scheduleState == 6) {
		if(currentRow != rowNum || currentScheduleId != scheduleId) {
			setMessage(CANNOT_CHANGE_ROW_MSG, scheduleId);
		} else {
			tdObj.className = 'scheduleEndSelection';
			if(colNum < fromColumn) {
				toColumn = fromColumn;
				fromColumn = colNum;
			} else {
				toColumn = colNum;
			}
			
			for(var i = fromColumn; i <= toColumn; i++) {
				var cell = getObjectByID(scheduleId + 'cell_' + currentRow + '_' + i);
				cell.className = getClassName();
				var inputObj = getObjectByID(scheduleId + 'schedule_' + rowNum + '_' + i);
				if(scheduleState == 2) {
					var positionId = getPositionId(rowNum, scheduleId);
					if(positionId) {
						inputObj.value = positionId;
					} else {
						inputObj.value = defaultPositionId;
					}
				} else if(scheduleState == 4) {
					inputObj.value = BREAK;
				} else {
					inputObj.value = '';
				}
				
			}
			refreshRow(rowNum, scheduleId);

			fromColumn = null;
			toColumn = null;
			onMouseOutEnabled = false;

			changeAction(scheduleState - 1, scheduleId);
		}
	}
	
}

function getClassName() {
	if(scheduleState == 2) {
		return 'scheduleSelected';
	} else if(scheduleState == 4) {
		return 'scheduleBreak';
	} else if(scheduleState == 6) {
		return 'scheduleEmpty';
	}
}

function changeAction(actionCode, scheduleId)
{
	if(fromColumn != null && scheduleState != actionCode) {
		var cell = getObjectByID(scheduleId + 'cell_' + currentRow + '_' + fromColumn);
		cell.className = 'scheduleEmpty';
	}
	fromColumn = null;
	toColumn = null;
	scheduleState = actionCode;
	setMessage(START_TIME_MSG, scheduleId);
}

function setMessage(msgTxt, scheduleId) {
	var msg = getObjectByID(scheduleId + 'actionMessage');
	if(msg) {
		msg.innerHTML = msgTxt;
	}
}

function scheduleOnMouseOver(tdObj) {
	previousClassName = tdObj.className;
	tdObj.className = 'scheduleSelectingOver';
}

function scheduleOnMouseOut(tdObj) {
	if(onMouseOutEnabled) {
		tdObj.className = previousClassName;
	}
	onMouseOutEnabled = true;
	previousColor = null;
}

function getHours(time) {
	var i = time.indexOf(':');
	if(i >= 0) {
		return time.substring(0, i);
	} else {
		return time.substring(0, 2);
	}
}

function getMinutes(time) {
	var i = time.indexOf(':');
	if(i >= 0) {
		return time.substring(i + 1, time.length);
	} else {
		return time.substring(2, 4);
	}
}

function integerDivision(numerator, denominator) {
    var remainder = numerator % denominator;
    var quotient = ( numerator - remainder ) / denominator;

    if ( quotient >= 0 )
        quotient = Math.floor( quotient );
    else  // negative
        quotient = Math.ceil( quotient );

	return quotient;
}


function timeInMinutes(time) {
	if(time != null && time != '') {
		return parseInt(getHours(time)) * 60 + parseInt(getMinutes(time));
	} else {
		return 0;
	}
}

function minutesToTime(minutes) {
	var h = integerDivision(minutes, 60);
	var m = minutes % 60;
	
	return formatTimeNumber(h) + ':' + formatTimeNumber(m);
}

function formatTimeNumber(n) {
	n = '' + n;
	
	if(n == null || n == '') {
		return '00';
	}
	
	if(n.length < 2) {
		return '0' + n;
	}
	
	return n;
}

function refreshRows(scheduleId) {
	for(var i=0; i < TOTAL_ROWS; i++) {
		refreshRow(i, scheduleId);
	}
}

function getPositionId(rowNum, scheduleId) {
	var selectObj = getObjectByID(scheduleId + 'scheduleposition_' + rowNum);
	var positionId = null;
	if(selectObj) {
		positionId = selectObj.value;
	}
	return positionId;
}

function refreshRow(rowNum, scheduleId) {
	var inHour = null;
	var outHour = null;
	var totalHours = 0;
	
	for(var i=0; i < TOTAL_COLS; i++) {
		var inputObj = getObjectByID(scheduleId + 'schedule_' + rowNum + '_' + i);
		var inputHourObj = getObjectByID(scheduleId + 'schedulehour_' + rowNum + '_' + i);
		
		var value = null;
		var currentHour = null;
		
		if(inputObj) {
			value = inputObj.value;
		}
		
		if(inputHourObj) {
			currentHour = inputHourObj.value;
		}
		
		if(value != null && value != '' && value != BREAK) {
			inputObj.value = getPositionId(rowNum, scheduleId);			
			
			if(inHour == null) {
				inHour = currentHour;
			}
			if(outHour == null || outHour < currentHour) {
				outHour = currentHour;
			}
			
			totalHours += 15;
		}
	}

	if(totalHours > 0) {
		totalHours = minutesToTime(totalHours);
	} else {
		totalHours = '-';
	}
	
	if(outHour != null && outHour != '') {
		outHour = timeInMinutes(outHour) + 15;
		outHour = minutesToTime(outHour);
	} else {
		outHour = '-';
	}
	
	if(inHour == null) {
		inHour = '-';
	}
	
	
	var o1 = getObjectByID(scheduleId + 'inHourInput_' + rowNum);
	var o2 = getObjectByID(scheduleId + 'outHourInput_' + rowNum);
	var o3 = getObjectByID(scheduleId + 'totalHoursInput_' + rowNum);
	
	if(o1) {
		o1.value = inHour;
	}
	
	if(o2) {
		o2.value = outHour;
	}
	if(o3) {
		o3.value = totalHours;
	}

	var o4 = getObjectByID(scheduleId + 'inHour_' + rowNum);
	var o5 = getObjectByID(scheduleId + 'outHour_' + rowNum);
	var o6 = getObjectByID(scheduleId + 'totalHours_' + rowNum);
	
	if(o4) {
		o4.innerHTML = inHour;
	}
	if(o5) {
		o5.innerHTML = outHour;
	}
	if(o6) {
		o6.innerHTML = totalHours;
	}		
}
</script>

