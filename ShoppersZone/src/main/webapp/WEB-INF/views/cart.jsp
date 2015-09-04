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
	var cnt = 0;

	function validate(checkbox) {
		if (checkbox.checked == true)
			cnt++;
		else
			cnt--;

		if (cnt == 0)
			document.getElementById("deleteBtn").disabled = true;
		else
			document.getElementById("deleteBtn").disabled = false;
	}

	function formSubmit() {
		document.getElementById("logoutForm").submit();
	}

	function redirectToLink(button) {
		var form = document.getElementById("cart");
		if (button.id == "purchaseBtn") {
			form.action = "/shopperszone/order";
		} else {
			form.action = "/shopperszone/deleteItems";
		}
		form.submit();
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
				<form:form action="order" method="post" commandName="items"
					id="cart">
					<div class="list">
						<table border="1" class="names">
							<thead>
								<tr>
									<th>#</th>
									<th>Item</th>
									<th>Quantity</th>
									<th>Price</th>
								</tr>
							</thead>
							<c:set var="totalPrice" value="${0} " />
							<c:set var="totalQuantity" />
							<c:forEach items="${items}" var="entry">
								<tr>
									<td><input type="checkbox" value="${entry.getId()}"
										name="items" onclick="validate(this)"></td>
									<td>${entry.getName()}</td>
									<td>1</td>
									<td>${entry.getPrice()}</td>
									<c:set var="totalPrice" value="${totalPrice+entry.getPrice()}" />
									<c:set var="totalQuantity" value="${1+totalQuantity}" />
								</tr>
							</c:forEach>
							<tr>
								<td></td>
								<td>Total</td>
								<td>${totalQuantity}</td>
								<td>${totalPrice}</td>
							</tr>
						</table>
					</div>
					<div class="clear"></div>
					<input type="submit" value="Purchase"
						class="btn btn-large btn-block btn-inverse"
						onclick="javascript:redirectToLink(this)" id="purchaseBtn">
					<input type="submit" value="Remove Items" disabled
						class="btn btn-large btn-block btn-inverse"
						onclick="javascript:redirectToLink(this)" id="deleteBtn">
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