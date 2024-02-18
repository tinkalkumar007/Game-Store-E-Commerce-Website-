<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>
<title>Write a Review - Online Game Store</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<form id="reviewForm" action="submit_review" method="post">
			<table class="form" width="60%">
				<tr>
					<td><h2>Your Reviews</h2></td>
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
						<input type="hidden" id="rating" name="rating" /> 
						<input type="hidden" name="game_id" value="${game.gameId }" /> <br /> 
						<input type="text" size="60" id="headline" name="headline" placeholder="headline or summary of your review(required)"><br />
						<br /> <textarea id="comment" name="comment" placeholder="Write review in details..."
							rows="10" cols="70"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="3" align="center">
						<button type="submit">Submit</button> &nbsp;&nbsp;
						<button type="button" onclick="javascript:history.go(-1);">Cancel</button>

					</td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#reviewForm").validate({
			rules : {
				headline : "required",
				comment : "required"
			},

			messages : {
				headline : "Please enter headline",
				comment : "Please enter review details"
			}
		})
		$("#rateYo").rateYo({
			starWidth : "40px",
			fullStar : true,
			onSet : function(rating, rateYoInstance) {
				$("#rating").val(rating);
			}
		});
	});
</script>
</html>