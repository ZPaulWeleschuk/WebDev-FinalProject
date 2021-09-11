<%-- 
    Document   : forgotPassword
    Created on : Apr 19, 2021, 12:57:22 PM
    Author     : 843876
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot Password Page</title>
    </head>
    <body>
        <h1>Forgot Password</h1>
        <form name="ForgotPassword" method="POST">
        Enter your account Email:<input type="email" name="resetPasswordEmail"><br>
        <input type="submit" value="Reset Password">
        
        </form>
        ${message}
    </body>
</html>
