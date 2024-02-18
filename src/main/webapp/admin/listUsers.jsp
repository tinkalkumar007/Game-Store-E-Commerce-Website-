<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>

<title>Manage Users - Game store</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h2 class="pageheading">User Management</h2>
		<hr width="60%" />
		<h3>
			<a href="user_form.jsp">Create New User</a>
		</h3>
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
				<th>Id</th>
				<th>Email</th>
				<th>Full Name</th>
				<th>Actions</th>
			</tr>

			<c:forEach var="user" items="${listUsers}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${user.userId}</td>
					<td>${user.email}</td>
					<td>${user.fullName}</td>
					<td><a href="javascript:void(0);" class="editUser" id="${user.userId}">Edit</a> &nbsp; 
					<a href="javascript:void(0);" class="deleteUser" id="${user.userId}">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>


	<jsp:directive.include file="footer.jsp" />

	<script>
	$(document).ready(function(){
		$(".editUser").each(function(){
			$(this).on("click",function(){
				userId=$(this).attr("id");
				if(userId==1){
					alert("The default admin user's acccount details cann't be modified.");
				}
				else{
					window.location='edit_user?id='+userId;
				}
				
			});
		});
		$(".deleteUser").each(function(){
			$(this).on("click",function(){
				userId=$(this).attr("id");
				if(userId==1){
					alert("The default admin user's acccount cann't be deleted!");
				}
				else if(confirm("Are you sure want to delete user with Id "+userId+" ?")){
					window.location='delete_user?id='+userId;
				}
			});
		});
	});
	</script>
</body>
</html>