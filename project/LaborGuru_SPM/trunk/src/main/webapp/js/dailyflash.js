$(function(){
    $('#dailyFlashTable').calx();

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
    $('#saveFlashReport').on({
        click: function(){
        	var dataObj = '{"preOpenHour":"' + $("#A0").val() + '",';
        	dataObj += '"storeId":"' + $("#storeId").val() + '",';
            dataObj +='"details" : [ ';
        	$("tr[id^='row']").each(function(i,row){
        		dataObj += '{"strHour": "' + $(row).find("td:eq(1)").text() + '",';
        		dataObj += '"strActualSale": "' + $(row).find("input[id^='actualSale']").val() + '",';
        		dataObj += ' "strActualHour": "' + $(row).find("input[id^='A']").val() + '"},';
        	});
        	
        	
        	dataObj=dataObj.substring(0, dataObj.length - 1);
        	dataObj += ']}';
        	
            $.ajax({
                type : 'POST',
                dataType: 'json',
                contentType: 'application/json',
                url: '/spm/report/saveDailyFlashReport.action',
                data: dataObj,
        		beforeSend: function() {
        			var sw = screen.width;
        			var styles = {
        					"visibility": "visible",
        					"left": Math.round((sw/2) - 100)
        			};
        			$('#splash').css(styles);
        			
        		},
                success: function(data) {
                	$('#splash').css("visibility","hidden");
                	alert(data);
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
    
    $('.selectableTableValueWithLeftBottomBorder').on({
    	click: function() {
    		//var row = $('tr:yellow').text(); 
    		$('.yellow').toggleClass();
    		$(this).parent().addClass("yellow");
    		var num = $(this).parent().attr('row');
    		var dataFormula='$cumulDiff' + num + '/$projectedSalesCumul' + num;
    		$('#forecast').attr('data-formula',dataFormula);
    		dataFormula='1 - ($projectedSalesCumul' + num +'/ $totalCumulSales)';
    		$('#percentOfDay').attr('data-formula',dataFormula);
    		 $('#dailyFlashTable').calx('refresh');
    	},
        mouseover: function() {
            $(this).addClass("ui-state-hover");
            $(this).css("cursor","pointer");
        	
        },
        mouseout: function() {
            $(this).removeClass("ui-state-hover");

        }    	
    })
});