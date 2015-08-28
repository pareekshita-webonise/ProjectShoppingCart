<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<spring:url value="/resources/css/home.css" var="mainCss" />
<link href="${mainCss}" rel="stylesheet" />
<title>Home</title>
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
	<div class="clear"></div>
	<div class="container">
		<div class="category">
			<span><a href="/cartservice/items">All</a></span> <a
				href="/cartservice/items/mobiles">Mobiles</a> <a
				href="/cartservice/items/clothing">Clothing</a> <a
				href="/cartservice/items/books">Books</a> <a
				href="/cartservice/items/footwear">Footwear</a>
		</div>
	</div>
</body>
</html>
