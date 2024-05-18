<%@ page import="bean.Departments" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Department List</title>
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
            max-width: 800px;
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
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        table, th, td {
            border: 1px solid #ccc;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #007bff;
            color: white;
        }
        a {
            text-decoration: none;
            color: #007bff;
        }
        a:hover {
            text-decoration: underline;
        }
        .btn {
            display: inline-block;
            padding: 10px 15px;
            font-size: 14px;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
            outline: none;
            color: #fff;
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            box-shadow: 0 2px #999;
        }
        .btn:hover {background-color: #0056b3}
        .btn:active {
            background-color: #0056b3;
            box-shadow: 0 2px #666;
            transform: translateY(2px);
        }
    </style>
    <script type="text/javascript">
        function confirmDelete(departmentId) {
            if (confirm("Are you sure you want to delete this department?")) {
                const form = document.createElement("form");
                form.method = "post";
                form.action = "modifydepartment";

                const input = document.createElement("input");
                input.type = "hidden";
                input.name = "id";
                input.value = departmentId;

                form.appendChild(input);
                document.body.appendChild(form);
                
                form.submit();
            }
        }
    </script>
</head>
<body>
    <div class="container">
        <h2>Department List</h2>
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Actions</th>
            </tr>
            <%
                List<Departments> departmentList = (List<Departments>) request.getAttribute("departmentsList");
                for (Departments department : departmentList) {
            %>
            <tr>
                <td><%= department.getDepartment_id() %></td>
                <td><%= department.getDepartment_name() %></td>
                <td>
                    <button class="btn" onclick="confirmDelete('<%= department.getDepartment_id() %>')">Delete</button>
                </td>
            </tr>
            <% } %>
        </table>
        <a href="add-department.jsp" class="btn">Add New Department</a>
    </div>
    <a href="../Object_Oriented_Project" class="go-back-button">Go Back</a>
</body>
</html>
