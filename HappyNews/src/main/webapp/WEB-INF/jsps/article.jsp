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
<!-- -------------------------------------------------------- -->
<!-- adding article -->

<h2>${article.title}</h2><br>
${article.articleText}<br>
Date: ${article.publicationDate}<br>
Location: ${article.location}<br>
Author: ${article.author.username}
Category: ${article.category}

<!-- adding comment -->
<%-- <form action="/addComment" method="post" >

<input type="hidden" name="commentator" value="${user.username}"><br>
<input type="hidden" name="articleId" value="${article.articleId}"><br>
Text:<input type="text" name="commentText" placeholder="Text" required /><br>
<input type="submit" value="Add new comment" />
</form>

<c:forEach items="${listOfCommentsOfArticle}" var="comment">
<tr>
<td>${user.username}</td>
<td>${comment.publicationTime}</td>
<td>${comment.commentText}</td>

</tr>
</c:forEach> --%>
<form action="/addComment" method="post">
  <!-- Add new comment form -->
  <input type="hidden" name="commentator" value="${user.username}">
  <input type="hidden" name="articleId" value="${article.articleId}">
  Text: <input type="text" name="commentText" placeholder="Text" required />
  <input type="submit" value="Add new comment" />
</form>

<c:forEach items="${listOfCommentsOfArticle}" var="comment">
  <!-- Loop through each comment -->
  <c:if test="${comment.parentComment == null}">
    <!-- Check if the comment is not a reply -->
    <br>
    ------------------------------
    <br>
    <table>
      <tr>
        <td>${user.username}</td>
        <td>${comment.publicationTime}</td>
        <td>${comment.commentText}</td>
        <td>
          <!-- Add reply form -->
          <form action="/addCommentReply" method="post">
            <input type="hidden" name="commentator" value="${user.username}">
            <input type="hidden" name="articleId" value="${article.articleId}">
            <input type="hidden" name="parentCommentId" value="${comment.commentId}">
            Reply <input type="text" name="commentText" placeholder="Text" required />
            <input type="submit" value="Add reply" />
          </form>
        </td>
      </tr>
    </table>
    <br>

    <c:forEach items="${listOfCommentsOfArticle}" var="reply">
      <!-- Loop through each reply -->
      <c:if test="${reply.parentComment != null && reply.parentComment.commentId == comment.commentId}">
        <!-- Check if the reply is a reply to the comment -->
        <table>
          <tr>
            <td></td>
            <td>${user.username}</td>
            <td>${reply.publicationTime}</td>
            <td>${reply.commentText}</td>
           <%--  <td>
              <!-- Add reply to reply form -->
              <form action="/addCommentReply" method="post">
                <input type="hidden" name="commentator" value="${user.username}">
                <input type="hidden" name="articleId" value="${article.articleId}">
                <input type="hidden" name="parentCommentId" value="${reply.commentId}">
                Reply to reply <input type="text" name="commentText" placeholder="Text" required />
                <input type="submit" value="Add reply to reply" />
              </form>
            </td> --%>
          </tr>
        </table>
      </c:if>
    </c:forEach>
  </c:if>
</c:forEach>
<!-- -------------------------------------------------------- -->
<jsp:include page="footer.jsp" />


</body>
</html>