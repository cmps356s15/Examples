<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="CSS/styles.css" rel="stylesheet">
        <title>Add book loan</title>
    </head>
    <body>

        <form action="addLoan" method="post">
            <label for="book"> Select a Book  
                <select id="book" name="book" >
                    <c:forEach var="book" items="${books}">
                        <option value="${book.isbn}">${book.title}</option>
                    </c:forEach>
                </select>
            </label>
            </br>
            <label for="borrower"> Select a Borrower 
                <select id="borrower" name="borrower"  >
                    <c:forEach var="borrower" items="${borrowers}">
                        <option value="${borrower.id}">${borrower.firstName} ${borrower.lastName}</option>
                    </c:forEach>
                </select>
            </label>
            </br>
            <input type="submit" value="submit">
        </form>
    </body>
</html>
