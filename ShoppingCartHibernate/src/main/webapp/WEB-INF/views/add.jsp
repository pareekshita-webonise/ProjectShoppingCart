<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>add to cart</title>
</head>
<body>

	<form action="buy" method="post">
		<table>
			<c:set var ="totalPrice" value="${0} "/>
			<c:set var ="totalPrice1" />
			<c:forEach items="${map}" var="entry">
				<tr>
					<td>${entry.value.getName()}</td>
					<td><input type="text" value="${entry.value.getQuantity()}" name="quantities" style="width:25px"></td>
					<td>${entry.value.getPrice()}</td>
					<c:set var ="totalPrice" value="${totalPrice+entry.value.getPrice()}"/>
					<c:set var ="totalPrice1" value="${entry.value.getQuantity()+totalPrice1}"/>
					<%-- <c:set var ="totalItems" value="${totalItems+entry.value.getQuantity()}"/> --%>
				</tr>
			</c:forEach>
			<tr>
				<td>Total</td>
				<td>${totalPrice1}</td>
				<td>${totalPrice}</td>
			</tr>
		</table>
		<input type="submit" value="Buy">
	</form>
</body>
</html>