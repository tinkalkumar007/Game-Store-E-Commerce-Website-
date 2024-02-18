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

<title>My Order Detail - Game store</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<c:if test="${order==null}">
		<div align="center">
			<h2 class="pageheading">Sorry, You are not authorized to view this order</h2>
		</div>
	</c:if>
	<c:if test="${order!=null}">
		<div align="center">
			<h2 class="pageheading">My Order ID: ${order.orderId }</h2>
		</div>
		<div align="center">
			<h3>Order Overview</h3>
			<table class="form">
				<tr>
					<td><b>Order Status:</b></td>
					<td>${order.status}</td>
				</tr>
				<tr>
					<td><b>Order Date:</b></td>
					<td>${order.orderDate}</td>
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
					<td><b> Recipient Phone: </b></td>
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
			</table>
		</div>
		<div align="center">
			<h2>Ordered Games</h2>

			<table border="1" cellpadding="5">
				<tr>
					<th>No.</th>
					<th>Game</th>
					<th>Creator</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>SubTotal</th>
				</tr>
				<c:forEach items="${order.orderDetails }" var="item"
					varStatus="status">
					<tr>
						<td>${ status.index+1 }</td>
						<td align="center"><img style="vertical-align: middle"
							src="data:image/jpg;base64,${item.game.base64Image}" width="48"
							height="64" /> ${ item.game.title }</td>
						<td>${item.game.creator}</td>
						<td><fmt:formatNumber value="${ item.game.price }"
								type="currency" /></td>
						<td>${ item.quantity}</td>
						<td><fmt:formatNumber value="${ item.subtotal }"
								type="currency" /></td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="4" align="right"><i><b>Total:</b></i></td>
					<td><b>${order.getGameCopies()} Game(s)</b></td>
					<td colspan="2"><b><fmt:formatNumber
								value="${order.total }" type="currency" /></b></td>
				</tr>
			</table>
		</div>
	</c:if>
	<jsp:directive.include file="footer.jsp" />
</body>
</html>