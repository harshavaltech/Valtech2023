<%@page import="com.valtech.training.employeedepartmentspringboot.models.EmployeeModel"%>
<%@page import="java.util.List"%>
<%@page
	import="com.valtech.training.employeedepartmentspringboot.models.DepartmentModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	DepartmentModel department = (DepartmentModel) request.getAttribute("department");
	request.setAttribute("current", department);
	%>
	<table>
		<tr>
			<td>Department Id:</td>
			<td><%=department.getDeptId()%></td>
		</tr>
		<tr>
			<td>Department Name:</td>
			<td><%=department.getDeptName()%></td>
		</tr>
		<tr>
			<td>Department Location:</td>
			<td><%=department.getDeptLocation()%></td>
		</tr>
		<tr>
			<td colspan="2">
				<form action="dept" method="post">
					<input type="submit" name="submit" value="First" /> 
					<input type="submit" name="submit" value="Next" /> 
					<input type="submit"name="submit" value="Previous" /> <input type="submit"
						name="submit" value="Last" />
				</form>
			</td>
		</tr>
	</table>
	<h2 align="center">Employees List</h2>
	<%
	List<EmployeeModel> employees = (List<EmployeeModel>) request.getAttribute("employees");
	%>
	<table border="1" align="center">
		<tr>
			<th>Emp Id</th>
			<th>Name</th>
			<th>Age</th>
			<th>Experience</th>
			<th>Seniority</th>
			<th>Salary</th>
			<th>Dept Id</th>
			<th>Actions</th>
		</tr>

		<c:forEach var="employee" items="${employees}">
			<%
			request.setAttribute("employees", employees);
			%>
			<tr>
				<td>${employee.id}</td>
				<td>${employee.name}</td>
				<td>${employee.age}</td>
				<td>${employee.experience}</td>
				<td>${employee.seniority}</td>
				<td>${employee.salary}</td>
				<td>${employee.department.deptId}</td>
				<td><a href="delete?id=${employee.id}">Delete</a> <a
					href="edit?id=${employee.id}">Edit</a></td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="5" align="right">
				<form action="newEmployee" method="get">
					<input type="submit" name="submit" value="Add Employee" />
				</form>
			</td>
		</tr>
	</table>
</body>
</html>