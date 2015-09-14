<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<spring:url value="/resources/css/admin.css" var="mainCss" />
<link href="${mainCss}" rel="stylesheet" />
<title>Admin</title>
<script>
	function formSubmit() {
		document.getElementById("logoutForm").submit();
	}
</script>
</head>
<body>
	<header>
		<div class="container">
			<span class="home"><a href="/shopperszone/">Shoppers
					Zone</a></span>
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
			</div>
		</div>
	</header>
	<div class="clear"></div>
	<div class="container">
		<div class="category">
		<ul>
			<li><a href="/shopperszone/admin/inventory">Inventory</a></li>
			<li><a href="/shopperszone/admin/users">Registered Users</a></li>
			<li><a href="/shopperszone/admin/orders">All Orders</a></li>
			<li><a href="/shopperszone/admin/additem">Add Items</a></li>
			</ul>
		</div>
	</div>
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
</body>
</html>