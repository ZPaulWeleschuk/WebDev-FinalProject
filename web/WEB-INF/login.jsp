<%-- 
    Document   : login
    Created on : Mar 25, 2021, 12:31:35 PM
    Author     : 843876
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Home Inventory</h1>
        <h2>Login</h2>
        <form method="post" action="Login">
            Username: <input type="text" name="email" value="${email}"><br>
            Password: <input type="text" name="password" value="${passworld}"><br>
            <input type="submit" value="Login">
        </form>
        ${message}
        <br> <a href="ForgotPassword">Forgot Password</a>
        <br> <a href="CreateAccount">Create new account</a>
        
    </body>
</html>
