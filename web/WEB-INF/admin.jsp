<%-- 
    Document   : admin
    Created on : Mar 25, 2021, 12:31:07 PM
    Author     : 843876
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <h1>Home Inventory</h1>
        <h3>Menu</h3>
        <a href="Inventory">Inventory</a><br>
        <a href="Admin">Admin</a><br>
        <a href='Category'>View, Add, Edit Categories</a><br>
        <a href="Login">Logout</a><br>
        <h2>Manage Users</h2>
        <!--table with for each-->
        <table>
            <tr>
                <th>Email</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Active Status</th>
                <th>Activate</th>
                <th>Delete</th>
                <th>Edit</th>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>

                    <td>${user.email}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.active}</td>
                    <td><form method="POST" action="Admin">
                            <input type="submit" value="Activate">
                            <input type='hidden' name='userEmail' value='${user.email}'>
                            <input type='hidden' name='action' value='activate'>
                        </form>
                    <td><form method="POST" action="Admin">
                            <input type="submit" value="delete">
                            <input type='hidden' name='userEmail' value='${user.email}'>
                            <input type="hidden" name="action" value="delete">
                        </form>
                    </td>
                    <td><form method="GET" action="Admin">
                            <input type="submit" value="edit">
                            <input type='hidden' name='userEmail' value='${user.email}'>
                            <input type="hidden" name="action" value="updateView">
                        </form>
                    </td>

                </tr>
            </c:forEach>
        </table>

        <c:if test="${selectedUser eq null}">
            <h2>Add User</h2>
            <form method="POST" action="Admin">
                Email:<input type='email' name='newEmail' ><br>
                First Name:<input type='text' name='firstName' ><br>
                Last Name:<input type='text' name='lastName' ><br>
                Password:<input type='text' name='password' ><br>
                <input type="hidden" name="action" value="create">
                <input type="submit" value="create">    
            </form>
        </c:if>


        <c:if test="${selectedUser ne null}">
            <h2>Edit User</h2>
            <form method="POST" action="Admin">
                Email:<input type='email' name='newEmail' value='${selectedUser.email}' readonly><br>
                First Name:<input type='text' name='firstName' value='${selectedUser.firstName}'><br>
                Last Name:<input type='text' name='lastName' value='${selectedUser.lastName}'><br>
                Password:<input type='text' name='password' value='${selectedUser.password}'><br>
                <input type="hidden" name="action" value="update">
                <input type="submit" value="Save Edit">    
            </form>
        </c:if>

    </body>
</html>
