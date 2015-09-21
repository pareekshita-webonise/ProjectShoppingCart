<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<spring:url value="/resources/css/order.css" var="mainCss" />
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
	<form:form action="home" method="get" commandName="order">
		<h2>Order Details</h2>
		<table border="1">

			<tr>
				<td colspan="2">Order ID :- ${order.getId()}</td>
				<td colspan="2">Order Date :- ${order.getDate()}</td>
			</tr>
			<tr>
				<td colspan="2">User Id :- ${order.getUser().getId()}</td>
				<td colspan="2">Username :- ${order.getUser().getFirstName()}
					${order.getUser().getLastName()}</td>
			</tr>
		</table>
		<table border="1">
			<thead>
				<tr>
				<th>Item ID</th>
				<th>Name</th>
				<th>Quantity</th>
				<th>Price</th>
				</tr>
			</thead>
			<tbody>
				<%-- <c:set var="items" value="${order.getItems()}" /> --%>
				<c:set var="totalPrice" value="${0} " />
				<c:set var="totalQuantity" />
				<c:forEach items="${order.getItems()}" var="entry">
					<tr>
						<td>${entry.getId()}</td>
						<td>${entry.getName()}</td>
						<td>1</td>
						<td>${entry.getPrice()}</td>
						<c:set var="totalPrice" value="${totalPrice+entry.getPrice()}" />
						<c:set var="totalQuantity" value="${1+totalQuantity}" />
					</tr>
				</c:forEach>
				<tr>
					<td colspan="2">Total</td>
					<td>${totalQuantity}</td>
					<td>${totalPrice}</td>
				</tr>
			</tbody>
		</table>
		<!-- <input type="submit" value="Go to home"> -->
	</form:form>
	</div>
</body>
</html>