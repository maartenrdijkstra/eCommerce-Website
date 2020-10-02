<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Evergreen Books - Online Book Store</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2>New Books:</h2>
		<div align="center" style="width:80%; margin: 0 14%">
			<c:forEach items="${listNewBooks}" var="book">
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
	</div>
	<div align="center" style="clear:both">	
		<h2>Best-Selling Books:</h2>
	</div>
	<div align="center" style="clear:both">
		<h2>Most-favored Books:</h2>
	</div>

	<jsp:directive.include file="footer.jsp" />
</body>
</html>