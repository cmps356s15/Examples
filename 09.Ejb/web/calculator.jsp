<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>

<html>
    <head>
        <title>Calculator</title>
    </head>
    <body>
        <h1>Calculator</h1>
        <form action="calculator" method="post">
            <input type="text" name="num1" size="3"/>+
            <input type="text" name="num2" size="3"/>
            <input type="submit" value="Add"  />
        </form>
    </body>

    <c:if test='${not empty result}'>
        <p style="font-weight: bold">${result}</p>
        <c:remove var="result" scope="session" />
    </c:if>
</html>

