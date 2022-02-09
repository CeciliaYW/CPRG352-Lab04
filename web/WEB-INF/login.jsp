
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h2>Login</h2>
        <form method="post" action="login">
        <label>Username: </label>
        <input type="text" name="u_name" value="${username}">
        <br>
        <label>Password: </label>
        <input type="password" name="u_password" value="${userpassword}">
        <br>
        <input type="submit" value="Log in">
        </form>
        <p>${error_message}</p>
        <p>${logout_message}</p>
    </body>
</html>
