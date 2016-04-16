<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript" src="js/flightdetails.js"></script>

<div class="origin-main-page">
	<div class="origin-page" style="width:700px;">
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
			
				<td><p class="sub-title">Driving Time to Source Airport</p></td>
				<td><p class="sub-title"> : ${history.drivingtimetomdwtext}</p></td>
				
				<td><p class="sub-title">Total Ticket Cost </p></td>
				<td><p class="sub-title"> : ${history.totalticketcosttext}</p></td>
				
			</tr>
			<tr>
			
				<td><p class="sub-title">Time at Source Airport</p></td>
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
				<td><p class="sub-title">Time at Destination Airport</p></td>
				<td><p class="sub-title"> : ${history.landingtext}</p></td>
				
				<td><p class="sub-title">Source Transportation Cost</p></td>
				<td><p class="sub-title"> : ${history.mdwtransportationcosttext}</p></td>
				
			</tr>
			<tr>
				<td><p class="sub-title">Driving time from Destination Airport </p></td>
				<td><p class="sub-title"> : ${history.drivingtimefromsjctext}</p></td>
				
				<td> <p class="sub-title"> Source Parking Cost</p> </td>
				<td><p class="sub-title"> : ${history.mdwparkingcosttext}</p></td>
				
			</tr>
			<tr>
				<td>----------------------------</td>
				<td>---------</td>
				<td><p class="sub-title">Destination Transportation Cost</p></td>
				<td><p class="sub-title"> : ${history.lgatransportationcost}</p></td>
			</tr>
			<tr>
				<td><p class="sub-title">One - Way - Door - To - Door </p></td>
				<td><p class="sub-title"> : ${history.totaltimetext }</p></td>
				
				<td>---------------------------</td>
				<td>-------</td>
			</tr>
			<tr><td></td><td></td>
				<td><p class="sub-title">Total One way Trip Cost</p></td>
				<td><p class="sub-title"> : ${history.totalonewaytripcost}</p></td>
			</tr>
		</table>
		<div class="form-title">
			
		</div>
		<form action="compare.htm">
			<input type="submit" class="btn gray" value=" Compare "/>
		</form>
	</div>
  <!-- <div class="origin-page" style="width:600px;">
		<table>
			<c:choose>
				<c:when test="${flights ne null && flights.size() != 0}">
					<c:forEach items="${flights}" var="flight">
						<tr class="bgcolor-for-status">
							<td>
								<div class="history-title-place-profile">
									<div class="history-no-place" style="width:80px;">
										<strong>FLIGHT ID</strong>
										<div class="user tmp"><p class="sub-title">${flight.flightid}</p></div>
									</div>
									<div class="history-data-place" style="width:120px;">
										<strong>AIRPORT NAME</strong>
										<p class="sub-title">${flight.airportname}</p>
									</div>
									<%-- <div class="history-from-to-title-data" style="width:20px;">
										<strong>AVAILABLE SEETS</strong>
										<p class="sub-title">${flight.availableseets}</p>
									</div> --%>
									<div class="history-from-to-title-data" style="width:120px;">
										<strong>ARAIVAL TIME</strong>
										<p class="sub-title">${flight.araivaltime}</p>
									</div>
									<div class="history-from-to-title-data" style="width:120px;">
										<strong>DEPARTURE TIME</strong>
										<p class="sub-title">${flight.departuretime}</p>
									</div>
								</div>
							</td>
						</tr>
    				</c:forEach>
				</c:when>
				<c:otherwise>
					<p style="color:red;">No Flight Details are available</p>
				</c:otherwise>
			</c:choose>
		</table>
	</div> -->
</div>