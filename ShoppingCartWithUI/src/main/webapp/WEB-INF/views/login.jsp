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

	function loadMsg() {
<%%>
	}
</script>
</head>
<body onload="loadMsg()">
	<header>
		<div class="container">
			<span class="home"><a href="/cartservice/">Shoppers Zone</a></span>
			<div class="login">
				<c:choose>
					<c:when test="${currentUser == null}">
						<a href="/cartservice/signup">Sign Up</a>
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
		<div class="userlogin">
			<form:form action="loginValidate" method="post" commandName="user">
				<form:input path="userName" type="text" placeholder="Email"
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
	<div style="color: red">${msg}</div>

</body>
</html>