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

<h1>Subscription Categories</h1> <br>
								<br>
			<c:forEach var="category" items="${categoriesByUser}">
			${category.name}
			<form action="/deleteCategory" method="get" >
			<input type="hidden" name="categoryName" value="${category.name}">
			<br>
			<input class=button type="submit" value="Delete" />
			</form>
			</c:forEach><br> <br>
					
			
			<form action="/addCategory" method="post" >
		Category:<select name="category" required><br> 	
				<option value="people">people</option>
				<option value="culture">culture</option>
				<option value="environment">environment</option>
				<option value="science">science</option>
				<option value="economics">economics</option>
				<option value="lifestyle">lifestyle</option>
				</select>

			<input class=button2 type="submit" value="Add to your subscription" />
		
	</form>
<br>
<br>
<jsp:include page="footer.jsp" />


</body>
</html>