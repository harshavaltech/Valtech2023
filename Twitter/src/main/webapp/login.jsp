<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Twitter Login Page</title>

    <!-- Add Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    
    <!-- Add custom CSS for the Twitter logo -->
    <style>
        /* Style for the Twitter logo container */
        .twitter-logo {
            text-align: center;
            margin-top: 20px;
        }

        /* Style for the Twitter logo image */
        .twitter-logo img {
            width: 100px; /* Adjust the width as needed */
        }

        /* Style for the login form container */
        .login-form {
            margin: 20px auto;
            max-width: 400px;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center">TWITTER LOGIN</h1>
</div>
<div class="twitter-logo">
    <img src="https://upload.wikimedia.org/wikipedia/commons/e/e4/Twitter_2012_logo.svg" alt="Twitter Logo">
</div>

<div class="container mt-4 login-form">
    <form action="TwitterServlet" method="POST">
        <div class="form-group">
            <label for="txtName">Enter Name:</label>
            <input type="text" class="form-control" name="txtName" id="txtName">
        </div>
        <div class="form-group">
            <label for="txtPwd">Enter Password:</label>
            <input type="password" class="form-control" name="txtPwd" id="txtPwd">
        </div>
        <button type="submit" class="btn btn-primary">Login</button>
        <button type="reset" class="btn btn-secondary ml-2">Reset</button>
    </form>
</div>

<!-- Add Bootstrap JS and jQuery (required for Bootstrap components) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
