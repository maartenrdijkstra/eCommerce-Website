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
	
	<div align="center">
		<h2>${category.name}</h2>
	</div>
	
	<div align="left" style="width:80%; margin: 0 14%">
		<c:forEach items="${listBooks}" var="book">
			<div style="display: inline-block; text-align: center; min-width: 95px; 
			vertical-align:top; width: 150px; margin: 20px; max-width: 15%">
				<div>
					<a href="view_book?id=${book.bookId}">
						<img src="data:image/jpg;base64,${book.base64Image}" width="128" 
						height="164"/>
					</a>
				</div>
				<div>
					<a href="view_book?id=${book.bookId}">
						<b>${book.title}</b>
					</a>
				</div>
				<div>Rating *****</div>
				<div><i>by ${book.author}</i></div>
				<div><b>$ ${book.price}</b></div>
			</div>
		</c:forEach>
	</div>

	<jsp:directive.include file="footer.jsp" />
</body>
</html>