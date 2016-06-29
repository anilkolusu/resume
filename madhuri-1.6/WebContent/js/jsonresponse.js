function validate(str){
	if(str.origin.value == ""){
		document.getElementById("error1").innerHTML="* Please enter origin";
		document.getElementById("error2").innerHTML="";
		return false;
	}else
	if(str.destination.value == ""){
		document.getElementById("error1").innerHTML="* Please enter destination";
		document.getElementById("error2").innerHTML="";
		return false;
	}else
	if(str.noofpsgrs.value == ""){
		document.getElementById("error1").innerHTML="* Please select number of passengers";
		document.getElementById("error2").innerHTML="";
		return false;
	}else
	if(str.caryear.value == ""){
		document.getElementById("error1").innerHTML="";
		document.getElementById("error2").innerHTML="* Please select car year";
		return false;
	}else
	if(str.carmake.value == ""){
		document.getElementById("error2").innerHTML="* Please select car make";
		document.getElementById("error1").innerHTML="";
		return false;
	}else
	if(str.carmodel.value == ""){
		document.getElementById("error2").innerHTML="* Please select car model";
		document.getElementById("error1").innerHTML="";
		return false;
	}else
	if(str.hours.value == ""){
		document.getElementById("error2").innerHTML="* Please enter hours to drive";
		document.getElementById("error1").innerHTML="";
		return false;
	}else
	if(str.hotelcost.value == ""){
			document.getElementById("error2").innerHTML="* Please enter hotel cost";
			document.getElementById("error1").innerHTML="";
			return false;
	}else
		if(str.tollscost.value == ""){
			document.getElementById("error2").innerHTML="* Please enter tolls cost";
			document.getElementById("error1").innerHTML="";
			return false;
	}else{
		getresult(str);
	}
}
function numbervalidate(evt) {
    var iKeyCode = (evt.which) ? evt.which : evt.keyCode;
    if (iKeyCode != 46 && iKeyCode > 31 && (iKeyCode < 48 || iKeyCode > 57)){
    	document.getElementById("error1").innerHTML="* Please enter Number only";
        return false;
    }
    return true;
} 
function getresult(str){
	var origin = str.origin.value;
	var destin = str.destination.value;
	var noofpsgrs   = str.noofpsgrs.value;
	var caryear = str.caryear.value;
	var carmake = str.carmake.value;
	var carmodel = str.carmodel.value;
	var hours = str.hours.value;
	var hcost = str.hotelcost.value;
	var tcost = str.tollscost.value;
	$(".status-origin-page").show();
	$(".load-page").html('<center><img src="images/indicator_blue_small.gif">&nbsp;&nbsp;Loading....</center>');
	$.ajax({
		type:"POST",
		url: "direction.htm?origin="+origin+"&&destin="+destin+"&&noofpsgrs="+noofpsgrs+"&&caryear="+caryear+"&&carmake="+carmake+"&&carmodel="+carmodel+"&&hours="+hours+"&&hotelcost="+hcost+"&&tollscost="+tcost,
		cache: false,
		dataType: "json",
		success: function(data) {
		$(".load-page").html('');
		
		if(data.s == 1){
			$.each(data.root, function(i,item){
				if (item)	{
					$(".map-page").show();
					$("#my-map-canvas").show();
					$("#my-map-canvas").html('<iframe style="height:100%;width:100%;border:0;" frameborder="0" src="https://www.google.com/maps/embed/v1/directions?origin='+item.srt+'&destination='+item.end+'&key=AIzaSyAN0om9mFmy1QN6Wf54tXAowK4eT0ZUPrU"></iframe>');
					$(".status-report").show();
					$("#status-report-id").html('');
					$("#display-data-status").html('');
					$(".origin-result-form-page").show();
					$("#flight-details-id").html('<form action="flight.htm" method="post"><input type="hidden" name="origin" value="'+item.srt+'"/><input type="hidden" name="destin" value="'+item.end+'"/><input type="hidden" name="noofps" value="'+item.n+'"/><input type="hidden" name="caryear" value="'+item.caryear+'"/><input type="hidden" name="carmake" value="'+item.carmake+'"/><input type="hidden" name="carmodel" value="'+item.carmodel+'"/><input type="submit" class="btn gray" value=" Get Flight Details >> "/></form>');
					$("#status-report-id").html('<tbody>'
					+'<tr>'
	 					+'<td>'
	 						+'<div class="source-place-profile" style="border-bottom:1px solid #f2f2f2;">'
	 							+'<div class="source-data">'
	 								+'<strong>FROM : </strong>'
	 								+'<strong>'+item.st+'</strong>'
	 								+'<p class="sub-title">'+item.sst+'</p>'
	 							+'</div>'
	 						+'</div>'
	 						+'<div class="source-place-profile" style="border-bottom:1px solid #f2f2f2;">'
	 							+'<div class="source-data">'
	 								+'<strong>TO : </strong>'
	 								+'<strong>'+item.et+'</strong>'
	 								+'<p class="sub-title">'+item.dst+'</p>'
	 							+'</div>'
	 						+'</div>'
	 					+'</td>'
	 				+'</tr>'
	 				+'<tr>'
	 					+'<td>'
	 						+'<div class="source-place-profile">'
	 							+'<div class="source-data">'
	 								+'<strong>Vehicle Details</strong>'
	 								+'<table>'
	 									+'<tr><td><p class="sub-title">Car Year  : </p></td><td><p class="sub-title">'+item.caryear+'</p></td></tr>'
	 									+'<tr><td><p class="sub-title">Car Model : </p></td><td><p class="sub-title">'+item.carmodel+'</p></td></tr>'
	 									+'<tr><td><p class="sub-title">Car Make  : </p></td><td><p class="sub-title">'+item.carmake+'</p></td></tr>'
	 									+'<tr><td><p class="sub-title">Car Driven: </p></td><td><p class="sub-title">'+item.mileage+' MPG On Gasoline</p></td></tr>'
	 								+'</table>'
	 							+'</div>'
	 						+'</div>'
	 						+'<div class="source-place-profile">'
								+'<div class="source-data">'
									+'<strong>No Of Stops : '+item.noofstops+'</strong>'
								+'</div>'
							+'</div>'
	 					+'</td>'
	 				+'</tr>'
	 				+'<tr>'
	 					+'<td>'
	 						+'<div class="source-place-profile">'
	 							+'<div class="source-data">'
	 								+'<strong>Time Details</strong>'
	 								+'<table>'
	 									+'<tr><td><p class="sub-title">Distance  : </p></td><td><p class="sub-title">'+item.dit+' ( '+item.dis+' )</p></td></tr>'
	 									+'<tr><td><p class="sub-title">Time  : </p></td><td><p class="sub-title">'+item.dut+'</p></td></tr>'
	 									+'<tr><td><p class="sub-title">Duration : </p></td><td><p class="sub-title">'+item.dur+'</p></td></tr>'
	 									+'<tr><td><p class="sub-title">Travel : </p></td><td><p class="sub-title">'+item.d+'</p></td></tr>'
	 								+'</table>'
	 							+'</div>'
	 						+'</div>'
	 						+'<div class="source-place-profile">'
								+'<div class="source-data">'
									+'<strong>Cost Details</strong>'
									+'<table>'
										+'<tr><td><p class="sub-title">Total Fuel Cost: </p></td><td><p class="sub-title">$ '+item.cost+'</p></td></tr>'
										+'<tr><td><p class="sub-title">Wear & Tear : </p></td><td><p class="sub-title"> $ '+item.wt+'</p></td></tr>'
										+'<tr><td><p class="sub-title">Estimated Hotel Cost : </p></td><td><p class="sub-title">$ '+item.hotelcost+'</p></td></tr>'
										+'<tr><td><p class="sub-title">Estimated Tolls : </p></td><td><p class="sub-title">$ '+item.tolls+'</p></td></tr>'
										+'<tr border="1"><td><p class="sub-title">Total Round Trip Cost </p></td><td><p class="sub-title">$ '+item.totalcost+'</p></td></tr>'
									+'</table>'
								+'</div>'
							+'</div>'
	 					+'</td>'
	 				+'</tr>'
	 			+'</tbody>');
				$("#display-data-status").append('<tr class="bgcolor-for-status">'
						+'<td>'
							+'<div class="stop-title-place-profile">'
								+'<div class="stop-no-place">'
									+'<div class="user tmp">STOP</div>'
								+'</div>'
								+'<div class="stop-from-to-title-data">'
									+'<strong>FROM CITY TO CITY</strong>'
								+'</div>'
							+'</div>'
							+'<div class="stop-dis-dur-cost-title-place-profile">'
								+'<div class="stop-data-place">'
									+'<strong>Cost US$</strong>'
								+'</div>'
								+'<div class="stop-data-place">'
									+'<strong>Distance</strong>'
								+'</div>'
								+'<div class="stop-data-place">'
									+'<strong>Duration</strong>'
								+'</div>'
							+'</div>'
						+'</td>'
					+'</tr>');
				}
			});
			$.each(data.noofstops, function(i,item){
				if (item)	{
					$("#display-data-status").append('<tr class="bgcolor-for-status">'
						+'<td>'
							+'<div class="stop-title-place-profile">'
								+'<div class="stop-no-place">'
									+'<div class="user tmp"><strong>'+item.sno+'</strong></div>'
								+'</div>'
								+'<!--<div class="stop-from-to-title-data-info">'
									+'<p class="sub-title"><strong>FROM : </strong>'+item.srtlat+','+item.srtlng+'</p>'
									+'<p class="sub-title"><strong>TO   : </strong>'+item.endlat+','+item.endlng+'</p>'
								+'</div>-->'
								+'<div class="stop-from-to-title-data">'
									+'<p class="sub-title"><strong>FROM : </strong>'+item.srt+'</p>'
									+'<p class="sub-title"><strong>TO   : </strong>'+item.end+'</p>'
								+'</div>'
							+'</div>'
							+'<div class="stop-dis-dur-cost-title-place-profile">'
								+'<div class="stop-data-place">'
									+'<p class="sub-title">'+item.cost+'</p>'
								+'</div>'
								+'<div class="stop-data-place">'
									+'<p class="sub-title">'+item.dit+'</p>'
									+'<p class="sub-title">'+item.dis+'</p>'
								+'</div>'
								+'<div class="stop-data-place">'
									+'<p class="sub-title">'+item.dut+'</p>'
									+'<p class="sub-title">'+item.dur+'</p>'
								+'</div>'
							+'</div>'
						+'</td>'
					+'</tr>');
				}
			});
		}else{
			$(".map-page").hide();
			$(".load-page").html('<font id="error2">Soory ! No root details are available.</font>');
			$("#my-map-canvas").hide();
			$(".status-report").hide();
		}
	}});
}
function getmakeinfo(str){
	var year = str.caryear.value;
	$.ajax({
		type:"POST",
		url: "getmakeinfo.htm?year="+year,
		cache: false,
		dataType: "json",
		success: function(data) {
		$("#carmake-info").html('<option value="">----- select car make -----</option>');
		$("#carmodel-info").html('<option value="">----- select car model -----</option>');
		if(data.s == 1){
			$.each(data.makes, function(i,item){
				if (item)	{
					$("#carmake-info").append('<option value="'+item.make+'">'+item.make+'</option>');
				}
			});
		}
	}});
}
function getmodelinfo(str){
	var year = str.caryear.value;
	var make = str.carmake.value;
	$.ajax({
		type:"POST",
		url: "getmakeinfo.htm?year="+year+"&make="+make,
		cache: false,
		dataType: "json",
		success: function(data) {
		$("#carmodel-info").html('<option value="">----- select car model -----</option>');
		if(data.s == 1){
			$.each(data.makes, function(i,item){
				if (item)	{
					$("#carmodel-info").append('<option value="'+item.make+'">'+item.make+'</option>');
				}
			});
		}
	}});
}

