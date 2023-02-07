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
	<h2>Send Article</h2>

	<form action="/addArticle" method="post" >
		Title:<input type="text" name="title" placeholder="Title" required /><br>
		Text:<input type="text" name="articleText" placeholder="Text" required /><br>
		Location:<input type="text" name="location"	placeholder="article Text" required /><br> 
		
		Category:<select name="category" required><br> 	
				<option value="people">people</option>
				<option value="culture">culture</option>
				<option value="environment">environment</option>
				<option value="science">science</option>
				<option value="science">economics</option>
				<option value="lifestyle">lifestyle</option>
				</select>
			<input type="hidden" name="author" value="${user.username}"><br>
			
			<input type="submit" value="Add new article" />
			
			</select>
	</form>



	<jsp:include page="footer.jsp" />


</body>
</html>