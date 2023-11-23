<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Result</title>
</head>
<body>
	<h1>Fancy Phone Numbers</h1>
 
	<p>Ranked Phone Numbers:</p>
	<ol>
		<% List<String> phoneNumbers=(List<String>) request.getAttribute("rankedPhoneNumbers"); %>
		<% for(String phoneNumber:phoneNumbers){ %>
		<li><%= phoneNumber %></li>
		<%} %>
	</ol>
	<p>
		<a href="index.jsp">Go back</a>
	</p>
</body>
</html>