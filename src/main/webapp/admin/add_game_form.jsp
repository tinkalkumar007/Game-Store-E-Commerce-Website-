<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Games - Game Store</title>
</head>
<body>
	<div align="center">
		<h2>Add Game to order ID: ${order.orderId}</h2>
		<form action="add_game_to_order" method="post">
			<table class="form">
				<tr>
					<td>Select a game :</td>
					<td><select name="gameId">
							<c:forEach items="${gameList}" var="game" varStatus="status">
								<option value="${game.gameId}">${game.title}-${game.creator }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>Number of Copies:</td>
					<td><select name="quantity">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
					</select></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="center"> <button type="submit"> Add</button> &nbsp;
					&nbsp;&nbsp;&nbsp;
					<button onclick="javascript:self.close();">Cancel</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>