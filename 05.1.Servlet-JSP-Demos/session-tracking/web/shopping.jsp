<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
    <head>
        <title>Shopping</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
         <script src="js/script.js"></script>
    </head>
    
    <body>
    <center>
        <h2>Shopping</h2>

        <label>Product Category
            <select id = "productCategory" name="productCategory" required>
                <c:forEach var="productCategory" items="${requestScope.productsCategory}">
                    <option value='${productCategory.code}'
                            ${productCategory.code eq requestScope.selectedCategory ? "selected" : "" }>
                        ${productCategory.name}
                    </option>
                </c:forEach>
            </select>
        </label>

        <br><br>
        
        <form action="shop" method="POST">
            <table>
                <tr>
                    <td>Qty</td>
                    <td>Item</td>
                    <td>Price</td>
                </tr>
                <c:forEach var="product" items="${requestScope.products}">
                    <tr>
                        <td><input TYPE="text" name="qty" class="qty"></td>
                        <td>${product.id}. ${product.name}</td>
                        <td class="price" data-price="${product.price}"> QR${product.price}</td>
                    </tr>
                </c:forEach>

            </table>
            <div id="total"></div>
            <br>
            <input TYPE="submit" VALUE="Submit">
        </form>
    </center>
</body>
</html>