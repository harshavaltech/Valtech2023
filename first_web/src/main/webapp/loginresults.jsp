<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Results</title>
</head>
<body>
Message =<%= request.getAttribute("message")%>

<br/>

<button onclick="window.location.href='http://localhost:8080/first_Web/login.jsp';">
      Click Here
    </button>

Message1=<%= request.getAttribute("Message1") %>
<br/>

Message2=<%= request.getAttribute("Message2") %>


</body>
</html>