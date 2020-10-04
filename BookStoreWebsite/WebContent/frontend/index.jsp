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
	<div class="center">
		<div>
			<h2>New Books:</h2>
			<div class="myStyle">
				<c:forEach items="${listNewBooks}" var="book">
					<div class="newBooks">
						<div>
							<a href="view_book?id=${book.bookId}"> 
							<img class="book-small" src="data:image/jpg;base64,${book.base64Image}" />
							</a>
						</div>
						<div>
							<a href="view_book?id=${book.bookId}"> <b>${book.title}</b>
							</a>
						</div>
						<div>Rating *****</div>
						<div>
							<i>by ${book.author}</i>
						</div>
						<div>
							<b>$ ${book.price}</b>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="next-row">
			<h2>Best-Selling Books:</h2>
		</div>
		<div class="next-row">
			<h2>Most-favored Books:</h2>
		</div>
		<br>
		<br>
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
</html>