<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your shopping cart</title>
    </head>
    <body BGCOLOR="#FDF5E6">
        

        <h1>Your shopping cart:</h1>
        <ul>
            <c:forEach var="item" begin="0" items="${sessionScope.orderedItems}">
                <li>${item}</li>
            </c:forEach>
        </ul>    
            
            
            
    </body>
</html>
