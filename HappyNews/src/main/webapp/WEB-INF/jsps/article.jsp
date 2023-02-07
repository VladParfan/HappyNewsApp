<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Article</title>
<c:url value="/css/style.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet">
</head>
<body>

<jsp:include page="header.jsp" />
as
<h2>${article.title}</h2><br>
${article.articleText}<br>
Date: ${article.publicationDate}<br>
Location: ${article.location}<br>
Author: ${article.author.username}
Category: ${article.category}
<jsp:include page="footer.jsp" />


</body>
</html>