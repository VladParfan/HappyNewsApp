<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<c:url value="/css/style.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet">
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

<h1 >Top Articles</h1>
<div class="center" >
<!-- ========Latest Articles================== -->
 	
 	
 	<div class="articles-view">
 	
 	
  <c:forEach var="article" items="${topArticles}" varStatus="status">
    <div class="max" id="col${status.index % 3 + 1}">
      
      <img src="pictures/pic1.jpg" class="img-article" />
     <b><a  class="products1 colo" href="goToArticlePage/${article.articleId}">${article.title}</a></b><br>
      <small>
     <b> Date:</b> ${article.publicationDate}<br>
     <b> Location:</b> ${article.location}<br>
      <b> Author: </b> ${article.author.username}<br>
      <b>Category:</b> <a  href="/${article.category}">${article.category}<br>
    
   </small>
   
    </div>
  </c:forEach>		
</div>
</div>

<!-- ========Latest Articles================== -->
<h1>Latest Articles</h1>
<div class="center" >

 	<div class="articles-view">
 	
  <c:forEach var="article" items="${latestArticles}">
    <div class="max" id="col${status.index % 3 + 1}">
    
    <small>
    <img src="pictures/pic1.jpg" class="img-article" />
      <b><a class="products1 colo" href="goToArticlePage/${article.articleId}">${article.title}</a></b><br>
     <b> Date: </b>${article.publicationDate}<br>
      <b>Location:</b> ${article.location}<br>
     <b> Author:</b>  <a href="/articles/${article.author.username}">${article.author.username}</a><br>
      <b>Category:</b> <a  href="/${article.category}">${article.category}</a><br>
   
    </small>
   
    </div>
  </c:forEach>
</div>
</div>
<!-- ========RECOMMENDED ARTICLES================== -->

<c:choose>
					<c:when test="${loggedIn == true}">
		<c:choose>			
		<c:when test ="${articlesByUserCategories.size() != 0 }">
<h1>Recommended Articles</h1>


<!-- ========Latest Articles================== -->
<div class="center" >

 	<div class="articles-view">
 	
  <c:forEach var="article" items="${articlesByUserCategories}">
    <div class="max" id="col${status.index % 3 + 1}">
    
    <small>
    <img src="pictures/pic1.jpg" class="img-article" />
      <b><a class="products1 colo" href="goToArticlePage/${article.articleId}">${article.title}</a></b><br>
      Date: ${article.publicationDate}<br>
      Location: ${article.location}<br>
      Author: ${article.author.username}<br>
      Category: ${article.category}
    
    </small>
   
    </div>
  </c:forEach>
  </c:when>
  <c:otherwise>
  <a href="/subscription">Go To Subscribe</a>
  </c:otherwise>
  </c:choose>
  </c:when>
 </c:choose>

</div>
<br><br>
</div>



<jsp:include page="footer.jsp" />
</body>
</html>