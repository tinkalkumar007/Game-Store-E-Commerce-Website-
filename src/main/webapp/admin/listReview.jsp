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

<title>Manage Reviews - Game store</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h2 class="pageheading">Review Management</h2>
		<hr width="60%" />
	</div>

	<c:if test="${message != null}">
		<div align="center">
			<h4 class="message"><i>${message}</i></h4>
		</div>
	</c:if>

	<div align="center">
		<table border="1">
			<tr>
				<th>Index</th>
				<th>Id</th>
				<th>Game</th>
				<th>Rating</th>
				<th>Headline</th>
				<th>Customer</th>
				<th>Review On</th>
				<th>Actions</th>
			</tr>
	
			<c:forEach var="review" items="${listReview}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${review.reviewId}</td>
					<td>${review.game.title}</td>
					<td>${review.rating}</td>
					<td>${review.headline}</td>
					<td>${review.customer.firstname}</td>
					<td>${review.reviewTime}</td>
					<td><a href="javascript:void(0);" class="editReview" id="${review.reviewId}">Edit</a> &nbsp; 
					<a href="javascript:void(0);" class="deleteReview" id="${review.reviewId}">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>


	<jsp:directive.include file="footer.jsp" />

	<script>
	$(document).ready(function(){
		$(".editReview").each(function(){
			$(this).on("click",function(){
				reviewId=$(this).attr("id");
				window.location='edit_review?id='+reviewId;
			});
		});
		$(".deleteReview").each(function(){
			$(this).on("click",function(){
				reviewId=$(this).attr("id");
				if(confirm("Are you sure want to delete review with Id "+reviewId+" ?")){
					window.location='delete_review?id='+reviewId;
				}
			});
		});
	});
	</script>
</body>
</html>