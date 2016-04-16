<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<script type="text/javascript" src="js/flightdetails.js"></script>

<div class="origin-main-page">
	<div class="origin-page" style="width:700px;">
		<div class="form-title">
			<p class="title-data">Flight Details Form </p>
			<p class="sub-title-data"><span id="error1"></span><span id="error2"></span></p>
		</div>
		<form name="originform" action="flightsecondaction.htm" method="post">
			<table>
				<tr>
					<td>Transportation Between Home and ${history.origin} </td>
					<td>
						Estimated Plane Ticket Price
					</td>
				</tr>
				<tr>
					<td>
						<input type="hidden" name="origin" value="${history.origin}"/>
						<input type="hidden" name="destination" value="${history.destin}"/>
						<input type="hidden" name="noofpsgrs" value="${history.numberofpassengers}"/>
						<input type="hidden" name="bagfee" value="${history.bagfee}"/>
						<input type="hidden" name="checkintime" value="${history.checkin}"/>
						<input type="hidden" name="landingtime" value="${history.landing}"/>
						<input type="hidden" name="caryear" value="${history.caryear}"/>
						<input type="hidden" name="carmake" value="${history.carmake}"/>
						<input type="hidden" name="carmodel" value="${history.carmodel}"/>
						
						<p class="sub-title"><input type="radio" name="tbsm" value="1"/>Ride Provided By Friend</p>
						<p class="sub-title"><input type="radio" name="tbsm" value="2"/>Taxi</p>
						<p class="sub-title"><input type="radio" name="tbsm" value="3"/>Shuttle</p>
						<p class="sub-title"><input type="radio" name="tbsm" value="4"/>Other</p>
						<p class="sub-title"><input type="radio" name="tbsm" value="5"/>Drive & Park vehicle at airport</p>
					</td>
					<td valign="top">
						<input type="text" name="eptc" class="form-field" value="${history.eptc}" readonly/>
					</td>
				</tr>
				<tr>
					<td>Airport Transportation Cost</td>
					<td>
						Checked Bag Fees
						<p class="sub-title">(per person)</p>
					</td>
				</tr>
				<tr>
					<td><input type="text" name="atc" class="form-field" value="${history.airporttransportationcost}" /></td>
					<td valign="top">
						<input type="text" name="checkedbagfee" class="form-field" value="${history.checkedbagfee}" />
					</td>
				</tr>
				<tr>
					<td>Parking Cost per Day</td>
					<td>Transportation While at ${history.destin}</td>
				</tr>
				<tr>
					<td><input type="text" name="pcpd" class="form-field" value="${history.parkingcostperday}" /></td>
					<td>
						<p class="sub-title"><input type="radio" name="twf" value="1" onclick="return displaycost(this.value)"/>Yes. Rental vehicle needed.  &nbsp;&nbsp; <span id="yes"> </span></p>
						<p class="sub-title"><input type="radio" name="twf" value="0" onclick="return displaycost(this.value)"/>No</p>
					</td>
				</tr>
				<tr>
					<td>Transportation Between ${history.destin} and Final Destination</td>
					<!-- <td>Airport Transportation Cost</td> -->
				</tr>
				<tr>
					<td>
						<p class="sub-title"><input type="radio" name="tbsd" value="1"/>Ride Provided By Friend</p>
						<p class="sub-title"><input type="radio" name="tbsd" value="2"/>Taxi</p>
						<p class="sub-title"><input type="radio" name="tbsd" value="3"/>Shuttle</p>
						<p class="sub-title"><input type="radio" name="tbsd" value="4"/>Other</p>
						<p class="sub-title"><input type="radio" name="tbsd" value="5"/>Drive & Park vehicle at airport</p>
					</td>
					<!-- <td valign="top"><input type="text" name="atpc" class="form-field"/></td> -->
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" class="btn gray" onclick="return validatefirstform(originform)" value="See Result"/></td>
				</tr>
			</table>
		</form>
		
		<div class="form-title">
			
		</div>
	</div>
</div>