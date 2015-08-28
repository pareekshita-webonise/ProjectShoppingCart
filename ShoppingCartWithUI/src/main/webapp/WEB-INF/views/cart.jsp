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
<title>add to cart</title>
</head>
<body>
	<header>
		<div class="container">
			<span class="home"><a href="/cartservice/">Shoppers Zone</a></span>
			<div class="login">
				<c:choose>
					<c:when test="${currentUser == null}">
						<a href="/cartservice/login">Log In</a>
						<a href="/cartservice/signup">Sign Up</a>
					</c:when>
					<c:otherwise>
        	<a href="">${currentUser.getFirstName()} ${currentUser.getLastName()}</a>
        	<a href="/cartservice/logout">Logout</a>
					</c:otherwise>
				</c:choose>
				<a href="/cartservice/cart">Cart</a><br> <br>
			</div>
		</div>
	</header>
	<div class="container">
	<c:choose>
		<c:when test="${items.size()==0}">
			<H1>No items in cart.</H1>
		</c:when>
		<c:otherwise>
			<form:form action="buy" method="post" commandName="items">
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
				<input type="submit" value="Buy" class="btn btn-large btn-block btn-inverse">
			</form:form>
		</c:otherwise>
	</c:choose>
</div>
</body>
</html>