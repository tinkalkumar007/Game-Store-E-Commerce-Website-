<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Games in ${category.name}- Game Store</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2>${category.name }</h2>
	</div>
	<div align="center" style="width: 80%; margin: 0 auto;">
		<c:forEach var="game" items="${gameList }">
			<div style="float: left; display: inline-block; margin: 20px">
				<div>
					<a href="view_game?id=${game.gameId}"> <img
						src="data:image/jpg;base64,${game.base64Image}" width="128"
						height="164" />
					</a>
				</div>
				<div>
					<a href="view_game?id=${game.gameId}"><b>${game.title }</b></a>
				</div>
				<div>
					<jsp:directive.include file="game_rating.jsp" />
				</div>
				<div>
					<i>By ${game.creator }</i>
				</div>
				<div>
					<b>$ ${game.price }</b>
				</div>
			</div>
		</c:forEach>
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
</html>