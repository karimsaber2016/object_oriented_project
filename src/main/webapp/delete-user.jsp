<!DOCTYPE html>
<html>
<head>
    <title>Delete User</title>
</head>
<body>
    <h2>Delete User</h2>
    <p>Are you sure you want to delete this user?</p>
    <form action="deleteuser" method="post">
        <input type="hidden" name="id" value="${user.id}">
        
        <p>Employee ID: ${user.id}</p>
        
        <input type="submit" value="Delete User">
    </form>
    <a href="list">Cancel</a>
</body>
</html>
