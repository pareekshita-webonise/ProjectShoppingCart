<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<spring:url value="/resources/css/cart.css" var="mainCss" />
<link href="${mainCss}" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cart</title>
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
	<div class="clear"></div>
	<div class="container">
		<c:choose>
			<c:when test="${items.size()==0}">
				<H1>No items in cart.</H1>
			</c:when>
			<c:otherwise>
				<form:form action="order" method="post" commandName="items">
					<table border="1" class="names">
						<thead>
							<tr>
								<th>Item</th>
								<th>Quantity</th>
								<th>Price</th>
							</tr>
						</thead>
						<c:set var="totalPrice" value="${0} " />
						<c:set var="totalQuantity" />
						<c:forEach items="${items}" var="entry">
							<tr>
								<td>${entry.getName()}</td>
								<td>1</td>
								<td>${entry.getPrice()}</td>
								<c:set var="totalPrice" value="${totalPrice+entry.getPrice()}" />
								<c:set var="totalQuantity" value="${1+totalQuantity}" />
							</tr>
						</c:forEach>
						<tr>
							<td>Total</td>
							<td>${totalQuantity}</td>
							<td>${totalPrice}</td>
						</tr>
					</table>
					<input type="submit" value="Purchase"
						class="btn btn-large btn-block btn-inverse">
				</form:form>
			</c:otherwise>
		</c:choose>
	</div>
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
</body>
</html>