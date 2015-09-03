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
<title>Insert title here</title>

<script>
	function formSubmit() {
		document.getElementById("logoutForm").submit();
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
						<a href="/shopperszone/signup">Sign Up</a>
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
					<form:form action="saveuser" method="post" modelAttribute="user">
						<form:input path="username" type="text" placeholder="Email" />
						<br>
						<form:input path="password" type="password" placeholder="Password" />
						<br>
						<form:input path="firstName" type="text" placeholder="First Name" />
						<br>
						<form:input path="lastName" type="text" placeholder="Last Name" />
						<br>
						<form:input path="address" type="text"
							placeholder="Shipping Address" value="" />
						<br>
						<form:input path="contactNo" type="text"
							placeholder="Mobile Number" value="" />
						<br>
						<form:input type="submit" value="Sign up" path=""
							class="btn btn-large btn-block btn-inverse" />
					</form:form>
					<br>
					<div style="color: red">${msg}</div>
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