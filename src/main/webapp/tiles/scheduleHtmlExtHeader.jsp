<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="s" uri="/struts-tags" %> 

<!--s:head theme="ajax"/-->
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
var previousClassName;
var onMouseOutEnabled = true;

var TOTAL_COLS = 20;
var OPEN_TIME = 4;
var BREAK = '__break__';
var CANNOT_CHANGE_ROW_MSG = 'Must select the end time in the same row';

function initialize(totalCols, OpenHour, breakTxt, cannotChangeRowMsg) {
	TOTAL_COLS = totalCols;
	OPEN_TIME = OpenHour;
	BREAK = breakTxt;
	CANNOT_CHANGE_ROW_MSG = cannotChangeRowMsg;
}

function getObjectByID(objectId) {
	if(document.getElementById) {
		return document.getElementById(objectId);
	} else {
		return null;
	}
}

function scheduleClick(tdObj, rowNum, colNum, positionId)
{
	setMessage('');
	if(scheduleState == 1 || scheduleState == 3 || scheduleState == 5) {
		currentRow = rowNum;
		fromColumn = colNum;
		toColumn = null;
		tdObj.className = 'scheduleStartSelection';
		onMouseOutEnabled = false;
		scheduleState += 1;
		setMessage('Select the finish time');
	} else if(scheduleState == 2 || scheduleState == 4 || scheduleState == 6) {
		if(currentRow != rowNum) {
			setMessage(CANNOT_CHANGE_ROW_MSG);
		} else {
			tdObj.className = 'scheduleEndSelection';
			if(colNum < fromColumn) {
				toColumn = fromColumn;
				fromColumn = colNum;
			} else {
				toColumn = colNum;
			}
			
			
			for(var i = fromColumn; i <= toColumn; i++) {
				var cell = getObjectByID('cell_' + currentRow + '_' + i);
				cell.className = getClassName();
				var inputObj = getObjectByID('schedule_' + rowNum + '_' + i); 
				inputObj.value = positionId;
			}
			refreshRow(rowNum);

			fromColumn = null;
			toColumn = null;
			onMouseOutEnabled = false;

			changeAction(scheduleState - 1);
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

function changeAction(actionCode)
{
	if(fromColumn != null && scheduleState != actionCode) {
		var cell = getObjectByID('cell_' + currentRow + '_' + fromColumn);
		cell.className = 'scheduleEmpty';
	}
	fromColumn = null;
	toColumn = null;
	scheduleState = actionCode;
	setMessage('Select the starting time');
}

function setMessage(msgTxt) {
	var msg = getObjectByID('actionMessage');
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


function refreshRow(rowNum) {
	var inHour = null;
	var outHour = null;
	var totalHours = 0;
	
	for(var i=0; i < TOTAL_COLS; i++) {
		var inputObj = getObjectByID('schedule_' + rowNum + '_' + i); 
		var value = null;
		if(inputObj) {
			value = inputObj.value;
		}
		if(value != null && value != '' && value != BREAK) {
			var currentHour = OPEN_TIME + 0.25 * i;
			if(inHour == null) {
				inHour = currentHour;
			}
			if(outHour == null || outHour < currentHour) {
				outHour = currentHour;
			}
			totalHours += 0.25;
		}
	}

	getObjectByID('inHour_' + rowNum).innerHTML = inHour;
	getObjectByID('outHour_' + rowNum).innerHTML = outHour;
	getObjectByID('totalHours_' + rowNum).innerHTML = totalHours;
}

</script>