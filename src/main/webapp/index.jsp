<%-- 
    Document   : index
    Created on : 19-Dec-2017, 18:39:24
    Author     : james
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Portfolio Project X</title>
    </head>
    <body>
        <h1>Categories List</h1>
        <table border="1">
            <tr>
                <th>Category ID</th>
                <th>Category Name</th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach var="category" items="${categories}">
                <tr>
                    <td><c:out value='${category.id}' /></td>
                    <td><c:out value='${category.categoryName}' /></td>

                    <td><form action="CategoriesServlet" method="POST">
                            <input type="hidden" name="id" 
                                   value="${category.id}">
                            <input type="submit" 
                                   value="Show Items" name="submit">
                        </form></td>        
                </tr>
            </c:forEach>
            <tr>
                <td></td>
                <td></td>
                <td><form action="CartServlet" method="POST">
                        <input type="submit" value="Show Cart" name="submit">
                    </form></td>
            </tr>
        </table>

    </body>
</html>
