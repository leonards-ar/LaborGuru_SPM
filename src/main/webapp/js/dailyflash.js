$(function(){
//Format Zero Value
	var number = numeral(0);

	numeral.zeroFormat('-');
	
	$('#dailyFlashTable').calx();
	
	$('#scheduleReportTable').find('td:nth-child(10),th:nth-child(10)').hide();
	$('#scheduleReportTable').find('td:nth-child(11),th:nth-child(11)').hide();
	$('#scheduleReportTable').find('td:nth-child(12),th:nth-child(12)').hide();
	$('#scheduleReportTable').find('td:nth-child(13),th:nth-child(13)').hide();
	$('#scheduleReportTable').find('td:nth-child(14),th:nth-child(14)').hide();
	$('#scheduleReportTable').find('td:nth-child(15),th:nth-child(14)').hide();	
	
    $('#saveFlashReport').on({
        click: function(){
        	var dataObj = '{"preOpenHour":"' + $("#A0").val() + '",';
        	dataObj += '"closeHour":"'+ $("#closeHour").find("input").val() + '",';
        	dataObj += '"storeId":"' + $("#storeId").val() + '",';
            dataObj +='"details" : [ ';
        	$("tr[id^='row']").each(function(i,row){
        		dataObj += '{"strHour": "' + $(row).find("td:eq(1)").text() + '",';
        		dataObj += '"strActualSale": "' + $(row).find("input[id^='actualSale']").val() + '",';
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
    
   
    /*
    $('#receipt_table tbody tr').live('click',function() {
        var id = this.id;
        var index = jQuery.inArray(id, aReceiptSelected);
        
        var monto = parseFloat($(this).find('td:eq(8)').text().replace(".","").replace(",","."));
        
        if ( index == -1 ) {
            aReceiptSelected.push( id );
            receiptBalance+= isNaN(monto)? 0 : monto;
        } else {
            aReceiptSelected.splice( index, 1 );
            receiptBalance-= isNaN(monto)? 0 : monto;
        }
        
        $("#receiptBalance").html('<b>Balance:' + String(receiptBalance.toFixed(2)) + '</b>');        
        $(this).toggleClass('row_selected');
    });
*/
    
});