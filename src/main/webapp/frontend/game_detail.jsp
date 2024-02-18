<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${game.title }-OnlineGameStore</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>

</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<table width="80%" style="border: 0;">
			<tr>
				<td colspan="3" align="left">
					<h2>${game.title }</h2> <i>Developer: ${game.creator }</i>
				</td>
			</tr>
			<tr>
				<td rowspan="2"><img alt="image"
					src="data:image/jpg;base64,${game.base64Image}" width="220px"
					height="330px"></td>
				<td valign="top" align="left" height="20px"><jsp:directive.include
						file="game_rating.jsp" /> &nbsp;&nbsp; <a href="#reviews">${fn:length(game.reviews) }
						Reviews </a></td>
				<td valign="top" width="20%"><b>$ ${game.price }</b> <br /> <br />
					<button id="addToCartBtn">Add to Cart</button></td>
			</tr>
			<tr>
				<td valign="top" style="text-align: justify;">
					${game.description }</td>
			</tr>
			<tr>
				<td><h2>
						<a id="reviews">Customer Reviews</a>
					</h2></td>
				<td colspan="2" align="center">
					<button id="writeReviewBtn">Write Customer Review</button>
				</td>
			</tr>
			<tr>
				<td colspan="3" align="left">
					<table class="form">
						<c:forEach items="${game.reviews}" var="review">
							<tr>
								<td><c:forTokens items="${review.stars}" delims=","
										var="star">
										<c:if test="${star eq 'on'}">
											<img src="images/rating_on.png" />
										</c:if>
										<c:if test="${star eq 'off'}">
											<img src="images/rating_off.png" />
										</c:if>
									</c:forTokens> - <b>${review.headline }</b></td>
							</tr>
							<tr>
								<td>by ${review.customer.firstname }
									${review.customer.lastname } on ${review.reviewTime }</td>
							</tr>
							<tr>
								<td><i>${review.comment } </i></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#writeReviewBtn").click(function() {
			window.location = "write_review?game_id="+${game.gameId};
		});
		$("#addToCartBtn").click(function() {
			window.location = "addto_cart?game_id="+${game.gameId};
		});
	});
</script>
</html>