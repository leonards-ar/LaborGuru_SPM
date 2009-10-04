/*****************************************
 * Functions used to handle total in daily projections and actuals page
 *****************************************/
	function updateProjectionRowValue(objectId, variableName, totalId){
		truncateDailyProjectionValue(objectId);
		updateTotalRow(variableName, totalId);
	}

	function getNumberFromObject(objectId){		
		var number = getObjectByIDValue(objectId,0).replace(/\,/, "");
		var truncatedValue = toInt(number);

		if(isNaN(truncatedValue)) {
			truncatedValue = 0;
		}

		return truncatedValue;
	}
	
	function truncateDailyProjectionValue(objectId){
		var truncatedValue = getNumberFromObject(objectId);
		setObjectByIDValue(objectId, truncatedValue);				
	}
		
	function updateTotalRow(variableName, totalId){
		var totalValue = 0;
		for (projectionIndexRow=0; projectionIndexRow < 7; projectionIndexRow++){
			var dailyValue = getNumberFromObject(variableName+'['+projectionIndexRow+']');
			totalValue += dailyValue;		
		}
		setObjectByIDValueAndClass(totalId, totalValue, 'editorTableEvenRowTotal');
		setObjectByIDValue(totalId+'Input', totalValue, 'editorTableEvenRowTotal');		
	}