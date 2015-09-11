<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<spring:url value="/resources/css/signup.css" var="mainCss" />
<link href="${mainCss}" rel="stylesheet" />
<title>Sign up</title>

<script>
	function formSubmit() {
		document.getElementById("logoutForm").submit();
	}
	function validate(){
		signupSubmit();
		checkEmail();
	}
	function signupSubmit() {

		var blankFlag = false;
		var boxes = document.getElementsByTagName("input");
		for (i = 0; i < boxes.length; i++) {
			if ((boxes[i].type == "text" || boxes[i].type == "password")
					&& boxes[i].value == "")
				blankFlag = true;
		}
		console.log(blankFlag);
		var form = document.getElementById("signupForm");
		if (blankFlag == true) {
			form.action = "/shopperszone/signup";
			form.method = "GET";
			alert("Please fill all the details");

		}
		form.submit();
	}
	function checkEmail() {

		var email = document.getElementById("txtEmail");
		var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;

		if (!filter.test(email.value)) {
			alert('Please provide a valid email address');
			email.focus;
			return false;
		}
	}
</script>

</head>
<body>
	<header>
		<div class="container">
			<span class="home"><a href="/shopperszone/">Shoppers Zone</a></span>
			<div class="login">
				<c:choose>
					<c:when test="${pageContext.request.userPrincipal.name == null}">
						<a href="/shopperszone/login">Log In</a>
					</c:when>
					<c:otherwise>
						<a href="/shopperszone/account">${pageContext.request.userPrincipal.name}</a>
						<a href="javascript:formSubmit()"> Logout</a>
					</c:otherwise>
				</c:choose>
				<a href="/shopperszone/cart">Cart</a><br> <br>
			</div>
		</div>
	</header>
	<div class="container">
		<div class="signup">
			<c:choose>
				<c:when test="${pageContext.request.userPrincipal.name == null}">
					<form:form action="saveuser" method="post" modelAttribute="user"
						id="signupForm" name="sform">
						<p>Username:</p>
						<form:input path="username" type="text" id="txtEmail"
							placeholder="Enter Email" />
						<br>
						<p>Password:</p>
						<form:input path="password" type="password"
							placeholder="Enter Password" />
						<br>
						<p>First name:</p>
						<form:input path="firstName" type="text"
							placeholder="Enter First Name" />
						<br>
						<p>Last name:</p>
						<form:input path="lastName" type="text"
							placeholder="Enter Last Name" />
						<br>
						<p>Shipping Address:</p>
						<form:input path="address" type="text" maxlength="200"
							placeholder="Enter  Address" value="" />
						<br>
						<p>Contact Number:</p>
						<form:input path="contactNo" type="text" maxlength="10"
							placeholder="Enter Mobile Number" value="" />
						<br>
						<form:input type="submit" value="Sign up" path=""
							class="btn btn-large btn-block btn-inverse"
							onclick="validate();" />
					</form:form>
					<br>
					<div style="color: red; text-align: center;">${message}</div>
				</c:when>
				<c:otherwise>
					<h1>Please logout the current account to Sign up for new
						Account</h1>
				</c:otherwise>
			</c:choose>

		</div>
	</div>
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
</body>
</html>