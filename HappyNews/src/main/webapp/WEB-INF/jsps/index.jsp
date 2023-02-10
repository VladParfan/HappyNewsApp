<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp" />
<jsp:include page="searchbar.jsp" />
		
		<br></br>
		<hr>
<div>

<span style="color: red">${errorMessage}</span>
			
<!-- ========Latest Articles================== -->
<h1>Latest Articles</h1>
  <c:forEach var="article" items="${latestArticles}">
    <p>
      <b><a href="goToArticlePage/${article.articleId}">${article.title}</a></b><br>
      Date: ${article.publicationDate}<br>
      Location: ${article.location}<br>
      Author: ${article.author.username}<br>
      Category: ${article.category}
    </p>
  </c:forEach>



<jsp:include page="footer.jsp" />
</body>
</html>