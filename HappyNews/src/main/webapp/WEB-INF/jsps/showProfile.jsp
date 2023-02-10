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

<div>
<h2>Profile ${user.username}</h2>

	<div class="users">
		<div class="div1">
			
			<b>Username:</b> ${user.username}<br><br> 
			<b>Email:</b> ${user.email}<br><br><br>
			<b>Role:</b> ${user.role}<br><br> 
			
			
		</div>
		<div class="div1">
			<a class="button" href="/goEditProfilePage">Edit Profile</a> <br> <br> <br> 
			<a class="button" href="/goChangePasswordPage">Change Password</a> <br><br> <br> 
			<a class="button" href="/goShowArticlesOfUser/${user.username}">Show my articles</a><br><br><br> 
		</div>
	</div>
	</div>

	<a href="/" ><small class=colo>Go to homepage</small></a>



<jsp:include page="footer.jsp" />


</body>
</html>