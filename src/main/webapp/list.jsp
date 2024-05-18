<%@ page import="dao.UserDAO" %>
<%@ page import="bean.Employee" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Employee List</title>
</head>
<body>
    <h2>Employee List</h2>
    <table border="1">
        <thead>
            <tr>
                <th>Employee ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Birth Date</th>
                <th>Job Title</th>
                <th>Department ID</th>
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
                <td><%= employee.getDepartment_id() %></td>
            </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>
