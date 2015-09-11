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
<title>Add Item</title>
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
					<form:form action="/shopperszone/admin/addinventory" method="post" modelAttribute="item"
						id="addItemForm">
						<p>Item Name:</p>
						<form:input path="name" type="text" id="txtEmail"
							placeholder="Enter Item Name" />
						<br>
						<p>Item Price:</p>
						<form:input path="price" type="text"
							placeholder="Enter Item Price" />
						<br>
						<p>Item Quantity:</p>
						<form:input path="quantity" type="text"
							placeholder="Enter Item Quantity" />
						<br>
						<p>Item Category:</p>
						<form:input path="category" type="text"
							placeholder="Enter Item Category" />
						<br>
						<form:input type="submit" value="Add Item" path=""
							class="btn btn-large btn-block btn-inverse" />
					</form:form>
					<br>
					<div style="color: red; text-align: center;">${message}</div>
		</div>
	</div>
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
</body>
</html>