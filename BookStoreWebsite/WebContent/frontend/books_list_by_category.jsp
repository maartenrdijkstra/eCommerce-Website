<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Books in ${category.name} - Online Bookstore</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div class="center">
		<h2>${category.name}</h2>
	</div>
	
	<div class="left myStyle">
		<c:forEach items="${listBooks}" var="book">
			<div class="myStyle2 center">
				<div>
					<a href="view_book?id=${book.bookId}">
						<img class="book-small" src="data:image/jpg;base64,${book.base64Image}" />
					</a>
				</div>
				<div>
					<a href="view_book?id=${book.bookId}">
						<b>${book.title}</b>
					</a>
				</div>
				<div>
					<jsp:directive.include file="book_rating.jsp" />
				</div>
				<div><i>by ${book.author}</i></div>
				<div><b>$ ${book.price}</b></div>
			</div>
		</c:forEach>
	</div>

	<jsp:directive.include file="footer.jsp" />
</body>
</html>