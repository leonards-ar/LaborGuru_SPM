$(function(){
//Format Zero Value
	var number = numeral(0);

	numeral.zeroFormat('-');
	
	$('#dailyFlashTable').calx();
	
//	$('#scheduleReportTable').find('td:nth-child(10),th:nth-child(10)').hide();
//	$('#scheduleReportTable').find('td:nth-child(11),th:nth-child(11)').hide();
//	$('#scheduleReportTable').find('td:nth-child(12),th:nth-child(12)').hide();
//	$('#scheduleReportTable').find('td:nth-child(13),th:nth-child(13)').hide();
//	$('#scheduleReportTable').find('td:nth-child(14),th:nth-child(14)').hide();
//	$('#scheduleReportTable').find('td:nth-child(15),th:nth-child(14)').hide();	
	
    $('#saveFlashReport').on({
        click: function(){
        	var dataObj = '{"preOpenHour":"' + $("#A0").val() + '",';
        	dataObj += '"closeHour":"'+ $("#closeHour").find("input").val() + '",';
        	dataObj += '"storeId":"' + $("#storeId").val() + '",';
            dataObj +='"details" : [ ';
        	$("tr[id^='row']").each(function(i,row){
        		dataObj += '{"strHour": "' + $(row).find("td:eq(1)").text() + '",';
        		dataObj += ' "strIdealHour": "' + $(row).find("td:eq(15)").text() + '",';
        		dataObj += '"strActualSale": "' + $(row).find("input[id^='B']").val() + '",';
        		dataObj += ' "strActualHour": "' + $(row).find("input[id^='A']").val() + '"},';
        		
        	});
        	
        	
        	dataObj=dataObj.substring(0, dataObj.length - 1);
        	dataObj += ']}';
        	
        	var sw = screen.width;
        	
		
            $.ajax({
                type : 'POST',
                dataType: 'json',
                contentType: 'application/json',
                url: url,
                data: dataObj,
        		beforeSend: function() {
        			
        			var styles = {
        					"visibility": "visible",
        					"left": Math.round((sw/2) - 100)
        			};
        			$('#splash').css(styles);
        			$(window).scrollTop(0);
        			
        		},
                success: function(data) {
                	$('#splash').css("visibility","hidden");
                	$('#result').find('#message').text(data);
                	$('#result').css("left", Math.round((sw/2) - 100));
                	$('#result').fadeIn("slow").delay(5000).fadeOut("slow");
                },
                error: function (xhr, ajaxOptions, thrownError) {
                	$('#splash').css("visibility","hidden");
                	$('#result').find('#message').text(xhr.status + " - " + thrownError);
                	$('#result').css("left", Math.round((sw/2) - 100));
                	$('#result').fadeIn("slow").delay(10000).fadeOut("slow");
                  }
            });
            return false;
        },
        mouseover: function() {
            $(this).addClass("ui-state-hover");
            $(this).css("cursor","pointer");
        	
        },
        mouseout: function() {
            $(this).removeClass("ui-state-hover");

        }
    	
    });
    
    $("input[type='text']").on({
    	focus: function(){
    		$(this).val('');
    	}
    });
    
    $("tr[id^='row']").find("input[id^='B']").on({
    	change: function(){
	    		
	    		if($(this).val() > 0){
		    		
		    		var dataObj = '{"storeId":"' + $("#storeId").val() + '",';
		    		dataObj += '"sales": [';
		    		$("tr[id^='row']").each(function(i,row){
		        		dataObj += '{"strTime": "' + $(row).find("td:eq(1)").text() + '",';
		        		dataObj += '"strSale": "' + $(row).find("td:eq(13)").text() + '"},';
		    		});
		    		
		    		dataObj=dataObj.substring(0, dataObj.length - 1);
		    		dataObj += "]}";
		    		
		            $.ajax({
		                type : 'POST',
		                dataType: 'json',
		                contentType: 'application/json',
		                url: calulateUrl,
		                data: dataObj,
		                success: function(data) {
		                	refreshIdealHours(data);
		                },
		            });
	    		
    		}
    	},
    });
    
    function refreshIdealHours(data){
    	
    	var hours = data.split(",");
    	var totalIdealHours=0;
    	var totalCumulIdealHours=0;
    	$("tr[id^='row']").each(function(i,row){
    		$(row).find("td:eq(15)").text(hours[i]);
    		totalIdealHours+=parseInt(hours[i]);
    		if($(row).find("td:eq(16)").text() !='-'){
    			$(row).find("td:eq(16)").text(hours[i]);
    			totalCumulIdealHours+=parseInt(hours[i]);
    		}
    	});
    	$('#totalIdealHours').text(totalIdealHours/2);
    	$('#totalCumulIdealHours').text(totalCumulIdealHours/2);
    	var percentOfDay = parseFloat($('#percentOfDay').text().replace("%",""))/100;
    	var partialIdealHours = (totalCumulIdealHours/2) + parseFloat(projectedOpenningHours) + (1 - percentOfDay)*parseFloat(projectedFlexHours);
    	var diffIdealHours = parseInt($('#actualHours').text()) - partialIdealHours;
    	var soFarIdealHours = totalIdealHours/2 - partialIdealHours;
    	var soFarIdealHoursDiff = soFarIdealHours - parseInt($('#soFarScheduleHours').text());
    	$('#partialIdealHours').text(numeral(partialIdealHours).format('0'));
    	$('#diffIdealHours').text(numeral(diffIdealHours).format('(0)'));
    	$('#soFarIdealHours').text(numeral(soFarIdealHours).format('(0)'));
    	$('#soFarIdealHoursDiff').text(numeral(soFarIdealHoursDiff).format('(0)'));
    	
    }
       
});