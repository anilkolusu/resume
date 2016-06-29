<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title><tiles:insertAttribute name="title" ignore="true" /></title>
		<link type="text/css" href="css/styles.css" rel="stylesheet" />
		<script type="text/javascript" src="js/javascript-validations.js"></script>
	</head>
	<body>
		<div class="page">
			<div class="welcome-page">
				<div class="page-header">
					<tiles:insertAttribute name="header" />
				</div>
				<div class="page-body">
					
					<tiles:insertAttribute name="body1" />
					
					<tiles:insertAttribute name="body2" />
					
					<tiles:insertAttribute name="body3" />
					
				</div>
				<div class="footer">
					<tiles:insertAttribute name="footer" />
				</div>
			</div>
		</div>
	</body>
</html>
		

		