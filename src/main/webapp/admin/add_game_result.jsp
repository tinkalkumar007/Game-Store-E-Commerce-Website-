<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Game Added - Game Store</title>
</head>
<body>
	<div align="center">
		<h2>
			The Game <i>${game.title }</i> has been added to order Id:
			${order.orderId}
		</h2>
		<button onclick="javascript:self.close();">Close</button>
	</div>
	<script>
		window.onunload = function() {
			window.opener.location.reload();
		}
	</script>
</body>

</html>