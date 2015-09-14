<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<spring:url value="/resources/css/order.css" var="mainCss" />
<link href="${mainCss}" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Past Orders</title>
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
						<c:choose>
							<c:when test="${isAdmin == true}">
								<a href="/shopperszone/admin">Admin</a>
							</c:when>
							<c:otherwise></c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
				<a href="/shopperszone/cart">Cart</a><br> <br>
			</div>
		</div>
	</header>
	<div class="clear"></div>
	<div class="container">
	<c:choose>
		<c:when test="${orders.size()==0}">
			<H1>No orders</H1>
		</c:when>
		<c:otherwise>
			<form:form action="/shopperszone/" method="get" commandName="orders">
				<h2>Past Orders : </h2><br>
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
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
</body>
</html>