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
		<c:if test="${category==null }">
			<h2 class="pageheading">Enter Category Details</h2>
		</c:if>
		<c:if test="${category!=null }">
			<h2 class="pageheading">Edit Category Details</h2>
		</c:if>
		<hr width=60%>
	</div>
	<div align="center">
		<c:if test="${category==null}">
			<form action="create_category" method="post" id="categoryForm">
		</c:if>
		<c:if test="${category!=null}">
			<form action="update_category" method="post" id="categoryForm">
				<input type="hidden" id="categoryId" name="categoryId"
					value="${category.categoryId}">
		</c:if>
		<table class="form">
			<tr>
				<td>Name:</td>
				<td><input type="text" id="name" name="name"
					value="${category.name}"></td>
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
		$("#categoryForm").validate({
			rules : {
				name : "required"
			},
			messages : {
				name : "Please enter Category Name."
			}
		})
		$("#cancelBtn").click(function() {
			history.go(-1);
		});
	});
</script>
</html>