<%@page import="com.valtech.training.employeedepartmentspringboot.models.DepartmentModel"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% DepartmentModel department = (DepartmentModel) request.getAttribute("department"); %>
	<h2>Add/Edit Department</h2>
	
	<form action="saveDept?deptId=<%= department.getDeptId() %>" method="post">
		<table>
			<tr>
				<td>Department Id</td>
				<td><input type="text" disabled="disabled" name="deptId" value="<%= department.getDeptId() %>"/></td>
			</tr>
			<tr>
				<td>Department Name</td>
				<td><input type="text" name="deptName" value="<%= department.getDeptName() %>"/></td>
			</tr>
			<tr>
				<td>Department Location</td>
				<td><input type="text" name="deptLocation" value="<%= department.getDeptLocation() %>"/></td>
			</tr>
			<tr>
				<td><input type="submit" name="submit" value="Add Department" /> 
				<input type="submit" name="cancel" value="cancel" /></td>
			</tr>
		</table>
	</form>
</body>
</html>