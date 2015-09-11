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
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</header>
	<div class="container">
		<c:choose>
			<c:when test="${users == null}">
				<h1>No Users Exist</h1>
			</c:when>
			<c:otherwise>
				<table border="1" class="names">
					<thead>
						<tr>
							<th>ID</th>
							<th>Username</th>
							<th>FirstName</th>
							<th>LastName</th>
							<th>Address</th>
							<th>Contact No</th>
							<th>Enabled</th>
						</tr>
					</thead>

					<c:forEach items="${users}" var="entry">
						<tr>
							<td>${entry.getId()}</td>
							<td>${entry.getUsername()}</td>
							<td>${entry.getFirstName()}</td>
							<td>${entry.getLastName()}</td>
							<td>${entry.getAddress()}</td>
							<td>${entry.getContactNo()}</td>
							<td>${entry.isEnabled()}</td>
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