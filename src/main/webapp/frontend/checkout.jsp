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

<title>Checkout</title>
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
			<h2>
				<a href="${pageContext.request.contextPath}/">Shop Now</a>
			</h2>

		</c:if>
		<c:if test="${cart.getTotalItems()>0 }">
			<h2>
				Review Your Order Details | <a href="view_cart">Edit</a>
			</h2>
			<div>
				<table border="1" cellpadding="5">
					<tr>
						<th>No.</th>
						<th colspan="2">Game</th>
						<th>Creator</th>
						<th>Quantity</th>
						<th>Price</th>
						<th>SubTotal</th>
					</tr>
					<c:forEach items="${cart.getItems() }" var="item"
						varStatus="status">
						<tr>
							<td>${ status.index+1 }</td>
							<td align="center"><img
								src="data:image/jpg;base64,${item.key.base64Image}" width="128"
								height="164" /></td>
							<td><span id="game-title">${ item.key.title }</span></td>
							<td>${item.key.creator}</td>
							<td>${ item.value }</td>
							<td><fmt:formatNumber value="${ item.key.price }"
									type="currency" /></td>
							<td><fmt:formatNumber value="${ item.value*item.key.price }"
									type="currency" /></td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="5" align="right"><b>${cart.getTotalQuantity()} Game(s)</b></td>
						<td><b>Total:</b></td>
						<td colspan="2"><b><fmt:formatNumber
									 value="${cart.getTotalAmount() }" type="currency" /></b></td>
					</tr>
				</table>
				<h2>Your Shipping information</h2>
				<form id="orderForm" action="place_order" method="post">
					<table class="form">
						<tr>
							<td>Recipient First Name:</td>
							<td><input type="text" name="firstName"
								value="${loggedCustomer.firstname }" /></td>
						</tr>
						<tr>
							<td>Recipient Last Name:</td>
							<td><input type="text" name="lastName"
								value="${loggedCustomer.lastname }" /></td>
						</tr>
						<tr>
							<td>Recipient Phone:</td>
							<td><input type="text" name="phone"
								value="${loggedCustomer.phone }" /></td>
						</tr>
						<tr>
							<td>Address Line 1:</td>
							<td><input type="text" name="addrLine1"
								value="${loggedCustomer.addressLine1 }" /></td>
						</tr>
						<tr>
							<td>Address Line 2:</td>
							<td><input type="text" name="addrLine2"
								value="${loggedCustomer.addressLine2 }" /></td>
						</tr>
						<tr>
							<td>City:</td>
							<td><input type="text" name="city"
								value="${loggedCustomer.city }" /></td>
						</tr>
						<tr>
							<td>State:</td>
							<td><input type="text" name="state"
								value="${loggedCustomer.state }" /></td>
						</tr>
						<tr>
							<td>Zip Code:</td>
							<td><input type="text" name="zipCode"
								value="${loggedCustomer.customercol }" /></td>
						</tr>
						<tr>
							<td>Country:</td>
							<td><input type="text" name="country"
								value="${loggedCustomer.country }" /></td>
						</tr>
					</table>
					<div>
						<h2>Payment</h2>
						Choose Your Payment Method: &nbsp;&nbsp;&nbsp;&nbsp; <select
							name="payment_method">
							<option value="Cash On Delivery">Cash on Delivery</option>
						</select>
					</div>
					<br/>
					<div>
						<table class="form">
							<tr>
								<td><button type="submit">Place Order</button></td>
								<td><a href="${pageContext.request.contextPath}/">
										Continue Shopping </a></td>
							</tr>
						</table>
					</div>
				</form>
			</div>
		</c:if>
	</div>

	<jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#orderForm").validate({
			rules : {
				firstName: "required",
			    lastName: "required",
				phone: "required",
				addrLine1: "required",
				addrLine2: "required",
				city: "required",
				state: "required",
				zipCode: "required",
				country: "required"
			},

			messages : {
				firstName: "Please enter First Name",
				lastName: "Please enter Last Name",
				phone: "Please enter Phone no.",
				addrLine1: "Please enter Address Line 1",
				addrLine2: "Please enter Address Line 2",
				city : "Please enter City",
				state: "Please enter state",
				zipCode:" Please enter Zip Code",
				country: "Please enter Country",
			}
		});
	});
</script>
</html>