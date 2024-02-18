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

<title>Edit Order Detail - Game store</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h2 class="pageheading">Edit Order ID: ${order.orderId }</h2>
	</div>
	<form action="update_order" method="post" id="orderForm">
		<div align="center">
			<h3>Order Overview</h3>

			<table class="form">
				<tr>
					<td><b>Ordered By:</b></td>
					<td>${order.customer.firstname}</td>
				</tr>
				<tr>
					<td><b>Order Date:</b></td>
					<td>${order.orderDate}</td>
				</tr>

				<tr>
					<td><b>Recipient Name:</b></td>
					<td><input type="text" name="recipientName" value="${order.firstname}" size="45" /></td>
				</tr>
				<tr>
					<td><b> Recipient Number: </b></td>
					<td><input type="text" name="recipientNo" value="${order.phone}" size="45" /></td>
				</tr>
				<tr>
					<td><b>Ship To:</b></td>
					<td><input type="text" name="shipTo"
						value="${order.city},${order.state},${order.country}" size="45" /></td>
				</tr>
				<tr>
					<td><b>Payment Method:</b></td>
					<td><select name="paymentMethod">
							<option value="Cash On Delivery">${order.paymentMethod}</option>
					</select></td>
				</tr>
				<tr>
					<td><b>Order Status:</b></td>
					<td><select name="orderStatus">
							<option value="Processing">Processing</option>
							<option value="Shipping">Shipping</option>
							<option value="Delivered">Delivered</option>
							<option value="Completed">Completed</option>
							<option value="Cancelled">Cancelled</option>
					</select></td>
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
					<th></th>
				</tr>
				<c:forEach items="${order.orderDetails}" var="orderDetail"
					varStatus="status">
					<tr>
						<td>${status.index+1}</td>
						<td>${orderDetail.game.title}</td>
						<td>${orderDetail.game.creator }</td>
						<td><fmt:formatNumber value="${orderDetail.game.price }"
								type="currency" /></td>
						<td>
						<input type="hidden" name="gameId" value="${orderDetail.game.gameId }"/>
						<input type="hidden" name="price" value="${orderDetail.game.price }"/>
						<input type="number" name="quantity${status.index+1}"
							value="${orderDetail.quantity }" style="width: 55px;" /></td>

						<td><fmt:formatNumber value="${orderDetail.subtotal }"
								type="currency" /></td>
						<td><a href="remove_game_from_order?id=${orderDetail.game.gameId}">Remove</a></td>
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
		<br />
		<div align="center">
			<a href="javascript:showAddGamePopUp()"><b>Add Games</b></a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="submit">Save</button>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button" onclick="javascript:window.location.href='list_order';">Cancel</button>

		</div>
	</form>
	<jsp:directive.include file="footer.jsp" />
	<script type="text/javascript">
	function showAddGamePopUp(){
		var width=600;
		var height=200;
		var left=(screen.width-width)/2;
		var top=(screen.height-height)/2;
		window.open('add_game_form','_blank','width='+width+',height='+height+',top='+top+',left='+left);
	}
	$(document).ready(function() {
		$("#orderForm").validate({
			rules : {
				recipientName : "required",
				recipientNo : "required",
				shipTo : "required",
				paymentMethod : "required",
				orderStatus : "required",
				<c:forEach items="${order.orderDetails }" var="item" varStatus="status">
				quantity${status.index+1}:{
					required : true, number: true, min: 1,
				},
				</c:forEach>
			},
			messages : {
				recipientName : "Please enter Recipient Name",
				recipientNo : "Please enter Recipient Phone",
				shipTo : "Please enter shipping address",
				paymentMethod : "Please select Payment method",
				orderStatus : "Please select Order Status",
				<c:forEach items="${order.orderDetails }" var="item" varStatus="status">
				quantity${status.index+1}: {
					required : "Please enter quantity",
					number : "Quantity must be a number",
					min : "Quantity must be greater than 0",
				},
				</c:forEach>
			}
		})
	});
	</script>
</body>
</html>