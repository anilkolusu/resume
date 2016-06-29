<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link type="text/css" href="css/styles.css" rel="stylesheet" />
		<script type="text/javascript" src="js/javascript-validations.js"></script>
	</head>
	<body>
		<div class="page">
			<div class="welcome-page">
				<div class="page-header">
					
				</div>
				<div class="page-body">
					<div class="page-body-left">
					
					</div>
					<div class="page-body-mid">
						<div class="login-page">
							<form name="loginform" action="dologin" method="post">
								<table>
									<tr><td valign="top">Login</td><td colspan="4"><span id="error">${error}</span></td></tr>
									<tr><td valign="top" colspan="4"><hr/></td></tr>
									<tr><td>Username</td><td><input type="text" name="username" class="text-field"/></td><td>Password</td><td><input type="password" name="password" class="text-field"/></td></tr>
									<tr><td></td><td><a href="#" class="forgotpassword">forgot password?</a></td><td></td><td><a href="register" class="forgotpassword">New user?</a></td></tr>
									<tr><td colspan="4"><input type="reset" class="submit-button"/><input type="submit" class="submit-button" onclick="return loginvalidation(loginform)"/></td></tr>
								</table>
							</form>
						</div>
					</div>
					<div class="page-body-right">
					
					</div>
				</div>
				<div class="footer">
					
				</div>
			</div>
		</div>
	</body>
</html>