<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<body>
	<div ="container">
		<form id="loginform" action="login" method = "POST">
			<input type="text" name="email" placeholder="Email" required><br>
			<input type="password" name="password" placeholder = "password" required><br>
			<input type="submit" value="Login">
			${errorMessage}
		</form>
	</div>

</body>
</html>