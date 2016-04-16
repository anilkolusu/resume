<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript" src="js/flightdetails.js"></script>

<div class="origin-main-page">
	<div class="origin-page" style="width:500px;">
		<div class="form-title">
			<p class="title-data">Flight Details </p>
			<p class="sub-title-data"><span id="error1"></span><span id="error2"></span></p>
		</div>
		<table>
			<tr>
				<td><strong>Time </strong></td><td></td>
				<td><strong>Cost for ${history.numberofpassengers} Traveler </strong></td><td></td>
			</tr>
			<tr>
			
				<td><p class="sub-title">Driving time to MDW </p></td>
				<td><p class="sub-title"> : ${history.drivingtimetomdwtext}</p></td>
				
				<td><p class="sub-title">Total Ticket Cost </p></td>
				<td><p class="sub-title"> : ${history.totalticketcosttext}</p></td>
				
			</tr>
			<tr>
			
				<td><p class="sub-title">Time at MDW </p></td>
				<td><p class="sub-title"> : ${history.checkintext}</p></td>
				
				<td><p class="sub-title">Total Baggage Fees </p></td>
				<td><p class="sub-title"> : ${history.baggagefeetext}</p></td>
				
			</tr>
			<tr>
			
				<td><p class="sub-title">Estimated Flying Time </p></td>
				<td><p class="sub-title"> : ${history.flyingtimetext}</p></td>
				
				<td><p class="sub-title">Total Car Rental Cost </p></td>
				<td><p class="sub-title"> : ${history.carrentalcosttext}</p></td>
				
			</tr>
			<tr>
				<td><p class="sub-title">Time at SJC </p></td>
				<td><p class="sub-title"> : ${history.landingtext}</p></td>
				
				<td><p class="sub-title">MDW Transportation Cost </p></td>
				<td><p class="sub-title"> : ${history.mdwtransportationcosttext}</p></td>
				
			</tr>
			<tr>
				<td><p class="sub-title">Driving time from SJC </p></td>
				<td><p class="sub-title"> : ${history.drivingtimefromsjctext}</p></td>
				
				<td><p class="sub-title">MDW Parking Cost </p></td>
				<td><p class="sub-title"> : ${history.mdwparkingcosttext}</p></td>
				
			</tr>
			<tr>
				<td>----------------------------</td>
				<td>--------------------------</td>
				<td><p class="sub-title">LGA Transportation Cost</p></td>
				<td><p class="sub-title"> : ${history.lgatransportationcost}</p></td>
			</tr>
			<tr>
				<td><p class="sub-title">One - Way - Door - To - Door </p></td>
				<td><p class="sub-title"> : ${history.totaltimetext }</p></td>
				
				<td>---------------------------</td>
				<td>----------------------------</td>
			</tr>
			<tr><td></td><td></td>
				<td><p class="sub-title">Total One way Trip Cost</p></td>
				<td><p class="sub-title"> : ${history.totalonewaytripcost}</p></td>
			</tr>
		</table>
		<div class="form-title">
			
		</div>
	</div>
	<div class="origin-page" style="width:500px;">
		<div class="form-title">
			<p class="title-data">Car Details </p>
			<p class="sub-title-data"><span id="error1"></span><span id="error2"></span></p>
		</div>
		<table>
			<tr>
				<td><strong>Time </strong></td><td></td>
				<td><strong>Cost Details </strong></td><td></td>
			</tr>
			<tr>
			
				<td><p class="sub-title">Distance </p></td>
				<td><p class="sub-title"> : ${cardetails.distance}</p></td>
				
				<td><p class="sub-title">Total Fuel Cost </p></td>
				<td><p class="sub-title"> : ${cardetails.totalfuelcost}</p></td>
				
			</tr>
			<tr>
			
				<td><p class="sub-title">Time </p></td>
				<td><p class="sub-title"> : ${cardetails.time}</p></td>
				
				<td><p class="sub-title">Wear & Tear </p></td>
				<td><p class="sub-title"> : ${cardetails.weartear}</p></td>
				
			</tr>
			<tr>
			
				<td><p class="sub-title">Duration </p></td>
				<td><p class="sub-title"> : ${cardetails.duration}</p></td>
				
				<td><p class="sub-title">Estimated Hotel Cost </p></td>
				<td><p class="sub-title"> : ${cardetails.hotelcost}</p></td>
				
			</tr>
			<tr>
				<td><p class="sub-title">Travel</p></td>
				<td><p class="sub-title"> : ${cardetails.travel}</p></td>
				
				<td><p class="sub-title">Estimated Tolls </p></td>
				<td><p class="sub-title"> : ${cardetails.estimatedtolls}</p></td>
				
			</tr>
			<tr><td></td><td></td>
				<td><p class="sub-title">Total Round Trip Cost</p></td>
				<td><p class="sub-title"> : ${cardetails.totalroundtripcost}</p></td>
			</tr>
		</table>
		<div class="form-title">
			
		</div>
	</div>
</div>