<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<spring:url value="/resources/css/inventory.css" var="mainCss" />
<link href="${mainCss}" rel="stylesheet" />
<title>Items</title>
</head>
<body>
	<header>
		<div class="container">
			<span class="home"><a href="/shopperszone/admin">Shoppers
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
						<c:choose>
							<c:when test="${isAdmin == true}">
								<a href="/shopperszone/admin">Admin</a>
							</c:when>
							<c:otherwise></c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</header>
	<div class="container">
		<div class="category">
			<ul>
				<li><a href="/shopperszone/admin/inventory">All</a></li>
				<c:forEach items="${categories}" var="entry">
					<li><a
						href="/shopperszone/admin/inventory/${entry.toLowerCase()}">${entry}</a></li>
				</c:forEach>
			</ul>
		</div>
		<c:choose>
			<c:when test="${items == null}">
				<h1>No Orders Present</h1>
			</c:when>
			<c:otherwise>
				<table border="1" class="names">
					<thead>
						<tr>
							<th>ID</th>
							<th>Name</th>
							<th>Price</th>
							<th>Quantity</th>
							<th>Category</th>
							<th>Option</th>
						</tr>
					</thead>

					<c:forEach items="${items}" var="entry">
						<tr>
							<td>${entry.getId()}</td>
							<td>${entry.getName()}</td>
							<td>${entry.getPrice()}</td>
							<td>${entry.getQuantity()}</td>
							<td>${entry.getCategory()}</td>
							<td><a href="/shopperszone/admin/updateitem/${entry.getId()}">Update</a></td>
						</tr>
					</c:forEach>
				</table>
				<br>
				<div style="color: red; text-align: center; margin-left: 110px;">${message}</div>
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