<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<spring:url value="/resources/css/items.css" var="mainCss" />
<link href="${mainCss}" rel="stylesheet" />
<title>Items</title>

<script type="text/javascript">
	var cnt = 0;

	function validate(checkbox) {
		if (checkbox.checked == true)
			cnt++;
		else
			cnt--;

		if (cnt == 0)
			document.getElementById("addButton").disabled = true;
		else
			document.getElementById("addButton").disabled = false;
	}

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
	<div class="container">
		<div class="category">
			<ul>
				<li><a href="/shopperszone/items">All</a></li>
				<c:forEach items="${categories}" var="entry">
					<li><a href="/shopperszone/items/${entry.toLowerCase()}">${entry}</a></li>
				</c:forEach>
			</ul>
		</div>
		<c:choose>
			<c:when test="${items == null}">
				<h1>No such category</h1>
			</c:when>
			<c:otherwise>
				<form:form action="/shopperszone/add" method="POST">
					<div class="list">
						<nav>
							<ul>
								<c:forEach items="${items}" var="entry">
									<li><input type="checkbox" value="${entry.getId()}"
										name="items" onclick="validate(this)">${entry.getName()}</li>
								</c:forEach>
							</ul>
						</nav>
					</div>
					<div class="btnshift">
						<input type="submit" value="Add To Cart" id="addButton" disabled
							class="btn btn-large btn-block btn-inverse">
					</div>
					<br>
					<div style="color: red; text-align: center; margin-left: 110px;">${message}</div>
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