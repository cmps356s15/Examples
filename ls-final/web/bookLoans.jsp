<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head >
        <meta charset="UTF-8">
        <link href="CSS/styles.css" rel="stylesheet">
        <title>Book Loans</title>
    </head>
    <body>
        <h2>Book Loans</h2>
        <table id="loanTable" name="loanTable" >
            <thead>
                <tr>
                    <th> Loan ID</th>
                    <th> Borrower ID</th>
                    <th> Borrower Name</th>
                    <th> Book ISBN </th>
                    <th> Book Title</th>
                    <th> Due Date</th>

                </tr>
            </thead>
            <tbody>
                <c:forEach var="loan" items="${loans}">
                    <tr>
                        <td> ${loan.id} </td>
                        <td> ${loan.borrower.id} </td>
                        <td> ${loan.borrower.firstName} ${loan.borrower.lastName} </td>
                        <td> ${loan.book.isbn} </td>
                        <td> ${loan.book.title} </td>
                        <td>
                            <fmt:formatDate value="${loan.dueDate}" pattern="dd/MM/yyyy" />
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </form>
    
    <br>
    <a href="addLoan">Add Loan</a>
</body>
</html>