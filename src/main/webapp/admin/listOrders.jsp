<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>

<title>Manage orders - Game store</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h2 class="pageheading">Order Management</h2>
		<hr width="60%" />
	</div>

	<c:if test="${message != null}">
		<div align="center">
			<h4 class="message"><i>${message}</i></h4>
		</div>
	</c:if>

	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
				<th>Index</th>
				<th>Order Id</th>
				<th>Ordered By</th>
				<th>Game Copies</th>
				<th>Total</th>
				<th>Payment Method</th>
				<th>Status</th>
				<th>Order Date</th>
				<th>Actions</th>
			</tr>

			<c:forEach var="order" items="${listOrders}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${order.orderId}</td>
					<td>${order.customer.firstname}</td>
					<td>${order.getGameCopies()}</td>
					<td><fmt:formatNumber value="${order.total}" type="currency"/></td>
					<td>${order.paymentMethod}</td>
					<td>${order.status}</td>
					<td>${order.orderDate}</td>
					
					<td>
					<a href="view_order?id=${order.orderId }">Details</a> &nbsp;
					<a href="javascript:void(0);" class="edit_order" id="${order.orderId}">Edit</a> &nbsp; 
					<a href="javascript:void(0);" class="delete_order" id="${order.orderId}">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>


	<jsp:directive.include file="footer.jsp" />

	<script>
	$(document).ready(function(){
		$(".edit_order").each(function(){
			$(this).on("click",function(){
				orderId=$(this).attr("id");
				window.location='edit_order?id='+orderId;
			});
		});
		$(".delete_order").each(function(){
			$(this).on("click",function(){
				orderId=$(this).attr("id");
				if(confirm("Are you sure want to delete order with Id "+orderId+" ?")){
					window.location='delete_order?id='+orderId;
				}
			});
		});
	});
	</script>
</body>
</html>