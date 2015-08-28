<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<spring:url value="/resources/css/buy.css" var="mainCss" />
<link href="${mainCss}" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>buy items</title>
</head>
<body>
<header>
	<div class="container">
		<span class="home"><a href="/cartservice/">Shoppers Zone</a></span>
	</div>
</header>
	<br> ${message }
	<table border="1">
		<c:set var="totalPrice" value="${0} " />
		<c:set var="totalItems" />
		<c:forEach items="${cart}" var="entry">
			<tr>
				<td>${entry.value.getName()}</td>
				<td>${entry.value.getQuantity()}</td>
				<td>${entry.value.getPrice()}</td>
				<c:set var="totalPrice" value="${totalPrice+entry.value.getPrice()}" />
				<c:set var="totalItems"
					value="${entry.value.getQuantity()+totalPrice1}" />
			</tr>
		</c:forEach>
		<tr>
			<td>Total</td>
			<td>${totalItems}</td>
			<td>${totalPrice}</td>
		</tr>
	</table>
	<br>
</body>
</html>