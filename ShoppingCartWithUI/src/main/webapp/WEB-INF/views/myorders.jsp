<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<spring:url value="/resources/css/myorders.css" var="mainCss" />
<link href="${mainCss}" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
	<div class ="container">
	<div class ="shift">
	<c:choose>
		<c:when test="${orders.size()==0}">
			<H1>No orders</H1>
		</c:when>
		<c:otherwise>
			<form:form action="/cartservice/" method="get" commandName="orders">
				<table border="1" class="names">
					<thead>
						<tr>
						<th>ID</th>
						<th>Date</th>
						<th>Total Items</th>
						<th>Amount</th>
						<th>Payment Type</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${orders}" var="entry">
						<tr>
							<td>${entry.getId()}</td>
							<td>${entry.getDate()}</td>
							<td>${entry.getItems().size()}</td>
							<td>${entry.getTotalAmt()}</td>
							<td>${entry.getPaymentType()}</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</form:form>
		</c:otherwise>
	</c:choose>
	</div>
	</div>
</body>
</html>