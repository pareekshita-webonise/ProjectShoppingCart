<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>buy items</title>
</head>
<body>	
	<a href="home">Home</a><br>
	${message }
	<table border="1">
		<c:set var ="totalPrice" value="${0} "/>
		<c:set var ="totalItems" />
		<c:forEach items="${cart}" var="entry">
			<tr>
				<td>${entry.value.getName()}</td>
				<td>${entry.value.getQuantity()}</td>
				<td>${entry.value.getPrice()}</td>
				<c:set var ="totalPrice" value="${totalPrice+entry.value.getPrice()}"/>
				<c:set var ="totalItems" value="${entry.value.getQuantity()+totalPrice1}"/>
			</tr>
		</c:forEach>
		<tr>
			<td>Total</td>
			<td>${totalItems}</td>
			<td>${totalPrice}</td>
		</tr>
		</table>
		<a href="home">Go to Home</a><br>
</body>
</html>