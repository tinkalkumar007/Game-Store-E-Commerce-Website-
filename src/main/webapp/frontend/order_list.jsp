<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>

<title>My orders - Game store</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h2 class="pageheading">Your Order history</h2>
	</div>
	<div align="center">
		<c:if test="${fn:length(listOrder)==0}">
			<h2>You have not placed any order yet.</h2>
		</c:if>
	</div>

	<div align="center">
		<c:if test="${fn:length(listOrder)>0}">
			<table border="1" cellpadding="5">
				<tr>
					<th>Index</th>
					<th>Order Id</th>
					<th>Quantity</th>
					<th>Total Amount</th>
					<th>Order Date</th>
					<th>Status</th>
					<th></th>
				</tr>

				<c:forEach var="order" items="${listOrder}" varStatus="status">
					<tr>
						<td>${status.index + 1}</td>
						<td>${order.orderId}</td>
						<td>${order.getGameCopies()}</td>
						<td><fmt:formatNumber value="${order.total}" type="currency" /></td>
						<td>${order.orderDate}</td>
						<td>${order.status}</td>
						<td><a href="show_order_detail?id=${order.orderId }">View Details</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
</html>