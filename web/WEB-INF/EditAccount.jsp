<%-- 
    Document   : EditAccount
    Created on : Apr 10, 2021, 5:45:40 PM
    Author     : 843876
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Account Information Page</title>
    </head>
    <body>
        <h1>Edit Account Information</h1>
        <a href="Inventory">Inventory</a><br>
        <a href="Login">Logout</a><br>
        <h2>edit Account Information</h2>
        <form method="POST" action="EditAccount">
            Email:<input type='email' name='newEmail' value='${email}' readonly><br>
            First Name:<input type='text' name='firstName' value='${firstName}'><br>
            Last Name:<input type='text' name='lastName' value='${lastName}'><br>
            Password:<input type='text' name='password' value='${password}'><br>
            <input type="hidden" name="action" value="update">
            <input type="submit" value="Save Edit">   
        </form>
        <br>
        <br>
        <form method="POST" action="EditAccount">
            <input type="hidden" name="action" value="deactivate">
            Deactivate Account:<input type="submit" value="Yes, Deactivate Account">
        </form>
        ${message}
    </body>
</html>
