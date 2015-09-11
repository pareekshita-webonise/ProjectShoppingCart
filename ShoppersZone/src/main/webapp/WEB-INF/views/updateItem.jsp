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
<title>Update Item</title>
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
			<form:form action="/shopperszone/admin/updateinventory" id="updateForm"
				method="post" modelAttribute="item">
				<form:input type="hidden" path="id" />
				<p>Item Name:</p>
				<form:input path="name" type="text" id="formTxt" />
				<p>Item Price:</p>
				<form:input path="price" type="text" id="formTxt" />
				<p>Item Quantity:</p>
				<form:input path="quantity" type="text" id="formTxt" />
				<p>Item Category:</p>
				<form:input path="category" type="text" id="formTxt" />
				<p></p>
				<form:input type="submit" value="Update" path=""
					class="btn btn-large btn-block btn-inverse" id="button" />
				<div style="color: red">${message}</div>
			</form:form>
			<br>
			<div class="clear"></div>
		</div>
	</div>
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
</body>
</html>