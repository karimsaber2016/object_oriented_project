<%@ page import="dao.UserDAO" %>
<%@ page import="bean.Departments" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add User</title>
    <style>
    	.go-back-button {
            position: fixed;
            bottom: 20px;
            right: 20px;
            background-color: #007bff;
            color: #fff;
            padding: 10px 20px;
            border-radius: 5px;
            text-decoration: none;
        }
        .go-back-button:hover {
            background-color: #0056b3;
        }
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h2 {
            color: #333;
        }
        form {
            max-width: 400px;
            margin: 0 auto;
        }
        label {
            display: block;
            margin-bottom: 5px;	
        }
        input[type="text"],
        input[type="email"],
        input[type="date"],
        select {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            border-radius: 5px;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h2>Add User</h2>
    <form action="adduser" method="post">

        <label for="first_name">First Name:</label>
        <input type="text" id="first_name" name="first_name" required><br>
        
        <label for="last_name">Last Name:</label>
        <input type="text" id="last_name" name="last_name" required><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br>

        <label for="birth_date">Birth Date:</label>
        <input type="date" id="birth_date" name="birth_date" required><br>

        <label for="job_title">Job Title:</label>
        <input type="text" id="job_title" name="job_title" required><br>

        <label for="department">Department:</label>
        <select id="department" name="department" required>
            <option value="" disabled selected>Select Department</option>
            <% UserDAO userDao = new UserDAO();
               List<Departments> departmentsList = userDao.selectAllDepartments();
               for (Departments department : departmentsList) {
            %>
            <option value="<%= department.getDepartment_id() %>"><%= department.getDepartment_name() %></option>
            <% } %>
        </select><br>

        <input type="submit" value="Add User">
    </form>
    <a href="../Object_Oriented_Project" class="go-back-button">Go Back</a>
</body>
</html>
