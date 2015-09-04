<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<spring:url value="/resources/css/login.css" var="mainCss" />
<link href="${mainCss}" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>

<script type="text/javascript">
	function loginSubmit() {

		var blankFlag = false;
		var boxes = document.getElementsByTagName("input");
		for (i = 0; i < boxes.length; i++) {
			if ((boxes[i].type == "text" || boxes[i].type == "password")
					&& boxes[i].value == "") {
				console.log("type : " + boxes[i].type);
				console.log("value : " + boxes[i].value);
				blankFlag = true;
			}

		}
		console.log(blankFlag);
		var form = document.getElementById("loginForm");
		if (blankFlag == true) {
			form.action = "/shopperszone/login";
			form.method = "GET";
			alert("Please fill all the details");

		}
		form.submit();
	}

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
		<div class="userlogin">
			<form:form action="j_spring_security_check" method="post"
				modelAttribute="user" id="loginForm">
				<center>
					<span style="color: red">${message}</span><br>
				</center>
				<br>
				<br>
				<form:input path="username" type="text" placeholder="Email" />
				<br>
				<form:input path="password" type="password" placeholder="Password" />
				<br>
				<span><form:input type="submit" value="Login" path=""
						class="btn btn-large btn-block btn-inverse"
						onclick="loginSubmit()" /></span>
			</form:form>
		</div>
	</div>
	<br>
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
</body>
</html>