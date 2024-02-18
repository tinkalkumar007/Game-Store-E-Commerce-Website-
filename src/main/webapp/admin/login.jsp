<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>

<title>Admin Login</title>
</head>
<body>
	<div align="center">
		<h1>Game Store Adminstration</h1>
	</div>
	<div align="center">
		<h2>Admin login</h2>
	</div>
	<c:if test="${message != null}">
		<div align="center">
			<h4 class="message"><i>${message}</i></h4>
		</div>
	</c:if>
	<div align="center">
		<form action="login" id="loginForm" method="post">
			<table class="form">
				<tr>
					<td align="right">E-mail:</td>
					<td align="left"><input type="email" id="email" name="email"></td>
				</tr>
				<tr>
					<td align="right">Password:</td>
					<td align="left"><input type="password" id="password" name="password"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button type="submit">Login</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#loginForm").validate({
			rules : {
				email : {
					required : true,
					email : true
				},
				password : "required"
			},

			messages : {
				email : {
					required : "Please enter Email.",
					email : "Please enter a valid email address."
				},
				password : "Please enter Password."
			}
		})
	});
</script>
</html>