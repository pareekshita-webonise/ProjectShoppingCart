<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<spring:url value="/resources/css/account.css" var="mainCss" />
<link href="${mainCss}" rel="stylesheet" />
<title>Account</title>
<script>
	function formSubmit() {
		document.getElementById("logoutForm").submit();
	}

	function updateSubmit() {
		var button = document.getElementById("button");
		if (button.value == "Edit") {
			setEditable();
			button.value = "Update";
		} else {
			var form = document.getElementById("updateForm");
			console.log(form.method);
			form.submit();
		}
	}

	function setEditable() {
		var boxes = document.getElementsByTagName("input");
		for (i = 0; i < boxes.length; i++) {
			if (boxes[i].type == "text" || boxes[i].type == "password")
				boxes[i].readOnly = false;
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
						<a href="/shopperszone/signup">Sign Up</a>
					</c:when>
					<c:otherwise>
						<a href="/shopperszone/account">${pageContext.request.userPrincipal.name}
						</a>
						<a href="javascript:formSubmit()"> Logout</a>
					</c:otherwise>
				</c:choose>
				<a href="/shopperszone/cart">Cart</a><br> <br>
			</div>
		</div>
	</header>
	<div class="clear"></div>
	<div class="container">
		<div class="userinfo">
			<ul>
				<li><a href="/shopperszone/orders">My Orders</a></li>
				<li><a href="/shopperszone/items">All Items</a></li>
			</ul>
		</div>
		<div class="account">
			<form:form action="/shopperszone/updateuser" id="updateForm"
				method="post" modelAttribute="user">
				<form:input type="hidden" path="id" />
				<p>Username:</p>
				<form:input path="username" type="text" readonly="true" id="formTxt" />
				<p>Password:</p>
				<form:input path="password" type="password" readonly="true"
					id="formTxt" />
				<p>First Name :</p>
				<form:input path="firstName" type="text" readonly="true"
					id="formTxt" />
				<p>Last Name:</p>
				<form:input path="lastName" type="text" readonly="true" id="formTxt" />
				<p>Address:</p>
				<form:input path="address" type="text" readonly="true" id="formTxt" />
				<p>Contact No:</p>
				<form:input path="contactNo" type="text" readonly="true"
					id="formTxt" />
				<p></p>
				<form:input type="button" value="Edit" path=""
					class="btn btn-large btn-block btn-inverse" id="button"
					onclick="javascript:updateSubmit()" />
			</form:form>
			<br>
			<div class="clear"></div>

			<div style="color: red">${msg}</div>
		</div>
	</div>
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
</body>
</html>