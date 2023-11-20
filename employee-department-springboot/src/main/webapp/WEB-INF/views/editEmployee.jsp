<%@page
	import="com.valtech.training.employeedepartmentspringboot.models.DepartmentModel"%>
<%@page
	import="com.valtech.training.employeedepartmentspringboot.models.EmployeeModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add or Edit Employee</title>
</head>
<body>
	<h2>Add/Edit Employee</h2>
	<%
	EmployeeModel employee = (EmployeeModel) request.getAttribute("employee");
	%>
	<form method="post" action="save?id=<%=employee.getId()%>">
		<table>
			<tr>
				<td>Employee Id</td>
				<td><input type="text" disabled="disabled" name="id"
					value="<%=employee.getId()%>" /></td>
			</tr>
			<tr>
				<td>Employee Name</td>
				<td><input type="text" name="name"
					value="<%=employee.getName()%>" /></td>
			</tr>
			<tr>
				<td>Age</td>
				<td><input type="text" name="age"
					value="<%=employee.getAge()%>" /></td>
			</tr>
			<tr>
				<td>Experience</td>
				<td><input type="text" name="experience"
					value="<%=employee.getExperience()%>" /></td>
			</tr>
			<tr>
				<td>Seniority</td>
				<td><input type="text" name="seniority"
					value="<%=employee.getSeniority()%>" /></td>
			</tr>
			<tr>
				<td>Salary</td>
				<td><input type="text" name="salary"
					value="<%=employee.getSalary()%>" /></td>
			</tr>
			<tr>
				<td>Department Id</td>
				<td><input type="text" name="department"
					value="<%=employee.getDepartment().getDeptId()%>" /></td>
			</tr>
			<tr>
				<td><input type="submit" name="submit" value="Add Employee" />
					<input type="submit" name="cancel" value="cancel" /></td>
			</tr>

		</table>
	</form>
</body>
</html>