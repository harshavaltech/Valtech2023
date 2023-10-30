<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Fancy Phone Number Ranker</title>
    <link rel="stylesheet" type="text/css" href="index.css">
</head>
<body>
    <h1>Fancy Phone Number Ranker</h1>
    <form action="Ranker" method="post">
        <p>Enter ten 10-digit phone numbers, one per line:</p>
        <textarea name="phoneNumbers" rows="10" cols="30"></textarea>
        <br>
        <input type="submit" value="Rank Numbers">
    </form>
</body>
</html>