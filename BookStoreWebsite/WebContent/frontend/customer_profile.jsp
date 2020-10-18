<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Profile - Online Bookstore</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<br>
		<h2>Welcome, ${loggedCustomer.fullname}</h2>
		<br>
	</div>
	
	<table align="center" class= "normal">
		<tr>
			<td><b>Email Adress:</b></td>	
			<td>${loggedCustomer.email}</td>	
		</tr>
		<tr>
			<td><b>Full Name:</b></td>	
			<td>${loggedCustomer.fullname}</td>	
		</tr>
		<tr>
			<td><b>Phone Number:</b></td>	
			<td>${loggedCustomer.phone}</td>	
		</tr>
		<tr>
			<td><b>Address:</b></td>	
			<td>${loggedCustomer.address}</td>	
		</tr>
		<tr>
			<td><b>City:</b></td>	
			<td>${loggedCustomer.city}</td>	
		</tr>
		<tr>
			<td><b>Zip Code:</b></td>	
			<td>${loggedCustomer.zipcode}</td>	
		</tr>
		<tr>
			<td><b>Country:</b></td>	
			<td>${loggedCustomer.country}</td>	
		</tr>
		<tr>
			<td colspan = "2">&nbsp;</td>
		</tr>
		<tr>
			<td colspan = "2" align="center"><b><a href="edit_profile">Edit My Profile</a></b></td>
		</tr>
	</table>

	<jsp:directive.include file="footer.jsp" />

</body>
</html>