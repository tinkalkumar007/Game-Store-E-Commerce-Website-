<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/style.css">
<meta charset="ISO-8859-1">
<title>Game Store - Online Game Store</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h3>Welcome, ${loggedCustomer.firstname }</h3>
		<table class="form">
			<tr>
				<td><b>Email:</b></td>
				<td align="left">${loggedCustomer.email }</td>
			</tr>
			<tr>
				<td><b>First Name:</b></td>
				<td align="left">${loggedCustomer.firstname}</td>
			</tr>
			<tr>
				<td><b>Last Name:</b></td>
				<td align="left">${loggedCustomer.lastname}</td>
			</tr>
			<tr>
				<td><b>Phone No.:</b></td>
				<td align="left">${loggedCustomer.phone}</td>
			</tr>
			<tr>
				<td><b>Address Line 1:</b></td>
				<td align="left">${loggedCustomer.addressLine1}</td>
			</tr>
			<tr>
				<td><b>Address Line 2:</b></td>
				<td align="left">${loggedCustomer.addressLine2}</td>
			</tr>
			<tr>
				<td ><b>City:</b></td>
				<td align="left">${loggedCustomer.city}</td>
			</tr>
			<tr>
				<td ><b>State:</b></td>
				<td align="left">${loggedCustomer.state}</td>
			</tr>
			<tr>
				<td><b>Zip Code:</b></td>
				<td align="left">${loggedCustomer.customercol}</td>
			</tr>
			<tr>
				<td><b>Country:</b></td>
				<td align="left">${loggedCustomer.countryName}</td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><a href="edit_profile">Edit My Profile</a></td>
			</tr>
		</table>
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
</html>