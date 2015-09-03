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
<title>Insert title here</title>

<script type="text/javascript">
	function InvalidMsg(textbox) {
		if (textbox.value == '') {
			switch (textbox.id) {
			case "uname":
				textbox.setCustomValidity('Please insert mail ID');
				break;
			case "upass":
				textbox.setCustomValidity('Please insert password');
				break;
			}
		}
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
		<div class="userlogin">
			<form:form action="j_spring_security_check" method="post" modelAttribute="user">
				<center><span style="color: red">${message}</span><br></center><br><br>
				<form:input path="username" type="text" placeholder="Email"
					required="required" oninvalid="InvalidMsg(this);" id="uname" />
				<br>
				<form:input path="password" type="password" placeholder="Password"
					required="required" oninvalid="InvalidMsg(this);" id="upass" />
				<br>
				<span><form:input type="submit" value="Login" path="" class="btn btn-large btn-block btn-inverse"/></span>
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