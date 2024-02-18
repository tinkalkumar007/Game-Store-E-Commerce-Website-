<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="../css/style.css">

<meta charset="ISO-8859-1">
<script type="text/javascript" src="../js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>

<title><c:if test="${customer==null}">
	Create New Customer
</c:if> <c:if test="${customer!=null}">
	Edit Customer Details
</c:if></title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2 class="pageheading">
			<c:if test="${customer!=null}"> Edit Customer Details </c:if>
			<c:if test="${customer==null}"> Enter Customer Details </c:if>
		</h2>
		<hr width=60%>
	</div>
	<div align="center">
		<c:if test="${customer!=null}">
			<form action="update_customer" method="post" id="customerForm">
				<input type="hidden" name="customerId"
					value="${customer.customerId}" />
		</c:if>
		<c:if test="${customer==null}">
			<form action="create_customer" method="post" id="customerForm">
		</c:if>
		<jsp:directive.include file="../common/customer_form.jsp" />
		</form>
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript" src="../js/customer-form.js">
	
</script>
</html>