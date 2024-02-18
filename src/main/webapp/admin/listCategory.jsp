<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="../css/style.css">
<meta charset="ISO-8859-1">
<script type="text/javascript" src="../js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>

<title>Manage Category - Game Store</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div align="center">
		<h2 class="pageheading">Category Management</h2>
		<hr width=60%>
	</div>
	<div align="center">
		<h3>
			<a href="category_form.jsp">Create New Category</a>
		</h3>
	</div>
	<c:if test="${message!=null}">
		<div align="center">
			<h4 class="message">
				<i>${message}</i>
			</h4>
		</div>
	</c:if>

	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
				<th>Index</th>
				<th>Id</th>
				<th>Category Name</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="category" items="${listCategory}" varStatus="status">
				<tr>
					<td>${status.index+1 }</td>
					<td>${category.categoryId}</td>
					<td>${category.name}</td>
					<td><a href="edit_category?id=${category.categoryId}">Edit</a> &nbsp;&nbsp;&nbsp; 
					<a href="javascript:void(0);" class="deleteLink" id="${category.categoryId}">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
<script>
    $(document).ready(function(){
    	$(".deleteLink").each(function(){
    		$(this).on("click",function(){
    			id=$(this).attr("id");
        		if (confirm("Are you sure want to delete category with id " + id + " ?")) {
        			window.location = "delete_category?id=" + id;
        		}
    		});
    	});
    });
</script>
</html>