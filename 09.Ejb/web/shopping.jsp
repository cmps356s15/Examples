<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>eShopping</title>
        <style>
            label {
                display: inline-block;
                min-width: 80px;
                margin-bottom: 10px;
            }
        </style>
    </head>
    <body>
        <form method="post">

            <label for="product">Product</label>
            <select id="product" name="product">
                <option value="Chocolate">Chocolate</option>
                <option value="Milk">Milk</option>
                <option value="Bread">Bread</option>
                <option value="Apple">Apple</option>
                <option value="Banana">Banana</option>
            </select>
            <br><label for="quantity">Quantity</label>
            <input id="quantity" name="quantity" type="text"/>
            <br><input type="submit" value="Submit"/>
        </form>

        <br><p>You have ${CartItemsCount} items in your shopping cart</p>
        Items in your cart:
        <ul>
            <c:forEach var="item" items="${CartItems}">
                <li>
                    ${item.key} (${item.value})
                </li>
            </c:forEach>
        </ul>
        <p>
            <a href="checkout">Checkout</a>
        </p>
    </body>
</html>
