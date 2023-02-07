<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<c:url value="/css/style.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet">
</head>
<body>

	<jsp:include page="header.jsp" />
<span style="color: red">${errorMessage}</span>

	<form method="post" action="/process_login">
		<h1>LOGIN</h1>
		<div class="input">
			<p>Email:</p>
			<input type="email" name="username" id="username">
		</div>
		<div>
			<p>Password:</p>
			<input type="password" name="password">
		</div>
		<div>
			<input class="button" type="submit" value="Log in">
		</div>
		<div>
			<a href="/auth/registration">Register</a>
		</div>
		<div>
			<a href="/auth/recover-password">Reset password</a>
		</div>




		<jsp:include page="footer.jsp" />
</body>
</html>