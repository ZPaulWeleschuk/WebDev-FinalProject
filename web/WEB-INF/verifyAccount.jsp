<%-- 
    Document   : verifyAccount
    Created on : Apr 21, 2021, 2:37:03 PM
    Author     : 843876
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verify Account Page</title>
    </head>
    <body>
        <h1>Inventory App</h1>
        <h2>Verify Account</h2>
        <p>Click button to verify account.</p>
        <form method="POST" action="CreateAccount">
            <input type="hidden" name="active" value="true"><!--not sure if we need to use this-->
            <input type="hidden" name="uuid" value="${uuid}">
            <input type="submit" value="verify">
        </form>
        
    </body>
</html>
