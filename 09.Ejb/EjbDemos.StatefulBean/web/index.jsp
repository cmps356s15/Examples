<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>eShopping</title>
    </head>
    <body>
        <form method="post">
            <select name="ProductName">
                <option value="Chocolate">Chocolate</option>
                <option value="Milk">Milk</option>
                <option value="Bread">Bread</option>
                <option value="Apple">Apple</option>
            </select>
            <br><label for="quantity"></label>
            <input id="quantity" name="quantity" type="text"/>
            <input type="submit" value="Submit"/>
        </form>

        <br><p>You have ${CartItemsCount} items in your shopping cart</p>
        <p>
            <a href="checkout">Checkout</a>
        </p>
    </body>
</html>
