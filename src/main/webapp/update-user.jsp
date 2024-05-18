<%@ page import="bean.Employee" %>
<%@ page import="bean.Departments" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <title>Update User</title>
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
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 600px;
            margin: auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        h2 {
            color: #333;
            text-align: center;
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin: 10px 0 5px;
            color: #555;
        }
        input[type="text"],
        input[type="email"],
        input[type="date"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            margin-bottom: 20px;
        }
        select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            margin-bottom: 20px;
        }
        input[type="submit"] {
            display: block;
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
    <script>
        function showUpdateForm() {
            document.getElementById("employeeIDForm").style.display = "none";
            document.getElementById("updateForm").style.display = "block";
        }
    </script>
</head>
<body>
    <div class="container">
        <h2>Update User</h2>
        <!-- First form to enter Employee ID -->
        <form id="employeeIDForm" action="updateuser" method="get">
            <label for="employeeID">Enter Employee ID:</label>
            <input type="text" id="employeeID" name="employeeID" required>
            <input type="submit" value="Submit">
        </form>

        <!-- Second form to update user details, hidden initially -->
        <div id="updateForm" style="display: none;">
            <form action="updateuser" method="post">
                <input type="hidden" name="employee_id" value="${user.employee_id}">

                <label for="first_name">First Name:</label>
                <input type="text" id="first_name" name="first_name" value="${user.first_name}" required>

                <label for="last_name">Last Name:</label>
                <input type="text" id="last_name" name="last_name" value="${user.last_name}" required>

                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="${user.email}" required>

                <label for="birth_date">Birth Date:</label>
                <input type="date" id="birth_date" name="birth_date" value="${user.birth_date}" required>

                <label for="job_title">Job Title:</label>
                <input type="text" id="job_title" name="job_title" value="${user.job_title}" required>

                <label for="department">Department:</label>
				<select id="department" name="department" required>
				    <option value="" disabled>Select Department</option>
				    <% 
				        List<Departments> departmentsList = (List<Departments>) request.getAttribute("departments");
				        Integer selectedDepartment = (Integer) request.getAttribute("selectedDepartment");
				        if (departmentsList != null && !departmentsList.isEmpty()) {
				            for (Departments department : departmentsList) {
				                String selected = (selectedDepartment != null && department.getDepartment_id() == selectedDepartment) ? "selected" : "";
				    %>
				        <option value="<%= department.getDepartment_id() %>" <%= selected %>><%= department.getDepartment_name() %></option>
				    <% 
				            } 
				        } else {
				    %>
				        <option disabled>No departments available</option>
				    <% } %>
				</select>
                <input type="submit" value="Update User">
            </form>
        </div>
    </div>
    <% if (request.getAttribute("user") != null) { %>
        <script>
            showUpdateForm();
        </script>
    <% } %>
    <a href="../Object_Oriented_Project" class="go-back-button">Go Back</a>
</body>
</html>
