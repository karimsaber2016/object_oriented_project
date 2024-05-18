<!DOCTYPE html>
<html>
<head>
    <title>Update User</title>
</head>
<body>
    <h2>Update User</h2>
    <form action="updateuser" method="post">
        <input type="hidden" name="id" value="${user.id}"><br>
        
        <label>First Name:</label>
        <input type="text" name="first_name" value="${user.first_name}" required><br>

        <label>Last Name:</label>
        <input type="text" name="last_name" value="${user.last_name}" required><br>

        <label>Email:</label>
        <input type="email" name="email" value="${user.email}" required><br>

        <label>Birth Date:</label>
        <input type="date" name="birth_date" value="${user.birth_date}" required><br>

        <label>Job Title:</label>
        <input type="text" name="job_title" value="${user.job_title}" required><br>

        <label>Department:</label>
        <input type="text" name="department" value="${user.department}" required><br>

        <input type="submit" value="Update User">
    </form>
</body>
</html>
