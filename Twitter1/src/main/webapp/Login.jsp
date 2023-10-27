<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Twitter Login page</title>
</head>
<body>
<div align="centre">
<h1>User Login</h1>
</div>
<form action="TwitterServlet" method="Post">
<table>
<tr>
<td>Enter Name:</td><td><input type=text name=txtName></td>
</tr>
<tr>
<td>Enter Password:</td><td><input type=password name=txtPwd></td></tr>
<tr><td><input type=Submit value=Login></td><td><input type=reset></td></tr>
</table>

</form>
</body>
</html>