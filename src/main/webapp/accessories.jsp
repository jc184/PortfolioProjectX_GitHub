<%-- 
    Document   : accessories
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
        <h1>Accessories List</h1>
        <table border="1">
            <tr>
                <th>Code</th>
                <th>Name</th>
                <th>Description</th>
                <th class="right">Price</th>
                <th>Category</th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach var="accessory" items="${accessories}">
                <tr>
                    <td><c:out value='${accessory.code}' /></td>
                    <td><c:out value='${accessory.name}' /></td>
                    <td><c:out value='${accessory.description}' /></td>
                    <td class="right">${accessory.priceCurrencyFormat}</td>
                    <td><c:out value='${accessory.category}' /></td>
                    <td><form action="CartServlet" method="POST">
                            <input type="hidden" name="code" 
                                   value="${accessory.code}">
                            <input type="submit" 
                                   value="Add To Cart" name="submit">
                        </form></td>        
                </tr>
            </c:forEach>
        </table>
        <%@include file="/includes/footer_1.jsp" %>
