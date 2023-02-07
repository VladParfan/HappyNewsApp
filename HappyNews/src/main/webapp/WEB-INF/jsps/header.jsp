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

	<header>
		<div>
			
		</div>
<!----------- menu bar with categories --------------->
		<div class="menu-categories">
		<c:choose>
		<c:when test="${loggedIn == true}">
		<a>You are logged in as ${user.username}</a>
		<a href="/logout">Logout</a>
		<a href="/showProfile">See Profile</a>
		<a href="/goToAddArticle">Add article</a>
		</c:when>
		<c:otherwise>
		      		<a href="/login">Login</a>
		      		<a href="/registration">Register</a>
			<!-- <a href="/login">People</a> 
			<a href="/login">Culture</a>
			<a href="/login">Environment</a> 
			<a href="/login">Science</a>
			<a href="/login">Economics</a> 
			<a href="/login">Lifestyle</a> -->
			
			</c:otherwise>
				</c:choose>
		</div>
		<div>
		
            <a href="/toPeople">People</a> 
			<a href="/toCulture">Culture</a>
			<a href="/toEnvironment">Environment</a> 
			<a href="/toScience">Science</a>
			<a href="/toEconomics">Economics</a> 
			<a href="/toLifestyle">Lifestyle</a>
			<a href="/">Home</a>
			</div>
	</header>



</body>
</html>