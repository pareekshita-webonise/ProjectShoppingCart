<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Shoppers Zone</h1>
	
	<c:choose>
    	<c:when test="${user == null}">
        	<a href="login">Log In</a>
			<a href="signup">Sign Up</a>
    	</c:when>
    	<c:otherwise>
        	${user.getFirstName()} ${user.getLastName()}
        	<a href="logout">Logout</a>
    	</c:otherwise>
	</c:choose>
	
	<br>
	
	<a href="/cartservice/items">All</a>
	<a href="/cartservice/items/mobiles">Mobiles</a>
	<a href="/cartservice/items/clothing">Clothing</a>
	<a href="/cartservice/items/books">Books</a>
	<a href="/cartservice/items/footwear">Mobiles</a>
</body>
</html>
