<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
		<c:when test="${orders.size()==0}">
			<H1>No orders</H1>
		</c:when>
		<c:otherwise>
			<form:form action="/cartservice/" method="get" commandName="orders">
				<table border="1" class="names">
					<thead>
						<tr>
						<th>ID</th>
						<th>Date</th>
						<th>Total Items</th>
						<th>Amount</th>
						<th>Payment Type</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${orders}" var="entry">
						<tr>
							<td>${entry.getId()}</td>
							<td>${entry.getDate()}</td>
							<td>${entry.getItems().size()}</td>
							<td>${entry.getTotalAmt()}</td>
							<td>${entry.getPaymentType()}</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				<input type="submit" value="Go to home" class="btn btn-large btn-block btn-inverse">
			</form:form>
		</c:otherwise>
	</c:choose>
</body>
</html>