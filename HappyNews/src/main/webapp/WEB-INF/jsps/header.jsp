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
			<a href="/">Home</a>
		</div>





<!----------- menu bar with categories --------------->
		<div class="menu-categories">
			<a href="/toPeople">People</a> 
			<a href="/toCulture">Culture</a>
			<a href="/toEnvironment">Environment</a> 
			<a href="/toScience">Science</a>
			<a href="/toEconomics">Economics</a> 
			<a href="/toLifestyle">Lifestyle</a>
		</div>

	</header>



</body>
</html>