<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
    <head>
        <title>NoJunk Food Store</title>
        <link href="css/styles.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script src="js/script.js"></script>
    </head>

    <body>
    <center>
        <img src="img/no-junk-food.png" style="width: 50px; height: 50px"/>
        <h2 style="display: inline">Say No to Junk Food Store</h2>
        <br>
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
                <thead>
                    <tr>
                        <th>Qty</th>
                        <th>Item</th>
                        <th>Price (QR per Kg)</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="product" items="${requestScope.products}">
                        <tr>
                    <input type="hidden" name="productId" value="${product.id}">
                    <td><input TYPE="text" name="qty" class="qty"></td>
                    <td>${product.id}. ${product.name}</td>
                    <td class="price" data-price="${product.price}" data-category="${product.category}"> 
                        ${product.price}
                    </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <div id="total"></div>
            <br>
            <input TYPE="submit" VALUE="Submit">
        </form>
    </center>
</body>
</html>