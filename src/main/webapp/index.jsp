<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .container {
            max-width: 1200px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-top: 100px;
        }
        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 20px;
        }
        .menu {
            text-align: center;
            margin-bottom: 20px;
        }
        .menu a {
            display: inline-block;
            margin: 0 10px;
            padding: 10px 20px;
            text-decoration: none;
            color: #fff;
            background-color: #007bff;
            border-radius: 5px;
        }
        .menu a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to the Admin Dashboard</h1>
        <div class="menu">
            <a href="add-user.jsp">Add an Employee</a>
            <a href="delete-user.jsp">Remove an Employee</a>
            <a href="update-user.jsp">Update an Employee's info</a>
        </div>
        <div>
            <a href="add-department.jsp">Add a Department</a>
            <a href="delete-department.jsp">Remove a Department</a>
            <a href="update-department.jsp">Update a Department</a>
        </div>
    </div>
</body>
</html>
	