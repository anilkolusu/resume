<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<script src="http://maps.googleapis.com/maps/api/js?sensor=false&libraries=places"></script>
<script src="js/jquery.geocomplete.min.js"></script>
<script>
	function g(str){
		$(function () {	$("#"+str).geocomplete({  }); });
	}
</script>

<div class="origin-main-page">
	<div class="origin-form-page">
		<div class="origin-page">
			<div class="form-title">
				<p class="title-data">Root Details Form </p>
				<p class="sub-title-data"><span id="error1"></span><span id="error2"></span></p>
			</div>
			<form name="originform" action="#" method="post">
				<table>
					<tr><td>Origin</td><td>Destination</td></tr>
					<tr>
						<td><input type="text" name="origin" id="origin" class="form-field" onkeyup="return g('origin')"/></td>
						<td><input type="text" name="destination" id="destin" class="form-field" onkeyup="return g('destin')"/></td>
					</tr>
					<tr><td>No Of Passengers</td><td>Select Year</td></tr>
					<tr>
						<td>
							<select name="noofpsgrs" class="form-field">
								<option value="">-- select no of passengers --</option>
								<option value="1">01</option>
								<option value="2">02</option>
								<option value="3">03</option>
								<option value="4">04</option>
								<option value="5">05</option>
							</select>
						</td>
						<td>
							<select name="caryear" class="form-field" onchange="return getmakeinfo(originform)">
								<option value="">----- select car year -----</option>
								<option value="2016">2016</option>
								<option value="2015">2015</option>
								<option value="2014">2014</option>
							</select>
						</td>
					</tr>
					<tr><td>Select Make</td><td>Select Model</td></tr>
					<tr>
						<td>
							<select name="carmake" class="form-field" id="carmake-info" onchange="return getmodelinfo(originform)">
								<option value="">----- select car make -----</option>
							</select>
						</td>
						<td>
							<select name="carmodel" class="form-field" id="carmodel-info">
								<option value="">----- select car model -----</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>No Of Hours You Drive per day</td>
						<td><input type="text" name="hours" id="from" class="form-field" onkeypress='return numbervalidate(event)'/></td>
					</tr>
					<tr>
						<td>Hotel Cost</td>
						<td><input type="text" name="hotelcost" class="form-field" onkeypress='return numbervalidate(event)'/></td>
					</tr>
					<tr>
						<td>Tolls Cost</td>
						<td><input type="text" name="tollscost" class="form-field" onkeypress='return numbervalidate(event)'/></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="button" class="btn gray" onclick="return validate(originform)" value="Search"/></td>
					</tr>
				</table>
			</form>
			
			<div class="form-title">
				
			</div>
		</div>
		<div class="status-origin-page">
			<div class="load-page"></div>
			<div class="map-page">
				<div id="my-map-canvas" style="height:100%; width:100%;max-width:100%;"></div>
			</div>
		</div>
	</div>
	<div class="origin-result-form-page">
		<div class="origin-page">
			<table class="status-report-table" id="status-report-id">
				<!-- <tbody>
	 				<tr>
	 					<td>
	 						<div class="source-place-profile">
	 							<div class="source-data">
	 								<strong>FROM : </strong>
	 								<strong>'+item.st+'</strong>
	 								<p class="sub-title">'+item.sst+'</p>
	 							</div>
	 						</div>
	 						<div class="source-place-profile">
	 							<div class="source-data">
	 								<strong>TO : </strong>
	 								<strong>'+item.et+'</strong>
	 								<p class="sub-title">'+item.dst+'</p>
	 							</div>
	 						</div>
	 					</td>
	 				</tr>
	 				<tr>
	 					<td>
	 						<div class="source-place-profile">
	 							<div class="source-data">
	 								<strong>Vehicle Details</strong>
	 								<table>
	 									<tr><td><p class="sub-title">Car Year  : </p></td><td><p class="sub-title">'+item.sst+'</p></td></tr>
	 									<tr><td><p class="sub-title">Car Model : </p></td><td><p class="sub-title">'+item.sst+'</p></td></tr>
	 									<tr><td><p class="sub-title">Car Make  : </p></td><td><p class="sub-title">'+item.sst+'</p></td></tr>
	 								</table>
	 							</div>
	 						</div>
	 					</td>
	 				</tr>
	 				<tr>
	 					<td>
	 						<div class="source-place-profile">
	 							<div class="source-data">
	 								<strong>Time Details</strong>
	 								<table>
	 									<tr><td><p class="sub-title">Time  : </p></td><td><p class="sub-title">'+item.sst+'</p></td></tr>
	 									<tr><td><p class="sub-title">Duration : </p></td><td><p class="sub-title">'+item.sst+'</p></td></tr>
	 								</table>
	 							</div>
	 						</div>
	 					</td>
	 				</tr>
	 				<tr>
	 					<td>
	 						<div class="source-place-profile">
	 							<div class="source-data">
	 								<strong>Cost Details</strong>
	 								<table>
	 									<tr><td><p class="sub-title">Total Fuel Cost: </p></td><td><p class="sub-title">'+item.sst+'</p></td></tr>
	 									<tr><td><p class="sub-title">Wear & Tear : </p></td><td><p class="sub-title">'+item.sst+'</p></td></tr>
	 									<tr><td><p class="sub-title">Estimated Hotel Cost : </p></td><td><p class="sub-title">'+item.sst+'</p></td></tr>
	 									<tr><td><p class="sub-title">Estimated Tolls : </p></td><td><p class="sub-title">'+item.sst+'</p></td></tr>
	 									<tr><td><p class="sub-title">Total Round Trip Cost </p></td><td><p class="sub-title">'+item.sst+'</p></td></tr>
	 								</table>
	 							</div>
	 						</div>
	 					</td>
	 				</tr>
	 			</tbody> -->
			</table>
			<div id="flight-details-id"></div>
		</div>
		
		<div class="status-origin-page">
			<div class="status-report">
				<table class="status-report-table" id="display-data-status">
					<!-- <tr class="bgcolor-for-status">
							<td>
								<div class="stop-title-place-profile">
									<div class="stop-no-place">
										<div class="user tmp">STOP</div>
									</div>
									<div class="stop-from-to-title-data">
										<strong>FROM CITY TO CITY</strong>
									</div>
								</div>
								<div class="stop-dis-dur-cost-title-place-profile">
									<div class="stop-data-place">
										<strong>Cost US$</strong>
									</div>
									<div class="stop-data-place">
										<strong>Distance</strong>
									</div>
									<div class="stop-data-place">
										<strong>Duration</strong>
									</div>
								</div>
							</td>
						</tr> -->
				</table>
			</div>
		</div>
	</div>
</div>