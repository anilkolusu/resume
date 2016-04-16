<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="page-body-mid">
	<div class="login-page">
		<!-- <form name="loginform" action="dologin" method="post"> -->
		<form:form commandName="login" method="post">
			<div class="login-title">
				<strong>Login</strong>
				<span id="error"><form:errors/><form:errors path="username"/>${status}</span>
			</div>
			<div class="login-box">
				<table>
					<tr>
						<td>Username</td>
						<td><form:input path="username" class="text-field"/></td>
						<td>Password</td>
						<td><form:input type="password" path="password" class="text-field"/></td>
					</tr>
					<tr><td></td><td><a href="#" class="forgotpassword">forgot password?</a></td><td></td><td><a href="register.htm" class="forgotpassword">New user?</a></td></tr>
					<tr><td colspan="4"><input type="submit" class="submit-button" onclick="return loginvalidation(loginform)" value="Login"/></td></tr>
				</table>
			</div>
		</form:form>
	</div>
</div>