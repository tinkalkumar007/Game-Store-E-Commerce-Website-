<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/style.css">
<meta charset="ISO-8859-1">
<script type="text/javascript" src="js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<title>Game Store - Online Game Store</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2 class="pageheading">Edit My Profile</h2>
	</div>
	<div align="center">
		<form action="update_profile" method="post" id="customerForm">
			<input type="hidden" name="customerId"
				value="${loggedCustomer.customerId }">
			<table class="form">
				<tr>
					<td align="right">Email:</td>
					<td align="left"><b>${loggedCustomer.email}</b> (cann't be
						changed)</td>
				</tr>
				<tr>
					<td align="right">First Name:</td>
					<td align="left"><input type="text" id="firstName"
						name="firstName" size="45" value="${loggedCustomer.firstname}"></td>
				</tr>
				<tr>
					<td align="right">Last Name:</td>
					<td align="left"><input type="text" id="lastName"
						name="lastName" size="45" value="${loggedCustomer.lastname}"></td>
				</tr>
				<tr>
					<td align="right">Phone No:</td>
					<td align="left"><input type="text" id="phone" name="phone"
						size="45" value="${loggedCustomer.phone}"></td>
				</tr>
				<tr>
					<td align="right">Address Line 1:</td>
					<td align="left"><input type="text" id="adrline1"
						name="adrline1" size="45" value="${loggedCustomer.addressLine1}"></td>
				</tr>
				<tr>
					<td align="right">Address Line 2:</td>
					<td align="left"><input type="text" id="adrline2"
						name="adrline2" size="45" value="${loggedCustomer.addressLine2}"></td>
				</tr>
				<tr>
					<td align="right">City:</td>
					<td align="left"><input type="text" id="city" name="city"
						size="45" value="${loggedCustomer.city}"></td>
				</tr>
				<tr>
					<td align="right">State:</td>
					<td align="left"><input type="text" id="state" name="state"
						size="45" value="${loggedCustomer.state}"></td>
				</tr>
				<tr>
					<td align="right">Zip Code:</td>
					<td align="left"><input type="text" id="zipcode"
						name="zipcode" size="45" value="${loggedCustomer.customercol}"></td>
				</tr>
				<tr>
					<td align="right">Country:</td>
					<td align="left"><select id="country" name="country">
							<c:forEach items="${countries }" var="country">
								<option value="${country.value }"
									<c:if test='${loggedCustomer.country eq country.value}'>selected='selected'</c:if>>${country.key}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td colspan="2" align="center">(leave password fields blank if
						you don't want to change password)</td>
				</tr>
				<tr>
					<td align="right">New Password:</td>
					<td align="left"><input type="password" id="password"
						name="password" size="45"></td>
				</tr>
				<tr>
					<td align="right">Confirm New Password:</td>
					<td align="left"><input type="password" id="cnfpwd"
						name="cnfpwd" size="45"></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button type="submit">Save</button> &nbsp;&nbsp;&nbsp;&nbsp;
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
		$("#customerForm").validate({
			rules : {
				firstName : "required",
				lastName : "required",

				email : {
					required : true,
					email : true
				},
				cnfpwd : {
					equalTo : "#password"
				},
				phone : "required",
				adrline1 : "required",
				adrline2 : "required",
				city : "required",
				state : "required",
				zipcode : "required",
				country : "required",

			},

			messages : {
				firstName : "Please enter First Name.",
				lastName : "Please enter Last Name.",

				email : {
					required : "Please enter Email.",
					email : "Please enter valid e-mail address."
				},
				cnfpwd : {
					equalTo : "Password doesn't match."
				},
				phone : "Please enter phone number.",
				adrline1 : "Please enter Address line 1.",
				adrline2 : "Please enter Address line 2.",
				city : "Please enter City.",
				state : "Please enter State",
				zipcode : "Please enter Zip Code.",
				country : "Please enter Country.",
			}
		})

		$("#cancelBtn").click(function() {
			history.go(-1);
		});
	});
</script>
</html>