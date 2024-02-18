<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>

<title>Shopping Cart</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<c:if test="${message != null}">
			<div align="center">
				<h4 class="message">
					<i>${message}</i>
				</h4>
			</div>
		</c:if>
		<c:set var="cart" value="${sessionScope['cart']}" />
		<c:if test="${cart.getTotalItems()==0 }">
			<h2>Your cart is empty!</h2>
			<h2><a href="${pageContext.request.contextPath}/">Shop Now</a></h2>
			
		</c:if>
		<c:if test="${cart.getTotalItems()>0 }">
		    <h2>Your cart details</h2>
			<form action="update_cart" method="post" id="cartForm">
				<div>
					<table border="1" cellpadding="5">
						<tr>
							<th>No.</th>
							<th colspan="2">Game</th>
							<th>Quantity</th>
							<th>Price</th>
							<th>SubTotal</th>
							<th>Action</th>
						</tr>
						<c:forEach items="${cart.getItems() }" var="item"
							varStatus="status">
							<tr>
								<td>${ status.index+1 }</td>
								<td align="center"><img
									src="data:image/jpg;base64,${item.key.base64Image}" width="128"
									height="164" /></td>
								<td><span id="game-title">${ item.key.title }</span></td>
								<td>
								<input type="hidden" name="gameId" value="${item.key.gameId }"/>
								<input type="number" style="width:80px;" name="quantity${status.index+1}"
									value="${ item.value }"/>
								</td>
								<td><fmt:formatNumber value="${ item.key.price }"
										type="currency" /></td>
								<td><fmt:formatNumber
										value="${ item.value*item.key.price }" type="currency" /></td>
								<td><a href="remove_from_cart?game_id=${item.key.gameId }">
										Remove</a></td>
							</tr>
						</c:forEach>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td>${cart.getTotalQuantity() } Game(s)</td>
							<td>Total:</td>
							<td colspan="2"><b><fmt:formatNumber
										value="${cart.getTotalAmount() }" type="currency" /></b></td>
						</tr>
					</table>
				</div>
				<div>
					<table class="form">
						<tr>
							<td><button type="submit">Update</button></td>
							<td><input type="button" id="clearCartBtn" value="Clear Cart"/></td>
							<td><a href="${pageContext.request.contextPath}/">
									Continue Shopping </a></td>
							<td><a href="check_out"> Check Out </a></td>
						</tr>
					</table>
				</div>
			</form>
		</c:if>
	</div>

	<jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#cartForm").validate({
			rules : {
				<c:forEach items="${cart.getItems() }" var="item" varStatus="status">
				quantity${status.index+1}:{
					required : true, number: true, min: 1,
				},
				</c:forEach>
			},

			messages : {
				<c:forEach items="${cart.getItems() }" var="item" varStatus="status">
				quantity${status.index+1}:{
					required : "Please enter quantity",
					number : "Quantity must be an integer",
					min : "Quantity must be greater than 0",
				},
				</c:forEach>
			}
		});
		$("#clearCartBtn").click(function() {
			window.location = "clear_cart";
		});
	});
</script>
</html>