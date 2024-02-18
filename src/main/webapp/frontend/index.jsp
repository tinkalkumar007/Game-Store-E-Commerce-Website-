<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Game store Website</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<div align="center" style="width: 80%; margin: 0 auto;">
			<h2>New Games</h2>
			<c:forEach items="${listNewGames}" var="game">
				<jsp:directive.include file="game_group.jsp" />
			</c:forEach>
		</div>

		<div class="next-row" style="clear: both">
			<h2>Best-Selling Games</h2>

			<c:forEach items="${listBestSellingGames}" var="game">
				<jsp:directive.include file="game_group.jsp" />
			</c:forEach>
		</div>

		<div class="next-row" style="clear: both">
			<h2>High-Rating Games</h2>

			<c:forEach items="${listMostRatedGames}" var="game">
				<jsp:directive.include file="game_group.jsp" />
			</c:forEach>
		</div>
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
</html>