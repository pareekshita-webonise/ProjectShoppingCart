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
</head>
<body>
	<header>
		<div class="container">
			<span class="home"><a href="/cartservice/">Shoppers Zone</a></span>
			<div class="login">
				<c:choose>
					<c:when test="${currentUser == null}">
						<a href="/cartservice/login">Log In</a>
					</c:when>
					<c:otherwise>
        	${user.getFirstName()} ${user.getLastName()}
        	<a href="logout">Logout</a>
					</c:otherwise>
				</c:choose>
				<a href="/cartservice/cart">Cart</a><br> <br>
			</div>
		</div>
	</header>
	<div class="container">
		<div class="signup">
			<form:form action="signupValidate" method="post" commandName="user">
				<form:input path="userName" type="text" placeholder="Email" />
				<br>
				<form:input path="password" type="password" placeholder="Password" />
				<br>
				<form:input path="firstName" type="text" placeholder="Firstname" />
				<br>
				<form:input path="lastName" type="text" placeholder="Lastname" />
				<br>
				<form:input path="contactNo" type="text" placeholder="Mobile Number"
					value="" />
				<br>
				<form:input type="submit" value="Sign up" path="" class="btn btn-large btn-block btn-inverse"/>
			</form:form>
			<br>

			<div style="color: red">${msg}</div>
		</div>
	</div>
</body>
</html>