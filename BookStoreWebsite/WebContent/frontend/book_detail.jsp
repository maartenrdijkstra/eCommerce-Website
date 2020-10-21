<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${book.title} - Online Book Store</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div class="center">
		<table class="book">
			<tr>
				<td colspan="3" class="left">
					<div class="left">
						<p id="book-title">${book.title}</p>
						by <span id="book-author">${book.author}</span>
					</div>	
				</td>
			</tr>	
			<tr>
				<td rowspan="2">
					<img class="book-large" src="data:image/jpg;base64,${book.base64Image}"/>
				</td>
				<td valign="top" align="left">
					<jsp:directive.include file="book_rating.jsp" />
					<a href="#reviews">${fn:length(book.reviews)}
					${book.reviews.size() eq 1 ? " Review" : " Reviews"}
					
					</a>
				</td>
				<td valign="top" rowspan="2" width="20%">
					<h2>$${book.price}</h2>
					<br><br>
					<button type="submit">Add to Cart</button>
				</td>
			</tr>	
			<tr>
				<td id="description">
					${book.description}
				</td>
			</tr>
			<tr>
			<tr><td colspan="2">&nbsp;</td></tr>
			<tr>
				<td>
					<h2><a id="reviews">Customer Reviews</a></h2>
				</td>
				<td colspan="2" align="center">
					<button>Write a Customer Review</button>
				</td>
			</tr>
			
			<tr>
				<td colspan = "3" align="left">
					<table border="0" class="normal">
						<c:forEach items="${book.reviews}" var="review">
							<tr>
								<td>
									<c:forTokens items="${review.stars}" delims="," var="star">
										<c:if test="${star eq 'on'}">
											<img alt="star" src="images/rating_on.png">
										</c:if>
													
										<c:if test="${star eq 'off'}">
											<img alt="star" src="images/rating_off.png">
										</c:if>
									</c:forTokens>
									- <b>${review.headline}</b>
								</td>
							</tr>
							<tr>
								<td>
									by ${review.customer.fullname} on ${review.reviewTime}
								</td>
							</tr>
							<tr><td><i>${review.comment}</i></td></tr>
							<tr><td>&nbsp;</td></tr>
						</c:forEach>
					</table>
				</td>
			</tr>
		</table>
	</div>

	<jsp:directive.include file="footer.jsp" />
</body>
</html>