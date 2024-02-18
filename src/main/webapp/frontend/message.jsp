<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/style.css">
<meta charset="ISO-8859-1">
<title>Game Store - Online Game Store</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<br>
		<h3>${message}</h3>
	</div>
	<div align="center">
		<h3>
			<a href="javascript:history.go(-1);">Go Back</a>
		</h3>
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
</html>