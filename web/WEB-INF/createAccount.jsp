<%-- 
    Document   : createAccount
    Created on : Apr 10, 2021, 3:31:52 PM
    Author     : 843876
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account Page</title>
    </head>
    <body>
        <h1>Create Account</h1>
        <h2>Add User</h2>
        <form method="POST" action="CreateAccount">
            Email:<input type='email' name='newEmail' ><br>
            First Name:<input type='text' name='firstName' ><br>
            Last Name:<input type='text' name='lastName' ><br>
            Password:<input type='text' name='password' ><br>
            <!--<input type="hidden" name="action" value="create">-->
            <input type="submit" value="create new account">    
        </form>
        ${message}
    </body>
</html>
