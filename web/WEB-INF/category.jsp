<%-- 
    Document   : category
    Created on : Apr 16, 2021, 12:46:30 PM
    Author     : 843876
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View and Edit Category Page</title>
    </head>
    <body>
        <h1>View and Edit Category</h1>
        <h3>Menu</h3>
        <a href="Admin">Admin</a><br>
        <a href="Login">Logout</a><br>
        <table>
            <tr>
                <th>Category Id</th>
                <th>Category Name</th>
            </tr>
            
            <c:forEach var='category' items="${categories}">
                <tr>
                <td>${category.categoryId}</td>
                <td>${category.categoryName}</td>
                <td><form method="GET" action="Category">
                        <input type="submit" value="Edit">
                        <input type="hidden" name="action" value="loadEditCategory">
                        <input type="hidden" name="categoryId" value="${category.categoryId}">
                    </form>
                </tr>
                </c:forEach>
        </table>
        <c:if test="${selectedCategory eq null}">
            <h2>Add New Category</h2>
            <form method="POST" action="Category">
                Category Name:<input type="text" name="categoryName">   
                <input type="submit" value="Add">
                <input type="hidden" name="action" value="addCategory">
            </form>  
        </c:if>
            
        <c:if test="${selectedCategory ne null}">
            <h2>Edit Category</h2>
            <form method="POST" action="Category">
                Category Name:<input type="text" name="categoryName" value="${selectedCategory.categoryName}">   
                <input type="submit" value="Save Edit">
                <input type="hidden" name="action" value="editCategory">
                <input type="hidden" name="categoryId" value="${selectedCategory.categoryId}">
            </form>
        </c:if>

            ${message}
    </body>
</html>
