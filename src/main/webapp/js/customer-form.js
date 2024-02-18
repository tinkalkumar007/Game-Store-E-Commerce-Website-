$(document).ready(function() {
	$("#customerForm").validate({
		rules: {
			firstName: "required",
			lastName: "required",

			email: {
				required: true,
				email: true
			},

			password: "required",
			cnfpwd: {
				required: true,
				equalTo: "#password"
			},

			phone: "required",
			adrline1: "required",
			adrline2: "required",
			city: "required",
			state: "required",
			zipcode: "required",
			country: "required",

		},

		messages: {
			firstName: "Please enter First Name.",
			lastName: "Please enter Last Name.",

			email: {
				required: "Please enter Email.",
				email: "Please enter valid e-mail address."
			},

			password: "Please enter Password",
			cnfpwd: {
				required: "Please Confirm password.",
				equalTo: "Password doesn't match."
			},
			phone: "Please enter phone number.",
			adrline1: "Please enter Address line 1.",
			adrline2: "Please enter Address line 2.",
			city: "Please enter City.",
			state: "Please enter State",
			zipcode: "Please enter Zip Code.",
			country: "Please enter Country.",
		}
	})

	$("#cancelBtn").click(function() {
		history.go(-1);
	});
});