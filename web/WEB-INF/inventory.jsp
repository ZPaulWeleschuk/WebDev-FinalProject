<%-- 
    Document   : inventory
    Created on : Mar 25, 2021, 12:31:25 PM
    Author     : 843876
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inventory Page</title>
    </head>
    <body>
        <h1>Home Inventory</h1>
        <h3>Menu</h3>
        <a href="Inventory">Inventory</a><br>
        <a href="Admin">Admin</a><br><!--perhaps we should remove this feature for reg users-->
        <a href="EditAccount">Edit account information</a><br>
        <a href="Login">Logout</a><br>

        <h2>Inventory for ${firstName} ${lastName}</h2>
        <table>
            <tr>
                <th>Category</th>
                <th>Item Name</th>
                <th>Price</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>

            <c:forEach var="item" items="${items}">
                <tr>
                    <td>${item.getCategory().getCategoryName()}</td>
                    <td>${item.itemName}</td>
                    <td>${item.price}</td>
                    <td> <form method="GET" action="Inventory">
                            <input type="submit" value="edit">
                            <input type="hidden" name="itemID" value="${item.itemId}">
                            <input type="hidden" name="itemAction" value="itemEdit">
                        </form></td>
                    <td><form method="POST" action="Inventory">
                            <input type="submit" value="delete">
                            <input type="hidden" name="itemID" value="${item.itemId}">
                            <input type='hidden' name='itemAction' value='itemDelete'>
                        </form></td>
                </tr>      
            </c:forEach>
        </table>
        ${message}

        <c:if test="${selectedItem eq null}">
            <h2> Add Item</h2>
            <form method='POST' action='Inventory'>
                Category: <select name="category">
                    <c:forEach var="category" items="${categorys}">
                        <option value="${category.getCategoryId()}">${category.getCategoryName()}</option>
                    </c:forEach></select><br>
                Name: <input type="text" name="itemName"><br>
                Price: <input type="text" name="price" min="0"><br>
                <input type='hidden' name='itemAction' value='itemAdd'>
                <input type='submit' value='addItem'>
            </form>
        </c:if>

        <c:if test="${selectedItem ne null}">
            <h2>Edit Item</h2>
            <form method='POST' action='Inventory'>
                Category: <select name="category">
                    <c:forEach var="category" items="${categorys}">
                        <option value="${category.getCategoryId()}">${category.getCategoryName()}</option>
                    </c:forEach></select><br>
                Name: <input type="text" name="itemName" value='${selectedItem.itemName}'><br>
                Price: <input type="text" name="price" min="0" value='${selectedItem.price}'><br>
                <input type='hidden' name='itemID' value='${selectedItem.itemId}'>
                <input type='hidden' name='itemAction' value='itemEdit'>
                <input type='submit' value='Save Item Edit'>
            </form>
        </c:if>

    </body>
</html>
