<table class="form">
	<tr>
		<td align="right">First Name:</td>
		<td align="left"><input type="text" id="firstName"
			name="firstName" size="45" value="${customer.firstname}"></td>
	</tr>
	<tr>
		<td align="right">Last Name:</td>
		<td align="left"><input type="text" id="lastName" name="lastName"
			size="45" value="${customer.lastname}"></td>
	</tr>
	<tr>
		<td align="right">Email:</td>
		<td align="left"><input type="email" id="email" name="email"
			size="45" value="${customer.email}"></td>
	</tr>
	<tr>
		<td align="right">Password:</td>
		<td align="left"><input type="password" id="password"
			name="password" size="45" value="${customer.password}"></td>
	</tr>
	<tr>
		<td align="right">Confirm Password:</td>
		<td align="left"><input type="password" id="cnfpwd" name="cnfpwd"
			size="45" value="${customer.password}"></td>
	</tr>
	<tr>
		<td align="right">Phone No:</td>
		<td align="left"><input type="text" id="phone" name="phone"
			size="45" value="${customer.phone}"></td>
	</tr>
	<tr>
		<td align="right">Address Line 1:</td>
		<td align="left"><input type="text" id="adrline1" name="adrline1"
			size="45" value="${customer.addressLine1}"></td>
	</tr>
	<tr>
		<td align="right">Address Line 2:</td>
		<td align="left"><input type="text" id="adrline2" name="adrline2"
			size="45" value="${customer.addressLine2}"></td>
	</tr>
	<tr>
		<td align="right">City:</td>
		<td align="left"><input type="text" id="city" name="city"
			size="45" value="${customer.city}"></td>
	</tr>
	<tr>
		<td align="right">State:</td>
		<td align="left"><input type="text" id="state" name="state"
			size="45" value="${customer.state}"></td>
	</tr>
	<tr>
		<td align="right">Zip Code:</td>
		<td align="left"><input type="text" id="zipcode" name="zipcode"
			size="45" value="${customer.customercol}"></td>
	</tr>
	<tr>
		<td align="right">Country:</td>
		<td align="left"><select id="country" name="country">
				<c:forEach items="${countries }" var="country">
					<option value="${country.value }"
						<c:if test='${customer.country eq country.key}'>selected='selected'</c:if>>${country.key}</option>
				</c:forEach>
		</select></td>
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