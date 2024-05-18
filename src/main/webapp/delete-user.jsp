<!DOCTYPE html>
<html>
<head>
    <title>Delete User</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        h2 {
            color: #333;
            text-align: center;
        }
        .container {
            max-width: 600px;
            margin: auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        form {
            margin-top: 20px;
        }
        input[type="text"] {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 3px;
            box-sizing: border-box;
        }
        input[type="submit"], a {
            display: inline-block;
            padding: 8px 16px;
            margin-top: 10px;
            border: none;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            border-radius: 3px;
            cursor: pointer;
        }
        input[type="submit"]:hover, a:hover {
            background-color: #0056b3;
        }
        .employee-info {
            margin-bottom: 15px;
            padding: 10px;
        }
        .button-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Delete User</h2>
        <p>Are you sure you want to delete this user?</p>
        <form action="deleteuser" method="post">
            <div class="employee-info">
                <input type="text" id="employeeID" name="employeeID" placeholder="Employee ID" required>
            </div>
            <div class="button-container">
                <input type="submit" value="Delete User">
                <a href="../Object_Oriented_Project" class="go-back-button">Cancel</a>
            </div>
        </form>
    </div>
</body>
</html>
