function validateflight(str){
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
		if(str.checkintime.value == ""){
			document.getElementById("error1").innerHTML="* Please select check-in time";
			document.getElementById("error2").innerHTML="";
			return false;
	}else
		if(str.landingtime.value == ""){
			document.getElementById("error1").innerHTML="* Please select landing time";
			document.getElementById("error2").innerHTML="";
			return false;
	}else{
		//getflightresult(str);
		return true;
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
function getflightresult(str){
	var origin = str.origin.value;
	var destin = str.destination.value;
	var noofpsgrs   = str.noofpsgrs.value;

	$(".status-origin-page").show();
	$(".load-page").html('<center><img src="images/indicator_blue_small.gif">&nbsp;&nbsp;Loading....</center>');
	$.ajax({
		type:"POST",
		url: "flightaction.htm?origin="+origin+"&&destin="+destin+"&&noofpsgrs="+noofpsgrs,
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
					$("#status-report-id").html('<tbody>'
				 				+'<tr>'
				 					+'<td>'
				 						+'<div class="source-place-profile">'
				 							+'<div class="source-photo">'
				 								+'<div class="user one" style="background-image: url(images/1.jpg);"></div>'
				 							+'</div>'
				 							+'<div class="source-data">'
				 								+'<strong>'+item.st+'</strong>'
				 								+'<p class="sub-title">'+item.sst+'</p>'
				 							+'</div>'
				 						+'</div>'
				 						+'<div class="source-place-profile">'
				 							+'<div class="source-photo">'
				 								+'<div class="user one" style="background-image: url(images/2.jpg);"></div>'
				 							+'</div>'
				 							+'<div class="source-data">'
				 								+'<strong>'+item.et+'</strong>'
				 								+'<p class="sub-title">'+item.dst+'</p>'
				 							+'</div>'
				 						+'</div>'
				 					+'</td>'
				 				+'</tr>'
				 			+'</tbody>');
				}
			});
			
			$.each(data.flights, function(i,item){
				if (item)	{
					$("#display-data-status").append('<tr class="bgcolor-for-status">'
							+'<td>'
							+'<div class="flight-title-place-profile">'
								+'<div class="history-no-place">'
									+'<div class="user tmp">'+item.fid+'</div>'
								+'</div>'
								+'<div class="flight-from-to-title-data">'
									+'<p class="sub-title"><strong>Airport :</strong> '+item.aname+'</p>'
									+'<p class="sub-title"><strong>fLIGHT NAME</strong> '+item.fname+'</p>'
									+'<p class="sub-title"><strong>START TIME :</strong> '+item.starttime+'</p>'
									+'<p class="sub-title"><strong>END TIME :</strong> '+item.endtime+'</P>'
								+'</div>'
							+'</div>'
							+'<div class="flight-dis-dur-cost-title-place-profile">'
								+'<div class="flight-data-place">'
									+'<p class="sub-title"><strong>DURATION :</strong> 78:67:90</P>'
									+'<p class="sub-title"><strong>AVAILABLE SEETS :</strong> 78:67:90</P>'
									+'<p class="sub-title"><strong>TOTAL COST :</strong> 78:67:90</P>'
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
function displaycost(str){
	if(str == "1"){
		document.getElementById("yes").innerHTML = "Daily Rate: <input type='text' size='10px' value='45'/>";
	}else{
		document.getElementById("yes").innerHTML = "";
	}
}
function validatefirstform(str){
	if(str.tbsm.value == ""){
		document.getElementById("error1").innerHTML="* Please select Transportation Between starting address and MDW";
		document.getElementById("error2").innerHTML="";
		return false;
	}else
	if(str.eptc.value == ""){
		document.getElementById("error1").innerHTML="* Please enter Estimated Plane Ticket Price";
		document.getElementById("error2").innerHTML="";
		return false;
	}else
		if(str.atc.value == ""){
			document.getElementById("error1").innerHTML="* Please enter Airport Transportation Cost";
			document.getElementById("error2").innerHTML="";
			return false;
	}else
		if(str.checkedbagfee.value == ""){
			document.getElementById("error1").innerHTML="* Please enter Checked Bag Fees";
			document.getElementById("error2").innerHTML="";
			return false;
	}else
		if(str.pcpd.value == ""){
			document.getElementById("error1").innerHTML="* Please enter Parking Cost per Day";
			document.getElementById("error2").innerHTML="";
			return false;
	}else
		if(str.tbsd.value == ""){
			document.getElementById("error1").innerHTML="* Please select Transportation Between SJC and Destination address";
			document.getElementById("error2").innerHTML="";
			return false;
	}else
		if(str.atpc.value == ""){
			document.getElementById("error1").innerHTML="* Please enter Airport Transportation Cost";
			document.getElementById("error2").innerHTML="";
			return false;
	}else{
		//getflightresult(str);
		return true;
	}
}

