<%-- 
    Document   : clothing
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
        <h1>Clothing List</h1>
        <table border="1">
            <tr>
                <th>Code</th>
                <th>Name</th>
                <th>Description</th>
                <th class="right">Price</th>
                <th>Category</th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach var="clothingitem" items="${clothing}">
                <tr>
                    <td><c:out value='${clothingitem.code}' /></td>
                    <td><c:out value='${clothingitem.name}' /></td>
                    <td><c:out value='${clothingitem.description}' /></td>
                    <td class="right">${clothingitem.priceCurrencyFormat}</td>
                    <td><c:out value='${clothingitem.category}' /></td>
                    <td><form action="CartServlet" method="POST">
                            <input type="hidden" name="code" 
                                   value="${clothingitem.code}">
                            <input type="submit" 
                                   value="Add To Cart" name="submit">
                        </form></td>        
                </tr>
            </c:forEach>
        </table>
        <%@include file="/includes/footer_1.jsp" %>
