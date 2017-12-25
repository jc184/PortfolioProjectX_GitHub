<%-- 
    Document   : placeorder
    Created on : 21-Dec-2017, 12:18:45
    Author     : james
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <h1>Your Order</h1>
        <p>${message}</p>
        <table border="1">
            <tr>
                <th>Quantity</th>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Amount</th>
                <th></th>
            </tr>
            <c:forEach var="item" items="${cart.items}">
                <tr>
                    <td><form action="" method="post">
                            <input type="hidden" name="code" value="<c:out value='${item.product.code}'/>" />
                            <input type="text" name="quantity" value="<c:out value='${item.quantity}'/>" />
                            <input type="submit" value="Update" name="submit"/>
                        </form></td>
                    <td><c:out value='${item.product.name}'/></td>    
                    <td><c:out value='${item.product.description}'/></td>
                    <td>${item.product.priceCurrencyFormat}</td>
                    <td>${item.totalCurrencyFormat}</td>
                    <td><form action="" method="post">
                            <input type="hidden" name="code" 
                                   value='${item.product.code}'/>
                            <input type="hidden" name="quantity" value="0">
                            <input type="submit" value="Remove Item" name="submit">
                        </form></td>
                </tr>
            </c:forEach>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td><c:out value='${totalSum}'/></td>
                <td><form action="" method="post">
                        <input type="hidden" name="action" value="checkout">
                        <input type="submit" value="Place Order" name="submit">
                    </form></td>
            </tr>


        </table>
        <p><b>To change the quantity</b>, enter the new quantity 
            and click on the Update button.</p>

        
        <c:forEach var="item" items="${cart.items}">

            <form action="CartServlet" method="post">
                <input type="hidden" name="code" value='${item.product.code}'/>
                <input type="submit" value="Continue Shopping" name="submit">
            </form>

        </c:forEach>

    </body>
</html>
