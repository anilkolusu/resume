<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="page-body-mid">
	<div class="signup-page">
		<%-- <form name="signupform" action="doregister" method="post"> --%>
		<form:form commandName="register">
			<div class="signup-title">
				<strong>SignUp</strong>
				<span id="error"><form:errors/></span>
			</div>
			<div class="signup-box">
				<table>
					<tr>
						<td>Full Name</td>
						<td><form:input path="fullname" class="text-field"/></td>
					</tr>
					<tr>
						<td>Email</td>
						<td><form:input path="emailid" class="text-field"/></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><form:input type="password" path="password" class="text-field"/></td>
					</tr>
					<tr>
						<td>Re-Password</td>
						<td><form:input type="password" path="repassword" class="text-field"/></td>
					</tr>
				</table>
			</div>
			<div class="signup-bottom">
				<a href="login.htm" class="forgotpassword">Login?</a>
				<input type="reset" class="submit-button" value="Clear"/>
				<input type="submit" value="Register" class="submit-button" onclick="return regvalidation(signupform)"/>
				
			</div>
		</form:form>
	</div>
</div>