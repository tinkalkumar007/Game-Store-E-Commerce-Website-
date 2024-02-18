<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>

<meta charset="ISO-8859-1">
<c:if test="${category==null }">
	<title>Create Category</title>
</c:if>
<c:if test="${category!=null }">
	<title>Edit Category</title>
</c:if>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2 class="pageheading">Edit Review</h2>
		<hr width=60%>
	</div>
	<div align="center">
		<form action="update_review" method="post" id="reviewForm">
			<input type="hidden" id="reviewId" name="reviewId"
				value="${review.reviewId}">
			<table class="form">
				<tr>
					<td>Game:</td>
					<td><b>${review.game.title}</b></td>
				</tr>
				<tr>
					<td>Rating:</td>
					<td><b>${review.rating}</b></td>
				</tr>
				<tr>
					<td>Creator:</td>
					<td><b>${review.game.creator}</b></td>
				</tr>
				<tr>
					<td>Headline:</td>
					<td><input type="text" size="60" id="headline" name="headline"
						value="${review.headline}"></td>
				</tr>
				<tr>
					<td>Comment:</td>
					<td><textarea rows="5" cols="70"
					 id="comment" name="comment">${review.comment}</textarea>
					 </td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button type="submit">Save</button>&nbsp; &nbsp;&nbsp;
						<button id="cancelBtn">Cancel</button>
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
				comment : "Please write a review"
			}
		})
		$("#cancelBtn").click(function() {
			history.go(-1);
		});
	});
</script>
</html>