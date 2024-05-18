<%@ page import="dao.UserDAO" %>
<%@ page import="bean.Employee" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Employee List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        h2 {
            color: #333;
            text-align:center;
            padding: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
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
    </style>
</head>
<body>
    <h2>Employee List</h2>
    <table>
        <thead>
            <tr>
                <th>Employee ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Birth Date</th>
                <th>Job Title</th>
                <th>Department</th>
            </tr>
        </thead>
        <tbody>
            <% 
                UserDAO userDao = new UserDAO();
                List<Employee> employeeList = userDao.selectAllEmployee();
                for (Employee employee : employeeList) {
            %>
            <tr>
                <td><%= employee.getEmployee_id() %></td>
                <td><%= employee.getFirst_name() %></td>
                <td><%= employee.getLast_name() %></td>
                <td><%= employee.getEmail() %></td>
                <td><%= employee.getBirth_date() %></td>
                <td><%= employee.getJob_title() %></td>
                <td><%= userDao.selectDepartmentByID(employee.getDepartment_id()) %></td>
            </tr>
            <% } %>
        </tbody>
    </table>
    <a href="../Object_Oriented_Project" class="go-back-button">Go Back</a>
</body>
</html>
