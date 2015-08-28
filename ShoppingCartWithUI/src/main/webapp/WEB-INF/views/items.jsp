<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<spring:url value="/resources/css/items.css" var="mainCss" />
<link href="${mainCss}" rel="stylesheet" />
<title>Insert title here</title>

<script type="text/javascript">
	var cnt = 0;

	function validate(checkbox) {
		console.log("1");
		if (checkbox.checked == true)
			cnt++;
		else
			cnt--;

		if (cnt == 0)
			document.getElementById("addButton").disabled = true;
		else
			document.getElementById("addButton").disabled = false;
	}
</script>
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
        				<a href="logout">Logout</a>
					</c:otherwise>
				</c:choose>
				<a href="/cartservice/cart">Cart</a><br> <br>
			</div>
		</div>
	</header>
	<div class="container">
		<div class="category">
			<span><a href="/cartservice/items">All</a></span> <a
				href="/cartservice/items/mobiles">Mobiles</a> <a
				href="/cartservice/items/clothing">Clothing</a> <a
				href="/cartservice/items/books">Books</a> <a
				href="/cartservice/items/footwear">Footwear</a>
		</div>
		<div class="clear"></div>
		<div class="list">
			<form action="/cartservice/add" method="POST">
				<ul>
					<c:forEach items="${items}" var="entry">
						<li><input type="checkbox" value="${entry.getId()}"
							name="items" onclick="validate(this)">${entry.getName()}</li>
					</c:forEach>
				</ul>
				<input type="submit" value="Add To Cart" id="addButton" disabled class="btn btn-large btn-block btn-inverse">
			</form>
		</div>
	</div>
</body>
</html>