<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.7.1.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>
<title>Your Review - Online Game Store</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<form id="reviewForm">
			<table class="form" width="60%">
				<tr>
					<td><h2>You already wrote review for this game</h2></td>
					<td>&nbsp;</td>
					<td><h2>${loggedCustomer.firstname}</h2></td>
				</tr>
				<tr>
					<td colspan="3"><hr /></td>
				</tr>
				<tr>
					<td align="center"><span id="game_title"><b>${game.title}</b>
					</span><br /> <img alt="image"
						src="data:image/jpg;base64,${game.base64Image}" width="220px"
						height="330px"></td>
					<td>
						<div id="rateYo"></div> 
						<br/>
						<input type="text" size="60" id="headline" name="headline" readonly="readonly" value="${review.headline }"><br />
						<br /> <textarea id="comment" name="comment" readonly="readonly"
							rows="10" cols="70"> ${review.comment} </textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#rateYo").rateYo({
			starWidth : "40px",
			fullStar : true,
			rating: ${review.rating},
			readOnly: true
		});
	});
</script>
</html>