<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>
	<c:if test="${user != null}"> 
				Edit User
			</c:if>
			<c:if test="${user == null}"> 
				Create New User
			</c:if>
</title>
</head>

<body>
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h2>
			<c:if test="${user != null}"> 
				Edit User
			</c:if>
			<c:if test="${user == null}"> 
				Create New User
			</c:if>
		</h2>
	</div>
	<div align="center">
		<c:if test="${user != null}"> 
			<form action="update_user" method="post" onsubmit="return validateFormInput()">
			<input type="hidden" name="userId" value="${user.userId}">
		</c:if>
	
		<c:if test="${user == null}">
		<form action="create_user" method="post"
			onsubmit="return validateFormInput()">
		</c:if>
			<table>
				<tr>
					<td align="right">Email:</td>
					<td align="left"><input type="text" id="email" name="email"
						size="20" value="${user.email}"></td>
				</tr>
				<tr>
					<td align="right">Full Name:</td>
					<td align="left"><input type="text" id="fullname"
						name="fullname" size="20" value="${user.fullName}"></td>
				</tr>
				<tr>
					<td align="right">Password:</td>
					<td align="left"><input type="password" id="password"
						name="password" size="20" value="${user.password}"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Save"> <input type="button" value="Cancel"
						onclick="javascript:history.go(-1);"></td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript">
	function validateFormInput() {
		var fieldEmail = document.getElementById("email");
		var fieldFullName = document.getElementById("fullname");
		var fieldPassword = document.getElementById("password");

		if (fieldEmail.value.length == 0) {
			alert("Email is required");
			fieldEmail.focus();
			return false;
		}

		if (fieldFullName.value.length == 0) {
			alert("Full Name is required");
			fieldFullName.focus();
			return false;
		}

		if (fieldPassword.value.length == 0) {
			alert("Password is required");
			fieldPassword.focus();
			return false;
		}

		return true;
	}
</script>
</html>