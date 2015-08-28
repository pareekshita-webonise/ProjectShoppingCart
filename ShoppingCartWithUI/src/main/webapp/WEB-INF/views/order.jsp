<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>add to cart</title>
</head>
<body>
	<a href="/cartservice/">Home</a>
	<form:form action="home" method="get" commandName="order">
		<h2>Order Details</h2>
		<table border="1">

			<tr>
				<td colspan="2">Order ID :- ${order.getId()}</td>
				<td colspan="2">Order Date :- ${order.getId()}</td>
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
		<input type="submit" value="Go to home">
	</form:form>
</body>
</html>