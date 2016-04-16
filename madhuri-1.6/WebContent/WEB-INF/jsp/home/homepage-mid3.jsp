<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<script type="text/javascript" src="js/flightdetails.js"></script>

<script src="http://maps.googleapis.com/maps/api/js?sensor=false&libraries=places"></script>
<script src="js/jquery.geocomplete.min.js"></script>
<script>
	function g(str){
		$(function () {	$("#"+str).geocomplete({  }); });
	}
</script>

<div class="origin-main-page">
	<div class="origin-page">
		<div class="form-title">
			<p class="title-data">Flight Details Form </p>
			<p class="sub-title-data"><span id="error1"></span><span id="error2"></span></p>
		</div>
		<form name="originform" action="flightfirstaction.htm" method="post">
			<table>
				<tr><td>Origin</td><td>Destination</td></tr>
				<tr>
					<td><input type="text" name="origin" id="origin" value="${history.origin}" class="form-field" onkeyup="return g('origin')"/></td>
					<td><input type="text" name="destination" id="destin" value="${history.destin}" class="form-field" onkeyup="return g('destin')"/></td>
				</tr>
				<tr><td>No Of Passengers</td></tr>
				<tr>
					<td>
						<select name="noofpsgrs" class="form-field">
							<option value="">-- select no of passengers --</option>
							<option value="${history.numberofpassengers}" selected>${history.numberofpassengers}</option>
							<option value="1">01</option>
							<option value="2">02</option>
							<option value="3">03</option>
							<option value="4">04</option>
							<option value="5">05</option>
						</select>
					</td>
					<td>
						<input type="hidden" name="bagfee" class="form-field"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">How Much time do you need to check-in at the airport?</td>
				</tr>
				<tr>
					<td colspan="2">
						<select name="checkintime" class="form-field" style="width:400px;">
							<option value="">-- select time to check-in --</option>
							<option value="1">15 minutes</option>
							<option value="2">30 minutes</option>
							<option value="3">45 minutes</option>
							<option value="4">1 hour</option>
							<option value="5">1 hour, 15 minutes</option>
							<option value="6">1 hour, 30 minutes</option>
							<option value="7">1 hour, 45 minutes</option>
							<option value="8">2 hours</option>
							<option value="9">2 hours, 15 minutes</option>
							<option value="10">2 hours, 30 minutes</option>
							<option value="11">2 hours, 45 minutes</option>
							<option value="12">3 hours</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2">How Much time do you need at the airport, after landing?</td>
				</tr>
				<tr>
					<td colspan="2">
						<select name="landingtime" class="form-field" style="width:400px;">
							<option value="">-- select time --</option>
							<option value="1">15 minutes</option>
							<option value="2">30 minutes</option>
							<option value="3">45 minutes</option>
							<option value="4">1 hour</option>
							<option value="5">1 hour, 15 minutes</option>
							<option value="6">1 hour, 30 minutes</option>
							<option value="7">1 hour, 45 minutes</option>
							<option value="8">2 hours</option>
							<option value="9">2 hours, 15 minutes</option>
							<option value="10">2 hours, 30 minutes</option>
							<option value="11">2 hours, 45 minutes</option>
							<option value="12">3 hours</option>
						</select>
					</td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" class="btn gray" onclick="return validateflight(originform)" value="Next >>"/></td>
				</tr>
			</table>
		</form>
		
		<div class="form-title">
			
		</div>
	</div>
</div>