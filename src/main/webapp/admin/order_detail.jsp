<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>

<title>Order Detail - Game store</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h2 class="pageheading">Details of Order ID: ${order.orderId }</h2>
	</div>

	<div align="center">
		<h3>Order Overview</h3>
		<table class="form">
			<tr>
				<td><b>Ordered By:</b></td>
				<td>${order.customer.firstname}</td>
			</tr>
			<tr>
				<td><b>Game Copies:</b></td>
				<td>${order.getGameCopies()}</td>
			</tr>
			<tr>
				<td><b>Total Amount:</b></td>
				<td><fmt:formatNumber value="${order.total}" type="currency" /></td>
			</tr>
			<tr>
				<td><b>Recipient Name:</b></td>
				<td>${order.firstname}</td>
			</tr>
			<tr>
				<td><b> Recipient Number: </b></td>
				<td>${order.phone}</td>
			</tr>
			<tr>
				<td><b>Ship To:</b></td>
				<td>${order.city},${order.state},${order.country}</td>
			</tr>
			<tr>
				<td><b>Payment Method:</b></td>
				<td>${order.paymentMethod}</td>
			</tr>
			<tr>
				<td><b>Order Status:</b></td>
				<td>${order.status}</td>
			</tr>
			<tr>
				<td><b>Order Date:</b></td>
				<td>${order.orderDate}</td>
			</tr>
		</table>
	</div>
	<div align="center">
		<h2>Ordered Games</h2>
		<table border="1" cellpadding="5">
			<tr>
				<th>Index</th>
				<th>Game</th>
				<th>Creator</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>SubTotal</th>
			</tr>
			<c:forEach items="${order.orderDetails}" var="orderDetail"
				varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td><img style="vertical-align:middle"
						src="data:image/jpg;base64,${orderDetail.game.base64Image}" width="48"
						height="64" />${orderDetail.game.title}</td>
					<td>${orderDetail.game.creator }</td>
					<td><fmt:formatNumber value="${orderDetail.game.price }"
							type="currency" /></td>
					<td>${orderDetail.quantity }</td>
					
					<td><fmt:formatNumber value="${orderDetail.subtotal }"
							type="currency" /></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="4" align="right"><b><i>Total:</i></b></td>
				<td><b>${order.getGameCopies()} Game(s)</b></td>
				<td><b><fmt:formatNumber value="${order.total }"
							type="currency" /></b></td>
				
			</tr>
		</table>
	</div>
	<br/>
	<div align="center">
		<a href="edit_order?id=${order.orderId}">Edit this Order</a> 
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="delete_order?id=${order.orderId}">Delete this Order</a>

	</div>

	<jsp:directive.include file="footer.jsp" />

	<script>
		$(document)
				.ready(
						function() {
							$(".edit_order")
									.each(
											function() {
												$(this)
														.on(
																"click",
																function() {
																	gameId = $(
																			this)
																			.attr(
																					"id");
																	window.location = 'edit_order?id='
																			+ gameId;
																});
											});
							$(".delete_order")
									.each(
											function() {
												$(this)
														.on(
																"click",
																function() {
																	gameId = $(
																			this)
																			.attr(
																					"id");
																	if (confirm("Are you sure want to delete Game with Id "
																			+ gameId
																			+ " ?")) {
																		window.location = 'delete_order?id='
																				+ gameId;
																	}
																});
											});
						});
	</script>
</body>
</html>