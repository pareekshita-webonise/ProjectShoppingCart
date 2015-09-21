<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<spring:url value="/resources/css/signup.css" var="mainCss" />
<link href="${mainCss}" rel="stylesheet" />
<title>Sign up</title>

<script type="text/javascript">
	reqObj = null;
	var allFieldsBlank = false;
	var registeredUser = false;

	function verifyUser() {

		document.getElementById("res").innerHTML = "Checking";
		var token = document.getElementById("token").value;
		var header = document.getElementById("header").value;
		console.log(header+" "+token);
		if (window.XMLHttpRequest) {
		{
			reqObj = new XMLHttpRequest();
		}
		} else {
			reqObj = new ActiveXObject("Microsoft.XMLHTTP");
		}

		
		reqObj.onreadystatechange = process;
		reqObj.open("POST", "./check?id="
				+ document.getElementById("username").value, true);
		reqObj.setRequestHeader(header, token);
		reqObj.send(null);
	}
	
	function process() {
		console.log(reqObj.readyState);
		var resDiv = document.getElementById("res");
		if (reqObj.readyState == 4) {
			console.log(reqObj.responseText);
			if(reqObj.responseText == "FAIL")
			{
				resDiv.style="color:red;";
				resDiv.innerHTML="User alread registered";
				registeredUser = true;
			}
			else
			{
				resDiv.style="color:green;";
				resDiv.innerHTML="OK";
				registeredUser = false;
			}
		}
	}

	function formSubmit() {
		document.getElementById("logoutForm").submit();
	}

	function signupSubmit() {		
		var boxes = document.getElementsByTagName("input");
		for (i = 0; i < boxes.length; i++) {
			if ((boxes[i].type == "text" || boxes[i].type == "password")
					&& boxes[i].value == "")
				allFieldsBlank = true;
		}
		console.log(allFieldsBlank);
		var form = document.getElementById("signupForm");
		if (allFieldsBlank == true) {
			form.method="get";
			alert("Please fill all the details");
			allFieldsBlank=false;
		}
		else if(registeredUser==true)
		{
			form.method="get";
			alert("Please provide unique username");
			registeredUser=false;
		}
		else if(registeredUser==false && allFieldsBlank == false)
		{
			form.method="post";
			form.submit();
		}
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
		<div class="signup">
			<c:choose>
				<c:when test="${pageContext.request.userPrincipal.name == null}">
					<form:form action="saveuser" method="post" modelAttribute="user"
						id="signupForm">
						<p>Username:</p>
						<form:input path="username" type="text" placeholder="Enter Email"
							id="username" onblur="verifyUser();" />
						<span id="res" style="color: red;"></span>
						<br />
						<br>
						<p>Password:</p>
						<form:input path="password" type="password"
							placeholder="Enter Password" />
						<br>
						<p>First name:</p>
						<form:input path="firstName" type="text"
							placeholder="Enter First Name" />
						<br>
						<p>Last name:</p>
						<form:input path="lastName" type="text"
							placeholder="Enter Last Name" />
						<br>
						<p>Shipping Address:</p>
						<form:input path="address" type="text"
							placeholder="Enter  Address" value="" maxlength="200"/>
						<br>
						<p>Contact Number:</p>
						<form:input path="contactNo" type="text"
							placeholder="Enter Mobile Number" value="" />
						<br>
						<form:input type="button" value="Sign up" path=""
							class="btn btn-large btn-block btn-inverse"
							onclick="javascript:signupSubmit()" />
					</form:form>
					<br>
					<div style="color: red; text-align: center;">${message}</div>
				</c:when>
				<c:otherwise>
					<h1>Please logout the current account to Sign up for new
						Account</h1>
				</c:otherwise>
			</c:choose>

		</div>
	</div>
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" id="token"/>	
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.headerName}" id="header"/>	
	</form>
</body>
</html>