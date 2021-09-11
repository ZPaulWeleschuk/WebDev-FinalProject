<%-- 
    Document   : ResetPassword
    Created on : Apr 20, 2021, 7:06:04 PM
    Author     : 843876
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password Page</title>
    </head>
    <body>
        <h1>Enter a new password</h1>
        <form name="ForgotPassword" method="POST">
            password:<input type="text" name="newPassword" value="">
            <input type="hidden" name="uuid" value="${uuid}">
            <input type="submit" value="submit">
        </form>
    </body>
</html>
