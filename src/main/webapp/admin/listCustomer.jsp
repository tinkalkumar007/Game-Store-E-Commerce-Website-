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

<title>Manage Customer - Game Store</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div align="center">
		<h2 class="pageheading">Customer Management</h2>
		<hr width=60%>
	</div>
	<div align="center">
		<h3>
			<a href="new_customer">Create New Customer</a>
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
				<th> Index </th>
				<th> Id </th>
				<th> E-mail </th>
				<th> First Name </th>
				<th> Last Name </th>
				<th> City </th>
				<th> Country </th>
				<th> Registered Date </th>
				<th> Actions </th>
			</tr>
			<c:forEach var="customer" items="${listCustomer}" varStatus="status">
				<tr>
					<td>${status.index+1 }</td>
					<td>${customer.customerId}</td>
					<td>${customer.email}</td>
					<td>${customer.firstname} </td>
					<td>${customer.lastname} </td>
					<td>${customer.city}</td>
					<td>${customer.countryName}</td>
					<td>${customer.registerDate}</td>
					<td><a href="edit_customer?id=${customer.customerId}">Edit</a> &nbsp;&nbsp;&nbsp; 
					<a href="javascript:void(0);" class="deleteLink" id="${customer.customerId}">Delete</a>
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
        		if (confirm("Are you sure want to delete customer with id " + id + " ?")) {
        			window.location = "delete_customer?id=" + id;
        		}
    		});
    	});
    });
</script>
</html>