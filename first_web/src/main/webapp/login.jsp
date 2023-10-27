<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.16/dist/tailwind.min.css">
</head>
<body class="bg-gray-100">
    <div class="w-1/3 mx-auto p-6 mt-10 bg-white rounded shadow-md">
        <form action="login" method="post" class="space-y-4">
            <div class="mb-4">
                <label for="name" class="block text-gray-700">Name :</label>
                <input name="name" type="text" id="name" class="form-input w-full">
            </div>
            <div class="mb-4">
                <label for="password" class="block text-gray-700">Password :</label>
                <input name="pass" type="password" id="password" class="form-input w-full">
            </div>
            <div class="flex justify-end">
                <input type="submit" value="Login" class="class="bg-black-500 text-white py-2 px-4 rounded-md hover:bg-red-600">
            </div>
        </form>
    </div>
</body>
</html>