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

<form  method="post" action="@{/auth/registration}" th:object="${happyUser}">
				 <h1>REGISTER</h1>
				 <div class="input">
					 <p>Name:</p>
					 <input type="text" name="username" id="username" required/>
					 
				 </div>
				 <div class="input">
					 <p>Email:</p>
					 <input type="email" name="email">
					 <div style="color:red" th:if="${#fields.hasErrors('username')}" th:errors="*{username}"> Email error </div>
				 </div>
				 <div class="input">
					 <p>Password:</p>
					 <input type="password" name="password" th:field="*{password}" id="password">
				 </div>
				 <div class="input">
					 <p>Confirm password:</p>
					 <input type="password" name="passwordConfirmation" id="confirm_password">
					 <div style="color:red" th:if="${#fields.hasErrors('password')}" th:errors="*{password}"> Password error </div>
				 </div>
				 <div class="center">
				 	<input class="button" type="submit" value="Create account">
				 </div>
				 <div class="register">
				 	<a th:href="@{/auth/login}">Login</a>
				 </div>





<jsp:include page="footer.jsp" />


</body>
</html>