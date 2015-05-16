<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>eShopping</title>
    </head>
    <body>
        <c:if test='${not empty error}'>
            <p style="font-weight: bold">${error}</p>
        </c:if>
        <c:if test='${empty error}'>
            <p>Thank you for shopping with us. Your order number is ${OrderId}.</p>
        </c:if>
    </body>
</html>
