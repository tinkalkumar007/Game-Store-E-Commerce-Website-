<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Internal Server Error</title>
</head>
<body>
	<div align="center">
		<img alt="logo"
			src="${pageContext.request.contextPath}/images/GamestoreLogo.png" />
	</div>
	<div align="center">
		<h2>Sorry, the server has encountered an error.</h2>
		<h3>Please check back later.</h3>
	</div>
	<div align="center">
		<a href="javascript:history.go(-1);">Go Back</a>
	</div>
</body>
</html>