<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
    <head>
        <title>NoJunk Food Store</title>
        <link href="css/styles.css" rel="stylesheet">
    </head>

    <body>
    <center>
        <h2>Your submitted order:</h2>
        <table>
            <thead>
                <tr>

                    <th>Item</th>
                    <th>Qty</th>
                    <th>Price</th>
                    <th>Item Total (QR)</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="orderItem" items="${requestScope.order}">
                    <tr>
                        <td>${orderItem.product.name}</td>
                        <td>${orderItem.quantity}</td>
                        <td>${orderItem.product.price}</td>
                        <td>${orderItem.itemTotal}</td>
                    </tr>
                    <c:set var='total' value='${total + orderItem.itemTotal}' />
                </c:forEach>
            </tbody>
            <tfoot>
                <tr>
                    <td colspan="3">Total</td>
                    <td>${total}</td>
                </tr>
            </tfoot>
        </table>
        <div id="total"></div>
    </form>
    
    <br>
    <a href="shop">Back to Shopping</a>
</center>
</body>
</html>