<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<head>
		<link type="text/css" href="css/styles.css" rel="stylesheet" />
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/javascript-validations.js"></script>
		
		<script src="http://maps.googleapis.com/maps/api/js?sensor=false&libraries=places"></script>
		<script src="js/jquery.geocomplete.min.js"></script>
		<script>
			function g(str){
				$(function () {	$("#"+str).geocomplete({  }); });
			}
		</script>
	</head>
	<body>
		<div class="page">
			<div class="welcome-page">
				<div class="page-header">
					
				</div>
				<div class="home-body">
					<div class="home-body-left">
						<div class="profile-info">
							<div class="profle-info-photo">
								
							</div>
							<div class="profle-info-fullname">
								<i>Welcome Mr Admin</i>
							</div>
						</div>
						<div class="left-side-menus">
							<a href="homepage" class="create-ride-button">Home</a>
							<a href="logout" class="create-ride-button">Logout</a>
						</div>
					</div>
					<div class="home-body-mid">
						<div class="post-message">
							<div class="post-message">
								<form name="createrideform" action="addflight" method="post">
									<table width="57%">
										<tr><td valign="top">Add Flight Details</td><td colspan="5"><span id="error">${error}</span></td></tr>
										<tr><td valign="top" colspan="5"><hr/></td></tr>
										<tr><td>Flight Id</td><td><input type="text" name="flightid" class="text-field"/></td></tr>
										<tr><td>Flight Name</td><td><input type="text" name="flightname" class="text-field"/></td></tr>
										<tr><td>Origin</td><td><input type="text" name="origin" id="origin" class="text-field" onkeyup="return g('origin')"/></td></tr>
										<tr><td>Destination</td><td><input type="text" name="destin" id="destin" class="text-field" onkeyup="return g('destin')"/></td></tr>
										<tr><td>Arrival Time</td><td><input type="text" name="atime" class="text-field"/></td></tr>
										<tr><td>Departure Time</td><td><input type="text" name="dtime" class="text-field"/></td></tr>
										<tr><td>Airport Name</td><td><input type="text" name="airportname" class="text-field"/></td></tr>
										<tr><td></td><td><input type="reset" value="Clear" class="submit-button"/><input type="submit" value="Submit" class="submit-button" onclick="return ridevalidation(createrideform)"/></td></tr>
									</table>
								</form>
							</div>
						</div>
					</div>
				</div>
				<div class="footer">
					
				</div>
			</div>
		</div>
	</body>
</html>