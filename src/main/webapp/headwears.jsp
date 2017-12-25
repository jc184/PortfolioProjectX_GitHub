<%-- 
    Document   : headwears
    Created on : 20-Dec-2017, 00:20:38
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
        <h1>Headgear List</h1>
        <table border="1">
            <tr>
                <th>Code</th>
                <th>Name</th>
                <th>Description</th>
                <th class="right">Price</th>
                <th>Category</th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach var="headwear" items="${headwears}">
                <tr>
                    <td><c:out value='${headwear.code}' /></td>
                    <td><c:out value='${headwear.name}' /></td>
                    <td><c:out value='${headwear.description}' /></td>
                    <td class="right">${headwear.priceCurrencyFormat}</td>
                    <td><c:out value='${headwear.category}' /></td>
                    <td><form action="CartServlet" method="POST">
                            <input type="hidden" name="code" 
                                   value="${headwear.code}">
                            <input type="submit" 
                                   value="Add To Cart" name="submit">
                        </form></td>        
                </tr>
            </c:forEach>
        </table>
        <%@include file="/includes/footer_1.jsp" %>
