<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="home">Home</a><br>
	<a href="/cartservice/items">All</a>
	<a href="/cartservice/items/mobiles">Mobiles</a>
	<a href="/cartservice/items/clothing">Clothing</a>
	<a href="/cartservice/items/books">Books</a>
	<a href="/cartservice/items/footwear">Mobiles</a>

	<form action="/cartservice/add" method="POST">
		<ul style="list-style-type: none">
		<c:forEach items="${map}" var="entry">
			    <li><input type="checkbox" value="${entry.key}" name="items">${entry.value.getName()}</li>
			</c:forEach>
		</ul>
		<input type="submit" value="addToCart">
	</form>
</body>
</html>